package com.example.assignment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Hon on 28/11/2016.
 */

public class RectPlayer implements GameObject
{
    private Rect rect;
    private int color;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;

    private AnimationManager animManager;

    public Rect getRect()
    {
        return  rect;
    }

    public RectPlayer(Rect rect, int color)
    {
        this.rect = rect;
        this.color = color;

        BitmapFactory bf = new BitmapFactory();
        Bitmap idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreen);
        Bitmap walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreen_walk1);
        Bitmap walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreen_walk2);

        idle = new Animation(new Bitmap[]{idleImg}, 2);
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight());
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight());

        walkLeft = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});
    }

    @Override
    public void draw(Canvas canvas)
    {
        //Paint paint = new Paint();
        //paint.setColor(color);
        //canvas.drawRect(rect, paint);
        animManager.draw(canvas, rect);
    }

    @Override
    public void update()
    {
        animManager.update();
    }

    public void update(Point point)
    {
        float oldLeft = rect.left;

        rect.set(point.x - rect.width()/2, point.y - rect.height()/2, point.x + rect.width()/2, point.y + rect.height()/2);

        int state = 0;
        if (rect.left - oldLeft > 5)
            state = 1;
        else if (rect.left - oldLeft < -5)
            state = 2;

        animManager.playAnim(state);
        animManager.update();
    }
}
