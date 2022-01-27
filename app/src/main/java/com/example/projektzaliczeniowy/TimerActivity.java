package com.example.projektzaliczeniowy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Button timerStart = (Button) findViewById(R.id.startBtn);
        SeekBar czasSeekBar = (SeekBar) findViewById(R.id.czasSeekBar) ;

        timerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("RESULT_STRING", String.valueOf(czasSeekBar.getProgress()));
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}