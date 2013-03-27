package com.willmemo;

import java.util.Calendar;

import com.gfan.sdk.statitistics.GFAgent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.TimePicker;

public class Setting extends PreferenceActivity{

//	static final int DATE_DIALOG_ID = 0;  

	private int mHour;
	private int mMinute;
	
	private Preference TimePreference;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting);		
		CheckBoxPreference myCheckBoxPreference = (CheckBoxPreference)findPreference("setting_switch");
		setNotiyPreferenceIsEnabled(myCheckBoxPreference.isChecked());
		Preference preference = (Preference)findPreference("setting_time");
//		TimePreference.setDefaultValue("1111111111111111");
//		preference.get
//		preference.get
		SharedPreferences mySP = getSharedPreferences(memoconstant.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
		mHour = mySP.getInt(memoconstant.SHARED_PREFERENCES_TIME_SET_HOUR, 8);
		mMinute = mySP.getInt(memoconstant.SHARED_PREFERENCES_TIME_SET_MINUTE, 0);
		preference.setSummary(TimeFormat(mHour,mMinute));
		

//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, mHour);
//		calendar.set(Calendar.MINUTE, mMinute);
//		calendar.set(Calendar.SECOND, 0);
//		calendar.set(Calendar.MILLISECOND, 0);
		
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		// TODO Auto-generated method stub
		
		if(preference.hasKey()&&preference.getKey().equals("setting_switch"))
		{
			CheckBoxPreference myCheckBoxPreference = (CheckBoxPreference) preference;
			PendingIntent TimePendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(memoconstant.SERVICE_ACTION_TIMEUP), 1);  
			AlarmManager TimeAm = (AlarmManager)getSystemService(ALARM_SERVICE);
			if(myCheckBoxPreference.isChecked())
			{
//				PendingIntent TimePendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(memoconstant.SERVICE_ACTION_TIMEUP), 1);  
//				AlarmManager TimeAm = (AlarmManager)getSystemService(ALARM_SERVICE);
//				am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,System.currentTimeMillis()+5000 , 5*1000, pendingIntent);
//				long firstTime = SystemClock.elapsedRealtime();  

				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis()); 
//				calendar.setTimeInMillis(System.currentTimeMillis());  
				calendar.set(Calendar.HOUR_OF_DAY, mHour);
				calendar.set(Calendar.MINUTE, mMinute);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				if(calendar.getTimeInMillis()<System.currentTimeMillis())
				{
					calendar.setTimeInMillis(calendar.getTimeInMillis()+(24 * 60 * 60 * 1000));
				}
				TimeAm.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),(24 * 60 * 60 * 1000), TimePendingIntent);	
				setNotiyPreferenceIsEnabled(myCheckBoxPreference.isChecked());
			}
			else
			{
//				PendingIntent TimePendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(memoconstant.SERVICE_ACTION_TIMEUP), 1);  
//				AlarmManager TimeAm = (AlarmManager)getSystemService(ALARM_SERVICE);
				TimeAm.cancel(TimePendingIntent);
				setNotiyPreferenceIsEnabled(myCheckBoxPreference.isChecked());
			}
		}
		else if(preference.hasKey()&&preference.getKey().equals("setting_time"))
		{
			TimePreference = preference;
			TimePickerDialog myTimePickerDialog = new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,false);
			myTimePickerDialog.setOnDismissListener(new OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					// TODO Auto-generated method stub
					TimePreference.setSummary(TimeFormat(mHour,mMinute));
//					PageCount = getPreferences(MODE_PRIVATE).getInt(memoconstant.TIME_SET, 0);
					SharedPreferences mySP = getSharedPreferences(memoconstant.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
					SharedPreferences.Editor editor = mySP.edit();
					editor.putInt(memoconstant.SHARED_PREFERENCES_TIME_SET_HOUR,mHour);
					editor.putInt(memoconstant.SHARED_PREFERENCES_TIME_SET_MINUTE,mMinute);
					editor.commit();
//					Map<String,Integer> map = new HashMap<String,Integer>();
//					map.put("Hour", mHour);
//					map.put("Minute", mMinute);
				}
			});
			myTimePickerDialog.show();
		}
		else if(preference.hasKey()&&preference.getKey().equals("setting_overtime"))
		{
			CheckBoxPreference myCheckBoxPreference = (CheckBoxPreference) preference;
			SharedPreferences mySP = getSharedPreferences(memoconstant.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
			SharedPreferences.Editor editor = mySP.edit();
			editor.putBoolean(memoconstant.SHARED_PREFERENCES_TIME_UP_NOTI,myCheckBoxPreference.isChecked());
			editor.commit();
		}
		else if(preference.hasKey()&&preference.getKey().equals("setting_readme"))
		{
			Intent intent = new Intent();
			intent.setClass(this,Help.class);
			startActivity(intent);
		}
		else if(preference.hasKey()&&preference.getKey().equals("setting_about"))
		{
			Intent intent = new Intent();
			intent.setClass(this,About.class);
			startActivity(intent);
		}
		else if(preference.hasKey()&&preference.getKey().equals("setting_report"))
		{
//			Intent intent = new Intent();
//			intent.setClass(this,SetReport.class);
//			startActivity(intent);
			GFAgent.onStartFeedbackActivity(this);
		}

		
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
	private void setNotiyPreferenceIsEnabled(boolean enabled)
	{
		Preference myPreference = (Preference)findPreference("setting_time");
		myPreference.setEnabled(enabled);
		myPreference = (Preference)findPreference("setting_overtime");
		myPreference.setEnabled(enabled);
	}
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {  

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
            mHour = hourOfDay;  
            mMinute = minute;
            
			PendingIntent TimePendingIntent = PendingIntent.getBroadcast(Setting.this, 0, new Intent(memoconstant.SERVICE_ACTION_TIMEUP), 1);  
			AlarmManager TimeAm = (AlarmManager)getSystemService(ALARM_SERVICE);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis()); 
			calendar.set(Calendar.HOUR_OF_DAY, mHour);
			calendar.set(Calendar.MINUTE, mMinute);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			if(calendar.getTimeInMillis()<System.currentTimeMillis())
			{
				calendar.setTimeInMillis(calendar.getTimeInMillis()+(24 * 60 * 60 * 1000));
			}
			TimeAm.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),(24 * 60 * 60 * 1000), TimePendingIntent);
		}  
    };
    
    private String TimeFormat(int HOUR_OF_DAY,int MINUTE)
    {
    	String ret = "  ";
//    	if(HOUR_OF_DAY==0)
//    	{
//    		HOUR_OF_DAY=12;
//    	}
//    	System.out.println("HOUR_OF_DAY:"+HOUR_OF_DAY);
    	if(HOUR_OF_DAY>11)
    	{
    		if(HOUR_OF_DAY!=12)
    		{
    			HOUR_OF_DAY = HOUR_OF_DAY - 12;
    		}
    		ret = getString(R.string.time_pm) + ret + (HOUR_OF_DAY) + ":" + format(MINUTE);
    	}
    	else
    	{
    		if(HOUR_OF_DAY==0)
    		{
    			HOUR_OF_DAY = HOUR_OF_DAY + 12;
    		}
    		ret = getString(R.string.time_am) + ret + (HOUR_OF_DAY) + ":" + format(MINUTE);
    	}
    	
    	return ret;
    }
    private String format(int x) {  
        String s = "" + x;  
        if (s.length() == 1)  
            s = "0" + s;  
        return s;  
    }  
    
}
