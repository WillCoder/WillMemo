package com.willmemo;

import java.io.File;

import android.annotation.TargetApi;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gfan.sdk.statitistics.GFAgent;

public class willmemo extends Activity {
	
//	static Memodata myMemodata = new Memodata();
	ListView listview;
//	boolean ListFreashFlag = true;
	float x = 0;
	float y = 0;
	static public boolean[] isSelect;
	static public Itemdata[] myItemdata;
	MemoAdapter myMemoAdapter;
//	boolean isSelect[];

    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** youmi ad **/
//        AdManager.init(this,"537ef88653a2993c", "b9e10bcfe994a9fb", 30, false);
        /** youmi ad **/
		SharedPreferences mySP = getSharedPreferences(memoconstant.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
		if(mySP.getBoolean(memoconstant.SHARED_PREFERENCES_START_GUIDE, true))
		{
			Intent intent = new Intent();
			intent.setClass(this,Help.class);
			startActivity(intent);
		}
		
        setContentView(R.layout.list);
        listview = (ListView)findViewById(R.id.mylist);
        listview.setCacheColorHint(Color.TRANSPARENT);
        listview.setOnItemClickListener(new myOnItemClickListener());
        listview.setOnItemSelectedListener(new myOnItemSelectedListener());	
        initButton();
        /** youmi ad **/
//        initAdView();
        /** youmi ad **/
		Memodata myMemodata = new Memodata(this);
		myItemdata = myMemodata.getMemo();
        myMemoAdapter = new MemoAdapter(this,myItemdata);
		listview.setAdapter(myMemoAdapter);
		updateAppWidget();
//		listview.set
	   	 Intent service = new Intent(Intent.ACTION_RUN);
	   	 service.putExtra("flags",PushService.FLAGS_START);
	   	 service.setClass(this, PushService.class); 
	   	 startService(service);
		
//        initDB();
        
//        initList();
        
//        PendingIntent pendingIntent;
//        pendingIntent.getActivity(context, requestCode, intent, flags);
//        Intent intent = getIntent();
//        System.out.println("AppWidgetAdd:"+intent.getBooleanExtra("AppWidgetAdd", false));
//        if(intent.getBooleanExtra("AppWidgetAdd", false))//intent.putExtra("AppWidgetAdd", true);
//        {
//			intent = new Intent();
//			intent.putExtra("memostate", memoconstant.STATEINSERT);
//			intent.setClass(willmemo.this, editmemo.class);
//			willmemo.this.startActivity(intent);
//        }
//    	CheckBox myCheckBox;
//    	myCheckBox = (CheckBox)findViewById(R.id.CheckBox);
//    	myCheckBox.setOnClickListener(new  CheckBoxListener());
//		nm.notify();
//		ShowNotification sn = new ShowNotification(this);
//		sn.Show();
		
		
//		PendingIntent pt=PendingIntent.getActivity(this, 0, new Intent("com.willmemo.REVIEW"), 0); 
//		PendingIntent pt=PendingIntent.getActivity(this, 0, new Intent("com.willmemo.REVIEW"), 0); 
//
//		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);;
//		Notification noti = new Notification.Builder(this)
//         .setContentTitle("New mail from ")
//         .setContentText("aaaaaaaa")
//         .setSmallIcon(R.drawable.icon)
//         .setContentIntent(pt)
//         .setAutoCancel(true)
//         .build();
//		nm.notify(123678, noti);


    }
//	public List<Map<String, Object>> getList()
//	{
//		Memodata myMemodata = new Memodata(this);
//		Itemdata[] myItemdata = myMemodata.getMemo();
//		
//    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//    	for(int i = 0;i<myItemdata.length;i++)
//    	{
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("title",myItemdata[i].text);
//			map.put("info",myItemdata[i].text);
//			  			
//			
////			map.put("image",new BitmapDrawable(myItemdata[i].imagebuffer));
////			map.put("image",android.R.drawable.ic_menu_camera);
//			list.add(map);
//    	}		
////    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
////    	for(int i = 0;i<15;i++)
////    	{
////			Map<String, Object> map = new HashMap<String, Object>();
////			map.put("title","XXXXXX");
////			map.put("info","xxxxx");
////			map.put("image","xx");
////			list.add(map);
////    	}
//    	return list;
//	}
	
	
	


