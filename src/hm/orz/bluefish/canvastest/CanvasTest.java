package hm.orz.bluefish.canvastest;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CanvasTest extends Activity
implements OnNavigationListener {

	TextView tv;
	CustomView cv;
	Button bt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.main, null);
		setContentView(layout);

		tv = (TextView)layout.findViewById(R.id.textView1);
		cv = (CustomView)layout.findViewById(R.id.customView1);
		bt = (Button)layout.findViewById(R.id.button1);

		bt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(CanvasTest.this, "Click!", Toast.LENGTH_LONG).show();
				cv.saveAsPngImage();
			}
		});

		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.color_list, R.layout.spinner);
		adapter.setDropDownViewResource( R.layout.spinner_down);
		getActionBar().setListNavigationCallbacks(adapter, this);
	}

	@Override
	public boolean onNavigationItemSelected(int pos, long id) {
		Toast.makeText(this, "pos=" + pos, Toast.LENGTH_SHORT).show();
		int color = Color.WHITE;
		switch(pos){
		case 0: color = Color.BLACK; break;
		case 1: color = Color.RED; break;
		case 2: color = Color.GREEN; break;
		case 3: color = Color.BLUE; break;
		case 4: color = Color.CYAN; break;
		case 5: color = Color.MAGENTA; break;
		case 6: color = Color.YELLOW; break;
		}
		cv.setColor(color);
		return true;
	}
}
