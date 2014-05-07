//package com.example.training;
//
//import java.io.UnsupportedEncodingException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Arrays;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.nfc.NdefMessage;
//import android.nfc.NdefRecord;
//import android.nfc.NfcAdapter;
//import android.nfc.Tag;
//import android.nfc.tech.Ndef;
//import android.util.Log;
//import android.view.Menu;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class NFCDumpClass {
//
//    public static final String MIME_TEXT_PLAIN = "text/plain";
//    public static final String TAG = "NfcDemo";
//	
//	private Button loginBT;
//	private EditText etUserName, etPassword;
//	private NfcAdapter mNfcAdapter;
//
//        
//        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        
//        if (mNfcAdapter == null) {
//            // Stop here, we definitely need NFC
//            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
//            //finish();
//            return;
// 
//        }
//     
//        if (!mNfcAdapter.isEnabled()) {
//            etUserName.setText("NFC is disabled!");
//        } else {
//            etUserName.setText("Scan NFC tag");
//        }
//        handleIntent(getIntent());
//    }
//
//    
//    private void handleIntent(Intent intent) {
//        String action = intent.getAction();
//        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
//             
//            String type = intent.getType();
//            if (MIME_TEXT_PLAIN.equals(type)) {
//     
//                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//                new NdefReaderTask().execute(tag);
//                 
//            } else {
//                Log.d(TAG, "Wrong mime type: " + type);
//            }
//        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
//             
//            // In case we would still use the Tech Discovered Intent
//            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//            String[] techList = tag.getTechList();
//            String searchedTech = Ndef.class.getName();
//             
//            for (String tech : techList) {
//                if (searchedTech.equals(tech)) {
//                    new NdefReaderTask().execute(tag);
//                    break;
//                }
//            }
//        }
//    }
//    private class NdefReaderTask extends AsyncTask<Tag, Void, String> {
//    	 
//        @Override
//        protected String doInBackground(Tag... params) {
//            Tag tag = params[0];
//             
//            Ndef ndef = Ndef.get(tag);
//            if (ndef == null) {
//                // NDEF is not supported by this Tag. 
//                return null;
//            }
//     
//            NdefMessage ndefMessage = ndef.getCachedNdefMessage();
//     
//            NdefRecord[] records = ndefMessage.getRecords();
//            for (NdefRecord ndefRecord : records) {
//                if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
//                    try {
//                        return readText(ndefRecord);
//                    } catch (UnsupportedEncodingException e) {
//                        Log.e(TAG, "Unsupported Encoding", e);
//                    }
//                }
//            }
//     
//            return null;
//        }
//         
//        private String readText(NdefRecord record) throws UnsupportedEncodingException {
//            
//     
//            byte[] payload = record.getPayload();
//     
//            // Get the Text Encoding
//            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
//     
//            // Get the Language Code
//            int languageCodeLength = payload[0] & 0063;
//             
//            // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
//            // e.g. "en"
//             
//            // Get the Text
//            return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
//        }
//         
//        @Override
//        protected void onPostExecute(String result) {
//            if (result != null) {
//                etUserName.setText(result);
//            }
//        }
//    }
//    
//}
