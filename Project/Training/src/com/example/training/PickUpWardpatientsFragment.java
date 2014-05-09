package com.example.training;

import java.util.*;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class PickUpWardpatientsFragment extends Fragment{
	
	private ListView lv;
	Button button1;
	MainActivity mainact = new MainActivity();
	
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_pickupwardpatients, container, false);
			
	         lv = (ListView) rootView.findViewById(R.id.lvPatients);
	         button1 = (Button)rootView.findViewById(R.id.btGoOn);
	         
	         
	         String[] matrix = mainact.getPatientList();
	         for(int i = 0; i < matrix.length; i++){
	        	 matrix[i].replaceFirst("#", ".: ");
	        	 matrix[i].replaceFirst("#", " ");
	        	 //matrix[i].substring(0, matrix[i].indexOf("#"));
	         }

	         ListAdapter myarrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, matrix);
	         
	         lv.setAdapter(myarrayAdapter);
	         
	 		button1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					PickUpPatientFragment PickUpPatientFragment = new PickUpPatientFragment();
					FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.content_wrapper, PickUpPatientFragment).commit();
				}	
			});
			
			return rootView;
		}
}