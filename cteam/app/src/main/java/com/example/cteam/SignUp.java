package com.example.cteam;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cteam.ATask.IdDoubleCheck;
import com.example.cteam.ATask.JoinInsert;
import com.example.cteam.Common.CommonMethod;

import java.io.File;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import static com.example.cteam.Common.CommonMethod.ipConfig;

public class SignUp extends AppCompatActivity {

    String state="";
    String member_id="";
    EditText signupId, signupPw, signupName, signupPwConfirm, signupAnswer, signupPhoneNum;
    Button btnJoin, btnCancel, Signup_IdCheck,Sigunup_photoAdd,Signup_idCheck;
    TextView SignUp_agree_text,txtResult;
    CheckBox SignUp_agree;
    ImageView SignUp_img;
    //UI
    Spinner Signup_qs_spinner;


    //Adapter
    SpinnerAdapter spinnerAdapter;
    String signupQuestion;
    int data1;

    final int LOAD_IMAGE = 1001;
    final int CAMERA_REQUEST = 1000;

    public String imageRealPathA, imageDbPathA;



    File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupId = findViewById(R.id.SignUp_id);
        signupPw = findViewById(R.id.SignUp_pw);
        signupName = findViewById(R.id.SignUp_name);
        //  signupQuestion = findViewById(R.id.SignUp_qs);
        signupAnswer = findViewById(R.id.SignUp_qs_as);
        signupPhoneNum = findViewById(R.id.SignUp_phonenum);
        btnJoin = findViewById(R.id.SignUp_join);
        btnCancel = findViewById(R.id.SignUp_cancel);
        signupPwConfirm =findViewById(R.id.SignUp_pw_confirm);
        SignUp_agree= findViewById(R.id.SignUp_agree);
        Signup_IdCheck=findViewById(R.id.Signup_idCheck);
        SignUp_img=findViewById(R.id.SignUp_img);
        Signup_idCheck=findViewById(R.id.Signup_idCheck);


        member_id=signupId.getText().toString();


        if(data1==1){
            SignUp_agree.setChecked(true);
        }


        //?????????
        List<String> data = new ArrayList<>();
        data.add("????????? ?????? ???????????? ??????????"); data.add("????????? ??????????????? ??????????"); data.add("????????? ??????????????? ????????? ??????????"); data.add("????????? ?????? 1???????"); data.add("????????? ?????? ???????????? ??? ????????? ??????????");
        data.add("???????????? ?????? ??????");


        //UI??????
        Signup_qs_spinner = (Spinner)findViewById(R.id.SignUp_qs);

        //Adapter
        spinnerAdapter = new com.example.cteam.Adapter.SpinnerAdapter(this,data);

        //Adapter ??????
        Signup_qs_spinner.setAdapter(spinnerAdapter);

        //?????? ????????????
        Signup_qs_spinner.setSelection(5);


        signupQuestion= (String)Signup_qs_spinner.getSelectedItem();

        //???????????? ????????? ??? ??????
        Signup_qs_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                signupQuestion=(String)parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        Signup_IdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member_id=signupId.getText().toString();

