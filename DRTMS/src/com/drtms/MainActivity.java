package com.drtms;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity{

	
	DataBase database;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new DataBase(this);
        if(!database.isProfileExsists())
        {
        	Log.d("location", "i don't have a db");
        	Intent i=new Intent(MainActivity.this,LogIn.class);
        	startActivity(i);
        	finish();
        	
        }
	
        else
        {
        	Log.d("location", "i  have a db");
        	//********stop sending data with the intent u can read it from their
        	Cursor c = database.getPatient();
        	c.moveToFirst();
        	int m=c.getColumnIndex("profileNumber");
        	String massege=c.getString(m);
        	Log.d("cursor massage",massege);
        	Intent i = new Intent ( MainActivity.this, Choice.class );
			i.putExtra("profileNumber",massege );
			startActivity(i); 
        }
	
    }
    }