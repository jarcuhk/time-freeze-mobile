package com.example.assignment;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Hon on 29/11/2016.
 */

public interface Scene
{
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
