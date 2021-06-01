package com.example.cteam.ATask.Board;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.Board.BoardDelete;
import com.example.cteam.ATask.Board.BoardDetailSelect;


import com.example.cteam.ATask.CommentInsert;
import com.example.cteam.ATask.CommentSelect;
import com.example.cteam.ATask.ListDelete;
import com.example.cteam.ATask.Listinsert;
import com.example.cteam.Adapter.CommentAdapter;
import com.example.cteam.Common.CommonMethod;

import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.Dto.BoardDetailDTO;
import com.example.cteam.Dto.CommentDTO;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.Login;
import com.example.cteam.MemberDTO;
import com.example.cteam.PetSelect;
import com.example.cteam.R;
import com.example.cteam.board.BoardUpdate;
import com.google.android.gms.maps.model.Circle;



import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;
import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;

public class BoardDetail extends AppCompatActivity {

    private static final String TAG = "BoardDetail";
    public static CommentDTO commentDTO = null;
    public static BoardDetailDTO boardDetailDTO = null;

    TextView board_detail_title, board_detail_id, board_detail_date,
            board_detail_content, board_detail_city, board_detail_region
            , board_detail_modify, board_detail_delete;
    CircleImageView board_detail_writer_img, board_detail_comment_img;
    EditText board_detail_comment_write;
    Button board_detail_comment_submit;
    RecyclerView board_detail_comment;
    ImageView board_detail_image;

    String member_id, board_num,content="";
    public String imageRealPathA, writer_image;

    ArrayList<CommentDTO> commentList;

    RecyclerView recyclerView;
    CommentAdapter adapter;
    CommentSelect commentSelect;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        board_detail_title = findViewById(R.id.board_detail_title);
        board_detail_id = findViewById(R.id.board_detail_id);
        board_detail_date = findViewById(R.id.board_detail_date);
        board_detail_content = findViewById(R.id.board_detail_content);
        board_detail_writer_img = findViewById(R.id.board_detail_writer_img);
        board_detail_comment_img = findViewById(R.id.board_detail_comment_img);
        board_detail_comment_write = findViewById(R.id.board_detail_comment_write);
        board_detail_comment_submit = findViewById(R.id.board_detail_comment_submit);
        board_detail_comment = findViewById(R.id.board_detail_comment);
        board_detail_city = findViewById(R.id.board_detail_city);
        board_detail_region = findViewById(R.id.board_detail_region);
        board_detail_image = findViewById(R.id.board_detail_image);
        board_detail_modify = findViewById(R.id.board_detail_modify);
        board_detail_delete = findViewById(R.id.board_detail_delete);