	@TargetApi(11)
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
//		try
//		{
//			menu.add("导入/导出").setIcon(R.drawable.ic_menu_add).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//		}
//		catch(Error e) 
//		{
//			Log.e("API level",e.toString());
//		}
//		menu.add("导入/导出").setIcon(android.R.drawable.ic_menu_set_as);
    	menu.add(getString(R.string.setting)).setIcon(android.R.drawable.ic_menu_manage).setIntent(new Intent(this,Setting.class));
//    	menu.add("帮助").setIcon(android.R.drawable.ic_menu_help);
//    	menu.add("关于").setIcon(android.R.drawable.ic_menu_info_details).setIntent(new Intent(this,About.class));
    	return super.onCreateOptionsMenu(menu);
    }
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onPrepareOptionsMenu(menu);
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}





	//    @Override
//	protected void onRestart() {
//		// TODO Auto-generated method stub
////    	Memodata myMemodata = new Memodata();
////    	System.out.println("onRestart");
////    	mySimpleAdapter adapter;
////		adapter = new mySimpleAdapter(this,myMemodata.getList(),R.layout.items,
////				new String[]{"importance","title","info","memoId"},
////				new int[]{R.id.importance,R.id.title,R.id.info,R.id.img});
//////		setListAdapter(adapter);
////		updateAppWidget();
////		listview.setAdapter(adapter);
//		Memodata myMemodata = new Memodata(this);
//		myItemdata = myMemodata.getMemo();
////        myMemoAdapter = new MemoAdapter(this,myItemdata);
//        myMemoAdapter.notifyDataSetChanged(myItemdata);
////		listview.notifyAll();
//		super.onRestart();
//	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
    	LinearLayout ListBarBottom = (LinearLayout)findViewById(R.id.list_bar_bottom);
    	Animation AnimDockExit = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
    	ListBarBottom.setAnimation(AnimDockExit);
		if(ListBarBottom.isShown())
		{
			ListBarBottom.setAnimation(AnimDockExit);
			ListBarBottom.setVisibility(View.GONE);
			for(int i = 0;i<myItemdata.length;i++)
			{
				myItemdata[i].isSelect = false;
			}
			selectChanged();
		}
		else
		{
			super.onBackPressed();
		}	
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





	//	@Override
//	protected void onStart() {
//		// TODO Auto-generated method stub
//		System.out.println("onStart");
//		super.onStart();
//	}
//
//
//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		System.out.println("onStop");
//		super.onStop();
//	}
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
    	System.out.println("onActivityResult");
    	switch(resultCode)
    	{
    	case RESULT_OK:
    		{
    			adapterChanged();
    		}
    	default:
    		super.onActivityResult(requestCode, resultCode, data);
    	}
    	
//		
	}

    
    
	public void initDB()
    {   	
		String dbcommand = null;
		SQLiteDatabase db = null;
		File memofile = new File(memoconstant.DBPATH,memoconstant.DBNAME);
		if(memofile.exists()&&memofile.isFile())
		{
			db = SQLiteDatabase.openOrCreateDatabase(memofile, null);
		}
		else
		{
			new File(memoconstant.DBPATH).mkdirs();
			db = SQLiteDatabase.openOrCreateDatabase(memofile, null);
			dbcommand="CREATE TABLE MEMODATA(MemoIndex TEXT,BeginTime Long,EndTime Long,CountentText TEXT,ImageBuffer MEDIUMBLOB)";
//			PRIMARY KEY (Id_P)
			db.execSQL(dbcommand); 
		}
		db.close();
    }//02-05 06:08:43.626: ERROR/Database(9258): Failure 1 (near "Index": syntax error) on 0x1ae218 when preparing 'CREATE TABLE MEMODATA(Index TEXT,BeginTime TEXT,EndTime TEXT,CountentText TEXT,ImageBuffer MEDIUMBLOB)'.
    

	public void initButton()
    {
    	ImageButton AddButton = null;
    	ImageButton DeleteButton = null;
    	Button SelectAllButton = null;
    	Button CancelAllButton = null;
    	AddButton = (ImageButton)findViewById(R.id.AddButton);
    	DeleteButton = (ImageButton)findViewById(R.id.DeleteButton);
    	SelectAllButton = (Button)findViewById(R.id.select_all_btn);
    	CancelAllButton = (Button)findViewById(R.id.cancel_all_btn);
    	
    	SelectAllButton.setOnClickListener(new SelectAllButtonListener());
    	CancelAllButton.setOnClickListener(new CancelAllButtonListener());
    	AddButton.setOnClickListener(new AddButtonListener());
    	DeleteButton.setOnClickListener(new DeleteButtonListener());
//    	myCheckBox.setOnClickListener(new  CheckBoxListener());
    }//02-05 06:52:09.497: ERROR/AndroidRuntime(18915): java.lang.RuntimeException: Unable to start activity ComponentInfo{com.willmemo/com.willmemo.willmemo}: java.lang.NullPointerException
