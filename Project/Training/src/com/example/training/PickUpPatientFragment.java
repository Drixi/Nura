package com.example.training;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class PickUpPatientFragment extends Fragment {
		
		TextView tvScanCup_PickUpPatient, tvID_PickUpPatient, tvPatient_PickUpPatient, tvCupID_PickUpPatient;
		Button btNext_PickUpPatient;
		EditText ETCupID_PickUpPatient;
		int testing = 5;
		int testResponse = Integer.valueOf(ETCupID_PickUpPatient.getText().toString());
		
		
		@Override
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_pickuppatient, container, false);
			tvScanCup_PickUpPatient = (TextView)rootView.findViewById(R.id.tvScanCup_PickUpPatient);
			tvID_PickUpPatient = (TextView)rootView.findViewById(R.id.tvID_PickUpPatient);
			tvPatient_PickUpPatient = (TextView)rootView.findViewById(R.id.tvPatient_PickUpPatient);
			tvCupID_PickUpPatient = (TextView)rootView.findViewById(R.id.tvCupID_PickUpPatient);
			btNext_PickUpPatient = (Button)rootView.findViewById(R.id.btNext_PickUpPatient);
			ETCupID_PickUpPatient = (EditText)rootView.findViewById(R.id.ETCupID_PickUpPatient);
			if(testResponse==testing){
				btNext_PickUpPatient.setBackgroundColor(TRIM_MEMORY_RUNNING_CRITICAL);
			}
			return rootView;
		}
}
