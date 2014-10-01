package hm.orz.bluefish.canvastest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
	private Paint  paint;
	private Path  path;

	public CustomView(Context cont) {
		super(cont);
		initView();
	}

	public CustomView(Context cont, AttributeSet attr) {
		super(cont,attr);
		initView();
	}

	private void initView() {
		path = new Path();

		paint = new Paint();
		paint.setColor(0xFF008800);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(10);
	}

	public void clearView() {
		invalidate();
	}

	@Override
	protected void onDraw(Canvas c){
		c.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			path.lineTo(x, y);
			invalidate();
			break;
		}
		return true;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(widthSize,heightSize);
	}
}
