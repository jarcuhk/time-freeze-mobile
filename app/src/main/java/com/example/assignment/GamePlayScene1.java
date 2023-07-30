package com.example.assignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;

/**
 * Created by Hon on 29/11/2016.
 */

public class GamePlayScene1 implements Scene
{
    private RectPlayer rectPlayer;
    private CirclePlayer circlePlayer;
    private Point playerPoint, rectPoint, circlePoint;

    private ObstacleManager obstacleManager;

    private boolean playerMove = false;

    private boolean gameOver = false;
    private long gameOverTime;

    private Rect drawRect = new Rect();

    public GamePlayScene1()
    {
        rectPlayer = new RectPlayer(new Rect(100, 100, 200, 200), Color.rgb(0,0,255));
        circlePlayer = new CirclePlayer(Color.rgb(0,255,0));
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        rectPlayer.update(playerPoint);
        //rectPoint = new Point(100, 100);
        //circlePoint = new Point(200, 100);

        obstacleManager = new ObstacleManager(200, 350, 100, Color.rgb(2, 132, 130));
    }

    public void reset()
    {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        rectPlayer.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 350, 75, Color.rgb(2, 132, 130));
        playerMove = false;
    }

    public boolean getGameOver()
    {
        return gameOver;
    }

    @Override
    public void terminate()
    {
        SceneManager.ACTIVE_SCENE = 1;
    }

    @Override
    public void receiveTouch(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && rectPlayer.getRect().contains((int)event.getX(), (int)event.getY()))
                    playerMove = true;
                if(gameOver && System.currentTimeMillis() - gameOverTime >= 2000)
                {
                    reset();
                    gameOver = false;
                    terminate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!gameOver && playerMove)
                    playerPoint.set((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                playerMove = false;
                break;
        }
    }

    @Override
    public void update()
    {
        if(!gameOver) {
            //rectPlayer.update(rectPoint);
            //circlePlayer.update(circlePoint);
            rectPlayer.update(playerPoint);

            obstacleManager.update();

            if(obstacleManager.playerCollide(rectPlayer))
            {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawColor(Color.WHITE);

        rectPlayer.draw(canvas);
        //circlePlayer.draw(canvas);

        obstacleManager.draw(canvas);

        if(gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.rgb(138, 7, 7));
            drawTextCenter(canvas, paint, "You're Done");
        }
    }

    private void drawTextCenter(Canvas canvas, Paint paint, String text)
    {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(drawRect);
        int cHeight = drawRect.height();
        int cWidth = drawRect.width();
        paint.getTextBounds(text, 0, text.length(), drawRect);
        float x = cWidth / 2f - drawRect.width() / 2f - drawRect.left;
        float y = cHeight / 2f + drawRect.height() / 2f - drawRect.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
