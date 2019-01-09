package com.jct.oshri.drivergettaxi2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;

public class RegisterActivity extends AppCompatActivity {
    DB_manager dBase = factoryMethod.getManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onCancel(View view) {
        finish();
    }

    public void onSignUp(View view) {
        // check inputs - all attributes filled
        if (!isValidated()) return;

        Toast toast = Toast.makeText(getApplicationContext(), "Sending..", Toast.LENGTH_SHORT);
        toast.show();

        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String LastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        int id = Integer.parseInt(((EditText) findViewById(R.id.id)).getText().toString());
        String phoneNumber = ((EditText) findViewById(R.id.phoneNumber)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String creditNumber = ((EditText) findViewById(R.id.creditNumber)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        Driver driver = new Driver(firstName, LastName, id, phoneNumber, email, creditNumber, password);

        dBase.addDriver(driver); // sending to DB

        Toast toast1 = Toast.makeText(getApplicationContext(), "Sending..", Toast.LENGTH_SHORT);
        toast1.show();

       // Intent intent = new Intent(RegisterActivity.this, MainActivity.class);  // go to custom activity (Main activity)
        //intent.putExtra("EXTRA_ID_DRIVER", driver.getId());
      //  startActivity(intent);

    }


    private boolean isValidated() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this).setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        if (((EditText) findViewById(R.id.firstName)).getText().toString().length() == 0) {
            alert.setMessage("first name is missing!");
            alert.show();
            return false;
        }

        if (((EditText) findViewById(R.id.lastName)).getText().toString().length() == 0) {
            alert.setMessage("last name is missing!");
            alert.show();
            return false;
        }

        if (((EditText) findViewById(R.id.phoneNumber)).getText().toString().length() == 0) {
            alert.setMessage("phone number is missing!");
            alert.show();
            return false;
        }

        if (((EditText) findViewById(R.id.email)).getText().toString().length() == 0) {
            alert.setMessage("Email address is missing!");
            alert.show();
            return false;
        }

        if (((EditText) findViewById(R.id.id)).getText().toString().length() == 0) {
            alert.setMessage("id is missing!");
            alert.show();
            return false;
        }

        if (((EditText) findViewById(R.id.creditNumber)).getText().toString().length() == 0) {
            alert.setMessage("credit number is missing!");
            alert.show();
            return false;
        }

        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String verifyPassword = ((EditText) findViewById(R.id.verify_password)).getText().toString();
        if (password.length() == 0) {
            alert.setMessage("password is missing!");
            alert.show();
            return false;
        }

        if (verifyPassword.length() == 0) {
            alert.setMessage("verify password is missing!");
            alert.show();
            return false;
        }

        if (!password.equals(verifyPassword)) {
            alert.setMessage("passwords are not match!");
            alert.show();
            return false;
        }


        return true;
    }
}