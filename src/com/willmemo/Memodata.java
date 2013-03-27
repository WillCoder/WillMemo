package com.willmemo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;

import com.will.tools.WriteAndReadText;

public class Memodata{


	private String fileDir;
//	public Memodata()
//	{
//		fileDir = Environment.getExternalStorageDirectory().toString()+File.separator+"WillMemo"+File.separator;
//	}
	public Memodata(Context context)
	{
//		context = arg;
		fileDir = context.getFilesDir()+File.separator+memoconstant.SAVEDIRNAME+File.separator;
//		fileDir = Environment.getExternalStorageDirectory().toString()+File.separator+"WillMemo"+File.separator;
	}
	public void setWmmPath(String dirPath)
	{
		fileDir = dirPath;
	}
	public String getDefaultPath()
	{
		return Environment.getExternalStorageDirectory().toString()+File.separator+"WillMemo"+File.separator;
	}
	public boolean createMemo(Itemdata itemdata)
	{
		boolean ret = false;
		try {
			JSONObject jsonObj  = new JSONObject();
			jsonObj.put("MemoID", itemdata.memoId);
			jsonObj.put("BeginTime", itemdata.beginDate);
			jsonObj.put("EndTime", itemdata.endDate);
			jsonObj.put("CountentText", itemdata.text);		
			jsonObj.put("ImageBuffer",Base64.encodeToString(tools.Bitmap2Bytes(itemdata.imagebuffer),Base64.DEFAULT));

			WriteAndReadText myWriteAndReadText = new WriteAndReadText();		
			if(!new File(fileDir).exists())
			{
				new File(fileDir).mkdirs();
			}
			myWriteAndReadText.setTextPath(fileDir);
			ret = myWriteAndReadText.writeText(itemdata.memoId+".wmm", jsonObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public boolean updateMemo(String MemoId,Itemdata itemdata)
	{
		boolean ret = false;
		if(deleteMemo(MemoId))
		{
			ret = createMemo(itemdata);
		}
		return ret;
	}
	public Itemdata[] getMemo()
	{
		ArrayList<Itemdata> myItemdata = new ArrayList<Itemdata>();
		File dataPath = new File(fileDir);
		File[] dataPathList;
		JSONObject jsonObj;
		FilenameFilter memoFilenameFilter =	new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String filename) {
				// TODO Auto-generated method stub
				if(filename.endsWith(".wmm"))
				{
					return true;
				}
				return false;
			}
		};
		
		dataPathList = dataPath.listFiles(memoFilenameFilter);
		for(int i = 0;dataPathList!=null && i<dataPathList.length;i++)
		{
			try {
				WriteAndReadText myWriteAndReadText = new WriteAndReadText();

				
				String Path = dataPathList[i].getParent();
				myWriteAndReadText.setTextPath(Path);				
				Itemdata ItemDataTemp = new Itemdata();
				jsonObj = new JSONObject(myWriteAndReadText.readText(dataPathList[i].getName()));
				ItemDataTemp.memoId = jsonObj.getString("MemoID");				
				ItemDataTemp.beginDate = jsonObj.getLong("BeginTime");
				ItemDataTemp.endDate = jsonObj.getLong("EndTime");
				ItemDataTemp.text = (String) jsonObj.get("CountentText");
				ItemDataTemp.imagebuffer = tools.Bytes2Bimap(Base64.decode(jsonObj.getString("ImageBuffer"),Base64.DEFAULT));
				myItemdata.add(ItemDataTemp);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		return rankMemo(myItemdata.toArray(new Itemdata[0]));	
	}
	private Itemdata[] rankMemo(Itemdata[] myItemdata)
	{
		
		Itemdata itemdataTemp = new Itemdata();
		if(myItemdata==null)
		{
			return myItemdata;
		}
		for(int i = 0;i<myItemdata.length;i++)
		{
			for(int j = 0;j<myItemdata.length;j++)
			{
				if(tools.getImportance(myItemdata[i].beginDate,myItemdata[i].endDate)<tools.getImportance(myItemdata[j].beginDate,myItemdata[j].endDate))
				{
					itemdataTemp = myItemdata[i];
					myItemdata[i] = myItemdata[j];
					myItemdata[j] = itemdataTemp;
				}
			}
		}
		return myItemdata;
	}
	public boolean deleteMemo(String memoId)
	{
		File dataPath = new File(fileDir,memoId+".wmm");
		if(dataPath.exists())
		{
			dataPath.delete();
			return true;
		}
		return false;
	}
	public void deleteMemo(String[] memoId)
	{
		for(int i=0;memoId!=null && i<memoId.length;i++)
		{
			deleteMemo(memoId[i]);
		}
	}
	public void deleteAllMemo()
	{
		File dataPath = new File(fileDir);
		FilenameFilter memoFilenameFilter =	new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String filename) {
				// TODO Auto-generated method stub
				if(filename.endsWith(".wmm"))
				{
					new File(dir,filename).delete();
					return true;
				}
				return false;
			}
		};		
		dataPath.listFiles(memoFilenameFilter);
	}

}
