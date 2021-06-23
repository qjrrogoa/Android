package com.kosmo.sqlite31_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//1]SQLiteOpenHelper 상속
/*
    A]생성자 정의-데이터베이스 생성하도록 정의
    B]onCreate()오버라이딩-테이블 생성
    C]onUpgrade()오버라이딩-데이타베이스 버전 변경시 (스키마 변경시)

 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String databaseName, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        //context: Activity등의 Context 인스턴스
        //databaseName: 데이터베이스의 이름
        //null: 커서 팩토리(보통 null지정)
        //version: 데이터베이스 스키마 버전
        super(context, databaseName, factory, version);
        Log.i(MainActivity.TAG,"MySQLiteOpenHelper의 생성자");
    }
    //B]테이블 생성
    //SQLiteDatabase getWritableDatabase()혹은 getReadableDatabase()호출시
    //딱 한번만 호출됨.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //같은 이름의 테이블이 존재한다면 삭제
        db.execSQL("DROP TABLE IF EXISTS "+MainActivity.TABLE_NAME);

        //테이블 생성
        //※SimpleCursorAdpapter사용시 반드시 _id컬럼이 존재해야됨.
        //※autoincrement 설정시 그 컬럼은 반드시 primary key로 설정해야 한다.
        db.execSQL(String.format("CREATE TABLE %s(_id INTEGER PRIMARY KEY AUTOINCREMENT,user TEXT,name TEXT,age INTEGER DEFAULT 1,regidate DATETIME)",MainActivity.TABLE_NAME));
        Log.i(MainActivity.TAG,"MySQLiteOpenHelper의 onCreate()");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //버전 업그레이드시
        //새롭게 테이블 생성 구문 실행]
        if(oldVersion < newVersion){
            onCreate(db);
        }
        Log.i(MainActivity.TAG,"MySQLiteOpenHelper의 onUpgrade()");
    }
    //Pie버전 이후부터는 아래 코드 추가]

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
        Log.i(MainActivity.TAG,"MySQLiteOpenHelper의 onOpen()");
    }
}
