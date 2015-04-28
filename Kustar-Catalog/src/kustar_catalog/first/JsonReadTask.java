package kustar_catalog.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

 class JsonReadTask extends AsyncTask<String, Void, String> 
{
	  
	 	public AsyncResponse delegate=null;
		private String jsonResult=new String();;
		@Override
		protected String doInBackground(String... params) 
	  {
			Log.d(" I'm in aynch", " in do in backgound");
	   HttpClient httpclient = new DefaultHttpClient();
	   HttpPost httppost = new HttpPost(params[0]);
	   try {
	    HttpResponse response = httpclient.execute(httppost);
	    jsonResult = inputStreamToString(
	      response.getEntity().getContent()).toString();
	    Log.d("Am in async result is", jsonResult);

	       }

	   
	   catch (ClientProtocolException e) 
	   {
	    e.printStackTrace();
	   } catch (IOException e) 
	   {
	    e.printStackTrace();

	   }
	   return null;
	  }
	  
	  
	  private StringBuilder inputStreamToString(InputStream is) 
	  {
		   String rLine = "";
		   StringBuilder answer = new StringBuilder();
		   BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		   try {
		    while ((rLine = rd.readLine()) != null) {
		     answer.append(rLine);
		    }
		   }

		   catch (IOException e) {
		    // e.printStackTrace();
		    
		    Log.d("error",e.toString());
		   }
		   return answer;
		 
	  }

		  @Override
		  protected void onPostExecute(String result) {
			    Log.d("Am in op post result is", "before posting result");
			//    Log.d("Am in op post result is after", result);

			  delegate.processFinish(jsonResult);
				Log.d(" I'm in aynch on execute ", " after delagate");

			  		  }







}

