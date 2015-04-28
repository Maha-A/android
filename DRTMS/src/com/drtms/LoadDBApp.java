package com.drtms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.drtms.R;


public class LoadDBApp extends Activity {
	
	private String url = "http://ediabetes.biz/testing/connect8.php";
	
	private String jsonResult;
	DataBase database;
	String profile;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new DataBase(this);
        
          profile = getIntent().getStringExtra("profileNumber");
         
      	 accessWebService(url);
      	 
      	
		 
		 
		 Intent i = new Intent ( LoadDBApp.this, LoadDBAdv.class );
			i.putExtra("profileNumber", profile);
			startActivity(i); 
			
			
        
	}
	
	
	
	void Load()
	{
		String user=new String();
		String flag=new String();
		String first=new String();
		String last=new String();
		String phone=new String();
		String mobile = new String ();
		String DOB=new String();
		String gender=new String();
		String doctorId=new String();
		String timeApp = new String();
		boolean check= false;
	
	   String mark = new String();
	   String timemark = new String();
	   String timemark2 = new String();
		
		Log.d("no am going to try","the appoin");

		try {
			JSONObject jsonResponse = new JSONObject(jsonResult);
			JSONArray jsonMainNode = jsonResponse.optJSONArray("appointments");
			
			Log.d("length appointments",jsonMainNode.length()+"");
		    for (int i = 0; i < jsonMainNode.length(); i++) 
			   {			 
		    	JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

		    	if (jsonChildNode.optString("profileNumber").equals(profile))
		    	{
		    		timeApp = jsonChildNode.optString("time");
		    		Log.d("timeApp",timeApp );
		    		
		    		Date time=new Date();
		    		Date timeNow= new Date();
		            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		            String app =timeApp;
		            try {
						time= fmt.parse(app);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		if (time.after(timeNow))
					database.insertAppointment(timeApp);
					
		    	}
			   }
		    Cursor c = database.getAppointment();
		    Log.d("NUMBER OF ROWS IN APPOINTMENT",c.getCount()+"");

			}
			catch (JSONException e) 
			{
				   Toast.makeText(getApplicationContext(), "Error" + e.toString(),
				     Toast.LENGTH_SHORT).show();
			}
		
		Log.d("no am going to try","the tests");
		
		//database.insertAppointment("2013-12-03 12:56:00");

		LoadNoti();
	}
	
	void LoadNoti()
	{
		int i=0;
		 Cursor c = database.getAppointment(); 
		 Log.d("length of app cursor",c.getCount()+"");
         c.moveToFirst();
         Date time=new Date();
         SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
while(!c.isAfterLast()){
	
        Log.d("I", i+"");
         String app = c.getString(0);
       try{
      	 time = fmt.parse(app); 
           Log.d("time after set", time+"");
       }
       catch (ParseException e) {
           e.printStackTrace();
       }
          Calendar Calendar_Object = Calendar.getInstance();
          Calendar_Object.setTime(time);
          Log.d("time after becoming calander", Calendar_Object+"");

          Calendar_Object.add(Calendar.HOUR, -2);
          
          Log.d("time after becoming calander -2", Calendar_Object+"");
          Intent myIntent = new Intent(LoadDBApp.this, AlarmReceiver.class);

          PendingIntent pendingIntent = PendingIntent.getBroadcast(LoadDBApp.this,
                          0, myIntent, 0);

          AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
          alarmManager.set(AlarmManager.RTC, Calendar_Object.getTimeInMillis(),pendingIntent);
          c.moveToNext(); 
          
 i++; 
}	
	}
	
	private class JsonReadTask extends AsyncTask<String, Void, String> 
	{
		  @Override
		  protected String doInBackground(String... params) 
		  {
		   HttpClient httpclient = new DefaultHttpClient();
		   HttpPost httppost = new HttpPost(params[0]);
		   try {
		    HttpResponse response = httpclient.execute(httppost);
		    jsonResult = inputStreamToString(
		      response.getEntity().getContent()).toString();
		       }

		   catch (ClientProtocolException e) 
		   {
		    e.printStackTrace();
		   } catch (IOException e) 
		   {
		    e.printStackTrace();
		   }
		   return null;
		  }
		  
		 private StringBuilder inputStreamToString(InputStream is) 
		  {
			   String rLine = "";
			   StringBuilder answer = new StringBuilder();
			   BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			   try {
			    while ((rLine = rd.readLine()) != null) {
			     answer.append(rLine);
			    }
			   }

			   catch (IOException e) {
			    // e.printStackTrace();
			    Toast.makeText(getApplicationContext(),
			      "Error..." + e.toString(), Toast.LENGTH_LONG).show();
			   }
			   return answer;
			 
		  }

			  @Override
			  protected void onPostExecute(String result) {
				
				Load();
			  }
	}
	
	public void accessWebService(String urll) {
		
		  JsonReadTask task = new JsonReadTask();
		  // passes values for the urls string array
		  task.execute(new String[] { urll });		  
		 }

	
}

	
