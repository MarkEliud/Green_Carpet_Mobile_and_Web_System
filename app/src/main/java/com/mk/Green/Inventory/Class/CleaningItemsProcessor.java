package com.mk.Green.Inventory.Class;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.Volley;
        import com.mk.Green.Activity.LoginActivity;

        import org.json.JSONException;
        import org.json.JSONObject;

public class CleaningItemsProcessor extends AsyncTask<Void, Void, Void> {


    private static final String BASE_URL =  LoginActivity.ip+"recyclerview/itemcalc.php";
    //private static final String BASE_URL = "http://your-api-url.com";
    private static final String TAG = "UpdateProductTask";
    private Context context;
    private String jsonString;
    private ProgressDialog progressDialog;

    public CleaningItemsProcessor(Context context, String jsonString) {
        this.context = context;
        this.jsonString = jsonString;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Updating products...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(jsonString);
            int totalItems = jsonObject.getInt("Total Items Assigned");

            // Iterate over the assigned cleaning items
            for (int i = 1; i <= totalItems; i++) {
                JSONObject itemObject = jsonObject.getJSONObject("Product " + i);
                String productName = itemObject.getString("Product name");
                int quantity = itemObject.getInt("Quantity");

                // Make a network request to update the product in the database
                updateProduct(productName, quantity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void updateProduct(String productName, int quantity) {
        String url = BASE_URL + "/updateProduct.php";
        RequestQueue queue = Volley.newRequestQueue(context);

        // Prepare the JSON request body
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("productName", productName);
            requestBody.put("quantity", quantity);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create the JSON request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the server
                        try {
                            boolean success = response.getBoolean("success");
                            if (success) {
                                Log.d(TAG, "Product updated successfully");
                            } else {
                                String error = response.getString("error");
                                Log.e(TAG, "Failed to update product: " + error);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error updating product: " + error.getMessage());
                    }
                });

        // Add the request to the queue
        queue.add(request);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context, "Products updated successfully", Toast.LENGTH_SHORT).show();
    }
}

