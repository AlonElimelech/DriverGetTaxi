package com.jct.oshri.drivergettaxi2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.datasource.FireBase_DBManager;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateDataFragment extends Fragment {

    Driver driver;
    String phoneNumber;
    String email;
    String creditNumber;
    String password;
    AlphaAnimation a = new AlphaAnimation(1F, 0.8F);

    public UpdateDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_data, container, false);
        Button buttonUpdate = (Button) view.findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdate(v);
            }
        });

        Button buttonCancel = (Button) view.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancel(v);
            }
        });

        driver = (Driver) (getArguments().getSerializable("Driver"));
        EditText phoneNumber = view.findViewById(R.id.phoneNumber);
        EditText email = view.findViewById(R.id.email);
        EditText creditNumber = view.findViewById(R.id.creditNumber);
        EditText password = view.findViewById(R.id.password);

        phoneNumber.setText(driver.phoneNumber);
        email.setText(driver.email);
        creditNumber.setText(driver.creditNumber);
        password.setText(driver.password);

        return view;
    }

    private void onCancel(View view) {
        view.startAnimation(a);
        Intent intent = new Intent(getActivity(), MainActivity.class);  // go to custom activity (Main activity)
        intent.putExtra("com.jct.oshri.drivergettaxi2019.model.entities.Driver", driver);
        startActivity(intent);
    }

    public void onUpdate(View view) {
        view.startAnimation(a);
        if (!isValidated()) return;

        Toast toast = Toast.makeText(getActivity(), "Sending..", Toast.LENGTH_SHORT);
        toast.show();

        phoneNumber = ((EditText) getView().findViewById(R.id.phoneNumber)).getText().toString();
        email = ((EditText) getView().findViewById(R.id.email)).getText().toString();
        creditNumber = ((EditText) getView().findViewById(R.id.creditNumber)).getText().toString();
        password = ((EditText) getView().findViewById(R.id.password)).getText().toString();

        Driver newDriver = new Driver(driver.firstName, driver.lastName, driver.id, phoneNumber, email, creditNumber, password);
        DB_manager dBase = factoryMethod.getManager();
        ((FireBase_DBManager) dBase).updateDriver(newDriver); // sending to DB

        Toast toast1 = Toast.makeText(getActivity(), "Updating..", Toast.LENGTH_SHORT);
        toast1.show();
    }

    /**
     * check inputs - return True if all attributes are filled
     */
    private boolean isValidated() {

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity()).setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        if (((EditText) getView().findViewById(R.id.phoneNumber)).getText().toString().length() == 0) {
            alert.setMessage("phone number is missing!");
            alert.show();
            return false;
        }

        if (((EditText) getView().findViewById(R.id.email)).getText().toString().length() == 0) {
            alert.setMessage("Email address is missing!");
            alert.show();
            return false;
        }

        if (((EditText) getView().findViewById(R.id.creditNumber)).getText().toString().length() == 0) {
            alert.setMessage("credit number is missing!");
            alert.show();
            return false;
        }

        String password = ((EditText) getView().findViewById(R.id.password)).getText().toString();
        String verifyPassword = ((EditText) getView().findViewById(R.id.verify_password)).getText().toString();
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
