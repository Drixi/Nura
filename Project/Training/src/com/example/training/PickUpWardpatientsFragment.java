package com.example.training;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import android.app.Fragment;
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
	MainActivity mainact = new MainActivity();
	
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_pickupwardpatients, container, false);
			
	         lv = (ListView) rootView.findViewById(R.id.lvPatients);
	         
	         
	         
	         String[] matrix = mainact.getPatientList();
	         for(int i = 0; i < matrix.length; i++){
	        	 matrix[i].replaceFirst("#", ".: ");
	        	 matrix[i].replaceFirst("#", " ");
	        	 //matrix[i].substring(0, matrix[i].indexOf("#"));
	         }

	         ListAdapter myarrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, matrix);
	         
	         lv.setAdapter(myarrayAdapter);
			
			return rootView;
		}
}