package kustar_catalog.first;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Description extends Activity {


TableRow tr;
android.widget.TableRow.LayoutParams trparams;
TableLayout  layoutINNER;
Database database= new Database(this);
String extra;
 String studentid;
RatingBar rate; 
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.description);
     extra = new String();
     studentid = new String();

     String line = new String("The course Pre req are:\n");
     String id = new String();
     studentid = getIntent().getStringExtra("ID"); 
     extra = getIntent().getStringExtra("Course_ID");
     extra.trim();
    TextView textView1 = (TextView)findViewById(R.id.textView1);
    TextView textView2 = (TextView)findViewById(R.id.textView2);
    TextView textView3 = (TextView)findViewById(R.id.textView3);
    rate = (RatingBar) findViewById(R.id.ratingBar1);
    Button b = (Button)findViewById(R.id.bu);
    Button r = (Button)findViewById(R.id.rating);
   LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
            LayoutParams.WRAP_CONTENT);    
    
   Cursor c = database.GetDescription(extra);
   c.moveToFirst();
    if (c.getCount()>0)
    {
    textView1.setText(c.getString(0)+"\n"+c.getString(1));
    textView2.setText(c.getString(2));
    c.close();
    }
    
    
  
    
    c=database.GetPreReq(extra);
	c.moveToFirst();	
   if (c.getCount()>0)
   {
	   while (!c.isAfterLast()) {
	        
		    line = line+c.getString(0)+"\n"; 
	        	c.moveToNext();

	                          }
	     
   }  
   c.close();
   
   textView3.setText(line);
   

    
    trparams = new TableRow.LayoutParams(android.widget.TableRow.LayoutParams.WRAP_CONTENT, android.widget.TableRow.LayoutParams.WRAP_CONTENT);   
    layoutINNER = new TableLayout(this);
    layoutINNER.setLayoutParams(params); 
    
    c=database.GetComments(extra);
    Cursor m = database.getInstructor_table();
    m.moveToFirst();
    c.moveToFirst();
    if (c.getCount()>0){
    while(!c.isAfterLast())
    {
    	line =c.getString(0);      
    	id=c.getString(1);
    	id.trim();
    	Log.d("The id i have in c is", line+"??????");
        if(m.getCount()>0)
        {
        	while (!m.isAfterLast())
        	{
        		Log.d("My Id"+id, "the id in M"+m.getString(0));
        		if (m.getString(0).equals(id)){
        			line = line + m.getString(1)+m.getString(2);
        			}
        		m.moveToNext();
        	
           }
        
    	 Log.d("THE LINE2", line); 
    
    }    	
     createRow(line);
     c.moveToNext();
    }
    }
    m.close();
    c.close();
    
    
  
    LinearLayout main = (LinearLayout)findViewById(R.id.linearlayout);
    main.addView(layoutINNER);
    
  
    
    
   final String sss = new String(studentid); 
   b.setOnClickListener(new OnClickListener ()
    {
    	public void onClick(View v)
    	{ 
    		//get the user name and id entered by the user
    		Intent i = new  Intent (Description.this , StudentComments.class);
    		i.putExtra("Course_ID",extra);
    		i.putExtra("Student_Id", sss);
    		startActivity(i);
    	}
    });
    
   
   
   c=database.getrating(extra);
   float k =0;
   int foo=0;
   int p=0;
   c.moveToFirst();	
   if (c.getCount()>0)
   {
	   while (!c.isAfterLast()) {
		   if (!c.getString(0).equals("null")){
		    foo = Integer.parseInt(c.getString(0));
		    k=k+foo;
		    p++;}
       	c.moveToNext();

	                          }
	   k=k/p;
	   rate.setRating(k);
	     
   }  
   c.close();
   
   
   
   r.setOnClickListener(new OnClickListener ()
   {
   	public void onClick(View v)
   	{ 
   		//get the user name and id entered by the user
   		Intent i = new  Intent (Description.this , StudentRate.class);
   		i.putExtra("Course_ID",extra);
   		i.putExtra("Student_Id", sss);
   		startActivity(i);
   	}
   });
   
  
}
void createRow(String x)
{
    tr = new TableRow(this);

	TextView tv1 = new TextView(this);
    //TextView tv2=new TextView(this);
    
    tv1.setLayoutParams(trparams);
  //  tv2.setLayoutParams(trparams);
    tv1.setText(x);
    //tv2.setText("Hello2!");
    
    tr.addView(tv1);
    //tr.addView(tv2);
    layoutINNER.addView(tr);

}

}