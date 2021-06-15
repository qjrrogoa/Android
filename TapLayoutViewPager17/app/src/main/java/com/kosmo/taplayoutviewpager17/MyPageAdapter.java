package com.kosmo.taplayoutviewpager17;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
import java.util.Vector;

//ViewPager와 연결해 Fragment를 관리하기 위한 어댑
public class MyPageAdapter extends FragmentStateAdapter {

    private List<Fragment> fragments = new Vector<>();

    public MyPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //탭 메뉴의 position에 해당하는 프레그먼트를 반환
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    //page의 개수를 반환. fragments 크기가 page의 개수
    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
