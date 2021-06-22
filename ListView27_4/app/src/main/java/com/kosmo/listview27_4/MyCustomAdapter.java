package com.kosmo.listview27_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//1]BaseAdapter상속-4개의 메소드 오버라이딩
public class MyCustomAdapter extends BaseAdapter {
    //생성자를 통해서 초기화 할 멤버 변수들]
    //리스트뷰가 뿌려지는 컨텍스트
    private Context context;
    //리스트뷰에 뿌릴 데이타
    private List<Item> items;
    //레이아웃 리소스 아이디(선택사항)
    private int itemLayoutResId;
    //2]생성자 정의:생성자로 Context와 리스트뷰에 뿌려줄 데이타를 받는다.
    //             리소스 레이아웃 아이디(int)는 선택사항
    //인자생성자1]컨텍스트와 데이타
    public MyCustomAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }
    //인자생성자2]컨텍스트와 데이타와 아이템레이아웃 리소스아이디
    public MyCustomAdapter(Context context, List<Item> items, int itemLayoutResId) {
        this.context = context;
        this.items = items;
        this.itemLayoutResId = itemLayoutResId;
    }
    //※리스트뷰에 의해서 호출됨.
    //아이템의 총 갯수 반환,
    //리스트뷰는 아이템 총 갯수만큼 아래 의 getView()메소드를 호출한다.
    @Override
    public int getCount() {
        return items.size();
    }
    //position에 해당하는 아이템 반환
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
    //아이템의 아이디 반환
    @Override
    public long getItemId(int position) {
        return position;
    }
    /*리스트뷰가 각 항목을 출력할때 어댑터의 getView()메소드 호출
   즉 출력할 아이템의 뷰를 생성해서 리스트 뷰에 반환
   convertview는 리스트 뷰의 각 항목을 나타내는 뷰이고
   parent는 부모 뷰 즉 리스트뷰가 됨.
  리스트뷰는 getView()호출시 최초 요청일때는 position이 0이고 convertView는 null이다
  두 번째 아이템 항목부터는 convertView레이아웃이 그대로 리스트뷰로부터 전달됨으로
  항목을 구성하는 위젯만 변경해서(리스트뷰로부터 전달받은 position값에 따라)  convertView를
  리스트뷰에 반환한다.
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //인자생성자1 사용시]
            //convertView=LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            //인자생성자2 사용시]
            //ViewGroup은 반드시 null로 설정
            convertView=LayoutInflater.from(context).inflate(itemLayoutResId,null);
        }

        //리스트 뷰에서 하나의 아이템을 구성하는 각 위젯의 데이타 바인딩]
        ImageView itemImage=convertView.findViewById(R.id.itemImage);
        TextView itemName =convertView.findViewById(R.id.itemName);
        TextView itemDept =convertView.findViewById(R.id.itemDept);
        TextView itemDate =convertView.findViewById(R.id.itemDate);

        itemImage.setImageResource(items.get(position).getItemImageResId());
        itemName.setText(items.get(position).getItemName());
        itemDept.setText(items.get(position).getItemDept());
        itemDate.setText(items.get(position).getItemDate());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,items.get(position).getItemName(),Toast.LENGTH_SHORT).show();
            }
        });
        if(position % 2==0)
            convertView.setBackgroundColor(0x22b8860b);
        else
            convertView.setBackgroundColor(0x22FA8072);
        //아이템의 배경색 변경]짝수번째는 빨강으로
        return convertView;
    }
}
