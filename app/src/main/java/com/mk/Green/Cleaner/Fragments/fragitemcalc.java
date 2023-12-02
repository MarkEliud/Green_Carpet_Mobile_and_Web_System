package com.mk.Green.Cleaner.Fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.Cleaner.Class.ItemUpdater;
import com.mk.Green.Cleaner.MainActivity;
import com.mk.Green.Inventory.Class.CleaningItemsProcessor;
//import com.mk.Green.Inventory.Class.ItemUpdater;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class fragitemcalc extends Fragment {

    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/activity.php";
    Context context;
    List<ItemUpdater.Item> itemList;
    public fragitemcalc(Context context,List<ItemUpdater.Item> itemList) {
        this.context=context;
        this. itemList=itemList;
    }
    // String details, name, disability,caretype,status,price,date,email,parent,code;
    String name, items,fprice,description, image, title, amount, date, email, address, code, status;
    EditText quote;
    Button price,itemcalc;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.dialog_item_updater, container, false);
        Bundle b = this.getArguments();
        if (b != null) {


//            title = b.getString("title");
//            description = b.getString("description");
//            image = b.getString("image");
//            name = b.getString("name");
//            amount = b.getString("amount");
//            date = b.getString("date");
//            email = b.getString("email");
//            address = b.getString("address");
//            code = b.getString("code");
//            status = b.getString("status");
//            fprice = b.getString("fprice");
//            items = b.getString("items");

        }

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        ItemUpdater.ItemAdapter adapter = new ItemUpdater.ItemAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        Button approve = v.findViewById(R.id.approve);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  ItemUpdater.updateProductTable();
            }
        });

        return v;
    }



}
