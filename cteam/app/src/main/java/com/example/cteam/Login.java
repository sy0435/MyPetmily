package com.example.cteam;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cteam.R;
import com.example.cteam.ATask.LoginSelect;
import com.example.cteam.Dto.MemberDTO;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    // 로그인이 성공하면 static 로그인DTO 변수에 담아서
    // 어느곳에서나 접근할 수 있게 한다
    public static MemberDTO loginDTO = null;

    EditText loginId, loginPw;
    Button loginBtn1, loginBtn2,loginBtn3,mainBtn;
    SignInButton btn_google;//구글 로그인버튼
    FirebaseAuth auth;  //파이어베이스 인증객체
    //GoogleApiClient googleApiClient;    //구글 api
    //private static final int REQ_SIGN_GOOGLE=100;   //구글로그인결과

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        checkDangerousPermissions();//위험권한

        loginId = findViewById(R.id.login_id);
        loginPw = findViewById(R.id.login_pw);

        loginBtn1 = findViewById(R.id.login_btn1);
        loginBtn2 = findViewById(R.id.login_btn2);
        loginBtn3 = findViewById(R.id.login_btn3);

//        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this,this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
//                .build();
//
//        auth = FirebaseAuth.getInstance(); //파이어베이스 인증객체 초기화
//
//        btn_google = findViewById(R.id.google_login);
//        btn_google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//                startActivityForResult(intent, REQ_SIGN_GOOGLE);
//
//            }
//        });


        // 로그인 버튼
        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDTO = null;

                if(loginId.getText().toString().length() != 0 && loginPw.getText().toString().length() != 0){
                    String id = loginId.getText().toString();
                    String pw = loginPw.getText().toString();

                    LoginSelect loginSelect = new LoginSelect(id, pw);
                    try {
                        loginSelect.execute().get();
                    } catch (ExecutionException e) {
                        e.getMessage();
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }

                } else {
                    Toast.makeText(Login.this, "아이디와 암호를 모두 입력하세요", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", "아이디와 암호를 모두 입력하세요 !!!");
                    return;
                }

                if(loginDTO != null){
                   // Toast.makeText(Login.this, "로그인 되었습니다 !!!", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", loginDTO.getMember_id() + "님 로그인 되었습니다 !!!");
                    Log.d("main:login", loginDTO.getMember_pw() + "님 로그인 되었습니다 !!!");
                    Log.d("main:login", loginDTO.getMember_name() + "님 로그인 되었습니다 !!!");

                    // 로그인 정보에 값이 있으면 로그인이 되었으므로 메인화면으로 이동
                    Intent intent = new Intent(getApplicationContext(), PetAdd.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(Login.this, "아이디나 비밀번호가 일치안함 !!!", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", "아이디나 비밀번호가 일치안함 !!!");
                    loginId.setText("");
                    loginPw.setText("");
                    loginId.requestFocus();
                }

            }
        });
        //비밀 번호 찾기 버튼
        loginBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Find.class);
                startActivity(intent);
            }
        });

        // 회원 가입 버튼
        loginBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 화면
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//구글 로그인 인증을 요청했을때 결과값을 되돌려받는곳
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_SIGN_GOOGLE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){ //인증결과가 성공적이면
                GoogleSignInAccount account = result.getSignInAccount();//account 라는 데이터는 구글 로그인 정보를 담음(닉네임,프로필사진)
                resultLogin(account); //로그인결과값 출력수행하라는 메소드
            }
        }

    }
*/

    private void resultLogin(final GoogleSignInAccount account) {
        AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){    //로그인이 성공햇으면
                            Toast.makeText(Login.this, "로그인성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), GoogleLogin.class);
                            intent.putExtra("nickname",account.getDisplayName());
                            intent.putExtra("photourl",String.valueOf(account.getPhotoUrl()));  //특정자료형을 string형태로 변환
                            startActivity(intent);

                        } else{  //로그인이 실패했으면
                            Toast.makeText(Login.this, "로그인실패", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.VIBRATE,
                Manifest.permission.CAMERA

        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
           // Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
           // Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {



    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    //  Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                   // Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
