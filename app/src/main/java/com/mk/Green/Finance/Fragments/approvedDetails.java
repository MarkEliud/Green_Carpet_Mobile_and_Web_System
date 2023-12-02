package com.mk.Green.Finance.Fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
//import com.mk.Green.KidActivity.Adapters.activityAdapter;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class approvedDetails extends Fragment {

    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/activity.php";

    public approvedDetails() {
    }
    // String details, name, disability,caretype,status,price,date,email,parent,code;
    String name, fprice,description, image, title, amount, date, email, address, code, status;
    EditText quote;
    Button price;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.financedetail, container, false);
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

        }

        ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        nameq.setText("Customer's Name:" + name + "\n" + "Item's Name: " + title + "\n" + "Description: " + description + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount paid: " + amount + "\n" + "Date: " + date + "\n" + "Status: " + status + "\n");
        Picasso.get().load(image).into(imagev);
        //Button reject = v.findViewById(R.id.reject);
        quote = v.findViewById(R.id.quote);
        price = v.findViewById(R.id.price);
        if(fprice.equals("null")){

            quote.setVisibility(View.VISIBLE);
            price.setVisibility(View.VISIBLE);
            price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // updateStatus(description, quote.getText().toString());
                    Toast.makeText(getActivity(), "no action", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Button approve = v.findViewById(R.id.approve);
        if(!amount.equals("null")){

            //quote.setVisibility(View.VISIBLE);
            // price.setVisibility(View.VISIBLE);
            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // updateStatus1(description,"Approved");
                    Toast.makeText(getActivity(), "no action", Toast.LENGTH_SHORT).show();
                }
            });
            Button reject = v.findViewById(R.id.reject);
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //updateStatus1(description,"Rejected");
                    Toast.makeText(getActivity(), "no action", Toast.LENGTH_SHORT).show();
                }
            });
        }
        // Generate the PDF
        // Button generatePdfButton = v.findViewById(R.id.generate);
        // Button generatePdfButton = findViewById(R.id.generatePdfButton);





        return v;
    }

    private void updateStatus(final String desc,final String fprice) {
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

                param.put("status", "quoteamount");
                param.put("fprice",fprice);
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
