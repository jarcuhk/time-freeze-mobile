package com.example.assignment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by Hon on 30/11/2016.
 */

public class GameOverScene implements Scene
{
    Rect restart, exit;
    private Rect drawRect = new Rect();

    public GameOverScene()
    {
        restart = new Rect(100, Constants.SCREEN_HEIGHT/3 - 200, Constants.SCREEN_WIDTH - 100, Constants.SCREEN_HEIGHT/3);
        exit = new Rect(100, 2*Constants.SCREEN_HEIGHT/3, Constants.SCREEN_WIDTH - 100, 2*Constants.SCREEN_HEIGHT/3 + 200);
    }

    public void receiveTouch(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(restart.contains((int)event.getX(), (int)event.getY()))
                    terminate();
                else if(exit.contains((int)event.getX(), (int)event.getY()))
                    System.exit(0);
                break;
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    public void update()
    {}

    public void draw(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        Paint paintW = new Paint();
        paint1.setColor(Color.GREEN);
        paint2.setColor(Color.RED);
        paintW.setColor(Color.WHITE);
        paintW.setTextSize(100);
        canvas.drawRect(restart, paint1);
        canvas.drawRect(exit, paint2);
        drawRestartText(canvas, paintW, "Try Again");
        drawExitText(canvas, paintW, "Leave");
        drawLoseText(canvas, paintW, "YOU LOSE!");
        drawScoreText(canvas, paintW, "Score:" + SceneManager.score);
    }

    private void drawLoseText(Canvas canvas, Paint paint, String text)
    {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(drawRect);
        int cHeight = drawRect.height();
        int cWidth = drawRect.width();
        paint.getTextBounds(text, 0, text.length(), drawRect);
        float x = cWidth / 2f - drawRect.width() / 2f - drawRect.left;
        //float y = cHeight / 4f + drawRect.height() / 4f - drawRect.bottom;
        float y = Constants.SCREEN_HEIGHT/2 - 50;
        canvas.drawText(text, x, y, paint);
    }

    private void drawScoreText(Canvas canvas, Paint paint, String text)
    {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(drawRect);
        int cHeight = drawRect.height();
        int cWidth = drawRect.width();
        paint.getTextBounds(text, 0, text.length(), drawRect);
        float x = cWidth / 2f - drawRect.width() / 2f - drawRect.left;
        //float y = cHeight / 2f + drawRect.height() / 2f - drawRect.bottom;
        float y = Constants.SCREEN_HEIGHT/2 + 100;
        canvas.drawText(text, x, y, paint);
    }

    private void drawRestartText(Canvas canvas, Paint paint, String text)
    {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(drawRect);
        int cHeight = drawRect.height();
        int cWidth = drawRect.width();
        paint.getTextBounds(text, 0, text.length(), drawRect);
        float x = cWidth / 2f - drawRect.width() / 2f - drawRect.left;
        //float y = cHeight / 4f + drawRect.height() / 4f - drawRect.bottom;
        float y = Constants.SCREEN_HEIGHT/3 - 60;
        canvas.drawText(text, x, y, paint);
    }

    private void drawExitText(Canvas canvas, Paint paint, String text)
    {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(drawRect);
        int cHeight = drawRect.height();
        int cWidth = drawRect.width();
        paint.getTextBounds(text, 0, text.length(), drawRect);
        float x = cWidth / 2f - drawRect.width() / 2f - drawRect.left;
        //float y = cHeight / 2f + drawRect.height() / 2f - drawRect.bottom;
        float y = 2*Constants.SCREEN_HEIGHT/3 +150;
        canvas.drawText(text, x, y, paint);
    }
}
