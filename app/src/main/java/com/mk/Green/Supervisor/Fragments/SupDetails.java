package com.mk.Green.Supervisor.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.Inventory.Adapters.incomingAdapter;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Adapters.Image1;
import com.mk.Green.Supervisor.Adapters.itemIncomingAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class SupDetails extends Fragment {

    itemIncomingAdapter adapter;
    List<Image1> productList;
    RecyclerView recyclerView;
    public SupDetails() {
    }
    //String details, name, disability,caretype,status,price,date,email,parent,code;

    String name, description, image, title, amount, date, email, address, code, status;

    private ArrayList<Image1> selectedItems = new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.supalocate, container, false);
        Bundle b = this.getArguments();
        if(b != null){
            title = b.getString("title");
            description = b.getString("description");
            image = b.getString("image");
            name = b.getString("name");
            amount = b.getString("amount");
            date = b.getString("date");
            email = b.getString("email");
            address = b.getString("address");
            code = b.getString("code");
            status = b.getString("status");

        }
        ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        nameq.setText("Customer's Name:" + name + "\n" + "Service's Name: " + title + "\n" + "Description: " + description + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount paid: " + amount + "\n" + "Date: " + date + "\n" + "Status: " + status + "\n");
        Picasso.get().load(image).into(imagev);

        Button done= v.findViewById(R.id.done);
        Button prd= v.findViewById(R.id.prd);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if(caretype.equals("Long")){
                    Bundle k = new Bundle();
                    k.putString("code", code);
                   // Toast.makeText(getActivity(), "allocate Cleaner", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    assignCleaner fragB = new assignCleaner();
                    fragB.setArguments(k);
                    transaction.replace(R.id.my_frame_layout,fragB);
                    transaction.
                            commit();



            }
        });
        prd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog quantityDialog = new Dialog(view.getContext());
                quantityDialog.setContentView(R.layout.displaykid_);
                quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                quantityDialog.setCancelable(false);
                recyclerView = quantityDialog.findViewById(R.id.recylcerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                productList = new ArrayList<>();

                loadProgrammes();



                Button cancelBtn = quantityDialog.findViewById(R.id.cancel_btn);
                Button okBtn = quantityDialog.findViewById(R.id.ok_btn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quantityDialog.dismiss();
                    }
                });
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        selectedItems = (ArrayList<Image1>) adapter.getSelectedItems();
                        StringBuilder message = new StringBuilder();
                        message.append("Assigned Cleaning Items:\n");

                        for (Image1 item : selectedItems) {
                            message.append("Product name: ").append(item.getName()).append("\n");
                            message.append("Quantity: ").append(item.getQty()).append("\n");
                            message.append("Description: ").append(item.getDescription()).append("\n");
                        }
                        message.append("Total Items Assigned: ").append(selectedItems.size());

                        Toast.makeText(getActivity(), code, Toast.LENGTH_SHORT).show();
                        updateActivity(message.toString(),code);
                        quantityDialog.dismiss();
                    }
                });
                quantityDialog.show();



            }
        });






        return v;
    }
    public static final String URL_PRODUCTS =LoginActivity.ip+ "recyclerview/displayproducts.php";
    private void loadProgrammes() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject jsonObject = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Image1(

                                        jsonObject.getString("name"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("packages"),
                                        jsonObject.getString("imageUrl"),
                                        jsonObject.getString("qty")
                                        , jsonObject.getString("id")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new itemIncomingAdapter(getActivity(), productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(requireActivity()).add(stringRequest);
    }
    private void updateActivity(final String items,final String imageurl) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,LoginActivity.ip+"recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Record updated successfully")) {

                   Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    // finish();
                } else {
                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("status", "selectitems");
                param.put("imageurl", imageurl);
                param.put("items",items);


                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }
}