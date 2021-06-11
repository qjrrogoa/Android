package com.kosmo.relativelayout03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("상대적 레이아웃");
        setContentView(R.layout.activity_main);
    }
}