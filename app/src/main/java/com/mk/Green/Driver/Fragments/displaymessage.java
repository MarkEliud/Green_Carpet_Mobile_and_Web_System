package com.mk.Green.Driver.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Driver.Adapters.flightModel;
import com.mk.Green.Driver.Adapters.receAdapter;
import com.mk.Green.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class displaymessage extends Fragment {

    private static final String URL_PRODUCTS =LoginActivity.ip+ "recyclerview/message.php";

    //a list to store all the products
    List<flightModel> productList;

    //the recyclerview
    RecyclerView recyclerView;
    public displaymessage() {
        // Required empty public constructor
    }
    String email,plane,dest,date,status,planeid;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_programes, container, false);
        Bundle b = this.getArguments();

        if(b != null){
            status = b.getString("user");


        }

        recyclerView = v.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        productList = new ArrayList<>();

        loadProgrammes();
        return v;
    }
    private void loadProgrammes() {



        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS+"?status="+status,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //create a hash set to store unique senders
                            Set<String> uniqueSenders = new HashSet<>();

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                String sender = product.getString("sender");

                                //check if the sender has already been added to the hash set
                                if (uniqueSenders.add(sender)) {
                                    //adding the product to product list
                                    productList.add(new flightModel(sender));
                                }
                            }

                            //creating adapter object and setting it to recyclerview
                            receAdapter adapter = new receAdapter(getActivity(), productList,status);
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

        Volley.newRequestQueue(requireActivity()).add(stringRequest);

    }

}