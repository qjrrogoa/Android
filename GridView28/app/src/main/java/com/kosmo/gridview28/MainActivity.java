package com.kosmo.gridview28;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //이미지 리소스 아이디 배열]
    private int[] resIds={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,
                          R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,
                          R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};

    //영화 제목]
    private String[] movies={"조커","보통의 연예","제미니",
                             "퍼펙트맨","소피와 드래곤","장사리",
                             "세계를 찾아서","벌새","판소리 복서"};

    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯얻기]
        gridView = findViewById(R.id.gridview);
        //어댑터 생성]
        CustomAdapter adapter = new CustomAdapter(this,movies,resIds);
        //연결]그리드뷰.setAdapter()
        gridView.setAdapter(adapter);
        //※리스트뷰와는 다르게 다른 어댑터뷰들은 아이템뷰를 커스텀뷰(내가 만든 뷰)
        //  로 구성시 커스텀 어댑터에서 이벤트 처리를 해도 되고
        //  어댑터뷰(그리드뷰)에 리스너를 부착해도 된다.
        //  그러나 리스트뷰는 커스텀뷰로 구성시 반드시 커스텀 어탭터에서 이벤트 처리 해야한다.
        //이벤트 처리]
        //그리드뷰에 리스너 부착-setOnItemClickListener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //다이얼로그용 레이아웃 전개
                View dialogView=View.inflate(MainActivity.this,R.layout.dialog_layout,null);
                //다이얼 로그용 이미지에 클릭한 해당 이미지 설정]
                ImageView movieDialog=dialogView.findViewById(R.id.movieDialog);
                movieDialog.setImageResource(resIds[position]);

                new AlertDialog.Builder(parent.getContext())
                        .setIcon(android.R.drawable.ic_menu_search)
                        .setTitle(movies[position])
                        .setView(dialogView)
                        .show();
            }
        });
    }///////////onCreate
}/////////////////