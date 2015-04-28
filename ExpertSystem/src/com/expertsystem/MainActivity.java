package com.expertsystem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends Activity {

	String [] symptoms = new String[8];
	CheckBox box1;
	CheckBox box2;
	CheckBox box3;
	CheckBox box4;
	CheckBox box5;
	CheckBox box6;
	CheckBox box7;
	CheckBox box8;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
Button buttonNext =  (Button)findViewById(R.id.button1);
 box1 = (CheckBox)findViewById(R.id.checkBox1);
 box2 = (CheckBox)findViewById(R.id.checkBox2);
 box3 = (CheckBox)findViewById(R.id.checkBox3);
 box4 = (CheckBox)findViewById(R.id.checkBox4);
 box5 = (CheckBox)findViewById(R.id.checkBox5);
 box6 = (CheckBox)findViewById(R.id.checkBox6);
 box7 = (CheckBox)findViewById(R.id.checkBox7);
 box8 = (CheckBox)findViewById(R.id.checkBox8);
buttonNext.setOnClickListener(new OnClickListener ()
{
	
	public void onClick(View v)
	{ 
		Log.d("Yo!", "in onclick");

		CheckSymptoms();
		Intent i = new Intent ( MainActivity.this, Blood.class );
		Bundle b=new Bundle();
		b.putStringArray("Symptoms", symptoms);
		i.putExtras(b);
		//i.putExtra("Symptoms",symptoms );
		startActivity(i);        		
	}
});

		
	}

	void CheckSymptoms()
	{
		Log.d("Yo!", "in checkinggggg1111111111");

		if (box1.isChecked())
			symptoms[0]= "Dizzy";
		else
			symptoms[0]="no";
		if (box2.isChecked())
			symptoms[1]= "Headache";
		else
			symptoms[1]="no";
		if (box3.isChecked())
			symptoms[2]= "Shortness";
		else
			symptoms[2]="no";
		if (box4.isChecked())
			symptoms[3]= "Retention";
		else
			symptoms[3]="no";
		if (box5.isChecked())
			symptoms[4]= "Throat";
		else
			symptoms[4]="no";
		if (box6.isChecked())
			symptoms[5]= "Itching";
		else
			symptoms[5]="no";
		if (box7.isChecked())
			symptoms[6]= "Fever";
		else
			symptoms[6]="no";
		if (box8.isChecked())
			symptoms[7]= "Swelling";
		else
			symptoms[7]="no";
		Log.d("Yo!", "in checkinggggg");
				
		}
	

}
