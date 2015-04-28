package com.example.directotyapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lookup extends Activity{
	
	

	String name = new String();
	String lastname = new String();
	String title = new String();
	String email = new String();
	String officeNum = new String();
	String phoneNum = new String();
	String department = new String();
	 JSONArray jsonMainNode = new JSONArray();
	 boolean check = false;
	private String jsonResult;
//	private String url = "http://10.10.129.227:8888/DirectoryService/test.php";//ku_auh_student
//	private String url = "http://192.168.1.135:8888/DirectoryService/test.php";//home	
	private String url = "http://192.168.43.213:8888/DirectoryService/test.php";//tethring
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        Button ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new OnClickListener ()
        {
        	
        	public void onClick(View v)
        	{ 
        		
        	EditText Qname = (EditText)findViewById(R.id.name);
        	EditText QLastName = (EditText)findViewById(R.id.lastName);
        	EditText QEmail = (EditText)findViewById(R.id.email);
        	EditText QPhoneNumber = (EditText)findViewById(R.id.phoneNumber);
        	EditText QTitle = (EditText)findViewById(R.id.title);
        	EditText Qdepartment = (EditText)findViewById(R.id.department);
        	
        	if((Qname.getText().toString().length()==0) && (QLastName.getText().toString().length()==0) && 
        			(QEmail.getText().toString().length()==0) && (QPhoneNumber.getText().toString().length()==0) && 
        			(Qdepartment.getText().toString().length()==0) && (QTitle.getText().toString().length()==0))
        	{
        		Toast.makeText(getApplicationContext(),
      			      "at least one field must be filled", Toast.LENGTH_LONG).show();
        		
        	}
        	
        	
        	
        	
        	else {
        	if(!(Qname.getText().toString().length()==0))
        	{
        		name=Qname.getText().toString();

        	}
        	else
        		name ="";
        	
        	if(!(QLastName.getText().toString().length()==0))
        	{
        		lastname=QLastName.getText().toString();

        	}
        	else
        		lastname="";
        	
        	if(!(QEmail.getText().toString().length()==0))
        	{
        		email=QEmail.getText().toString();
        	}
        	else
        		email ="";
        	
        	if(!(QPhoneNumber.getText().toString().length()==0))
        	{
        		phoneNum=QPhoneNumber.getText().toString();
        	}
        	else
        		phoneNum ="";
        	
        	
        	if(!(Qdepartment.getText().toString().length()==0))
        	{
        		department=Qdepartment.getText().toString();
        	}
        	else
        		department ="";
        	
        	
        	if(!(QTitle.getText().toString().length()==0))
        	{
        		title=QTitle.getText().toString();
        	}
        	else
        		title="";
        		accessWebService();   
        		
        	}	   		
        	}
        });
        
                
	}
	
	
	
	public void accessWebService() {
		  JsonReadTask task = new JsonReadTask();
		  // passes values for the urls string array
		  task.execute(new String[] { url });
		 }
	
	
	
	
	
	private class JsonReadTask extends AsyncTask<String, Void, String> 
	{
		  @Override
		  protected String doInBackground(String... params) 
		  {

		  
		   HttpPost httppost = new HttpPost(params[0]);
		   HttpParams httpParameters = new BasicHttpParams();
		   int timeoutConnection = 3000;
		   HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		    HttpClient httpclient = new DefaultHttpClient(httpParameters);
		   
		   try {

			   
			   List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("name", name));
		        nameValuePairs.add(new BasicNameValuePair("lname", lastname));
		        nameValuePairs.add(new BasicNameValuePair("title", title));
		        nameValuePairs.add(new BasicNameValuePair("phonenumber", phoneNum));
		        nameValuePairs.add(new BasicNameValuePair("email", email));
		        nameValuePairs.add(new BasicNameValuePair("department", department));
		        

		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			   
		    HttpResponse response = httpclient.execute(httppost);
			  HttpEntity entity = response.getEntity();
			  
		    if (entity != null) {
		    	Log.d("am actully not empty ","u think");
				  	check = true;
		    jsonResult = inputStreamToString(
		      response.getEntity().getContent()).toString();
		    }
		    
		    else
		    {
		    	Log.d("setting check","to fales");

		    	check = false;
		    	
		    }
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
			      "error" + e.toString(), Toast.LENGTH_LONG).show();
			   }
			   return answer;
			 
		  }

			  @Override
			  protected void onPostExecute(String result) {
				  
					  CheckDB();
				  
			  }
			 }// end async task

	
	
	
	
	void CheckDB()
	{	
	String fname=new String();
	String lname=new String();
	String title=new String();
	String DepName=new String();
	
	
	
	if(!check)
	{
	   

		Toast.makeText(getApplicationContext(),
			      "Connection error please try again later", Toast.LENGTH_LONG).show();
	}
	
	else {
	try {
		
		 Log.d("check","is true");
		 check=false;
		   JSONObject jsonResponse = new JSONObject(jsonResult);
		    jsonMainNode = jsonResponse.optJSONArray("EmpInfo");
		  

		     Intent intent = new Intent(Lookup.this, mylist.class);
		        intent.putExtra("jsonArray", jsonMainNode.toString());
		        startActivity(intent); 
		
		   
		   
		  } 
	
	catch (JSONException e) 
	{
		   Toast.makeText(getApplicationContext(), "No results found",
		     Toast.LENGTH_SHORT).show();
	}
	
	
	
	}	
	
	}
	
	
	


}
