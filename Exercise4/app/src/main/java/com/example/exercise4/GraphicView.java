package com.example.exercise4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class GraphicView extends View {

    Bitmap[] frames = new Bitmap[128];
    int i = 0;
    long last_tick = 0;
    long period = 200; // milliseconds
    static Context ctext;
    static MediaPlayer mediaPlayer;

    public GraphicView(Context context) {
        super(context);
        ctext = context;
        frames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.cat0);
        frames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.cat1);
        frames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.cat2);
        frames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.cat3);
        frames[4] = BitmapFactory.decodeResource(getResources(), R.drawable.cat4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (i < 5) { // Adjusted to the number of frames you have
            long time = System.currentTimeMillis();
            if (time - last_tick >= period) {
                last_tick = time;
                canvas.scale(0.75f, 0.8f);
                canvas.drawBitmap(frames[i], 40, 40, new Paint());
                i++;
                postInvalidateDelayed(period); // Schedule a repaint after 'period' milliseconds
            } else {
                canvas.scale(0.75f, 0.8f);
                canvas.drawBitmap(frames[i], 40, 40, new Paint());
                postInvalidateDelayed(period - (time - last_tick)); // Adjust the delay to maintain a constant frame rate
            }
        } else {
            i = 0;
            postInvalidateDelayed(period); // Schedule a repaint after 'period' milliseconds
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Add your touch event handling here, if needed
        return super.onTouchEvent(event);
    }
}
