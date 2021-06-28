package com.kosmo.jsonparse34_3;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ProductWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        WebView webView=findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //웹 페이지로딩 담당 - 아래 생략시 : 
        //<activity android:name=".ProductWebActivity" android:noHistory="true"/> noHistory 옵션 추가
        //back 버튼 클릭시 빈 공백화면 제거
        webView.setWebViewClient(new WebViewClient());
        Intent intent = getIntent();
        String url = intent.getStringExtra("link");
        webView.loadUrl(url);
    }///onCreate
}////ProductWebActivity