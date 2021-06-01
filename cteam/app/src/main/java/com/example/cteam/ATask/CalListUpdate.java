package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.nio.charset.Charset;

import static com.example.cteam.Common.CommonMethod.ipConfig;

public class CalListUpdate extends AsyncTask<Void, Void, Void> {
    String calendar_date;
    String calendar_icon;
    String calendar_memo;
    String calendar_hour;
    String calendar_minute;
    String calendar_id;

    public CalListUpdate(String calendar_date, String calendar_icon, String calendar_memo,String calendar_hour,String calendar_minute,String calendar_id ) {
        this.calendar_date = calendar_date;
        this.calendar_icon = calendar_icon;
        this.calendar_memo = calendar_memo;
        this.calendar_hour = calendar_hour;
        this.calendar_minute = calendar_minute;
        this.calendar_id=calendar_id;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            String postURL = "";
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            builder.addTextBody("calendar_date", calendar_date, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("calendar_icon", calendar_icon, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("calendar_memo", calendar_memo, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("calendar_hour", calendar_hour, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("calendar_minute", calendar_minute, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("calendar_id", calendar_id, ContentType.create("Multipart/related", "UTF-8"));

            postURL = ipConfig + "/app/calUpdate";

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

