package com.drtms;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.drtms.R;

public class ImageAdapter extends BaseAdapter {
private Context mContext;
public ImageAdapter(Context c)
{
mContext=c;
}
public int getCount() {
// TODO Auto-generated method stub
return mThumbIds.length;
}

public Object getItem(int position) {
// TODO Auto-generated method stub
return null;
}

public long getItemId(int position) {
// TODO Auto-generated method stub
return 0;
}

public View getView(int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
ImageView imageView;
if(convertView==null){
imageView=new ImageView(mContext);
imageView.setLayoutParams(new GridView.LayoutParams(400,400));
imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
imageView.setPadding(8,8,8,8);
}else
{
imageView=(ImageView)convertView;
}
imageView.setImageResource(mThumbIds[position]);
return imageView;

}
private Integer[] mThumbIds={
		 com.drtms.R.drawable.medication,
		 com.drtms.R.drawable.appointments,
		 com.drtms.R.drawable.advices,
		 com.drtms.R.drawable.diet,
		 com.drtms.R.drawable.test
		};

}
