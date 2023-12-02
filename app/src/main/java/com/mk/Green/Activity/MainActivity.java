package com.mk.Green.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

//import com.mk.Green.KidActivity.Fragment.displaychild;
import com.mk.Green.Activity.Adapters.UIConsts;
import com.mk.Green.Activity.Fragment.HDialog;
import com.mk.Green.Activity.Fragment.ServicesDisplay;
import com.mk.Green.Activity.Fragment.addOrder;
import com.mk.Green.Activity.Fragment.faqs;
import com.mk.Green.R;

import androidx.annotation.NonNull;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.mk.Green.Activity.Fragment.incomingFrag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
private String email;  private Fragment fragment;
    public String FragmentMethod() {

        return email;
    }



    String user;

    private String getJsonData(String serverUrl) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String customerJsonString = null;
        try {
            URL url = new URL(serverUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            customerJsonString = buffer.toString();
        } catch (IOException e) {
            Log.e(TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
        return customerJsonString;
    }
     String name="",address1="";
    private void displayCustomerData(String customerData) {
        try {
            JSONArray customerArray = new JSONArray(customerData);
            JSONObject customerObject = customerArray.getJSONObject(0);

            String customerName = customerObject.getString("fullname");
            String address = customerObject.getString("address");
            name=customerName;
            address1=address;
           // Toast.makeText(this, customerName+address, Toast.LENGTH_SHORT).show();
            //customerNameTextView.setText(customerName);
            //addressTextView.setText(address);
        } catch (JSONException e) {
            Log.e(TAG, "Error ", e);
        }
    }
    Spinner spinner;  String choice="";
    private void showRecoverPasswordDialog() {
        final Dialog quantityDialog = new Dialog(this);
        quantityDialog.setContentView(R.layout.messcustomer);

        String [] resett={"Choose user", "Driver","Finance", "Service Manager","Cleaner","Inventory","Supervisor"};
        //String [] resett={"Choose user","Parent/Guardian","Finance","Caregiver", "Physician","Supervisor"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,resett);
        spinner= quantityDialog.findViewById(R.id.choiceOf);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        choice="choose user";
                        // ref.setText("Inventory");

                        break;

                    case 1:
                        //   ref.setText("Customer");
                        choice="Driver";

                        break;
                    case 2:
                        //   ref.setText("Drivers");
                        //
                        //    uRl=url;
                        choice="Finance";
                        break;
                    case 3:
                        // ref.setText("Supplier");
                        choice="Service Manager";
                        break;
                    case 4:
                        // ref.setText("Customer Care");
                        choice="Cleaner";
                        break;
                    case 5:
                        // ref.setText("Customer Care");
                        choice="Inventory";
                        break;
                    case 6:
                        // ref.setText("Customer Care");
                        choice="Supervisor";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        quantityDialog.setCancelable(false);
        final EditText quantityNo =quantityDialog.findViewById(R.id.email1);
//        quantityNo.setVisibility(View.GONE);
        Button cancelBtn = quantityDialog.findViewById(R.id.cancel_btn);
        Button okBtn = quantityDialog.findViewById(R.id.ok_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityDialog.dismiss();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //   String productQuantity_str = quantityNo.getText().toString() ;
                if(choice.equals("choose user")) {

                    Toast.makeText(MainActivity.this, "choose user to message first!!", Toast.LENGTH_SHORT).show();
                }
                else if(choice.equals("Finance")){
                    Intent MainIntent = new Intent(MainActivity.this, ChatActivity.class);
                    MainIntent.putExtra("choice","Finance");
                    MainIntent.putExtra("current",name);
                    startActivity(MainIntent);
                    quantityDialog.dismiss();

                }
                else if(choice.equals("Customer")){
                    quantityDialog.dismiss();

                }
                else if(choice.equals("Service Manager")){
                    //  b.putString("fullname",name1);
                    Intent MainIntent = new Intent(MainActivity.this, ChatActivity.class);
                    MainIntent.putExtra("choice","Service Manager");
                    MainIntent.putExtra("current",name);
                    startActivity(MainIntent);
                    quantityDialog.dismiss();

                }
                else if(choice.equals("Supervisor")){

                    Intent MainIntent = new Intent(MainActivity.this, ChatActivity.class);
                    MainIntent.putExtra("choice","Supervisor");
                    MainIntent.putExtra("current",name);
                    startActivity(MainIntent);
                    quantityDialog.dismiss();
                }
                else if(choice.equals("Cleaner")){

                    Intent MainIntent = new Intent(MainActivity.this, ChatActivity.class);
                    MainIntent.putExtra("choice","Cleaner");
                    MainIntent.putExtra("current",name);
                    startActivity(MainIntent);
                    quantityDialog.dismiss();
                }
                else if(choice.equals("Inventory")){

                    Intent MainIntent = new Intent(MainActivity.this, ChatActivity.class);
                    MainIntent.putExtra("choice","Inventory");
                    MainIntent.putExtra("current",name);
                    startActivity(MainIntent);
                    quantityDialog.dismiss();
                }
                else if(choice.equals("Driver")){

                    Intent MainIntent = new Intent(MainActivity.this, ChatActivity.class);
                    MainIntent.putExtra("choice","Driver");
                    MainIntent.putExtra("current",name);
                    startActivity(MainIntent);
                    quantityDialog.dismiss();
                }
            }
        });
        quantityDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = getIntent();
        email = intent.getStringExtra("email");
        new Thread(new Runnable() {
            @Override
            public void run() {
                    String customerData = getJsonData(LoginActivity.ip+"recyclerview/customer.php?email=" + email);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayCustomerData(customerData);
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                        Bundle b = new Bundle();
//                        FragmentTransaction transaction1 = MainActivity.this.getSupportFragmentManager().beginTransaction();
//                        b.putString("name", name);
//                        b.putString("category", "all");
//                        b.putString("email", email);
//                        b.putString("address", address1);
//                        incomingFrag fragB = new incomingFrag();
//                        fragB.setArguments(b);
//                        transaction1.replace(R.id.my_frame_layout, fragB);
//                        transaction1.addToBackStack(null)
//                                .commit();
                      //  Log.e(TAG, "Error ");



                        b.putString("size", "null");
                        b.putString("desc", "Enter descriptions");
                        b.putString("image","0");
                        b.putString("price", "null");

                        FragmentTransaction transaction = ((FragmentActivity)MainActivity.this).getSupportFragmentManager().beginTransaction();
                        b.putString("name", name);
                        b.putString("address", address1);
                        b.putString("email", email);
                        addOrder fragB = new addOrder();
                        fragB.setArguments(b);
                        transaction.replace(R.id.my_frame_layout, fragB);
                        transaction.addToBackStack(null).commit();


                    }
                });
            }
        }).start();
        //new GetData(getApplicationContext()).execute("http://"+LoginActivity.ip+"/green/admin/recyclerview/customer.php");
        //


        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction1 = MainActivity.this.getSupportFragmentManager().beginTransaction();
                int id = item.getItemId();
                Bundle b = new Bundle();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {




                    case R.id.home:

                        if (name != "" && address1 != "") {

                            b.putString("size", "null");
                            b.putString("desc", "Enter descriptions");
                            b.putString("image","0");
                            b.putString("price", "null");

                            FragmentTransaction transaction = ((FragmentActivity)MainActivity.this).getSupportFragmentManager().beginTransaction();
                            b.putString("name", name);
                            b.putString("address", address1);
                            b.putString("email", email);
                            addOrder fragB = new addOrder();
                            fragB.setArguments(b);
                            transaction.replace(R.id.my_frame_layout, fragB);
                            transaction.addToBackStack(null).commit();

                        }
                        break;

//                    case R.id.add:
//
//                        if (name != "" && address1 != "") {
//                            b.putString("category", "all");
//                            b.putString("name", name);
//                            b.putString("email", email);
//                            b.putString("address", address1);
//                            incomingFrag fragB = new incomingFrag();
//                            fragB.setArguments(b);
//                            transaction1.replace(R.id.my_frame_layout, fragB);
//                            transaction1.addToBackStack(null)
//                                    .commit();
//
//                        }
//                        break;
       //             case R.id.pest:
//
//                        if (name != "" && address1 != "") {
//                            b.putString("category", "Pest Control Service");
//                            b.putString("name", name);
//                            b.putString("email", email);
//                            b.putString("address", address1);
//                            incomingFrag fragB = new incomingFrag();
//                            fragB.setArguments(b);
//                            transaction1.replace(R.id.my_frame_layout, fragB);
//                            transaction1.addToBackStack(null)
//                                    .commit();
//
//                        }
//                        break;
//                    case R.id.carp:
//
//                        if (name != "" && address1 != "") {
//                            b.putString("category", "Carpet Cleaning");
//                            b.putString("name", name);
//                            b.putString("email", email);
//                            b.putString("address", address1);
//                            incomingFrag fragB = new incomingFrag();
//                            fragB.setArguments(b);
//                            transaction1.replace(R.id.my_frame_layout, fragB);
//                            transaction1.addToBackStack(null)
//                                    .commit();
//
//                        }
//                        break;

//                    case R.id.car:
//
//                        if (name != "" && address1 != "") {
//                            b.putString("category", "Car-Interior-Cleaning");
//                            b.putString("name", name);
//                            b.putString("email", email);
//                            b.putString("address", address1);
//                            incomingFrag fragB = new incomingFrag();
//                            fragB.setArguments(b);
//                            transaction1.replace(R.id.my_frame_layout, fragB);
//                            transaction1.addToBackStack(null)
//                                    .commit();
//
//                        }
//                        break;
//                    case R.id.sofa:
//
//                        if (name != "" && address1 != "") {
//                            b.putString("category", "Sofa Cleaning");
//
//                            b.putString("name", name);
//                            b.putString("email", email);
//                            b.putString("address", address1);
//                            incomingFrag fragB = new incomingFrag();
//                            fragB.setArguments(b);
//                            transaction1.replace(R.id.my_frame_layout, fragB);
//                            transaction1.addToBackStack(null)
//                                    .commit();
//
//                        }
//                        break;
//                    case R.id.matt:
//
//                        if (name != "" && address1 != "") {
//                            b.putString("category", "Mattress Cleaning");
//
//                            b.putString("name", name);
//                            b.putString("email", email);
//                            b.putString("address", address1);
//                            incomingFrag fragB = new incomingFrag();
//                            fragB.setArguments(b);
//                            transaction1.replace(R.id.my_frame_layout, fragB);
//                            transaction1.addToBackStack(null)
//                                    .commit();
//
//                        }
                        //break;
                    case R.id.help:

                        loadFragment(new faqs());

                        break;
                    case R.id.about:

                        new HDialog().show(getFragmentManager(), UIConsts.Fragments.ABOUT_DIALOG_TAG);

                        break;
                    //
                    case R.id.feed:

                        startActivity(new Intent(MainActivity.this, FeedbackActivity.class));

                        break;
                    case R.id.activityhistory:
                        Bundle c = new Bundle();
                        c.putString("email", email);
                        if (name != "" && address1 != "") {
                            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                            c.putString("name", name);
                            c.putString("address", address1);
                            FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
                            ServicesDisplay fragc = new ServicesDisplay();
                            fragc.setArguments(c);
                            transaction.replace(R.id.my_frame_layout, fragc);
                            transaction.addToBackStack(null)
                                    .commit();
                        }

                        // loadFragment(new ServicesDisplay());
                        // startActivity(new Intent(MainActivity.this, ImagesActivity.class));
                        break;
                    case R.id.logout:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        break;
                    case R.id.mess:
                       showRecoverPasswordDialog();
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });


    }
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.my_frame_layout, fragment);
        transaction.addToBackStack(null).commit();
    }
}