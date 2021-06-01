package com.example.cteam;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.ListUpdate;
import com.example.cteam.ATask.PetPhoto.PetPhotoUpdateP;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.Dto.PetPhotoDTO;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;

public class PetPhotoUpdate extends AppCompatActivity {

    EditText petPhotoUpdate_content;

    String petName = "", member_id = "", petPhoto_no = "";
    ImageView petPhoto;

    Button petPhotoUpdate_back, petPhotoUpdate_upload, petPhotoUpdate_PhotoUpLoad, petPhotoUpdate_Camera;

    public String pImgDbPathU;
    public String imageRealPathA = "", imageDbPathU = "";
    public String imagePath;

    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;

    File file = null;
    long fileSize = 0;

    PetPhotoDTO petPhotoDTO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petphotoupdate);

        petName = petAddDto.getPetname();
        member_id = loginDTO.getMember_id();
        petPhoto = findViewById(R.id.petPhoto);
        petPhotoUpdate_content = findViewById(R.id.petPhotoUpdate_content);
        petPhotoUpdate_upload = findViewById(R.id.petPhotoUpdate_upload);
        petPhotoUpdate_back = findViewById(R.id.petPhotoUpdate_back);
        petPhotoUpdate_PhotoUpLoad = findViewById(R.id.petPhotoUpdate_PhotoUpLoad);
        petPhotoUpdate_Camera = findViewById(R.id.petPhotoUpdate_Camera);

        // 보내온 값 파싱
        Intent intent = getIntent();
        petPhotoDTO = (PetPhotoDTO) intent.getSerializableExtra("petPhoto");

        // 가져온 값 써 넣기
        petPhotoUpdate_content.setText(petPhotoDTO.getPetPhoto_content());

        imagePath = petPhotoDTO.getPetPhoto_imgpath();
        pImgDbPathU = imagePath;
        imageDbPathU = imagePath;

        // 선택된 이미지 보여주기
        petPhoto.setVisibility(View.VISIBLE);
        Glide.with(this).load(imagePath).into(petPhoto);


        petPhotoUpdate_Camera.setOnClickListener(new View.OnClickListener() {
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


        petPhotoUpdate_PhotoUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petPhoto.setVisibility(View.VISIBLE);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), LOAD_IMAGE);


            }
        });


        petPhotoUpdate_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonMethod.isNetworkConnected(getApplicationContext()) == true) {
                    if (fileSize <= 30000000) {  // 파일크기가 30메가 보다 작아야 업로드 할수 있음


                        String petPhoto_content = petPhotoUpdate_content.getText().toString();
                        String petPhoto_no = petPhotoDTO.getPetPhoto_no();
                        Log.d("there", "onClick: ㅇㅇ"+petPhoto_no);


                        PetPhotoUpdateP petPhotoUpdatep = new PetPhotoUpdateP(petPhoto_no, petPhoto_content, pImgDbPathU, imageDbPathU, imageRealPathA);
                        petPhotoUpdatep.execute();

                        Intent showIntent = new Intent(getApplicationContext(), PetSelect.class);
                        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        showIntent.putExtra("fragment", "petphotoupdate");
                        startActivity(showIntent);

                        finish();

                    } else {
                        // 알림창 띄움
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setTitle("알림");
                        builder.setMessage("파일 크기가 30MB초과하는 파일은 업로드가 제한되어 있습니다.\n30MB이하 파일로 선택해 주십시요!!!");
                        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "인터넷이 연결되어 있지 않습니다.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        petPhotoUpdate_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
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
                if (newBitmap != null) {
                    petPhoto.setImageBitmap(newBitmap);
                } else {
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = file.getAbsolutePath();
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathU = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));

                Log.d("Sub1Update:picPath", file.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == LOAD_IMAGE && resultCode == RESULT_OK) {

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
                if (newBitmap != null) {
                    petPhoto.setImageBitmap(newBitmap);
                } else {
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = path;
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathU = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e) {
                e.printStackTrace();
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


