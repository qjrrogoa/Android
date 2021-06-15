package com.kosmo.webview18;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
/*
   findViewById()메소드 자동화 플러그인 설치:
   File - Settings-Plugin-findViewByMe로 검색후 install버튼 클릭

   setContentView의 activity_main텍스트 위에 마우스 우 클릭후-Generate-FindViewByMe클릭하면
   코드가 자동완성됨
    */
public class MainActivity extends AppCompatActivity {

    private EditText edittext;
    private Button buttonGo;
    private Button buttonBack;
    private Button btnData1;
    private Button btnData2;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 얻기]
        initView();
        //WebView설정]
        //1]WebView의 getSettings()메소드로 WebSettings객체
        WebSettings settings=webview.getSettings();
        //자스가 실행되도록 설정- 기본적으로 웹뷰는 자스를 지원하지 않음]
        settings.setJavaScriptEnabled(true);//필수 설정
        //확대모드 설정]-옵션
        settings.setBuiltInZoomControls(true);
        //스마트폰 웹뷰안에 사이트가 들어오도록 설정 즉 상단에
        // 에디트 텍스트나 버튼들 그대도 보이도록 설정.
        //아래부분 생략시 웹뷰가 전체 레이아웃을 차지함(사이트 로드시)]-옵션
        webview.setWebViewClient(new WebViewClient(){
            //오버라이딩만하면 된다]
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

        });
        //자스의 기본 경고창(alert) 그대로 사용시]
        //webview.setWebChromeClient(new WebChromeClient());//setWebViewClient(new CustomWebViewClient())와 쌍
        //※WebChromeClient에서 제공하는 기본 경고창 사용하지 않고
        //  즉 자스의 alert()모양을 Toast 로 변경
        webview.setWebChromeClient(new CustomWebChromeClient());


        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=edittext.getText().toString();
                Log.i("com.kosmo.webview",url);
                //WebView의 loadUrl()메소드 호출
                //1]사용자가 입력한 사이트 url을 로딩하기
                webview.loadUrl(url);
                //2]앱안에 있는 html파일 로딩하기
                //Android탭->app선택후 New->Folder->Assets Foldr생성
                //assets가 아닌 android_asset으로 URL 설정
                //webview.loadUrl("file:android_asset/html/GoogleChart.html");
                //3]html소스를 데이타로 사용해서 로드하기
                String htmlData="<html>\n" +
                        "  <head>\n" +
                        "  \t<meta charset=\"utf-8\">\n" +
                        "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                        "    <script type=\"text/javascript\">\n" +
                        "      google.charts.load('current', {'packages':['corechart']});\n" +
                        "      google.charts.setOnLoadCallback(drawChart);\n" +
                        "\n" +
                        "      function drawChart() {\n" +
                        "\n" +
                        "        var data = google.visualization.arrayToDataTable([\n" +
                        "          ['Task', 'Hours per Day'],\n" +
                        "          ['일',     11],\n" +
                        "          ['식사',      2],\n" +
                        "          ['사회생활',  2],\n" +
                        "          ['TV보기', 2],\n" +
                        "          ['잠',    7]\n" +
                        "        ]);\n" +
                        "\n" +
                        "        var options = {\n" +
                        "          title: '나의 하루 일과'\n" +
                        "        };\n" +
                        "\n" +
                        "        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\n" +
                        "\n" +
                        "        chart.draw(data, options);\n" +
                        "      }\n" +
                        "    </script>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "    <div id=\"piechart\" style=\"width: 900px; height: 500px;\"></div>\n" +
                        "    <img src=\"../images/sumnail.png\" alt=\"이미지\"/>\n" +
                        "  </body>\n" +
                        "</html>\n";
                //이미지 표시되 않음 즉 img태그가 실행안됨
                //webview.loadData(htmlData,"text/html;charset=UTF-8","UTF-8");
                //<img src="../sumnail.png" alt="이미지"/>를 보이게 하려면
                //loadDataWithBaseURL()를 사용해야 한다
                //webview.loadDataWithBaseURL("file:android_asset/images/",htmlData,"text/html;charset=UTF-8","UTF-8",null);

            }
        });//////////////////

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.goBack();
            }
        });

        //RESTfull API 서버로부터 데이타를 받는다고 가정
        btnData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pieData="['20대',5],['30대',5],['40대',10],['50대',20]";
                webview.loadDataWithBaseURL("file:android_asset/images/",getHtmlString("연령대","연령비","연령대별 분포",pieData),"text/html;charset=UTF-8","UTF-8",null);
            }
        });

        btnData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pieData="['등산',5],['보드타기',5],['드라이브',10],['수영',20],['서핑',24],['헬스',15]";
                webview.loadDataWithBaseURL("file:android_asset/images/",getHtmlString("취미","인원수","취미별 분포",pieData),"text/html;charset=UTF-8","UTF-8",null);
            }
        });

    }/////////onCreate

    private String getHtmlString(
            String subTitle1,
            String subTitle2,String title,String pieData){
            return "<html>\n" +
                    "  <head>\n" +
                    "  \t<meta charset=\"utf-8\">\n" +
                    "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                    "    <script type=\"text/javascript\">\n" +
                    "      google.charts.load('current', {'packages':['corechart']});\n" +
                    "      google.charts.setOnLoadCallback(drawChart);\n" +
                    "\n" +
                    "      function drawChart() {\n" +
                    "\n" +
                    "        var data = google.visualization.arrayToDataTable([\n" +
                    "          ['"+subTitle1+"', '"+subTitle2+"'],\n" +
                    pieData+
                    "        ]);\n" +
                    "\n" +
                    "        var options = {\n" +
                    "          title: '"+title+"'\n" +
                    "        };\n" +
                    "\n" +
                    "        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\n" +
                    "\n" +
                    "        chart.draw(data, options);\n" +
                    "      }\n" +
                    "    </script>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div id=\"piechart\" style=\"width: 900px; height: 500px;\"></div>\n" +
                    "    <img src=\"../images/sumnail.png\" alt=\"이미지\"/>\n" +
                    "  </body>\n" +
                    "</html>\n";
    }

    private void initView() {
        edittext = findViewById(R.id.edittext);
        buttonGo = findViewById(R.id.button_go);
        buttonBack = findViewById(R.id.button_back);
        btnData1 = findViewById(R.id.btnData1);
        btnData2 = findViewById(R.id.btnData2);
        webview = findViewById(R.id.webview);
    }////////////////////

    //alert()를 스마트폰 앱에 맞게 Toast로 변경하기 위한 클래스
    private class CustomWebChromeClient extends  WebChromeClient{
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            //경고 메시지를 Toast로 보여주기
            Toast.makeText(view.getContext(),message,Toast.LENGTH_SHORT).show();
            //자바스크립트 경고창의 확인버튼을 클릭한것으로 처리하도록 호출
            //해야한다 alert()는 모달이라 클릭한 것으로 처리안하면
            //다른 메뉴를 클릭 할 수 없다
            result.confirm();
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            //AlertDialog로 띄우자
            //Toast.makeText(view.getContext(),message,Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(false)
                    .setTitle("확인창")
                    .setMessage(message)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.cancel();
                }
            }).create().show();
            return true;
        }
    }///////////
}