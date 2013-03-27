package com.willmemo;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class tools {
	
    public static Bitmap Bytes2Bimap(byte[] b){  
        if(b.length!=0){  
            return BitmapFactory.decodeByteArray(b, 0, b.length);  
        }  
        else {  
            return null;
        }  
    }
    public static byte[] Bitmap2Bytes(Bitmap bm)
    {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();    
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);    
        return baos.toByteArray();  
    }  
	public static int getImportance(long begin_Milliseconds,long end_Milliseconds)
	{
		int ret = 0;
		long cut_Milliseconds = System.currentTimeMillis();
		
		Calendar begin_calendar = Calendar.getInstance();
		Calendar end_calendar = Calendar.getInstance();
		Calendar cut_calendar = Calendar.getInstance();
		
		begin_calendar.setTimeInMillis(begin_Milliseconds);	
		end_calendar.setTimeInMillis(end_Milliseconds);
		cut_calendar.setTimeInMillis(cut_Milliseconds);

		if(end_calendar.get(Calendar.YEAR)==cut_calendar.get(Calendar.YEAR)&&
		   end_calendar.get(Calendar.DAY_OF_YEAR)==cut_calendar.get(Calendar.DAY_OF_YEAR))
		{
			ret = 1;
		}
		else if(cut_Milliseconds<end_Milliseconds)
		{
			long x1 = begin_Milliseconds;
			long x2 = cut_Milliseconds;
			long x3 = end_Milliseconds;
			long p = (x3-x2)*100/(x3-x1);
//			if(p>=60)
//			{
//				ret = 6;
//			}
//			else if(60>p&&p>=40)
//			{
//				ret = 5;
//			}
//			else if(40>p&&p>=25)
//			{
//				ret = 5;
//			}
//			else if(25>p&&p>=15)
//			{
//				ret = 3;
//			}
//			else if(15>p&&p>=5)
//			{
//				ret = 3;
//			}
//			else if(5>p)
//			{
//				ret = 1;
//			}		
			if(p>=60)
			{
				ret = 6;
			}
			else if(60>p&&p>=30)
			{
				ret = 5;
			}
			else if(30>p&&p>=10)
			{
				ret = 3;
			}
			else if(10>p)
			{
				ret = 1;
			}
		}
		else
		{
			ret = 0;
		}
		return ret;
	}
//	public static int getImportanceRTint(long begin_Milliseconds,long end_Milliseconds)
//	{
//		Calendar begin_calendar = Calendar.getInstance();
//		Calendar end_calendar = Calendar.getInstance();
//		begin_calendar.setTimeInMillis(begin_Milliseconds);	
//		end_calendar.setTimeInMillis(end_Milliseconds);
//		long cutTime = System.currentTimeMillis();
//		int ret = 0;
//		
//		
//		if(cutTime>end_Milliseconds)
//		{
//			long x1 = begin_Milliseconds;
//			long x2 = cutTime;
//			long x3 = end_Milliseconds;
//			long p = (x3-x2)*100/(x3-x1);
//			if(p>60)
//			{
//				ret = 6;
//			}
//			else if(60>p&&p>=40)
//			{
//				ret = 5;
//			}
//			else if(40>p&&p>=25)
//			{
//				ret = 4;
//			}
//			else if(25>p&&p>=15)
//			{
//				ret = 3;
//			}
//			else if(15>p&&p>=5)
//			{
//				ret = 2;
//			}
//			else if(5>p)
//			{
//				ret = 1;
//			}			
//		}
//		else
//		{
//			ret = 0;
//		}
//		return ret;
//	}
	static public String getDateString(long milliseconds)
	{
		String ret = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		ret = calendar.get(Calendar.YEAR) +"-"+(calendar.get(Calendar.MONTH)+1) +"-"+ calendar.get(Calendar.DAY_OF_MONTH);		
//		ret = (date.getYear()+1900)+"/"+date.getMonth()+"/"+date.getDay();
		return ret;
	}
	static public String getDateString(long begin_Milliseconds,long end_Milliseconds)
	{
		String ret = "";
		String deadline = "";

		Calendar begin_calendar = Calendar.getInstance();
		Calendar end_calendar = Calendar.getInstance();
		begin_calendar.setTimeInMillis(begin_Milliseconds);	
		end_calendar.setTimeInMillis(end_Milliseconds);
		

		int year = end_calendar.get(Calendar.YEAR)-begin_calendar.get(Calendar.YEAR);
		int month = end_calendar.get(Calendar.MONTH)-begin_calendar.get(Calendar.MONTH);
		int day = end_calendar.get(Calendar.MONDAY)-begin_calendar.get(Calendar.MONDAY);

		if(year>0)
		{
			deadline = ""+year+"Y";
		}
		if(month>0)
		{
			deadline = deadline + month + "M";
		}
		if(day>0)
		{
			deadline = deadline + day + "D";
		}
		deadline = deadline + "   ";
//		ret = deadline+end_calendar.get(Calendar.YEAR) +"-"+(end_calendar.get(Calendar.MONTH)+1) +"-"+ end_calendar.get(Calendar.DAY_OF_MONTH);
		ret = end_calendar.get(Calendar.YEAR) +"-"+(end_calendar.get(Calendar.MONTH)+1) +"-"+ end_calendar.get(Calendar.DAY_OF_MONTH);
		return ret;
	}
}
