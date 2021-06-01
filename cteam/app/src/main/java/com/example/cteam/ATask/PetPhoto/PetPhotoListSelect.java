package com.example.cteam.ATask.PetPhoto;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.example.cteam.Adapter.PetPhotoAdapter;
import com.example.cteam.Adapter.petAddAdapter;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.Dto.PetPhotoDTO;
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

public class PetPhotoListSelect extends AsyncTask<Void, Void, Void> {
    ArrayList<PetPhotoDTO> petPhotos;
    PetPhotoAdapter petPhotoAdapter;
    String member_id;
    String pet_name;



    public PetPhotoListSelect(ArrayList<PetPhotoDTO> petPhotos, PetPhotoAdapter petPhotoAdapter,String pet_name,String member_id) {
        this.petPhotos = petPhotos;
        this.petPhotoAdapter = petPhotoAdapter;
        this.pet_name = pet_name;
        this.member_id= member_id;
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
        petPhotos.clear();
        String result = "";
        String postURL = CommonMethod.ipConfig + "/app/cPetPhotoSelect";

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);




            builder.addTextBody("petName", pet_name, ContentType.create("Multipart/related", "UTF-8"));
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
            Log.d("petphoto", e.getMessage());
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
        if (petPhotos.size() > 0) {    //데이타가 추가, 수정되었을때

            petPhotoAdapter.notifyDataSetChanged();

        } else {    //뷰에 표시될 데이타가 없을때



        }

    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                petPhotos.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public PetPhotoDTO readMessage(JsonReader reader) throws IOException {
        String petName = "",petPhoto_imgpath = "",petPhoto_content = "", petPhoto_date = "",petPhoto_no = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();


            if (readStr.equals("petName")) {
                petName = reader.nextString();
            } else if (readStr.equals("petPhoto_imgpath")) {
                petPhoto_imgpath = reader.nextString();
            } else if (readStr.equals("petPhoto_content")) {
                petPhoto_content = reader.nextString();
            } else if (readStr.equals("petPhoto_date")) {
                petPhoto_date = reader.nextString();
            } else if(readStr.equals("petPhoto_no")){
                petPhoto_no=reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        
      //  Log.d("listselect:myitem", petName + "," + petPhoto_imgpath + "," + petPhoto_content + "," + petPhoto_date);

        Log.d("listselect:myitem", petName);

        return new PetPhotoDTO(petPhoto_imgpath, petPhoto_content, petPhoto_date, petName, petPhoto_no);

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
