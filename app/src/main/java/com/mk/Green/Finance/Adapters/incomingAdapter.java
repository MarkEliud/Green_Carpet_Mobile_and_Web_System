package com.mk.Green.Finance.Adapters;

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
import com.mk.Green.Finance.Fragments.incomingDetails;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class incomingAdapter extends RecyclerView.Adapter<incomingAdapter.ProductViewHolder> {

    private static List<Image> filteredList;
    private Context mCtx;
    private  String dob,gender,email,date;
    private List<Image> productList;

    public incomingAdapter(Context mCtx, List<Image> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.filteredList=new ArrayList<>(productList);;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.image_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Image product = filteredList.get(position);
        holder.nameTextView.setText("Item Name: "+product.getName());
        holder.dateTextView.setText("Date: "+product.getDate());
        holder.descriptionTextView.setText("Description: "+product.getDescription());
        holder.fprice.setText("Quoted Price: "+product.getFprice());
        if(Objects.equals(product.getStatus(), "Not Paid Yet")){
            holder.status1.setText("Status: "+"Pending");
        }

        String imageUrl = LoginActivity.ip+"uploads/" + product.getImageUrl();
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

                FragmentTransaction transaction = ((FragmentActivity)mCtx).getSupportFragmentManager().beginTransaction();
                incomingDetails fragB = new incomingDetails();
                fragB.setArguments(b);
                transaction.replace(R.id.my_frame_layout,fragB);
                transaction.
                        commit();
            }

        });

        Picasso.get().load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
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
