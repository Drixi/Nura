package com.example.training;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class PickUpPatientFragment extends Fragment {
		
		TextView tvScanCup_PickUpPatient, tvID_PickUpPatient, tvPatient_PickUpPatient, tvCupID_PickUpPatient;
		Button btNext_PickUpPatient;
		EditText etCupID_PickUpPatient;
		MainActivity MainActivity = new MainActivity();
		String[] matrix = MainActivity.getPatientList();
        String[] matrixPatientSplit;
		ArrayList<String> list = new ArrayList<String>();
	    HttpPost httppost;
	    StringBuffer buffer;
	    String response;
	    HttpClient httpclient;
	    List<NameValuePair> nameValuePairs;
		
		@Override
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_pickuppatient, container, false);
			tvScanCup_PickUpPatient = (TextView)rootView.findViewById(R.id.tvScanCup_PickUpPatient);
			tvID_PickUpPatient = (TextView)rootView.findViewById(R.id.tvID_PickUpPatient);
			tvPatient_PickUpPatient = (TextView)rootView.findViewById(R.id.tvPatient_PickUpPatient);
			tvCupID_PickUpPatient = (TextView)rootView.findViewById(R.id.tvCupID_PickUpPatient);
			btNext_PickUpPatient = (Button)rootView.findViewById(R.id.btNext_PickUpPatient);
			etCupID_PickUpPatient = (EditText)rootView.findViewById(R.id.ETCupID_PickUpPatient);
			
			matrixPatientSplit = matrix[1].split("#");
	        for (int i = 0; i < matrixPatientSplit.length; ++i) {
	            list.add(matrixPatientSplit[i]);}
	        
	        tvPatient_PickUpPatient.setText("Patient Name : " + matrixPatientSplit[1]);
	        tvID_PickUpPatient.setText("Patient ID : " + matrixPatientSplit[2]);
	        
	        btNext_PickUpPatient.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
		                 new Thread(new Runnable() {
		                        public void run() {
		                            addCanister();
		                        }
		                 }).start();
		                 
		                 new Handler().postDelayed(new Runnable() {
		                     @Override
		                     public void run() {
		                    	 System.out.println("Successfull Canister Add");
		                     }
		                 }, 2000);
				}
			});
	        
			return rootView;
		}
		
		public void addCanister(){
	            try{
	                
	                httpclient=new DefaultHttpClient();
	                httppost= new HttpPost("http://188.226.221.153/readfromdbaddcanister.php");
	                nameValuePairs = new ArrayList<NameValuePair>(2);
	                nameValuePairs.add(new BasicNameValuePair("id", matrixPatientSplit[0]));
	                nameValuePairs.add(new BasicNameValuePair("canisterID",etCupID_PickUpPatient.getText().toString().trim()));
	                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                response = httpclient.execute(httppost, responseHandler);
	                System.out.println(response);
	            }catch(IOException e){
	           	Log.e("drixi", "FEJLET");

	            }
	       
		}
}
