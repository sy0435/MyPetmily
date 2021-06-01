package com.example.cteam.ATask.Board;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.cteam.Dto.BoardDetailDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.cteam.Common.CommonMethod.ipConfig;
import static com.example.cteam.Login.loginDTO;

public class BoardDetailSelect extends AsyncTask<Void, Void, BoardDetailDTO> {

    private static final String TAG = "BoardDetailSelect";
    BoardDetailDTO details;
    String num, member_id;

    public BoardDetailSelect(String num, String member_id) {
        this.num = num;
        this.member_id = member_id;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    @Override
    protected BoardDetailDTO doInBackground(Void... voids) {
        String postURL = ipConfig + "/app/boarddetail";

        try {
            Log.d(TAG, "doInBackground: 실행");

            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 문자열 및 데이터 추가
            builder.addTextBody("board_num", num, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("member_id", member_id, ContentType.create("Multipart/related", "UTF-8"));


            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("cteam");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            // 하나의 오브젝트 가져올때
            details = readMessage(inputStream);

            inputStream.close();

//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//            String line = "";
//            StringBuffer sb = new StringBuffer();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//            Log.i(TAG, "doInBackground: " + sb.toString());
//
//            Gson gson = new Gson();
//            details = gson.fromJson(sb.toString().trim(), BoardDetailDTO.class);

        } catch (Exception e) {
            Log.d("main:boarddetailselect", e.getMessage());
            e.printStackTrace();
        } finally {
            if (httpEntity != null) {
                httpEntity = null;
            }
            if (httpResponse != null) {
                httpResponse = null;
            }
            if (httpPost != null) {
                httpPost = null;
            }
            if (httpClient != null) {
                httpClient = null;
            }

        }
        Log.d(TAG, "doInBackground: 종료");
        return details;
    }

    @Override
    protected void onPostExecute(BoardDetailDTO boardDetailDTO) {

    }

    public BoardDetailDTO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        String board_subject = "", board_title = "", board_content = "", member_id2 = "", petname = "", board_date = ""
                , board_imagepath = "", board_city = "", board_region = "", petimagepath = "", board_num2 = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("board_subject")) {
                board_subject = reader.nextString();
            } else if (readStr.equals("board_title")) {
                board_title = reader.nextString();
            } else if (readStr.equals("board_content")) {
                board_content = reader.nextString();
            } else if (readStr.equals("member_id2")) {
                member_id2 = reader.nextString();
            } else if (readStr.equals("petname")) {
                petname = reader.nextString();
            } else if (readStr.equals("board_date")) {
                board_date = reader.nextString();
            } else if (readStr.equals("board_imagepath")) {
                board_imagepath = reader.nextString();
            } else if (readStr.equals("board_city")) {
                board_city = reader.nextString();
            } else if (readStr.equals("board_region")) {
                board_region = reader.nextString();
            } else if (readStr.equals("petimagepath")) {
                petimagepath = reader.nextString();
            } else if (readStr.equals("board_num2")) {
                board_num2 = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("main:loginselect : ", board_num2 + "," + board_subject + "," + board_title +
                "," + board_content + "," + member_id2 + "," + petname + "," + board_date +
                "," + board_imagepath + "," + board_city + "," + board_region + "," + petimagepath);
        return new BoardDetailDTO(board_num2, board_subject, board_title, board_content,
                member_id2, petname, board_date, board_imagepath, board_city, board_region, petimagepath);

    }
}