package com.mk.Green.Supplier.Fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
    String name, fprice,amount, desc, status, code, pck,investatus,itemname;
    EditText quote;
    Button price,approve;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.supplyapprove, container, false);
        Bundle b = this.getArguments();
        if (b != null) {


            name = b.getString("name");
            pck = b.getString("pck");
            desc = b.getString("desc");
            amount = b.getString("amount");
            status = b.getString("status");
            code = b.getString("code");
            fprice = b.getString("fprice");
            investatus = b.getString("investatus");
          //  itemname = b.getString("itemname");

        }

       // ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        nameq.setText("Product's Name:" + name + "\n" + "Product's Description: " + desc + "\n" + "Packages: " + pck + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount paid: " + fprice + "\n" + "Quoted Price: " + amount + "\n" + "Status: " + status + "\n");
        //Picasso.get().load(image).into(imagev);
        price = v.findViewById(R.id.approve);
        Button reject = v.findViewById(R.id.reject);

       EditText quote = v.findViewById(R.id.quote);
        Button pay = v.findViewById(R.id.price);
       approve = v.findViewById(R.id.approve);
//        if(fprice.equals("null")){
//            price.setEnabled(false);
//            reject.setEnabled(false);
//            quote.setVisibility(View.GONE);
//            pay.setVisibility(View.GONE);
//            Toast.makeText(getActivity(), "Cannot approve or reject until Finance has Paid", Toast.LENGTH_SHORT).show();
//
//        }
        if(amount.equals("null")){
            approve.setEnabled(false);
            reject.setEnabled(false);
            quote.setAllCaps(true);
            pay.setEnabled(true);
            quote.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            quote.setVisibility(View.VISIBLE);
            pay.setVisibility(View.VISIBLE);
            pay.setText("Quote and Supply");
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   if(isValidCode(quote.getText().toString())){
                        quotePrice(desc, quote.getText().toString());
                 //   }
                 //   else{
                   //     Toast.makeText(getActivity(), "Wrong Code", Toast.LENGTH_SHORT).show();
                   // }

                }
            });
        }

        if(!fprice.equals("null")){
            approve.setEnabled(true);
            quote.setEnabled(false);
            pay.setEnabled(false);
            approve.setText("Approve for payment");
            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!investatus.equals("Approved")){
                        int total=0;
                        int pck1=Integer.parseInt(pck);
                        int m;
                        Toast.makeText(getActivity(), investatus, Toast.LENGTH_SHORT).show();

                        Toast.makeText(getActivity(), "Inventory hasn't approved requested stock yet", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        updateStatus(code, pck,name);

                    }
                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!investatus.equals("Approved")){
                        Toast.makeText(getActivity(), "Inventory hasn't approved requested stock yet", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        updateStatus1(code, desc);

                    }
                }
            });
        }

//        else{
//            Toast.makeText(getActivity(), "Finance hasnt paid yet", Toast.LENGTH_SHORT).show();
//        }







        return v;
    }
    public boolean isValidCode(final String code) {

        boolean number = false;
        boolean character = false;
        boolean symbol = false;
        boolean length = false;
        int letterCounter = 0;
        int numCounter = 0;

        char [] letters = code.toCharArray();

        for (char c: letters){
            if(Character.isLetter(c)) {
                letterCounter++;
            }
            else if(Character.isDigit(c)) {
                numCounter++;
            }
            else {
                symbol = true;
            }
        }

        //Checking booleans
        if (code.length()>=10) {
            length = true;
        }
        if (letterCounter>=8) {
            character = true;
        }
        if (numCounter>=2) {
            number = true;
        }

        if (character && length && number && !symbol){
            Toast.makeText(getActivity(), "valid mpesa code", Toast.LENGTH_SHORT).show();
            System.out.println("Success");
            return true;
        }
        else {
            System.out.println("Invalid");
            return false;
        }

    }
    private void updateStatus(final String code, String pck, String name) {
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
                  //  Toast.makeText(getActivity(), name, Toast.LENGTH_LONG).show();
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
                //supplydel
                param.put("status", "supplydel");
           //     param.put("fprice",fprice);
                param.put("code",code);
                param.put("pck",pck);
                param.put("name",name);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }
    private void updateStatus1(final String code, final  String status) {
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

                param.put("status", "supplyRejected");
                // param.put("fprice",fprice);
                param.put("code",code);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }
    private void quotePrice(final String desc, final  String amount) {
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

                param.put("status", "supplyQuote");
                param.put("amount",amount);
                param.put("desc",desc);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }
}
