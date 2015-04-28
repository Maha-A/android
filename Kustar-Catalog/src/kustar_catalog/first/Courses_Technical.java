package kustar_catalog.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Courses_Technical extends Activity {

	String id;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.twobuttons);
        
        Button courses = (Button)findViewById(R.id.button1);
        Button technicals = (Button)findViewById(R.id.button2);
id = new String();
id = getIntent().getStringExtra("ID");

        courses.setOnClickListener(new OnClickListener ()
        {	public void onClick(View v)
        	{     		
        	Intent i = new Intent ( Courses_Technical.this, Courses.class );
        	i.putExtra("ID", id);
        	startActivity(i);
        	}
        }); 
        
        technicals.setOnClickListener(new OnClickListener ()
        {	public void onClick(View v)
        	{     		
        	Intent i = new Intent ( Courses_Technical.this, Courses.class );
        	i.putExtra("ID", id);
        	startActivity(i);
        	}
        }); 
}
	
	
	
	
	
	
}
