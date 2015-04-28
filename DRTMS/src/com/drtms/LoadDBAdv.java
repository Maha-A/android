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
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.drtms.R;


public class LoadDBAdv extends Activity {
	
	private String url1 = "http://ediabetes.biz/testing/connect10.php";
	
	private String jsonResult;
	DataBase database;
	String profile;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new DataBase(this);
        
          profile = getIntent().getStringExtra("profileNumber");
         
      	 accessWebService(url1);
      	 
      	
		 
		 
		 Intent i = new Intent ( LoadDBAdv.this, LoadDBTest.class );
			i.putExtra("profileNumber", profile);
			startActivity(i); 
			
			
        
	}
	
	
	void Load(){
		  String timeAdvice = new String();
			String advice = new String();
			
			try {
				JSONObject jsonResponse = new JSONObject(jsonResult);
				JSONArray jsonMainNode = jsonResponse.optJSONArray("advices");
			    for (int i = 0; i < jsonMainNode.length(); i++) 
				   {			 
			    	JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

			    	if (jsonChildNode.optString("profileNumber").equals(profile))
			    	{
			    		
			    		timeAdvice = jsonChildNode.optString("time");
			    		Log.d("timeAdvice",timeAdvice );
						advice = jsonChildNode.optString("advice");					
						database.insertAdvice(timeAdvice, advice);
						
			    	}
				   }
			    Cursor c = database.getAdvices();
			    Log.d("NUMBER OF ROWS IN Advice",c.getCount()+"");
				}
				catch (JSONException e) 
				{
					   Toast.makeText(getApplicationContext(), "Error" + e.toString(),
					     Toast.LENGTH_SHORT).show();
				}
			
			Log.d("no am going to try","the medication");

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

	
