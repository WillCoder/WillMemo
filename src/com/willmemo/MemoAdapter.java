package com.willmemo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MemoAdapter extends BaseAdapter{

	private Context context;
	private Itemdata[] itemdata;
	private LayoutInflater mInflater;
	
	public final class ViewHolder{
		public TextView importance;
		public ImageView img;
		public TextView title;
		public TextView info;
		public Button viewBtn;
		public CheckBox checkbox;
	}
	MemoAdapter(Context arg1,Itemdata[] args2)
	{
		this.mInflater = LayoutInflater.from(arg1);
		context = arg1;
		itemdata = args2;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		willmemo.isSelect = new boolean[itemdata.length];
		return itemdata.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			
			holder=new ViewHolder();  
			
			convertView = mInflater.inflate(R.layout.items, null);
			holder.importance = (TextView)convertView.findViewById(R.id.importance);
			holder.img = (ImageView)convertView.findViewById(R.id.img);
			holder.title = (TextView)convertView.findViewById(R.id.title);
			holder.info = (TextView)convertView.findViewById(R.id.info);
//			holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
			holder.checkbox = (CheckBox)convertView.findViewById(R.id.checkbox);
			convertView.setTag(holder);
			
		}else {
			
			holder = (ViewHolder)convertView.getTag();
		}
		
//		getImportance
		holder.importance.setBackgroundColor(Color.parseColor( memoconstant.importenceLevel[tools.getImportance(itemdata[position].beginDate,itemdata[position].endDate)]));
//		if(position==1)
//		{
//			holder.importance.setBackgroundColor(Color.parseColor(memoconstant.importenceLevel[1]));
//		}
//		if(position==2)
//		{
//			holder.importance.setBackgroundColor(Color.parseColor(memoconstant.importenceLevel[3]));
//		}
//		if(position==3)
//		{
//			holder.importance.setBackgroundColor(Color.parseColor(memoconstant.importenceLevel[5]));
//		}
//		if(position==4)
//		{
//			holder.importance.setBackgroundColor(Color.parseColor(memoconstant.importenceLevel[6]));
//		}
		
//		if(position<7)
//		{
//			holder.importance.setBackgroundColor(Color.parseColor(memoconstant.importenceLevel[position]));
//		}
		
		holder.img.setImageBitmap(itemdata[position].imagebuffer);
		
		
//		holder.title.setText(tools.getDateString(itemdata[position].endDate));
		holder.title.setText(tools.getDateString(itemdata[position].beginDate,itemdata[position].endDate));
		holder.info.setText(itemdata[position].text);
		
		holder.checkbox.setId(position + 1000);
		holder.checkbox.setChecked(itemdata[position].isSelect);
		holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				
				int id = buttonView.getId()-1000;
//				boolean isAnySelected = false;
//				System.out.println("ID:"+id+"["+isChecked+"]");
				itemdata[id].isSelect = isChecked;
				((willmemo)context).setButtonState(itemdata);			
			}
			
		});
		
//        final News news = favoriteNewsList.get(position);  
//        holder.textView.setText(news.getTitle());  
//        holder.delete.setOnClickListener(new OnClickListener()  
//        {  
//            @Override  
//            public void onClick(View v)  
//            {  
//                // favoriteNewsList是listview List型的的数据源  
//                favoriteNewsList.remove(position);  
//                adapter.notifyDataSetChanged();  
//            }  
//        });
//		holder.viewBtn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				showInfo();					
//			}
//		});
		
		
		return convertView;
	}
	public void notifyDataSetChanged(Itemdata[] args2) {
		// TODO Auto-generated method stub
		itemdata = args2;
		super.notifyDataSetChanged();
	}

	
}
