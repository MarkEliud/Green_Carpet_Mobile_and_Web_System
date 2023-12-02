package com.mk.Green.Driver.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mk.Green.Driver.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Driver.Fragments.SupDetails;
import com.mk.Green.R;

import com.squareup.picasso.Picasso;

import java.util.List;

public class compAdapter extends RecyclerView.Adapter<compAdapter.ProductViewHolder> {


    private Context mCtx;
    private  String dob,gender,email,date;
    private List<Image> productList;

    public compAdapter(Context mCtx, List<Image> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        //this.email=email;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.image_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Image image = productList.get(position);
        String imageUrl = LoginActivity.ip+"uploads/" + image.getImageUrl();

        holder.c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                // Toast.makeText(mCtx, email, Toast.LENGTH_SHORT).show();

                ///  if(Objects.equals(product.getCaretype(), "long")){
                Bundle b = new Bundle();

                b.putString("cleaner", image.getCleanerName());
                b.putString("address", image.getAddress());
                b.putString("date", image.getDate());
                b.putString("cname", image.getCustomerName());
                b.putString("code", image.getCode());


//                FragmentTransaction transaction = ((FragmentActivity)mCtx).getSupportFragmentManager().beginTransaction();
//                SupDetails fragB = new SupDetails();
//                fragB.setArguments(b);
//                transaction.replace(R.id.my_frame_layout,fragB);
//                transaction.
//                        commit();

                // else{

                // }


            }

        });
        Picasso.get().load(imageUrl).into(holder.imageView);
        // Image image = images.get(position);
        holder.nameTextView.setText("Cleaner Name: "+image.getCleanerName());
        holder.dateTextView.setText("Date: "+image.getDate());
        holder.descriptionTextView.setText("Customer Name: "+image.getCustomerName());
        holder.fprice.setText("Address: "+image.getAddress());
        holder.status1.setVisibility(View.GONE);

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
