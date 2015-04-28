package com.drtms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.drtms.R;

/**
 * @author ediabetes
 * 
 * GlucoseMeter class extends Activity in order to built activity which can connect to
 * myglucohealth glucose meter, get the data from the glucose meter,display it, insert it to the database 
 * and send it to the server.
 */
public class GlucoseMeter extends Activity implements OnClickListener{
	//Debugging
	 
	private static final int REQUEST_ENABLE_BT = 1;
	//member fields
	private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
	private BluetoothDevice mDevice;
	private TextView deviceName;
	private  Button text;
	private ConnectThread connectThread;
	private ConnectedThread connectedThread;
	//private GlucoseTestsResultsSet gTestR;
	private boolean found_flag;
	List<String> devicesList; // store discoverd devices
	//private final Handler mHandler;
	String profile = new String();
	
   
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		found_flag=false;
		setContentView(R.layout.glucosemeter);
	    final String profileNumber = getIntent().getStringExtra("profileNumber");
	    profile=profileNumber;
		// REGITER BROADCAST RECIVER FOR DEVICE FOUND
		IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
		this.registerReceiver(mReceiver, filter);
		//REGISTER BROADCAST RECIEVER FOR SEARCH FINISHED
		filter=new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		this.registerReceiver(mReceiver, filter);
		//get the default adapter
		mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
		//check if phone support bluetooth
		if(mBluetoothAdapter==null)
		{
		}
		//enable bluetooth if its not enabled
		if(!mBluetoothAdapter.isEnabled())
		{
		    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

		}
		
