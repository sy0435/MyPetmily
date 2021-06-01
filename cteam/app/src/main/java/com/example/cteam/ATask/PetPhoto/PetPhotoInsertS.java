package com.example.cteam.ATask.PetPhoto;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cteam.Adapter.PetPhotoAdapter;
import com.example.cteam.Dto.PetPhotoDTO;
import com.example.cteam.Login;
import com.example.cteam.PetPhoto;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;


import static com.example.cteam.Common.CommonMethod.ipConfig;
import static com.example.cteam.Login.loginDTO;


public class PetPhotoInsertS extends AsyncTask<Void,Void,Void> {

    String member_id,petName,PetPhoto_content,imageDbPathA, imageRealPathA;
    PetPhotoAdapter petPhotoAdapter;
    ArrayList<PetPhotoDTO> petPhotos;

    public PetPhotoInsertS(String member_id, String petName, String PetPhoto_content, String imageDbPathA, String imageRealPathA, PetPhotoAdapter petPhotoAdapter, ArrayList<PetPhotoDTO> petPhotos) {
        this.member_id = member_id;
        this.petName = petName;
        this.PetPhoto_content = PetPhoto_content;
        this.imageDbPathA = imageDbPathA;
        this.imageRealPathA = imageRealPathA;
        this.petPhotoAdapter = petPhotoAdapter;
        this.petPhotos=petPhotos;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {


        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가

            builder.addTextBody("member_id", member_id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petName", petName, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("PetPhoto_content", PetPhoto_content, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("imageDbPathA", imageDbPathA , ContentType.create("Multipart/related", "UTF-8"));
            builder.addPart("image", new FileBody(new File(imageRealPathA)));

            String postURL = ipConfig + "/app/PetPhotoInsert";

            HttpClient httpClient = AndroidHttpClient.newInstance("cteam");
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Log.d("Sub1Add:imageFilePath1", "추가성공");

        /*        if(progressDialog != null){
            progressDialog.dismiss();
        }

        Log.d("PetAdd", "petList Select Complete!!!");  */

        //adapter.notifyDataSetChanged();
        if (petPhotos.size() > 0) {    //데이타가 추가, 수정되었을때

            petPhotoAdapter.notifyDataSetChanged();

        } else {    //뷰에 표시될 데이타가 없을때



        }

    }




}
