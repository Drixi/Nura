package com.example.training;

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
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PickUpWardFragment extends Fragment{

    static HttpPost httppost;
    StringBuffer buffer;
    static String response;
    static HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    static String[] separated;
    static String[][] matrix;
    ArrayList<String> list = new ArrayList<String>();
    Button btGastro;
    private PickUpWardpatientsFragment buttonWard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pickupward, container, false);
        btGastro = (Button)rootView.findViewById(R.id.btgastro);

        btGastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        patientdb();
                    }
                }).start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity MainActivity = new MainActivity();
                        MainActivity.matrix = matrix;
                        //btGastro.setText(matrix[1][1]);
                        buttonWard = new PickUpWardpatientsFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_wrapper, buttonWard).commit();
                    }
                }, 1500);
            }
        });

        return rootView;
    }

    public static String[] patientdb(){
        try{

            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://188.226.221.153/readfromdbpatientlist.php");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);

            separated = response.split("@");
            matrix = new String[response.length()][];
            for(int i=0; i<separated.length; i++){
                matrix[i] = separated[i].split("#");
            }

            return separated;

        }catch(Exception e){
            return separated;
        }

    }
}
