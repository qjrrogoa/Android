package com.kosmo.sqlite31_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //디버깅용 상수
    public static final String TAG="com.kosmo.sqlite";
    //데이타베이스명 및 테이블명 상수 선언]
    public static final String DATABASE_NAME="ANDROID_DB";
    public static final String TABLE_NAME="MEMBER";
    //SQLiteOpenHelper클래스를 상속받는 클래스 선언]
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    //CRUD작업을 위한 클래스]
    private SQLiteDatabase sqLiteDatabase;
    //SELECT 쿼리결과 저장용 커서]
    private Cursor cursor;
    //리스트뷰와 커서(레코드 정보)를 쉽게 연결해주는 어댑터]
    //private SimpleCursorAdapter adapter;
    //나만의 커서 어댑터 사용시
    private MyCursorAdapter adapter;


    //SimpleCursorAdapter생성자에 인자로 전달할 변수 선언]
    //컬럼명을 담고 있는 String형 배열]
    private String[] from ={"user","name","age","regidate"};
    //위의 컬럼명에 해당하는 데이타를
    //표시할 뷰의 리소스 아이디 배열]-뷰는 반드시 TextView여야됨(SimpleCursorAdapter사용시).
    private int[] to={R.id.tvuser,R.id.tvname,R.id.tvage,R.id.tvdate};

    //데이타 베이스 버전관리용]
    int oldVersion = 1,newVerson=1;
    //선택여부 판단용 및 수정/삭제시 사용할 키값 저장변수]
    private int _id = -1;
    //경고 메시지 출력용
    private AlertDialog alertDialog;
    //위젯 저장용
    private EditText editUser,editName,editAge;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        editAge = findViewById(R.id.editage);
        editName=findViewById(R.id.editname);
        editUser=findViewById(R.id.edituser);
        listView = findViewById(R.id.listview);
        //1]SQLiteOpenHelper를 상속받은  MySQLiteOpenHelper객체 생성
        //데이타베이스가 생성됨
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this,DATABASE_NAME,null,oldVersion);
        //2]getWritableDatabase()메소드로 테이블 생성-onCreate()호출됨.
        //  이때 databases폴더에 ANDROID_DB가 생성됨:Device File Exploerer도구로 확인
        Log.i(TAG,"getWritableDatabase()호출 전");
        sqLiteDatabase=mySQLiteOpenHelper.getWritableDatabase();
        Log.i(TAG,"getWritableDatabase()호출 후");
        //전체 레코드 가져오기
        cursor=selectList();
        //4]SimpleCursorAdapter 생성
        /* context : 리스트뷰가 속해 있는 Context
	       layout:리스트뷰 하나의  아이템을 정의한 뷰 레이아웃 파일의 아이디.레이아웃 파일의 뷰들(위젯)은 적어도 "to"에 정의된 위젯의 아이디를 포함하고 있어야 한다.
           c :데이타베이스의 커서로 커서가 준비가 안되엇다면 null 지정가능
         from : UI에 바인딩 될 데이타를 나타내는 컬럼명 배열 .
	           커서가 준비가 안되엇다면 null 지정가능.
         to : "from" 매개변수에 정의된 컬럼명에 표시할 뷰의 리소스 아이디 배열.
	      ※이들 뷰는 모두 TextView여야한다.
	       커서가 준비가 안되엇다면 null 지정가능.

        flags:  어댑터의 행동을 결정하는 상수, CursorAdapter(Context, Cursor, int) 참조
        */
        //SimpleCursorAdapter는 컬럼의 데이타를 표시하는 뷰가 반드시 TextView여야한다
        //adapter = new SimpleCursorAdapter(this,R.layout.record_layout,cursor,from,to, CursorAdapter.NO_SELECTION);
        //나만의 커서 어댑터 적용하기
        adapter = new MyCursorAdapter(this,cursor,CursorAdapter.NO_SELECTION);


        //5]리스트뷰와 어댑터 연결
        listView.setAdapter(adapter);

        //6]리스트뷰에 리스너 부착
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor=(Cursor)adapter.getItem(position);
                //SELECT시 순서:"_id","user","name","age","regidate"
                editAge.setText(cursor.getString(3));
                editName.setText(cursor.getString(cursor.getColumnIndex("name")));
                editUser.setText(cursor.getString(cursor.getColumnIndex("user")));
                //아이템 클릭여부를 판단하기위한 변수 _id에 키값저장
                _id = cursor.getInt(0);

            }
        });

        //대화 상자 생성하기]
        alertDialog = new AlertDialog.Builder(this)
                      .setCancelable(false)
                      .setTitle("회원가입")
                      .setPositiveButton("확인",null).create();

    }
    private Cursor selectList() {
        if(sqLiteDatabase !=null){
            //query("테이블명",new String[]{조회할 컬럼명들},WHERE절,WHERE절 데이타,GROUP BY절 ,having절 ,ORRDR BY절)
            //※like절은 자바JDBC의 PreparedStatement객체 와 동일하게 사용해여 함 즉 '%?%'형식 안됨.
            //예] cursor=sqLiteDatabase.query(TABLE_NAME,new String[]{"_id","user","name","age","regidate"},"name LIKE '%' || ? || '%'",new String[]{"DONG"},null,null,"_id DESC");
            //SELECT user FROM MEMBER WHERE NAME LIKE '%' || 'DONG' || '%' ORDER BY _ID DESC
            //방법1]query("테이블명",조회할 컬럼명 배열,.....
            //반드시 _id컬럼을 추가해야 한다.그렇지 않으면 column '_id' does not exist에러)
            //cursor=sqLiteDatabase.query(TABLE_NAME,new String[]{"_id","user","name","age","regidate"},null,null,null,null,"_id DESC");
            //방법2]rawQuery("쿼리문",null)
            cursor=sqLiteDatabase.rawQuery("SELECT * FROM member ORDER BY _id DESC",null);
        }
        return cursor;
    }//////////////////////

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cursor !=null) cursor.close();
        if(sqLiteDatabase !=null) sqLiteDatabase.close();
    }

    public void init(View view){
        //버전 얻그레이드
        newVerson++;
        if(sqLiteDatabase !=null){
            //MySQLiteOpenHelper의 onUpgrade()호출
            mySQLiteOpenHelper.onUpgrade(sqLiteDatabase,oldVersion,newVerson);
            //oldVersion을 newVersion으로 초기화
            oldVersion = newVerson;
            //데이타 변경 반영]
            notifyAdapter();
        }
    }
    public void insert(View view){
        //입력 값 얻기]
        String user = editUser.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        //날짜 입력용]
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String regidate=dateFormat.format(new Date());
        if(sqLiteDatabase !=null){
            //방법1]execSQL(쿼리문자열)
            //sqLiteDatabase.execSQL("INSERT INTO MEMBER(USER,NAME,AGE,REGIDATE) VALUES('"+user+"','"+name+"',"+age+",'"+regidate+"')");
            //방법2]execSQL(쿼리문자열,데이타가 저장된 String형 배열)
            //sqLiteDatabase.execSQL("INSERT INTO MEMBER(USER,NAME,AGE,REGIDATE) VALUES(?,?,?,?)",new String[]{user,name,age,regidate});
            //방법3]ContentValue와 insert()메소드 이용
            //※키값은 컬럼명으로....
            ContentValues values = new ContentValues();
            values.put("name",name);
            values.put("user",user);
            values.put("age",age);
            values.put("regidate",regidate);
            sqLiteDatabase.insert(TABLE_NAME,null,values);
            //데이타변경 어댑터에 통지 및 텍스트 클리어 및 전체 레코드 얻기
            notifyAdapter();
        }
    }////////////////

    private void notifyAdapter() {
        //변경된 데이타를 커서에 다시 반영]
        cursor=selectList();
        //변경된 데이타가 반영된 커서를 다시 어댑터에 설정]
        adapter.changeCursor(cursor);
        //변경된 데이타가 반영된 커서를 다시 어댑터에 설정]
        adapter.notifyDataSetChanged();
        //모든 텍스트 클리어]
        clearText();
    }
    private void clearText() {
        editAge.setText("");
        editName.setText("");
        editUser.setText("");
        //포커스 주기]
        editUser.requestFocus();
    }
    public void update(View view){
        if(_id == -1){
            alertDialog.setMessage("수정할 레코드를 먼저 선택하세요");
            alertDialog.show();
        }
        //입력 값 얻기]
        String user = editUser.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String age = editAge.getText().toString().trim();

        if(sqLiteDatabase !=null) {
            //방법1]execSQL(쿼리문자열)
            //sqLiteDatabase.execSQL("UPDATE MEMBER SET user='"+user+"',name='"+name+"',age="+age+" WHERE _id="+ _id);
            //방법2]execSQL(쿼리문자열,데이타가 저장된 String형 배열)
            //sqLiteDatabase.execSQL("UPDATE member SET user=?,name=?,age=? WHERE _id=?",new String[]{user,name,age,_id});
            //방법3]ContentValue와 update()메소드 이용
            //※키값은 컬럼명으로....
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("user", user);
            values.put("age", age);
            sqLiteDatabase.update(TABLE_NAME, values, "_id=?", new String[]{String.valueOf(_id)});
            //데이타변경 어댑터에 통지 및 텍스트 클리어 및 전체 레코드 얻기
            notifyAdapter();
            //다시 -1로 초기화
            _id = -1;
        }

    }////////////////update
    public void delete(View view){
        if(_id == -1){
            alertDialog.setMessage("삭제할 레코드를 먼저 선택하세요");
            alertDialog.show();
        }
        //입력 값 얻기]
        String user = editUser.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String age = editAge.getText().toString().trim();

        if(sqLiteDatabase !=null) {
            //방법1]execSQL(쿼리문자열)
            //sqLiteDatabase.execSQL("DELETE FROM member WHERE _id="+ _id);
            //방법2]execSQL(쿼리문자열,데이타가 저장된 String형 배열)
            //sqLiteDatabase.execSQL("DELETE FROM member WHERE _id=?",new String[]{String.valueOf(_id)});
            //방법3]delete()메소드 이용

            sqLiteDatabase.delete(TABLE_NAME,"_id=?",new String[]{String.valueOf(_id)});
            //데이타변경 어댑터에 통지 및 텍스트 클리어 및 전체 레코드 얻기
            notifyAdapter();
            //다시 -1로 초기화
            _id = -1;
        }

    }
}