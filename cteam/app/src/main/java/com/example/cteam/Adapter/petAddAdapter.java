package com.example.cteam.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.cteam.CalendarAdd.selectIcon;

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.CommentDelete;
import com.example.cteam.ATask.ListDelete;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.Login;
import com.example.cteam.PetAdd;
import com.example.cteam.PetSelect;
import com.example.cteam.R;
import com.example.cteam.pet_add.petInsert;
import com.example.cteam.pet_add.petUpdate;

import java.util.ArrayList;


public class petAddAdapter extends RecyclerView.Adapter<petAddAdapter.ItemViewHolder> {
    Context context;
    ArrayList<PetDTO> petList;

    public petAddAdapter(Context context, ArrayList<PetDTO> petList) {
        this.context = context;
        this.petList = petList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View itemView = inflater.inflate(R.layout.petlistviewcard,parent,false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        PetDTO dto = petList.get(position);
        holder.setPet(dto);

        holder.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialog dialog = new AppCompatDialog(context);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.progress);
                dialog.show();
                final ImageView cteam_progress = (ImageView) dialog.findViewById(R.id.cteam_progress);
                final AnimationDrawable frameAnimation = (AnimationDrawable) cteam_progress.getBackground();
                cteam_progress.post(new Runnable() {
                    @Override
                    public void run() {
                        frameAnimation.start();
                    }
                });
                petAddDto = petList.get(position);
                Log.d("main:petaddadapter", "onClick: " + petAddDto.getPetname());
                Intent intent = new Intent(context, PetSelect.class);
                context.startActivity(intent);
            }
        });


        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petAddDto = petList.get(position);
                Log.d("main:petaddadapter", "onClick: " + petAddDto.getPetname());
                Intent intent = new Intent(context, petUpdate.class);
                context.startActivity(intent);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(context);
                builder.setTitle(Html.fromHtml("<font color='#333333'>안내</font>"));
                builder.setMessage(Html.fromHtml("<font color='#333333'>정말로 "+dto.getPetname()+" 의 정보를 삭제하시겠습니까?</font>"));
                builder.setIconAttribute(android.R.attr.alertDialogIcon);

                builder.setPositiveButton(Html.fromHtml("<font color='#333333'>확인</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dto != null) {
                            String member_id = loginDTO.getMember_id();
                            String petname = dto.getPetname();
                            ListDelete listDelete = new ListDelete(member_id, petname);
                            listDelete.execute();

                            Intent refresh = new Intent(context, PetAdd.class);
                            context.startActivity(refresh);
                        }else {
                            Log.d("TAG", "onClick: "+dto.getPetname());
                        }
                    }
                });
                builder.setNegativeButton(Html.fromHtml("<font color='#333333'>취소</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        petList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public PetDTO dto(int position) {
        return petList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, PetDTO dto){
        petList.set(position, dto);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<PetDTO> petList){
        this.petList = petList;
    }
    @Override
    public int getItemCount() {
        return petList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout parentLayout;
        public TextView tvName, tvWeight, tvGender, tvAge;
        public ImageView imageView;
        public Button selectBtn, updateBtn, deleteBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            tvName = itemView.findViewById(R.id.TVname);
            tvWeight = itemView.findViewById(R.id.TVweight);
            tvGender = itemView.findViewById(R.id.TVgender);
            tvAge = itemView.findViewById(R.id.TVage);
            imageView = itemView.findViewById(R.id.imageView);
            selectBtn = itemView.findViewById(R.id.selectBtn);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }

        public void setPet(PetDTO dto){
            tvName.setText(dto.getPetname());
            tvGender.setText(dto.getPetgender());
            tvWeight.setText(dto.getPetweight()+" Kg");
            tvAge.setText(dto.getPetage() + " 세");

            Glide.with(itemView).load(dto.getPetimage_path()).into(imageView);
        }
    }
    private void showMessage() {
/*        AlertDialog.Builder builder =
                new AlertDialog.Builder(context);
        builder.setTitle(Html.fromHtml("<font color='#333333'>안내</font>"));
        builder.setMessage(Html.fromHtml("<font color='#333333'>비밀번호를 다시 입력하세요!</font>"));
        //builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setIconAttribute(android.R.attr.alertDialogIcon);

        builder.setPositiveButton(Html.fromHtml("<font color='#333333'>확인</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dto != null) {
                    String member_id = loginDTO.getMember_id();
                    String petname = dto.getPetname();
                    ListDelete listDelete = new ListDelete(member_id, petname);
                    listDelete.execute();

                    Intent refresh = new Intent(context, PetAdd.class);
                    context.startActivity(refresh);
                }else {
                    Log.d("TAG", "onClick: "+dto.getPetname());
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();*/
    }
    }
