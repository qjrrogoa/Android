package com.kosmo.thread32_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.function.Consumer;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/*
 작업스레드안에서 위젯의 UI변경시 ANR발생
 메인 스레드(뷰를 생성한 곳)가 아닌 스레드에서 UI 개체를 수정하거나 참조하려고
 하면 예외, 자동 실패, 비정상 종료, 기타 정의되지 않은 오동작이 그 결과로
 발생할 수 있다
Only the original thread that created a view hierarchy can touch its views.
 */
public class MainActivity extends AppCompatActivity {

    private int mainNumber,threadNumber;
    private TextView textMain,textThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 얻기]
        textMain = findViewById(R.id.tvMainNumber);
        textThread = findViewById(R.id.tvThreadNumber);
        /*
        작업 내용:
        버튼 클릭시 mainNumber는  1증가시키고
        threadNumber는 스레드내에서 시간 간격(0.5초마다)을 두고 1씩 증가 시켜
        UI스레드인 MainActivity
        의 텍스트뷰에 그 값을 출력한다
        작업 결과:
        버튼을 클릭할때마다  mainNumber는 1씩 증가하고
        threadNumber도 0.5초마다 1씩 증가한다.
        */
    }////////////////
    private class WorkingThread extends  Thread{
        @Override
        public void run() {

            try {
                while (true) {
                    threadNumber++;
                    //try~catch불필요:안드로이드 OS에 있는 클래스
                    sleep(500);//SystemClock.sleep(500);//
                    //textThread.setText(String.valueOf(threadNumber));//ANR발생 테스트용(연속해서 계속 버튼 누르기)

                    //방법1]

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //메인스레드에 부착된 위젯의 UI변경
                        textThread.setText(String.valueOf(threadNumber));
                    }
                });
                    //방법2]
                    /*textThread.post(new Runnable() {
                        @Override
                        public void run() {
                            //메인스레드에 부착된 위젯의 UI변경
                            textThread.setText(String.valueOf(threadNumber));
                        }
                    });*/
                }///while

            }///try
            catch(InterruptedException e){
                e.printStackTrace();
            }


        }////run
    }////////////
    //ANR 발생
    public void error(View view){
        new WorkingThread().start();
    }
    private WorkingThread workingThread;
    public void send(View view){
        if(workingThread ==null || !workingThread.isAlive()){
            workingThread = new WorkingThread();
            workingThread.setDaemon(true);

            workingThread.start();
        }
        mainNumber++;
        textMain.setText(String.valueOf(mainNumber));
    }
    //[방법1]
     /*
     스레드 클래스(작업스레드)의 run()메소드안에서
      runOnUiThread()메소드 호출
      runOnUiThread()메소드안에서 위젯의 UI변경
     */
    public void runonui(View view){
        send(view);
    }
    //[방법2]
    /*
      스레드 클래스(작업스레드)의 run()메소드안에서
          위젯.post()메소드 호출
         위젯.post()메소드안에서 위젯의 UI변경
    */
    public void widgetpost(View view){
        send(view);
    }

    private MyAsyncTask myAsyncTask;

    public void asynctask(View view){
        mainNumber++;
        textMain.setText(String.valueOf(mainNumber));
        if(myAsyncTask ==null){
            myAsyncTask = new MyAsyncTask();
            //스레드 실행]
            myAsyncTask.execute();
        }
    }
    /*
    RxJava는 Reactive Java
    자바에서 Reactive Programming을  구현하기 위해서 나온 라이브러리
    Reactive Programming은 비동기 데이터 흐름을 중시하는 프로그래밍
    원격서버 (API서버)에서 데이터 입출력을 하여도 메인쓰레드와 방해하지 않는것을
    중시하는 프로그래밍이다다    라이브러리 추가
    implementation group: 'io.reactivex.rxjava3', name: 'rxjava', version: '3.0.13'
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
     */



    Observer myObserver = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("com.kosmo.thread","onSubscribe thread:"+Thread.currentThread().getName());
        }

        @Override
        public void onNext(Object value) {
            Log.d("com.kosmo.thread","on next, valor:<<"+value.toString()+">> \n nombre hilo:"+Thread.currentThread().getName());
        }

        @Override
        public void onError(Throwable e) {
            Log.d("com.kosmo.thread","error "+e.toString());
        }

        @Override
        public void onComplete() {
            Log.d("com.kosmo.thread","onCompleted thread:"+Thread.currentThread().getName());
        }
    };
    private Disposable rxJava;

    public void asyncTask(){
        //백그라운드 작업(run()메소드에서 하는 작업)을 하기전 실행할 일
        //예: 프로그래스 창 띄우기기
        //onPreExecute()부분
        Toast.makeText(this,"백그라운드 작업전 할 일",Toast.LENGTH_SHORT).show();

        rxJava = Observable.fromCallable(()->{
            //run()메소드 혹은 doInBackground() 작업 코딩
            //백그라운드 작업 시작
            //여기서 UI 업데이트 하지 말자
            while(true){
                threadNumber++;
                SystemClock.sleep(500);
                textThread.setText(String.valueOf(threadNumber));
                //dispose()메소드 호출시 스레드 종료
                if(rxJava.isDisposed()) break;
            }

            return String.valueOf(threadNumber);//반환값이 subscribe()메소드의 인자로 전달된다
            //백그라운드 작업 끝
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result)->{
                    //여기사 UI 업데이트
                    //백그라운드 작업이 끝났을때 실행되는 메소드 즉 onPostExecute()부분이다
                    //textThread.setText(String.valueOf(result));

                    //작업이 complete되었음으로 스레드 중지
                    rxJava.dispose();
                    Log.i("com.kosmo.thread","RxJava:result="+result);
                });



    }//////////////
    public void rxJava(View view){
        mainNumber++;
        textMain.setText(String.valueOf(mainNumber));
        asyncTask();
    }
    public void stop(View view){
        //Thread상속받은 스레드 클래스를 직접 만들어서 사용할때

        if(workingThread !=null && workingThread.isAlive()){
            workingThread.interrupt();//interrupt() 호출시 InterruptedException예외 발생
        }
        //AsyncTask를 사용할때
        if(myAsyncTask !=null) myAsyncTask.cancel(true);
        //RxJava 사용시
        if(rxJava !=null) rxJava.dispose();
    }
    //[방법3]
     /*
        API LEVEL 30이후 DEPRECATED
        1.스레드 클래스 불필요
        2.AsyncTask상속받아서 주요 메소드 오버라딩한 후
          실행시킬때는 AsyncTask객체.execute(파라티터들)
          실행시킬때 파라미터들이 doInBackground(varArgs )의 매개변수로 전달됨.
        3.AsyncTask로 실행한 스레드를 중지할때는 AsyncTask객체.cancel(true)
     */
    private class MyAsyncTask extends AsyncTask<Void,Void,Void>{//<Params, Progress, Result>
        //doInBackground()호출전에 선행작업이 있을때
        @Override
        protected void onPreExecute() {
            Log.i("com.kosmo.thread","onPreExecute() invoked");
        }
        //doInBackground()작업이 끝난후
        @Override
        protected void onPostExecute(Void unused) {
            Log.i("com.kosmo.thread","onPostExecute() invoked");
        }
        //doInBackground()에서 publishProgress();를 호출할때마다
        //자동으로 호출됨.
        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i("com.kosmo.thread","onProgressUpdate() invoked");
            textThread.setText(String.valueOf(threadNumber));
        }
        //스레드 중지시
        @Override
        protected void onCancelled() {
            Log.i("com.kosmo.thread","onCancelled() invoked");
        }
        //필수:doInBackground(Void... params) -작업스레드의  run()역할
        @Override
        protected Void doInBackground(Void... voids) {
            //작업 스레드의 run()메소드에서 수행할 코드 작성-위젯의 UI변경시 ANR발생
            Log.i("com.kosmo.thread","doInBackground() invoked");
            while(true){
                threadNumber++;
                SystemClock.sleep(500);
                //아래 메소드를 호출해야 onProgressUpdate()가 호출됨.
                publishProgress();
                //cancel()메소드 호출시 스레드 종료
                if(isCancelled()) break;
            }
            return null;
        }////////
    }

}