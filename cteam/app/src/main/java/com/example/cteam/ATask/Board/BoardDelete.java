package com.example.cteam.ATask.Board;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.InputStream;

import static com.example.cteam.Common.CommonMethod.ipConfig;

public class BoardDelete extends AsyncTask<Void, Void, Void> {

    String board_num2, board_delete_image;

    public BoardDelete(String board_num2, String board_delete_image) {
        this.board_num2 = board_num2;
        this.board_delete_image = board_delete_image;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String postURL = ipConfig + "/app/boarddelete";
        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 문자열 및 데이터 추가
            builder.addTextBody("board_num2", board_num2, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("delDbImgPath", board_delete_image, ContentType.create("Multipart/related", "UTF-8"));


            // 전송
            InputStream inputStream = null;
            HttpClient httpClient = AndroidHttpClient.newInstance("cteam");
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            // 응답
           /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }*/
            //inputStream.close();

            // 응답결과
            /*String result = stringBuilder.toString();
            Log.d("response", result);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
