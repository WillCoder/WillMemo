package com.willmemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PushService extends Service{

	public final static int FLAGS_START = 0;
	public final static int FLAGS_UPDATE = 1;
	public final static int FLAGS_TIMEUP = 2;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
	   	Intent service = new Intent(Intent.ACTION_RUN);
	   	service.putExtra("flags",PushService.FLAGS_START);
	   	service.setClass(this, PushService.class); 
	   	startService(service);
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
//		Log.e("WillMemo", "Appwidget---------------------update!");
		super.onStartCommand(intent, flags, startId);
		int flag = intent.getIntExtra("flags",flags);
		switch(flag)
		{
			case FLAGS_START:
				start();
				break;
			case FLAGS_UPDATE:
				update();
				break;
			case FLAGS_TIMEUP:
				timeUp();
				break;
			default:
				break;
		}
		return 0;
	}
	public void start()
	{
		Log.d(this.toString(), "PushService_start");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(memoconstant.SERVICE_ACTION_UPDATE), 1);  
		AlarmManager UpdateAm = (AlarmManager)getSystemService(ALARM_SERVICE);
		UpdateAm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,0,60*1000, pendingIntent);
		
/**
		int mHour = this.getPreferences(MODE_PRIVATE).getInt(memoconstant.TIME_SET_HOUR, 8);
		int mMinute = getPreferences(MODE_PRIVATE).getInt(memoconstant.TIME_SET_MINUTE, 0);
        PendingIntent TimePendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(memoconstant.SERVICE_ACTION_TIMEUP), 1);  
		AlarmManager TimeAm = (AlarmManager)getSystemService(ALARM_SERVICE);
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,System.currentTimeMillis()+5000 , 5*1000, pendingIntent);
		TimeAm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,0,5*1000, TimePendingIntent);
 */
	}
	public void update()
	{
		Log.d(this.toString(), "PushService_update");
 		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
		int []appWidgetIds =appWidgetManager.getAppWidgetIds(new ComponentName(this, myAppWidget.class));
		myAppWidget.updateAppWidget(this, appWidgetManager, appWidgetIds);
	}
	public void timeUp()
	{
		Log.d(this.toString(), "PushService_timeUp");
		ShowNotification sn = new ShowNotification(this);
		sn.showNotification();
	}
}
