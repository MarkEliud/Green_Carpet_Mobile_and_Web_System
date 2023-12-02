package com.mk.Green.Inventory.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class assignsupplierAdapter extends RecyclerView.Adapter<assignsupplierAdapter.ProductViewHolder> {

    private FragmentManager fragmentManager;
    private Context mCtx;
    private  String desc,pname,pack;
    private List<Image> productList;
    private String details, name, disability,caretype,status,email;
    public assignsupplierAdapter(Context mCtx, List<Image> productList
            , String pname
            , String pack
            , String desc
           ) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.pname=pname;
        this.pack=pack;
      //  this.email=email;
        this.desc=desc;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.supassignphys, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Image product = productList.get(position);
        holder.nameq.setText("Supplier Name: ");
        holder.emailq.setText("Supplier Email: ");
        holder.c.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               // updateActivity(product.getEmail());
                return false;
            }
        });
        holder.c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                final Dialog quantityDialog = new Dialog(view.getContext());
                quantityDialog.setContentView(R.layout.quantity_dialog);
                quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                quantityDialog.setCancelable(false);
                //2,500)
                final TextView priceq = quantityDialog.findViewById(R.id.textView26);
                priceq.setText("Assign Supplier to Request Order");
                final EditText quantityNo = quantityDialog.findViewById(R.id.quantiy_no);
                quantityNo.setVisibility(View.GONE);
                Button cancelBtn = quantityDialog.findViewById(R.id.cancel_btn);
                Button okBtn = quantityDialog.findViewById(R.id.ok_btn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quantityDialog.dismiss();
                    }
                });
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // updateActivity(code,product.getEmail());
                        updateActivity(pname,pack,desc, product.getEmail());
                       // quantityDialog.dismiss();



                    }
                });
                quantityDialog.show();

            }

        });
        holder.name.setText(product.getFullname());
        holder.phone.setText(product.getEmail());
        //holder.spec.setText(product.getSpecialization());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone, nameq,emailq;
        CardView c;
        public ProductViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
             nameq = itemView.findViewById(R.id.name1);
            emailq = itemView.findViewById(R.id.email1);

            c = itemView.findViewById(R.id.c);
        }
    }
    private void updateActivityq(final String pname, final String pack, final String desc, final String email) {
        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setTitle("Loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();

        JSONObject requestData = new JSONObject();
        try {
            requestData.put("status", "request");
            requestData.put("pname", pname);
            requestData.put("pack", pack);
            requestData.put("desc", desc);
            requestData.put("email", email);
            requestData.put("fprice", "null");
            requestData.put("amount", "null");
            requestData.put("code", "null");
        } catch (JSONException e) {
            e.printStackTrace();
            progressDialog.dismiss();
            return;
        }
      //  LoginActivity.ip + "recyclerview/update.php",
           //     LoginActivity.ip + "recyclerview/update.php",
       // LoginActivity.ip+"recyclerview/update.php"https://greencarpet.blupayinc.com/admin/recyclerview/update.php
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                LoginActivity.ip + "recyclerview/update.php",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try {
                            String message = response.getString("message");
                            Toast.makeText(mCtx, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {

                            Toast.makeText(mCtx, "Error parsing response.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        error.printStackTrace();
                       // e.printStackTrace();
                        Toast.makeText(mCtx, "An error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(mCtx).addToRequestQueue(request);
    }

//    private void updateActivity(final String pname,final String pack,final String desc,final String email) {
//        //if (phone.length() == 10) {
//        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
//        progressDialog.setTitle("loading");
//        progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.show();
//        StringRequest request = new StringRequest(Request.Method.POST,LoginActivity.ip+"recyclerview/update.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                if (response.equals("Record updated successfully")) {
//
//                    Toast.makeText(mCtx, response, Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                    // finish();
//                } else {
//                    Toast.makeText(mCtx, response, Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(mCtx, error.toString(), Toast.LENGTH_SHORT).show();
//                error.printStackTrace();
//                progressDialog.dismiss();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> param = new HashMap<>();
//
//                param.put("status", "request");
//                param.put("pname",pname);
//                param.put("pack",pack);
//                param.put("desc",desc);
//                param.put("email",email);
//                param.put("fprice","null");
//                param.put("amount","null");
//                param.put("code","null");
//
//                return param;
//
//            }
//        };
//        request.setShouldCache(false).setRetryPolicy(new DefaultRetryPolicy(
//                100000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//       // request.setRetryPolicy(new DefaultRetryPolicy(30000,
//        // DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//        // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Singleton.getmInstance(mCtx).addToRequestQueue(request);
//
//    }
//    private void updateActivity(final String email) {
//        //if (phone.length() == 10) {
//        //  final String total;
//        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
//        progressDialog.setTitle("loading");
//        progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.show();
//        StringRequest request = new StringRequest(Request.Method.POST,"http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/total.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//
//
//                Toast.makeText(mCtx, "Total allocated tasks: "+response, Toast.LENGTH_SHORT).show();
//                //total= response;
//                progressDialog.dismiss();
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(mCtx, error.toString(), Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> param = new HashMap<>();
//
//                param.put("email",email);
//
//                return param;
//
//            }
//        };
//
//        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Singleton.getmInstance(mCtx).addToRequestQueue(request);
//
//    }

    private void updateActivity(final String pname,final String pack,final String desc,final String email) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
       // LoginActivity.ip+"recyclerview/displayproducts.php";
        StringRequest request = new StringRequest(Request
                .Method.POST,LoginActivity.ip+"recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Record updated successfully")) {

                    Toast.makeText(mCtx, "requested successifully", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    // finish();
                } else {
                    Toast.makeText(mCtx, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mCtx, error.toString()+",mmmm", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();

                param.put("status", "request");
                param.put("pname",pname);
                param.put("pack",pack);
                param.put("desc",desc);
                param.put("email",email);
                param.put("fprice","null");
                param.put("amount","null");
                param.put("code","null");

                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(mCtx).addToRequestQueue(request);

    }
}
