package com.willmemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.gfan.sdk.statitistics.GFAgent;

public class Help extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		SharedPreferences mySP = getSharedPreferences(memoconstant.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
		SharedPreferences.Editor editor = mySP.edit();
		editor.putBoolean(memoconstant.SHARED_PREFERENCES_START_GUIDE,false);
		editor.commit();
		setContentView(R.layout.scroll);
		ImageButton BtnStart = (ImageButton)findViewById(R.id.btn_start);
		
		BtnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		GFAgent.onResume(this);
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		GFAgent.onPause(this);
	}

}
