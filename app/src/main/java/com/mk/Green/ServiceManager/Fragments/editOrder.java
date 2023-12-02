package com.mk.Green.ServiceManager.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class editOrder extends Fragment {
    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/child.php";

    public editOrder() {
        // Required empty public constructor
    }

    private static final int PICK_IMAGE_REQUEST = 1;
    public static final String url1 = LoginActivity.ip+"recyclerview/product.php";
    private Button selectImageButton;
    private EditText nameEditText;
    private EditText dateEditText;
    private EditText descriptionEditText;
    private Button uploadButton;

    private Bitmap imageBitmap;
    private ImageView imageView;
    String price,desc, image,size,id,category;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.editproduct, container, false);
        Bundle b = this.getArguments();
        if(b != null){
            size = b.getString("size");
            desc = b.getString("desc");
            image = b.getString("image");
            price = b.getString("price");
            id = b.getString("id");
            category = b.getString("category");
        }
       // Toast.makeText(getActivity(), name1+"\n"+address, Toast.LENGTH_SHORT).show();
        TextView sizee,pricee;
        sizee = view.findViewById(R.id.size);
        pricee = view.findViewById(R.id.price);
       // if(category.equals())
        sizee.setText("Size/Name: ");
        pricee.setText("Price: ");
        selectImageButton = view.findViewById(R.id.select_image_button);
        imageView = view.findViewById(R.id.image_view);
        Picasso.get().load(image).into(imageView);
        nameEditText =  view.findViewById(R.id.name_edit_text);
        nameEditText.setText(size);
        dateEditText =  view.findViewById(R.id.date_edit_text);
        dateEditText.setText(price);
        descriptionEditText =  view.findViewById(R.id.description_edit_text);
        descriptionEditText.setText(desc);
        uploadButton =  view.findViewById(R.id.upload_button);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadData() {
        String name = nameEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (name.isEmpty() || date.isEmpty() || description.isEmpty() || imageBitmap == null) {
            Toast.makeText(getActivity(), "Please fill all the details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert the image to a Base64 encoded string
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = Base64.encodeToString(byteArray, Base64.DEFAULT);

        // Make a network request to upload the data to the server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
//                        if (response.equals("Data uploaded successfully")) {
//                            Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
//                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("size", name);
                params.put("price", date);
                params.put("description", description);
                params.put("image", imageString);

                params.put("oimage", id);
                params.put("status", "editserv");

                return params;
            }
        };

        // Add the request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }



}