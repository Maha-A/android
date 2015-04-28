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
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	
	    public void onCreate(Bundle m) {
        super.onCreate(m);
        
        setContentView(R.layout.choice);
        
        Button lookup = (Button)findViewById(R.id.ok);
        Button register = (Button)findViewById(R.id.ok2);
        
        lookup.setOnClickListener(new OnClickListener ()
        {
        	
        	public void onClick(View v)
        	{ 
        		Intent intent = new Intent(MainActivity.this, Lookup.class);
		        startActivity(intent); 
        	}
        });
        
  //TO-DO add registration Activity      
        register.setOnClickListener(new OnClickListener ()
        {
        	
        	public void onClick(View v)
        	{ 
        		Intent intent = new Intent(MainActivity.this, Register.class);
		        startActivity(intent); 
        	}
        }); 
        
        
                
	}
	
	
	
}