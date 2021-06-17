package com.kosmo.fileio26_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edittext);
    }//////////////////////////
    //버튼 클릭시 레이아웃용 xml의 onClick속성에 지정한 메소드 정의]
    //※단, 버튼에만 사용가능
    /*
    접근 지정자:public
    반환타입:void
    메소드명:onClick속성에 지정한 메소드명
    매개변수:View타입*/
    public void fileWriterInMemory(View view) throws IOException {
        // /data/data/패키지/files 디렉토리에 파일 쓰기]-openFileOutput("파일명만",int mode)
        //파일 미 존재시 생성됨(/data/data/패키지명/files/indata.txt)
        //-기존 내용이 있는 경우 삭제됨(Activity.MODE_PRIVATE)
        //-기존 내용이 있는 경우 뒤에 추가됨(Activity.MODE_APPEDN)
        FileOutputStream fos= openFileOutput("Memory",MODE_PRIVATE);
        fos.write(editText.getText().toString().getBytes());
        fos.close();
        //내장메모리에 임의의 디렉토리 생성]
        //1]File getDir("mydir",Activity.MODE_PRIVATE);라고 설정시
        // /data/data/패키지명/app_mydir라는 디렉토리가 생성됨
        File file=getDir("mydir",MODE_PRIVATE);
        fos = new FileOutputStream(file.getAbsoluteFile()+File.separator+"Memory2.txt");
        fos.write("자바 IO로 파일 출력".getBytes());
        fos.close();
    }
    public void fileReaderInMemory(View view) throws IOException {
        // /data/data/패키지/files디렉토리에 있는 파일 읽기]-openFileInput("파일명만")
        FileInputStream fis= openFileInput("Memory");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        editText.setText(new String(bytes));
        fis.close();
        //내장 메모리의 임의의 디렉토리(app_mydir)에 저장된 파일 읽기]app_가 붙은 디렉토리
        File file = new File("/data/data/"+getPackageName()+"/app_mydir/Memory2.txt");
        fis = new FileInputStream(file);
        bytes = new byte[fis.available()];
        fis.read(bytes);
        editText.append("\r\n"+new String(bytes));
        fis.close();



    }
    public void fileReaderInRaw(View view) throws IOException {
        //res폴더 우클릭->new->Android resources Directory로 raw폴더 생성
        //※Resource Type을 raw선택
        //생성된 raw폴더에 파일 저장시는 DDMS에서 가 아닌
        //탐색기에서 직접 파일을 COPY & PASTE
        //파일 저장시 UTF-8로 저장(한글이 깨지지 않는다.)
        //혹은 raw폴더 우클릭->new->File로 직접 안드로이드에서 생성
        //Resources객체의 openRawResource(리소스아이디)메소드로 읽는다
        InputStream is=getResources().openRawResource(R.raw.readonly);
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        is.close();
        editText.setText(new String(bytes));
    }
    public void fileWriterInSD(View view) throws IOException {
         /*
        //저장할 파일 유형:미리 정의된 디렉터리 이름으로 시스템에서 파일이 올바르게 처리되도록 한다
        Environment.DIRECTORY_ALARMS:알람으로 사용할 오디오파일
        Environment.DIRECTORY_DCIM:카메라로 촬영한 사진
        Environment.DIRECTORY_DOWNLOADS:다운로드한 파일
        Environment.DIRECTORY_MUSIC:오디오파일
        Environment.DIRECTORY_MOVIES:동영상 파일
        Environment.DIRECTORY_PICTURES:이미지 파일
        Environment.DIRECTORY_RINGTONES:벨소리용 오디오파일
        Environment.DIRECTORY_NOTIFICATIONS:알림음으로 사용할 오디오파일
        null:미리 정의된 하위 디렉터리 이름 중 파일에 알맞은 이름이 없을 경우
        */
        File file=getExternalFilesDir(null);
        //Environment.DIRECTORY_DCIM지정시 : /storage/emulated/0/Android/data/com.kosmo.fileio26_01/files/DCIM
        //null지정시 : /storage/emulated/0/Android/data/com.kosmo.fileio26_01/files
        Log.i("com.kosmo.fileio",file.getAbsolutePath());

        FileWriter fw = new FileWriter(file.getAbsolutePath()+File.separator+"SDFile.txt");
        fw.write("안녕하세요\r\n안드로이드 수업입니다");
        fw.close();
    }
    public void fileReaderInSD(View view) throws IOException {
        FileReader fr = new FileReader(
                getExternalFilesDir(null).getAbsolutePath()+File.separator+"SDFile.txt");
        BufferedReader br = new BufferedReader(fr);
        String data;
        while((data=br.readLine())!=null){
            editText.append(data+"\r\n");
        }
        br.close();

    }
    public void dirMakeInMemory(View view){
        //File객체로  내장 메모리에 디렉토리 만들기]
        /*
        app_가  디렉토리명에 추가 되지 않는다.
        단, getDir()로 내장 메모리에 디렉토리 생성시에는
        app_가 디렉토리명 앞에 붙는다.
         */
        File file = new File("/data/data/"+getPackageName()+"/MYDIR");
        if(!file.exists()) file.mkdir();
    }
    public void dirMakeInSD(View view){
        File file = new File(getExternalFilesDir(null).getAbsolutePath()+File.separator+"/MYDIR");
        if(!file.exists()) file.mkdir();
    }
}