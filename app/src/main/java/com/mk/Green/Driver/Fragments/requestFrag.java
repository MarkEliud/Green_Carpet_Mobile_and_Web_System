package com.mk.Green.Driver.Fragments;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.Inventory.Fragments.assignSupplier;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Fragments.assignCleaner;
import com.mk.Green.Supervisor.Fragments.assignDriver;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class requestFrag extends Fragment {


    List<Image> productList;
    RecyclerView recyclerView;
    public requestFrag() {
    }
    String cleaner, name, address,date,cname,code,email,namer="Choose Product Name";
EditText pname,desc,pack;
    // String name, description, image, title, amount, date, email, address, code, status;
    private AutoCompleteTextView dropdown;//Dropdown
    private EditText description;
    private Spinner spinner;
    private EditText editText;
    private ProgressDialog progressDialog;
    private ArrayList<String> productNames;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.requestinv, container, false);
        Bundle b = this.getArguments();
        if(b != null){





        }
        //ImageView imagev = v.findViewById(R.id.image_view);
         pname = v.findViewById(R.id.name);
        pname.setVisibility(View.GONE);
        pack = v.findViewById(R.id.pck);
        desc = v.findViewById(R.id.desc);
        spinner = v.findViewById(R.id.dropdown);
        //editText = findViewById(R.id.editText);

        productNames = new ArrayList<>();
        productNames.add("Choose Product Name");
        // Make the initial Volley request to fetch product names
        makeRequestForProductNames();
        Button done= v.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pack1=pack.getText().toString();
                String desc1=desc.getText().toString();
                pname.setText(namer);
                String pname1=pname.getText().toString();

                Toast.makeText(getActivity(), namer, Toast.LENGTH_SHORT).show();

                if(pname1.isEmpty()||pack1.isEmpty()||desc1.isEmpty()){


                    Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if(pname1.equals("Choose Product Name")){
//
//
//                    Toast.makeText(getActivity(), "Choose Product Name", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                Bundle k = new Bundle();

                k.putString("pname", pname1);

               // if(!namer.equals("")){
                 //   k.putString("pname", namer);
                    k.putString("pack", pack1);
                    k.putString("desc", desc1);


                    // Toast.makeText(getActivity(), "allocate Cleaner", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    assignSupplier fragB = new assignSupplier();
                    fragB.setArguments(k);
                    transaction.replace(R.id.my_frame_layout,fragB);
                    transaction.
                            commit();

            }
        });






        return v;
    }


    private void makeRequestForProductNames() {
        String url =  LoginActivity.ip+"recyclerview/getitems.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Handle the response JSON to populate the dropdown menu
                            handleResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getActivity(), "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }
// ... Existing code ...

    private void handleResponse(JSONObject response) throws JSONException {
        // Parse the JSON response and populate the dropdown menu
        JSONArray productsArray = response.getJSONArray("products");
        for (int i = 0; i < productsArray.length(); i++) {
            productNames.add(productsArray.getString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Set an OnItemSelectedListener on the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected product name
                String selectedProductName = productNames.get(position);
                if (selectedProductName.equals("Choose Product Name")) {
                    desc.setText(null);
                   // desc.setHint("set desc");// Clear the EditText
                    pname.setText(selectedProductName);
                    namer = "";
                    return;
                }

else{
                    namer = selectedProductName;
                    makeRequestForDescription(selectedProductName);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Description...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    private void makeRequestForDescription(String productName) {
        String url = LoginActivity.ip + "recyclerview/getitems.php?name=" + productName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Handle the response JSON to set the description in the EditText
                            String description = response.getString("description");

                            // Remove any HTML tags from the description
                            String cleanDescription = android.text.Html.fromHtml(description).toString();
                            desc.setText(cleanDescription);
                            hideProgressDialog();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }

                        // Hide the progress dialog once the description is set
                        hideProgressDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getActivity(), "Error fetching description", Toast.LENGTH_SHORT).show();
                        // Hide the progress dialog if there's an error
                        hideProgressDialog();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

//    private void makeRequestForDescription(String productName) {
//        String url = LoginActivity.ip + "recyclerview/getitems.php?name=" + productName;
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            // Handle the response JSON to set the description in the EditText
//                            String description = response.getString("description");
//
//                            // Remove any HTML tags from the description
//                            String cleanDescription = android.text.Html.fromHtml(description).toString();
//
//                            desc.setText(cleanDescription);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getActivity(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                        Toast.makeText(getActivity(), "Error fetching description", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(jsonObjectRequest);
//    }


}