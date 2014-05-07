package com.example.training;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;

public class MainActivity extends FragmentActivity {
	private HeaderFragment headerFragment;
	private MenuFragment contentFragment;

	public static final String MIME_TEXT_PLAIN = "text/plain";
  	public static final String TAG = "NfcDemo";
  	private NfcAdapter mNfcAdapter;
  	public static String ReadResult;
  	public static String hejmeddig = "Hej med dig";
  	static String[] seperated;
  	static String[][] matrix;
  	
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_layout);
		
        if (savedInstanceState == null) {
            headerFragment = new HeaderFragment();
            contentFragment = new MenuFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.header_wrapper, headerFragment)
            	.add(R.id.content_wrapper, contentFragment).commit();
            
        }
        
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		
		handleIntent(getIntent());
	}
	
		  private void handleIntent(Intent intent) {
		  String action = intent.getAction();
		  if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
		       
		      String type = intent.getType();
		      if (MIME_TEXT_PLAIN.equals(type)) {
		
		          Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		          new NdefReaderTask().execute(tag);
		           
		      } else {
		          Log.d(TAG, "Wrong mime type: " + type);
		      }
		  } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
		      
		      // In case we would still use the Tech Discovered Intent
		      Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		      String[] techList = tag.getTechList();
		      String searchedTech = Ndef.class.getName();
		       
		      for (String tech : techList) {
		          if (searchedTech.equals(tech)) {
		              new NdefReaderTask().execute(tag);
		              break;
		          }
		      }
		  
		
		  }
		}
		public class NdefReaderTask extends AsyncTask<Tag, Void, String> {
			 
		  @Override
		  protected String doInBackground(Tag... params) {
		      Tag tag = params[0];
		       
		      Ndef ndef = Ndef.get(tag);
		      if (ndef == null) {
		          // NDEF is not supported by this Tag. 
		          return null;
		      }
		
		      NdefMessage ndefMessage = ndef.getCachedNdefMessage();
		
		      NdefRecord[] records = ndefMessage.getRecords();
		      for (NdefRecord ndefRecord : records) {
		          if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
		              try {
		                  return readText(ndefRecord);
		              } catch (UnsupportedEncodingException e) {
		                  Log.e(TAG, "Unsupported Encoding", e);
		              }
		          }
		      }
		
		      return null;
		  }
		   
		  private String readText(NdefRecord record) throws UnsupportedEncodingException {
		      
		
		      byte[] payload = record.getPayload();
		
		      // Get the Text Encoding
		      String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
		
		      // Get the Language Code
		      int languageCodeLength = payload[0] & 0063;
		       
		      // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
		      // e.g. "en"
		       
		      // Get the Text
		      return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
		  }
   
		  @Override
		  protected void onPostExecute(String result) {
		      if (result != null) {
		    	  ReadResult = result;
		      }
		  }
		}

		public static String NFCRead(){
			return ReadResult;
		}
		
		public static String[] getPatientList(){
			PickUpWardFragment pickup = new PickUpWardFragment();
			seperated = pickup.patientdb();
			return seperated;
		}
		
	
}
