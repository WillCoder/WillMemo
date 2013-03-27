package com.willmemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class MemoReceiver extends BroadcastReceiver { 
		@Override
		public void onReceive(Context context, Intent intent) 
		{
			// TODO Auto-generated method stub
			String action = intent.getAction();
		     if(action.equals("android.intent.action.BOOT_COMPLETED"))//与receiver的action android:name保持一致
		     {//判断是否开机启动
		    	 Intent service = new Intent(Intent.ACTION_RUN);
		    	 service.putExtra("flags",PushService.FLAGS_START);
		    	 service.setClass(context, PushService.class); 
		    	 context.startService(service); //在此做开启services
		     }
		     //为了 在Context中有一个startActivity方法，Activity继承自Context，重载了startActivity方法。如果使用Activity的startActivity方法，不会有任何限制，而如果使用Context的startActivity方法的话，就需要开启一个新的task，遇到上面那个异常的，都是因为使用了Context的startActivity方法。解决办法是，加一个flag。    intent .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		     else if(action.equals(memoconstant.SERVICE_ACTION_UPDATE))
		     {
		    	 Intent service = new Intent(Intent.ACTION_RUN);
		    	 service.putExtra("flags",PushService.FLAGS_UPDATE);
		    	 service.setClass(context, PushService.class); 
		    	 context.startService(service); //在此做开启services
		     }
		     else if(action.equals(memoconstant.SERVICE_ACTION_TIMEUP))
		     {
		    	 Intent service = new Intent(Intent.ACTION_RUN);
		    	 service.putExtra("flags",PushService.FLAGS_TIMEUP);
		    	 service.setClass(context, PushService.class); 
		    	 context.startService(service); //在此做开启services
		     }
		}
}
