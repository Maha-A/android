package com.drtms;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.*;
import com.drtms.R;

public class ViewResult extends Activity {
DataBase database= new DataBase(this);
ListView listView ;
//ListView listView1 ;


protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.results);
    String ComingFrom=new String();
    // Get ListView object from xml
    listView = (ListView) findViewById(R.id.list);
   // listView1 = (ListView) findViewById(R.id.list2);
   List<String> RE = new ArrayList<String>();
  // List RM = new ArrayList();
    //List RE = new ArrayList();
    ComingFrom = getIntent().getStringExtra("Flag");
    
    
    if (ComingFrom.equals("test")){
    	
    Cursor c=database.getGlucoseTest();
    RE.add("Result               Time");
    c.moveToFirst();
    while (!c.isAfterLast()) {
    	
    	String line = c.getString(0)+"               "+c.getString(1);
    	RE.add(line);
    	
    	c.moveToNext();
    	
                            }
    c.close();
   
    }
    
    
  if (ComingFrom.equals("med")){
    	
        Cursor c=database.getMedication();
        RE.add("Name     Dosage       description");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Log.d("column med",c.getString(1));

        	String line = c.getString(1)+"     "+c.getString(3)+" "+c.getString(4)+"     "+c.getString(2);
        	RE.add(line);
        	c.moveToNext();
        	
                                }
        c.close();
    }
    
  
  if (ComingFrom.equals("app")){
  	
      Cursor c=database.getAppointment();
      Log.d("column index app time",c.getColumnIndex("time")+"");

      RE.add("Time");
      c.moveToFirst();
      
     // int i = 0;
      while (!c.isAfterLast()) {
      	
      	String line = c.getString(0);
      //  Log.d("this is to check",c.getString(0));

      	RE.add(line);
      	
      	
    
      	//View rowView = inflater.inflate(R.layout.results, )
       // ImageView iv = new ImageView(this);
       //iv.setImageResource(R.drawable.user);
      //  RM.add(iv);
        //listView1.addView(iv);
      	
      	c.moveToNext();
      	
      //	i++;
                              }
      //c.moveToFirst();
      //String[] values = new String[i];
      //int j=0;
      //while (!c.isAfterLast()) {
        	
        //	String line = c.getString(0);
        	//values[j]= line;
        	//c.moveToNext();
        //j++;
                                //}
     // View convertView = null;////
      //ViewGroup parent = null;////
      //MySimpleArrayAdapter A = new MySimpleArrayAdapter(this,values);
      //A.getView(0, convertView, parent);
     // setListAdapter(A);
  // for(int x=0; x<j; x++)
   //  listView.addView(A.getView(x, convertView, parent));
  
      
      
      c.close();
  }
  
  
  
  
  if (ComingFrom.equals("advice")){
    	
        Cursor c=database.getAdvices();
        Log.d("column index app time",c.getColumnIndex("advice")+"");

       RE.add("Advice");
        c.moveToFirst();
        while (!c.isAfterLast()) {
        	
        	String line = c.getString(1);
        	RE.add("*" + line);
            //line = c.getString(0);
        	//RE.add(line);            
        	c.moveToNext();
        	
                                }
        c.close();
    }
  
  
  
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
      android.R.layout.simple_list_item_1, android.R.id.text1, RE);

    
    

   // ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, android.R.id.text1, RM);
    // Assign adapter to ListView
    listView.setAdapter(adapter); 
   // listView1.setAdapter(adapter1);
    
    // ListView Item Click Listener
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
              .show();
         
          }

     }); 
}
    
 
    
   

    
    
    
  
        
    
    
    
    
    
}

