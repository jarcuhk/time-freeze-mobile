package com.example.assignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(R.layout.activity_main);

        Button btn_start = (Button) findViewById(R.id.btn_start);
        Button btm_instr = (Button) findViewById(R.id.btn_instruction);
        ImageButton btn_exit = (ImageButton) findViewById(R.id.btn_exit);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GameActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btm_instr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createExitDialogBox();
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    public void createExitDialogBox() {
        AlertDialog.Builder adExit = new AlertDialog.Builder(this);
        adExit.setTitle("Instruction");
        adExit.setMessage("This game is about moving the character to dodge falling obstacles. But be aware of the count down at the top of screen. Every 5 seconds movement of obstacles will be frozen. Don't pop into obstacles when time freeze!");
        adExit.setCancelable(false);

        adExit.setNegativeButton("Understand!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });

        adExit.create();
        adExit.show();
    }
}
