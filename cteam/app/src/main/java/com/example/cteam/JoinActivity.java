package com.example.cteam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cteam.ATask.JoinInsert;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {

    String state;

    EditText signupId, signupPw, signupName, signupConfirm, signupQuestion, signupAnswer, signupPhoneNum;
    Button btnJoin, btnCancel;
    TextView SignUp_agree_text,txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupId = findViewById(R.id.SignUp_id);
        signupPw = findViewById(R.id.SignUp_pw);
        signupName = findViewById(R.id.SignUp_name);
        signupQuestion = findViewById(R.id.SignUp_qs);
        signupAnswer = findViewById(R.id.SignUp_qs_as);
        signupPhoneNum = findViewById(R.id.SignUp_phonenum);
        btnJoin = findViewById(R.id.SignUp_cancel);
        btnCancel = findViewById(R.id.SignUp_join);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String member_id = signupId.getText().toString();
                String member_pw = signupPw.getText().toString();
                String member_name = signupName.getText().toString();
                String member_qeustion =  signupQuestion.getText().toString();
                String member_answer = signupAnswer.getText().toString();
                String member_phonenum = signupPhoneNum.getText().toString();


                if(!Pattern.matches("^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$", member_phonenum))
                {
                    Toast.makeText(JoinActivity.this,"올바른 핸드폰 번호가 아닙니다.",Toast.LENGTH_SHORT).show();
                    signupPhoneNum.setText("");
                    signupPhoneNum.requestFocus();
                    return;
                }

                //비밀번호 유효성
                if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", member_pw))
                {
                    Toast.makeText(JoinActivity.this,"비밀번호 형식을 지켜주세요.",Toast.LENGTH_SHORT).show();
                    signupPw.setText("");
                    signupPw.requestFocus();
                    return;
                }

                JoinInsert joinInsert = new JoinInsert(member_id,member_pw, member_name, member_qeustion, member_answer, member_phonenum);
                try {
                    state = joinInsert.execute().get().trim();
                    Log.d("main:joinact0 : ", state);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(state.equals("1")){
                    Toast.makeText(JoinActivity.this, "회원가입 되었습니다", Toast.LENGTH_SHORT).show();
                    Log.d("main:joinact", "삽입성공 !!!");
                    finish();
                }else{
                    Toast.makeText(JoinActivity.this, "정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                    Log.d("main:joinact", "삽입실패 !!!");
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //약관보기
        SignUp_agree_text = (TextView) findViewById(R.id.SignUp_agree_text);

        SignUp_agree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 1);

            }
        });

    }

}
