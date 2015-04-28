package com.drtms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.drtms.R;


public class LoadDBTest extends Activity {
	
	private String url = "http://ediabetes.biz/testing/connect7.php";
	

	private String jsonResult;
	DataBase database;
	String profile;
	int x=0;
	int m=0;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new DataBase(this);
        /*Cursor c = database.getPatient();
		c.moveToFirst();
		int index=c.getColumnIndex("profileNumber");
		 profile= c.getString(index);*/
          profile = getIntent().getStringExtra("profileNumber");
         
      	 accessWebService(url);
      	
		 
		 
		 
		 Intent i = new Intent ( LoadDBTest.this, Choice.class );
			i.putExtra("profileNumber", profile);
			startActivity(i); 
			
			
        
	}
	
	
	
	
 void Load(){
	  
	 
	  String time = new String();
		String result = new String();
		String level = new String();
		   

		try {
		JSONObject jsonResponse = new JSONObject(jsonResult);
		JSONArray jsonMainNode = jsonResponse.optJSONArray("GlucoseTests");


		Log.d("length tests",jsonMainNode.length()+"");

	    for (int i = 0; i < jsonMainNode.length(); i++) 
		    {			 
	    	JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
	   
	    	Log.d("profileNumber", jsonChildNode.optString("profileNumber"));
	    	if (jsonChildNode.optString("profileNumber").equals(profile))
	    	{
	    		
	    		Log.d("profile", profile);
	    		
	    		time = jsonChildNode.optString("time");	    		
				result = jsonChildNode.optString("result");
				level=jsonChildNode.optString("level");
				database.insertGlucoseTests(result, time, level);
				
	    	}
		    }
		    Cursor c = database.getGlucoseTest();
		    Log.d("NUMBER OF ROWS IN Glucose",c.getCount()+"");
		    }
		
		catch (JSONException e) 
		{
			   Toast.makeText(getApplicationContext(), "Error" + e.toString(),
			     Toast.LENGTH_SHORT).show();
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
			  
			  
			  
			 // end async task
	}
	
	
	public void accessWebService(String urll) {
		
		  JsonReadTask task = new JsonReadTask();
		  // passes values for the urls string array
		  task.execute(new String[] { urll });
		
		  
		  
		 }
	
	
}

	
