package com.kosmo.webview18;

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

import androidx.appcompat.app.AppCompatActivity;

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
        //위젯 얻기
        initView();
        //WebView설정]
        //1]WebView의 getSettings()메소드로 WebSettings객체 생성
        WebSettings settings = webview.getSettings();
        //자스가 실행되도록 설정- 기본적으로 웹뷰는 자스를 지원하지 않음
        settings.setJavaScriptEnabled(true);//필수설정
        //확대모드 설정]-옵션
        settings.setBuiltInZoomControls(true);
        //스마트폰 웹뷰안에 사이트가 들어오도록 설정 즉 상단에
        //에디트 텍스트나 버튼들이 그대로 보이도록 설정
        //아래부분 생략시 웹뷰가 전체 레이아웃을 차지함(사이트 로드시)]-옵션
        webview.setWebViewClient(new CustomWebViewClient());

        //자스의 기본 경고창(alert) 그대로 사용시
        //webview.setWebChromeClient(new WebChromeClient());//setWebViewClient(new CustomWebViewClient())와 쌍이다.
        //※WebChromeClient에서 제공하는 기본 경고창 사용하지않고
        //즉 자스의 alert()모양을 Toast로 변경
        webview.setWebChromeClient(new CustomWebChromeClient());

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edittext.getText().toString();
                Log.i("com.kosmo.webview",url);
                //WebView의 loadUrl()메소드 호출
                //1]사용자가 입력한 사이트 url을 로딩하기
                //webview.loadUrl(url);
                //2]앱안에 있는 html파일 로딩하기
                //Android탭 -> app선택후 New -> Folder -> Assets Folder생성
                //assets가 아닌 android_asset으로 URL설정
                //webview.loadUrl("file:android_asset/html/GoogleChart.html");
                //3]html소스로 데이터
                String htmlData = "<html>\n" +
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
                //이미지 표시되지 않음 즉 img태그가 실행안됨
                //webview.loadData(htmlData,"text/html;charset=UTF-8","UTF-8");
                //<img src="../images/sumnail.png" alt="이미지"/>를 보이게 하려면
                //loadDataWithBaseURL()를 사용해야한다
                webview.loadDataWithBaseURL("file:android_asset/images/",htmlData,"text/html;charset=UTF-8","UTF-8",null);
            }
        });////////////////////

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.goBack();
            }
        });
        //RESTfull API 서버로부터 데이터를 받는다고 가정
        btnData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pieData = "['20대',5],['30대',5],['40대',10],['50대',20]";
                webview.loadDataWithBaseURL("file:android_asset/images/",getHtmlString("연령대","연령비","연령대별 분포",pieData),"text/html;charset=UTF-8","UTF-8",null);
            }
        });

        btnData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pieData = "['보드타기',5],['수영',5],['서핑',10],['헬스',20]";
                webview.loadDataWithBaseURL("file:android_asset/images/",getHtmlString("연령대","연령비","연령대별 분포",pieData),"text/html;charset=UTF-8","UTF-8",null);
            }
        });
    }/////////////////////////onCreate

    private String getHtmlString(String subTitle1,String subTitle2,String title,String pieData){
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
    }/////////////////initView
    //WebViewClient상속]-웹 페이지 로딩 담당
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
    //alert()를 스마트폰 앱에 맞게 Toast로 변경하기 위한 클래스
    private  class CustomWebChromeClient extends WebChromeClient{
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            //경고 메세지를 Toast로 보여주기
            Toast.makeText(view.getContext(),message, Toast.LENGTH_SHORT).show();
            //자바스크립트 경고창의 확인버튼을 클릭한것으로 처리하도록 호출해야한다
            //alert()는 모달이라 클릭한것으로 처리안하면 다른메뉴를 클릭할 수 없다
            result.confirm();
            return true;//반드시 true로 반환해야한다.
        }
    }
}