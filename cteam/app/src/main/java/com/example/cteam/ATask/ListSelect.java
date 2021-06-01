package com.example.cteam.ATask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.example.cteam.Adapter.petAddAdapter;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.Login;
import com.example.cteam.PetAdd;

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

public class ListSelect extends AsyncTask<Void, Void, Void> {
    String member_id;
    ArrayList<PetDTO> petList;
    petAddAdapter adapter;


    public ListSelect(String member_id, ArrayList<PetDTO> petList, petAddAdapter adapter) {
        this.member_id = member_id;
        this.petList = petList;
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
        petList.clear();
        String result = "";
        String postURL = CommonMethod.ipConfig + "/app/cPetSelect";

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

/*        if(progressDialog != null){
            progressDialog.dismiss();
        }

        Log.d("PetAdd", "petList Select Complete!!!");  */

        //adapter.notifyDataSetChanged();
        if (petList.size() > 0) {    //데이타가 추가, 수정되었을때

            adapter.notifyDataSetChanged();

        } else {    //뷰에 표시될 데이타가 없을때



        }

    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                petList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public PetDTO readMessage(JsonReader reader) throws IOException {
        String petname = "", petage = "", petweight = "", petgender = "", petimage_path = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("petname")) {
                petname = reader.nextString();
            } else if (readStr.equals("petage")) {
                petage = reader.nextString();
            } else if (readStr.equals("petweight")) {
                petweight = reader.nextString();
            } else if (readStr.equals("petgender")) {
                petgender = reader.nextString();
            } else if (readStr.equals("petimagepath")) {
                petimage_path = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("listselect:myitem", petname + "," + petage + "," + petweight + "," + petgender);

        return new PetDTO(petname, petage, petweight, petgender, petimage_path);

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
