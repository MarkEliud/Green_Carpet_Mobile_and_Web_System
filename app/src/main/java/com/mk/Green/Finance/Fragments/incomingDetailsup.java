package com.mk.Green.Finance.Fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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


public class incomingDetailsup extends Fragment {

    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/activity.php";

    public incomingDetailsup() {
    }
    // String details, name, disability,caretype,status,price,date,email,parent,code;
    String name,id, fprice,amount, desc, status, code, pck;
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
            id = b.getString("id");


        }

       // ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        nameq.setText("Product's Name:" + name + "\n" + "Product's Description: " + desc + "\n" + "Packages: " + pck + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount paid: " + fprice + "\n" + "Quoted Price: " + amount + "\n" + "Status: " + status + "\n");
        //Picasso.get().load(image).into(imagev);
        //Button reject = v.findViewById(R.id.reject);
        quote = v.findViewById(R.id.code);
        price = v.findViewById(R.id.pay);
        Button approve = v.findViewById(R.id.approve);
        Button reject = v.findViewById(R.id.reject);
        approve.setVisibility(View.GONE);
        reject.setVisibility(View.GONE);
        if(amount.equals("null")){
             quote.setEnabled(false);
             price.setEnabled(false);
            Toast.makeText(getActivity(), "Wait until supplier Quotes Price", Toast.LENGTH_SHORT).show();
//            price.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    updateStatus(quote.getText().toString());
//                }
//            });
        }
        if(!amount.equals("null")){
            quote.setEnabled(true);
            price.setEnabled(true);
            quote.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            quote.setAllCaps(true);
            price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if(isValidCode(quote.getText().toString())){
                        updateStatus(quote.getText().toString());
                    }
else{
                        Toast.makeText(getActivity(), "Wrong code", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        if(amount.equals(fprice) && !amount.equals("null")){
            Toast.makeText(getActivity(), "This has Already been paid!", Toast.LENGTH_SHORT).show();

            quote.setEnabled(false);
            price.setEnabled(false);
//            price.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    updateStatus(quote.getText().toString());
//                }
//            });
        }
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
                param.put("fprice",amount);
                param.put("code",code);
                param.put("desc",desc);
                param.put("id",id);
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
