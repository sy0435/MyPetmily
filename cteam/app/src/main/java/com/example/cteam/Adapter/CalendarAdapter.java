package com.example.cteam.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.Dto.CalendarDTO;
import com.example.cteam.R;

import java.util.ArrayList;

import static com.example.cteam.CalendarAdd.selectIcon;




public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.IconViewHolder> {
    //로그 찍어볼 때
    private static final String TAG = "calendarAdapter";

    private int selectedPosition = -1;

    Context context;
    ArrayList<CalendarDTO> icons;

    public CalendarAdapter(Context context, ArrayList<CalendarDTO> icons) {
        this.context = context;
        this.icons = icons;
        selectIcon=null;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View iconView = inflater.inflate(R.layout.fragment_calendar_add_view, parent, false);

        return new IconViewHolder(iconView);

    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        CalendarDTO icon = icons.get(position);
        holder.setIcon(icon);



        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position);

                selectIcon = icons.get(position);

                Log.d(TAG, "onClick: " + selectIcon.getCalendar_memo());
               // Toast.makeText(context, "Onclick " + icons.get(position).getCalendar_memo(), Toast.LENGTH_SHORT).show();
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });

        // 선택 항목 강조
        if(selectedPosition == position) {
            holder.parentLayout.setBackgroundResource(R.drawable.cardpressed);
        } else {
            holder.parentLayout.setBackgroundResource(R.drawable.cardunpressed);
        }


