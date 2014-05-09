package com.example.training;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CheckUpCanisterFragment extends Fragment {
    Button CanBack;
    private CheckUpFragment CheckUpFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_checkupscancanister, container, false);

        CanBack = (Button) rootView.findViewById(R.id.btCanisterBack);

        // Back Button
        CanBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckUpFragment = new CheckUpFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_wrapper, CheckUpFragment).commit();
            }
        });

        return rootView;


    }
}
