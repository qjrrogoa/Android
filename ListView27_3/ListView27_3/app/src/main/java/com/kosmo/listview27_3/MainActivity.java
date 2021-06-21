package com.kosmo.listview27_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView;
    private ArrayAdapter adapter;
    //데이타는 arrays.xml이나 컬렉션 사용]

    //아이템 동적 추가 및 삭제시 사용
    List items = new Vector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기
        editText = findViewById(R.id.edittext);
        listView = findViewById(R.id.listview);
        //2]어댑터 생성
        //방법1]ArrayAdapter의 정적메소드 createFromResource()사용
        //adapter=ArrayAdapter.createFromResource(this,R.array.mountains, android.R.layout.simple_list_item_single_choice);
        //adapter=ArrayAdapter.createFromResource(this,R.array.mountains, android.R.layout.simple_list_item_multiple_choice);
        //방법2]ArrayAdapter생성자 이용
        //adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,getResources().getStringArray(R.array.mountains));
        //컬렉션 사용
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,items);
        //3]어댑터와 리스뷰 연결:setAdapter()
        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//코드로 모드 변경



        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Single모드 일때
                //Toast.makeText(parent.getContext(),listView.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
                //Multiple모드 일때
                if(listView.isItemChecked(position))
                    Toast.makeText(parent.getContext(),listView.getItemAtPosition(position).toString()+"선택",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(parent.getContext(),listView.getItemAtPosition(position).toString()+"해제",Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void read(View view){//리스트뷰의 체크된 아이템을 토스트로 출력
        if(listView.getCount()==0) return;
        if(listView.getCheckedItemCount() ==0){
            Toast.makeText(this,"아이템을 먼저 선택하세요",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buf = new StringBuffer();
        for(int i=0; i< listView.getCount();i++){
            if(listView.isItemChecked(i))
                buf.append(listView.getItemAtPosition(i)+"\r\n");
        }
        Toast.makeText(this,buf,Toast.LENGTH_SHORT).show();

    }
    public void add(View view){//에디트 텍스트에 입력한 값을 리스트뷰의 아이템으로 추가
        if(editText.getText().toString().trim().length()==0){
            Toast toast=Toast.makeText(this,"추가할 아이템을 먼저 입력하세요?",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            return;
        }
        //1]에디터텍스트에 있는 입력문자열을 컬렉션에 추가
        items.add(editText.getText().toString());
        //2]텍스트에디트 클리어
        editText.setText("");
        //3]어댑터에게 데이타 변화통지
        adapter.notifyDataSetChanged();
    }
    public void remove(View view){
        if(listView.getCheckedItemCount()==0){
            Toast.makeText(this,"삭제할 아이템을 먼저 선택하세요",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //삭제전 사용자확인용 대화상자 띄우기]
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("삭제확인")
                .setMessage("정말 삭제 할래요?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //삭제처리
                        checkedItemsRemove();
                    }
                })
                .setNegativeButton("아니오",null)
                .show();
    }
    private void checkedItemsRemove(){
        //※리스트 계열 컬렉션은 앞에서부터 삭제시
        //앞으로 땡겨서 인덱스가 재배치됨.
        //그럼으로 outOfboound발생할 수 있다.
        //뒤에서 부터 삭제
        for(int i=items.size()-1;i >=0;i--){
            if(listView.isItemChecked(i)) items.remove(i);
        }
        //삭제후 체크가 안된 바로 전 아이템이 체크된 상태로 보인다
        //이를 해지 하기
        listView.clearChoices();
        //어댑터에게 데이터 변경 통지
        adapter.notifyDataSetChanged();
    }
}