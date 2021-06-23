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
            //return;
        }
        //3.데이타 베이스 생성]
        sqLiteDatabase=openOrCreateDatabase(
                editDatabase.getText().toString().trim(),
                MODE_PRIVATE,
                null);
        //Pie버전부터는 아래코드 추가해야 함
        sqLiteDatabase.disableWriteAheadLogging();
        Toast.makeText(this,"데이타베이스가 생성되었어요",Toast.LENGTH_SHORT).show();
        //입력상자 비활성화
        editDatabase.setEnabled(false);
        //버튼 비활성화
        view.setEnabled(false);
    }
    public void createTable(View view){
        //1.반드시 데이타 베이스 생성후에 테이블 생성]
        if(sqLiteDatabase ==null){
            alertDialog.setMessage("먼저 데이타베이스를 생성하세요");
            alertDialog.show();
            return;
        }
        //2.테이블명 입력여부 판단]
        String tableName = editTable.getText().toString().trim();
        if(tableName.length()==0){
            alertDialog.setMessage("테이블명을 입력하세요");
            alertDialog.show();
            return;
        }
        //3.기존 테이블 존재시 테이블 삭제]
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
        //4.테이블 생성]
        sqLiteDatabase.execSQL("CREATE TABLE "+tableName+"(_id integer primary key autoincrement,name text not null)");
        Toast.makeText(this,"테이블이 생성되었어요",Toast.LENGTH_SHORT).show();
        //입력상자 비활성화
        editTable.setEnabled(false);
        //버튼 비활성화
        view.setEnabled(false);
    }
    public void insert(View view){
        //1.테이블 생성여부 판단]
        if(editTable.isEnabled()){
            alertDialog.setMessage("먼저 테이블을 입력하세요");
            alertDialog.show();
            return;
        }
        //2.이름 입력여부 판단]
        String name = editInsert.getText().toString().trim();
        if(name.equals("")){
            alertDialog.setMessage("먼저 이름을 입력하세요");
            alertDialog.show();
            return;
        }
        //3.데이타 입력]
        //SQLiteDatabase객체의 execSQL()메소드로 INSERT/DELETE/UPDATE
        sqLiteDatabase.execSQL("INSERT INTO "+editTable.getText().toString().trim()+"(name) VALUES('"+name+"')");
        //입력창 클리어
        editInsert.setText("");
        //포커스 주기]
        editInsert.requestFocus();
    }
    public void select(View view){
        if(sqLiteDatabase !=null){
            //데이타 조회]
            //SQLiteDatabase객체의 query()메소드나 rawQuery()메소드로조회]
            cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+editTable.getText().toString().trim(),null);
            tvResult.setText("");
            while(cursor.moveToNext()){
                //Cursor의 getXXXXX()메소드로 각 컬럼의 데이터 꺼내오기]
                //컬럼 인덱스는 0부터 시작(자바의 JDBC API는 1부터 시작)
                int _id=cursor.getInt(0);//컬럼인덱스
                String name = cursor.getString(cursor.getColumnIndex("name"));//컬럼명
                tvResult.append(String.format("번호:%s,이름:%s",_id,name));
            }
        }
    }
    //데이타 베이스 자원 반납
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cursor !=null) cursor.close();
        if(sqLiteDatabase !=null) sqLiteDatabase.close();
    }
}