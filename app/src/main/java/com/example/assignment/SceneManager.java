package com.example.assignment;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Hon on 29/11/2016.
 */

public class SceneManager
{
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;
    public static int score;

    public SceneManager()
    {
        ACTIVE_SCENE = 0;
        scenes.add(new GamePlayScene1());
        scenes.add(new GameOverScene());
    }

    public void receiveTouch(MotionEvent event)
    {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update()
    {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas)
    {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
