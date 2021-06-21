package com.kosmo.fileio26_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    //메모리 저장용 멤버 변수]
    private Map memory = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("shared",MODE_PRIVATE);
    }

    public void saveInMemory(View view){
        //메모리(컬렉션-ram)에 데이타 저장]
        memory.put("map","메모리에 데이타 저장");
    }
    public void readInMemory(View view){
        //앱의 메모리(변수)에 저장된 데이타는 onDestory()시 삭제된다
        //즉 back버튼 클릭시 정상종료(onDestory()까지 호출됨)
        //시 데이타 가 사라진다
        if(!memory.containsKey("map")){
            Toast.makeText(this,"컬렉션에 저장된 데이타가 없어요",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this,memory.get("map").toString(),Toast.LENGTH_SHORT).show();
    }
    public void saveInFile(View view) throws IOException {
        //내장 메모리에 파일 생성후 데이타 쓰기]
        //위치:/data/data/패키지명/files
        FileOutputStream fos=openFileOutput("inner.dat",MODE_PRIVATE);
        fos.write("내장 메모리에 저장한 데이타\r\n앱 삭제나 데이타 삭제시에만 삭제됨".getBytes());
        fos.close();
    }
    public void readInFile(View view)  {
        try {
            //내장메모리에 있는 파일에서 데이타 읽어오기]
            FileInputStream fis = openFileInput("inner.dat");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            Toast.makeText(this, new String(bytes), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this,"파일 읽기시 오류", Toast.LENGTH_SHORT).show();
        }
    }
    public void saveInShared(View view){
        //파일 IO사용안하고 내장 메모리의 shred_prefs폴더에
        //shared.xml파일이 생성됨.
        //위치:/data/data/패키지명/shared_prefs
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("SHARED","shared.xml에 저장한 데이타");
        editor.commit();
    }
    public void readInShared(View view){
        Toast.makeText(this,preferences.getString("SHARED","키가 존재하지 않아요"), Toast.LENGTH_SHORT).show();
    }
}
