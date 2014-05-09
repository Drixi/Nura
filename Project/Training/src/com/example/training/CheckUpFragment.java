package com.example.training;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CheckUpFragment extends Fragment {

    Button btCheckMed, btCheckCan, btCheckPat;
    private CheckUpMedicineFragment CheckMedicineFragment;
    private CheckUpCanisterFragment CheckCanisterFragment;
    private CheckUpPatientFragment CheckPatientFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_checkup, container, false);

        btCheckMed = (Button) rootView.findViewById(R.id.btcheckup_medicine);
        btCheckCan = (Button) rootView.findViewById(R.id.btcheckup_canister);
        btCheckPat = (Button) rootView.findViewById(R.id.btcheckup_patient);

        // Med Button
        btCheckMed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckMedicineFragment = new CheckUpMedicineFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_wrapper, CheckMedicineFragment).commit();
            }
        });

        // Can Button
        btCheckCan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckCanisterFragment = new CheckUpCanisterFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_wrapper, CheckCanisterFragment).commit();
            }
        });

        // Pat Button
        btCheckPat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckPatientFragment = new CheckUpPatientFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_wrapper, CheckPatientFragment).commit();
            }
        });
        return rootView;

    }
}