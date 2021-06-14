package com.kosmo.chronometer11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Calendar;

//액티비티이자 이벤트 핸들러
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Chronometer chronometer;
    private Button btnTimeSet,btnDateSet,btnStart,btnStop;
    private TextView textview;
    private Calendar cal = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 전개] - inflate
        setContentView(R.layout.activity_main);
        //위젯 얻기
        chronometer = findViewById(R.id.timer_chronometer);
        btnDateSet = findViewById(R.id.dateset_button);
        btnTimeSet = findViewById(R.id.timeset_button);
        btnStart = findViewById(R.id.start_button);
        btnStart = findViewById(R.id.stop_button);
        textview=findViewById(R.id.textview);
        //위젯에 리스너 부착]
        btnStart.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnTimeSet.setOnClickListener(this);
        btnDateSet.setOnClickListener(this);
    }//////onCreate

    @Override
    public void onClick(View v) {

    }
}