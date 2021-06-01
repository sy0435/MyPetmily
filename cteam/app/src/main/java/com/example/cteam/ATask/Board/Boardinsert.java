package com.example.cteam.ATask.Board;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

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

public class Boardinsert extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "Boardinsert";
    
    String subject, title, content, city, region, imageDbPathA, imageRealPathA,
            member_id, Petimage_path, petname;

    public Boardinsert(String subject, String title, String content, String city, String region,
                       String imageDbPathA, String imageRealPathA, String member_id,
                       String petname, String Petimage_path) {
        this.subject = subject;
        this.title = title;
        this.content = content;
        this.city = city;
        this.region = region;
        this.imageDbPathA = imageDbPathA;
        this.imageRealPathA = imageRealPathA;
        this.member_id = member_id;
        this.Petimage_path = Petimage_path;
        this.petname = petname;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.d(TAG, "doInBackground: 실행");
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가
            builder.addTextBody("member_id", member_id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("subject", subject, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("title", title, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("content", content, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("city", city, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("region", region, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("Petimage_path", Petimage_path, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petname", petname, ContentType.create("Multipart/related", "UTF-8"));
            if (imageDbPathA != null) {
                builder.addTextBody("dbImgPath", imageDbPathA, ContentType.create("Multipart/related", "UTF-8"));
            }
            if (imageRealPathA != null) {
                builder.addPart("image", new FileBody(new File(imageRealPathA)));
            }

            String postURL = ipConfig + "/app/boardinsert";

            // 전송
            httpClient = AndroidHttpClient.newInstance("cteam");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
/*
            // 받기
            BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
*/

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }
        }
        Log.d(TAG, "doInBackground: 종료");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Log.d("Sub1Add:imageFilePath1", "추가성공");

    }
//    HttpClient httpClient;
//    HttpPost httpPost;
//    HttpResponse httpResponse;
//    HttpEntity httpEntity;

//    @Override
//    protected String doInBackground(Void... voids) {

//        String result = "";
//        String postURL = ipConfig + "/app/Boardinsert";

//        try {
//            // MultipartEntityBuild 생성
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

//            // 전송
//            InputStream inputStream = null;
//            httpClient = AndroidHttpClient.newInstance("cteam");
//            httpPost = new HttpPost(postURL);
//            httpPost.setEntity(builder.build());
//            httpResponse = httpClient.execute(httpPost);
//            httpEntity = httpResponse.getEntity();
//            inputStream = httpEntity.getContent();
//
//            /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//            StringBuilder stringBuilder = new StringBuilder();
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null){
//                stringBuilder.append(line + "\n");
//            }
//            String jsonStr = stringBuilder.toString();

//            inputStream.close();*/

//        } catch (Exception e) {
//            Log.d("Sub1", e.getMessage());
//            e.printStackTrace();
//        }finally {
//            if(httpEntity != null){
//                httpEntity = null;
//            }
//            if(httpResponse != null){
//                httpResponse = null;
//            }
//            if(httpPost != null){
//                httpPost = null;
//            }
//            if(httpClient != null){
//                httpClient = null;
//            }

//        }

//        return "";
//    }

}
