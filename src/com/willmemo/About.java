package com.willmemo;

import com.gfan.sdk.statitistics.GFAgent;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class About extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setTheme(R.style.edit_dialog);
		setContentView(R.layout.about);	
		TextView versionText = (TextView)findViewById(R.id.about_version);
		versionText.setText(getAppVersionName(this));
	}
	public static String getAppVersionName(Context context) {  
	    String versionName = "";  
	    try {  
	        // Get the package info  
	        PackageManager pm = context.getPackageManager();  
	        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);  
	        versionName = pi.versionName;  
	        if (TextUtils.isEmpty(versionName)) {  
	            return "";  
	        }  
	    } catch (Exception e) {  
//	        Log.e(THIS_FILE, "Exception", e);  
	    }  
	    return versionName;  
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
