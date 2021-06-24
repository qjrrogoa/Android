package com.kosmo.retrofit33_2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText id;
    private EditText pwd;
    private Button btnPlain;
    private Button btnJson;
    private Button btnJsonArray;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        initView();
        //버튼에 리스너 부착
        btnPlain.setOnClickListener(handler);
        btnJson.setOnClickListener(handler);
        btnJsonArray.setOnClickListener(handler);
    }////////////////onCreate
    private View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //아이디와 비번 얻기
            String username=id.getText().toString().trim();
            String password=pwd.getText().toString().trim();

            if(v == btnPlain){

                SpringApiService springApiService=getSpringApiService(ScalarsConverterFactory.create());
                //요청을 보내기 위한 Call객체 생성
                Call<String> call=springApiService.getMemberText(username,password);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Log.i("com.kosmo.retrofit","문자열로 응답:200 성공");
                            Toast.makeText(v.getContext(),response.body(),Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Log.i("com.kosmo.retrofit","문자열로 응답:not 200 성공아님");
                        }

                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("com.kosmo.retrofit",t.getMessage());
                    }
                });
            }
            else if(v == btnJson){

                SpringApiService springApiService=getSpringApiService(JacksonConverterFactory.create());

                //요청을 보내기 위한 Call객체 생성
                Call<HashMap<String,String>> call=springApiService.getMemberJson(username,password);

                call.enqueue(new Callback<HashMap<String, String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        if(response.isSuccessful()){

                            HashMap<String,String> map=response.body();
                            Toast.makeText(v.getContext(),map.get("isLogin"),Toast.LENGTH_SHORT).show();
                        }
                        else{

                        }
                    }
                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

                    }
                });
            }
        }
    };//////////////////////////////////

    private SpringApiService getSpringApiService(Converter.Factory factory) {
        return new Retrofit.Builder()
                .addConverterFactory(factory)
                .baseUrl("http://192.168.0.25:9090/")
                .build()
                .create(SpringApiService.class);
    }////////////////

    private void initView() {
        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
        btnPlain = (Button) findViewById(R.id.btn_plain);
        btnJson = (Button) findViewById(R.id.btn_json);
        btnJsonArray = (Button) findViewById(R.id.btn_json_array);
        textResult = (TextView) findViewById(R.id.text_result);
    }
}