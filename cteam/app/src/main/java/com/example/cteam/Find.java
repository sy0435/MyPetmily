package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cteam.ATask.FindPwSelect;
import com.example.cteam.ATask.FindSelect;
import com.example.cteam.ATask.LoginSelect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Find extends AppCompatActivity {
    private static final String TAG = "mainFind";

    String id_return = "", pw_return = "";
    Button  Find_btn_idFind, Find_btn_pwFind,Find_btn_login, Find_btn_signup;
    TextView Find_name, Find_phonenum, Find_id, Find_as;
    Spinner Find_qs_spinner;
    SpinnerAdapter spinnerAdapter;
    String Find_qs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        Find_name=findViewById(R.id.Find_name);
        Find_phonenum=findViewById(R.id.Find_phonenum);
        Find_id=findViewById(R.id.Find_id);
        Find_as=findViewById(R.id.Find_qs_as);


        //데이터
        List<String> data = new ArrayList<>();
        data.add("당신이 가장 존경하는 사람은?"); data.add("당신의 반려동물의 이름은?"); data.add("당신의 반려동물을 입양한 날짜는?"); data.add("당신의 보물 1호는?"); data.add("당신이 가장 인상깊게 본 영화의 제목은?");
        data.add("비밀번호 찾기 힌트");


        //UI생성
        Find_qs_spinner = (Spinner)findViewById(R.id.Find_qs);

        //Adapter
        spinnerAdapter = new com.example.cteam.Adapter.SpinnerAdapter(this,data);

        //Adapter 적용
        Find_qs_spinner.setAdapter(spinnerAdapter);

        //힌트 나타나게
        Find_qs_spinner.setSelection(5);


        Find_qs= (String)Find_qs_spinner.getSelectedItem();

        //스피너가 선택한 값 받게
        Find_qs_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Find_qs=(String)parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        //아이디 찾기 버튼 > 팝업창
        Find_btn_idFind = findViewById((R.id.Find_btn_idFind));
        Find_btn_idFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Find_name.getText().toString().length() != 0 && Find_phonenum.getText().toString().length() != 0) {
                    String member_name = Find_name.getText().toString();
                    String member_phonenum = Find_phonenum.getText().toString();

                    FindSelect findSelect = new FindSelect(member_name, member_phonenum);
                    try {

                        id_return = findSelect.execute().get().trim();

                        Log.d(TAG, "onClick: " + id_return);
                    } catch (ExecutionException e) {
                        e.getMessage();
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }

                } else {
                    Toast.makeText(Find.this, "이름과 전화번호를 모두 입력하세요", Toast.LENGTH_SHORT).show();
                    Log.d("main:find", "이름과 전화번호를 모두 입력하세요 !!!");
                    return;
                }


                if (!id_return.equals("null")) {

                    Log.d("main:find", id_return + "아이디입니다");

                    // db에 아이디가 있을경우 팝업창으로 아이디 알려줌

                    Intent intent = new Intent(getApplicationContext(), IdFindPopup.class);
                    intent.putExtra("data", id_return);
                    startActivityForResult(intent, 1);

                } else {

                    Toast.makeText(Find.this, "존재하는 아이디가 없습니다", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", "아이디가 없습니다");
                    Find_name.setText("");
                    Find_phonenum.setText("");
                    Find_name.requestFocus();
                }
            }

        });



        //비밀번호 찾기 버튼 > 비밀번호 변경 페이지로 이동
        Find_btn_pwFind = findViewById(R.id.Find_btn_pwFind);
        Find_btn_pwFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Find_id.getText().toString().length() != 0 && Find_qs.length() != 0&& Find_as.getText().toString().length() != 0) {
                    String member_id = Find_id.getText().toString();
                    String member_question = Find_qs;
                    String member_answer = Find_as.getText().toString();

                    //Toast.makeText(Find.this, Find_qs, Toast.LENGTH_SHORT).show();

                    FindPwSelect findPwSelect = new FindPwSelect(member_id, member_question,member_answer);
                    try {
                        pw_return=findPwSelect.execute().get().trim();
                    } catch (ExecutionException e) {
                        e.getMessage();
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }

                } else {
                    Toast.makeText(Find.this, "정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                    Log.d("main:find", "비밀번호가 없습니다");
                    return;
                }


                if (!pw_return.equals("null")) {

                    Log.d("main:findPw", pw_return + "패스워드입니다");

                    // 정보가 맞으면 패스워드 변경창으로 넘겨줌

                    Intent intent = new Intent(getApplicationContext(), PasswordModify.class);
                    intent.putExtra("pw_return", pw_return);
                    intent.putExtra("member_id", Find_id.getText().toString());
                    startActivityForResult(intent, 1);


                } else {
                    Toast.makeText(Find.this, "일치하는 회원가입 정보가 없습니다", Toast.LENGTH_SHORT).show();
                    Log.d("main:find", "정보가 입력안됨 !!!");
                    Find_id.setText("");
                    Find_as.setText("");
                    Find_id.requestFocus();
                }
            }



        });




        //회원가입 버튼 > 가입 화면으로 이동
        Find_btn_signup = findViewById(R.id.Find_btn_signup);
        Find_btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });


        //로그인하기 버튼 > 로그인 화면으로 이동
        Find_btn_login = findViewById(R.id.Find_btn_login);
        Find_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    } //onCreate
}