package com.example.cteam.pet_add;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.AutoText;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.cteam.ATask.Listinsert;
import com.example.cteam.Adapter.GenderSpinnerAdapter;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Login;
import com.example.cteam.PetAdd;
import com.example.cteam.PetSelect;
import com.example.cteam.R;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;

public class petInsert extends AppCompatActivity {

    EditText petName;
    EditText petAge;
    EditText petWeight;
    String petGender;
    Button goMain;
    Spinner petgenderSpinner;
    GenderSpinnerAdapter spinnerAdapter;

    String petname = "", petage = "",petweight = "",petgender;
    ImageView petPhoto;
    String id="";

    Button btnLoad,btnCancle,btn_add;

    public String imageRealPathA="", imageDbPathA="";

    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;

    File file = null;
    long fileSize = 0;

    java.text.SimpleDateFormat tmpDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add_insert);

        petName = findViewById(R.id.petName);
        petAge = findViewById(R.id.petAge);
        //petGender = findViewById(R.id.petGender);
        petWeight = findViewById(R.id.petWeight);
        petPhoto = findViewById(R.id.petPhoto);

        btnLoad = findViewById(R.id.btnLoad);
        btn_add = findViewById(R.id.btn_add);
        btnCancle = findViewById(R.id.btnCancle);

        //데이터
        List<String> data = new ArrayList<>();
        data.add("여(중성화 O)"); data.add("여(중성화 X)"); data.add("남(중성화 O)"); data.add("남(중성화 X)");data.add("성별선택");


        //UI생성
        petgenderSpinner = findViewById(R.id.petGender);

        //Adapter
        spinnerAdapter = new com.example.cteam.Adapter.GenderSpinnerAdapter(this,data);

        //Adapter 적용
        petgenderSpinner.setAdapter(spinnerAdapter);

        //힌트 나타나게
        petgenderSpinner.setSelection(4);


        petGender= (String) petgenderSpinner.getSelectedItem();

        //스피너가 선택한 값 받게
        petgenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                petGender=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                petPhoto.setVisibility(View.VISIBLE);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);

            }
        });

        goMain = findViewById(R.id.goMain);
        goMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PetSelect.class);
                startActivity(intent);
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(petName.getText().toString().equals("") || petAge.getText().toString().equals("")
                        || petWeight.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸을 모두 채워주세요", Toast.LENGTH_SHORT).show();
                }else if(imageRealPathA.equals("")){
                    Toast.makeText(petInsert.this, "사진을 등록해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    btnAddClicked();
                }
            }

        });
    }

    private File createFile() throws IOException {

        String imageFileName = "My" + tmpDateFormat.format(new Date()) + ".jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir, imageFileName);

        return curFile;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            try {
                // 이미지 돌리기 및 리사이즈
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                if(newBitmap != null){
                    petPhoto.setImageBitmap(newBitmap);
                    imageRealPathA = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                    imageDbPathA = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;
                }else{

                    Toast.makeText(this, "이미지를 등록해주세요", Toast.LENGTH_SHORT).show();
                }



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
                // 이미지 돌리기 및 리사이즈
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                if(newBitmap != null){
                    petPhoto.setImageBitmap(newBitmap);
                }else{

                    Toast.makeText(petInsert.this, "이미지를 등록해주세요", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = path;
                Log.d("petInsert", "imageFilePathA Path : " + imageRealPathA);
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathA = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    // Get the real path from the URI
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
    private String getURLForResource(int resId) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resId).toString();
    }
    public void btnAddClicked() {
        
        if (CommonMethod.isNetworkConnected(this) == true) {
            if (fileSize <= 30000000) {
                id = loginDTO.getMember_id().toString();
                petname = petName.getText().toString();
                petage = petAge.getText().toString();
                petgender = petGender;
                petweight = petWeight.getText().toString();

                Listinsert listinsert = new Listinsert(id, petname, petage, petweight, petgender, imageDbPathA, imageRealPathA);
                listinsert.execute();

                Intent showIntent = new Intent(getApplicationContext(), PetAdd.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(showIntent);

                Intent refresh = new Intent(this, PetAdd.class);
                startActivity(refresh);
                finish();
            } else {
                Toast.makeText(petInsert.this, "이미지를 등록해주세요", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(petInsert.this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        }
    }
