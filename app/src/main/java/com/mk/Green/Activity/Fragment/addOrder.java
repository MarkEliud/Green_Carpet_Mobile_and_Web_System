package com.mk.Green.Activity.Fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.Adapters.Constants;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.R;
import com.savvi.rangedatepicker.CalendarPickerView;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class addOrder extends Fragment  implements
        View.OnClickListener{
    //private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/child.php";

    public addOrder() {
        // Required empty public constructor
    }

    private static final int PICK_IMAGE_REQUEST = 1;
    public static final String url1 = LoginActivity.ip+"recyclerview/upload.php";
    private Button selectImageButton;
    private EditText nameEditText;
    private TextView dateEditText;
    private EditText code1;
    private EditText descriptionEditText;
    private Button uploadButton;

    private Bitmap imageBitmap;
String email,name1,address,price,size,desc,cm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_add_child, container, false);
        Bundle b = this.getArguments();
       // price="0";
       // size="0";
        if(b != null){
            email = b.getString("email");
            name1 = b.getString("name");
            address = b.getString("address");
            price = b.getString("price");
            size = b.getString("size");
            desc = b.getString("desc");
        }
        //calendar = Calendar.getInstance();
       // btnDatePicker.setOnClickListener(this);

        Toast.makeText(getActivity(), name1+"\n"+address, Toast.LENGTH_SHORT).show();
        selectImageButton = view.findViewById(R.id.select_image_button);
        nameEditText =  view.findViewById(R.id.name_edit_text);
        code1 =  view.findViewById(R.id.code1);
        dateEditText =  view.findViewById(R.id.dateEditText);
        descriptionEditText =  view.findViewById(R.id.description_edit_text);
        descriptionEditText.setText(desc);
        if(desc.equals("Enter descriptions")){
            descriptionEditText.setText(null);
            descriptionEditText.setHint("Enter descriptions");
        }
        dateEditText.setOnClickListener(this);
        if(price.equals("null")){
            cm="null";
            code1.setVisibility(View.GONE);
        }
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
        String code = code1.getText().toString();
        if(code1.getVisibility()!= View.GONE){
            Toast.makeText(getActivity(), "Please fill all the details", Toast.LENGTH_SHORT).show();

            return;
        }
        //|| code.isEmpty()
        if (name.isEmpty() || date.isEmpty() ||
                description.isEmpty() || imageBitmap == null ) {
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
                            Toast.makeText(getActivity(), "Ordered successfully, wait \nfor quotation from service manager!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("date", date);
                params.put("description", description);
                params.put("image", imageString);

                params.put("amount", price);
                params.put("code", code);
                params.put("status", "Not Paid Yet");
                params.put("address", address);
                params.put("customer", name1);
                params.put("fprice", price);
                params.put("email", email);

                params.put("size", size);
               // params.put("email", email);

                return params;
            }
        };

        // Add the request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
    //private Calendar calendar;
    CalendarPickerView calendar;private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    public void onClick(View v) {

        if (v == dateEditText) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mYear);
        calendar.set(Calendar.MONTH, mMonth);
        calendar.set(Calendar.DAY_OF_MONTH, mDay);
        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mHour);
        calendar.set(Calendar.SECOND, 0);

    }


    public boolean isValidCode(final String code) {

        boolean number = false;
        boolean character = false;
        boolean symbol = false;
        boolean length = false;
        int letterCounter = 0;
        int numCounter = 0;

        char [] letters = code.toCharArray();

        for (char c: letters){
            if(Character.isLetter(c)) {
                letterCounter++;
            }
            else if(Character.isDigit(c)) {
                numCounter++;
            }
            else {
                symbol = true;
            }
        }

        //Checking booleans
        if (code.length()>=10) {
            length = true;
        }
        if (letterCounter>=8) {
            character = true;
        }
        if (numCounter>=2) {
            number = true;
        }

        if (character && length && number && !symbol){
            Toast.makeText(getActivity(), "valid mpesa code", Toast.LENGTH_SHORT).show();
            System.out.println("Success");
            return true;
        }
        else {
            System.out.println("Invalid");
            return false;
        }

    }

}