package com.mk.Green.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mk.Green.R;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {


    private ProgressDialog loadingBar;
    private CheckBox privacy_checkbox;
    String [] users={"Customer","Finance","Driver", "Service Manager","Cleaner","Supplier","Inventory","Supervisor"};
   // String []users ={"Driver","Customer","Inventory","Finance","Supplier","Customer Care","Shipment Manager"};
    Spinner spinner;
    String selectedUrl="",choice;
    String  url=LoginActivity.ip+"insert.php";
    Timestamp timestamp;




    EditText userName,secondname,emailAddress,password,mobile, address1,specia;
    RadioGroup radioGroup;
    Button register;
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toast.makeText(this, LoginActivity.ip, Toast.LENGTH_SHORT).show();

        ///////////////////////  HIDE STATUS BAR  ///////////////////////////////////
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //mAuth=FirebaseAuth.getInstance();

        ///////////////////////////////////////////////////////////////////////////////
      //  sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(sdf3.format(timestamp));

        loadingBar = new ProgressDialog(RegisterActivity.this,R.style.MyAlertDialogStyle);

        specia = findViewById(R.id.specia);
        userName = findViewById(R.id.username);
        emailAddress = findViewById(R.id.email);
        secondname=findViewById(R.id.sname);

        password = findViewById(R.id.password);

        spinner=findViewById(R.id.choiceOfUser);
        mobile = findViewById(R.id.mobile);
        address1 = findViewById(R.id.address);
        radioGroup = findViewById(R.id.radioButton);



        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,users);

        spinner.setAdapter(arrayAdapter);

        register = findViewById(R.id.register);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mobile.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                        address1.setVisibility(View.VISIBLE);
                        specia.setVisibility(View.GONE);
                     //   selectedUrl="http://"+LoginActivity.ip+"/bdcms/admin/insertDriver.php";
                        address1.setHint("Enter Address");
                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                               final String address = address1.getText().toString().trim();
                                final String user_name = userName.getText().toString().trim();
                                final String secName = secondname.getText().toString().trim();
                                final String email = emailAddress.getText().toString().trim();
                                final String txt_password = password.getText().toString().trim();
                                final String txt_mobile = mobile.getText().toString().trim();
                                int checkedId = radioGroup.getCheckedRadioButtonId();
                                RadioButton selected_gender = radioGroup.findViewById(checkedId);
                                if (selected_gender == null){
                                    Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                                }
//                                if (!isValidCode(address)){
//                                    address1.setError("Wrong address");
//                                    Toast.makeText(RegisterActivity.this, "Wrong license number", Toast.LENGTH_SHORT).show();
//                                }
                                else {
                                    final String gender = selected_gender.getText().toString();
                                    if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password) ||
                                            TextUtils.isEmpty(txt_mobile)){
                                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                                    }
                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                        emailAddress.setError("Enter a valid Email Address!");
                                        emailAddress.requestFocus();
                                        return;
                                    }
                                    if (txt_mobile.length()!=10){
                                        mobile.setError("Enter valid mobile number!");
                                        mobile.requestFocus();
                                        return;
                                    }
                                    if (txt_password.length()<=5){
                                        password.setError("Enter more than 6 characters for password");
                                        password.requestFocus();
                                        return;
                                    }
                                    if (address.length()==0){
                                        address1.setError("Enter valid address");
                                        address1.requestFocus();
                                        return;
                                    }
                                    else{
                                        registerCustomer(address,user_name,secName,email,txt_password,txt_mobile,gender);
                                    }
                                }


                            }
                        });



                        break;
                    case 1:
                        selectedUrl=url;
                        address1.setVisibility(View.VISIBLE);
                        mobile.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                        specia.setVisibility(View.GONE);
                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                final String address = address1.getText().toString();
                                final String user_name = userName.getText().toString();
                                final String secName = secondname.getText().toString();
                                final String email = emailAddress.getText().toString();
                                final String txt_password = password.getText().toString();
                                final String txt_mobile = mobile.getText().toString();
                                int checkedId = radioGroup.getCheckedRadioButtonId();
                                RadioButton selected_gender = radioGroup.findViewById(checkedId);
                                if (selected_gender == null){
                                    Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    final String gender = selected_gender.getText().toString();
                                    if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password) ||
                                            TextUtils.isEmpty(txt_mobile)){
                                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                                    }
                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                        emailAddress.setError("Enter a valid Email Address!");
                                        emailAddress.requestFocus();
                                        return;
                                    }
                                    if (txt_mobile.length()!=10){
                                        mobile.setError("Enter valid mobile number!");
                                        mobile.requestFocus();
                                        return;
                                    }
                                    else{
                                        registerFinance(address,user_name+" "+secName,email,txt_password,txt_mobile,gender,"Finance");
                                    }
                                }


                            }
                        });

                        break;
                    case 2:
                         choice = String.valueOf(spinner.getAdapter().getItem(i));
                        selectedUrl=LoginActivity.ip+"register.php";
                        address1.setVisibility(View.VISIBLE);
                        address1.setHint("Enter licence");
                        mobile.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                        specia.setVisibility(View.GONE);

                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                               final String address = address1.getText().toString();
                                final String user_name = userName.getText().toString();
                                final String secName = secondname.getText().toString();
                                final String email = emailAddress.getText().toString();
                                final String txt_password = password.getText().toString();
                               final String txt_mobile = mobile.getText().toString();
                                int checkedId = radioGroup.getCheckedRadioButtonId();
                                RadioButton selected_gender = radioGroup.findViewById(checkedId);
                                if (selected_gender == null){
                                    Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                                }



                                    if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password)){
                                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                                    }
                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                        emailAddress.setError("Enter a valid Email Address!");
                                        emailAddress.requestFocus();
                                        return;
                                    }
