package kustar_catalog.first;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class ChoiceDepartments extends Activity{
	
	String userName = new String();
	String passWord=new String();
	private String jsonResult;
	private String url = "http://ediabetes.biz/catalog/LogIn.php";
	boolean check= false;
    Database db;	
    String id ;
    String x;
    
    //***********************************
    private GridView gridView;
    private final String[] items = new String[]{"ECE", "Mechanical", "Biomedical", "Aerospace", "Civil","Industrial and System"," "};

    
    //***********************************
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //database= new DataBase(this);
        
        db=new Database(this);
      
        
        
        
        
        
         x = new String(getIntent().getStringExtra("ID"));
        
        Cursor c2 = db.getStudent_table();
        c2.moveToFirst();
        String line2 = c2.getString(3);
        String line = "Your Major: "+line2;
        items[6] = line;
        c2.moveToNext();
        setContentView(R.layout.departments);
        
        gridView = (GridView) this.findViewById(R.id.myGridView);
        
        CustomGridAdapter gridAdapter = new CustomGridAdapter(ChoiceDepartments.this, items);
        gridView.setAdapter(gridAdapter);
        

}
	
	public void itemClicked(int position) {
       // text.setText(items[position]);
		if (position == 0){
	        Intent intent = new Intent(ChoiceDepartments.this ,Majors.class);
	        intent.putExtra("ID", x);
	        startActivity(intent);
		}
	//	else
	//		text.setText(items[position]);


    }
	
	


public void onBackPressed()
	{
   	        //Intent intent = new Intent(Intent.ACTION_MAIN);
	        //intent.addCategory(Intent.CATEGORY_HOME);
	       // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Intent intent = new Intent ( ChoiceDepartments.this, LogIn.class );
	        startActivity(intent);
	        Context context = getApplicationContext();
	        context.deleteDatabase("catalog");
	        Log.d("I deleted the","database from login");
	    
	}



}