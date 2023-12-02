package com.mk.Green.ServiceManager.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Adapters.assignAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class assignCleaner extends Fragment {
    private Button toolbar;
    assignAdapter adapter;
    private ArrayList<Image> selectedItems = new ArrayList<>();
    private static final String URL_PRODUCTS =LoginActivity.ip+ "recyclerview/assignCleaner.php";
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
                                                message.append("Selected Items:\n");
                                                for (Image item : selectedItems) {
                                                    message.append("Name: ").append(item.getFullname()).append(", Email: ").append(item.getEmail()).append("\n");
                                                }
                                                message.append("Total Items Selected: ").append(selectedItems.size());

                                                Toast.makeText(getActivity(), message.toString(), Toast.LENGTH_SHORT).show();


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



}