package com.example.assignment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Hon on 28/11/2016.
 */

public class Obstacle implements GameObject
{
    private Rect rect;
    private Rect rect2;
    private int color;
    private int startX;
    private int playerGap;

    public Rect getRect() {return rect;}

    public void incrementY(float y)
    {
        rect.top += y;
        rect.bottom += y;
        rect2.top += y;
        rect2.bottom += y;
    }

    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap)
    {
        this.color = color;

        rect = new Rect(0, startY, startX, startY + rectHeight);
        rect2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
        /*
                this.rect = rect;
                this.startX = startX;
                this.playerGap = playerGap;
                */
    }

    /*
    public boolean playerCollide(RectPlayer player) {
        if (rect.contains(player.getRect().left, player.getRect().top)
                || rect.contains(player.getRect().right, player.getRect().top)
                || rect.contains(player.getRect().left, player.getRect().bottom)
                || rect.contains(player.getRect().right, player.getRect().bottom)
                )
            return true;
        return false;
    }

    public boolean playerCollide(CirclePlayer player)
    {
        if(rect.contains(player.getCx(), player.getCircleTop())
                || rect.contains(player.getCx(), player.getCircleBottom())
                || rect.contains(player.getCircleLeft(), player.getCy())
                || rect.contains(player.getCircleRight(), player.getCy())
                )
            return true;
        return false;
    }
    */
    public boolean playerCollide(RectPlayer player)
    {
        return Rect.intersects(rect, player.getRect())
                || Rect.intersects(rect2, player.getRect());
    }

    @Override
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rect2, paint);
    }

    @Override
    public void update() {

    }
}
