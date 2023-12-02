package com.mk.Green.Activity.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//import com.mk.Green.Activity.Adapters.CleaningServiceAdapter.OnItemClickListener;

import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.Adapters.ImagesAdapter;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServicesDisplay extends Fragment {
    private RecyclerView recyclerView;
    private ImagesAdapter imageAdapter;
    private List<Image> imageList;
    public static final String url = LoginActivity.ip+"recyclerview/cusdisplay.php";
    public ServicesDisplay() {
        // Required empty public constructor
    }
   String name, dob, gender,email,date;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.activity_images, container, false);
        Bundle b = this.getArguments();
        if(b != null){
            email = b.getString("email");
         //   Toast.makeText(getActivity(), email, Toast.LENGTH_SHORT).show();
            name = b.getString("name");
            //address = b.getString("address");
        }
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        imageList = new ArrayList<>();
        imageAdapter = new ImagesAdapter(getActivity(), imageList, name);
        recyclerView.setAdapter(imageAdapter);

        loadImages(email);
        return v;
    }
    private void loadImages(String email) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+"?email="+email,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Image image = new Image(
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

                                );
                                imageList.add(image);
                            }
                            imageAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "JSON Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                         //   Log(e,"");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}