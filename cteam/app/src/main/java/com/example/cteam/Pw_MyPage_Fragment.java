package com.example.cteam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.cteam.Dto.MemberDTO;

public class Pw_MyPage_Fragment extends Fragment {
    PetSelect activity;
    Button pw_btn;
    Button pw_cancleBtn;
    EditText editPW;
    //MemberDTO dto;
    Bundle bundle = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.pw_mypage,container,false);

        activity = (PetSelect) getActivity();

        editPW = rootView.findViewById(R.id.edPW);
        pw_btn = rootView.findViewById(R.id.pw_btn);
        pw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editPW.getText().toString().trim().equals(Login.loginDTO.getMember_pw())){

                    activity.onFragmentChange(1, bundle);


                }else {
                    showMessage();
                }
            }
        });
        pw_cancleBtn = rootView.findViewById(R.id.pw_cancleBtn);
        pw_cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(3, null);
            }
        });
        return rootView;

    }
    private void showMessage() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(activity);
        builder.setTitle(Html.fromHtml("<font color='#333333'>안내</font>"));
        builder.setMessage(Html.fromHtml("<font color='#333333'>비밀번호를 다시 입력하세요!</font>"));
        //builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setIconAttribute(android.R.attr.alertDialogIcon);

        builder.setPositiveButton(Html.fromHtml("<font color='#333333'>확인</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public void onStart(){
        super.onStart();
        editPW.setText(null);
    }

    @Override
    public void onResume(){
        super.onResume();
        editPW.setText(null);

    }
}
