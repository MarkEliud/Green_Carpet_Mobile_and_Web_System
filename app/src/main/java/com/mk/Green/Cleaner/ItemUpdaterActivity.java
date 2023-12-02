package com.mk.Green.Cleaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.Cleaner.Class.ItemUpdater;
import com.mk.Green.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUpdaterActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private List<ItemUpdater.Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_item_updater);

        // Retrieve items string from intent
        String items = getIntent().getStringExtra("items");
        String code = getIntent().getStringExtra("code");
        // Parse items string and create item list
        itemList = parseItems(items);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ItemUpdater.ItemAdapter adapter = new ItemUpdater.ItemAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button approve = findViewById(R.id.approve);


            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    updateProductTable();
                    updateStatus(code);

                }
            });

    }
    private List<ItemUpdater.Item> parseItems(String items) {
        List<ItemUpdater.Item> itemList = new ArrayList<>();

        String[] lines = items.split("\n");

        int itemCount = 0;
        String productName = "";
        int quantity = 0;
        String description = "";

        for (String line : lines) {
            if (line.startsWith("Product name: ")) {
                // If we have already started capturing an item, add it to the list
                if (itemCount > 0) {
                    ItemUpdater.Item item = new ItemUpdater.Item(productName, quantity, description);
                    itemList.add(item);
                }

                // Reset values for the next item
                productName = extractValue(line, "Product name: ");
                quantity = 0;
                description = "";
                itemCount++;
            } else if (line.startsWith("Quantity: ")) {
                quantity = Integer.parseInt(extractValue(line, "Quantity: "));
            } else if (line.startsWith("Description: ")) {
                description = extractValue(line, "Description: ");
            }
        }

        // Add the last item to the list
        if (itemCount > 0) {
            ItemUpdater.Item item = new ItemUpdater.Item(productName, quantity, description);
            itemList.add(item);
        }

        return itemList;
    }
    private String extractValue(String line, String prefix) {
        if (line.startsWith(prefix)) {
            return line.substring(prefix.length());
        }
        return null;
    }
    private void updateProductTable() {
        // Show progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating product table...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Perform update for each item
        for (ItemUpdater.Item item : itemList) {
            // Update the product table with the item's data

            // Create a request queue using Volley
            RequestQueue queue = Volley.newRequestQueue(this);
            // String url = "http://your-api-url/update_product.php"; // Replace with your API endpoint
            String url = LoginActivity.ip+"recyclerview/cleaneritemcalc.php";

            // Create a StringRequest to send a POST request
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Handle the response from the API

                            // Dismiss progress dialog when all requests are completed
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            // Handle the error response from the API

                            // Dismiss progress dialog when all requests are completed
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    // Add parameters to the POST request
                    Map<String, String> params = new HashMap<>();
                    params.put("name", item.getName());
                    params.put("quantity", String.valueOf(item.getQuantity()));
                    return params;
                }
            };

            // Add the request to the request queue
            queue.add(request);
        }
    }
    private void updateStatus(final String code) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
//        ItemUpdater itemUpdater = new ItemUpdater(getActivity());
//        itemUpdater.updateProductTable(items);
        StringRequest request = new StringRequest(Request.Method.POST,LoginActivity.ip+"recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Records updated successfully")) {
                    progressDialog.dismiss();

                    //updateStatus1(description,"Allocated");

                    Toast.makeText(ItemUpdaterActivity.this, response, Toast.LENGTH_SHORT).show();

                    // finish();
                } else {
                    Toast.makeText(ItemUpdaterActivity.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ItemUpdaterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();

                param.put("status", "cleanerUpdate");
                // param.put("fprice",fprice);
                param.put("code",code);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(ItemUpdaterActivity.this).addToRequestQueue(request);

    }

}