package com.kosmo.compoundbutton12;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    public static final String TAG="com.kosmo.compound";

    //스피너의 데이터로 사용]
    private String[] items = {"자바","오라클","HTML5","CSS3","자바스크립트(ES5)","JSP/SERVLET","SPRING(MyBatis)","jQuery/Ajax","부트스트랩","Git/Github"};
    //결과출력용 멤버변수들]
    private String radioString = "남성";
    private String spinnerString ="JSP/SERVLET";
    //체크박스/토글버튼/스위치 체크여부 판단]
    private List checkboxChecked = new Vector();
    private String toggleString="OFF";
    private String switchString = "ON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 얻기]
        TextView textView = findViewById(R.id.textview);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        textView.setTypeface(Typeface.SANS_SERIF); //글꼴 설정

        //체크 박스류
        CheckBox cbPol = findViewById(R.id.check_politics);
        CheckBox cbEnt = findViewById(R.id.check_entertainments);
        CheckBox cbEco = findViewById(R.id.check_economics);
        CheckBox cbSpo = findViewById(R.id.check_sports);

        //자바코드로 체크된 상태로 만들기
        cbEnt.setChecked(false);
        cbEco.setChecked(true);
        checkboxChecked.add("경제");

        //라디오 그룹]
        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        //라디오 버튼 체크 해제:clearCheck()
        radioGroup.clearCheck();
        //라디오 버튼 체크:check(리소스 아이디)
        radioGroup.check(R.id.radio_male);

        //토글버튼 및 스위치]
        ToggleButton toggleButton = findViewById(R.id.togglebutton);
        Switch switchButton = findViewById(R.id.switch_button);
        //자바코드로 온/오프상태 만들기]
        toggleButton.setChecked(false);
        switchButton.setChecked(true);

        //스피너
        Spinner spinner = findViewById(R.id.spinner);
        //어댑터 생성
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,items);
        //위젯에 어댑터 적용
        spinner.setAdapter(adapter);
        //처음에 보여줄 아이템 선택:
        //xml속성에는 없음 , AbsSpinner클래스가 갖고 있는 메소드임.
        spinner.setSelection(5);

        //버튼
        Button button = findViewById(R.id.button);
        //리스너 부착]
        //체크박스/토글버튼/스위치-OnCheckedChangeListener 부착
        cbEco.setOnCheckedChangeListener(this);
        cbEnt.setOnCheckedChangeListener(this);
        cbPol.setOnCheckedChangeListener(this);
        cbSpo.setOnCheckedChangeListener(this);

        toggleButton.setOnCheckedChangeListener(this);
        switchButton.setOnCheckedChangeListener(this);

        //라디오 버튼- 라디오그룹에 리스너 부착해라
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //두번째 인자는 체크된 라디오버튼의 리소스 ID값(int) 즉
                //R.id.id명
                //RadioGroup의 getCheckdRadioButtonId()메서드로
                //체크된 라디오버튼의 아이디를 알아 낼 수 있다.

                //방법1]
                /*
                switch (checkedId){
                    case R.id.radio_female:
                        radioString="여성";
                        break;
                    case R.id.radio_male:
                         radioString="남성";
                         break;
                    default:
                        radioString="트랜스젠더";
                }
                */
                //방법2]
                radioString = ((RadioButton)findViewById(checkedId)).getText().toString();
            }
        });

        //스피너에는 setOnItemClickedListener가 아니라
        // setOnItemSelectedListener를 붙여라
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //parent : 여기서는 Spinner를 의미
                //view : 하나의 아이템을 표시하는 텍스트 뷰(CheckedTextView)
                //positon: The  position of the view in the adapter
                //id:The row id of the item that is selected
                Log.i(TAG,"postion :" + position+", row id : " +id);
                Log.i(TAG,items[position]+"선택");
                Log.i(TAG,((CheckedTextView)view).getText()+"선택") ;
                spinnerString=((CheckedTextView)view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //버튼에 리스너 부착
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(
                        String.format(("체크박스:%s%n 라디오버튼:%s%n 토클버튼:%s%n 스위치:%s%n 스피너:%s%n"),
                        Arrays.toString(checkboxChecked.toArray()),
                        radioString,
                        toggleString,
                        switchString,
                        spinnerString
                        ));
            }
        });
       }////////////onCreate

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.i(TAG,"isChecked:"+isChecked);
        if(buttonView instanceof CheckBox){//체크 박스
            if(isChecked){//체크된 박스
                checkboxChecked.add(buttonView.getText());
                Log.i(TAG,buttonView.getText()+"선택");
            }
            else{
                checkboxChecked.remove(buttonView.getText());
                Log.i(TAG,buttonView.getText()+"해제");
            }
        }

        else if(buttonView.getId()==R.id.togglebutton){
            if(isChecked){
              toggleString=((ToggleButton)buttonView).getTextOn().toString();
            }
            else{
                toggleString=((ToggleButton)buttonView).getTextOff().toString();
            }
        }
        else{
            if(isChecked){
                switchString="ON";
            }
            else{
                switchString="OFF";
            }
        }
    }
}