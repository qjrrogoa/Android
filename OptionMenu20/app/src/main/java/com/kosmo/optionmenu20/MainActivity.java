package com.kosmo.optionmenu20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        layout = findViewById(R.id.mylayout);
    }

    //콜백 메소드:옵션 메뉴가 생성(xml전개혹은 자바코드)될때 자동으로 호출되는 메소드

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //방법1] 메뉴용 xml로 옵션 메뉴 전개
        //getMenuInflater().inflate(R.menu.option_menu,menu);
        //방법2]자바코드만로 옵션 메뉴 생성(메뉴용 xml파일 불필요)
        /*
        add(int groupId,int itemId,int order,CharSequence title)메소드로 메뉴 추가
		인자 설명]
		groupId:그룹아이디로 그룹에 포함되않은 경우는 Menu.NONE이나 0설정
		itemId:메뉴 아이템의 아이디,필요 없을 경우 Menu.NONE 이나 0
		order:메뉴 아이템의 순서.순서를 지정하고 싶지 않으면 Menu.NONE 이나 0
		title:메뉴명
         */
        menu.add(Menu.NONE,101,1,"옵션메뉴1");//옵션메뉴(...)에 생성됨
        menu.add(Menu.NONE,102,2,"옵션메뉴2").setIcon(R.drawable.away).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(Menu.NONE,103,3,"옵션메뉴3").setIcon(R.drawable.offline).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM|MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(Menu.NONE,105,4,"RED");
        menu.add(Menu.NONE,106,5,"GREEN");
        menu.add(Menu.NONE,107,6,"BLUE");

        SubMenu subMenu= menu.addSubMenu(Menu.NONE,107,3,"서브메뉴");
        subMenu.add(Menu.NONE,1,1,"서브메뉴1");
        subMenu.add(Menu.NONE,2,2,"서브메뉴2");
        subMenu.add(Menu.NONE,3,3,"서브메뉴3");

        return super.onCreateOptionsMenu(menu);
    }

    //콜백메소드:옵션 메뉴 아이템을 선택했을때 자동으로 호출되는 메소드


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 101:
            case 102:
            case 103:
            case R.id.opt_menu1:
            case R.id.opt_menu2:
            case R.id.opt_menu3:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case 105:
            case R.id.menuitem_red:
                layout.setBackgroundColor(Color.RED);break;
            case 106:
            case R.id.menuitem_green:
                layout.setBackgroundColor(Color.GREEN);break;
            case 107:
            case R.id.menuitem_blue:
                layout.setBackgroundColor(Color.BLUE);break;

        }
        return super.onOptionsItemSelected(item);
    }
}