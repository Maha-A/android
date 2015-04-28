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

public class LoadStudentCourses implements AsyncResponse{
		
	private Context myContext;
	  Database database;	
	private String url; 
	private String jsonResult;

	public LoadStudentCourses(Context context)
{
  myContext = context;
  database=new Database(context);	 
  url = "http://ediabetes.biz/catalog/Student_courses.php";
	jsonResult=new String();
  accessWebService();

}
	        void CheckDB()
	    	{
	        
	    	
	    		    		try {
	    			   JSONObject jsonResponse = new JSONObject(jsonResult);
	    			   JSONArray jsonMainNode = jsonResponse.optJSONArray("Student_courses");
	    			   for (int i = 0; i < jsonMainNode.length(); i++) 
	    			   {

	    			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);	    			 		    
	    			 	StudentCourseTable s = new StudentCourseTable();
	    			 		   
	    			 		s.CRN_Student=jsonChildNode.optString("CRN");
	    			 		s.Grade=jsonChildNode.optString("Grade");
	    			 		s.Rating=jsonChildNode.optString("Rating");
	    			 		s.Student_comment=jsonChildNode.optString("Student_comment");
	    			 		s.Student_ID_courses=jsonChildNode.optString("Student_ID");   
	    			 		s.Course_ID_courses=jsonChildNode.optString("Course_ID");
	    			 		   
	    			 			
	    				    	database.insertStudentCourses(s);
	    			    	
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
	    




