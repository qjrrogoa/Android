package com.kosmo.sqlite31_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

//※SQLiteOpenHelper클래스를 상속받아서 생성자와 onCreate()
//메소드로에서 데이타베이스와 테이블을 생성하지 않고
//SQLiteDatabase클래스의 openOrCreateDatabase()메소드로
//데이타베이스 생성하기]
public class MainActivity extends AppCompatActivity {

    //데이타 베이스 생성용]
    private SQLiteDatabase sqLiteDatabase;
    //select쿼리결과 저장용]
    private Cursor cursor;

    private AlertDialog alertDialog;
    private EditText editDatabase,editTable,editInsert;
    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        editDatabase = findViewById(R.id.editdatabase);
        editTable = findViewById(R.id.edittable);
        editInsert = findViewById(R.id.editinsert);
        tvResult = findViewById(R.id.tvresult);

        alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("데이타 베이스")
                .setPositiveButton("확인",null)
                .create();

    }
    public void createDatabase(View view){
        //1.데이타베이스명 입력여부 판단]
        if(editDatabase.getText().toString().trim().length()==0){
            alertDialog.setMessage("데이타베이스명을 입력하세요");
            alertDialog.show();
            return;
        }
        //SQLiteDatabase openOrCreateDatabase(String 데이타베이스명,int 모드,Cursorfactory)
        //Cursorfactory는 null지정
        //※데이타 베이스는 /data/data/패키지명/databases에 생성됨.
        //2.데이타베이스 존재유무 판단]
        File file = new File("/data/data/"+getPackageName()+"/databases/"+editDatabase.getText().toString().trim());
        if(file.exists()){
            alertDialog.setMessage("이미 존재하는 데이타베이스명 입니다");
            alertDialog.show();
            return;
        }
        //3.데이타 베이스 생성]
        sqLiteDatabase=openOrCreateDatabase(
                editDatabase.getText().toString().trim(),
                MODE_PRIVATE,
                null);
        //Pie버전부터는 아래코드 추가해야 함
        sqLiteDatabase.disableWriteAheadLogging();
        Toast.makeText(this,"데이타베이스가 생성되엇어요",Toast.LENGTH_SHORT).show();
        //입력상자 비활성화
        editDatabase.setEnabled(false);
        //버튼 비활성화
        view.setEnabled(false);
    }
    public void createTable(View view){

    }
    public void insert(View view){

    }
    public void select(View view){

    }
}