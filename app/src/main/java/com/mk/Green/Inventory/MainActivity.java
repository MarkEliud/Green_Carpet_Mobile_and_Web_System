package com.mk.Green.Inventory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Driver.Fragments.displaymessage;
import com.mk.Green.Driver.Fragments.requestFrag;
import com.mk.Green.Inventory.Fragments.addOrder;
import com.mk.Green.Inventory.Fragments.incomingAllocateItems;
import com.mk.Green.Inventory.Fragments.incomingFrag;
import com.mk.Green.Inventory.Fragments.inveApprove;
import com.mk.Green.Inventory.Fragments.supplyApprove;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Fragments.approvedFrag;

public class MainActivity extends AppCompatActivity {
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        final Intent intent = getIntent();
        email = intent.getStringExtra("email");
        FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
        incomingFrag fragB = new incomingFrag();
        // fragB.setArguments(b);
        transaction.replace(R.id.my_frame_layout,fragB);
        transaction.addToBackStack(null)
                .commit();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform your action here
                FragmentTransaction transaction2= MainActivity.this.getSupportFragmentManager().beginTransaction();
              //  Bundle b = new Bundle();
                addOrder frag = new addOrder();
                //frag.setArguments(b);
                transaction2.replace(R.id.my_frame_layout, frag);
                transaction2.addToBackStack(null)
                        .commit();
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction1 = MainActivity.this.getSupportFragmentManager().beginTransaction();
                Bundle b = new Bundle();
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.viewp:
                        //b.putString("email",email);
                        incomingFrag fragB = new incomingFrag();
                       // fragB.setArguments(b);
                      transaction1.replace(R.id.my_frame_layout,fragB);
                        transaction1.addToBackStack(null)
                                .commit();
                          break;
                    case R.id.addp:
                        addOrder frag = new addOrder();
                        frag.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout, frag);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.appritem:
                        incomingAllocateItems fragv = new incomingAllocateItems();
                        fragv.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout, fragv);
                        transaction1.addToBackStack(null)
                                .commit();

                        break;
                    case R.id.app:
                        inveApprove fragf = new inveApprove();
                        fragf.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout, fragf);
                        transaction1.addToBackStack(null)
                                .commit();

                        break;
                    case R.id.request:
                        // b.putString("email",email);
                        //   setContentView(R.layout.childcare_activity_main);
                        requestFrag fragr = new requestFrag();
                        fragr.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout,fragr);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.requestApproved:
                        // b.putString("email",email);
                        //   setContentView(R.layout.childcare_activity_main);
                        com.mk.Green.ServiceManager.Fragments.approvedFrag fragt = new approvedFrag();
                        fragt.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout,fragt);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.mess:
                        b.putString("user","Inventory");
                        displaymessage fragb = new displaymessage();
                        fragb.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout,fragb);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.logout:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        break;

                    default:
                        return true;

                }
                return true;
            }
        });


    }

}