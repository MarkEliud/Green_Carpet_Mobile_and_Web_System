package com.mk.Green.Cleaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Cleaner.Fragments.incomingFrag;
import com.mk.Green.Cleaner.Fragments.approvedFrag;
import com.mk.Green.Driver.Fragments.displaymessage;
import com.mk.Green.R;
//import com.mk.Green.Cleaner.Fragments.incomingSupFrag;

public class MainActivity extends AppCompatActivity {
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        Bundle k = new Bundle();
        k.putString("email",email);
        FragmentTransaction transaction =MainActivity.this.getSupportFragmentManager().beginTransaction();

        incomingFrag fragB = new incomingFrag();
        fragB.setArguments(k);
        transaction.replace(R.id.my_frame_layout,fragB);
        transaction.addToBackStack(null)
                .commit();



//        FragmentTransaction transaction =MainActivity.this.getSupportFragmentManager().beginTransaction();
//        incomingFrag fragB = new incomingFrag();
//        transaction.replace(R.id.my_frame_layout,fragB);
//        transaction.addToBackStack(null)
//                .commit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ff =MainActivity.this.getSupportFragmentManager().beginTransaction();
                Bundle b = new Bundle();
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.incoming:


                        b.putString("email",email);


                        incomingFrag fragB = new incomingFrag();
                        fragB.setArguments(b);
                        ff.replace(R.id.my_frame_layout,fragB);
                        ff.addToBackStack(null)
                                .commit();



                        break;
                    case R.id.add:
                        b.putString("email",email);
                        approvedFrag fragA = new approvedFrag();
                        fragA.setArguments(b);
                        ff.replace(R.id.my_frame_layout,fragA);
                        ff.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.mess:
                        //
                        b.putString("user","Cleaner");
                        displaymessage fragb = new displaymessage();
                        fragb.setArguments(b);
                        ff.replace(R.id.my_frame_layout,fragb);
                        ff.addToBackStack(null)
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