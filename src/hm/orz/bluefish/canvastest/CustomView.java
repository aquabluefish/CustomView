package hm.orz.bluefish.canvastest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomView extends View {
	private Canvas canvas;
	private Bitmap bitmap;
	private Paint  paint;
	private Path   path;
	private Context appCont;
	
	public CustomView(Context cont) {
		super(cont);
		initView(cont);
	}

	public CustomView(Context cont, AttributeSet attr) {
		super(cont,attr);
		initView(cont);
	}

	private void initView(Context cont) {
		appCont = cont.getApplicationContext();
		
		path = new Path();
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(10);
	}

	@Override
	protected void onDraw(Canvas c){
		c.drawBitmap(bitmap, 0, 0,null);
		c.drawPath(path, paint);
	}

	@Override
	protected void onSizeChanged(int w,int h,int oldw,int oldh) {
		bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			path.reset();
			path.moveTo(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			path.lineTo(x, y);
			canvas.drawPath(path, paint);
			path.reset();
			invalidate();
			break;
		}
		return true;
	}

	public void setColor(int color) {
		paint.setColor(color);
//		paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
	}

	public void clearView() {
		canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
		invalidate();
	}

	public void saveAsPngImage(){
		try {
			File extStrageDir =
					Environment.getExternalStorageDirectory();
			File file = new File(
				extStrageDir.getAbsolutePath() + "/" + Environment.DIRECTORY_DCIM,
				getFileName());
			FileOutputStream outStream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.PNG, 100, outStream);
			outStream.close();

			Toast.makeText(appCont,"Image saved",Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			Toast.makeText(appCont,"FileNotFoundException!!",Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(appCont,"IOException!!",Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

	protected String getFileName(){
		Calendar c = Calendar.getInstance();
		String s = c.get(Calendar.YEAR)
				+ "_" + (c.get(Calendar.MONTH)+1)
				+ "_" + c.get(Calendar.DAY_OF_MONTH)
				+ "_" + c.get(Calendar.HOUR_OF_DAY)
				+ "_" + c.get(Calendar.MINUTE)
				+ "_" + c.get(Calendar.SECOND)
				+ "_" + c.get(Calendar.MILLISECOND)
				+ ".png";
		return s;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(widthSize,heightSize);
	}
}
