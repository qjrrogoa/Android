package com.kosmo.customview10_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 용 xml 전개]
        setContentView(R.layout.activity_main);

        //위젯 얻기
        TextView textView = findViewById(R.id.textview);
        EditText editText = findViewById(R.id.basic_edittext);
        LinearLayout layout = findViewById(R.id.activity_main);
    }
}