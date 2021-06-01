package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.cteam.Adapter.BoardAdapter;
import com.example.cteam.Dto.BoardDTO;

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

import static com.example.cteam.Common.CommonMethod.ipConfig;

public class MyPostingSelect extends AsyncTask<Void, Void, String> {
    // 생성자
    public static ArrayList<BoardDTO> walkboardArrayList;
    com.example.cteam.Adapter.BoardAdapter BoardAdapter;
    String member_id;

    public MyPostingSelect(ArrayList<BoardDTO> walkboardArrayList, BoardAdapter BoardAdapter, String member_id) {
        this.walkboardArrayList = walkboardArrayList;
        this.BoardAdapter = BoardAdapter;
        this.member_id = member_id;
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
    protected String doInBackground(Void... voids) {
        walkboardArrayList.clear();
        String result = "";
        String postURL = ipConfig + "/app/myPostingSelect";

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            builder.addTextBody("member_id", member_id, ContentType.create("Multipart/related", "UTF-8"));

            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("cteam");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);
            /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            String jsonStr = stringBuilder.toString();

            inputStream.close();*/

        } catch (Exception e) {
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

        return "Work Complete !!!";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        BoardAdapter.notifyDataSetChanged();
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                walkboardArrayList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public BoardDTO readMessage(JsonReader reader) throws IOException {
        String id = "",subject = "", title = "", city = "", region = "", date = "",comment="";
        String num = "";


        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("comment")) {
                comment = reader.nextString();
            } else if (readStr.equals("id")) {
                id = reader.nextString();
            } else if (readStr.equals("subject")) {
                subject = reader.nextString();
            } else if (readStr.equals("title")) {
                title = reader.nextString();
            } else if (readStr.equals("city")) {
                city = reader.nextString();
            } else if (readStr.equals("region")) {
                region = reader.nextString();
            } else if (readStr.equals("date")) {
                date = reader.nextString();
            }else if (readStr.equals("num")) {
                num = reader.nextString();

            }
            else {
                reader.skipValue();
            }
        }

        String city2=city+""+region;
        reader.endObject();
        return new BoardDTO(id, subject, title, city, region, date, num);

    }

    /*public List<Double> readDoublesArray(JsonReader reader) throws IOException {
        List<Double> doubles = new ArrayList<Double>();

        reader.beginArray();
        while (reader.hasNext()) {
            doubles.add(reader.nextDouble());
        }
        reader.endArray();
        return doubles;
    }

    public User readUser(JsonReader reader) throws IOException {
        String username = null;
        int followersCount = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                username = reader.nextString();
            } else if (name.equals("followers_count")) {
                followersCount = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new User(username, followersCount);
    }*/

}
