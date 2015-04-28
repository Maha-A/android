package com.drtms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.drtms.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class LogIn extends Activity{
	
	String userName = new String();
	String passWord=new String();
	private String jsonResult;
	private String url = "http://ediabetes.biz/testing/connect2.php";
	boolean check= false;
    DataBase database;
    //DB load=new LoadDB();
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //database= new DataBase(this);
        
        database=new DataBase(this);
        
        
        setContentView(R.layout.sign);
        	
        Button ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new OnClickListener ()
        {
        	
        	public void onClick(View v)
        	{ 
        		
        		EditText user = (EditText)findViewById(R.id.user);
                EditText pass=(EditText)findViewById(R.id.password);
        		userName=user.getText().toString();
        		passWord= pass.getText().toString();
        		Log.d("hi am ", "calling the web service");
        		accessWebService();        		
        	}
        });
       
        
        
        
        
	}

	
	
	void CheckDB(String userName, String passWord)
	{
		String user=new String();
		String flag=new String();
		String pass=new String();
		
		String first=new String();
		String last=new String();
		String phone=new String();
		String mobile = new String ();
		String DOB=new String();
		String gender=new String();
		String doctorId=new String();
		int count =0;
		try {
			
			   JSONObject jsonResponse = new JSONObject(jsonResult);
			   JSONArray jsonMainNode = jsonResponse.optJSONArray("patients");
			   for (int i = 0; i < jsonMainNode.length(); i++) 
			   {
			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
			     user = jsonChildNode.optString("profileNumber");
			     pass = jsonChildNode.optString("password");
			    if(userName.equals(user) )
			    {
			    	if ( passWord.equals(pass))
			    	{
			    		check=true;	
			    		first=jsonChildNode.optString("firstname");
			    		 last=jsonChildNode.optString("lastname");
			    		 phone=jsonChildNode.optString("phoneNumber");
			    		 mobile = jsonChildNode.optString("mobileNumber");
			    		 DOB=jsonChildNode.optString("DOB");
			    		 gender=jsonChildNode.optString("gender");
			    		 doctorId=jsonChildNode.optString("doctorID");
			 		    Log.d("NAme ", first);
					    Log.d("DOB", DOB);
					    Log.d("mobile", mobile);

				    		flag=user;
				    		database.insertPatient(flag, first, last,phone,mobile,DOB, gender,doctorId);
					    	
			    		
			    	}
	     		}	
			    }
			   
			   
			  } 
		
		catch (JSONException e) 
		{
			   Toast.makeText(getApplicationContext(), "Error" + e.toString(),
			     Toast.LENGTH_SHORT).show();
		}
		
		
		
		if (check)
		{
			
			Intent i = new Intent ( LogIn.this, LoadDBMed.class );
			i.putExtra("profileNumber", flag);
    		Log.d("hi am ", "leaving login for load");
			startActivity(i); 
			finish();
		}
		else
		{
			
			   Toast.makeText(getApplicationContext(), 
					   "You have entered a wrong profile Number or password plese try again",
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
				  
			CheckDB(userName,passWord);
			  }
			 }// end async task

	
	
	public void accessWebService() {
		  JsonReadTask task = new JsonReadTask();
		  // passes values for the urls string array
		  task.execute(new String[] { url });
		 }
	
	
	
	public void onBackPressed()
	{
	    
			//DataBase da
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(intent);
	        Context context = getApplicationContext();
	        
	    context.deleteDatabase("diabetes");
	    
	}
	
	
	
	
}
	



