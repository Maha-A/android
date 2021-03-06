package kustar_catalog.first;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadInstructorTable /*extends Activity*/ implements AsyncResponse{
		
	
	private Context myContext;
	  Database database;	
	private String url; 
	private String jsonResult;

	public LoadInstructorTable(Context context)
{
    myContext = context;
    database=new Database(context);	 
    url = "http://ediabetes.biz/catalog/Instructor_table.php";
	jsonResult=new String();
    accessWebService();

}
  
	        void CheckDB()
	    	{
	        
	    	
	    		    		try {
	    			   JSONObject jsonResponse = new JSONObject(jsonResult);
	    			   JSONArray jsonMainNode = jsonResponse.optJSONArray("Instructor_table");
	    			   for (int i = 0; i < jsonMainNode.length(); i++) 
	    			   {

	    			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
	    			        		     	       
	    			    
	    			 		    
	    			 		    InstructorTable m = new InstructorTable();
	    			 		   
	    			 		    m.Department=jsonChildNode.optString("Department");
	    			 		m.Fname=jsonChildNode.optString("Fname");
	    			 		 m.Instrutor_ID=jsonChildNode.optString("Instrutor_ID");
	    			 		   m.Lname=jsonChildNode.optString("Lname");
	    				    	database.insertIntstructor_table(m);
	    			    	
	    	     		}	
	    			
	    		  } 
	    		
	    		catch (JSONException e) 
	    		{

	    		}	
	    			    		
	    	 }
	    	    	
	    	public void accessWebService() {
	    		  JsonReadTask task = new JsonReadTask();
	    		  task.execute(new String[] { url });
	    		  task.delegate=this;
	    									}

			@Override
			public void processFinish(String output) {
				jsonResult=output;	
	    		CheckDB();
			}
	    	
	}
	    