		//Install the "I took the test" Button
		ImageButton go=(ImageButton)findViewById(R.id.GO);
		go.setOnClickListener(this);
		
		
		ImageButton viewResult=(ImageButton)findViewById(R.id.view);
		viewResult.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                    Intent i = new Intent ( GlucoseMeter.this, ChartDemo.class );
                    i.putExtra("Flag", "test");
                    startActivity(i); 
            }
    });
		 
		
		Button secret=(Button)findViewById(R.id.thesecret);
		secret.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                    Intent i = new Intent ( GlucoseMeter.this, Danger.class );
                    startActivity(i); 
            }
    });
	}
	@Override
	public void onResume()
	{
		super.onResume();
		devicesList=new ArrayList<String>();
		//doDiscovery();

		//start bluetooth discovery
				
	}
	
	/**
	 * This method is used to start the blutooth discovery
	 * preconditions : bluetooth adapter is available and enabled
	 */
	private void doDiscovery()
	{
		//check if its already discovering & stop Discovery
		if(mBluetoothAdapter.isDiscovering())
			mBluetoothAdapter.cancelDiscovery();
	
		Log.d("indodiscover", "Start Discovery");
		//start discovery
		mBluetoothAdapter.startDiscovery();
		
	}
	// broadcast receiver for device found and discovery finished
	final private BroadcastReceiver mReceiver =new BroadcastReceiver()
	{ 
		@Override
		public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
            	BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            	String DvName=device.getName();
               if(DvName.equals("myglucohealth"));
            	{  
            	   Log.d("found", "GlucohEALT FOUND");
            	mDevice =device;
        		connectThread=new ConnectThread(mDevice);
        		connectThread.run();
        		found_flag=true;

            	   }
     

             // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            	Log.d("DISCOVERY","FINSIED");
            	if(found_flag==false)
            		doDiscovery();
            	return;
            }
        }	
		};
		
		 /**
		  * 
		  * @author ediabetes
		  * this class is used to connect to the discovered myglucohealth glucose meter 
		  * it is called from GlucoseMeter mReciver when the myglucohealth glucose meter is discovered
		  * if the connection is sussfully established the class will call GlucoseMeter.connectedThread class
		  * which will handle the communications
		  */
		private  class ConnectThread extends Thread {
			private  final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
			private final BluetoothSocket mmSocket;
		    private final BluetoothDevice mmDevice;
		    public ConnectThread(BluetoothDevice device) {
		        // Use a temporary object that is later assigned to mmSocket,
		        // because mmSocket is final
		        BluetoothSocket tmp = null;
		        mmDevice = device; 
		        mBluetoothAdapter.cancelDiscovery();
      				Method m = null;
				try {
					m = device.getClass().getMethod("createRfcommSocket", new Class[] {int.class});
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					tmp = (BluetoothSocket) m.invoke(device, 1);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        mmSocket = tmp;
		       // mmSocket.
		    }
		 
		    public void run() {
		        // Cancel discovery because it will slow down the connection
		       // mBluetoothAdapter.cancelDiscovery();
		 
		        try {
		        	
		            // Connect the device through the socket. This will block
		            // until it succeeds or throws an exception
		        	mBluetoothAdapter.cancelDiscovery();
		        	mmSocket.connect();
		           
		        } catch (IOException connectException) {
		            // Unable to connect; close the socket and get out
		        	Log.d("c", ""+connectException);
		            try {
		                mmSocket.close();
		                doDiscovery();
		            } catch (IOException closeException) { Log.d("c", "nConected");}		            
		            return;
		        }
		 
		        // Do work to manage the connection (in a separate thread)
		        //ensureDiscoverable();
		        connectedThread=new ConnectedThread(mmSocket);
		        connectedThread.start();
		        
		    }
		 
		    /** Will cancel an in-progress connection, and close the socket */
		    public void cancel() {
		        try {
		            mmSocket.close();
		        } catch (IOException e) { }
		    }
		}
		/**
		 * 
		 * @author ediabetes
		 * this class handles the write& read from the glucosemeter. it is called by GlucoseMeter.connectThread
		 * After the connection with glucose meter is established 
		 */
		private class ConnectedThread extends Thread {
		    private static final byte STX = (byte) 0x80;
			private final BluetoothSocket mmSocket;
		    private final InputStream mmInStream;
		    private final OutputStream mmOutStream;
		    int dResult;
		    public ConnectedThread(BluetoothSocket socket) {
		        mmSocket = socket;
		        InputStream tmpIn = null;
		        OutputStream tmpOut = null;
		        try {
		            tmpIn = socket.getInputStream();
		            tmpOut = socket.getOutputStream();
		        } catch (IOException e) { }
		 
		        mmInStream = tmpIn;
		        mmOutStream = tmpOut;
		    }
		 
		    public void run() {
		        byte[] buffer = new byte[1024];  // buffer store for the stream
		        int bytes; // bytes returned from read()
		        Log.d("ConnectedThread","Started");
		        try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		            try {
						byte[] sc_sendBuf = new byte[8];
						byte[] sc_receiveBuf=new byte[13];
						byte num_Record = (byte)0x00;
						sc_sendBuf[0] = STX;//stx
						sc_sendBuf[1] = (byte)0x02;//size
						sc_sendBuf[2] = (byte) ~ ( sc_sendBuf[1] );//~size
						sc_sendBuf[3] = (byte)0x01;//command
						sc_sendBuf[4] = (byte)num_Record;//data
						sc_sendBuf[5] = (byte) ~ ((sc_sendBuf[0] ^ sc_sendBuf[2]) ^ sc_sendBuf[4]); //checksum L
						sc_sendBuf[6] = (byte) ~(sc_sendBuf[1] ^ sc_sendBuf[3]);// checksum H

						//attmept
						mmOutStream.write(sc_sendBuf);						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try
						{
						bytes = mmInStream.read(sc_receiveBuf);
						}
						catch(IOException e)
						{
							Log.d("error read", ""+e);
							bytes = mmInStream.read(sc_receiveBuf);
						}
						 dResult = (int)( (sc_receiveBuf[7]&0x03) << 8 ) + (int) ( sc_receiveBuf[8]&0xFF );//0xFF correction
						Log.d("result",""+dResult);
						mmSocket.close();
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					sendResultToServer(dResult);
						finish();
		            } catch (IOException e) {
		            }		
		    }
		}
		

	
	    protected void onDestroy() {
	        super.onDestroy();

	        // Make sure we're not doing discovery anymore
	        //if (mBtAdapter != null) {
	     //       mBtAdapter.cancelDiscovery();
	     //s   }

	        // Unregister broadcast listeners
	        this.unregisterReceiver(mReceiver);
	    }
	    /**
	     * 
	     * @param result 
	     * 
	     */
	    private void sendResultToServer(int results) {
	  	
	    	Integer level=0;
	    	Date time = new Date();
	    	if (results>120)
	    		level=1;
	    	Integer result = new Integer(results);
	    	SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    	fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String dateString = fmt.format(time);
	    	
	    	Log.d("datastring", dateString);
	    	   HttpClient httpclient = new DefaultHttpClient();
               HttpPost httppost = new HttpPost("http://ediabetes.biz/testing/insert.php");

               try {
                   // Add your data
                   List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                   nameValuePairs.add(new BasicNameValuePair("profilenumber", profile));
                   nameValuePairs.add(new BasicNameValuePair("level",level.toString()));
                   nameValuePairs.add(new BasicNameValuePair("result",result.toString()));
                   nameValuePairs.add(new BasicNameValuePair("time", dateString));

                  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                	Log.d("I am currently sending data level",time.toString() );
                  	Log.d("I am currently sending data profile", profile.toString());
                   HttpResponse response = httpclient.execute(httppost);

               } catch (ClientProtocolException e) {
                   // TODO Auto-generated catch block
               } catch (IOException e) {
                   // TODO Auto-generated catch block
               }
               Intent m = new Intent ( GlucoseMeter.this, Danger.class );
               m.putExtra("result",result.toString() );
               startActivity(m); 			
			
		}
	    /**
	     * 
	     * @param result
	     */
	    
		/*private void insertResultToDatabase(int result) {
			Database dat=new Database(GlucoseMeter.this);
			GlucoseTestsResultsSet gTestR=new GlucoseTestsResultsSet();
			gTestR.setTestResult(result);
			//gTestR.setTestEventR(testEventR);
			//gTestR.setTestTimeR(testTimeR);
			dat.InsertGlucoseTestResult(gTestR);
			
		} */
		private void ensureDiscoverable() {
	        
	        if (mBluetoothAdapter.getScanMode() !=
	            BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
	            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
	            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 10);
	            startActivity(discoverableIntent);
	        }
	    }


	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(this, "Please Press the left key on the Glucose Meter", Toast.LENGTH_LONG).show();
			doDiscovery();
		}

}	
////////////////////////////////////////////////////////
		
