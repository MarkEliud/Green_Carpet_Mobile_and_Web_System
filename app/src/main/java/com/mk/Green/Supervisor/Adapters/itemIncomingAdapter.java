package com.mk.Green.Supervisor.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
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
import com.mk.Green.Inventory.Fragments.editOrder;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Adapters.Image1;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class itemIncomingAdapter extends RecyclerView.Adapter<itemIncomingAdapter.ProductViewHolder> {


    private Context mCtx;
    private  String dob,gender,email,date;
    private List<Image1> productList;
    private List<Image1> selectedItems = new ArrayList<>();

    public itemIncomingAdapter(Context mCtx, List<Image1> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        //this.email=email;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.selectproduct, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Image1 product = productList.get(position);
        holder.itemView.setOnClickListener(view -> {
            if (selectedItems.contains(product)) {
                selectedItems.remove(product);
            } else {
                selectedItems.add(product);
            }
            notifyDataSetChanged(); // Notify the adapter that the dataset has changed to update the view holders
        });

        // Set the background color based on selection state
        if (selectedItems.contains(product)) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.select));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        }
//        holder.itemView.setOnClickListener(view -> {
//            if (selectedItems.contains(product)) {
//                selectedItems.remove(product);
//                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
//            } else {
//                selectedItems.add(product);
//                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.select));
//            }
//        });

        holder.nameTextView.setText("Item Name: "+product.getName());
        holder.packag.setText("Packages: "+product.getPackages());
        holder.descriptionTextView.setText("Description: "+product.getDescription());



        String imageUrl = LoginActivity.ip+"uploads/" + product.getImageUrl();


        Picasso.get().load(imageUrl).into(holder.imageView);
        holder.qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog quantityDialog = new Dialog(view.getContext());
                quantityDialog.setContentView(R.layout.item_quantity_dialog);
                quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                quantityDialog.setCancelable(false);
                final EditText quantityNo =quantityDialog.findViewById(R.id.quantiy_no);
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

                        String productQuantity_str = quantityNo.getText().toString() ;
                        quantityDialog.dismiss();

                        int mm=Integer.parseInt(productQuantity_str);
                        int mk=Integer.parseInt(product.getPackages());

                        if(mm<=0){
                            quantityDialog.dismiss();
                            product.setQty("1"); // Set the selected quantity for the Image1 object

                            holder.qty.setText(product.getQty()); // Update the quantity text in the view holder
                            Toast.makeText(mCtx, "Quantity can't be zero or less, \nQuantity SET TO 1", Toast.LENGTH_SHORT).show();

                        }
                       else if (!(mm >mk)){

                            product.setQty(productQuantity_str); // Set the selected quantity for the Image1 object
                            updateActivity(productQuantity_str, product.getImageUrl());
                            holder.qty.setText(product.getQty()); // Update the quantity text in the view holder
                        }
                        else {
                            quantityDialog.dismiss();
                            updateActivity(product.getPackages(),product.getImageUrl());
                            holder.qty.setText(product.getPackages());
                           // productReference.child("productQuantity").setValue(uploadCur.getQuantityy());
                            Toast.makeText(mCtx, "Maximum Quantity Exceeded, set to available Stock", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                quantityDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public List<Image1> getSelectedItems() {
        return selectedItems;
    }
    class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView packag,qty;
        public TextView descriptionTextView;
        public LinearLayout c;

        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            packag = itemView.findViewById(R.id.packag);
            qty = itemView.findViewById(R.id.product_quantity);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);
            c = itemView.findViewById(R.id.c);

        }
    }

    private void updateActivity(final String qty,final String imageurl) {
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

                   // Toast.makeText(mCtx, "allocated driver", Toast.LENGTH_SHORT).show();
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
                param.put("status", "selectq");
                param.put("imageurl", imageurl);
                param.put("qty",qty);


                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(mCtx).addToRequestQueue(request);

    }
}
