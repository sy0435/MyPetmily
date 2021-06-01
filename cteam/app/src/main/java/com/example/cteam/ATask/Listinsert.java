package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Login;

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


import static com.example.cteam.Common.CommonMethod.ipConfig;
import static com.example.cteam.Login.loginDTO;


public class Listinsert extends AsyncTask<Void,Void,Void> {

    String id,petname,petage,petweight,petgender,petimagepath,imageRealPathA;

    public Listinsert(String id, String petname, String petage, String petweight, String petgender, String petimagepath, String imageRealPathA) {
        this.id = id;
        this.petname = petname;
        this.petage = petage;
        this.petweight = petweight;
        this.petgender = petgender;
        this.petimagepath = petimagepath;
        this.imageRealPathA = imageRealPathA;
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

            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petname", petname, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petage", petage, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petweight", petweight , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petgender", petgender , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petimagepath", petimagepath, ContentType.create("Multipart/related", "UTF-8"));
            builder.addPart("image", new FileBody(new File(imageRealPathA)));

            String postURL = ipConfig + "/app/cPetInsert";

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

    }

}
