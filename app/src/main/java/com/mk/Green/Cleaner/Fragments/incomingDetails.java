package com.mk.Green.Cleaner.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.Cleaner.Class.ItemUpdater;
import com.mk.Green.Cleaner.ItemUpdaterActivity;
import com.mk.Green.Cleaner.MainActivity;
import com.mk.Green.Inventory.Class.CleaningItemsProcessor;
//import com.mk.Green.Inventory.Class.ItemUpdater;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class incomingDetails extends Fragment {

    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/activity.php";
Context context;
    public incomingDetails() {
    }
   // String details, name, disability,caretype,status,price,date,email,parent,code;
    String name, items,fprice,description, image, title, amount, date, email, address, code, status;
    EditText quote;
    Button price,itemcalc;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.cleanertask, container, false);
        Bundle b = this.getArguments();
        if (b != null) {


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
            fprice = b.getString("fprice");
            items = b.getString("items");

        }

        ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        TextView clean = v.findViewById(R.id.clean);
        clean.setText(items);
        nameq.setText("Customer's Name:" + name + "\n" + "Item's Name: " + title + "\n" + "Description: " + description + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount paid: " + amount + "\n" + "Date: " + date + "\n" + "Status: " + status + "\n");
        Picasso.get().load(image).into(imagev);
        //Button reject = v.findViewById(R.id.reject);
        itemcalc=v.findViewById(R.id.itemcalc);

        itemcalc.setVisibility(View.GONE);

        Button approve = v.findViewById(R.id.approve);
        if(!amount.equals("null")){

            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  progressDialog.dismiss();
                    //updateStatus(code);
                    Intent intent = new Intent(getActivity(), ItemUpdaterActivity.class);
                    intent.putExtra("items", items);
                    intent.putExtra("code", code);
                    startActivity(intent);
                }
            });

        }
        return v;
    }


    private void updateStatus(final String code) {


        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
//        ItemUpdater itemUpdater = new ItemUpdater(getActivity());
//        itemUpdater.updateProductTable(items);
        StringRequest request = new StringRequest(Request.Method.POST,"http://"+ LoginActivity.ip+"/green/admin/recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Records updated successfully")) {
                   progressDialog.dismiss();

                    //updateStatus1(description,"Allocated");

                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();

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

                param.put("status", "cleanerUpdate1");
               // param.put("fprice",fprice);
                param.put("code",code);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }

}