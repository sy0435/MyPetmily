package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cteam.ATask.FindPwSelect;
import com.example.cteam.ATask.PwUpdate;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class PasswordModify extends AppCompatActivity {
    //extends Activity implements OnClickListener
    Button PasswordModify_cancel, PasswordModify_change;
    TextView PasswordModify_pw, PasswordModify_pw_confirm;
    String state,pw_return,member_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_modify);

        //비밀번호 변경취소 버튼 > 로그인 화면으로 이동
        PasswordModify_cancel = (Button)findViewById(R.id.PasswordModify_cancel);
        PasswordModify_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(getApplicationContext(), Login.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentLogin);
                finish();
            }
        });

        PasswordModify_pw=findViewById(R.id.PasswordModify_pw);
        PasswordModify_pw_confirm=findViewById(R.id.PasswordModify_pw_confirm);

        //찾은 패스워드를 putextra에서 가져온다
        Intent intent=getIntent();
        pw_return=intent.getStringExtra("pw_return");
        member_id=intent.getStringExtra("member_id");
        //비밀번호 변경하기 버튼 > 로그인 화면으로 이동
        PasswordModify_change = (Button)findViewById(R.id.PasswordModify_change);

        PasswordModify_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (PasswordModify_pw.getText().toString().length() != 0 && PasswordModify_pw_confirm.getText().toString().length() != 0) {

                    if (PasswordModify_pw.getText().toString().equals(PasswordModify_pw_confirm.getText().toString())) { //비밀번호 확인이 같으면

                        if (!PasswordModify_pw.getText().toString().equals(pw_return)) {

                            if(!PasswordModify_pw.getText().toString().equals(member_id)) {

                                if(Pattern.matches("^[a-zA-Z0-9]{8,12}$", PasswordModify_pw.getText().toString())) {
                                    String member_pw = PasswordModify_pw.getText().toString();

                                    PwUpdate pwUpdate = new PwUpdate(member_pw, member_id);
                                    try {
                                        state = pwUpdate.execute().get().trim();
                                    } catch (ExecutionException e) {
                                        e.getMessage();
                                    } catch (InterruptedException e) {
                                        e.getMessage();
                                    }

                                }else{//비밀번호 형식이 다르면
                                    Toast.makeText(PasswordModify.this, "비밀번호는 영문,숫자 8-12글자로 만들어주세요", Toast.LENGTH_SHORT).show();
                                    Log.d("main:find", "정규표현식에 걸림");
                                    PasswordModify_pw.setText("");
                                    PasswordModify_pw_confirm.setText("");
                                    PasswordModify_pw.requestFocus();
                                    return;
                                }

                            }else{  //아이디와 비밀번호가 같으면
                                Toast.makeText(PasswordModify.this, "비밀번호는 아이디와 같을 수 없습니다", Toast.LENGTH_SHORT).show();
                                Log.d("main:find", "아이디와 비밀번호가 같음");
                                PasswordModify_pw.setText("");
                                PasswordModify_pw_confirm.setText("");
                                PasswordModify_pw.requestFocus();
                                return;
                            }




                        } else { //변경 전 비밀번호와 변경 비밀번호가 같으면
                            Toast.makeText(PasswordModify.this, "새로운 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                            Log.d("main:find", "비밀번호가 바뀌지 않음");
                            PasswordModify_pw.setText("");
                            PasswordModify_pw_confirm.setText("");
                            PasswordModify_pw.requestFocus();
                            return;
                        }
                    } else {
                        Toast.makeText(PasswordModify.this, "비밀번호확인이 다릅니다", Toast.LENGTH_LONG).show();
                        Log.d("main:find", "비밀번호가 다릅니다");
                        return;
                    }

                } else {
                    Toast.makeText(PasswordModify.this, "변경할 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    Log.d("main:find", "비밀번호가 없습니다");
                    return;
                }


                if (state.equals("1")) {

                    Log.d("main:ModifyPw", state + "변경");

                    // 비밀번호 올바르게 수정하면 로그인창으로 넘겨줌

                    Toast.makeText(PasswordModify.this, "변경된 비밀번호로 로그인하세요", Toast.LENGTH_LONG).show();
                    Intent intentLogin = new Intent(getApplicationContext(), Login.class);
                    intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intentLogin.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intentLogin);
                    finish();

                } else {
                    Toast.makeText(PasswordModify.this, "변경 실패했습니다 !!!", Toast.LENGTH_SHORT).show();
                    Log.d("main:find", "정보가 입력안됨 !!!");
                    PasswordModify_pw.setText("");
                    PasswordModify_pw_confirm.setText("");
                    PasswordModify_pw.requestFocus();
                }

            }
        });

    } //onCreate
}