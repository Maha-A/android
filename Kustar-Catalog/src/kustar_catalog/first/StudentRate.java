package kustar_catalog.first;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;





import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class StudentRate extends Activity  {

String id;
String extra;
String studentid;
String m;
Integer num;
Database database= new Database(this);
EditText t;
		@Override
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.rate);
		    studentid = getIntent().getStringExtra("Student_Id"); 
		     extra = getIntent().getStringExtra("Course_ID");
		      t = (EditText)findViewById(R.id.t);
		     Button b = (Button)findViewById(R.id.button1);
		      m = new String();
		       num=0;
		       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		       StrictMode.setThreadPolicy(policy); 
		     b.setOnClickListener(new OnClickListener ()
		     {
		     	public void onClick(View v)
		     	
		     	{ 
		     		
		     num =Integer.parseInt(t.getText().toString());
		     	
		     		database.addrating(num,extra,studentid);
		     		
		     		if (database.addrating(num,extra,studentid) < 1)
		    		{
		    		Toast.makeText(getApplicationContext(), "You can't add a rating if you didn't take this course" ,
		    			     Toast.LENGTH_SHORT).show();
		    			   
		    		}
		     		else
		     		{
		     			sendResultToServer();
		     			Toast.makeText(getApplicationContext(), "Thank you for rating this course!" ,
			    			     Toast.LENGTH_SHORT).show();
		     		}
		     		
		     		
		     	}
		     });
		     
		     

		}
		
		
		
		private void sendResultToServer() {
		  	
	   
	    	
	    	   HttpClient httpclient = new DefaultHttpClient();
               HttpPost httppost = new HttpPost("http://ediabetes.biz/catalog/insert.php");

               try {
                   // Add your data
                   List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                   nameValuePairs.add(new BasicNameValuePair("Course_ID", extra));
                   nameValuePairs.add(new BasicNameValuePair("Student_ID",studentid));
                   nameValuePairs.add(new BasicNameValuePair("Rate",num.toString()));

                  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                   HttpResponse response = httpclient.execute(httppost);

               } catch (ClientProtocolException e) {
                   // TODO Auto-generated catch block
               } catch (IOException e) {
                   // TODO Auto-generated catch block
               }
               
		}
		


		
}
