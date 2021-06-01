package com.example.cteam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.cteam.R;

public class IdFindPopup extends Activity {

    TextView txtText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_id_find_popup);

        //UI 객체생성
        txtText2 = (TextView)findViewById(R.id.txtText2);

        //putextra에 넣은 아이디를 가져와 뿌려준다
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        txtText2.setText(data);


    }

    //확인 버튼 클릭
    public void mOnClose(View v){

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }


}