# Android

# 핸들러 부착 방법

1. 익명 클래스?
2. 안된다!!!

        var.setOnClickListener(new View onClickListener(){
          @Override
          public void onClick(View v){
          
          
          }
        });

2. 이벤트 핸들러 객체 생성

        //이벤트 핸들러 객체 생성
        EventHandler handler = new EventHandler();
        
        //버튼에 리스너 부착
        va1.setOnClickListener(handelr)
        va2.setOnClickListener(handelr)
        va3.setOnClickListener(handelr)
        
        //내부 멤버 클래스로 이벤트 핸들러 만들기
        
        private class EventHandler implements View.OnClickListener{
            @Override
            public void onClick(View v){

            }
        }

파일 업로드 오류!!
