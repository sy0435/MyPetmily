package com.example.cteam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GoogleLogin extends AppCompatActivity {
    TextView GoogleLogin_profile;
    ImageView GoogleLogin_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");    //로그인으로부터 닉네임 전달 받음
        String photourl = intent.getStringExtra("photourl");    //로그인으로부터 프로필 사진 url 전달받음

        GoogleLogin_profile=findViewById(R.id.google_profile);
        GoogleLogin_profile.setText(nickname);//닉네임을 텍스트뷰에 세팅

        GoogleLogin_image=findViewById(R.id.googleLogin_image);
        Glide.with(this).load(photourl).into(GoogleLogin_image);//프로필 url을 이미지뷰에 세팅



    }
}