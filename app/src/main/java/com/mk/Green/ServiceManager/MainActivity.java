package com.mk.Green.ServiceManager;

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
import com.google.android.material.navigation.NavigationView;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Driver.Fragments.displaymessage;
import com.mk.Green.Driver.Fragments.requestFrag;

import com.mk.Green.ServiceManager.Fragments.addOrder;
import com.mk.Green.ServiceManager.Fragments.approvedFrag;
import com.mk.Green.R;
import com.mk.Green.ServiceManager.Fragments.compFrag;
import com.mk.Green.ServiceManager.Fragments.incomingFrag;
import com.mk.Green.ServiceManager.Fragments.incomingFragquote;
import com.mk.Green.ServiceManager.Fragments.incomingSupFrag;
import com.mk.Green.ServiceManager.Fragments.requestFragShipped;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
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
        // email = intent.getStringExtra("email");
        FragmentTransaction transaction =MainActivity.this.getSupportFragmentManager().beginTransaction();
//        incomingSupFrag fragB = new incomingSupFrag();
//        transaction.replace(R.id.my_frame_layout,fragB);
//        transaction.addToBackStack(null)
//                .commit();
        addOrder frag = new addOrder();
        //frag.setArguments(b);
        transaction.replace(R.id.my_frame_layout, frag);
        transaction.addToBackStack(null)
                .commit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction1 =MainActivity.this.getSupportFragmentManager().beginTransaction();
                Bundle b = new Bundle();
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.incoming:
                        addOrder frag = new addOrder();
                        frag.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout, frag);
                        transaction1.addToBackStack(null)
                                .commit();

//                        incomingSupFrag fragB = new incomingSupFrag();
//                        transaction1.replace(R.id.my_frame_layout,fragB);
//                        transaction1.addToBackStack(null)
//                                .commit();
                        break;
                    case R.id.quote:
                        incomingFragquote fragn = new incomingFragquote();
                        fragn.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout, fragn);
                        transaction1.addToBackStack(null)
                                .commit();

//                        incomingSupFrag fragB = new incomingSupFrag();
//                        transaction1.replace(R.id.my_frame_layout,fragB);
//                        transaction1.addToBackStack(null)
//                                .commit();
                        break;
                    case R.id.edit:
                        //b.putString("email",email);
                        incomingFrag fragB = new incomingFrag();
                        // fragB.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout,fragB);
                        transaction1.addToBackStack(null)
                                .commit();

//                        compFrag fragA = new compFrag();
//                        transaction1.replace(R.id.my_frame_layout,fragA);
//                        transaction1.addToBackStack(null)
//                                .commit();
                        break;
                    case R.id.add:
                        compFrag fragA = new compFrag();
                        transaction1.replace(R.id.my_frame_layout,fragA);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.mess:
                        b.putString("user","Service Manager");
                        displaymessage fragb = new displaymessage();
                        fragb.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout,fragb);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
//                    case R.id.request:
//                       // b.putString("email",email);
//                        //   setContentView(R.layout.childcare_activity_main);
//                        requestFrag fragr = new requestFrag();
//                        fragr.setArguments(b);
//                        transaction1.replace(R.id.my_frame_layout,fragr);
//                        transaction1.addToBackStack(null)
//                                .commit();
//                        break;
//                    case R.id.requestApproved:
//                        // b.putString("email",email);
//                        //   setContentView(R.layout.childcare_activity_main);
//                        approvedFrag fragt = new approvedFrag();
//                        fragt.setArguments(b);
//                        transaction1.replace(R.id.my_frame_layout,fragt);
//                        transaction1.addToBackStack(null)
//                                .commit();
                      //  break;
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