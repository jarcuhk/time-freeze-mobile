package com.example.assignment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Hon on 28/11/2016.
 */

public class CirclePlayer implements GameObject
{
    private float cx, cy, radius = 50;
    private int color;

    public CirclePlayer(int color)
    {
        this.color = color;
    }

    public int getCx() {return (int)cx;}
    public int getCy() {return (int)cy;}
    public int getCircleLeft() {return (int)(cx - radius);}
    public int getCircleRight() {return (int)(cx + radius);}
    public int getCircleTop() {return (int)(cy - radius);}
    public int getCircleBottom() {return (int)(cy + radius);}

    @Override
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        //x, y, rad
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point)
    {
        //rect.set(point.x - rect.width()/2, point.y - rect.height()/2, point.x + rect.width()/2, point.y + rect.height()/2);
        cx = point.x;
        cy = point.y;
    }
}
