package com.mk.Green.Cleaner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.mk.Green.Cleaner.Adapters.approvedAdapter;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Adapters.approvedAdapter1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class approvedFrag extends Fragment {

    //private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/pending.php";
    public static final String URL_PRODUCTS = LoginActivity.ip+"recyclerview/cleanerComp.php";
    List<Image> productList;
    RecyclerView recyclerView;
    public approvedFrag() {
    }
    String email;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_programes, container, false);
        Bundle b = this.getArguments();
         if(b != null){
        email = b.getString("email");

        }

        recyclerView = v.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productList = new ArrayList<>();
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
                productList = new ArrayList<>();
                loadProgrammes();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }
    private void loadProgrammes() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS+"?email="+email,
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
                                productList.add(new Image(

                                        jsonObject.getString("name"),
                                        jsonObject.getString("date"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("imageUrl"),

                                        jsonObject.getString("status"),
                                        jsonObject.getString("customerName"),
                                        jsonObject.getString("code"),
                                        jsonObject.getString("address"),
                                        jsonObject.getString("amount"),
                                        jsonObject.getString("fprice"),
                                        jsonObject.getString("email")
                                      //  jsonObject.getString("items")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            approvedAdapter adapter = new approvedAdapter(getActivity(), productList);
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