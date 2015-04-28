package kustar_catalog.first;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadTechnicalElective /*extends Activity*/ implements AsyncResponse{
		
	private Context myContext;
	  Database database;	
	private String url; 
	private String jsonResult;

	public LoadTechnicalElective(Context context)
    {
        myContext = context;
        database=new Database(context);	 
        url = "http://ediabetes.biz/catalog/Technical_elective.php";
    	jsonResult=new String();
        accessWebService();

    }
	
		
	        
	        void CheckDB()
	    	{
	    		    		try {
	    			   JSONObject jsonResponse = new JSONObject(jsonResult);
	    			   JSONArray jsonMainNode = jsonResponse.optJSONArray("Technical_elective");
	    			   for (int i = 0; i < jsonMainNode.length(); i++) 
	    			   {

	    			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
	    			        		     	       
	    			    
	    			 		    
	    			    TechnicalElective t = new TechnicalElective();
	    			 		   
	    			 		t.Course_ID_course=jsonChildNode.optString("Course_ID");
	    			 		t.Major_course=jsonChildNode.optString("Major");
	    			 		
	    			 		   
	    			 			
	    				    	database.insertTechnicalElective(t);
	    			    	
	    	     		}	
	    			   //comes from login
	    			   //load LoadCourseTable
	    			   /*Intent m = new Intent (LoadTechnicalElective.this , LoadCourseTable.class);
		    	        startActivity(m); 
		    			finish();*/
	    			      
	    		  } 
	    		
	    		catch (JSONException e) 
	    		{
	    			   
	    			   Log.d("line 5",e.toString());

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
	    




