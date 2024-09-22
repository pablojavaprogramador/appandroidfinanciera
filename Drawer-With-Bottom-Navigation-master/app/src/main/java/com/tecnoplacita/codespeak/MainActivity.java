package com.tecnoplacita.codespeak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tecnoplacita.codespeak.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
               finish();
            }

    };
        Timer tiempo=new Timer();
        tiempo.schedule(tarea,5000);
    }

}
