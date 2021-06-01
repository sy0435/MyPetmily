package com.example.cteam.ATask.Board;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cteam.Dto.BoardDetailDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.example.cteam.Common.CommonMethod.ipConfig;

public class BoardDetailUpdate extends AsyncTask<Void, Void, BoardDetailDTO> {

    private static final String TAG = "BoardDetailUpdate";

    String board_num2, board_write_subject, board_write_region1, board_write_region2
            ,board_write_title, board_write_content, pImgDbPathU, imageDbPathU, imageRealPathU;

    public BoardDetailUpdate(String board_num2, String board_write_subject, String board_write_region1, String board_write_region2,
                             String board_write_title, String board_write_content, String pImgDbPathU,
                             String imageDbPathU, String imageRealPathU) {
        this.board_num2 = board_num2;
        this.board_write_subject = board_write_subject;
        this.board_write_region1 = board_write_region1;
        this.board_write_region2 = board_write_region2;
        this.board_write_title = board_write_title;
        this.board_write_content = board_write_content;
        this.pImgDbPathU = pImgDbPathU;
        this.imageDbPathU = imageDbPathU;
        this.imageRealPathU = imageRealPathU;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected BoardDetailDTO doInBackground(Void... voids) {
        try {
            // MultipartEntityBuild 생성
            String postURL = "";
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가
            builder.addTextBody("board_num2", board_num2, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("board_write_subject", board_write_subject, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("board_write_region1", board_write_region1, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("board_write_region2", board_write_region2, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("board_write_title", board_write_title, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("board_write_content", board_write_content, ContentType.create("Multipart/related", "UTF-8"));

            Log.d(TAG, board_num2);
            Log.d(TAG, board_write_subject);
            Log.d(TAG, board_write_region1);
            Log.d(TAG, board_write_region2);
            Log.d(TAG, board_write_title);
            Log.d(TAG, board_write_content);

            if(!imageRealPathU.equals("")){
                Log.d(TAG + postURL, "1");
                // 기존에 있던 DB 경로
                builder.addTextBody("pDbImgPath", pImgDbPathU, ContentType.create("Multipart/related", "UTF-8"));
                // DB에 저장할 경로
                builder.addTextBody("dbImgPath", imageDbPathU, ContentType.create("Multipart/related", "UTF-8"));
                // 실제 이미지 파일
                builder.addPart("image", new FileBody(new File(imageRealPathU)));

                postURL = ipConfig + "/app/boarddetailupdate";

            }else if(imageRealPathU.equals("")){  // 이미지를 바꾸지 않았다면
                Log.d(TAG + postURL, "3");
                postURL = ipConfig + "/app/boarddetailupdateNo";
            }else{
                Log.d(TAG + postURL, "5 : error");
            }
            Log.d(TAG + postURL , postURL);
            //postURL = ipConfig + "/app/anUpdateMulti";
            // 전송
            InputStream inputStream = null;
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
    protected void onPostExecute(BoardDetailDTO boardDetailDTO) {
        super.onPostExecute(boardDetailDTO);
        //dialog.dismiss();

    }

}
