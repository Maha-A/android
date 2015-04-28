package com.drtms;

import java.util.List;


import android.os.Bundle;
import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.drtms.R;

import android.content.Context;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import com.drtms.R;

public class Choice extends Activity implements OnItemClickListener{
    GridView gridview; 

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("now i am before set","content view");
        setContentView(R.layout.grid);  
        Log.d("now i am after set","content view");

        gridview=(GridView)findViewById(R.id.gridview);
        Log.d("now i am after set","grid by id");

         gridview.setAdapter(new ImageAdapter(this));
         Log.d("now i am after ","new adapter");

       gridview.setOnItemClickListener(this);
         Log.d("now i am after set","click ");

         }


 public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
 Intent i;
 final String profileNumber = getIntent().getStringExtra("profileNumber");

        switch(position)
        {
        case 0:
        //Start medication Activity
        	Intent m = new Intent ( Choice.this, ViewResult.class );
            m.putExtra("Flag", "med");
            startActivity(m); 
        break;
        case 1:
        //Start appointment Activity
        	Intent k = new Intent ( Choice.this, CalanderView.class );
            k.putExtra("Flag", "app");
            startActivity(k); 
        break;
        case 2:
        //start advice Activity
        	Intent e = new Intent ( Choice.this, ViewResult.class );
            e.putExtra("Flag", "advice");
            startActivity(e);
        break;
        case 3:
        //Start diet Activity
        	
        break;
        case 4:
        //Start testActivity
        	Intent l = new Intent ( Choice.this, GlucoseMeter.class );
            l.putExtra("profileNumber", profileNumber);
            startActivity(l); 
        break;
        }

        }


 public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK ) {
	    	
	    	Intent l = new Intent ( Choice.this, LogIn.class );
	    	Context context = getApplicationContext();
		    context.deleteDatabase("diabetes");
	    	startActivity(l); 
	    	
	    	return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}

            
            


        
}

///////////////


