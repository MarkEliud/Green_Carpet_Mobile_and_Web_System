package com.mk.Green.ServiceManager.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class SupDetails extends Fragment {


    List<Image> productList;
    RecyclerView recyclerView;
    public SupDetails() {
    }
    //String details, name, disability,caretype,status,price,date,email,parent,code;

    String name, description, image, title, amount, date, email, address, code, status;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.supalocate, container, false);
        Bundle b = this.getArguments();
        if(b != null){
            title = b.getString("title");
            description = b.getString("description");
            image = b.getString("image");
            name = b.getString("name");
            amount = b.getString("amount");
            date = b.getString("date");
            email = b.getString("email");
            address = b.getString("address");
            code = b.getString("code");
            status = b.getString("status");

        }
        ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        nameq.setText("Customer's Name:" + name + "\n" + "Item's Name: " + title + "\n" + "Description: " + description + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount paid: " + amount + "\n" + "Date: " + date + "\n" + "Status: " + status + "\n");
        Picasso.get().load(image).into(imagev);

        Button done= v.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if(caretype.equals("Long")){
                    Bundle k = new Bundle();
                    k.putString("code", code);
                   // Toast.makeText(getActivity(), "allocate Cleaner", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    assignCleaner fragB = new assignCleaner();
                    fragB.setArguments(k);
                    transaction.replace(R.id.my_frame_layout,fragB);
                    transaction.
                            commit();



            }
        });






        return v;
    }



}