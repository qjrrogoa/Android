package com.kosmo.spinner28;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //성별과 이동통신사 데이타 ]
    private String[] genders={"남자","여자","트랜스 젠더"};
    private String[] telecoms = {"LG 텔레콤","SK 텔레콤","KT(한국통신)"};
    //영화 데이타]res폴더의 values디렉토리의 arrays.xml로 데이타 준비
    private String[] movies;

    //※앱 최초 실행시 스피너의 onSelectedItem()메소드 안의
    // 로직을 실행하지 않기 위한 플래그 변수
    private boolean isAutoSelected;

    private Spinner genderSpinner;
    private Spinner telecomSpinner;
    private Spinner movieSpinner;
    private Button btnResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        genderSpinner=findViewById(R.id.genderSpinner);
        telecomSpinner = findViewById(R.id.telecomSpinner);
        movieSpinner = findViewById(R.id.movieSpinner);
        btnResult = findViewById(R.id.btnresult);

        //어댑터 생성]
        ArrayAdapter genderAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,genders){

        };
        ArrayAdapter telecomAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,telecoms);
        //방법1]getResources()사용
        movies=getResources().getStringArray(R.array.movies);
        ArrayAdapter movieAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,movies);
        //방법2]ArrayAdapter의 정적 메소드인 createFromResource()메소드 사용
        //ArrayAdapter movieAdapter = ArrayAdapter.createFromResource(this,R.array.movies,android.R.layout.simple_spinner_dropdown_item);

        //스피터와 어댑터 연결]스피너.setAdapter(어댑터)
        genderSpinner.setAdapter(genderAdapter);
        telecomSpinner.setAdapter(telecomAdapter);
        movieSpinner.setAdapter(movieAdapter);

        //스피너에 리스너 부착]-setOnItemSelectedListener
        movieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.i("com.kosmo.spinner","onItemSelected invoked");
                if(!isAutoSelected){
                    isAutoSelected=true;
                    return;
                }
                //1.배열 사용-비추천
                //Toast.makeText(parent.getContext(),movies[position]+"선택",Toast.LENGTH_SHORT).show();
                //2.어댑터뷰의 getItemAtPosition(position)메소드
                //Toast.makeText(parent.getContext(),movieSpinner.getItemAtPosition(position)+"선택",Toast.LENGTH_SHORT).show();
                //3. 어댑터의 getItem(position) 사용
                //Toast.makeText(parent.getContext(),movieAdapter.getItem(position)+"선택",Toast.LENGTH_SHORT).show();

                Toast.makeText(parent.getContext(),((TextView)view).getText().toString()+"선택",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("com.kosmo.spinner","onNothingSelected invoked");
            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = genderSpinner.getSelectedItem().toString();
                String telecom = telecomSpinner.getItemAtPosition(telecomSpinner.getSelectedItemPosition()).toString();
                String movie = ((TextView)movieSpinner.getSelectedView()).getText().toString();
                Toast.makeText(v.getContext(),
                        String.format("성별:%s,통신사:%s,영화:%s",gender,telecom,movie),Toast.LENGTH_SHORT).show();
            }
        });

    }
}