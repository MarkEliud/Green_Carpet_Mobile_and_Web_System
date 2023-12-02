package com.mk.Green.Activity.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class incomingDetailscustomer extends Fragment {

    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/activity.php";

    public incomingDetailscustomer() {
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
        String statusq="";
        if(status.equals("Not Paid Yet")){
            statusq="Pending";

        }
        ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        nameq.setText("Customer's Name:" + name + "\n" + "Item's Name: " + title + "\n" + "Description: " + description + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount payable: " + fprice + "\n" + "Date: " + date + "\n" + "Status: " + statusq + "\n");
        Picasso.get().load(image).into(imagev);
        //Button reject = v.findViewById(R.id.reject);
        quote = v.findViewById(R.id.quote);
        quote.setHint("Enter mpesa code");
        price = v.findViewById(R.id.price);
        price.setText("Accept And Approve");
        Button approve = v.findViewById(R.id.approve);
        Button reject = v.findViewById(R.id.reject);
        approve.setEnabled(false);
        approve.setVisibility(View.VISIBLE);
        reject.setEnabled(false);
//        if(fprice.equals("null")){

//            price.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                }
//            });
//        }

        if(!fprice.equals("null")){
            quote.setAllCaps(true);
            quote.setInputType(EditorInfo.TYPE_CLASS_TEXT);
             quote.setVisibility(View.VISIBLE);
            price.setVisibility(View.VISIBLE);

            price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(TextUtils.isEmpty(quote.getText().toString())){

                        @SuppressLint("UseCompatLoadingForDrawables") Drawable errorIcon = getResources().getDrawable(R.drawable.add); // Replace R.drawable.ic_error_icon with your drawable resource
                        quote.setFocusable(true);
                        quote.setError("Error",errorIcon);
                       //if( )
                        Toast.makeText(getActivity(), "Enter mpesa code", Toast.LENGTH_SHORT).show();

                    }
                    else{
                       // updateStatus(description, quote.getText().toString());
                        if (isValidCode(quote.getText().toString())) {
                                  insertChild(description,fprice,quote.getText().toString());
                                //  Toast.makeText(getActivity(), image.getFprice(), Toast.LENGTH_SHORT).show();

                        } else {
                      Toast.makeText(getActivity(), "Invalid M-pesa code!", Toast.LENGTH_SHORT).show();
                               }
                    }
                }
            });
        }
        else{
                        quote.setVisibility(View.VISIBLE);
            price.setVisibility(View.VISIBLE);
            approve.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Cant Approve until Service manager quotes price", Toast.LENGTH_SHORT).show();

        }

        reject.setEnabled(true);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quote.setEnabled(false);
            price.setEnabled(false);
                updateStatus1(description,"Rejected");
            }
        });


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

                param.put("status", "entermpesa");
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
        StringRequest request = new StringRequest(Request.Method.POST,LoginActivity.ip+"recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Records updated successfully")) {

                    Toast.makeText(getActivity(), "cancelled", Toast.LENGTH_SHORT).show();
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
    private void insertChild(final String description, final String amount,final String code) {
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
                    // Toast.makeText(get, "done", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(CheckOrderDetails.this, LoginActivity.class));
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

                param.put("amount", amount);
                param.put("description",description);
                param.put("code",code);
                param.put("status", "updateamount");

                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

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
}