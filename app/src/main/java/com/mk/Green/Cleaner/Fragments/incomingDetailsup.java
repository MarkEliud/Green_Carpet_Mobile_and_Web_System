package com.mk.Green.Cleaner.Fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.mk.Green.R;

import java.util.HashMap;
import java.util.Map;


public class incomingDetailsup extends Fragment {

    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/activity.php";

    public incomingDetailsup() {
    }
    // String details, name, disability,caretype,status,price,date,email,parent,code;
    String name, fprice,amount, desc, status, code, pck;
    EditText quote;
    Button price;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.financeapp, container, false);
        Bundle b = this.getArguments();
        if (b != null) {


            name = b.getString("name");
            pck = b.getString("pck");
            desc = b.getString("desc");
            amount = b.getString("amount");
            status = b.getString("status");
            code = b.getString("code");
            fprice = b.getString("fprice");


        }

       // ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        nameq.setText("Product's Name:" + name + "\n" + "Product's Description: " + desc + "\n" + "Packages: " + pck + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount paid: " + amount + "\n" + "Quoted Price: " + fprice + "\n" + "Status: " + status + "\n");
        //Picasso.get().load(image).into(imagev);
        //Button reject = v.findViewById(R.id.reject);
        quote = v.findViewById(R.id.code);
        price = v.findViewById(R.id.pay);
        if(amount.equals("null")){


            price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateStatus(quote.getText().toString());
                }
            });
        }

        // Generate the PDF
        // Button generatePdfButton = v.findViewById(R.id.generate);
        // Button generatePdfButton = findViewById(R.id.generatePdfButton);





        return v;
    }

    private void updateStatus(final String code) {
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

                if (response.equals("Records updated successfully")) {

                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
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

                param.put("status", "financeAmount");
                param.put("fprice",fprice);
                param.put("code",code);
                param.put("desc",desc);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }
    private void updateStatus1(final String desc, final  String status) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,"http://"+ LoginActivity.ip+"/green/admin/recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Records updated successfully")) {

                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
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

                param.put("status", status);
                // param.put("fprice",fprice);
                param.put("desc",desc);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }

}
