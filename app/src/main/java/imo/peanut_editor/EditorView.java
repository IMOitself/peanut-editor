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
    private String mText = "beep boop ";
    private float mTextSize = 30f;
	private int mTextColor = -1;
    private int mLettersToDrawSoFar = 1;

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
        float letterWidth = mPaint.measureText(" ");
        float x = 0;
        float y = -mPaint.ascent(); // cant be 0 because drawing the text start from baseline
        
        for(int i = 0; i < mLettersToDrawSoFar; i++) { // draw all letters allowed to show so far
            char letter = mText.charAt(i % mText.length());

            canvas.drawText(letter+"", x, y, mPaint);

            x += letterWidth;
        }

        if (x < getWidth()) { // not hit the right edge yet
            mLettersToDrawSoFar++;
            postInvalidateDelayed(1000);
            return;
        }
        // else
        // TODO: draw how many letters has been drawn on another line
        
    }
}
