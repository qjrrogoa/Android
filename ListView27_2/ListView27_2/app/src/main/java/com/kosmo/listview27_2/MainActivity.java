package com.kosmo.listview27_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

//1]ListActivity상속
public class MainActivity extends ListActivity/*AppCompatActivity*/ {
    //2]데이타용
    private List items = new Vector();
    //리스트뷰 저장용
    private ListView listView;

    //NONE모드(simple_list_item_1) 일때 클릭한 아이템 저장용
    private String selectedItemNone;

    //SINGLE 모드 일때(simple_list_item_checked나 혹은 simple_list_item_single_choice) 클릭한 아이템 저장용
    private String selectedItemSingle;
    //Multiple모드(simple_list_item_multiple_choice)일때 선택한 아이템 저장용]
    private List<String> selectedItems = new Vector<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ListActivity상속시 레이아웃 전개 불필요-다른 위젯 배치하지 않고 목록만 표시하고자 할때]
        setContentView(R.layout.activity_main);
        items.add("한라산");items.add("지리산");items.add("치악산");items.add("속리산");
        //3]어댑터 생성
        //simple_list_item_1 일때-모드(싱글 혹은 멀티모드 설정) 설정 불필요
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        //simple_list_item_checked일때 모드 설정 필요(single이나 multi둘 다 설정 가능)
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked,items);
        //simple_list_item_single_choiced일때-모드 설정 필요
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice,items);
        //simple_list_item_multiple_choice일때-모드 설정 필요
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,items);

        //4]연결-ListActivity상속시는 setAdapter()가 아니나 setListAdapter()로 연결
        setListAdapter(adapter);
        //5]리스트뷰 얻기-ListActivity상속시는 getListView();
        listView=getListView();
        //5-1]리스트뷰 꾸미기
        //※분리선의 기본 색상 이외에 다른 색상 설정시에는
        // 반드시 색상 설정한후 두께 설정한다. (순서에 주의)
        //분리선 색상 설정]
        listView.setDivider(new ColorDrawable(Color.RED));
        //분리선 두께 설정]
        listView.setDividerHeight(5);
        //헤더와 푸터 설정]-헤더와 푸터도 아이템으로 포함됨
        //listView.addHeaderView(View v);
        //listView.addFooterView(View v);
        //자바코드로 헤더와 푸터로 사용할 뷰 만들기
        /*
        TextView header = new TextView(this);
        header.setText("Mountain List");
        header.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200);
        header.setLayoutParams(layoutParams);
        header.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        header.setTextColor(Color.WHITE);
        header.setBackgroundColor(Color.BLACK);
        listView.addHeaderView(header);

        TextView footer = new TextView(this);
        footer.setText("한국 산악 협회");
        footer.setGravity(Gravity.CENTER);

        footer.setLayoutParams(layoutParams);
        footer.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        footer.setTextColor(Color.WHITE);
        footer.setBackgroundColor(Color.BLACK);
        listView.addFooterView(footer);
         */
        //XML로 헤더와 푸터를 설정-
        View header=View.inflate(this,R.layout.header_layout,null);
        listView.addHeaderView(header);
        listView.setHeaderDividersEnabled(false);
        View footer=View.inflate(this,R.layout.footer_layout,null);
        listView.addFooterView(footer);
        listView.setFooterDividersEnabled(false);
        //6]※리스트 뷰 모드 설정-
        // 레이아웃이 simple_list_item_checked나 simple_list_item_single_choice나
        //           simple_list_item_multiple_choice일때는 반드시 모드 설정
        //          simple_list_item_checked일때는 CHOICE_MODE_SINGLE나 CHOICE_MODE_MULTIPLE모드 두개 다 가능
        //즉 CHOICE_MODE_SINGLE는 하나만 체크되고 CHOICE_MODE_MULTIPLE모드일때는 여러개 체크 가능
        //[simple_list_item_checked]
        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }//////////

    public void click(View view){
        int choiceMode=listView.getChoiceMode();
        if(choiceMode == ListView.CHOICE_MODE_NONE)
            Toast.makeText(this,selectedItemNone,Toast.LENGTH_SHORT).show();
        else if(choiceMode == ListView.CHOICE_MODE_SINGLE)
            Toast.makeText(this,selectedItemSingle,Toast.LENGTH_SHORT).show();
        else{
            //SparseBooleanArray 미 사용
            //Toast.makeText(this,selectedItems.toString(),Toast.LENGTH_SHORT).show();
            //SparseBooleanArray 사용
            SparseBooleanArray sparseBooleanArray=listView.getCheckedItemPositions();
            //{키=값} 키는 position 값은 true 혹은  false 예] { 1=true,2=false,5=true}
            //즉 사용자가 체크하거나 해제한 아이템에 대해서만
            Toast.makeText(this,"sparseBooleanArray:"+sparseBooleanArray,Toast.LENGTH_SHORT).show();
            StringBuffer buf = new StringBuffer();
            for(int i=0; i < sparseBooleanArray.size();i++){
                int position=sparseBooleanArray.keyAt(i);//keyAt(인덱스) :키값 반환
                if(sparseBooleanArray.valueAt(i)){//valueAt(인덱스):value반환
                    buf.append(listView.getItemAtPosition(position).toString()+" ");
                    
                }
            }
            Toast.makeText(this,buf,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //position:클릭한 아이템의 인덱스 값(헤더는 0 푸터는 배열의 length혹은 length+1(헤더포함시))
        //id:실제 데이타 아이템의 인덱스값(0부터 시작:헤더나 푸터는 -1)
        if(id== -1) return;//헤더나 푸터클릭한 경우
        Toast.makeText(this,String.format("position:%s,id:%s,%s 선택",position,id,items.get((int)id)),Toast.LENGTH_SHORT).show();
        Toast.makeText(this,String.format("두번째 인자 View사용 %s 선택",((TextView)v).getText()),Toast.LENGTH_SHORT).show();
        Toast.makeText(this,String.format("아이템의 총수:%s,헤더 수:%s,푸터 수:%s",listView.getCount(),listView.getHeaderViewsCount(),listView.getFooterViewsCount()),Toast.LENGTH_SHORT).show();

         /*
         ※특정 인덱스의 아이템 가져올때는
          getItemAtPosition(int position)메소드 사용
         배열명[인덱스] 비 추천
         왜냐하면
         리스트뷰에 헤더나 푸터 추가시 헤더도 아이템에 포함됨.
         모드가 single인 경우:getCheckedItemPosition()으로 체크된 아이템
               multiple인 경우:getCheckedItemPositions()으로 체크된 아이템을 얻는다
               그리고 getItemAtPosition(int position)메소드로 아이템의 텍스트를 얻는다
         ※isItemChecked(int position) : 반드시 인자로 받은
                                         position 을 넣어야한다
                                        isItemChecked()메소드는 헤더나
                                              푸터를 아이템으로 인식한다

         */
        int choiceMode=listView.getChoiceMode();
        if(choiceMode == ListView.CHOICE_MODE_NONE)
            selectedItemNone=listView.getItemAtPosition(position).toString();
        else if(choiceMode == ListView.CHOICE_MODE_SINGLE)
            selectedItemSingle =listView.getItemAtPosition(position).toString();
        else if(choiceMode == ListView.CHOICE_MODE_MULTIPLE)
            if(listView.isItemChecked(position))//체크 한 경우
                selectedItems.add(listView.getItemAtPosition(position).toString());
            else//체크 해제한 경우
                selectedItems.remove(listView.getItemAtPosition(position).toString());



    }
}