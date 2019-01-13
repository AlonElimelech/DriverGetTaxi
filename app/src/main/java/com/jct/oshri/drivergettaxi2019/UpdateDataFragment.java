package com.jct.oshri.drivergettaxi2019;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateDataFragment extends Fragment {


    public UpdateDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_update_data, container, false);
        Button button = (Button) view.findViewById(R.id.button_update);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onUpgrade(v);
            }
        });
        return view;
    }


    public void onUpgrade(View view) {
        // check inputs - all attributes filled
        if (!isValidated()) return;

        Toast toast = Toast.makeText(getActivity(), "Sending..", Toast.LENGTH_SHORT);
        toast.show();


        String phoneNumber = ((EditText)getView().findViewById(R.id.phoneNumber)).getText().toString();
        String email = ((EditText) getView().findViewById(R.id.email)).getText().toString();
        String creditNumber = ((EditText) getView().findViewById(R.id.creditNumber)).getText().toString();
        String password = ((EditText) getView().findViewById(R.id.password)).getText().toString();

        //Driver driver = new Driver(firstName, LastName, id, phoneNumber, email, creditNumber, password);

        //DB_manager dBase = factoryMethod.getManager();
        //dBase.addDriver(driver); // sending to DB

        Toast toast1 = Toast.makeText(getActivity(), "Sending..", Toast.LENGTH_SHORT);
        toast1.show();


    }
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
