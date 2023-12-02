package com.mk.Green.ServiceManager.Adapters;



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Finance.Fragments.approvedDetails;
import com.mk.Green.Finance.Fragments.incomingDetails;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class approvedAdapter1 extends RecyclerView.Adapter<approvedAdapter1.ProductViewHolder> {


    private Context mCtx;
    private  String dob,gender,email,date;
    private List<Image> productList;

    public approvedAdapter1(Context mCtx, List<Image> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        //this.email=email;

    }

    @Override
    public approvedAdapter1.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.image_item, null);
        return new approvedAdapter1.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(approvedAdapter1.ProductViewHolder holder, int position) {
        Image product = productList.get(position);
        holder.nameTextView.setText("Item Name: "+product.getName());
        holder.dateTextView.setText("Date: "+product.getDate());
        holder.descriptionTextView.setText("Description: "+product.getDescription());
        holder.fprice.setText("Quoted Price: "+product.getFprice());
        holder.status1.setText("Status: "+product.getStatus());
        String imageUrl =LoginActivity.ip+ "uploads/" + product.getImageUrl();
        holder.c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                // Toast.makeText(mCtx, email, Toast.LENGTH_SHORT).show();

                Bundle b = new Bundle();
                b.putString("title", product.getName());
                b.putString("description", product.getDescription());
                b.putString("image",imageUrl);
                b.putString("name", product.getCustomerName());


                b.putString("amount", product.getAmount());
                b.putString("date", product.getDate());
                b.putString("email", product.getEmail());
                b.putString("address", product.getAddress());

                b.putString("code", product.getCode());

                b.putString("status", product.getStatus());
                b.putString("fprice", product.getFprice());

//                FragmentTransaction transaction = ((FragmentActivity)mCtx).getSupportFragmentManager().beginTransaction();
//                approvedDetails fragB = new approvedDetails();
//                fragB.setArguments(b);
//                transaction.replace(R.id.my_frame_layout,fragB);
//                transaction.
//                        commit();
            }

        });

        Picasso.get().load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView dateTextView;
        public TextView descriptionTextView,fprice,status1;
        public LinearLayout c;

        public ProductViewHolder(View itemView) {
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

}