package com.example.cteam.ATask.PetPhoto;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.example.cteam.Adapter.PetPhotoAdapter;
import com.example.cteam.Dto.PetPhotoDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.cteam.Common.CommonMethod.ipConfig;

public class PetPhotoDelete extends AsyncTask<Void, Void, Void> {

    String petPhoto_no;
    ArrayList<PetPhotoDTO> petPhotos;



    public PetPhotoDelete(String petPhoto_no,ArrayList<PetPhotoDTO> petPhotos) {
        this.petPhoto_no= petPhoto_no;
        this.petPhotos=petPhotos;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가

            builder.addTextBody("petPhoto_no", petPhoto_no, ContentType.create("Multipart/related", "UTF-8"));

            String postURL = ipConfig + "/app/petPhotoDelete";
            // 전송
            //InputStream inputStream = null;
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



}
