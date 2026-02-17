package imo.peanut_editor;

import android.content.Context;
import android.view.View;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Paint;

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

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, 100, 100, mPaint);
    }
}