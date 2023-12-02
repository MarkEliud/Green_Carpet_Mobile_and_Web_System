package com.mk.Green.ServiceManager.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Fragments.assignCleaner;
import com.mk.Green.ServiceManager.Fragments.assignDriver;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class assignAdapter extends RecyclerView.Adapter<assignAdapter.ProductViewHolder> {

    private FragmentManager fragmentManager;
    private Context mCtx;
    private  String code,gender,date;
    private List<Image> productList;
    private String details, name, disability,caretype,status;
    private List<Image> selectedItems = new ArrayList<>();
    public assignAdapter(Context mCtx, List<Image> productList, String code) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.code=code;


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
        holder.itemView.setOnClickListener(view -> {
            if (selectedItems.contains(product)) {
                selectedItems.remove(product);
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            } else {
                selectedItems.add(product);
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.select));
            }
        });
        Toast.makeText(mCtx, code, Toast.LENGTH_SHORT).show();
//        holder.c.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View view) {
//
//                final Dialog quantityDialog = new Dialog(view.getContext());
//                quantityDialog.setContentView(R.layout.quantity_dialog);
//                quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                quantityDialog.setCancelable(false);
//                //2,500)
//                final TextView priceq = quantityDialog.findViewById(R.id.textView26);
//                priceq.setText("Assign to: "+product.getFullname()+".?");
//                final EditText quantityNo = quantityDialog.findViewById(R.id.quantiy_no);
//                quantityNo.setVisibility(View.GONE);
//                Button cancelBtn = quantityDialog.findViewById(R.id.cancel_btn);
//                Button okBtn = quantityDialog.findViewById(R.id.ok_btn);
//                cancelBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        quantityDialog.dismiss();
//                    }
//                });
//                okBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        updateActivity(code,product.getEmail(),product.getFullname());
//                        quantityDialog.dismiss();
//                        Toast.makeText(mCtx, "Assign Driver to ship the cleaner", Toast.LENGTH_SHORT).show();
//
//
//                        Bundle bundle = new Bundle();
//                        //bundle.putInt("code", code);
//                        bundle.putString("code", code);
//                       // bundle.putString("title", "Item " + position);
//
//                        assignDriver fragment = new assignDriver();
//                        fragment.setArguments(bundle);
//
//                        FragmentManager fragmentManager = ((AppCompatActivity) mCtx).getSupportFragmentManager();
//                        FragmentTransaction transaction = fragmentManager.beginTransaction();
//                        transaction.replace(R.id.my_frame_layout, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
//
//                    }
//                });
//                quantityDialog.show();
//
//            }
//
//        });
        holder.name.setText(product.getFullname());
        holder.phone.setText(product.getEmail());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public List<Image> getSelectedItems() {
        return selectedItems;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone, spec;
        CardView c;
      public ProductViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
           // spec = itemView.findViewById(R.id.spe);

            c = itemView.findViewById(R.id.c);
        }
    }
    private void updateActivity(final String code,final String email,final String fname) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,"http://"+ LoginActivity.ip+"/green/admin/recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Records updated successfully")) {

                    Toast.makeText(mCtx, "allocated", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(mCtx, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();

                param.put("status", "CleanerAssign");
                param.put("code",code);
                param.put("fname",fname);
                param.put("email",email);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(mCtx).addToRequestQueue(request);

    }
    private void updateActivity(final String email) {
        //if (phone.length() == 10) {
     //  final String total;
        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,"http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/total.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                    Toast.makeText(mCtx, "Total allocated tasks: "+response, Toast.LENGTH_SHORT).show();
                    //total= response;
                    progressDialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mCtx, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();

                param.put("email",email);

                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(mCtx).addToRequestQueue(request);

    }
}
