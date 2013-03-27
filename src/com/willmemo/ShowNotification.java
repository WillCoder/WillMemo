package com.willmemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

public class ShowNotification {

	Context context;
	public ShowNotification(Context arg)
	{
		context = arg;
	}
	public void showNotification()
	{
		int RedCount = 0;
		int DeadCount = 0;
		boolean isTimeUpOpen = false;
		SharedPreferences mySP = context.getSharedPreferences(memoconstant.SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
		isTimeUpOpen = mySP.getBoolean(memoconstant.SHARED_PREFERENCES_TIME_UP_NOTI,false);
		Memodata myMemodata = new Memodata(context);
		Itemdata[] itemdata = myMemodata.getMemo();
		
		for(int i = 0;i<itemdata.length;i++)
		{
			if(tools.getImportance(itemdata[i].beginDate,itemdata[i].endDate)==1)
			{
				RedCount++;
			}
			if(tools.getImportance(itemdata[i].beginDate,itemdata[i].endDate)==0)
			{
				DeadCount++;
			}
		}
		if((RedCount == 0 && DeadCount == 0)||(!isTimeUpOpen && RedCount == 0))
		{
			return;
		}
        PendingIntent pt=PendingIntent.getActivity(context, 0, new Intent("com.willmemo.REVIEW"), 0); 
        //在Status Bar显示的图片、 Ticker文本、通知时间  
        Notification notification = new Notification(R.drawable.icon_noti,context.getString(R.string.app_name),System.currentTimeMillis()+5000);  
        //在通知列表中显示的标题、内容、点击时触发的事件  
        if(isTimeUpOpen && DeadCount!=0)
        {
        	if(RedCount==0)
        	{
        		notification.setLatestEventInfo(context,context.getString(R.string.app_name),String.format(context.getString(R.string.notification_1),DeadCount), pt);	
        	}
        	else
        	{
        		notification.setLatestEventInfo(context,context.getString(R.string.app_name),String.format(context.getString(R.string.notification_2),RedCount,DeadCount), pt);
        	}
        }
        else
        {
        	 notification.setLatestEventInfo(context,context.getString(R.string.app_name), String.format(context.getString(R.string.notification_3),RedCount), pt); 
        }
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.flags |= notification.FLAG_ONLY_ALERT_ONCE;
//        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_ALL;
//        notification.priority |= Notification.PRIORITY_DEFAULT;
//        notification.largeIcon = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon);
        NotificationManager nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);  
        nm.notify(0, notification);  
    } 
}
