package com.kosmo.fragment25;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //프래그먼트 관리자]
    //androidx.fragment.app패키지에 있는 FragmentManager import
    private FragmentManager fragmentManager;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기-XML fragment태그로 Top에 배치한 프래그먼트(TopFragment)의 뷰(버튼들) 얻기
        Button btnFirstFragment=findViewById(R.id.btnFirstFragment);
        Button btnSecondFragment=findViewById(R.id.btnSecondFragment);
        Button btnThirdFragment=findViewById(R.id.btnThirdFragment);
        //버튼에 리스너 부착]
        btnFirstFragment.setOnClickListener(handler);
        btnSecondFragment.setOnClickListener(handler);
        btnThirdFragment.setOnClickListener(handler);
        //프래그먼트 객체 생성]
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        //액티비티에서 프래그먼트(FirstFragment)에 데이타 전달
        //프래그먼트객체.setArguments(Bundle)로 데이타 전달
        Bundle bundle = new Bundle();
        bundle.putString("KOSMO","한국 소프트웨어 인재 개발원");
        firstFragment.setArguments(bundle);

        //getSupportFragmentManager()메소드로 FragmentManager얻기
        fragmentManager=getSupportFragmentManager();
        //FragmentManager객체의 beginTransaction()메소드로  FragmentTransaction 얻기
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        //FragmentTransaction의 replace()메소드로 해당 레이아웃 영역을 프래그먼트로 교체
        //replace(영역의 리소스 아이디,프래그먼트 객체)
        transaction.replace(R.id.bottomLayout,firstFragment);
        //commit()메소드로 실제 반영
        transaction.commit();


    }
    //이벤트 핸들러
    private View.OnClickListener handler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnFirstFragment)
                fragmentManager.beginTransaction().replace(R.id.bottomLayout,firstFragment).commit();
            else if(v.getId()==R.id.btnSecondFragment)
                fragmentManager.beginTransaction().replace(R.id.bottomLayout,secondFragment).commit();
            else
                fragmentManager.beginTransaction().replace(R.id.bottomLayout,thirdFragment).commit();
        }
    };
    //TOP부분:XML코드로 프래그먼트 붙이기 시작
    //Fragment를 상속한 클래스 작성(내부 정적 클래스 혹은 별도 외부 클래스로)
    //Fragment상속(androidx.fragment.app.Fragment상속)
    public static class TopFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
            //※setContentView()가 아닌 매개변수에 전달된 LayoutInflater로 레이아웃 전개]
            return inflater.inflate(R.layout.top_fragment_layout,container);
        }
    }////////////TopFragment
    //첫번째 화면용 프래그먼트
    public static class FirstFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.first_fragment_layout,container,false);
            view.findViewById(R.id.btnGetData).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //[데이타를 받아서 토스트로 뿌려주기]
                    //getArguments()로 Bundle객체 얻기
                    Bundle bundle=getArguments();
                    //Bundle객체의 getXXXX(키값)데이타 받기
                    String value=bundle.getString("KOSMO");
                    //프래그먼트가 받은 데이타 출력
                    Toast.makeText(v.getContext(),"액티비티에서 받은 데이타:"+value,Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }/////////////////FirstFragment
    //두번째 화면용 프래그먼트
    public static class SecondFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable ViewGroup container,  Bundle savedInstanceState) {
            return inflater.inflate(R.layout.second_fragment_layout,container,false);
        }
    }//////////////SecondFragment
    //세번째 화면용 프래그먼트
    public static class ThirdFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
            return inflater.inflate(R.layout.third_fragment_layout,container,false);
        }
    }//////////////SecondFragment


}