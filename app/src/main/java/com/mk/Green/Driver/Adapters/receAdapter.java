package com.mk.Green.Driver.Adapters;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.Driver.ChatActivity;
import com.mk.Green.R;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class receAdapter extends RecyclerView.Adapter<receAdapter.ProductViewHolder> {


    private Context mCtx;
    String status;
    private List<flightModel> productList;

    public receAdapter(Context mCtx, List<flightModel> productList, String status) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.status = status;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.message, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        flightModel product = productList.get(position);


        holder.c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                Intent MainIntent = new Intent(mCtx, ChatActivity.class);
                MainIntent.putExtra("choice",product.getSender());
                MainIntent.putExtra("current",status);
                mCtx.startActivity(MainIntent);

            }

        });
        holder.name.setText(product.getSender());
        //holder.email.setText(product.getEmail());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, email;
        CardView c;
        //ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.pilot);
            //email = itemView.findViewById(R.id.email);

            c = itemView.findViewById(R.id.c);
        }
    }
    private void insertChild(final String email,final String planeID,final String date,final String dest) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,"http://"+ LoginActivity.ip+"/green/admin/recyclerview/updateflight.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Record updated successfully")) {
                    Bundle b = new Bundle();
                   // b.putString("name", name);


                    FragmentTransaction transaction = ((FragmentActivity)mCtx).getSupportFragmentManager().beginTransaction();
                  //  moreKids fragB = new moreKids();
                  //  fragB.setArguments(b);
                //    transaction.replace(R.id.my_frame_layout,fragB);
                  //  transaction.commit();

                    Toast.makeText(mCtx, "Allocated successifully", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    // finish();
                }

                else {
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

                param.put("email", email);
                param.put("dest", dest);
                param.put("date", date);
                param.put("planeid", planeID);
                param.put("status1", "Allocated");
                param.put("status", "Allocated");
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(mCtx).addToRequestQueue(request);

    }

}
