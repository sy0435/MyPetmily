package com.example.cteam.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.CommentDelete;
import com.example.cteam.CustomDialog;
import com.example.cteam.Dto.CommentDTO;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.Login;
import com.example.cteam.PetAdd;
import com.example.cteam.R;
import com.example.cteam.board.BoardDetail;

import java.util.ArrayList;

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.board.BoardDetail.commentDTO;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ItemViewHolder> {
    Context context;
    ArrayList<CommentDTO> commentList;
    Button btnCommentUpdate, btnCommentDelete;

    public CommentAdapter(Context context, ArrayList<CommentDTO> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.comment_list,parent,false);

        btnCommentUpdate = itemView.findViewById(R.id.CommentUpdate);
        btnCommentDelete = itemView.findViewById(R.id.CommentDelete);

        return new CommentAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ItemViewHolder holder, int position) {
        CommentDTO dto = commentList.get(position);
       // commentDTO = commentList.get(position);

        if(dto.getMember_id().equals(loginDTO.getMember_id())){
            btnCommentUpdate.setVisibility(View.VISIBLE);
            btnCommentDelete.setVisibility(View.VISIBLE);

        } else if(!dto.getMember_id().equals(loginDTO.getMember_id()) && !loginDTO.getMember_id().equals("admin")) {
            btnCommentUpdate.setVisibility(View.GONE);
            btnCommentDelete.setVisibility(View.GONE);
        } else if(!dto.getMember_id().equals(loginDTO.getMember_id()) && loginDTO.getMember_id().equals("admin")) {
            btnCommentUpdate.setVisibility(View.GONE);
            btnCommentDelete.setVisibility(View.VISIBLE);
        }

        holder.setComment(dto);
        holder.CommentUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentDTO = commentList.get(position);
                CustomDialog customDialog = new CustomDialog(context);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(holder.TVcomment);
            }
        });

        holder.CommentDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(context);
                builder.setTitle(Html.fromHtml("<font color='#333333'>삭제 확인</font>"));
                builder.setMessage(Html.fromHtml("<font color='#333333'>정말 댓글을 삭제하시겠습니까? \n 삭제하시려면 확인 버튼을 눌러주세요</font>"));
                //builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setIconAttribute(android.R.attr.alertDialogIcon);

                builder.setPositiveButton(Html.fromHtml("<font color='#333333'>확인</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String comment_num=dto.getComment_num();
                        CommentDelete commentDelete = new CommentDelete(comment_num);
                        commentDelete.execute();

                        commentList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, commentList.size());
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
        commentList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public CommentDTO dto(int position) {
        return commentList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, CommentDTO dto){
        commentList.set(position, dto);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<CommentDTO> commentList){
        this.commentList = commentList;
    }
    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout CommentParentLayout;
        public TextView comment_id, comment_date, TVcomment;
        public EditText ETcomment;
        public Button CommentUpdate, CommentDelete;
        public ImageView comment_writer_img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            CommentParentLayout = itemView.findViewById(R.id.CommentParentLayout);
            comment_id = itemView.findViewById(R.id.comment_id);
            comment_date = itemView.findViewById(R.id.comment_date);
            TVcomment = itemView.findViewById(R.id.TVcomment);
            CommentUpdate = itemView.findViewById(R.id.CommentUpdate);
            CommentDelete = itemView.findViewById(R.id.CommentDelete);
            comment_writer_img = itemView.findViewById(R.id.comment_writer_img);

        }

        public void setComment(CommentDTO dto){
            comment_id.setText(dto.getMember_id());
            comment_date.setText(dto.getWritedate());
            TVcomment.setText(dto.getContent());

            Glide.with(itemView).load(dto.getWriter_image()).into(comment_writer_img);
        }
    }

}

