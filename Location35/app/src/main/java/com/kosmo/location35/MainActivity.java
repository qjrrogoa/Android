package com.kosmo.location35;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

//실행시 권한 요청:
//https://developer.android.com/training/permissions/requesting.html
public class MainActivity extends AppCompatActivity {

    //현재 앱에서 필요한 권한들
    private String[] permissions={
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    //권한요청시 각 권한을 구분하기 위한 요청코드값
    public  static final int MY_REQUEST_PERMISSION =1;

    //사용자 위치 정보 관련 API 들]
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location;

    //PROVIDER활성화 여부관련 변수]
    private boolean gpsProvider,networkProvider;
    //최적의 프로바이더]
    private String bestProvider;

    //허용이 안된 권한들을 저장할 컬렉션
    private List<String> deniedPermissions = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위치 관리자 얻기]
        //위치 관리자는 시스템 서비스 이므로 객체를 참조하기 위해 getSystemService()메소드 사용
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        gpsProvider =locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        networkProvider  = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Toast.makeText(this,
                String.format("GPS:%s,NETWORK:%s",gpsProvider,networkProvider),Toast.LENGTH_SHORT).show();

        //위치 변화가 있을때마다  위치를 감지하는 리스너 객체 생성]
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //현재 위치의 위도/경도 얻기
                double lat=location.getLatitude();
                double lng = location.getLongitude();
                Log.i("com.kosmo.location",String.format("위도:%s,경도:%s",lat,lng));
            }

        };

        //현재 앱이 필요로 하는 모든 권한을 사용자로부터 얻기
        //사용자 권한은 String형 상수로 정의 되어 있다
        //자바코드:Manifest.permission.권한
        //xml : android.permission.권한
        //마쉬멜로우 이후 버전부터 사용자에게 권한 요청보낸다
        if(Build.VERSION.SDK_INT >=23){
            requestsUserPermissions();
        }
    }////////////onCreate

    private void requestsUserPermissions() {


    }

    public void startLocation(View viw){

    }///////////
    public void stopLocation(View viw){

    }///////////
    public void getLastLocation(View viw){

    }///////////
}