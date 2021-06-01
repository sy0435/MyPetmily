package com.example.cteam;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.cteam.ATask.Listinsert;
import com.example.cteam.ATask.PetPhoto.PetPhotoInsertS;
import com.example.cteam.Adapter.PetPhotoAdapter;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Dto.PetPhotoDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;

public class PetPhotoInsert extends AppCompatActivity {

    PetPhotoAdapter petPhotoAdapter;
    ArrayList<PetPhotoDTO> petPhotos;

   PetSelect Activity;

    TextView petPhotoInsert_content;
    String petName, member_id, PetPhoto_content, PetPhoto_imgPath;
    ImageView petPhoto;

    Button petPhotoInsert_upload,petPhotoInsert_back,petPhotoInsert_PhotoUpLoad,petPhotoInsert_Camera;

    public String imageRealPathA, imageDbPathA;

    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;

    File file = null;
    long fileSize = 0;

    java.text.SimpleDateFormat tmpDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petphoto_insert);

      //  petPhotoAdapter= (PetPhotoAdapter) getIntent().getSerializableExtra("adpater");


        petPhotoInsert_upload=findViewById(R.id.petPhotoInsert_upload);
        petPhotoInsert_back=findViewById(R.id.petPhotoInsert_back);
        petPhotoInsert_content=findViewById(R.id.petPhotoInsert_content);
        petPhoto=findViewById(R.id.petPhoto);
        petPhotoInsert_PhotoUpLoad=findViewById(R.id.petPhotoInsert_PhotoUpLoad);
        petPhotoInsert_Camera=findViewById(R.id.petPhotoInsert_Camera);

        petPhotos=new ArrayList<>();

        petPhotoInsert_PhotoUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petPhoto.setVisibility(View.VISIBLE);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);
            }
        });

        petPhotoInsert_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        petPhotoInsert_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    try {
                        file = createFile();
                        Log.d("Sub1Update:FilePath ", file.getAbsolutePath());
                    } catch (Exception e) {
                        Log.d("Sub1Update:error1", "Something Wrong", e);
                    }

                    petPhoto.setVisibility(View.VISIBLE);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                FileProvider.getUriForFile(getApplicationContext(),
                                        getApplicationContext().getPackageName() + ".fileprovider", file));
                        Log.d("sub1:appId", getApplicationContext().getPackageName());
                    } else {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    }

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, CAMERA_REQUEST);
                    }

                } catch (Exception e) {
                    Log.d("Sub1Update:error2", "Something Wrong", e);
                }

            }
        });

        petPhotoInsert_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonMethod.isNetworkConnected(getApplicationContext()) == true) {
                    if (fileSize <= 30000000) {
                          member_id = loginDTO.getMember_id().toString();
                          petName = petAddDto.getPetname();

                        PetPhoto_content = petPhotoInsert_content.getText().toString();

                        PetPhotoInsertS petPhotoInsertS = new PetPhotoInsertS(member_id, petName, PetPhoto_content, imageDbPathA, imageRealPathA,petPhotoAdapter,petPhotos);
                        petPhotoInsertS.execute();

                        Intent showIntent = new Intent(getApplicationContext(), PetPhotoInsert.class);
                        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(showIntent);

                     //   petPhotoAdapter.notifyDataSetChanged();

                        //petphoto 화면갱신위해해
                       setResult(Activity.RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    private File createFile() throws IOException {
        java.text.SimpleDateFormat tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

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
                }else{
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = file.getAbsolutePath();
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathA = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;

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
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
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


}