//
                                    else{
                                        final String gender = selected_gender.getText().toString();
                                        registerFinance(address,user_name+" "+secName,email,txt_password,txt_mobile,gender,"Driver");
                                     //   registerFinance(user_name+" "+secName,email,txt_password,txt_mobile,gender,"Finance");
                                    }



                            }
                        });

                        break;
                    case 3:
                        choice = String.valueOf(spinner.getAdapter().getItem(i));
                        selectedUrl=LoginActivity.ip+"register.php";
                        address1.setVisibility(View.VISIBLE);
                        address1.setHint("Address");
                        specia.setVisibility(View.INVISIBLE);
                        mobile.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);

                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //final String special = specia.getText().toString();
                                final String address = address1.getText().toString();
                                final String user_name = userName.getText().toString();
                                final String secName = secondname.getText().toString();
                                final String email = emailAddress.getText().toString();
                                final String txt_password = password.getText().toString();
                                final String txt_mobile = mobile.getText().toString();

                                {
                                    int checkedId = radioGroup.getCheckedRadioButtonId();
                                    RadioButton selected_gender = radioGroup.findViewById(checkedId);
                                    if (selected_gender == null){
                                        Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                                    }

                                    if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password)){
                                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                                    }
                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                        emailAddress.setError("Enter a valid Email Address!");
                                        emailAddress.requestFocus();
                                        return;
                                    }
//
                                    else{
                                        final String gender = selected_gender.getText().toString();
                                        registerPhys(selectedUrl,user_name+" "+secName,email,txt_password,gender,txt_mobile,address);
                                    }
                                }


                            }
                        });

                        break;
                    case 4:
                        choice = String.valueOf(spinner.getAdapter().getItem(i));
                        selectedUrl=LoginActivity.ip+"register.php";
                        address1.setVisibility(View.VISIBLE);
                        mobile.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);

                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                 final String address = address1.getText().toString();
                                final String user_name = userName.getText().toString();
                                final String secName = secondname.getText().toString();
                                final String email = emailAddress.getText().toString();
                                final String txt_password = password.getText().toString();
                                final String txt_mobile = mobile.getText().toString();

                                {
                                    int checkedId = radioGroup.getCheckedRadioButtonId();
                                    RadioButton selected_gender = radioGroup.findViewById(checkedId);
                                    if (selected_gender == null){
                                        Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                                    }

                                    if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password)){
                                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                                    }
                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                        emailAddress.setError("Enter a valid Email Address!");
                                        emailAddress.requestFocus();
                                        return;
                                    }
