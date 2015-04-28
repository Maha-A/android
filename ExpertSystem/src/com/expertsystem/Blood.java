package com.expertsystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Blood extends Activity{
	
//	String[] arrayS ;//= extras.getStringArray("Symptoms");

	int[] test; 
	EditText hg ;
	EditText creatinen;
	EditText glu;
	EditText neut;
	EditText bas;
	CheckBox dia;
	CheckBox heart;
	int [] family;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fillingresults);
//		EditText hg = (EditText)findViewById(R.id.editText1);
//		EditText creatinen = (EditText)findViewById(R.id.editText2);
//		EditText glu = (EditText)findViewById(R.id.editText3);

		Button buttonNext =  (Button)findViewById(R.id.button2);

		family= new int [2] ;
        hg = (EditText)findViewById(R.id.editText1);
		creatinen = (EditText)findViewById(R.id.editText2);
		glu = (EditText)findViewById(R.id.editText3);
		neut = (EditText)findViewById(R.id.EditText5);
		bas = (EditText)findViewById(R.id.EditText4);
		 dia=(CheckBox)findViewById(R.id.checkBox99);
		 heart=(CheckBox)findViewById(R.id.checkBox88);
		
		 
		
		Log.d("Yo!", "in 1");

		Bundle b=this.getIntent().getExtras();
		Log.d("Yo!", "in 2");

		String[] arrayS = b.getStringArray("Symptoms");
		Log.d("ARRAY!", arrayS.length+"");

	// arrayS = getIntent().getStringArrayExtra("Symptoms");

		for(int j=0;j<8;j++)
		{
			Log.d("Yo!", arrayS[j]+"***");
		}
		test = new int[10];		Log.d("Yo!", "in 4");
		boolean g1 = isInArray("Headache",arrayS);
		boolean g2 = isInArray("Dizzy",arrayS);
		boolean g3 = isInArray("Shortness",arrayS);
		boolean g4 = isInArray("Retention",arrayS);
		boolean g5 = isInArray("Throat",arrayS);
		boolean g6 = isInArray("Fever",arrayS);
		boolean g7 = isInArray("Itching",arrayS);
		boolean g8 = isInArray("Swelling",arrayS);
		//TextView txt = (TextView)findViewById(R.id.textView1);
		//txt.setText(""+g+"   "+r);
		
		hg.setEnabled(false);
		glu.setEnabled(false);
		creatinen.setEnabled(false);
		neut.setEnabled(false);
		bas.setEnabled(false);
		
		hg.setBackgroundColor(Color.BLACK);
		glu.setBackgroundColor(Color.BLACK);
		creatinen.setBackgroundColor(Color.BLACK);
		neut.setBackgroundColor(Color.BLACK);
		bas.setBackgroundColor(Color.BLACK);

		
		if(g1 && g2)
		{
				Log.d("Yo!", "in 6");
				//test[0] = 1;
				hg.setEnabled(true);
				glu.setEnabled(true);
				hg.setBackgroundColor(Color.GRAY);
				glu.setBackgroundColor(Color.GRAY);
				//creatinen.setEnabled(false);
				//neut.setEnabled(false);
				//bas.setEnabled(false);
		}
		if(g3 && g4 )
		{
			//hg.setEnabled(false);
			//glu.setEnabled(false);
			creatinen.setEnabled(true);
			creatinen.setBackgroundColor(Color.GRAY);

			//neut.setEnabled(false);
			//bas.setEnabled(false);
		}
		
		 if(g5 && g6)
		{
				Log.d("checkin if g5 and g6:","..... ");

			//hg.setEnabled(false);
			//glu.setEnabled(false);
			//creatinen.setEnabled(false);
			neut.setEnabled(true);
			neut.setBackgroundColor(Color.GRAY);

			//bas.setEnabled(true);
			
		}
		 if(g7 && g8)
		 {
			bas.setEnabled(true); 
			bas.setBackgroundColor(Color.GRAY);

		 }
		 
		 
		
		
		buttonNext.setOnClickListener(new OnClickListener ()
		{
			
			public void onClick(View v)
			{ 
			    checkFamily();
				double[] a = new double[15];
				a = analyzeResults();
				Intent i = new Intent ( Blood.this, Results.class );
				Bundle b=new Bundle();
				
				b.putDoubleArray("analysis", a);
				b.putIntArray("family", family);
				i.putExtras(b);
				startActivity(i);        		
			}
		});

	}
	
	void checkFamily()
	{
		if(dia.isChecked())
			family[0]=1;
		else
			family[0]=0;
		if(heart.isChecked())
			family[1]=1;
		else
			family[1]=0;
		
	}

	boolean isInArray(String a,String []arrayS)
	{
		Log.d("Yo!", "in 5");

		for(int i=0; i<arrayS.length; i++)
		{
			if(arrayS[i].compareTo(a) == 0)
			{	Log.d("Yo!", "in 8");
				return true;

			}
			

		}
		Log.d("Yo!", "in 10");

		return false;	
	}
	
	double[] analyzeResults(){
		double[] a = new double[15];
		
		if(!(hg.getText().toString().length()==0))
		{
			a[0] = Double.parseDouble(hg.getText().toString());
		}
		else if(hg.getText().toString().length()==0)
		{
			a[0] = -1;
		}
		
		
		 if(!(glu.getText().toString().length()==0))
		{
			 //Log.d("THE GLU RESUKT IS:", glu.getText().toString());
			 Log.d("THE EMPTY ID: ",creatinen.getText()+"");
			a[2] = Double.parseDouble(glu.getText().toString());
		}
		else if(glu.getText().toString().length()==0)
		{
			a[2] = -1;
		}
		 
		 
		 if(!(creatinen.getText().toString().length()==0))
		{
			 Log.d("Creatinen is:Not", "NULL");
			a[1] = Double.parseDouble(creatinen.getText().toString());
		}
		else if(creatinen.getText().toString().length()==0)
		{
			Log.d("Creatinen is:", "NULL");
			a[1] = -1;
		}
		 
		 if(!(neut.getText().toString().length()==0))
		{
			a[3] =Double.parseDouble(neut.getText().toString());
		}
		else if(neut.getText().toString().length()==0)
		{
			Log.d("Neut is:", "NULL");

			a[3] = -1;
		}
		 if(!(bas.getText().toString().length()==0))
		{
			a[4] =Double.parseDouble(bas.getText().toString());
		}
		else if(bas.getText().toString().length()==0)
		{
			Log.d("BAS is:", "NULL");

			a[4] = -1;
		}
		
		return a;
		
	}

	
}
