package com.mk.Green.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.R;


import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private ProgressDialog loadingBar;

    EditText password,email,phoneq;
    Button register,login;
    CheckBox checkedStatus;
    Spinner spinner;

   static String user="";
    String teaser="";
    TextView ref;
    ProgressDialog progressDialog;
    private RequestQueue mRequestQueue;
    //https://greencarpet.blupayinc.com/admin/
    public static String ip="https://greencarpet.blupayinc.com/admin/";// +
          //  "recyclerview/update.php";//192.168.175.37
    //String  url="http://"+ip+"/floordecor/loginCustomer.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ///////////////////////  HIDE STATUS BAR  ///////////////////////////////////
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ref=findViewById(R.id.ref);

        ///////////////////////////////////////////////////////////////////////////////


        loadingBar = new ProgressDialog(LoginActivity.this,R.style.MyAlertDialogStyle);

        email = findViewById(R.id.email);

        spinner=findViewById(R.id.choiceOf);
        password = findViewById(R.id.password);
        checkedStatus = findViewById(R.id.checkbox);


//        final String curator=ipp.getIp();


        String [] userers={"Driver","Finance","Customer", "Service Manager","Cleaner","Supplier","Inventory","Supervisor"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,userers);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        ref.setText("Driver");

                        break;
                    case 1:
                        ref.setText("Finance");
                       // uRl=url;

                        break;
                    case 2:
                        ref.setText("Customer");


                        break;
                    case 3:
                        ref.setText("Service Manager");
                        //
                        //uRl=url;

                        break;
                    case 4:
                        ref.setText("Cleaner");
                        break;
                    case 5:
                        ref.setText("Supplier");

                        break;

                    case 6:
                        ref.setText("Inventory");

                        break;
                    case 7:
                        ref.setText("Supervisor");

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        login = findViewById(R.id.login1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ref.getText().toString().equals("")) {
                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    Toast.makeText(LoginActivity.this, "choose user first", Toast.LENGTH_SHORT).show();
                }
                else if(ref.getText().toString().equals("Driver")){

                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    loginInve(tex_email,tex_password);}
                }
                else if(ref.getText().toString().equals("Service Manager")){

                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    loginship(tex_email,tex_password);}
                }
                else if(ref.getText().toString().equals("Finance")){
                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        loginF(tex_email,tex_password);}
                }
                else if(ref.getText().toString().equals("Cleaner")){
                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        loginsup(tex_email,tex_password);}
                }
                else if(ref.getText().toString().equals("Supplier")){
                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        loginsuppl(tex_email,tex_password,"supplier");}
                }
                else if(ref.getText().toString().equals("Inventory")){
                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        loginsuppl(tex_email,tex_password,"Inventory");}
                }
                else if(ref.getText().toString().equals("Supervisor")){
                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        loginsuppl(tex_email,tex_password,"Supervisor");}
                }
                else if(ref.getText().toString().equals("Drivers")){
                    String tex_email = email.getText().toString();
//                    String pho = phoneq.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                     //   loginD(tex_email,tex_password);
                    }
                }

                else if(ref.getText().toString().equals("Customer Care")){
                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    logincare(tex_email,tex_password);}
                }

                else if(ref.getText().toString().equals("Customer")){
                    String tex_email = email.getText().toString();
                    String tex_password = password.getText().toString();
                    if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                        Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        login(tex_email,tex_password);
                    }
                }


            }
        });



        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
               // finish();
            }
        });


    }

    private void insertChild(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Uploading data");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        mRequestQueue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,ip+"login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String Login ="Records updated successfully";
                if(response.trim().equals(Login)) {
                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    MainIntent.putExtra("email",email);
                    startActivity(MainIntent);
                    //sendToMainActivity();
                    Toast.makeText(LoginActivity.this, "Welcome Customer", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();


                }

                else {
                    Toast.makeText(LoginActivity.this,response.trim(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("psw",password);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }


    private void login(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging into your account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        mRequestQueue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,ip+"login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String Login ="Login";
                if(response.trim().equals(Login)) {


//                    if(user.)
//                    {
//
//                    }
                  //  Toast.makeText(LoginActivity.this, GetData.user, Toast.LENGTH_SHORT).show();
                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    MainIntent.putExtra("email",email);
                    startActivity(MainIntent);
                    //sendToMainActivity();
                    Toast.makeText(LoginActivity.this, "Welcome Customer", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();


                }

                else {
                    Toast.makeText(LoginActivity.this,response.trim(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("psw",password);
                param.put("choice","Customer");
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }
    private void login_user(String loginEmail, String loginPassword) {


    }

    @Override
    public void onBackPressed() {

    }
    private void logincare(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging into your service care account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        mRequestQueue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,ip+"login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String Login ="Login";
                if(response.trim().equals(Login)) {


                    startActivity(new Intent(LoginActivity.this, com.mk.Green.Driver.MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Welcome customer care", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

                else {
                    Toast.makeText(LoginActivity.this,response.trim(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("psw",password);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }
    private void loginF(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging into your finance account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        mRequestQueue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,ip+"login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String Login ="Login";
                if(response.trim().equals(Login)) {


                    startActivity(new Intent(LoginActivity.this, com.mk.Green.Finance.MainActivity.class));

                    Toast.makeText(LoginActivity.this, "Welcome finance manager", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

                else {
                    Toast.makeText(LoginActivity.this,response.trim(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("psw",password);
                param.put("choice","Finance");
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }
    private void loginInve(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging into your driver  account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        mRequestQueue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,ip+"login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String Login ="Login";
                if(response.trim().equals(Login)) {

                    Intent MainIntent = new Intent(LoginActivity.this, com.mk.Green.Driver.MainActivity.class);
                    MainIntent.putExtra("email",email);
                    startActivity(MainIntent);


                    Toast.makeText(LoginActivity.this, "Welcome driver", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

                else {
                    Toast.makeText(LoginActivity.this,response.trim(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString()+"kk", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
               // params.put("Cookie", "__test=YOUR COOKIE HERE; expires=Friday, January 1, 2038 at 5:25:55 AM; path=/");
                params.put("email",email);
                params.put("psw",password);
                params.put("choice","Driver");
                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }
    private void loginsuppl(final String email, final String password, final String choice){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging into your "+ choice+" account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        mRequestQueue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,ip+"login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String Login ="Login";
                if(response.trim().equals(Login)) {



                    if(choice.equals("supplier")){

                        Intent MainIntent = new Intent(LoginActivity.this, com.mk.Green.Supplier.MainActivity.class);
                        MainIntent.putExtra("email",email);
                        startActivity(MainIntent);
                      //  startActivity(new Intent(LoginActivity.this,com.mk.Green.Supplier.MainActivity.class));
                    }
                    if(choice.equals("Inventory")){
                        startActivity(new Intent(LoginActivity.this,com.mk.Green.Inventory.MainActivity.class));
                    }
                    if(choice.equals("Supervisor")){
                        startActivity(new Intent(LoginActivity.this,com.mk.Green.Supervisor.MainActivity.class));
                    }
                     Toast.makeText(LoginActivity.this, "Welcome  "+ choice+" ", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

                else {
                    Toast.makeText(LoginActivity.this,response.trim(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("psw",password);
                param.put("choice",choice);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }
    private void loginsup(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging into your Cleaner account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        mRequestQueue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,ip+"login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String Login ="Login";
                if(response.trim().equals(Login)) {

                    Intent MainIntent = new Intent(LoginActivity.this, com.mk.Green.Cleaner.MainActivity.class);
                    MainIntent.putExtra("email",email);
                    startActivity(MainIntent);
//                    startActivity(new Intent(LoginActivity.this,com.mk.Green.Cleaner.MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Welcome cleaner", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();

                }

                else {
                    Toast.makeText(LoginActivity.this,response.trim(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("psw",password);
                param.put("choice","cleaner");
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }
    private void loginship(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging into your service manager  account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        mRequestQueue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,ip+"login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String Login ="Login";
                if(response.trim().equals(Login)) {

                    Intent MainIntent = new Intent(LoginActivity.this, com.mk.Green.ServiceManager.MainActivity.class);
                    MainIntent.putExtra("email",email);
                    startActivity(MainIntent);


                    Toast.makeText(LoginActivity.this, "Welcome service manager", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

                else {
                    Toast.makeText(LoginActivity.this,response.trim(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("psw",password);
                param.put("choice","service");
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }
}

