package com.example.assignment;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Hon on 29/11/2016.
 */

public class GameActivity extends Activity
{
    public MediaPlayer player;
    public static SoundPlayer hit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        player = MediaPlayer.create(GameActivity.this, R.raw.gg_64kb);
        player.setLooping(true);
        player.setVolume(1.0f, 1.0f);
        player.start();

        hit = new SoundPlayer(this);

        setContentView(new GamePanel(this));
    }

    public static void playHit()
    {
        hit.playHitSound();
    }
}
