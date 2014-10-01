package hm.orz.bluefish.canvastest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CanvasTest extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.main, null);
		setContentView(layout);

		TextView tv = (TextView)layout.findViewById(R.id.textView1);
		final CustomView cv = (CustomView)layout.findViewById(R.id.customView1);
		Button bt = (Button)layout.findViewById(R.id.button1);
		
		bt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
	                Toast.makeText(CanvasTest.this, "Click!", Toast.LENGTH_LONG).show();
	                cv.clearView();
	        }
	    });
	}
}
