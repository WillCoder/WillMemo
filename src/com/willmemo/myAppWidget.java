package com.willmemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class myAppWidget extends AppWidgetProvider{

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
//		Log.d(this.toString(), "AppWidget_update");
		updateAppWidget(context, appWidgetManager, appWidgetIds);
//		context.startService(new Intent(context,WillTimer.class));
//		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int [] appWidgetIds) 
	{
		Memodata myMemodata = new Memodata(context);
		Itemdata[] itemdata = myMemodata.getMemo();
		int N = appWidgetIds.length;
//		memodata.getMemoData();
		for (int i = 0; i < N; i++) 
		{
			int appWidgetId = appWidgetIds[i];		 
			RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.appwidget);
//			for(int j = 0;j<willmemo.myMemodata.getList().size()&&j<3;j++)
//			{
//				views.setTextColor(R.id.appwiget_title_1,Color.parseColor((String)willmemo.myMemodata.getList().get(0).get("importance")));
//				views.setTextViewText(R.id.appwiget_info_1,(String)willmemo.myMemodata.getList().get(0).get("info"));
//				views.setTextViewText(R.id.appwiget_title_1,(String)willmemo.myMemodata.getList().get(0).get("title"));
//				views.setImageViewBitmap(R.id.appwiget_img_1,(Bitmap)willmemo.myMemodata.getList().get(0).get("img"));
//			}
//			Intent intent = new Intent(context, editmemo.class);
//			intent = intent.putExtra("AppWidgetAdd", true);
//			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//			views.setOnClickPendingIntent(R.id.appwiget_addbutton, pendingIntent);
			views.setOnClickPendingIntent(R.id.appwiget_review,PendingIntent.getActivity(context, 0,new Intent("com.willmemo.REVIEW"), 0));			
//			pendingIntent = ;
			views.setOnClickPendingIntent(R.id.appwiget_addbutton,PendingIntent.getActivity(context, 0,new Intent("com.willmemo.EDITMEMO").putExtra("memostate", memoconstant.STATEINSERT), 0));
			
			
			
			
			
			views.setTextViewText(R.id.appwiget_info_1,"");
			views.setTextViewText(R.id.appwiget_title_1,"");
			views.setImageViewResource(R.id.appwiget_img_1, android.R.drawable.ic_menu_camera);
			views.setTextViewText(R.id.appwiget_info_2,"");
			views.setTextViewText(R.id.appwiget_title_2,"");
			views.setImageViewResource(R.id.appwiget_img_2, android.R.drawable.ic_menu_camera);
			views.setTextViewText(R.id.appwiget_info_3,"");
			views.setTextViewText(R.id.appwiget_title_3,"");
			views.setImageViewResource(R.id.appwiget_img_3, android.R.drawable.ic_menu_camera);

			for(int count = 0;count<itemdata.length;count++)
			{
//				views.setTextColor(R.id.appwiget_title_1,Color.parseColor((String)myMemodata.getList().get(0).get("importance")));

			}
			if(itemdata.length==1)
			{
//				views.setTextColor(R.id.appwiget_title_1,Color.parseColor((String)myMemodata.getList().get(0).get("importance")));
				views.setTextViewText(R.id.appwiget_title_1,tools.getDateString(itemdata[0].beginDate,itemdata[0].endDate));
				views.setTextViewText(R.id.appwiget_info_1,itemdata[0].text);
				views.setImageViewBitmap(R.id.appwiget_img_1,itemdata[0].imagebuffer);
			}
			else if(itemdata.length==2)
			{
//				views.setTextColor(R.id.appwiget_title_1,Color.parseColor((String)myMemodata.getList().get(0).get("importance")));
				views.setTextViewText(R.id.appwiget_title_1,tools.getDateString(itemdata[0].beginDate,itemdata[0].endDate));
				views.setTextViewText(R.id.appwiget_info_1,itemdata[0].text);
				views.setImageViewBitmap(R.id.appwiget_img_1,itemdata[0].imagebuffer);
				
//				views.setTextColor(R.id.appwiget_title_2,Color.parseColor((String)myMemodata.getList().get(1).get("importance")));
				views.setTextViewText(R.id.appwiget_title_2,tools.getDateString(itemdata[1].beginDate,itemdata[1].endDate));
				views.setTextViewText(R.id.appwiget_info_2,itemdata[1].text);
				views.setImageViewBitmap(R.id.appwiget_img_2,itemdata[1].imagebuffer);
			}
			else if(itemdata.length>=3)
			{
//				views.setTextColor(R.id.appwiget_title_1,Color.parseColor((String)myMemodata.getList().get(0).get("importance")));
				views.setTextViewText(R.id.appwiget_title_1,tools.getDateString(itemdata[0].beginDate,itemdata[0].endDate));
				views.setTextViewText(R.id.appwiget_info_1,itemdata[0].text);
				views.setImageViewBitmap(R.id.appwiget_img_1,itemdata[0].imagebuffer);
				
//				views.setTextColor(R.id.appwiget_title_2,Color.parseColor((String)myMemodata.getList().get(1).get("importance")));
				views.setTextViewText(R.id.appwiget_title_2,tools.getDateString(itemdata[1].beginDate,itemdata[1].endDate));
				views.setTextViewText(R.id.appwiget_info_2,itemdata[1].text);
				views.setImageViewBitmap(R.id.appwiget_img_2,itemdata[1].imagebuffer);
				
//				views.setTextColor(R.id.appwiget_title_3,Color.parseColor((String)myMemodata.getList().get(2).get("importance")));
				views.setTextViewText(R.id.appwiget_title_3,tools.getDateString(itemdata[2].beginDate,itemdata[2].endDate));
				views.setTextViewText(R.id.appwiget_info_3,itemdata[2].text);
				views.setImageViewBitmap(R.id.appwiget_img_3,itemdata[2].imagebuffer);
			
			}
			
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}	
	}
//	private void setViewData(Itemdata itemdata)
//	{
//		
//	}
}
