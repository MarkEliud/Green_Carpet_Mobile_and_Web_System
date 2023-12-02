package com.mk.Green.Inventory.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Finance.Fragments.incomingDetails;
import com.mk.Green.Inventory.Fragments.editOrder;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Adapters.Image1;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class incomingAdapter extends RecyclerView.Adapter<incomingAdapter.ProductViewHolder> {


    private Context mCtx;
    private  String dob,gender,email;
    private List<Image1> productList;

    public incomingAdapter(Context mCtx, List<Image1> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        //this.email=email;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Image1 product = productList.get(position);
        holder.nameTextView.setText("Item Name: "+product.getName());
        holder.packag.setText("Packages: "+product.getPackages());
        holder.descriptionTextView.setText("Description: "+product.getDescription());



        String imageUrl = LoginActivity.ip+"uploads/" + product.getImageUrl();
        holder.c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, product.getId(), Toast.LENGTH_SHORT).show();

                Bundle b = new Bundle();
                b.putString("name", product.getName());
                b.putString("desc", product.getDescription());
                b.putString("image",imageUrl);
                b.putString("packages", product.getPackages());
                b.putString("id", product.getId());


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
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView packag;
        public TextView descriptionTextView;
        public LinearLayout c;

        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            packag = itemView.findViewById(R.id.packag);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);
            c = itemView.findViewById(R.id.c);

        }
    }

}
