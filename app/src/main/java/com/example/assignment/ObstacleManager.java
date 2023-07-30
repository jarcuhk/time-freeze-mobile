package com.example.assignment;

import android.content.res.AssetFileDescriptor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by Hon on 29/11/2016.
 */

public class ObstacleManager
{
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;

    private long startTime;
    private long initTime;

    private long lastFreezeTime = 0;
    private long freezeCount = 5000;
    private int countDown = 2000;
    private int countShow = 0;
    CountDownTimer timer;

    private int score = 0;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color)
    {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;

        startTime = initTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();

        timer = new CountDownTimer(5000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                long scnds=0;
                scnds=(millisUntilFinished/1000);
                countShow = (int)scnds;
            }


            public void onFinish()
            {

            }
        }.start();

        populateObstacles();
    }

    public boolean playerCollide(RectPlayer player)
    {
        for(Obstacle ob : obstacles)
        {
            if(ob.playerCollide(player))
            {
                GameActivity.playHit();
                return true;
            }
        }
        return false;
    }

    private void populateObstacles()
    {
        int currY = -5 * Constants.SCREEN_HEIGHT/4;
        while(currY < 0)
        {
            int xStart = (int)(Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obstacleHeight, color, xStart, currY, playerGap));
            currY += obstacleHeight + obstacleGap;
        }
    }

    public void display()
    {
        long currentTime = System.currentTimeMillis();
        System.out.println("curr-lastF=" + (currentTime - lastFreezeTime));
        System.out.println("lastFreezeTime= " + lastFreezeTime);
        System.out.println("currentTime= " + currentTime);
        System.out.println("freezeCount= " + freezeCount);
    }

    public boolean isFreeze()
    {
        long currentTime = System.currentTimeMillis();
        if(lastFreezeTime == 0 && (currentTime - initTime >= 1000))
        {
            lastFreezeTime = currentTime;
            return true;
        }
        else if (lastFreezeTime != 0 && currentTime - lastFreezeTime <= 1000)
        {
            return true;
        }
        else if (lastFreezeTime != 0 && currentTime - lastFreezeTime >= 5000)
        {
            lastFreezeTime = currentTime;
            return true;
        }
        else
            return false;
    }



    public void update()
    {
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT/10000.0f;
        for(Obstacle ob : obstacles)
        {
            display();
            if (isFreeze())
            {
                if (freezeCount >= 0)
                    freezeCount -= elapsedTime;
                else
                    freezeCount = 5000;
                timer.start();
                countShow = 0;
            }
            else
            {
                ob.incrementY(speed * elapsedTime);
                //timer.cancel();
                //if (countDown <= 0)
                //    countDown = 2000;
                //else
                //    countDown -= elapsedTime;
            }
        }
        /*
        if(countDown <= 0)
            countShow = 0;
        else if(countDown >= 500 && countDown <= 1000)
            countShow = 1;
        else
            countShow = 2;
        */

        if(obstacles.get(obstacles.size() - 1).getRect().top >= Constants.SCREEN_HEIGHT)
        {
            int xStart = (int)(Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, color, xStart,
                    obstacles.get(0).getRect().top - obstacleHeight - obstacleGap,
                    playerGap));
            obstacles.remove(obstacles.size() - 1);
            score++;
            SceneManager.score = score;
        }
    }

    public void draw(Canvas canvas)
    {
        for(Obstacle ob : obstacles)
            ob.draw(canvas);
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        paint1.setTextSize(70);
        paint1.setColor(Color.CYAN);
        paint2.setTextSize(100);
        paint2.setColor(Color.RED);
        canvas.drawText("Score: " + score, 50, Constants.SCREEN_HEIGHT - 100 + paint1.descent() - paint1.ascent(), paint1);
        canvas.drawText("" + countShow, Constants.SCREEN_WIDTH/2, 50 + paint2.descent() - paint2.ascent(), paint2);
    }
}
