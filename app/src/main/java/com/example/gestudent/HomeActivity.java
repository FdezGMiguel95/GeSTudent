package com.example.gestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ImageView profPic;


    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar= findViewById(R.id.toolbar);
        //profPic = (ImageView) findViewById(R.id.ivProfMenu);

        getSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navHeader);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.mate);
        }

        View headerView = navigationView.getHeaderView(0);
        profPic = (ImageView) headerView.findViewById(R.id.ivProfMenu);
        profPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProfile = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(goProfile);
            }
        });
    }

    private void getSupportActionBar ( Toolbar toolbar ) {
    }


    @Override
    public boolean onNavigationItemSelected ( @NonNull MenuItem item ) {

            int itemid = item.getItemId();

            if(itemid ==R.id.mate) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

            }else if (itemid == R.id.lengua){

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LenguaFragment()).commit();

            }else if (itemid == R.id.ciencia){

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CienciasFragment()).commit();

            }else if (itemid == R.id.sociales){

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SocialesFragment()).commit();

            }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed () {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super .onBackPressed();
        }
    }
}