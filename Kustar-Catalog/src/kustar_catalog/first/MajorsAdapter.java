package kustar_catalog.first;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class MajorsAdapter extends BaseAdapter {

    private Majors context;
    private String[] items;
    LayoutInflater inflater;

    public MajorsAdapter(Majors context, String[] items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cell2, null);
        }
        Button button = (Button) convertView.findViewById(R.id.grid_item2);
        button.setText(items[position]);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                context.itemClicked(position);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

	//@Override
	/*public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}*/
}