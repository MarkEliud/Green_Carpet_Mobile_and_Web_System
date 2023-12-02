package com.mk.Green.Supervisor.Adapters;

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
import com.android.volley.toolbox.StringRequest;
import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class assignDriverAdapter extends RecyclerView.Adapter<assignDriverAdapter.ProductViewHolder> {

    private FragmentManager fragmentManager;
    private Context mCtx;
    private  String code,gender,date;
    private List<Image> productList;
    private String details, name, disability,caretype,status;
    public assignDriverAdapter(Context mCtx, List<Image> productList, String code) {
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
        holder.nameq.setText("Driver Name: ");
        holder.emailq.setText("Driver Email: ");
//        holder.c.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                updateActivity(product.getEmail());
//                return false;
//            }
//        });
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
                priceq.setText("Assign Driver to Cleaners");
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
                        updateActivity(code,product.getEmail());
                        quantityDialog.dismiss();



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
    private void updateActivity(final String code,final String email) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,LoginActivity.ip+"recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Records updated successfully")) {

                    Toast.makeText(mCtx, "allocated driver", Toast.LENGTH_SHORT).show();
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

                param.put("status", "DriverAssign");
                param.put("code",code);
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
        StringRequest request = new StringRequest(Request.Method.POST,LoginActivity.ip+"recyclerview/total.php", new Response.Listener<String>() {
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
