package com.drtms;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class ChartDemo extends Activity {
	
    public IDemoChart[] demochart = new IDemoChart[] {new MultipleTemperatureChart()};
    
    Intent intent = null;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Log.d("here I am","***********");
		
		DataBase database= new DataBase(this);//database.getGlucoseTest()
		Cursor c=database.getGlucoseTest();
		
		Log.d("here I am 2","***********");
		//String lastvalue []=new String[9];
		int i=0;
		
	    c.moveToFirst();*/
	    /*if (c.getCount()<10){
	    	String firstvalues []=new String[c.getCount()];
	    	 while (i<c.getCount()) {
	    		 
	    		 firstvalues[i] = c.getString(0);
	    		 c.moveToNext();
	    		 i++;
	    	
	    	 int value []=new int[i];
	    	 for(int j=0; j<i; j++)
	 		{
	 			value[j] = Integer.parseInt(firstvalues[j]);
	 		}
	    intent = demochart[0].execute(this);
		intent.putExtra("Results", value);
	    	
	    	
	                            }
	   
	    }
	    */
	    
	    /* if (c.getCount()>10){
	    	String firstvalues []=new String[c.getCount()];
	    	 while (i<10) {
	 	    	
	    		firstvalues[i] = c.getString(0);
	 	    	c.moveToNext();
	 	    	i++;
	 	                            }
	    	 int value []=new int[i];
	    	 for(int j=0; j<i; j++)
	 		{
	 			value[j] = Integer.parseInt(firstvalues[j]);
	 		}
	    	 Log.d("here I am 3","***********");
	   // 
	    intent = new Intent ( ChartDemo.this, MultipleTemperatureChart.class );
		intent.putExtra("Results", value);
		Log.d("here I am 4","***********");
	    }
	   
	    c.close();*/
	    
	    intent = demochart[0].execute(this);
		startActivity(intent);
	}
}