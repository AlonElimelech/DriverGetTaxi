package com.jct.oshri.drivergettaxi2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

public class TakeRideActivity extends AppCompatActivity {

    //View v;
    TextView name;
    TextView email;
    TextView phone;
    //String SendSMSDEST;
    Button takeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_ride);

        findViews();
    }





    private void findViews() {

        Intent myIntent = getIntent();
        Ride r = (Ride) myIntent.getSerializableExtra("Ride");
        name = findViewById(R.id.name_cus);
        name.setText(r.nameOfClient);

        email = findViewById(R.id.email_cus);
        email.setText(r.emailOfClient);
        phone = findViewById(R.id.phone_cus);
        phone.setText(r.phoneNumberOfClient);

        takeButton = findViewById(R.id.TakeItButton);
        //SendSMSDEST = bundle.getString("PhoneNumber").toString();


        takeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //SmsManager smsManager = SmsManager.getDefault();
                // smsManager.sendTextMessage(SendSMSDEST, null, "Hello,Im your driver and I ready to give a service to you. I LOVE YOU!", null, null);
                Toast.makeText(getApplicationContext(), " Sent!",
                        Toast.LENGTH_LONG).show();

                /*
                new AsyncTask<Void, Void, String>() {
                        @Override
                        protected void onPostExecute(String idResult) {


                            MyRidesFragment fragment = new MyRidesFragment();

                            FragmentManager fragmentManager = getActivity().getFragmentManager();
                            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



                            fragmentTransaction.replace(R.id.fram, fragment);
                            fragmentTransaction.commit();





                        }
                        @Override
                        protected String doInBackground(Void... params) {
                            //factoryMethod.getManager().UpdateTravel(keyToDelete,DriverId,r);

                            return "";

                        }
                    }.execute();
                    */


            }
        });


    }


    public void onClick(View view) {
    }
}