//	public void initAdView()
//	{
//		
//		LinearLayout adViewLayout = (LinearLayout) findViewById(R.id.adViewLayout);
//		adViewLayout.addView(new AdView(this), 
//		new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//		
//	}
	public void setButtonState(Itemdata[] args)
    {	
    	Itemdata[] itemdata = args;
//    	ImageButton DeleteButton = (ImageButton)findViewById(R.id.DeleteButton);
    	LinearLayout ListBarBottom = (LinearLayout)findViewById(R.id.list_bar_bottom);
    	Animation AnimDockEnter = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
    	Animation AnimDockExit = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
    	boolean isAnySelected = false;
		for(int i = 0;i<itemdata.length;i++)
		{
			if(itemdata[i].isSelect)
			{
				isAnySelected = true;						
				break;
			}
		}
		if(isAnySelected)
		{
//			overridePendingTransition(enterAnim, exitAnim);
			if(!ListBarBottom.isShown())
			{
				ListBarBottom.setAnimation(AnimDockEnter);
			}
			ListBarBottom.setVisibility(View.VISIBLE);
//			DeleteButton.setVisibility(View.VISIBLE);
		}
		else
		{
			if(ListBarBottom.isShown())
			{
				ListBarBottom.setAnimation(AnimDockExit);
			}
//			ListBarBottom.setAnimation(AnimDockExit);
			ListBarBottom.setVisibility(View.GONE);
//			DeleteButton.setVisibility(View.GONE);  
		}
//    	ImageButton AddButton = (ImageButton)findViewById(R.id.AddButton);
    	
//    	switch(state)
//    	{
//    	case memoconstant.BTN_DELETE:
////    		AddButton.setVisibility(View.GONE);
//        	DeleteButton.setVisibility(View.VISIBLE);
////			Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
////			DeleteButton.startAnimation(animation);
//    		break;
//    	case memoconstant.BTN_ADD:
////    		AddButton.setVisibility(View.VISIBLE);
//        	DeleteButton.setVisibility(View.GONE);  
//    		break;
//    	case memoconstant.BTN_DEFAULT:
//    		break;
//    		default:
//    			break;
//    	}
//    	ImageButton AddButton = null;
//    	ImageButton DeleteButton = null;
//    	AddButton = (ImageButton)findViewById(R.id.AddButton);
//    	DeleteButton = (ImageButton)findViewById(R.id.DeleteButton);
//    	DeleteButton.setVisibility(visibility);
    }
    public void initList()
    {
//    	Memodata myMemodata = new Memodata();
//		mySimpleAdapter adapter;
//        listview = (ListView)findViewById(R.id.mylist);
//        listview.setCacheColorHint(Color.WHITE);
//        listview.setOnItemClickListener(new myOnItemClickListener());
//      listview.setAdapter(adapter);
//        listview.setOnTouchListener(new myOnTouchListener());

        
//        String[] COUNTRIES = new String[] { "Afghanistan", "Albania",  
//            "Algeria", "American Samoa", "Andorra" }; 
//        listview.setAdapter(new ArrayAdapter<String>(this,R.layout.list,  
//                COUNTRIES));
        
//    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//    	for(int i = 0;i<10;i++)
//    	{
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("importance","good");
//			list.add(map);
//    	}

        
        
//        adapter = new mySimpleAdapter(this,list,R.layout.items,
//				null,null
//				);
        
        
//		Memodata myMemodata = new Memodata(this);
//		mySimpleAdapter adapter = new mySimpleAdapter(this,myMemodata.getList(),R.layout.items,
//				new String[]{"importance","title","info","memoId"},
//				new int[]{R.id.importance,R.id.title,R.id.info,R.id.img});
//		updateAppWidget();
//		listview.setAdapter(adapter);
        
        

//		updateAppWidget();
//		listview.setAdapter(adapter);
    }
    class myOnItemClickListener implements OnItemClickListener
    {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			System.out.println("arg2:"+arg2);
			
			
			
			Intent intent = new Intent();
			intent.putExtra("itemnum",arg2);
			intent.putExtra("memostate", memoconstant.STATEUPDATE);
			intent.setClass(willmemo.this, editmemo.class);
			willmemo.this.startActivityForResult(intent,RESULT_FIRST_USER);
		}
    }
    class myOnItemSelectedListener implements OnItemSelectedListener
    {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			System.out.println("onItemSelected:"+arg2);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			System.out.println("onNothingSelected:"+arg0);
		}
    	
    }
