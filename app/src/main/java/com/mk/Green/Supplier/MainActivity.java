package com.mk.Green.Supplier;

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
import com.mk.Green.R;
import com.mk.Green.Supplier.Fragments.approvedFrag;
import com.mk.Green.Supplier.Fragments.incomingFrag;
import com.mk.Green.Supplier.Fragments.supplyApprove;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplier);
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
        String email;
        email = intent.getStringExtra("email");
        FragmentTransaction transaction =MainActivity.this.getSupportFragmentManager().beginTransaction();
        Bundle b = new Bundle();
        b.putString("email",email);
        supplyApprove fragB = new supplyApprove();
        fragB.setArguments(b);
        transaction.replace(R.id.my_frame_layout,fragB);
        transaction.addToBackStack(null)
                .commit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction =MainActivity.this.getSupportFragmentManager().beginTransaction();
                Bundle b = new Bundle();
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.incoming:
                        b.putString("email",email);
                        supplyApprove fragB = new supplyApprove();
                         fragB.setArguments(b);
                        transaction.replace(R.id.my_frame_layout,fragB);
                        transaction.addToBackStack(null)
                                .commit();
                        break;

                    case R.id.add:
                              b.putString("email",email);
                        approvedFrag fragb = new   approvedFrag();
                        fragb.setArguments(b);
                        transaction.replace(R.id.my_frame_layout,fragb);
                        transaction.addToBackStack(null)
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