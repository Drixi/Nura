package com.example.training;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuFragment extends Fragment {

    Button btDeliver, btPatientList, btPickup;
    private DeliverFragmentOld buttonDeliver;
    private PickUpWardFragment buttonPickUp;
    private PickUpPatientFragment buttonPatienList;
    HeaderFragment HeaderFragment = new HeaderFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        btDeliver = (Button)rootView.findViewById(R.id.btDeliver);
        btPatientList = (Button)rootView.findViewById(R.id.btPatientList);
        btPickup = (Button)rootView.findViewById(R.id.btPickup);

        // Administer Button
        btDeliver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonDeliver = new DeliverFragmentOld();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_wrapper, buttonDeliver).commit();
            }
        });
        // My Patients Button
        btPatientList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonPatienList = new PickUpPatientFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_wrapper, buttonPatienList).commit();
            }
        });
        // Dispense Button
        btPickup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonPickUp = new PickUpWardFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_wrapper, buttonPickUp).commit();
            }
        });
        return rootView;
    }


}
