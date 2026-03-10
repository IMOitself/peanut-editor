package imo.peanut_editor;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class EditorView extends View {
    public EditorView(Context context) {
        super(context);
        init();
    }

    public EditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;
    private String mText = "Hello World";
    private float mTextSize = 30f;
	private int mTextColor = -1;

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setTypeface(Typeface.MONOSPACE);
		
		TypedArray typedArray = getContext().obtainStyledAttributes(new int[]{android.R.attr.textColor});
		mTextColor = typedArray.getColor(0, Color.RED);
		typedArray.recycle();
		
		mPaint.setColor(mTextColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = 0;
        float y = -mPaint.ascent(); // cant be 0 because drawing the text start from baseline
        canvas.drawText(mText, x, y, mPaint);
    }
}
