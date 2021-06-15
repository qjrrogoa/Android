package com.kosmo.tablayoutviewpager17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.kosmo.tablayoutviewpager17.view.TabContent1;
import com.kosmo.tablayoutviewpager17.view.TabContent2;
import com.kosmo.tablayoutviewpager17.view.TabContent3;

import java.util.List;
import java.util.Vector;

public class MainActivity extends FragmentActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private List<Fragment> fragments = new Vector<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        //탭 메뉴 추가
        tabLayout.addTab(tabLayout.newTab().setText("메뉴1"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tabmenu2));
        tabLayout.addTab(tabLayout.newTab().setText("메뉴3").setIcon(R.drawable.tabmenu3));
        //Fragment 생성후 컬렉션에 저장
        TabContent1 tabContent1 = new TabContent1();
        TabContent2 tabContent2 = new TabContent2();
        TabContent3 tabContent3 = new TabContent3();
        fragments.add(tabContent1);fragments.add(tabContent2);fragments.add(tabContent3);
        //뷰 페이저를 관리하는 PageAdapter를 생성
        MyPageAdapter myPageAdapter = new MyPageAdapter(this,fragments);
        //ViewPager에 PageAdapter를 연결
        viewPager2.setAdapter(myPageAdapter);
        //탭 레이아웃에 리스너 설정
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //탭이 선택되었을때 자동 호출
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
            //탭이 선택도지 않았을때
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            //탭이 다시 선택되었을때
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}