                if(!Pattern.matches("^[a-zA-Z0-9]{5,10}$", member_id)) {
                    Toast.makeText(SignUp.this, "???????????? ??????,?????? 5-10?????? ???????????????.", Toast.LENGTH_LONG).show();
                    signupId.setText("");
                    signupId.requestFocus();
                    return;
                }else {

                    IdDoubleCheck idDoubleCheck = new IdDoubleCheck(member_id);
                    try {
                        state = idDoubleCheck.execute().get().trim();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (state.equals("1")) {
                        Toast.makeText(SignUp.this, "????????? ??????????????????", Toast.LENGTH_SHORT).show();
                        signupId.setText("");
                        signupId.requestFocus();
                    } else {
                        Toast.makeText(SignUp.this, "??????????????? ??????????????????", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });



        btnJoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                member_id=signupId.getText().toString();
                String member_pw = signupPw.getText().toString();
                String member_name = signupName.getText().toString();
                String member_question =  signupQuestion;
                String member_answer = signupAnswer.getText().toString();
                String member_phonenum = signupPhoneNum.getText().toString();
                String member_signupPwConfirm =signupPwConfirm.getText().toString();

                //?????? ?????????
                if(!Pattern.matches("^[???-???]{2,8}$", member_name))
                {
                    Toast.makeText(SignUp.this,"????????? ?????? 2-8?????? ???????????????.",Toast.LENGTH_SHORT).show();
                    signupName.setText("");
                    signupName.requestFocus();
                    return;
                }


                //????????? ?????????
                if(!Pattern.matches("^[a-zA-Z0-9]{5,10}$", member_id)) {
                    Toast.makeText(SignUp.this, "???????????? ??????,?????? 5-10?????? ???????????????.", Toast.LENGTH_LONG).show();
                    signupId.setText("");
                    signupId.requestFocus();
                    return;
                }





                //????????????
                if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[.$@$!%*#?&])[A-Za-z\\d.$@$!%*#?&]{8,20}$", member_pw))
                {
                    Toast.makeText(SignUp.this,"??????????????? ??????,??????,??????????????? ????????? 8-20???????????????.",Toast.LENGTH_LONG).show();
                    signupPw.setText("");
                    signupPw.requestFocus();
                    return;
                }

                if(member_pw.equals(member_id)){
                    Toast.makeText(SignUp.this,"??????????????? ???????????? ?????? ??? ????????????",Toast.LENGTH_LONG).show();
                    signupPw.setText("");
                    signupPw.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(signupPwConfirm.getText())){
                    Toast.makeText(SignUp.this,"????????????????????? ???????????????",Toast.LENGTH_LONG).show();
                    signupPwConfirm.requestFocus();
                    return;
                }

                //??????????????? ?????????????????? ??????
                if(!member_signupPwConfirm.equals(member_pw)){
                    Toast.makeText(SignUp.this,"??????????????? ????????????",Toast.LENGTH_LONG).show();
                    signupPwConfirm.setText("");
                    signupPwConfirm.requestFocus();
                    return;
                }


                //???????????? ?????? ????????????
                if(signupQuestion.equals("???????????? ?????? ??????")){
                    Toast.makeText(SignUp.this, "?????????????????? ????????? ???????????????", Toast.LENGTH_SHORT).show();
                    Signup_qs_spinner.requestFocus();

                    return;
                }


                //???????????? ??? ??????

                if(TextUtils.isEmpty(signupAnswer.getText())){
                    Toast.makeText(SignUp.this,"???????????? ???????????? ???????????????",Toast.LENGTH_LONG).show();
                    signupAnswer.requestFocus();
                    return;
                }

                //????????? ?????????
                if(!Pattern.matches("^01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})$", member_phonenum))
                {
                    Toast.makeText(SignUp.this,"????????? ????????? ????????? ????????????.",Toast.LENGTH_LONG).show();
                    signupPhoneNum.setText("");
                    signupPhoneNum.requestFocus();
                    return;
                }


                //????????????
                if(!SignUp_agree.isChecked()){
                    Toast.makeText(SignUp.this, "????????? ???????????????", Toast.LENGTH_SHORT).show();
                    return;
                }


                JoinInsert joinInsert = new JoinInsert(member_id,member_pw, member_name, member_question, member_answer, member_phonenum);
                try {
                    state = joinInsert.execute().get().trim();
                    Log.d("main:joinact0 : ", state);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(state.equals("1")){
                    Toast.makeText(SignUp.this, "???????????? ???????????????", Toast.LENGTH_SHORT).show();
                    Log.d("main:joinact", "???????????? !!!");
                    finish();
                }else{

                    Toast.makeText(SignUp.this, "????????? ??????????????????", Toast.LENGTH_SHORT).show();
                    Log.d("main:joinact", "???????????? !!!");


                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //????????????
        SignUp_agree_text = (TextView) findViewById(R.id.SignUp_agree_text);

        SignUp_agree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                startActivityForResult(intent, 1);



            }
        });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            try {
                // ????????? ????????? ??? ????????????
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                if(newBitmap != null){
                    SignUp_img.setImageBitmap(newBitmap);
                    SignUp_img.setBackgroundResource(0);
                }else{
                    Toast.makeText(this, "???????????? null ?????????...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = file.getAbsolutePath();
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathA = ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e){
                e.printStackTrace();
            }
        }else if (requestCode == LOAD_IMAGE && resultCode == RESULT_OK) {

            try {
                String path = "";
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    // Get the path from the Uri
                    path = getPathFromURI(selectedImageUri);
                }
                // ????????? ????????? ??? ????????????
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                if(newBitmap != null){
                    SignUp_img.setImageBitmap(newBitmap);
                }else{
                    Toast.makeText(this, "???????????? null ?????????...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = path;
                Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA);
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathA = ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e){
                e.printStackTrace();
            }
        }else if(requestCode==1 && resultCode==RESULT_OK){

            int data1=data.getIntExtra("data",0);
            if(data1==1){
                SignUp_agree.setChecked(true);
            }else{
                Toast.makeText(this, "??????", Toast.LENGTH_SHORT).show();
            }
        }



    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}
