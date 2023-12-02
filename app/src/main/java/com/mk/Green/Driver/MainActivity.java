package com.mk.Green.Driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Driver.Fragments.compFrag;
import com.mk.Green.Driver.Fragments.displaymessage;
import com.mk.Green.Driver.Fragments.incomingFrag;
import com.mk.Green.Driver.Fragments.requestFrag;
import com.mk.Green.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.childcare_activity_main);
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
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        Bundle b = new Bundle();
        b.putString("email",email);
        FragmentTransaction transaction =MainActivity.this.getSupportFragmentManager().beginTransaction();

        incomingFrag fragB = new incomingFrag();
        fragB.setArguments(b);
        transaction.replace(R.id.my_frame_layout,fragB);
        transaction.addToBackStack(null)
                .commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction2 =MainActivity.this.getSupportFragmentManager().beginTransaction();
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.incoming:
                        b.putString("email",email);
                       /// setContentView(R.layout.childcare_activity_main);
                        incomingFrag fragB = new incomingFrag();
                        fragB.setArguments(b);
                        transaction2.replace(R.id.my_frame_layout,fragB);
                        transaction2.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.completed:
                        b.putString("email",email);
                     //   setContentView(R.layout.childcare_activity_main);
                        compFrag fraga = new compFrag();
                        fraga.setArguments(b);
                        transaction2.replace(R.id.my_frame_layout,fraga);
                        transaction2.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.mess:
                        b.putString("user","Driver");
                        displaymessage fragb = new displaymessage();
                        fragb.setArguments(b);
                        transaction2.replace(R.id.my_frame_layout,fragb);
                        transaction2.addToBackStack(null)
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