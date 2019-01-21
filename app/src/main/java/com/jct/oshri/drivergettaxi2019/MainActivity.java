package com.jct.oshri.drivergettaxi2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jct.oshri.drivergettaxi2019.model.entities.Driver;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent myIntent = getIntent();
        driver = (Driver) myIntent.getSerializableExtra("com.jct.oshri.drivergettaxi2019.model.entities.Driver");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String upperFirstName = driver.firstName.substring(0, 1).toUpperCase() + driver.firstName.substring(1);
        String upperLastName = driver.lastName.substring(0, 1).toUpperCase() + driver.lastName.substring(1);

        setTitle("Welcome" + " " + upperFirstName);

        View header_nav = navigationView.getHeaderView(0);

        TextView name = header_nav.findViewById(R.id.textView_name);
        name.setText(upperFirstName + " " + upperLastName);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_personal_zone) {
            String upperFirstName = driver.firstName.substring(0, 1).toUpperCase() + driver.firstName.substring(1);
            setTitle(upperFirstName + " zone");
            Bundle bundle = new Bundle();
            bundle.putSerializable("Driver", driver);

            UpdateDataFragment fragment = new UpdateDataFragment();
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_my_rides) {
            setTitle("my Rides");
            MyRidesFragment fragment = new MyRidesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_waiting_rides) {
            setTitle("Waiting Rides");
            Bundle bundle = new Bundle();
            bundle.putSerializable("Driver", driver);
            WaitingRidesFragment fragment = new WaitingRidesFragment();
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_exit) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);  // go to custom activity (Main activity)
            intent.putExtra("com.jct.oshri.drivergettaxi2019.model.entities.Driver", driver);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
