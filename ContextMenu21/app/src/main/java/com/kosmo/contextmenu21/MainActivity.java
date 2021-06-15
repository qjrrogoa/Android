package com.kosmo.contextmenu21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        linearLayout = findViewById(R.id.linearLayout);
        button = findViewById(R.id.button);
        //위젯(뷰) 롱 클릭시 컨텍스트 메뉴가  뜨도록 설정]
        registerForContextMenu(button);
        registerForContextMenu(linearLayout);

    }
    //컨텐스트 메뉴 전개(생성)하기위한 오버라이딩
    //두번째 인자 View:컨텍스트 메뉴가 등록된 뷰를 의미
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater= getMenuInflater();
        if(v == button){

            //menuInflater.inflate(R.menu.button_menu,menu);
            menu.add(Menu.NONE,1,1,"RED");
            menu.add(Menu.NONE,2,2,"GREEN");
            menu.add(Menu.NONE,3,3,"BLUE");

        }
        else{
            //menuInflater.inflate(R.menu.layout_menu,menu);
            menu.add(Menu.NONE,4,4,"레이아웃 컨텍스트");
            SubMenu submenu= menu.addSubMenu(Menu.NONE,100,5,"서브메뉴");
            submenu.add(Menu.NONE,5,5,"서브메뉴1");
            submenu.add(Menu.NONE,6,6,"서브메뉴2");
            submenu.add(Menu.NONE,7,7,"서브메뉴3");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    //메뉴 클릭시 이벤트 처리용
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case 1:
            case R.id.menuitem_red:
                linearLayout.setBackgroundColor(Color.RED);break;
            case 2:
            case R.id.menuitem_green:
                linearLayout.setBackgroundColor(Color.GREEN);break;
            case 3:
            case R.id.menuitem_blue:
                linearLayout.setBackgroundColor(Color.BLUE);break;
            case 4:
            case R.id.layout_menu:
                Toast.makeText(this,"레이아웃 컨텍스트 메뉴입니다",Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}