//    class myOnTouchListener implements OnTouchListener
//    {
//
//		@Override
//		public boolean onTouch(View v, MotionEvent event) {
//			// TODO Auto-generated method stub
//			switch(event.getAction())
//			{
//			case MotionEvent.ACTION_DOWN:
//			{
//				x = event.getX();
//				y = event.getY();
//				System.out.println("[Down]X:"+x+"   Y:"+y);
//			}break;
//			case MotionEvent.ACTION_MOVE:
//			{
////				System.out.println("[Position]:"+listview.getPositionForView(v));
////				for(int i = 0;i<listview.getCount();i++)
////				{
////					if(event.getY(i)<y)
////					{
////						System.out.println("[Move]"+"GetY:"+ event.getY(i)+"    HisY:"+y+"    Position:"+i);
////						break;
////					}
////				}
////				if()
////				System.out.println("[Move]X:"+event.getX(0)+"   Y:"+event.getY(0));
//			}break;
//			default:
//				break;
//			}
//			return false;
//		}
//    	
//    }

	public void adapterChanged()
	{	
		Memodata myMemodata = new Memodata(this);
		myItemdata = myMemodata.getMemo();	
		myMemoAdapter.notifyDataSetChanged(myItemdata);
		setButtonState(myItemdata);
		updateAppWidget();
	}
	public void selectChanged()
	{	
//		Memodata myMemodata = new Memodata(this);
//		myItemdata = myMemodata.getMemo();	
		myMemoAdapter.notifyDataSetChanged(myItemdata);
		setButtonState(myItemdata);
		updateAppWidget();
	}
	public void updateAppWidget()
	{
		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
		int []appWidgetIds =appWidgetManager.getAppWidgetIds(new ComponentName(this, myAppWidget.class));
		myAppWidget.updateAppWidget(this, appWidgetManager, appWidgetIds);
	}
	class AddButtonListener implements ImageButton.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("memostate", memoconstant.STATEINSERT);
			intent.setClass(willmemo.this, editmemo.class);
			willmemo.this.startActivityForResult(intent,RESULT_FIRST_USER);
		}	
	}
	class DeleteButtonListener implements ImageButton.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Memodata mymemodata = new Memodata(v.getContext());
			for(int i = 0;i<myItemdata.length;i++)
			{
				if(myItemdata[i].isSelect)
				{
					mymemodata.deleteMemo(myItemdata[i].memoId);
				}
			}
			adapterChanged();
		}	
	}
	class SelectAllButtonListener implements Button.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			for(int i = 0;i<myItemdata.length;i++)
			{
				myItemdata[i].isSelect = true;
			}
			selectChanged();
		}
		
	}
	class CancelAllButtonListener implements Button.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			for(int i = 0;i<myItemdata.length;i++)
			{
				if(myItemdata[i].isSelect)
				{
					myItemdata[i].isSelect = false;
				}
				else
				{
					myItemdata[i].isSelect = true;
				}
			}
			selectChanged();
		}
		
	}
}