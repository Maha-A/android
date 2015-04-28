package com.example.directotyapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class mylist extends Activity {
	
	String DepName = new String();
	String Fname = new String();
	String Lname = new String();
	String email = new String();
	String title = new String();
	String phonenumber = new String();
	ListView listView ;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        listView = (ListView) findViewById(R.id.mylist);
        List<String> RE = new ArrayList<String>();
        
        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("jsonArray");


        
        try {


            JSONArray array = new JSONArray(jsonArray);



 		   for (int i = 0; i < array.length(); i++) 
 		   {
 			     

 		    JSONObject jsonChildNode = array.getJSONObject(i);
 		    
 		     DepName  = jsonChildNode.optString("DepName");
		     Fname = jsonChildNode.optString("FirstName");
		     Lname = jsonChildNode.optString("LastName");
    	     title  = jsonChildNode.optString("Title");
    	     phonenumber = jsonChildNode.optString("PhoneNumber");
    	     email = jsonChildNode.optString("Email"); 
 		  String line = "Name: "+Fname+" "+Lname;
 		  RE.add(line);
 		  line= "Department: "+DepName;
 		  RE.add(line);
    	  line = "Title: "+title;
 		  RE.add(line);
 		  line = "Email: "+email;
 		  RE.add(line);
 		  line = "Phone Number: "+phonenumber;
 		  RE.add(line);
 		  RE.add("*****************************");}  
 		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
 			      R.layout.row, R.id.list_content, RE);
 		    listView.setAdapter(adapter); 
 		 listView.setOnItemClickListener(new OnItemClickListener() {
 	          @Override
 	          public void onItemClick(AdapterView<?> parent, View view,
 	             int position, long id) {
 	           // ListView Clicked item index
 	           int itemPosition     = position;
 	           // ListView Clicked item value
 	           String  itemValue    = (String) listView.getItemAtPosition(position); 	              
 	            // Show Alert 
 	            Toast.makeText(getApplicationContext(),
 	              "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
 	              .show();}  });  }
 	     catch (JSONException e) {
            e.printStackTrace();
        }
        
}
}