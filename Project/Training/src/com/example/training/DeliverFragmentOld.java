package com.example.training;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.training.MainActivity.NdefReaderTask;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeliverFragmentOld extends Fragment{

    TextView tvScanPatient, tvPatientID, tvScanCanister, tvCanisterID, tvDelivered;
    Button btNextPatient, btBackToMenu, btGetPatient, btGetCanister;
    EditText etPatient, etCanister;
    MainActivity MainActivity = new MainActivity();
    HttpPost httppost;
    StringBuffer buffer;
    String response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    static String[] separated;
    static String separatedremove;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deliver, container, false);

        tvScanPatient = (TextView)rootView.findViewById(R.id.tvScanPatient);
        tvPatientID = (TextView)rootView.findViewById(R.id.tvPatientID);
        tvScanCanister = (TextView)rootView.findViewById(R.id.tvScanCanister);
        tvCanisterID = (TextView)rootView.findViewById(R.id.tvCanisterID);
        tvDelivered = (TextView)rootView.findViewById(R.id.tvDelivered);
        btNextPatient = (Button)rootView.findViewById(R.id.btNextPatient);
        btBackToMenu = (Button)rootView.findViewById(R.id.btBackToMenu);
        btGetPatient = (Button)rootView.findViewById(R.id.btGetPatient);
        btGetCanister = (Button)rootView.findViewById(R.id.btGetCanister);
        etPatient = (EditText)rootView.findViewById(R.id.etPatient);
        etCanister = (EditText)rootView.findViewById(R.id.etCanister);
        reloadAll();

        btGetPatient.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        getDatabaseInfo();
                    }
                }).start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int patientInt = Integer.valueOf(etPatient.getText().toString());
                        int separatedInt = Integer.valueOf(separated[3]);
                        if(patientInt == separatedInt){
                            tvPatientID.setVisibility(View.VISIBLE);
                            tvPatientID.setText("Patient ID : "+ separatedInt);
                            tvCanisterID.setVisibility(View.VISIBLE);
                            etCanister.setVisibility(View.VISIBLE);
                            btGetCanister.setVisibility(View.VISIBLE);
                        }
                        else{
                            //tvScanPatient.setText("Wrong user ID please scan again");
                        }
                    }
                }, 2000);
            }
        });

        btGetCanister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        removeCanister();
                    }
                }).start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(separatedremove != null){
                            tvDelivered.setVisibility(View.VISIBLE);
                            btNextPatient.setVisibility(View.VISIBLE);
                        }

                    }
                }, 2000);


            }
        });

        btNextPatient.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                reloadAll();
            }
        });

        return rootView;
    }


    public void getDatabaseInfo(){
        try{

            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://188.226.221.153/readfromdbdeliverpatient.php");
            nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("id",etPatient.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);

            Log.d("drixi", response);
            separated = response.split("#");
            for (int i = 0; i < separated.length; ++i) {
                list.add(separated[i]);}
        }catch(IOException e){
            Log.e("drixi", "FEJLET");

        }

    }

    public void removeCanister(){
        try{

            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://188.226.221.153/readfromdbremovecanister.php");
            nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("id",etCanister.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            System.out.println(response);
            separatedremove = response;
        }catch(IOException e){
            Log.e("drixi", "FEJLET");

        }

    }

    public void reloadAll(){
        tvPatientID.setVisibility(View.INVISIBLE);
        tvScanCanister.setVisibility(View.INVISIBLE);
        tvCanisterID.setVisibility(View.INVISIBLE);
        tvDelivered.setVisibility(View.INVISIBLE);
        btGetCanister.setVisibility(View.INVISIBLE);
        etCanister.setVisibility(View.INVISIBLE);
        btNextPatient.setVisibility(View.INVISIBLE);
        btBackToMenu.setVisibility(View.INVISIBLE);
        tvScanPatient.setText("Scan Patient");
        etPatient.setText("");
        etCanister.setText("");
    }

}
