package com.mk.Green.Activity.Adapters;

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

import androidx.annotation.NonNull;
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
import com.mk.Green.Activity.Fragment.Servicedetais;
import com.mk.Green.Activity.Fragment.incomingDetailscustomer;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.Finance.Fragments.incomingDetails;
import com.squareup.picasso.Picasso;
import com.mk.Green.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {

    private List<Image> images;
    private Context context;
    private String name;
    public ImagesAdapter(Context context, List<Image> images,String name) {
        this.context = context;
        this.images = images;
        this.name=name;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image = images.get(position);
        holder.nameTextView.setText("Service Name: "+image.getName());
        holder.dateTextView.setText("Date: "+image.getDate());
        holder.descriptionTextView.setText("Description: "+image.getDescription());
        holder.fprice.setText("Quoted Price: "+image.getFprice());
        holder.status1.setText("Status: "+image.getStatus());
       // Toast.makeText(context, image.getDate(), Toast.LENGTH_SHORT).show();
       // String imageUrl = "http://"+ LoginActivity.ip+"/green/admin/uploads/" + image.getImageUrl();
        String imageUrl = LoginActivity.ip+"uploads/" + image.getImageUrl();
        String fp=image.getFprice();


        holder.c.setOnClickListener(new View.OnClickListener() {
                                                @SuppressLint("SetTextI18n")
                                                @Override
                                                public void onClick(View view) {

                                                    if(image.getFprice().equals("null")){
                                                        Toast.makeText(context, "wait for finance to quote price", Toast.LENGTH_SHORT).show();
                                                    }
                                                    //
                                                    if(Objects.equals(image.getAmount(), "null")){
//                                                        Toast.makeText(context, fp, Toast.LENGTH_SHORT).show();
//                                                        final Dialog quantityDialog = new Dialog(context);
//                                                        quantityDialog.setContentView(R.layout.quantity_dialog);
//                                                        quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                                                        quantityDialog.setCancelable(false);
//                                                        //2,500)
//                                                        final TextView priceq = quantityDialog.findViewById(R.id.textView26);
//                                                        priceq.setText("mpesa code(Kshs."+String.valueOf(image.getFprice())+" )");
//                                                        final EditText quantityNo = quantityDialog.findViewById(R.id.quantiy_no);
//                                                        Button cancelBtn = quantityDialog.findViewById(R.id.cancel_btn);
//                                                        Button okBtn = quantityDialog.findViewById(R.id.ok_btn);
//                                                        cancelBtn.setOnClickListener(new View.OnClickListener() {
//                                                            @Override
//                                                            public void onClick(View v) {
//                                                                quantityDialog.dismiss();
//                                                            }
//                                                        });
//
//                                                        okBtn.setOnClickListener(new View.OnClickListener() {
//                                                            @Override
//                                                            public void onClick(View v) {
//                                                                if (isValidCode(quantityNo.getText().toString())) {
//                                                                    insertChild(image.getDescription(),image.getFprice(),quantityNo.getText().toString());
//                                                                    Toast.makeText(context, image.getFprice(), Toast.LENGTH_SHORT).show();
//                                                                } else {
//                                                                    Toast.makeText(context, "Invalid M-pesa code!", Toast.LENGTH_SHORT).show();
//                                                                }
//
//                                                            }
//                                                        });
//                                                        quantityDialog.show();


                                                        Bundle b = new Bundle();
                                                        b.putString("title", image.getName());
                                                        b.putString("description", image.getDescription());
                                                        b.putString("image",imageUrl);
                                                        b.putString("name", name);


                                                        b.putString("amount", image.getAmount());
                                                        b.putString("date", image.getDate());
                                                        b.putString("email", image.getEmail());
                                                        b.putString("address", image.getAddress());

                                                        b.putString("code", image.getCode());
                                                        b.putString("fprice", image.getFprice());
                                                        b.putString("status", image.getStatus());
                                                        //b.putString("code", image.getCode());
                                                        FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                                                        incomingDetailscustomer fragB = new incomingDetailscustomer();
                                                        fragB.setArguments(b);
                                                        transaction.replace(R.id.my_frame_layout,fragB);
                                                        transaction.
                                                                commit();
                                                    }
                                                    if(!image.getAmount().equals("null")){

                                                        Bundle b = new Bundle();
                                                        b.putString("title", image.getName());
                                                        b.putString("description", image.getDescription());
                                                        b.putString("image",imageUrl);
                                                        b.putString("name", name);


                                                        b.putString("amount", image.getAmount());
                                                        b.putString("date", image.getDate());
                                                        b.putString("email", image.getEmail());
                                                        b.putString("address", image.getAddress());

                                                        b.putString("code", image.getCode());

                                                        b.putString("status", image.getStatus());
                                                        //b.putString("code", image.getCode());
                                                        FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                                                        Servicedetais fragB = new Servicedetais();
                                                        fragB.setArguments(b);
                                                        transaction.replace(R.id.my_frame_layout,fragB);
                                                        transaction.
                                                                commit();
                                                    }
                                                }
                                            });
        // Load the image from the URL using a library like Picasso or Glide
        Picasso.get().load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView dateTextView;
        public TextView descriptionTextView,fprice,status1;
        public LinearLayout c;
        public ImageViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            dateTextView = itemView.findViewById(R.id.date_text_view);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);
            c = itemView.findViewById(R.id.c);
            fprice = itemView.findViewById(R.id.fprice);
            status1 = itemView.findViewById(R.id.status);
        }

    }
    private void insertChild(final String description, final String amount,final String code) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
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
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    // finish();
                } else {
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
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
        Singleton.getmInstance(context).addToRequestQueue(request);

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
            Toast.makeText(context, "valid mpesa code", Toast.LENGTH_SHORT).show();
            System.out.println("Success");
            return true;
        }
        else {
            System.out.println("Invalid");
            return false;
        }

    }
}
