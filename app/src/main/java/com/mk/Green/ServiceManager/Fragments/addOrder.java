package com.mk.Green.ServiceManager.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.mk.Green.Activity.RegisterActivity;
import com.mk.Green.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class addOrder extends Fragment {
    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/child.php";
    String date;
    public addOrder() {
        // Required empty public constructor
    }

    private static final int PICK_IMAGE_REQUEST = 1;
    public static final String url1 =LoginActivity.ip+ "recyclerview/addservice.php";
    private Button selectImageButton;
    private EditText nameEditText;
    private EditText dateEditText;
    private EditText descriptionEditText;
    private Button uploadButton;

    private Bitmap imageBitmap;
String email,name1,address,select="Carpet Cleaning";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.addservice, container, false);
        Bundle b = this.getArguments();
        if(b != null){
            email = b.getString("email");
            name1 = b.getString("name");
            address = b.getString("address");
        }
        //Toast.makeText(getActivity(), name1+"\n"+address, Toast.LENGTH_SHORT).show();
        selectImageButton = view.findViewById(R.id.select_image_button);
        nameEditText =  view.findViewById(R.id.name_edit_text);
        dateEditText =  view.findViewById(R.id.date_edit_text);
        //dateEditText.setHint("Packages Left");
        descriptionEditText =  view.findViewById(R.id.description_edit_text);
        uploadButton =  view.findViewById(R.id.upload_button);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        Spinner spinner=view.findViewById(R.id.choiceOf);
        String [] userers={"Carpet Cleaning","Mattress Cleaning",
                "Sofa Cleaning", "Car-Interior-Cleaning","Pest Control Service"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,userers);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        select="Carpet Cleaning";
                        Toast.makeText(getActivity(), "selection: Carpet Cleaning", Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        select="Mattress Cleaning";
                        Toast.makeText(getActivity(), "selection: Mattress Cleaning", Toast.LENGTH_SHORT).show();


                        break;
                    case 2:
                        select="Sofa Cleaning";
                        Toast.makeText(getActivity(), "selection: Sofa Cleaning", Toast.LENGTH_SHORT).show();


                        break;
                    case 3:
                        select="Car-Interior-Cleaning";
                        dateEditText.setHint("Enter Name");
                        //
                        //uRl=url;
                        Toast.makeText(getActivity(), "selection: Car-Interior-Cleaning", Toast.LENGTH_SHORT).show();

                        break;
                    case 4:
                        select="Pest Control Service";
                        dateEditText.setHint("Enter Name");
                        Toast.makeText(getActivity(), "selection: Pest Control Service", Toast.LENGTH_SHORT).show();

                        break;



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
         date = dateEditText.getText().toString();
//        if(TextUtils.isEmpty(date) ){
//            date="";
//        }
        String name = nameEditText.getText().toString();

        String description = descriptionEditText.getText().toString();

        if (name.isEmpty()||date.isEmpty() || description.isEmpty() || imageBitmap == null) {
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
                        if (response.equals("Data uploaded successfully")) {
                            Toast.makeText(getActivity(), "success///////, wait \n", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                        }
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

//                params.put("category", category);
//                params.put("size", size);
//                params.put("price", price);
//                params.put("desc", desc);
//                params.put("image", image);

                params.put("price", name);
                params.put("size", date);
                params.put("desc", description);
                params.put("image", imageString);
              //  params.put("price", price);
                params.put("status", "add");
                params.put("category", select);
                return params;
            }
        };

        // Add the request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }



}