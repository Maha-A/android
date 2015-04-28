package com.drtms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;


public class CalanderView extends Activity {
	//private static final Context Context = null;
	CalendarView calendar; 
	DataBase database= new DataBase(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.calander);
	calendar = (CalendarView)findViewById(R.id.calendar);
	
	//calendar.
	calendar.setOnDateChangeListener(new OnDateChangeListener(){
	 
	public void onSelectedDayChange(CalendarView view,
	int year, int month, int dayOfMonth) {
	Toast.makeText(getApplicationContext(),
	dayOfMonth +"/"+month+"/"+ year,Toast.LENGTH_LONG).show();}});
	
	
	Cursor c= database.getAppointment();
	c.moveToFirst();
	String line = new String();
	while (!c.isAfterLast()){
	 line = c.getString(0);
	Date time=new Date();
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 	 try {
 		 Log.d("I am in the ", "try and catch");
		time = fmt.parse(line);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    
	//calendar.set(time.getTime());
	
	c.moveToNext();
	}
	
	c.close();
	
	}
	}