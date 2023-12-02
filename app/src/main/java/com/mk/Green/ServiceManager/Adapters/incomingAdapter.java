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

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.ServiceManager.Fragments.editOrder;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class incomingAdapter extends RecyclerView.Adapter<incomingAdapter.ProductViewHolder> {

    private static List<Image> filteredList;
    private Context mCtx;
    private  String dob,gender,email,date;
    private List<Image> productList;

    public incomingAdapter(Context mCtx, List<Image> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        //this.email=email;
        this.filteredList=new ArrayList<>(productList);;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.service_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Image product = filteredList.get(position);


        holder.size.setText("Item Size: "+product.getSize());
        holder.desc.setText("Description: "+product.getDesc());
        holder.price.setText("price: "+product.getPrice());
        if(product.getCategory().equals("Car-Interior-Cleaning")){
            holder.size.setText("Item Name: "+product.getSize());
        }
        if(product.getCategory().equals("Pest Control Service")){
            holder.size.setText("Item Name: "+product.getSize());
        }




        String imageUrl = LoginActivity.ip+"uploads/" + product.getImageUrl();
        holder.c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
               // Toast.makeText(mCtx, email, Toast.LENGTH_SHORT).show();

                Bundle b = new Bundle();
                b.putString("size", product.getSize());
                b.putString("desc", product.getDesc());
                b.putString("image",imageUrl);
                b.putString("price", product.getPrice());
                b.putString("id", product.getId());
                b.putString("category", product.getCategory());
                FragmentTransaction transaction = ((FragmentActivity)mCtx).getSupportFragmentManager().beginTransaction();
                editOrder fragB = new editOrder();
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
        public TextView size;
        public TextView desc;
        public TextView price;
        public LinearLayout c;

        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            size = itemView.findViewById(R.id.size);
            desc = itemView.findViewById(R.id.desc);
            price = itemView.findViewById(R.id.price);
            c = itemView.findViewById(R.id.c);

        }
    }

}
