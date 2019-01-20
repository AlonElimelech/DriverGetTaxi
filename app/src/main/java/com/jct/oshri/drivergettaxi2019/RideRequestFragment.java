package com.jct.oshri.drivergettaxi2019;


import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;


/**
 * A simple {@link Fragment} subclass.
 */
public class RideRequestFragment extends Fragment {

    View v;
    TextView name;
    TextView email;
    TextView phone;
    String SendSMSDEST;
    Button SendingSms;

    public RideRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_ride_request, container, false);

        findViews();

        return v;
    }

    private void findViews() {

        Bundle bundle = this.getArguments();
        Ride r = (Ride) bundle.getSerializable("Ride");
        name = v.findViewById(R.id.fullName_customer);
        name.setText(r.nameOfClient);

        email = v.findViewById(R.id.email_customer);
        email.setText(r.emailOfClient);
        phone = v.findViewById(R.id.phoneNumber_customer);
        phone.setText(r.phoneNumberOfClient);

        SendingSms = v.findViewById(R.id.TakeItButton);
        SendSMSDEST = bundle.getString("PhoneNumber").toString();


        SendingSms.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //SmsManager smsManager = SmsManager.getDefault();
                // smsManager.sendTextMessage(SendSMSDEST, null, "Hello,Im your driver and I ready to give a service to you. I LOVE YOU!", null, null);
                Toast.makeText(getContext(), " Sent!",
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


}
