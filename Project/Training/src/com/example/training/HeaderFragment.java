package com.example.training;

import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HeaderFragment extends Fragment {
	
	LoginActivity LoginActivity = new LoginActivity();
	String[] separated;
	static String titleInfo;
	TextView user_name, user_id, date;
	Button logout, check_up, gotomain;
	ImageView profile_img;
	private CheckUpFragment buttonCheckUp;
	private MenuFragment buttonMenu;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_header_layout, container, false);
		separated = LoginActivity.GetValue();
		
        user_name = (TextView)rootView.findViewById(R.id.user_name);
        user_id = (TextView)rootView.findViewById(R.id.user_id);
        gotomain = (Button)rootView.findViewById(R.id.gotomain);
        date = (TextView)rootView.findViewById(R.id.date);
        logout = (Button)rootView.findViewById(R.id.logout);
        check_up = (Button)rootView.findViewById(R.id.check_up);
        profile_img = (ImageView)rootView.findViewById(R.id.profile_img);
        
//        title.setText(titleName);
        user_name.setText("Sygeplejer : " + separated[3]);
        user_id.setText("ID : " + separated[1]);
        // Calendar/date 
        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        date.setText(new StringBuilder()
        // Month is 0 based, therefore we add 1
                .append(dd).append(" ").append("- ").append(mm + 1).append(" - ")
                .append(yy));
        
        // Log Out Button
		        logout.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						DownloadImage DownloadImage = new DownloadImage();
						DownloadImage.rundownload();
						
					}
				});
		// Check Up Button
		        check_up.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						buttonCheckUp = new CheckUpFragment();
						FragmentTransaction ft = getFragmentManager().beginTransaction();
						ft.replace(R.id.content_wrapper, buttonCheckUp).commit();
					}
				});
		// Go To Main Button
		        gotomain.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						buttonMenu = new MenuFragment();
						FragmentTransaction ft = getFragmentManager().beginTransaction();
						ft.add(R.id.content_wrapper, buttonMenu).commit();
					}
				});
        
		return rootView;
	}
	
//	public static String changeTitle(){
//		return titleInfo;
//	}
	
}
