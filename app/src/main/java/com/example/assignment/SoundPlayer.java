package com.example.assignment;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Hon on 1/12/2016.
 */

public class SoundPlayer
{
    private static SoundPool soundPool;
    private static int hitSound;

    public SoundPlayer(Context context)
    {
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        hitSound = soundPool.load(context, R.raw.hitsound, 1);
    }

    public void playHitSound()
    {
        soundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
