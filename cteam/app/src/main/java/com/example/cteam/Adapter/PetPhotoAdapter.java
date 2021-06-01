package com.example.cteam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.PetPhoto.PetPhotoDelete;
import com.example.cteam.Dto.PetPhotoDTO;
import com.example.cteam.PetPhotoUpdate;
import com.example.cteam.R;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.circleCrop;
import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;
import static com.example.cteam.PetPhoto.selectPetPhoto;


public class PetPhotoAdapter extends RecyclerView.Adapter<PetPhotoAdapter.ItemViewHolder>{
    private static final String TAG = "MyRecyclerviewAdapter";

    Context mContext;
    ArrayList<PetPhotoDTO> petPhotos;

    public PetPhotoAdapter(Context mContext, ArrayList<PetPhotoDTO> petPhotos) {
        this.mContext = mContext;
        this.petPhotos = petPhotos;
    }
    public PetPhotoAdapter(){};

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.petphotoitem_view, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d("main:adater", "" + position);

        PetPhotoDTO petPhoto = petPhotos.get(position);
        holder.setItem(petPhoto,mContext);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position);

                selectPetPhoto = petPhotos.get(position);

              //  Toast.makeText(mContext, "Onclick " + arrayList.get(position).getId(), Toast.LENGTH_SHORT).show();

            }

        });


        holder.spinnerOfferType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item = holder.spinnerOfferType.getSelectedItem().toString();

                if(item.equals("delete")){

                    Log.d(TAG, "onItemSelected: 눌린거"+petPhoto.getPetPhoto_no());
                    PetPhotoDelete petPhotoDelete;
                   petPhotoDelete=new PetPhotoDelete(petPhoto.getPetPhoto_no(),petPhotos);
                    try {
                        petPhotoDelete.execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    petPhotos.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, petPhotos.size());



                }else if(item.equals("edit")){

                    Intent intent=new Intent(mContext,PetPhotoUpdate.class);
                    intent.putExtra("petPhoto", petPhoto);
                    mContext.startActivity(intent);

                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return petPhotos.size();
    }


    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        petPhotos.clear();
    }

    // 특정 인덱스 항목 가져오기
    public PetPhotoDTO getItem(int position) {
        return petPhotos.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, PetPhotoDTO petPhoto){
        petPhotos.set(position, petPhoto);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<PetPhotoDTO> petPhotos){
        this.petPhotos = petPhotos;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout parentLayout;
        public TextView petPhoto_petName;
        public ImageView petPhoto_profile;
        public TextView petPhoto_date;
        public ImageView petPhoto_image;
        public TextView petPhoto_content;
        public ProgressBar progressBar;
        public Spinner spinnerOfferType;


        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            petPhoto_petName = itemView.findViewById(R.id.petPhoto_petName);
            petPhoto_date = itemView.findViewById(R.id.petPhoto_date);
            petPhoto_profile = itemView.findViewById(R.id.petPhoto_profile);
            petPhoto_image = itemView.findViewById(R.id.petPhoto_image);
            petPhoto_content=itemView.findViewById(R.id.petPhoto_content);
            progressBar = itemView.findViewById(R.id.progressBar);
            spinnerOfferType = (Spinner)itemView.findViewById(R.id.spinnerOfferType);
        }

        public void setItem(PetPhotoDTO petPhoto,Context mContext){
            if(petPhoto!=null){
                Log.d("yyyy", "setItem: d"+petPhoto.getPetPhoto_content());
            }
           // id.setText(item.getId());
            petPhoto_petName.setText(petPhoto.getPetName());
           // petPhoto_petName.setText(petAddDto.getPetname());
            petPhoto_date.setText(petPhoto.getPetPhoto_date());
            petPhoto_content.setText(petPhoto.getPetPhoto_content()
            );


            Glide.with(itemView).load(petAddDto.getPetimage_path()).circleCrop().into(petPhoto_profile);
            Glide.with(itemView).load(petPhoto.getPetPhoto_imgpath()).into(petPhoto_image);


            //스피너
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                    R.array.offer_types, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerOfferType.setAdapter(adapter);



        }
    }

}



