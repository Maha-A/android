package kustar_catalog.first;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class Majors extends Activity {
	
	 private GridView gridView;
	 private final String[] items = new String[]{"Software", "Computer", "Communcation", "Electronic", "Electrical"};
String id;
	    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //database= new DataBase(this);
        setContentView(R.layout.majors);
        id = new String();
      id = getIntent().getStringExtra("ID");

        gridView = (GridView) this.findViewById(R.id.myGridView2);
        
        MajorsAdapter gridAdapter = new MajorsAdapter(Majors.this, items);
        gridView.setAdapter(gridAdapter);
	}

	public void itemClicked(int position) {
	       // text.setText(items[position]);
			if (position == 0){
		        Intent intent = new Intent(Majors.this ,Courses_Technical.class);
		        intent.putExtra("ID", id);
		        startActivity(intent);
			}
}
	



	
}

