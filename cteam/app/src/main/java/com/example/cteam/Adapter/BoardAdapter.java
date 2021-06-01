package com.example.cteam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.Login;
import com.example.cteam.R;
import com.example.cteam.board.BoardDetail;
import static com.example.cteam.Login.loginDTO;
import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private static final String TAG = "BoardAdapter";
    Context mcontext;
    ArrayList<BoardDTO> list;
    BoardDTO dto;

    public BoardAdapter(Context mcontext, ArrayList<BoardDTO> list) {
        this.mcontext = mcontext;
        this.list = list;

    }

    public BoardAdapter(ArrayList<BoardDTO> list) {
        this.list = list;
    }

    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.board_list_item, parent, false);
        BoardViewHolder holder = new BoardViewHolder(itemView);
        return holder;
//        return new BoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.board_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder,final int position) {
        dto = list.get(position);
        String member_id = loginDTO.getMember_id();
        String num = dto.getNum();
        Log.d(TAG, "onClick: member_id = "+member_id);
        Log.d(TAG, "onClick: num = "+num);
        holder.setBoard(dto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BoardViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout parentLayout;
        public TextView msubject;
        public TextView mtitle;
        public TextView mid;
        public TextView mcity;
        public TextView mdate;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);

            //클릭했을때 Detail로 변경
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.d(TAG, "onClick: position" + position);
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(mcontext, BoardDetail.class);
                        String member_id = loginDTO.getMember_id();
                        String num = list.get(position).getNum();
                        intent.putExtra("member_id", member_id);
                        intent.putExtra("num", num);
                        Log.d(TAG, "onClick: member_" + member_id);
                        Log.d(TAG, "onClick: num" + num);
                        mcontext.startActivity(intent);
                    }
                }
            });

            parentLayout = itemView.findViewById(R.id.parentLayout);
            msubject = itemView.findViewById(R.id.msubject);
            mtitle = itemView.findViewById(R.id.mtitle);
            mid = itemView.findViewById(R.id.mid);
            mcity = itemView.findViewById(R.id.mcity);
            mdate = itemView.findViewById(R.id.mdate);
        }

        public void setBoard(BoardDTO dto) {
            msubject.setText(dto.getSubject());
            mtitle.setText(dto.getTitle());
            mid.setText(dto.getId());
            if(dto.getCity().equals("서울특별시")) {
                mcity.setText("서울 " + dto.getRegion());
            } else if(dto.getCity().equals("부산광역시")) {
                mcity.setText("부산 " + dto.getRegion());
            } else if(dto.getCity().equals("대구광역시")) {
                mcity.setText("대구 " + dto.getRegion());
            } else if(dto.getCity().equals("인천광역시")) {
                mcity.setText("인천 " + dto.getRegion());
            } else if(dto.getCity().equals("광주광역시")) {
                mcity.setText("광주 " + dto.getRegion());
            } else if(dto.getCity().equals("대전광역시")) {
                mcity.setText("대전 " + dto.getRegion());
            } else if(dto.getCity().equals("울산광역시")) {
                mcity.setText("울산 " + dto.getRegion());
            } else if(dto.getCity().equals("세종특별자치시")) {
                mcity.setText("세종 " + dto.getRegion());
            } else if(dto.getCity().equals("경기도")) {
                mcity.setText("경기 " + dto.getRegion());
            } else if(dto.getCity().equals("강원도")) {
                mcity.setText("강원 " + dto.getRegion());
            } else if(dto.getCity().equals("충청북도")) {
                mcity.setText("충북 " + dto.getRegion());
            } else if(dto.getCity().equals("충청남도")) {
                mcity.setText("충남 " + dto.getRegion());
            } else if(dto.getCity().equals("전라북도")) {
                mcity.setText("전북 " + dto.getRegion());
            } else if(dto.getCity().equals("전라남도")) {
                mcity.setText("전남 " + dto.getRegion());
            } else if(dto.getCity().equals("경상북도")) {
                mcity.setText("경북 " + dto.getRegion());
            } else if(dto.getCity().equals("경상남도")) {
                mcity.setText("경남 " + dto.getRegion());
            } else if(dto.getCity().equals("제주특별자치도")) {
                mcity.setText("제주 " + dto.getRegion());
            }
            //mcity.setText(dto.getCity()+ " " +dto.getRegion());
            mdate.setText(dto.getDate());
        }

    }

}
