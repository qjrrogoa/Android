package com.kosmo.tablayoutviewpager17.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kosmo.tablayoutviewpager17.R;

//1]Fragement상속
//※androidx.fragment.app.Fragment 상속
public class TabContent3 extends Fragment {
    //2]onCreateView()오버 라이딩
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tabmenu3_layout,null,false);
        TextView textView=view.findViewById(R.id.textView);
        return view;
    }
}
