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

public class LoadPreReq /*extends Activity*/ implements AsyncResponse{
		
	
	private Context myContext;
	  Database database;	
	private String url; 
	private String jsonResult;

	public LoadPreReq(Context context)
{
  myContext = context;
  database=new Database(context);	 
  url = "http://ediabetes.biz/catalog/Pre_req.php";
	jsonResult=new String();
  accessWebService();

}
		
	        void CheckDB()
	    	{
	        
	    		    		try {
	    			   JSONObject jsonResponse = new JSONObject(jsonResult);
	    			   JSONArray jsonMainNode = jsonResponse.optJSONArray("Pre_req");
	    			   for (int i = 0; i < jsonMainNode.length(); i++) 
	    			   {

	    			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
	    			 		    Pre_req p = new Pre_req();
	    			 		   p.Course_ID_Pre=jsonChildNode.optString("Course_ID");
	    			 		  p.Course_ID_Pre.trim();
	    			 		p.Pre_req_Pre=jsonChildNode.optString("Pre_req");
	    			 		p.Pre_req_Pre.trim();
	    				    	database.insertPre_req(p);
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
	    




