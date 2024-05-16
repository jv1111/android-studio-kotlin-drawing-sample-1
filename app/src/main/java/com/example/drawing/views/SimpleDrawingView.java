package com.example.drawing.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimpleDrawingView extends View {
    // setup initial color
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;
    // Store circles to draw each time the user touches down
    private List<Point> circlePoints;

    private Path path = new Path();
    private Context context;

    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        context = context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
        circlePoints = new ArrayList<Point>();
    }

    private void setupPaint() {
        drawPaint = new Paint();
        //drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20f);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    float touchX = 50;
    float touchY= 50;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();

        //store the touch coordination for drawing
        //circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));

        //draw using path
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // Starts a new line in the path
                path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                // Draws line between last point and this point
                path.lineTo(touchX, touchY);
                break;
            default:
                return false;
        }
        postInvalidate(); // indicate view should be redrawn
        return true; // Indicate we've consumed the touch
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //position, position, size
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(touchX, touchY, 20, drawPaint);

        Log.i("mtag", "drawing");

//
//        drawPaint.setColor(Color.GREEN);
//        drawPaint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(50, 150, 20, drawPaint);
//
//        drawPaint.setColor(Color.BLUE);
//        canvas.drawCircle(50, 250, 20, drawPaint);

        //draw using touch event
//        for (Point p : circlePoints) {
//            canvas.drawCircle(p.x, p.y, 5, drawPaint);
//        }


        //draw using path
        //canvas.drawPath(path, drawPaint);
    }
}