//
                                    else{
                                        final String gender = selected_gender.getText().toString();

                                        registerFinance(address,user_name+" "+secName,email,txt_password,txt_mobile,gender,"cleaner");

                                    }
                                }


                            }
                        });

                        break;
                    case 5:
                        choice = String.valueOf(spinner.getAdapter().getItem(i));
                        selectedUrl=LoginActivity.ip+"register.php";
                        address1.setVisibility(View.VISIBLE);
                        mobile.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                        specia.setVisibility(View.INVISIBLE);
                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                final String address = address1.getText().toString();
                                final String user_name = userName.getText().toString();
                                final String secName = secondname.getText().toString();
                                final String email = emailAddress.getText().toString();
                                final String txt_password = password.getText().toString();
                                final String txt_mobile = mobile.getText().toString();

                                {
                                    int checkedId = radioGroup.getCheckedRadioButtonId();
                                    RadioButton selected_gender = radioGroup.findViewById(checkedId);
                                    if (selected_gender == null){
                                        Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                                    }

                                    if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password)){
                                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                                    }
                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                        emailAddress.setError("Enter a valid Email Address!");
                                        emailAddress.requestFocus();
                                        return;
                                    }
//
                                    else{
                                        final String gender = selected_gender.getText().toString();

                                        registerFinance(address,user_name+" "+secName,email,txt_password,txt_mobile,gender,"supplier");

                                    }
                                }


                            }
                        });

                        break;
                    case 6:
                        choice = String.valueOf(spinner.getAdapter().getItem(i));
                        selectedUrl=LoginActivity.ip+"register.php";
                        address1.setVisibility(View.VISIBLE);
                        mobile.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                        specia.setVisibility(View.INVISIBLE);
                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                final String address = address1.getText().toString();
                                final String user_name = userName.getText().toString();
                                final String secName = secondname.getText().toString();
                                final String email = emailAddress.getText().toString();
                                final String txt_password = password.getText().toString();
                                final String txt_mobile = mobile.getText().toString();

                                {
                                    int checkedId = radioGroup.getCheckedRadioButtonId();
                                    RadioButton selected_gender = radioGroup.findViewById(checkedId);
                                    if (selected_gender == null){
                                        Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                                    }

                                    if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password)){
                                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                                    }
                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                        emailAddress.setError("Enter a valid Email Address!");
                                        emailAddress.requestFocus();
                                        return;
                                    }
//
                                    else{
                                        final String gender = selected_gender.getText().toString();

                                        registerFinance(address,user_name+" "+secName,email,txt_password,txt_mobile,gender,"inventory");

                                    }
                                }


                            }
                        });

                        break;
                    case 7:
                        choice = String.valueOf(spinner.getAdapter().getItem(i));
                        selectedUrl=LoginActivity.ip+"register.php";
                        address1.setVisibility(View.VISIBLE);
                        mobile.setVisibility(View.VISIBLE);
                        radioGroup.setVisibility(View.VISIBLE);
                        specia.setVisibility(View.INVISIBLE);
                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                final String address = address1.getText().toString();
                                final String user_name = userName.getText().toString();
                                final String secName = secondname.getText().toString();
                                final String email = emailAddress.getText().toString();
                                final String txt_password = password.getText().toString();
                                final String txt_mobile = mobile.getText().toString();

                                {
                                    int checkedId = radioGroup.getCheckedRadioButtonId();
                                    RadioButton selected_gender = radioGroup.findViewById(checkedId);
                                    if (selected_gender == null){
                                        Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                                    }

                                    if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password)){
                                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                                    }
                                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                        emailAddress.setError("Enter a valid Email Address!");
                                        emailAddress.requestFocus();
                                        return;
                                    }
//
                                    else{
                                        final String gender = selected_gender.getText().toString();

                                        registerFinance(address,user_name+" "+secName,email,txt_password,txt_mobile,gender,"Supervisor");

                                    }
                                }


                            }
                        });

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

