package com.mk.Green.Cleaner.Class;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Cleaner.Fragments.fragitemcalc;
import com.mk.Green.Cleaner.Fragments.incomingDetails;
import com.mk.Green.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemUpdater {
    private static Context context = null;
    private ProgressDialog progressDialog;
    private List<Item> itemList;

    public ItemUpdater(Context context) {
        ItemUpdater.context = context;
    }

    public void updateProductTable(String items) {
        // Parse items string and create item list
        itemList = parseItems(items);

        // Show dialog with item list
        showDialog();
    }

    private List<Item> parseItems(String items) {
        List<Item> itemList = new ArrayList<>();

        String[] lines = items.split("\n");

        int itemCount = 0;
        String productName = "";
        int quantity = 0;
        String description = "";

        for (String line : lines) {
            if (line.startsWith("Product name: ")) {
                // If we have already started capturing an item, add it to the list
                if (itemCount > 0) {
                    Item item = new Item(productName, quantity, description);
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
            Item item = new Item(productName, quantity, description);
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

//    private void showDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_item_updater, null);
//        builder.setView(dialogView);
//        builder.setTitle("Update Items");
//
//        RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerView);
//        ItemAdapter adapter = new ItemAdapter(itemList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(adapter);
//
//        builder.setPositiveButton("Update", (dialog, which) -> {
//            // Perform product table update
//            updateProductTable();
//        });
//
//        builder.setNegativeButton("Cancel", (dialog, which) -> {
//            // Cancel update
//            dialog.dismiss();
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//    }
private void showDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_item_updater, null);
    builder.setView(dialogView);
    builder.setTitle("Update Items");

    RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerView);
    ItemAdapter adapter = new ItemAdapter(itemList);
    recyclerView.setLayoutManager(new LinearLayoutManager(context));
    recyclerView.setAdapter(adapter);

    builder.setPositiveButton("Update", null);
    builder.setNegativeButton("Cancel", (dialog, which) -> {
        // Cancel update
        dialog.dismiss();
    });

    AlertDialog dialog = builder.create();
    dialog.show();

    // Set the positive button click listener after dialog show
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(view -> {
        // Perform product table update
        updateProductTable();

        // Dismiss the dialog after the update is performed
        dialog.dismiss();
    });

    // Set up the text change listener for each EditText in the dialog
    adapter.setTextChangeListener(new ItemAdapter.TextChangeListener() {
        @Override
        public void onTextChanged(EditText editText) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    });
}


    private void updateProductTable() {
        // Show progress dialog
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Updating product table...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Perform update for each item
        for (Item item : itemList) {
            // Update the product table with the item's data

            // Create a request queue using Volley
            RequestQueue queue = Volley.newRequestQueue(context);
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




//    public static class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
//        private List<Item> itemList;
//
//        public ItemAdapter(List<Item> itemList) {
//            this.itemList = itemList;
//        }
//
//        @NonNull
//        @Override
//        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false);
//            return new ItemViewHolder(itemView);
//        }
////        @Override
////        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
////            Item item = itemList.get(position);
////            holder.nameTextView.setText(item.getName());
////            holder.quantityEditText.setText(String.valueOf(item.getQuantity()));
////
////            // Set an OnClickListener on the quantity EditText to trigger the keyboard
////            holder.quantityEditText.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    holder.quantityEditText.requestFocus();
////                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
////                    imm.showSoftInput(holder.quantityEditText, InputMethodManager.SHOW_IMPLICIT);
////                }
////            });
////        }
//
//
//        @Override
//        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//            Item item = itemList.get(position);
//            holder.nameTextView.setText(item.getName());
//            holder.quantityEditText.setText(String.valueOf(item.getQuantity()));
//            holder.quantityEditText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    holder.quantityEditText.requestFocus();
//                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(holder.quantityEditText, InputMethodManager.SHOW_FORCED);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return itemList.size();
//        }
//
//        public static class ItemViewHolder extends RecyclerView.ViewHolder {
//            TextView nameTextView;
//            EditText quantityEditText;
//
//            public ItemViewHolder(View itemView) {
//                super(itemView);
//                nameTextView = itemView.findViewById(R.id.nameTextView);
//                quantityEditText = itemView.findViewById(R.id.quantityEditText);
//            }
//        }
//    }
public static class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;
    private TextChangeListener textChangeListener;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.quantityEditText.setText(String.valueOf(item.getQuantity()));

        holder.quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int newQuantity = Integer.parseInt(charSequence.toString());
                    itemList.get(position).setQuantity(newQuantity);
                } catch (NumberFormatException e) {
                    // Handle invalid input
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        EditText quantityEditText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            quantityEditText = itemView.findViewById(R.id.quantityEditText);
        }
    }

    // Interface to listen for text change in EditText fields
    public interface TextChangeListener {
        void onTextChanged(EditText editText);
    }

    public void setTextChangeListener(TextChangeListener listener) {
        this.textChangeListener = listener;
    }
}

    public static class Item {
        private String name;
        private int quantity;
        private String description;

        public Item(String name, int quantity, String description) {
            this.name = name;
            this.quantity = quantity;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
