package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class AgreePopup extends AppCompatActivity {

    TextView AgreePopup_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_agree_popup);

        //UI 객체생성
        AgreePopup_txt = (TextView)findViewById(R.id.AgreePopup_txt);

        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        AgreePopup_txt.setText(data);
    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        finish();
    }

    //바깥 레이어 클릭시 안 닫히게
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    //백버튼 막기
    @Override
    public void onBackPressed() {
        return;
    }



}