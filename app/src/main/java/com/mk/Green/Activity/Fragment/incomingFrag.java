package com.mk.Green.Activity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

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
import com.mk.Green.Activity.Adapters.incomingAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class incomingFrag extends Fragment {

    //private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/pending.php";
    public static final String URL_PRODUCTS = LoginActivity.ip+"recyclerview/displayservice.php";
    List<Image> productList;
    RecyclerView recyclerView;
    public incomingFrag() {
    }
    String name,address,email,category;

    private ArrayList<Image> originalList = new ArrayList<>();
    private ArrayList<Image> filteredList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.assignsel, container, false);
        Bundle b = this.getArguments();
        if(b != null){
            name = b.getString("name");
            address = b.getString("address");
            email = b.getString("email");
            category = b.getString("category");
            // Toast.makeText(getActivity(), name+address+email, Toast.LENGTH_SHORT).show();
        }
        SearchView searchView = v.findViewById(R.id.searchView);

        // Set up the OnQueryTextListener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Call the filter method with the new text
                filter(newText);
                return false;
            }
        });
       Button toolbar = v.findViewById(R.id.toolbar);
         toolbar.setVisibility(View.INVISIBLE);
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


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS+"?category="+category,
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

                                        jsonObject.getString("size"),
                                        jsonObject.getString("imageUrl"),
                                        jsonObject.getString("price"),
                                        jsonObject.getString("desc"),
                                        jsonObject.getString("id"),
                                        jsonObject.getString("category"),
                                        jsonObject.getString("category")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            incomingAdapter adapter = new incomingAdapter(getActivity(), productList,name,address,email);
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
    private void filter(String text) {
        filteredList.clear();

        if (text.isEmpty()) {
            filteredList.addAll(productList);
        } else {
            text = text.toLowerCase();
            for (Image item : productList) {
                if (
                        item.getDesc().toLowerCase().contains(text)||
                        item.getPrice().toLowerCase().contains(text)||
                        item.getSize().toLowerCase().contains(text)



                ) {
                    filteredList.add(item);
                }
            }
        }
        incomingAdapter adapter = new incomingAdapter(getActivity(), filteredList,name,address,email);
      //  incomingAdapter adapter = new incomingAdapter(getActivity(), filteredList);
        recyclerView.setAdapter(adapter);
    }
}