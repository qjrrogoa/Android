package com.kosmo.chronometer11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Activity 에다가 이벤트 핸들러의 역할을 추가해줌 (implements View.OnClickListener)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer chronometer;
    private Button btnTimeSet,btnDateSet,btnStart,btnStop;
    private TextView textView;
    private Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 전개] inflate
        setContentView(R.layout.activity_main);
        //위젯 얻기
        chronometer = findViewById(R.id.timer_chronometer);
        btnDateSet = findViewById(R.id.dateset_button);
        btnTimeSet = findViewById(R.id.timeset_button);
        btnStart = findViewById(R.id.start_button);
        btnStop = findViewById(R.id.stop_button);
        textView = findViewById(R.id.textview);

        //위젯에 리스너 부착
        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnTimeSet.setOnClickListener(this);
        btnDateSet.setOnClickListener(this);
        //※크로노미터는 디폴트로 분:초로 표시됨. 아래 코드 추가시 시:분:초로 변경할 수 있음
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            //초가 변경될때마다 자동으로 호출되는 콜백 메소드
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                //크로노미터를 시작한 이후 흘러온 시간
                long elapsedtime = SystemClock.elapsedRealtime()-chronometer.getBase();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String timeString = dateFormat.format(new Date(elapsedtime));
                //텍스트 뷰에 크로노미터의 텍스트 설정
                chronometer.setText(timeString);
                //OS부팅후 흘러온 시간
                Log.i("com.kosmo.chronometer","elapsedRealtime:"+dateFormat.format(new Date(elapsedtime)));
                //setBase() 로 설정한 시간을 반환
                Log.i("com.kosmo.chronometer","getBase():"+dateFormat.format(new Date(chronometer.getBase())));
            }
        });

    }////onCreate

    @Override
    public void onClick(View v) {
        if(v == btnTimeSet){
            //부팅후 클릭시까지 흘러온 시간을 기준시간으로 설정
            chronometer.setBase(SystemClock.elapsedRealtime());
            //Chronometer 시작
            chronometer.start();
            new TimePickerDialog(v.getContext(), android.R.style.Theme_Material_Dialog, new TimePickerDialog.OnTimeSetListener() {
                //시간 날짜 설정 완료시
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //Chronometer 중지
                    chronometer.stop();
                    textView.setText(String.format("설정 시간 : %s시 %s분",hourOfDay,minute));
                }
            },cal.get(Calendar.HOUR),cal.get(Calendar.MARCH),true).show();
        }
        else if (v.getId()==R.id.dateset_button){
            //부팅후 클릭시까지 흘러온 시간을 기준시간으로 설정
            chronometer.setBase(SystemClock.elapsedRealtime());
            //Chronometer 시작
            chronometer.start();

            new DatePickerDialog(v.getContext(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                //시간 날짜 설정 완료시
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //Chronometer 중지
                    chronometer.stop();
                    textView.setText(String.format("설정 시간 : %s년 %s월 %s일",year,month+1,dayOfMonth));
                }
            },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).show();
        }
        else if (v == btnStart){
            //1] SystemClock 시간으로 Chronometer 시간 설정
            chronometer.setBase(SystemClock.elapsedRealtime());
            //Chronometer 시작
            chronometer.start();

        }
        else  //Chronometer 중지
            chronometer.stop();

    }////onClick

}////class