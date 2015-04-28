package kustar_catalog.first;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Technicals extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.courseslist);
        
        ListView list = (ListView)findViewById(R.id.listView1);
	
}
}
