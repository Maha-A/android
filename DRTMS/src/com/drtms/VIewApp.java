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

public class VIewApp extends Activity {

DataBase database= new DataBase(this);
ListView listView ;
ListView listView1 ;


protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rowlayout);
    //String ComingFrom=new String();
    // Get ListView object from xml
    //listView = (ListView) findViewById(R.id.list);
    //listView1 = (ListView) findViewById(R.id.list2);
   //List<String> RE = new ArrayList<String>();
  // List RM = new ArrayList();
    //List RE = new ArrayList();
   // ComingFrom = getIntent().getStringExtra("Flag");
    
    
  
      Cursor c=database.getAppointment();
      Log.d("column index app time",c.getColumnIndex("time")+"");

    //  RE.add("Time");
      c.moveToFirst();
      
      int i = 0;
      while (!c.isAfterLast()) {
      	
      	//String line = c.getString(0);
      	//RE.add(line);
    
      	//View rowView = inflater.inflate(R.layout.results, )
       // ImageView iv = new ImageView(this);
       //iv.setImageResource(R.drawable.user);
      //  RM.add(iv);
        //listView1.addView(iv);
      	
      	c.moveToNext();
      	
      	i++;
                              }
      c.moveToFirst();
      String[] values = new String[i];
      int j=0;
      while (!c.isAfterLast()) {
        	
        	String line = c.getString(0);
        	values[j]= line;
        	c.moveToNext();
        j++;
                                }
      View convertView = null;////
      ViewGroup parent = null;////
      MySimpleArrayAdapter A = new MySimpleArrayAdapter(this,values);
      A.getView(1, convertView, parent);
     // setListAdapter(A);
  // for(int x=0; x<j; x++)
   //  listView.addView(A.getView(x, convertView, parent));
  
      
      
      c.close();

   // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
     // android.R.layout.simple_list_item_1, android.R.id.text1, RE);

    
    

   // ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, android.R.id.text1, RM);
    // Assign adapter to ListView
 //  // listView1.setAdapter(adapter1);
    
    // ListView Item Click Listener
  /*  listView.setOnItemClickListener(new OnItemClickListener() {

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

     }); */
}
    
 
 


}
