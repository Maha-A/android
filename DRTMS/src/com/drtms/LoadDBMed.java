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

public class LoadDBMed extends Activity {
	
	
	private String url = "http://ediabetes.biz/testing/connect11.php";
	

	private String jsonResult;
	DataBase database;
	String profile;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new DataBase(this);
        
          profile = getIntent().getStringExtra("profileNumber");
         
      	 accessWebService(url);
      	 
      	
		 
		 
		 
		 Intent i = new Intent ( LoadDBMed.this, LoadDBApp.class );
			i.putExtra("profileNumber", profile);
			startActivity(i); 
			
			
        
	}
	
	
	
	
 
 void  Load(){
	 
		String MedicationID=new String();
		String MedName=new String();
	    String description=new String();
		String Dosage=new String();
		String DosageUnit=new String();
	    String MedStartDate=new String();
		String MedEndDate=new String();
		String MedAddedDate=new String();
		try {

			JSONObject jsonResponse = new JSONObject(jsonResult);
			JSONArray jsonMainNode = jsonResponse.optJSONArray("Medications");
			JSONArray jsonMainNode2 = jsonResponse.optJSONArray("MedicationsID");
			Log.d("length med",jsonMainNode.length()+"");
			Log.d("length medid",jsonMainNode2.length()+"");

		    for (int i = 0; i < jsonMainNode.length(); i++) 
			   {	
		    	
			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
			    if (jsonChildNode.optString("profileNumber").equals(profile))
		    	{	
			    	description=jsonChildNode.optString("description");
		    		 Dosage=jsonChildNode.optString("dosage");
		    		 DosageUnit=jsonChildNode.optString("dosageUnit");
		    	     MedStartDate=jsonChildNode.optString("medStartDate");
		    		 MedEndDate=jsonChildNode.optString("medEndDate");
		    		 MedAddedDate=jsonChildNode.optString("medAddedDate");
	    		     MedicationID=jsonChildNode.optString("medicationID");
	    		     
			    			 
	    		     for (int j = 0; j < jsonMainNode2.length(); j++) 
	  			   {
		    	      JSONObject jsonChildNode2 = jsonMainNode2.getJSONObject(j);
		    	
		    		if (jsonChildNode2.optString("medicationID").equals(MedicationID))
		    		{
		    			 MedName=jsonChildNode2.optString("name");	
		    			 database.insertMedication(MedicationID, MedName, description,
		    			 Dosage, DosageUnit, MedStartDate, MedEndDate, MedAddedDate);
		    			 Log.d("med name", MedName);
		    	   }
	  			   }		    	     			
		    	   }
			       }
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

	
