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

public class LoadCourseSchedule /*extends Activity*/ implements AsyncResponse{
		
	
	private Context myContext;
	  Database database;	
	private String url; 
	private String jsonResult;

	public LoadCourseSchedule(Context context)
{
    myContext = context;
    database=new Database(context);	 
    url = "http://ediabetes.biz/catalog/Course_schedule.php";
	jsonResult=new String();
    accessWebService();

}

	        void CheckDB()
	    	{
	        
	    	
	    		    		try {
	    			   JSONObject jsonResponse = new JSONObject(jsonResult);
	    			   JSONArray jsonMainNode = jsonResponse.optJSONArray("Course_schedule");
	    			   for (int i = 0; i < jsonMainNode.length(); i++) 
	    			   {

	    			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
	    			        		     	       
	    			    
	    			 		    Log.d("I am here in the reading  ", "I read");
	    			 		    Student student = new Student();
	    			 		    CourseSchedule s = new CourseSchedule();
	    			 		    s.Campus=jsonChildNode.optString("Campus");
	    			 		   s.Class_time=jsonChildNode.optString("Time");
	    			 		   s.Course_ID=jsonChildNode.optString("Course_ID");
	    			 		   s.CRN=jsonChildNode.optString("CRN");
	    			 		   s.Days=jsonChildNode.optString("Days");
	    			 		   s.Instructor_comment=jsonChildNode.optString("Instructor_comment");
	    			 		   s.Instrutor_ID=jsonChildNode.optString("Instructor_ID");
	    			 		   s.Term=jsonChildNode.optString("Term");
	    			 		   s.Time=jsonChildNode.optString("Time");
	    			 		   
	    			 		   
	    			 		   
	    			 			
	    				    	database.insertCourse_schedule(s);
	    			    	
	    	     		}	
	    			   //comes from LoadCourseTable
	    			   /*Intent m = new Intent (LoadCourseSchedule.this , ChoiceDepartments.class);
		    	        startActivity(m); 
		    			finish();*/
	    			      
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
				Log.d("hi",jsonResult);
	    		CheckDB();
			}
	    	
	}
	    



