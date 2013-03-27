package com.willmemo;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class myServices extends Service{

	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Intent service = new Intent(this,myServices.class);
		startService(service);
		super.onDestroy();
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		// TODO Auto-generated method stub
//		Log.e("WillMemo", "Appwidget---------------------update!");
		super.onStartCommand(intent, flags, startId);
		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
		int []appWidgetIds =appWidgetManager.getAppWidgetIds(new ComponentName(this, myAppWidget.class));
		myAppWidget.updateAppWidget(this, appWidgetManager, appWidgetIds);
		ShowNotification sn = new ShowNotification(this);
		sn.showNotification();
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				onStartCommand(intent,flags, startId);
			}
		}, 10000);//1000*60
		return 0;
	}	
}