private void regdriver(final String name, final String email, final String phone1, String pwd){


}
    private void registerCustomer(final String address,final String username,final String secName, final String email, final String password, final String mobile, final String gender) {
        if (mobile.length() == 10) {
            Toast.makeText(getApplicationContext(), secName, Toast.LENGTH_SHORT).show();

            final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setTitle("Registering your account");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setIndeterminate(false);
            progressDialog.show();


            StringRequest request = new StringRequest(Request.Method.POST,LoginActivity.ip+"register.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equals("You are registered successfully")) {
                       // regdriver(username+" "+secName,email, mobile, password);
                        Toast.makeText(RegisterActivity.this, "Wait for approval", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        progressDialog.dismiss();
                        // finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    //param.put("username", username);
                    param.put("name",username+" "+secName);
                    param.put("email", email);
                    param.put("psw", password);
                    param.put("mobile", mobile);
                    param.put("date", sdf3.format(timestamp));
                    param.put("gender", gender);
                    param.put("address", address);
                    param.put("choice", "Customer");

                    return param;

                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Singleton.getmInstance(RegisterActivity.this).addToRequestQueue(request);

        }else{
            Toast.makeText(RegisterActivity.this, "max phone length 10", Toast.LENGTH_SHORT).show();
            return;
        }
    }





    private void register_user(final String name, final String email, final String mobile, String password) {

//       loadingBar.setTitle("Creating New Account");
//        loadingBar.setMessage("Please wait, while we are creating new account for you...");
//        loadingBar.setCanceledOnTouchOutside(false);
//        Toast.makeText(this, "in firebase", Toast.LENGTH_SHORT).show();
//        loadingBar.show();


    }
    private void registerPhys(String url1,final String username,final String email, final String password,final String gender,final String mobile,final String address) {
        if (email.length() > 1) {
           // Toast.makeText(getApplicationContext(), secName, Toast.LENGTH_SHORT).show();

            final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setTitle("Registering your account");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setIndeterminate(false);
            progressDialog.show();


            StringRequest request = new StringRequest(Request.Method.POST,url1, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equals("You are registered successfully")) {
                        //register_user(username,email, mobile, password);
                        Toast.makeText(RegisterActivity.this, "Wait for approval", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        progressDialog.dismiss();
                        // finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("name", username);
                    param.put("email", email);
                    param.put("psw", password);
                    param.put("date", sdf3.format(timestamp));
                    param.put("gender", gender);
                    //param.put("special", specia);
                    param.put("address", address);
                    param.put("choice", "service");
                    param.put("mobile", mobile);
                    return param;

                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Singleton.getmInstance(RegisterActivity.this).addToRequestQueue(request);

        }else{
            Toast.makeText(RegisterActivity.this, "no email", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    private void registerFinance(final String address,final String username,final String email, final String password, final String mobile, final String gender, final String choice) {
        if (mobile.length() == 10) {
          //  Toast.makeText(getApplicationContext(), secName, Toast.LENGTH_SHORT).show();

            final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setTitle("Registering your account");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
           progressDialog.setIndeterminate(false);
           progressDialog.show();


            StringRequest request = new StringRequest(Request.Method.POST,LoginActivity.ip+"register.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equals("You are registered successfully")) {
                       register_user(username,email, mobile, password);
                        Toast.makeText(RegisterActivity.this, "Wait for approval", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        progressDialog.dismiss();
                       // finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("username", username);
                    param.put("date", sdf3.format(timestamp));
                    param.put("email", email);
                    param.put("psw", password);
                    param.put("mobile", mobile);
                    param.put("gender", gender);
                    param.put("address", address);
                    param.put("choice", choice);
                    return param;

                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Singleton.getmInstance(RegisterActivity.this).addToRequestQueue(request);

        }else{
            Toast.makeText(RegisterActivity.this, "max phone length 10", Toast.LENGTH_SHORT).show();
            return;
        }
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
        if (code.length()>=6) {
            length = true;
        }
        if (letterCounter>=3) {
            character = true;
        }
        if (numCounter>=3) {
            number = true;
        }

        if (character && length && number && !symbol){
            Toast.makeText(RegisterActivity.this, "valid license code", Toast.LENGTH_SHORT).show();
            System.out.println("Success");
            return true;
        }
        else {
            System.out.println("Invalid");
            return false;
        }

    }
}