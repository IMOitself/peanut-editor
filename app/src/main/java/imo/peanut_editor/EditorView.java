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
    private String mText = "beep-boop-";
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
		float letterHeight = mPaint.descent() - mPaint.ascent();
		letterHeight /= 2; // apparently its resulted in 2x expected size so we make it half

        float x = 0;
        float y = -mPaint.ascent(); // should've been 0 but drawing the text start from baseline
        
		// draw all letters allowed to show so far
        for(int i = 0; i < mLettersToDrawSoFar; i++) { 
            char letter = mText.charAt(i % mText.length());
            canvas.drawText(letter+"", x, y, mPaint);
            x += letterWidth;
        }
		
		boolean isLettersStillVisible = x < getWidth();
		
        if (isLettersStillVisible) { 
            mLettersToDrawSoFar++;
            postInvalidateDelayed(10);
            return;
        }
        // else
		x = 0;
		y += letterHeight + -mPaint.ascent();
		int maximumLetters = mLettersToDrawSoFar;
		canvas.drawText("maximum letters: " + maximumLetters, x, y, mPaint);
        
    }
}
