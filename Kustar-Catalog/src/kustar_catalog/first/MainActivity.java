package kustar_catalog.first;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity{

	
	Database database;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       database=new Database(this);
 //if a student profile doesn't exit 
        if(!database.isProfileExsists())
        {
        	Log.d("location", "i don't have a db");
        	Intent i=new Intent(MainActivity.this,LogIn.class);
        	startActivity(i);
        	finish();
        	
        }
	//if we have a student profile
        else
        {
        	Cursor c = database.getStudent_table();
        	
        	Log.d("I have a database","the count is"+c.getCount());
    Intent i = new Intent ( MainActivity.this, ChoiceDepartments.class );
			startActivity(i); 
        }
	
    }}
    