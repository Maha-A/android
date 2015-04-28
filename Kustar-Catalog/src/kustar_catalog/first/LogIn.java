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

import kustar_catalog.first.Database;
import kustar_catalog.first.R;

public class LogIn extends Activity implements AsyncResponse{
	
	String userName = new String();
	String passWord=new String();
	private String jsonResult=new String();
	private String url = "http://ediabetes.biz/catalog/LogIn.php";
//	private String url = "http://ediabetes.biz/catalog/preReq.php";
	EditText ID;
	    Student student ;

	boolean check= false;
    Database database;	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        database=new Database(this);
        setContentView(R.layout.sign);
        	
        Button ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new OnClickListener ()
        {
        	public void onClick(View v)
        	{ 
        		//get the user name and id entered by the user
        		 ID = (EditText)findViewById(R.id.user);
                EditText pass=(EditText)findViewById(R.id.password);
        		userName=ID.getText().toString();
        		passWord= pass.getText().toString();
        		accessWebService(); 
        		
        	}
        });
	}
        
        void CheckDB()
    	{
        
    		String id=new String();
    		String pass=new String();
    		    		try {
    			   JSONObject jsonResponse = new JSONObject(jsonResult);
    			   JSONArray jsonMainNode = jsonResponse.optJSONArray("Student_table");
    			   for (int i = 0; i < jsonMainNode.length(); i++) 
    			   {

    			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
    			     pass = jsonChildNode.optString("Password");
    			     id = jsonChildNode.optString("Student_ID");    	        		     	       
    			    if(userName.equals(id) )
    			    {
    	        		Log.d("id checks out"," passed first if");
    			    	
    			    	if ( passWord.equals(pass))
    			    	{
    			    		check=true;
    			 		    Log.d("I am here in the reading  ", "I read");
    			 		     student = new Student();
    			 		    student.SFname=jsonChildNode.optString("Fname");
    			 		    student.SLname=jsonChildNode.optString("Lname");
    			 		    student.Major = jsonChildNode.optString("Major");
    			 		    student.DOB=jsonChildNode.optString("DOB");
    			 		    student.Status=jsonChildNode.optString("Status");
    			 		    student.GPA=jsonChildNode.optString("GPA");
    			 		    student.Gender=jsonChildNode.optString("Gender");
    			 			student.Campus=jsonChildNode.optString("Campus");
    			 			student.ID=id;
    			 			student.Pass=pass;
    				    	database.insertStudent(student);	
    				    		
    			    	}
    	     		}
    			    
    			   
    			    }
    			   
    			   if (check==true){
    	        		LoadTechnicalElective elective = new LoadTechnicalElective(ID.getContext());
    	        		LoadCourseTable table = new LoadCourseTable(ID.getContext());
    	        		LoadCourseSchedule course = new LoadCourseSchedule(ID.getContext());
    	        		LoadInstructorTable instructor = new LoadInstructorTable(ID.getContext());
    	        		LoadMajorCourse major = new LoadMajorCourse (ID.getContext());
    	        		LoadPreReq pre = new LoadPreReq(ID.getContext());
    	        		LoadStudentCourses stu = new LoadStudentCourses(ID.getContext());
    	        		Log.d("i loaded", "the technical electives");
    	        		Intent m = new Intent (LogIn.this , ChoiceDepartments.class);
    	        		m.putExtra("ID",student.ID );
    	    	        startActivity(m);} 
    			   
    			   else 
   	    		{
   	    			
   	    			   Toast.makeText(getApplicationContext(), 
   	    					   "You have entered a wrong profile Number or password plese try again",
   	    					   Toast.LENGTH_SHORT).show();
   	    		}
    		  } 
    		
    		catch (JSONException e) 
    		{
    			   Toast.makeText(getApplicationContext(), "Error" + e.toString(),
    			     Toast.LENGTH_SHORT).show();
    			   Log.d("line 5",e.toString());

    		}	
    		
    		/*if (check)
    		{
    			//go from log in to LoadTEchnical
    			TechnicalElective e = new TechnicalElective(getBaseContext());
    			Intent m = new Intent (LogIn.this , ChoiceDepartments.class);
    	        startActivity(m); 
    			finish();
    		}*/
    		
    		
    	 }
    	    	
    	public void accessWebService() {
    		  JsonReadTask task = new JsonReadTask();
    		  task.execute(new String[] { url });
    		  task.delegate=this;
    									}
 
    	public void onBackPressed()
    	{
       	        Intent intent = new Intent(Intent.ACTION_MAIN);
    	        intent.addCategory(Intent.CATEGORY_HOME);
    	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	        startActivity(intent);
    	       // Context context = getApplicationContext();
    	        //context.deleteDatabase("catalog");
    	        Log.d("I deleted the","database from login");
    	    
    	}

		@Override
		public void processFinish(String output) {
			jsonResult=output;	
    		CheckDB();
		}
    	
}
    



