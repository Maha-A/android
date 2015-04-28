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

public class LoadCourseTable /*extends Activity*/ implements AsyncResponse{
		
	
	private Context myContext;
	  Database database;	
	private String url; 
	private String jsonResult;

	public LoadCourseTable(Context context)
  {
      myContext = context;
      database=new Database(context);	 
      url = "http://ediabetes.biz/catalog/Course_table.php";
  	jsonResult=new String();
      accessWebService();

  }
	        
	        void CheckDB()
	    	{
	        
	    	
	    		    		try {
	    			   JSONObject jsonResponse = new JSONObject(jsonResult);
	    			   JSONArray jsonMainNode = jsonResponse.optJSONArray("Course_table");
	    			   for (int i = 0; i < jsonMainNode.length(); i++) 
	    			   {

	    			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
	    			        		     	       
	    			    
	    			 		    
	    			 		    CourseTable t = new CourseTable();
	    			 		   
	    			 		    t.Course_ID=jsonChildNode.optString("Course_ID");
	    			 		 t.Credits=jsonChildNode.optString("Credits");
	    			 		  t.Description=jsonChildNode.optString("Description");
	    			 		   t.Free_elective=jsonChildNode.optString("Free_elective");
	    			 		   t.Main_major=jsonChildNode.optString("Main_major");
	    			 		 t.Name=jsonChildNode.optString("Name");
	    			 		   t.Pre_req=jsonChildNode.optString("Pre_req");
	    			 		  t.Technical_elective=jsonChildNode.optString("Technical_elective");
	    				    	database.insertCourse_table(t);
	    			    	
	    	     		}	
	    			   //comes from LoadTechnicalElective
	    			   //goes to LoadCourseSchedule
	    			  /* Intent m = new Intent (LoadCourseTable.this , LoadCourseSchedule.class);
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
	 
	    	/*public void onBackPressed()
	    	{
	       	        Intent intent = new Intent(Intent.ACTION_MAIN);
	    	        intent.addCategory(Intent.CATEGORY_HOME);
	    	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	        startActivity(intent);
	    	        Context context = getApplicationContext();
	    	        context.deleteDatabase("catalog");
	    	    
	    	}*/

			@Override
			public void processFinish(String output) {
				jsonResult=output;	
				Log.d("hi",jsonResult);
	    		CheckDB();
			}
	    	
	}
	    




