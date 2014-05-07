//package com.example.training;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Arrays;
//
//import com.example.training.MainActivity.NdefReaderTask;
//
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.content.Intent;
//import android.nfc.NdefMessage;
//import android.nfc.NdefRecord;
//import android.nfc.NfcAdapter;
//import android.nfc.Tag;
//import android.nfc.tech.Ndef;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class CopyOfDeliverFragment extends Fragment{
//
//	TextView tvScanPatient, tvPatientID, tvScanCanister, tvCanisterID, tvDelivered;
//	Button btNextPatient, btBackToMenu, testbutton;
//	MainActivity MainActivity = new MainActivity();
//	
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_deliver, container, false);
//			
//			tvScanPatient = (TextView)rootView.findViewById(R.id.tvScanPatient);
//			tvPatientID = (TextView)rootView.findViewById(R.id.tvPatientID);
//			tvScanCanister = (TextView)rootView.findViewById(R.id.tvScanCanister);
//			tvCanisterID = (TextView)rootView.findViewById(R.id.tvCanisterID);
//			tvDelivered = (TextView)rootView.findViewById(R.id.tvDelivered);
//			btNextPatient = (Button)rootView.findViewById(R.id.btNextPatient);
//			btBackToMenu = (Button)rootView.findViewById(R.id.btBackToMenu);
//			testbutton = (Button)rootView.findViewById(R.id.btSubmitPatient);
//			
//			tvPatientID.setVisibility(View.INVISIBLE);
//			//tvPatientID.setText(MainActivity.ReadResult);
//			tvScanCanister.setVisibility(View.INVISIBLE);
//			tvCanisterID.setVisibility(View.INVISIBLE);
//			tvDelivered.setVisibility(View.INVISIBLE);
//			//btNextPatient.setVisibility(View.INVISIBLE);
//			
//	        testbutton.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					tvScanPatient.setText(MainActivity.NFCRead());
//				}
//			});
//
//			
//			
//			if(MainActivity.ReadResult != null){
//				tvPatientID.setVisibility(View.VISIBLE);
//			}
//			
//			return rootView;
//		}
//
//}
