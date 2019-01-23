package com.jct.oshri.drivergettaxi2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.datasource.FireBase_DBManager;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.OptionOfTrip;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;


public class TakeRideActivity extends AppCompatActivity {

    //View v;
    TextView name;
    TextView email;
    TextView phone;

    //String SendSMSDEST;
    Button takeButton;
    Button cancel;
    Ride r;
    Driver driver;

    AlphaAnimation a = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_ride);
        findViews();
    }

    private void findViews() {

        Intent myIntent = getIntent();
        r = (Ride) myIntent.getSerializableExtra("Ride");
        driver = (Driver) myIntent.getSerializableExtra("Driver");

        takeButton = findViewById(R.id.TakeItButton);
        cancel = findViewById(R.id.cancel_TakeRide);
        name = findViewById(R.id.name_cus);
        email = findViewById(R.id.email_cus);
        phone = findViewById(R.id.phone_cus);

        name.setText("Name: " + r.nameOfClient);
        email.setText("Email: " + r.emailOfClient);
        phone.setText("Phone Number: " + r.phoneNumberOfClient);

        takeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(a);
                onTakeIt(v);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(a);
                onCancel(v);
            }
        });
    }

    public void onTakeIt(View view) {

        //update ride
        r.setStatus(OptionOfTrip.IN_PROGGRESS);
        r.setIdDriver(driver.getId());

        DB_manager dBase = factoryMethod.getManager();
        ((FireBase_DBManager) dBase).updateRide(r); // sending to DB

        Toast.makeText(getApplicationContext(), " Sent!",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(TakeRideActivity.this, MainActivity.class);  // go to custom activity (Main activity)
        intent.putExtra("Driver", driver);
        startActivity(intent);
    }

    public void onCancel(View view) {
        finish();
    }
}

