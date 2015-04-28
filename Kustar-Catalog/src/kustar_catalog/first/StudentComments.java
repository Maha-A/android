package kustar_catalog.first;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class StudentComments extends Activity{

String id;
String extra;	
		Database database= new Database(this);
		ListView listView ;
		EditText t;
		List<String> RE;
		@Override
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.comments);
		     extra = new String();
		    extra= getIntent().getStringExtra("Course_ID");
		   // final String course = new String (extra);
		    Button b =(Button)findViewById(R.id.com);
		     t = (EditText)findViewById(R.id.editText1);
		     id = new String();
		     id =  getIntent().getStringExtra("Student_Id");
		     Log.d("THE ID I Get IN COMMENTS", id);
		    extra.trim();
		    listView = (ListView) findViewById(R.id.list);
		    // listView1 = (ListView) findViewById(R.id.list2);
		 RE = new ArrayList<String>();
		   
		    Cursor c=database.GetCommentsStudents(extra);
		    c.moveToFirst();
		    Log.d("Num of student comments", c.getCount()+"");
		    
		    
		    
		    if (c.getCount()>0){
		    while (!c.isAfterLast()) {
		    	
		    	String line = c.getString(0);
		    	if (!line.equals("null"))
		    	RE.add(line);
		    	
		    	c.moveToNext();
		    	
		                            }
		    }
		    c.close();
		    
		    
		    b.setOnClickListener(new OnClickListener ()
		    {
		    	public void onClick(View v)
		    	{ 
		    		//get the user name and id entered by the user
		    		//RE.add(t.getText().toString());
		    		database.updateComment(t.getText().toString(), id, extra);
		    	if (database.updateComment(t.getText().toString(), id, extra) < 1)
		    		{
		    		Toast.makeText(getApplicationContext(), "You can't add a comment if didn't take this course" ,
		    			     Toast.LENGTH_SHORT).show();
		    			   
		    		}
		    	else
		    	{
		    		RE.add(t.getText().toString());}

		    	
		    	}
		    });    
		    
		  
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			      android.R.layout.simple_list_item_1, android.R.id.text1, RE);

		 listView.setAdapter(adapter); 
		   
		   // c.moveToPosition(0);
		   // Log.d("QUERY RESULT",c.getString(0)+ " "++" "+c.getString(2) );
		    
		    

		}
	
}
