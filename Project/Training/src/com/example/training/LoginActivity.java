package com.example.training;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class LoginActivity extends Activity {
	
	Button loginBT;
	EditText etUserID;
	EditText etPIN;
    HttpPost httppost;
    StringBuffer buffer;
    String response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    static String[] separated;
    ArrayList<String> list = new ArrayList<String>();
    
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        loginBT = (Button)findViewById(R.id.btLogin);
        etUserID = (EditText)findViewById(R.id.etUserName);
        etPIN = (EditText)findViewById(R.id.etPassword);
        
        loginBT.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
	               dialog = ProgressDialog.show(LoginActivity.this, "", 
	                        "Validating user...", true);
	                 new Thread(new Runnable() {
	                        public void run() {
	                            logindb();
	                        }
	                 }).start();
	                 new Handler().postDelayed(new Runnable() {
	                     @Override
	                     public void run() {
	                    	 dialog.dismiss();
	                         String pinString = etPIN.getText().toString();
	                         int pinInteger = Integer.valueOf(pinString);
	                         int responseInteger = Integer.valueOf(separated[2]);
	                         if(responseInteger == pinInteger){
	                             runOnUiThread(new Runnable() {
	                                 public void run() {
	                                     Toast.makeText(LoginActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
	                                 }
	                             });
	                             startActivity(new Intent(LoginActivity.this, MainActivity.class));
	                             finish();   
	                         }
	                         else{
	                             showAlert();                
	                         }
	                     }
	                 }, 2500);
			}
		});    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    public void logindb(){
                 try{
                     
                     httpclient=new DefaultHttpClient();
                     httppost= new HttpPost("http://188.226.221.153/readfromdblogin.php");
                     nameValuePairs = new ArrayList<NameValuePair>(1);
                     nameValuePairs.add(new BasicNameValuePair("id",etUserID.getText().toString().trim()));
                     //nameValuePairs.add(new BasicNameValuePair("pin",etPIN.getText().toString().trim()));
                     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                     //response=httpclient.execute(httppost);
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
    
    public void showAlert(){
        LoginActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Login Error.");
                builder.setMessage("Wrong ID or PIN.")  
                       .setCancelable(false)
                       .setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                           }
                       });                     
                AlertDialog alert = builder.create();
                alert.show();               
            }
        });
    }
    
    public static String[] GetValue(){
    	return separated;
    }
}
