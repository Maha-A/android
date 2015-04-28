package com.expertsystem;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Results extends Activity{

	TextView txt ;
	int [] family;
	String [] list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);

		Bundle b=this.getIntent().getExtras();
		double[] arrayD = b.getDoubleArray("analysis");
		family = b.getIntArray("family");
		txt = (TextView)findViewById(R.id.results);
		Log.d("am sending the array to be exmined","NOOOOOW");
		
		list = new String[2];
		compare(arrayD);
		
		
}
	
	void compare(double[] arrayD){
		String[] disease = new String[5];
		disease[0]="a";
		disease[1]="a";
		disease[2]="a";
		disease[3]="a";
		disease[4]="a";

		
		if(arrayD[0]<13 && arrayD[0] != -1 )
	    {
			disease[0] = "Anemia";
		}
		
		if((arrayD[2]<80 || arrayD[2]>120) && arrayD[2]!=-1)
		{
			disease[1] = "Diabetes";
		}
	    if(arrayD[2]>=80 && arrayD[2]<120 && arrayD[0]>=13)
		{
			disease[0] = "Dehydration";
			disease[1] = null;
		}
		
		
		 if((arrayD[1]<62 || arrayD[1]>115) && arrayD[1]!=-1)
		{
			disease[2] = "Kidney Failure";
		}
		 else if(arrayD[1]>=62 && arrayD[1]<=115)
		{
			disease[2] = "Early Signs Of Heart Fauiler";
		}
		
		 if(arrayD[3]<1.8 && arrayD[3]!=-1)
		{
			disease[3] = "Viral Infection";
		}
		else if(arrayD[3]>1.8)
		{
			disease[3] = "Common Cold";
		}
		 
		 if(arrayD[4]>0.2)
		{
			disease[4] = "Alergic Reaction";
			
		}
		else if(arrayD[4]<0.02 && arrayD[4]!=-1)
		{
			disease[4] = "Acute Infection";
			
		}	
		
		 setOutput(disease);  
		// txt.setText(disease[0] + "\n" + disease[1] + "\n" + disease[2]+ "\n" + disease[3]+ "\n" + disease[4]);
		
	}
	
	
	
	void setOutput(String [] de)
	{
		
		
		Log.d("am setting the output","NOOOOOW");

		
		boolean anemia = isInArray("Anemia",de);
		boolean diabetese = isInArray("Diabetes",de);
		boolean heart = isInArray("Early Signs Of Heart Fauiler",de);
		boolean viral = isInArray("Viral Infection",de);
		boolean kideny = isInArray("Kidney Failure",de);
		boolean cold = isInArray("Common Cold",de);
		boolean alergic = isInArray("Alergic Reaction",de);
		boolean infection = isInArray("Acute Infection",de);
		boolean dehy = isInArray("Dehydration",de);

		Log.d("am setting the output done calling isinarray","NOOOOOW");

		
		if(de[0]=="a" && de[1] =="a" && de[2]=="a" && de[3]=="a" && de[4]=="a")
		{
			txt.setText("I am ot sure what you have we need more info\n");
		}
		
		
		else if(family[0] == 1 && diabetese)
		{
				txt.setText("You Probably have diabetes\n");

		}
		
		
		else if(family[1] == 1 && heart )
		{
			txt.setText("You Probably have Early Signs Of Heart Fauiler\n");
		}
		
		
		else if(kideny)
		{
			
				txt.setText("You Probably have Kideny Failure\n");

		}
		
		else if(anemia)
		{
			txt.setText("You Probably have anemia\n");

		}
		
		
		else if(viral)
		{

			txt.setText("You Probably have Viral Infection\n");

		}
		else if(cold)
		{

			txt.setText("You Probably have Common Cold\n");

		}
		
		else if(infection)
		{
			txt.setText("You Probably have Acute Infection\n");
		}
		
		else if(alergic)
		{
			txt.setText("You Probably have alergic\n");
		}
		
		else if(dehy)
		{
			txt.setText("You Probably have Dehydration\n");
		}
		
		else if(diabetese)
		{
			txt.setText("You Probably have diabetes\n");
		}
		
		else if (heart)
		{
			txt.setText("You Probably have Early Signs Of Heart Fauiler\n");
		}
			
		
		//		if(anemia && diabetese)
//		{
//		de[0]="Anemia";
//		de[1]=null;
//		}
//		
//		if((viral && alergic) || (viral&& infection))
//		{
//			de[3]="Viral Infection";
//			de[4]=null;
//		}
//		
//		if((cold && alergic) || (cold&& infection))
//		{
//			de[4]="Common Cold";
//			de[4]=null;
//		}
//		
//		
//		txt.setText(de[0] + "\n" + de[1] + "\n" + de[2]+ "\n" + de[3]+ "\n" + de[4]);	
//		}
	}
	
	
	
	boolean isInArray(String a,String []arrayS)
	{
		Log.d("Yo!", arrayS.length+" ... ");
		
		for(int i=0; i<arrayS.length; i++)
		{
			if(arrayS[i].compareTo(a) == 0)
			{	Log.d("Yo!", "in 8");
				return true;

			}
		}
		return false;	
}
}