        commentList = new ArrayList<>();
        adapter = new CommentAdapter(this, commentList);
        recyclerView = findViewById(R.id.board_detail_comment);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        board_detail_comment_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSubmitClicked();
            }
        });

        if (getIntent() != null) {
            Intent intent = getIntent();
            String num = intent.getStringExtra("num");
            String member_id = intent.getStringExtra("member_id");
            Log.i(TAG, "num: " + num);
            Log.i(TAG, "member_id: " + member_id);
            BoardDetailSelect boardDetailSelect = new BoardDetailSelect(num, member_id);
            boardDetailSelect.execute();
            try {
                boardDetailDTO = boardDetailSelect.get();
                Log.i(TAG, "dto " + boardDetailDTO.getBoard_title());
            }catch(Exception e) {
                e.getMessage();
            }

        }



        //여기에 셀렉트
        commentSelect = new CommentSelect(boardDetailDTO.getboard_num2(), commentList, adapter);
        try {
            commentSelect.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //삭제
        board_detail_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage();

//            AlertDialog.Builder builder =
//                    new AlertDialog.Builder(getApplicationContext());
//            builder.setTitle("안내");
//            builder.setMessage("정말 삭제 하시겠습니까?");
//            builder.setIcon(android.R.drawable.ic_dialog_alert);
//            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {

//                }
//            });

//            builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//                }
//            });
//            AlertDialog dialog = builder.create();
//            dialog.show();


            }
        });

    }
    @Override
    public void onStart(){
        super.onStart();

        board_detail_title.setText(boardDetailDTO.getBoard_title());
        board_detail_id.setText(boardDetailDTO.getmember_id2());
        board_detail_date.setText(boardDetailDTO.getBoard_date());
        board_detail_content.setText(boardDetailDTO.getBoard_content());
        board_detail_city.setText(boardDetailDTO.getBoard_city());
        board_detail_region.setText(boardDetailDTO.getBoard_region());

        Glide.with(this).load(boardDetailDTO.getBoard_imagepath()).into(board_detail_image);
        Glide.with(this).load(boardDetailDTO.getPetimagepath()).circleCrop().into(board_detail_writer_img);
        Glide.with(this).load(petAddDto.getPetimage_path()).circleCrop().into(board_detail_comment_img);

        if( !boardDetailDTO.getmember_id2().equals(loginDTO.getMember_id()) ) {
            board_detail_modify.setVisibility(View.GONE);
            board_detail_delete.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResume(){
        super.onResume();

        board_detail_title.setText(boardDetailDTO.getBoard_title());
        board_detail_id.setText(boardDetailDTO.getmember_id2());
        board_detail_date.setText(boardDetailDTO.getBoard_date());
        board_detail_content.setText(boardDetailDTO.getBoard_content());
        board_detail_city.setText(boardDetailDTO.getBoard_city());
        board_detail_region.setText(boardDetailDTO.getBoard_region());

        Glide.with(this).load(boardDetailDTO.getBoard_imagepath()).into(board_detail_image);
        Glide.with(this).load(boardDetailDTO.getPetimagepath()).circleCrop().into(board_detail_writer_img);
        Glide.with(this).load(petAddDto.getPetimage_path()).circleCrop().into(board_detail_comment_img);

    }


    public void btnSubmitClicked() {
        member_id= loginDTO.getMember_id();
        board_num = boardDetailDTO.getboard_num2();
        content = board_detail_comment_write.getText().toString();
        writer_image = petAddDto.getPetimage_path();

        CommentInsert commentInsert = new CommentInsert(member_id, board_num, content, writer_image, imageRealPathA);
        commentInsert.execute();

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    public void board_detail_modify(View v){
        if(isNetworkConnected(this) == true){
            if(boardDetailDTO != null){
                Log.d("update버튼 클릭", boardDetailDTO.getboard_num2());

                Intent intent = new Intent(this, BoardUpdate.class);
                intent.putExtra("boardDetailDTO", boardDetailDTO);
                startActivity(intent);

            }else {
                Toast.makeText(getApplicationContext(), "항목 선택을 해 주세요",
                        Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }
    private void showMessage() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (isNetworkConnected(getApplicationContext()) == true) {
                    if (boardDetailDTO != null) {
                        Log.d(TAG, boardDetailDTO.getBoard_imagepath());

                        BoardDelete boarddelete = new BoardDelete(boardDetailDTO.getboard_num2(), boardDetailDTO.getBoard_imagepath());
                        boarddelete.execute();

                        Log.d(TAG + "delete실행", boardDetailDTO.getBoard_imagepath());


                        Intent showIntent = new Intent(getApplicationContext(), PetSelect.class);
                        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        showIntent.putExtra("fragment", "BoardDetail");
                        startActivity(showIntent);
                        finish();

                        adapter.notifyDataSetChanged(); // adapter 갱신
                    } else {
                        Toast.makeText(getApplicationContext(), "항목 선택을 해 주세요(항목선택)",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BoardDetail.this, "인터넷이 연결되어 있지 않습니다.",
                            Toast.LENGTH_SHORT).show(); // 테스트 111
                }
            }
        });
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    // 삭제
//    public void board_detail_delete(View v) {
//
//    }

}