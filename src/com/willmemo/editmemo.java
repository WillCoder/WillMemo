package com.willmemo;

import java.util.Calendar;

import com.gfan.sdk.statitistics.GFAgent;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class editmemo extends Activity{
	
//	Itemdata myInsertItemdata = new Itemdata();
	EditText myEditText;
	DatePicker myDatePicker;
	ImageButton saveAndExitButton;
//	ImageButton saveAndNextButton;
	ImageButton cancelButton;
	ImageView myImageView;
	Bitmap bitmap = null;
	String memostate = null;
//	String memoId = null;
	int itemnum;
	int CASE_CAMERA = 0;
//	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		context = this;
		
//		setTheme(R.style.edit_dialog);
		setContentView(R.layout.edit);
		Intent intent = getIntent();
		memostate = intent.getStringExtra("memostate");
		if(memostate.equals(memoconstant.STATEINSERT))
		{
			
		}
		if(memostate.equals(memoconstant.STATEUPDATE))//update
		{
			itemnum = intent.getIntExtra("itemnum",0);
		}
		initlayout();	
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("onActivityResult");
			try{
				if(resultCode==Activity.RESULT_OK){
					if(requestCode==CASE_CAMERA){
//						typeMulti = 0;  
						Bundle extras = data.getExtras();
						bitmap= (Bitmap) extras.get("data");
						int temp=0;
						int startx=0;
						int Starty=0;
						int width=bitmap.getWidth();
						int height=bitmap.getHeight();
						if(width>height)
						{
							temp = width-height;
							startx = temp/2;
							Starty = 0;
							width = height;
						}
						else
						{
							temp = height-width;
							startx = 0;
							Starty = temp/2;
							height = width;
						}
						
						bitmap = Bitmap.createBitmap(bitmap, startx, Starty, width,height);
						myImageView.setImageBitmap(bitmap);
						System.out.println("bitmap:"+bitmap);
//						path = IOUtils.saveImg(bitmap);
						/*Toast toast=Toast.makeText(this, "SDcard/DCIM/Camera, Toast.LENGTH_LONG);
						toast.setGravity(Gravity.BOTTOM, 0, 0);
						toast.show();*/
//						isUploadPhoto(bitmap);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		updateAppWidget();
		super.finish();
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
	
	public void updateAppWidget()//same as willmemo.updateAppWidget()
	{
		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
		int []appWidgetIds =appWidgetManager.getAppWidgetIds(new ComponentName(this, myAppWidget.class));
		myAppWidget.updateAppWidget(this, appWidgetManager, appWidgetIds);
	}


	public void initlayout()
	{
		myDatePicker = (DatePicker)findViewById(R.id.datePicker1);
//		myDatePicker.setEnabled(false);
//		myDatePicker.setCalendarViewShown (false);
		myEditText = (EditText)findViewById(R.id.editText1);
		saveAndExitButton = (ImageButton)findViewById(R.id.button1);
//		saveAndNextButton = (Button)findViewById(R.id.button2);
		cancelButton = (ImageButton)findViewById(R.id.button3);
		myImageView = (ImageView)findViewById(R.id.imageView1);
				
		saveAndExitButton.setOnClickListener(new saveAndExitButtonListener());
//		saveAndNextButton.setOnClickListener(new saveAndNextButtonListener());
		cancelButton.setOnClickListener(new cancelButtonListener());
		
//		saveAndExitButton.setBackgroundResource(android.R.drawable.ic_menu_save);
		
		myImageView.setOnClickListener(new myImageViewListener());
//		myDatePicker.set;
		
		if(memostate.equals(memoconstant.STATEINSERT));//insert
		if(memostate.equals(memoconstant.STATEUPDATE))//update
		{		
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(willmemo.myItemdata[itemnum].endDate);
			myDatePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
			myImageView.setImageBitmap(willmemo.myItemdata[itemnum].imagebuffer);
			bitmap = willmemo.myItemdata[itemnum].imagebuffer;
			myEditText.setText(willmemo.myItemdata[itemnum].text);
		}
		
		
//		myEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);   
		//文本显示的位置在EditText的最上方   
//		myEditText.setGravity(Gravity.TOP);   
//		myEditText.setText("数据测试数据测试数据测试数据测试数据测试数据测试");   
//		改变默认的单行模式   
//		myEditText.setSingleLine(false);   
		//水平滚动设置为False   
//		myEditText.setHorizontallyScrolling(false); 
	}
	public void CreateMemo()
	{
		Itemdata myItemdata = new Itemdata();
		if(bitmap==null)
		{
			bitmap=BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_menu_camera);  			
		}
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTimeInMillis(System.currentTimeMillis());
		Calendar endCalendar = Calendar.getInstance();

		endCalendar.set(Calendar.YEAR, myDatePicker.getYear());
		endCalendar.set(Calendar.MONTH, myDatePicker.getMonth());
		endCalendar.set(Calendar.DATE, myDatePicker.getDayOfMonth());
		endCalendar.set(Calendar.HOUR_OF_DAY,23);
		endCalendar.set(Calendar.MINUTE,59);
		endCalendar.set(Calendar.SECOND,59);
		String text = myEditText.getText().toString();
//		endCalendar.set(myDatePicker.getYear(),myDatePicker.getMonth(),myDatePicker.getDayOfMonth());

		
		
//		System.out.println("Begin:"+tools.getDateString(beginCalendar.getTimeInMillis()));
//		System.out.println("End:"+tools.getDateString(endCalendar.getTimeInMillis()));
//		System.out.println("New:"+tools.getDateString(beginCalendar.getTimeInMillis(),endCalendar.getTimeInMillis()));
		
		
//		long ID = Long.valueOf(beginCalendar.toString()+endCalendar.toString()+text);
		String ID = Long.toString(beginCalendar.getTimeInMillis()+endCalendar.getTimeInMillis());
//		long x = (long)beginCalendar.toString()+endCalendar.toString()+text;
//		String memoId = Long.valueOf(beginCalendar.toString()+endCalendar.toString()+text).toString();
//		String memoId = String.valueOf(beginCalendar.toString()+endCalendar.toString()+text);
//		myItemdata.memoId =	ID;
//		myItemdata.beginDate = beginCalendar.getTimeInMillis();
//		myItemdata.endDate = endCalendar.getTimeInMillis();
//		myItemdata.text = text;
//		myItemdata.imagebuffer = bitmap;

		
		
		if(memostate.equals(memoconstant.STATEINSERT))//insert
		{
			myItemdata.memoId =	ID;
			myItemdata.beginDate = beginCalendar.getTimeInMillis();
			myItemdata.endDate = endCalendar.getTimeInMillis();
			myItemdata.text = text;
			myItemdata.imagebuffer = bitmap;
			
			Memodata myMemodata = new Memodata(this);
			if(myMemodata.createMemo(myItemdata))
			{
				Toast.makeText(this,getString(R.string.insert),Toast.LENGTH_SHORT).show();				
			}
		}
		if(memostate.equals(memoconstant.STATEUPDATE))//update
		{
			myItemdata.memoId =	willmemo.myItemdata[itemnum].memoId;
			myItemdata.beginDate = willmemo.myItemdata[itemnum].beginDate;
			myItemdata.endDate = endCalendar.getTimeInMillis();
			myItemdata.text = text;
			myItemdata.imagebuffer = bitmap;
			
			Memodata myMemodata = new Memodata(this);
			if(myMemodata.updateMemo(willmemo.myItemdata[itemnum].memoId,myItemdata))
			{
				Toast.makeText(this,getString(R.string.update),Toast.LENGTH_SHORT).show();				
			}
		}
		this.setResult(RESULT_OK);	
	}

	class saveAndExitButtonListener implements Button.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			CreateMemo();
			finish();
		}
		
	}
	class saveAndNextButtonListener implements Button.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class cancelButtonListener implements Button.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		
	}
	class myImageViewListener implements ImageView.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			System.out.println("myImageViewListener");
//			Camera myCamera = null;
//			myCamera.startPreview();
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if (getPackageManager().queryIntentActivities(intent,0).size() > 0) 
			{
				startActivityForResult(intent,CASE_CAMERA);
			}
			else
			{
				Toast.makeText(v.getContext(),getString(R.string.no_function), Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
}
