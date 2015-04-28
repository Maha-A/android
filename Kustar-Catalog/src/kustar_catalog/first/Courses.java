package kustar_catalog.first;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Courses extends Activity{
	Database database= new Database(this);
	 ListView listview;
	 String ID ;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.courseslist);
        
        listview = (ListView)findViewById(R.id.listView1);
        
        List<String> RE = new ArrayList<String>();
         ID = new String();
        ID = getIntent().getStringExtra("ID");
        Cursor c=database.getCourse_table();
        c.moveToFirst();

        while (!c.isAfterLast()) {
        Log.d("my postion is",	c.getPosition()+"");
        	String line =c.getString(1);
        	RE.add(line);
        	c.moveToNext();
        
                          }
        c.close();
               
        	 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        	      android.R.layout.simple_list_item_1, android.R.id.text1, RE);
        listview.setAdapter(adapter); 

        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
            	Cursor m=database.getCourse_table();
              m.moveToPosition(position);
            	Intent i = new Intent ( Courses.this, Description.class );
            	i.putExtra("Course_ID", m.getString(0));
            	i.putExtra("ID", ID);
            	//Cursor x = database.GetCRN(m.getString(0),ID);
            	Log.d("I added",m.getString(0));
            	startActivity(i);       
            }

       }); 
        
            
            
            
 
	}
}
