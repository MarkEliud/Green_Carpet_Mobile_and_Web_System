package com.mk.Green.Supervisor.Fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Adapters.Image1;
import com.mk.Green.Supervisor.Adapters.assignAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class assignCleaner extends Fragment {
    private Button toolbar;
    assignAdapter adapter;
    private ArrayList<Image> selectedItems = new ArrayList<>();
    private static final String URL_PRODUCTS = LoginActivity.ip+"recyclerview/assignCleaner.php";
    List<Image> productList;
    RecyclerView recyclerView;
    public assignCleaner() {
    }
    String details, code, disability,caretype,status,price,date,email,parent;




    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.assignsel, container, false);
        Bundle b = this.getArguments();
        if(b != null) {
            code = b.getString("code");
        }
        toolbar = v.findViewById(R.id.toolbar);
//        v.getActivity().setSupportActionBar(toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
                                        @SuppressLint("SetTextI18n")
                                        @Override
                                        public void onClick(View view) {
                                           // loadProgrammes();
                                         //   if (productList.getItemId() == R.id.action_done) {
                                                selectedItems = (ArrayList<Image>) adapter.getSelectedItems();
                                                StringBuilder message = new StringBuilder();
                                                message.append("Selected Cleaner Details:\n");

                                                for (Image item : selectedItems) {
                                                    message.append("Name: ").append(item.getFullname()).append("\n");
                                                            message.append("Email: ").append(item.getEmail()).append("\n");
                                                }



                                           // String size=Integer.toString(selectedItems.size()) ;
                                               // message.append("Total cleaners Selected: ").append(size);

                                                Toast.makeText(getActivity(), message.toString(), Toast.LENGTH_SHORT).show();
                                            updateActivity(message.toString(),code);
                                           // quantityDialog.dismiss();


                                        }
                                    });

        recyclerView = v.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productList = new ArrayList<Image>();
        loadProgrammes();
        SwipeRefreshLayout mSwipeRefreshLayout;
        mSwipeRefreshLayout=v.findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //perform your action here
                recyclerView = v.findViewById(R.id.recylcerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                productList = new ArrayList<Image>();
                loadProgrammes();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });




        return v;
    }
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
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Image(

                                        product.getString("fullname"),
                                        product.getString("email")


                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                             adapter = new assignAdapter(getActivity(), productList,code);
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

    private void updateActivity(final String cleaners,final String code) {
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
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                if (response.equals("success")) {
                    Toast.makeText(getActivity(), "Proceed to select driver to ship the c" +
                            "cleaners", Toast.LENGTH_SHORT).show();
                    Bundle k = new Bundle();
                    k.putString("code", code);
                    // Toast.makeText(getActivity(), "allocate Cleaner", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    assignDriver fragB = new assignDriver();
                    fragB.setArguments(k);
                    transaction.replace(R.id.my_frame_layout,fragB);
                    transaction.
                            commit();
                    //  Toast.makeText(getActivity(), "allocated driver", Toast.LENGTH_SHORT).show();
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
                param.put("status", "selectcleaners");
                param.put("code", code);
                param.put("cleaners",cleaners);


                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }

}