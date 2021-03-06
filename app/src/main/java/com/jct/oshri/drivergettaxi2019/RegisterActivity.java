package com.jct.oshri.drivergettaxi2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;

public class RegisterActivity extends AppCompatActivity {

    AlphaAnimation a = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onCancel(View view) {
        view.startAnimation(a);
        finish();
    }

    public void onSignUp(View view) {

        view.startAnimation(a);
        if (!isValidated()) return;

        Toast toast = Toast.makeText(getApplicationContext(), "Sending..", Toast.LENGTH_SHORT);
        toast.show();

        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String LastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        String id = ((EditText) findViewById(R.id.id)).getText().toString();
        String phoneNumber = ((EditText) findViewById(R.id.phoneNumber)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String creditNumber = ((EditText) findViewById(R.id.creditNumber)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        Driver driver = new Driver(firstName, LastName, id, phoneNumber, email, creditNumber, password);
        DB_manager dBase = factoryMethod.getManager();
        dBase.addDriver(driver); // sending to DB

        Toast toast1 = Toast.makeText(getApplicationContext(), "Sending..", Toast.LENGTH_SHORT);
        toast1.show();

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);  // go to custom activity (Main activity)
        intent.putExtra("Driver", driver);
        startActivity(intent);
    }

    /**
     * @return True if all attributes are filled
     */
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