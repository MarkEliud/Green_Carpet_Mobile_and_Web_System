package com.mk.Green.Inventory.Class;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemUpdater {
    private Context context;
    private ProgressDialog progressDialog;
    private int totalRequests;
    private int completedRequests;

    public ItemUpdater(Context context) {
        this.context = context;
    }

    public void updateProductTable(String items) {
        // Show progress dialog
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Updating product table...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Parse items string and make API requests
        String regex = "Product name: ([a-zA-Z\\s]+)\\s+Quantity: (\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(items);
        totalRequests = 0;
        completedRequests = 0;

        while (matcher.find()) {
            String productName = matcher.group(1);
            int quantity = Integer.parseInt(matcher.group(2));

            // Make API request to update product table
            totalRequests++;
            updateProductQuantity(productName, quantity);
        }
    }

    private void updateProductQuantity(final String productName, final int quantity) {
        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);
       // String url = "http://your-api-url/update_product.php";
        String url =LoginActivity.ip+ "recyclerview/itemcalc.php";
        // Make a POST request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Handle the API response
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Log.d("ItemUpdater", productName + " updated successfully");
                            } else {
                                Log.d("ItemUpdater", "Failed to update " + productName);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("ItemUpdater", "Error parsing JSON response");
                        }

                        // Check if all requests are completed
                        completedRequests++;
                        if (completedRequests == totalRequests) {
                            // Dismiss progress dialog when all requests are completed
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("ItemUpdater", "API request failed");

                // Check if all requests are completed
                completedRequests++;
                if (completedRequests == totalRequests) {
                    // Dismiss progress dialog when all requests are completed
                    progressDialog.dismiss();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Add parameters to the POST request
                Map<String, String> params = new HashMap<>();
                params.put("name", productName);
                params.put("quantity", String.valueOf(quantity));
                return params;
            }
        };

        // Add the request to the RequestQueue
        queue.add(stringRequest);
    }
}
