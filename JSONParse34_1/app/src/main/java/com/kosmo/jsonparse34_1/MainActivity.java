package com.kosmo.jsonparse34_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void jsonParse(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1]raw폴더에 있는 데이타 가져오기
                    InputStream is = getResources().openRawResource(R.raw.json);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String data;
                    StringBuffer buf = new StringBuffer();
                    while((data=br.readLine())!=null){
                        buf.append(data);
                    }
                    br.close();
                    Log.i("com.kosmo.jsonparse",buf.toString());
                    //2]읽어온 문자열 데이타를 JSON으로 파싱하기
                    //new JSONObject("JSON형식의 문자열")
                    JSONObject json = new JSONObject(buf.toString());
                    Log.i("com.kosmo.jsonparse","이름:"+json.getString("name"));
                    Log.i("com.kosmo.jsonparse","나이:"+json.getInt("age"));
                    JSONArray array=json.getJSONArray("hobbys");
                    Log.i("com.kosmo.jsonparse","취미:"+array.toString());
                    JSONObject loginJson=json.getJSONObject("login");
                    Log.i("com.kosmo.jsonparse","로그인 정보-"+
                            String.format("아이디:%s,비번:%s",loginJson.get("user"),loginJson.getString("pass")));
                }
                catch(Exception e){
                    e.printStackTrace();
                }



                /*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //여기서는 UI변경 가능
                    }
                });*/
            }
        }).start();
    }
    Disposable rxJava;
    public void jsonArrayparse(View view){
        rxJava = Observable.fromCallable(()->{
            //run()메소드 혹은 doInBackground() 작업 코딩
            //백그라운드 작업 시작
            //여기서 UI 업데이트 하지 말자
            //1]raw폴더에 있는 데이타 가져오기
            InputStream is = getResources().openRawResource(R.raw.json_array);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data;
            StringBuffer buf = new StringBuffer();
            while((data=br.readLine())!=null){
                buf.append(data);
            }
            br.close();
            Log.i("com.kosmo.jsonparse",buf.toString());
            return buf.toString();//반환값이 subscribe()메소드의 인자로 전달된다
            //백그라운드 작업 끝
            }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result)->{
                    //여기사 UI 업데이트
                    //백그라운드 작업이 끝났을때 실행되는 메소드 즉 onPostExecute()부분이다
                    JSONArray jsonArray = new JSONArray(result);
                    for(int i=0; i < jsonArray.length();i++){
                        JSONObject json =jsonArray.getJSONObject(i);
                        Log.i("com.kosmo.jsonparse","==============["+(i+1)+"번째]===============");
                        Log.i("com.kosmo.jsonparse","이름:"+json.getString("name"));
                        Log.i("com.kosmo.jsonparse","나이:"+json.getInt("age"));
                        JSONArray array=json.getJSONArray("hobbys");
                        Log.i("com.kosmo.jsonparse","취미:"+array.toString());
                        JSONObject loginJson=json.getJSONObject("login");
                        Log.i("com.kosmo.jsonparse","로그인 정보-"+
                                String.format("아이디:%s,비번:%s",loginJson.get("user"),loginJson.getString("pass")));
                    }

                    //작업이 complete되었음으로 스레드 중지
                    rxJava.dispose();

                });

    }
}