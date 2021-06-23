package com.kosmo.thread31_03;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressbar;
    private TextView seektext;
    private SeekBar seekbar;
    private TextView asynctext1;
    private SeekBar asyncseekbar1;
    private TextView asynctext2;
    private SeekBar asyncseekbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        initView();
        //seekbar에 리스너 부착:seekbar를 터치해서 움직일때
        //움직인 정도를 seektext에 뿌려주기
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seektext.setText("씨크바 진행율:"+(int)(((double)progress/seekBar.getMax())*100)+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }//////////onCreate

    public void increase(View view) {
        if(progressbar.getProgress() < progressbar.getMax()){
            //방법1]setProgress()
            //progressbar.setProgress(progressbar.getProgress()+10);
            //방법2]incrementProgressBy()
            progressbar.incrementProgressBy(10);
        }
    }////////increase

    public void decrease(View view) {
        if(progressbar.getProgress() > progressbar.getMin()){
            //방법1]setProgress()
            //progressbar.setProgress(progressbar.getProgress()-10);
            //방법2]incrementProgressBy()
            progressbar.incrementProgressBy(-10);
        }
    }////////decrease
    //[AsyncTask 연습]
    //AsyncTask를 상속받은 클래스 정의]
    /*
    AsyncTask<Param,Progress,Result>
    Param: execute()메소드나 doInBackground()메소드의 인자 타입
    Progress:onProgressUpdate()메소드의 인자 타입
    Result:doInBackround()의 반환타입.onPostExcecute()의 인자 타입이 됨
    */
    private class MyAsyncTask extends AsyncTask<String,Void,String>{
        //씨크바의 진행정도를 랜덤하게 설정하기위함
        private Random random = new Random();
        @Override
        protected String doInBackground(String... params) {
            //위젯 UI변경 불가-작업 스레드니까
            //execute(String ...params)호출시 전달된 문자열 출력]
            for(String param:params) Log.i("com.kosmo.thread",param);
            while(true){
                publishProgress();//onProgressUpdate(Void... values)호출됨
                SystemClock.sleep(100);
                if(isCancelled() || asyncseekbar1.getProgress()==asyncseekbar1.getMax() && asyncseekbar2.getProgress()==asyncseekbar2.getMax())
                    break;
            }

            return Arrays.toString(params);
        }///////////doInBackground
        //doInBackground(String... params)에서
        // publishProgress()호출시 마다 아래
        //메소드 호출됨
        @Override
        protected void onProgressUpdate(Void... values) {
            //위젯의 UI변경]
            //0.1초마다  시크바에 진행율 설정
            int bar1=random.nextInt(10)+1;
            asyncseekbar1.setProgress(asyncseekbar1.getProgress()+bar1);
            int bar2=random.nextInt(10)+1;
            asyncseekbar2.setProgress(asyncseekbar2.getProgress()+bar2);
            //텍스트뷰에 진행율 표시
            asynctext1.setText("1번 진행율:"+asyncseekbar1.getProgress());
            asynctext2.setText("2번 진행율:"+asyncseekbar2.getProgress());

        }/////////onProgressUpdate
        //doInBackground()메소드 정상종료시 호출되고
        //doInBackground()에서 반환한 값을 매개변수로 받는다
        @Override
        protected void onPostExecute(String result) {
            Log.i("com.kosmo.thread","doInBackground()에서 반환한 값:"+result);
        }//////////onPostExecute
        //doInBackground()메소드 실행중 cancel(true)메소드 호출시
        // 아래 메소드가 자동 호출됨.
        @Override
        protected void onCancelled() {
            myAsyncTask =null;
        }
    }/////////////////////
    private MyAsyncTask myAsyncTask;

    public void start(View view) {
        //[스레드로 구현시-AsyncTask이용]
        myAsyncTask = new MyAsyncTask();
        if(asyncseekbar1.getProgress()==asyncseekbar1.getMax() && asyncseekbar2.getProgress()==asyncseekbar2.getMax()){
            asyncseekbar1.setProgress(0);
            asyncseekbar2.setProgress(0);
        }
        myAsyncTask.execute("doInBackground()로 전달","두번째 문자열","세번째 문자열");
    }////////start

    public void stop(View view) {
        //onCancelled(String s) 혹은 onCancelled()가 호출됨.
        if(myAsyncTask !=null) myAsyncTask.cancel(true);
    }////////stop

    private void initView() {
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        seektext = (TextView) findViewById(R.id.seektext);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        asynctext1 = (TextView) findViewById(R.id.asynctext1);
        asyncseekbar1 = (SeekBar) findViewById(R.id.asyncseekbar1);
        asynctext2 = (TextView) findViewById(R.id.asynctext2);
        asyncseekbar2 = (SeekBar) findViewById(R.id.asyncseekbar2);
    }
}/////////////class