//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 선택 항목 강조
//                selectedPosition = position;
//                notifyDataSetChanged();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return icons.size();
    }

    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        icons.clear();
    }

    // 특정 인덱스 항목 가져오기
    public CalendarDTO getIcon(int position) {
        return icons.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setIcon(int position, CalendarDTO icon){
        icons.set(position, icon);
    }

    // arrayList 통째로 셋팅하기
    public void setIcon(ArrayList<CalendarDTO> icons){
        this.icons = icons;
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout parentLayout;
        public ImageView c_icon;
        public TextView c_memo;
        public TextView c_time;

        public IconViewHolder(@NonNull final View iconView) {
            super(iconView);

            parentLayout = iconView.findViewById(R.id.parentLayout);
            c_icon = iconView.findViewById(R.id.CalendarAdd_icon);
            c_memo = iconView.findViewById(R.id.CalendarAdd_memo);
            c_time = iconView.findViewById(R.id.CalendarAdd_time);

       /*     parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentLayout.setBackgroundre(R.drawable.cardpressed);
                }
            });*/


        }

        public void setIcon(CalendarDTO icon) {

            if(icon.getCalendar_icon().equals("11")) {
                c_icon.setImageResource(R.drawable.icon11);
            } else if (icon.getCalendar_icon().equals("12")) {
                c_icon.setImageResource(R.drawable.icon12);
            } else if (icon.getCalendar_icon().equals("13")) {
                c_icon.setImageResource(R.drawable.icon13);
            } else if (icon.getCalendar_icon().equals("14")) {
                c_icon.setImageResource(R.drawable.icon14);
            } else if (icon.getCalendar_icon().equals("15")) {
                c_icon.setImageResource(R.drawable.icon15);
            } else if (icon.getCalendar_icon().equals("16")) {
                c_icon.setImageResource(R.drawable.icon16);
            } else if (icon.getCalendar_icon().equals("17")) {
                c_icon.setImageResource(R.drawable.icon17);
            } else if (icon.getCalendar_icon().equals("18")) {
                c_icon.setImageResource(R.drawable.icon18);
            } else if (icon.getCalendar_icon().equals("21")) {
                c_icon.setImageResource(R.drawable.icon21);
            } else if (icon.getCalendar_icon().equals("22")) {
                c_icon.setImageResource(R.drawable.icon22);
            } else if (icon.getCalendar_icon().equals("23")) {
                c_icon.setImageResource(R.drawable.icon23);
            } else if (icon.getCalendar_icon().equals("24")) {
                c_icon.setImageResource(R.drawable.icon24);
            } else if (icon.getCalendar_icon().equals("25")) {
                c_icon.setImageResource(R.drawable.icon25);
            } else if (icon.getCalendar_icon().equals("26")) {
                c_icon.setImageResource(R.drawable.icon26);
            } else if (icon.getCalendar_icon().equals("27")) {
                c_icon.setImageResource(R.drawable.icon27);
            } else if (icon.getCalendar_icon().equals("28")) {
                c_icon.setImageResource(R.drawable.icon28);
            } else if (icon.getCalendar_icon().equals("31")) {
                c_icon.setImageResource(R.drawable.icon31);
            } else if (icon.getCalendar_icon().equals("32")) {
                c_icon.setImageResource(R.drawable.icon32);
            } else if (icon.getCalendar_icon().equals("33")) {
                c_icon.setImageResource(R.drawable.icon33);
            } else if (icon.getCalendar_icon().equals("34")) {
                c_icon.setImageResource(R.drawable.icon34);
            } else if (icon.getCalendar_icon().equals("35")) {
                c_icon.setImageResource(R.drawable.icon35);
            } else if (icon.getCalendar_icon().equals("36")) {
                c_icon.setImageResource(R.drawable.icon36);
            } else if (icon.getCalendar_icon().equals("37")) {
                c_icon.setImageResource(R.drawable.icon37);
            } else if (icon.getCalendar_icon().equals("38")) {
                c_icon.setImageResource(R.drawable.icon38);
            } else if (icon.getCalendar_icon().equals("41")) {
                c_icon.setImageResource(R.drawable.icon41);
            } else if (icon.getCalendar_icon().equals("42")) {
                c_icon.setImageResource(R.drawable.icon42);
            } else if (icon.getCalendar_icon().equals("43")) {
                c_icon.setImageResource(R.drawable.icon43);
            } else if (icon.getCalendar_icon().equals("44")) {
                c_icon.setImageResource(R.drawable.icon44);
            } else if (icon.getCalendar_icon().equals("45")) {
                c_icon.setImageResource(R.drawable.icon45);
            } else if (icon.getCalendar_icon().equals("46")) {
                c_icon.setImageResource(R.drawable.icon46);
            } else if (icon.getCalendar_icon().equals("47")) {
                c_icon.setImageResource(R.drawable.icon47);
            } else if (icon.getCalendar_icon().equals("48")) {
                c_icon.setImageResource(R.drawable.icon48);
            } else if (icon.getCalendar_icon().equals("51")) {
                c_icon.setImageResource(R.drawable.icon51);
            } else if (icon.getCalendar_icon().equals("52")) {
                c_icon.setImageResource(R.drawable.icon52);
            } else if (icon.getCalendar_icon().equals("53")) {
                c_icon.setImageResource(R.drawable.icon53);
            } else if (icon.getCalendar_icon().equals("54")) {
                c_icon.setImageResource(R.drawable.icon54);
            } else if (icon.getCalendar_icon().equals("55")) {
                c_icon.setImageResource(R.drawable.icon55);
            } else if (icon.getCalendar_icon().equals("56")) {
                c_icon.setImageResource(R.drawable.icon56);
            } else if (icon.getCalendar_icon().equals("57")) {
                c_icon.setImageResource(R.drawable.icon57);
            } else if (icon.getCalendar_icon().equals("58")) {
                c_icon.setImageResource(R.drawable.icon58);
            } else if (icon.getCalendar_icon().equals("61")) {
                c_icon.setImageResource(R.drawable.icon61);
            } else if (icon.getCalendar_icon().equals("62")) {
                c_icon.setImageResource(R.drawable.icon62);
            } else if (icon.getCalendar_icon().equals("63")) {
                c_icon.setImageResource(R.drawable.icon63);
            } else if (icon.getCalendar_icon().equals("64")) {
                c_icon.setImageResource(R.drawable.icon64);
            } else if (icon.getCalendar_icon().equals("65")) {
                c_icon.setImageResource(R.drawable.icon65);
            } else if (icon.getCalendar_icon().equals("66")) {
                c_icon.setImageResource(R.drawable.icon66);
            } else if (icon.getCalendar_icon().equals("67")) {
                c_icon.setImageResource(R.drawable.icon67);
            } else if (icon.getCalendar_icon().equals("68")) {
                c_icon.setImageResource(R.drawable.icon68);
            } else if (icon.getCalendar_icon().equals("71")) {
                c_icon.setImageResource(R.drawable.icon71);
            } else if (icon.getCalendar_icon().equals("72")) {
                c_icon.setImageResource(R.drawable.icon72);
            } else if (icon.getCalendar_icon().equals("73")) {
                c_icon.setImageResource(R.drawable.icon73);
            } else if (icon.getCalendar_icon().equals("74")) {
                c_icon.setImageResource(R.drawable.icon74);
            } else if (icon.getCalendar_icon().equals("75")) {
                c_icon.setImageResource(R.drawable.icon75);
            } else if (icon.getCalendar_icon().equals("76")) {
                c_icon.setImageResource(R.drawable.icon76);
            } else if (icon.getCalendar_icon().equals("77")) {
                c_icon.setImageResource(R.drawable.icon77);
            } else if (icon.getCalendar_icon().equals("78")) {
                c_icon.setImageResource(R.drawable.icon78);
            } else if (icon.getCalendar_icon().equals("81")) {
                c_icon.setImageResource(R.drawable.icon81);
            } else if (icon.getCalendar_icon().equals("82")) {
                c_icon.setImageResource(R.drawable.icon82);
            } else if (icon.getCalendar_icon().equals("83")) {
                c_icon.setImageResource(R.drawable.icon83);
            } else if (icon.getCalendar_icon().equals("84")) {
                c_icon.setImageResource(R.drawable.icon84);
            } else if (icon.getCalendar_icon().equals("85")) {
                c_icon.setImageResource(R.drawable.icon85);
            } else if (icon.getCalendar_icon().equals("86")) {
                c_icon.setImageResource(R.drawable.icon86);
            } else if (icon.getCalendar_icon().equals("87")) {
                c_icon.setImageResource(R.drawable.icon87);
            } else if (icon.getCalendar_icon().equals("88")) {
                c_icon.setImageResource(R.drawable.icon88);
            }

            c_memo.setText(icon.getCalendar_memo());
            c_time.setText(icon.getCalendar_hour()+"시"+icon.getCalendar_minute()+"분");
        }

    }
}
