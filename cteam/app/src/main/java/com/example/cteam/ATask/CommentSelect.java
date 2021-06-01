package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.cteam.Adapter.CommentAdapter;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Dto.CommentDTO;
import com.example.cteam.Dto.PetDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommentSelect extends AsyncTask<Void, Void, Void> {
    String board_num;
    CommentAdapter adapter;
    ArrayList<CommentDTO> commentList;

    public CommentSelect(String board_num, ArrayList<CommentDTO> commentList, CommentAdapter adapter){
        this.board_num = board_num;
        this.commentList = commentList;
        this.adapter = adapter;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String result = "";
        String postURL = CommonMethod.ipConfig + "/app/cCommentSelect";

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);


            builder.addTextBody("board_num", board_num, ContentType.create("Multipart/related", "UTF-8"));

            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("cteam");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);


        } catch (Exception e) {
            Log.d("petAdd", e.getMessage());
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

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (commentList.size() > 0) {    //데이타가 추가, 수정되었을때

            adapter.notifyDataSetChanged();

        } else {    //뷰에 표시될 데이타가 없을때



        }

    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                commentList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public CommentDTO readMessage(JsonReader reader) throws IOException {
        String member_id = "", board_num = "", content = "", writedate = "", writer_image = "",comment_num="";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("member_id")) {
                member_id = reader.nextString();
            } else if (readStr.equals("board_num")) {
                board_num = reader.nextString();
            } else if (readStr.equals("content")) {
                content = reader.nextString();
            } else if (readStr.equals("writedate")) {
                writedate = reader.nextString();
            } else if (readStr.equals("writer_image")) {
                writer_image = reader.nextString();
            }else if (readStr.equals("comment_num")) {
                comment_num = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("CommentSelect:myitem", member_id + "," + board_num + "," + content + "," + writedate + "," + writer_image + ","+ comment_num);

        return new CommentDTO(member_id, board_num, content, writedate, writer_image, comment_num);

    }
}
