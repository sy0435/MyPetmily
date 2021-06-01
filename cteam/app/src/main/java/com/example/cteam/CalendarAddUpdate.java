package com.example.cteam;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cteam.ATask.CalListUpdate;
import com.example.cteam.Dto.CalendarDTO;
import com.example.cteam.Dto.PetDTO;

import java.lang.reflect.Field;

import static com.example.cteam.CalendarAdd.selectIcon;
import static com.example.cteam.Common.CommonMethod.isNetworkConnected;

public class CalendarAddUpdate extends Fragment {
    public static PetDTO selItem = null;

    PetSelect activity;

    CalendarDTO dto;
    Bundle bundle = null;

    String calendar_date;
    String calendar_icon;
    String calendar_memo;
    String calendar_minute;
    String calendar_hour;
    String calendar_id;

    NumberPicker CalendarAddUpdate_Time;
    TextView CalendarAddUpdate_memo;

    Button button[] = new Button[24];

    Integer[] Rid_button = {

            R.id.barBtn0, R.id.barBtn1, R.id.barBtn2, R.id.barBtn3, R.id.barBtn4,

            R.id.barBtn5, R.id.barBtn6, R.id.barBtn7, R.id.barBtn8, R.id.barBtn9,

            R.id.barBtn10, R.id.barBtn11, R.id.barBtn12, R.id.barBtn13, R.id.barBtn14,

            R.id.barBtn15, R.id.barBtn16, R.id.barBtn17, R.id.barBtn18, R.id.barBtn19,

            R.id.barBtn20, R.id.barBtn21, R.id.barBtn22, R.id.barBtn23

    };


    int selectedTime;
    ImageView CalendarAddUpdate_color1, CalendarAddUpdate_color2, CalendarAddUpdate_color3, CalendarAddUpdate_color4,
            CalendarAddUpdate_color5, CalendarAddUpdate_color6, CalendarAddUpdate_color7, CalendarAddUpdate_color8;
    int checked1 = 0;
    ImageView CalendarAddUpdate_icon1, CalendarAddUpdate_icon2, CalendarAddUpdate_icon3, CalendarAddUpdate_icon4,
            CalendarAddUpdate_icon5, CalendarAddUpdate_icon6, CalendarAddUpdate_icon7, CalendarAddUpdate_icon8;
    int checked2 = 0;

    Button CalendarAddUpdate_cancel, CalendarAddUpdate_ok;

    String select_date = "";
    java.text.SimpleDateFormat tmpDateFormat;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar_add_update, container, false);

        activity = (PetSelect) getActivity();

        //날짜 데이터 받는 부분 : bundle로 받은 후 bundle 비우기
        /*if(activity.sBundle != null){
            bundle = activity.sBundle;
            selectIcon= (CalendarDTO) bundle.getSerializable("selectIcon");
            activity.sBundle = null;
        }*/
        //refresh();


        //메모찾기
        CalendarAddUpdate_memo = rootView.findViewById(R.id.CalendarAddUpdate_memo);


        //컬러
        CalendarAddUpdate_color1=rootView.findViewById(R.id.CalendarAddUpdate_color1);
        CalendarAddUpdate_color2=rootView.findViewById(R.id.CalendarAddUpdate_color2);
        CalendarAddUpdate_color3=rootView.findViewById(R.id.CalendarAddUpdate_color3);
        CalendarAddUpdate_color4=rootView.findViewById(R.id.CalendarAddUpdate_color4);
        CalendarAddUpdate_color5=rootView.findViewById(R.id.CalendarAddUpdate_color5);
        CalendarAddUpdate_color6=rootView.findViewById(R.id.CalendarAddUpdate_color6);
        CalendarAddUpdate_color7=rootView.findViewById(R.id.CalendarAddUpdate_color7);
        CalendarAddUpdate_color8=rootView.findViewById(R.id.CalendarAddUpdate_color8);

        //아이콘 찾기
        CalendarAddUpdate_icon1=rootView.findViewById(R.id.CalendarAddUpdate_icon1);
        CalendarAddUpdate_icon2=rootView.findViewById(R.id.CalendarAddUpdate_icon2);
        CalendarAddUpdate_icon3=rootView.findViewById(R.id.CalendarAddUpdate_icon3);
        CalendarAddUpdate_icon4=rootView.findViewById(R.id.CalendarAddUpdate_icon4);
        CalendarAddUpdate_icon5=rootView.findViewById(R.id.CalendarAddUpdate_icon5);
        CalendarAddUpdate_icon6=rootView.findViewById(R.id.CalendarAddUpdate_icon6);
        CalendarAddUpdate_icon7=rootView.findViewById(R.id.CalendarAddUpdate_icon7);
        CalendarAddUpdate_icon8=rootView.findViewById(R.id.CalendarAddUpdate_icon8);


        if(selectIcon != null){
            Log.d("main:CalAddUpdate", "onCreateView: " + selectIcon.getCalendar_memo());

            calendar_date = selectIcon.getCalendar_date();
            calendar_icon=selectIcon.getCalendar_icon();
            calendar_memo=selectIcon.getCalendar_memo();
            calendar_hour=selectIcon.getCalendar_hour();
            calendar_minute=selectIcon.getCalendar_minute();
            calendar_id=selectIcon.getCalendar_id();

            Log.d("main:date", "onCreateView:memo 값"+calendar_memo);


            //메모값 초기화
            CalendarAddUpdate_memo.setText(calendar_memo);

            // 메모에 가져온 값 써 넣기
           if(CalendarAddUpdate_memo != null) {
               CalendarAddUpdate_memo.setText(calendar_memo);
           }
        }


        //hour 넘버피커 찾기
        CalendarAddUpdate_Time=rootView.findViewById(R.id.CalendarAddUpdate_Time);

        CalendarAddUpdate_Time.setMinValue(0);
        CalendarAddUpdate_Time.setMaxValue(23);
        CalendarAddUpdate_Time.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setDividerColor(CalendarAddUpdate_Time, android.R.color.white );
        CalendarAddUpdate_Time.setWrapSelectorWheel(false);

        if(calendar_hour != null) {
            CalendarAddUpdate_Time.setValue(Integer.parseInt(calendar_hour));     //가져온 값으로 기본 값 설정
        }
        CalendarAddUpdate_Time.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                calendar_hour=String.valueOf(newVal);

                // Toast.makeText(activity, calendar_minute, Toast.LENGTH_SHORT).show();
            }
        });

        selectedTime=CalendarAddUpdate_Time.getValue();





        activity = (PetSelect) getActivity();
        //minute 넘버피커 찾기
        final NumberPicker np = rootView.findViewById(R.id.petbarPicker);


        //날짜 포맷 설정
        tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

        //넘버피커
        np.setMinValue(0);
        np.setMaxValue(59);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setDividerColor(np, android.R.color.white );
        np.setWrapSelectorWheel(false);

        if(calendar_minute!=null) {
            np.setValue(Integer.parseInt(calendar_minute));     //가져온 값으로 기본 값 설정
        }
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                calendar_minute=String.valueOf(newVal);

                // Toast.makeText(activity, calendar_minute, Toast.LENGTH_SHORT).show();
            }
        });

        selectedTime=np.getValue();


        //시간 설정
        //CalendarAddUpdate_Time.setText(calendar_hour+"시");




       //아이콘 첫 세팅

        String[] icon=calendar_icon.split("");
        int icon0= Integer.parseInt(icon[0]);
        int icon1= Integer.parseInt(icon[1]);
        //calendar_date = select_date;
            if (icon1 == 1) {

                if (icon0 == 1) {
                        CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#FF0000"));
                        CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                        CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                    } else if (icon0 == 2) {
                        CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#FF9800"));
                        CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                        CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                    } else if (icon0 == 3) {
                        CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#FFC107"));
                        CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                        CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                    } else if (icon0 == 4) {
                        CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#41AF39"));
                        CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                        CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                    } else if (icon0 == 5) {
                        CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#3F51B5"));
                        CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                        CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                    } else if (icon0 == 6) {
                        CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#A566FF"));
                        CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                        CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                    } else if (icon0 == 7) {
                        CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#FFB2F5"));
                        CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                        CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                    } else if (icon0 == 8) {
                        CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#8C8C8C"));
                        CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                        CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                    }

                    CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                    CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                    CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                    CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                    CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                    CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                    CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);


            }else if(icon1==2){

                if (icon0 == 1) {

                CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#FF0000"));
                CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 2) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#FF9800"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 3) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#FFC107"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 4) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#41AF39"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 5) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#3F51B5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 6) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#A566FF"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 7) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#FFB2F5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 8) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#8C8C8C"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                }

        CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
        CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
        CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
        CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
        CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
        CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
        CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
            }else if(icon1==3){

                if (icon0 == 1) {

                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#FF0000"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 2) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#FF9800"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 3) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#FFC107"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 4) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#41AF39"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 5) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#3F51B5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 6) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#A566FF"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 7) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#FFB2F5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 8) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#8C8C8C"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                }

                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
            }else if(icon1==4){

                if (icon0 == 1) {

                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#FF0000"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 2) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#FF9800"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 3) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#FFC107"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 4) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#41AF39"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 5) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#3F51B5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 6) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#A566FF"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 7) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#FFB2F5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 8) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#8C8C8C"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                }

                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
            }else if(icon1==5){

                if (icon0 == 1) {

                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#FF0000"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 2) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#FF9800"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 3) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#FFC107"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 4) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#41AF39"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 5) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#3F51B5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 6) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#A566FF"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 7) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#FFB2F5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 8) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#8C8C8C"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                }

                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
            }else if(icon1==6){

                if (icon0 == 1) {

                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FF0000"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 2) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FF9800"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 3) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FFC107"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 4) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#41AF39"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 5) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#3F51B5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 6) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#A566FF"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 7) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FFB2F5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 8) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#8C8C8C"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                }

                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
            }else if(icon1==6){

                if (icon0 == 1) {

                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FF0000"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 2) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FF9800"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 3) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FFC107"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 4) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#41AF39"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 5) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#3F51B5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 6) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#A566FF"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 7) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FFB2F5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 8) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#8C8C8C"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                }

                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
            }else if(icon1==7){

                if (icon0 == 1) {

                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#FF0000"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 2) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#FF9800"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 3) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#FFC107"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 4) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#41AF39"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 5) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#3F51B5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 6) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#A566FF"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 7) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#FFB2F5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 8) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#8C8C8C"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                }

                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
            }else if(icon1==8){

                if (icon0 == 1) {

                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#FF0000"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 2) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#FF9800"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 3) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#FFC107"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 4) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#41AF39"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 5) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#3F51B5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 6) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#A566FF"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 7) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#FFB2F5"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);
                } else if (icon0 == 8) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#8C8C8C"));
                    CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                    CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);
                }

                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
            }


//                } else if (checked2 == 2) {
//                    calendar_icon = "12";
//                } else if (checked2 == 3) {
//                    calendar_icon = "13";
//                } else if (checked2 == 4) {
//                    calendar_icon = "14";
//                } else if (checked2 == 5) {
//                    calendar_icon = "15";
//                } else if (checked2 == 6) {
//                    calendar_icon = "16";
//                } else if (checked2 == 7) {
//                    calendar_icon = "17";
//                } else if (checked2 == 8) {
//                    calendar_icon = "18";
//                }
//            } else if (checked1 == 2) {
//                if (checked2 == 1) {
//                    calendar_icon = "21";
//                } else if (checked2 == 2) {
//                    calendar_icon = "22";
//                } else if (checked2 == 3) {
//                    calendar_icon = "23";
//                } else if (checked2 == 4) {
//                    calendar_icon = "24";
//                } else if (checked2 == 5) {
//                    calendar_icon = "25";
//                } else if (checked2 == 6) {
//                    calendar_icon = "26";
//                } else if (checked2 == 7) {
//                    calendar_icon = "27";
//                } else if (checked2 == 8) {
//                    calendar_icon = "28";
//                }
//            } else if (checked1 == 3) {
//                if (checked2 == 1) {
//                    calendar_icon = "31";
//                } else if (checked2 == 2) {
//                    calendar_icon = "32";
//                } else if (checked2 == 3) {
//                    calendar_icon = "33";
//                } else if (checked2 == 4) {
//                    calendar_icon = "34";
//                } else if (checked2 == 5) {
//                    calendar_icon = "35";
//                } else if (checked2 == 6) {
//                    calendar_icon = "36";
//                } else if (checked2 == 7) {
//                    calendar_icon = "37";
//                } else if (checked2 == 8) {
//                    calendar_icon = "38";
//                }
//            } else if (checked1 == 4) {
//                if (checked2 == 1) {
//                    calendar_icon = "41";
//                } else if (checked2 == 2) {
//                    calendar_icon = "42";
//                } else if (checked2 == 3) {
//                    calendar_icon = "43";
//                } else if (checked2 == 4) {
//                    calendar_icon = "44";
//                } else if (checked2 == 5) {
//                    calendar_icon = "45";
//                } else if (checked2 == 6) {
//                    calendar_icon = "46";
//                } else if (checked2 == 7) {
//                    calendar_icon = "47";
//                } else if (checked2 == 8) {
//                    calendar_icon = "48";
//                }
//            } else if (checked1 == 5) {
//                if (checked2 == 1) {
//                    calendar_icon = "51";
//                } else if (checked2 == 2) {
//                    calendar_icon = "52";
//                } else if (checked2 == 3) {
//                    calendar_icon = "53";
//                } else if (checked2 == 4) {
//                    calendar_icon = "54";
//                } else if (checked2 == 5) {
//                    calendar_icon = "55";
//                } else if (checked2 == 6) {
//                    calendar_icon = "56";
//                } else if (checked2 == 7) {
//                    calendar_icon = "57";
//                } else if (checked2 == 8) {
//                    calendar_icon = "58";
//                }
//            } else if (checked1 == 6) {
//                if (checked2 == 1) {
//                    calendar_icon = "61";
//                } else if (checked2 == 2) {
//                    calendar_icon = "62";
//                } else if (checked2 == 3) {
//                    calendar_icon = "63";
//                } else if (checked2 == 4) {
//                    calendar_icon = "64";
//                } else if (checked2 == 5) {
//                    calendar_icon = "65";
//                } else if (checked2 == 6) {
//                    calendar_icon = "66";
//                } else if (checked2 == 7) {
//                    calendar_icon = "67";
//                } else if (checked2 == 8) {
//                    calendar_icon = "68";
//                }
//            } else if (checked1 == 7) {
//                if (checked2 == 1) {
//                    calendar_icon = "71";
//                } else if (checked2 == 2) {
//                    calendar_icon = "72";
//                } else if (checked2 == 3) {
//                    calendar_icon = "73";
//                } else if (checked2 == 4) {
//                    calendar_icon = "74";
//                } else if (checked2 == 5) {
//                    calendar_icon = "75";
//                } else if (checked2 == 6) {
//                    calendar_icon = "76";
//                } else if (checked2 == 7) {
//                    calendar_icon = "77";
//                } else if (checked2 == 8) {
//                    calendar_icon = "78";
//                }
//            } else if (checked1 == 8) {
//                if (checked2 == 1) {
//                    calendar_icon = "81";
//                } else if (checked2 == 2) {
//                    calendar_icon = "82";
//                } else if (checked2 == 3) {
//                    calendar_icon = "83";
//                } else if (checked2 == 4) {
//                    calendar_icon = "84";
//                } else if (checked2 == 5) {
//                    calendar_icon = "85";
//                } else if (checked2 == 6) {
//                    calendar_icon = "86";
//                } else if (checked2 == 7) {
//                    calendar_icon = "87";
//                } else if (checked2 == 8) {
//                    calendar_icon = "88";
//                }



        //색상 선택 > (코드 간단하게 수정 가능?)
        CalendarAddUpdate_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddUpdate_color1.setBackgroundColor(Color.BLACK);
                CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 1;
            }
        });
        CalendarAddUpdate_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color2.setBackgroundColor(Color.BLACK);
                CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 2;
            }
        });
        CalendarAddUpdate_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color3.setBackgroundColor(Color.BLACK);
                CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 3;
            }
        });
        CalendarAddUpdate_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color4.setBackgroundColor(Color.BLACK);
                CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 4;
            }
        });
        CalendarAddUpdate_color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color5.setBackgroundColor(Color.BLACK);
                CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 5;
            }
        });
        CalendarAddUpdate_color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color6.setBackgroundColor(Color.BLACK);
                CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 6;
            }
        });
        CalendarAddUpdate_color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color7.setBackgroundColor(Color.BLACK);
                CalendarAddUpdate_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 7;
            }
        });
        CalendarAddUpdate_color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddUpdate_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddUpdate_color8.setBackgroundColor(Color.BLACK);

                checked1 = 8;
            }
        });

        //아이콘1 선택 > (코드 간단하게 수정 가능?)
        CalendarAddUpdate_icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddUpdate_icon1.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);

                checked2 = 1;
            }
        });
        //아이콘2 선택
        CalendarAddUpdate_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked1 == 1) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#FF0000"));
                } else if (checked1 == 2) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#FF9800"));
                } else if (checked1 == 3) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#FFC107"));
                } else if (checked1 == 4) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#41AF39"));
                } else if (checked1 == 5) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#3F51B5"));
                } else if (checked1 == 6) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#A566FF"));
                } else if (checked1 == 7) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if (checked1 == 8) {
                    CalendarAddUpdate_icon2.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);

                checked2 = 2;
            }
        });
        //아이콘3 선택
        CalendarAddUpdate_icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked1 == 1) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#FF0000"));
                } else if (checked1 == 2) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#FF9800"));
                } else if (checked1 == 3) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#FFC107"));
                } else if (checked1 == 4) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#41AF39"));
                } else if (checked1 == 5) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#3F51B5"));
                } else if (checked1 == 6) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#A566FF"));
                } else if (checked1 == 7) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if (checked1 == 8) {
                    CalendarAddUpdate_icon3.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);

                checked2 = 3;
            }
        });
        //아이콘4 선택
        CalendarAddUpdate_icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddUpdate_icon4.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);

                checked2 = 4;
            }
        });
        //아이콘5 선택
        CalendarAddUpdate_icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddUpdate_icon5.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);

                checked2 = 5;
            }
        });
        //아이콘6 선택
        CalendarAddUpdate_icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddUpdate_icon6.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);

                checked2 = 6;
            }
        });
        //아이콘4 선택
        CalendarAddUpdate_icon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddUpdate_icon7.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddUpdate_icon8.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);

                checked2 = 7;
            }
        });
        //아이콘8 선택
        CalendarAddUpdate_icon8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#FFC107"));
                }  else if(checked1 == 4) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#4374D9"));
                } else if(checked1 == 5) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddUpdate_icon8.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddUpdate_icon1.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon2.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon3.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon4.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon5.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon6.setColorFilter(Color.BLACK);
                CalendarAddUpdate_icon7.setColorFilter(Color.BLACK);

                checked2 = 8;
            }
        });

        //취소 > 저장하지 않고 CalendarAdd로 이동
        CalendarAddUpdate_cancel = rootView.findViewById(R.id.CalendarAddUpdate_cancel);
        CalendarAddUpdate_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //CalendarAddUpdate_memo.setText("");
                activity.onFragmentChange(4, null);

            }

        });


        //확인 > 저장하고 CalendarAdd로 이동
        CalendarAddUpdate_ok = rootView.findViewById(R.id.CalendarAddUpdate_ok);
        CalendarAddUpdate_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected(getContext()) == true) {
                    //calendar_date = select_date;
                    if (checked1 == 1) {
                        if (checked2 == 1) {
                            calendar_icon = "11";
                        } else if (checked2 == 2) {
                            calendar_icon = "12";
                        } else if (checked2 == 3) {
                            calendar_icon = "13";
                        } else if (checked2 == 4) {
                            calendar_icon = "14";
                        } else if (checked2 == 5) {
                            calendar_icon = "15";
                        } else if (checked2 == 6) {
                            calendar_icon = "16";
                        } else if (checked2 == 7) {
                            calendar_icon = "17";
                        } else if (checked2 == 8) {
                            calendar_icon = "18";
                        }
                    } else if (checked1 == 2) {
                        if (checked2 == 1) {
                            calendar_icon = "21";
                        } else if (checked2 == 2) {
                            calendar_icon = "22";
                        } else if (checked2 == 3) {
                            calendar_icon = "23";
                        } else if (checked2 == 4) {
                            calendar_icon = "24";
                        } else if (checked2 == 5) {
                            calendar_icon = "25";
                        } else if (checked2 == 6) {
                            calendar_icon = "26";
                        } else if (checked2 == 7) {
                            calendar_icon = "27";
                        } else if (checked2 == 8) {
                            calendar_icon = "28";
                        }
                    } else if (checked1 == 3) {
                        if (checked2 == 1) {
                            calendar_icon = "31";
                        } else if (checked2 == 2) {
                            calendar_icon = "32";
                        } else if (checked2 == 3) {
                            calendar_icon = "33";
                        } else if (checked2 == 4) {
                            calendar_icon = "34";
                        } else if (checked2 == 5) {
                            calendar_icon = "35";
                        } else if (checked2 == 6) {
                            calendar_icon = "36";
                        } else if (checked2 == 7) {
                            calendar_icon = "37";
                        } else if (checked2 == 8) {
                            calendar_icon = "38";
                        }
                    } else if (checked1 == 4) {
                        if (checked2 == 1) {
                            calendar_icon = "41";
                        } else if (checked2 == 2) {
                            calendar_icon = "42";
                        } else if (checked2 == 3) {
                            calendar_icon = "43";
                        } else if (checked2 == 4) {
                            calendar_icon = "44";
                        } else if (checked2 == 5) {
                            calendar_icon = "45";
                        } else if (checked2 == 6) {
                            calendar_icon = "46";
                        } else if (checked2 == 7) {
                            calendar_icon = "47";
                        } else if (checked2 == 8) {
                            calendar_icon = "48";
                        }
                    } else if (checked1 == 5) {
                        if (checked2 == 1) {
                            calendar_icon = "51";
                        } else if (checked2 == 2) {
                            calendar_icon = "52";
                        } else if (checked2 == 3) {
                            calendar_icon = "53";
                        } else if (checked2 == 4) {
                            calendar_icon = "54";
                        } else if (checked2 == 5) {
                            calendar_icon = "55";
                        } else if (checked2 == 6) {
                            calendar_icon = "56";
                        } else if (checked2 == 7) {
                            calendar_icon = "57";
                        } else if (checked2 == 8) {
                            calendar_icon = "58";
                        }
                    } else if (checked1 == 6) {
                        if (checked2 == 1) {
                            calendar_icon = "61";
                        } else if (checked2 == 2) {
                            calendar_icon = "62";
                        } else if (checked2 == 3) {
                            calendar_icon = "63";
                        } else if (checked2 == 4) {
                            calendar_icon = "64";
                        } else if (checked2 == 5) {
                            calendar_icon = "65";
                        } else if (checked2 == 6) {
                            calendar_icon = "66";
                        } else if (checked2 == 7) {
                            calendar_icon = "67";
                        } else if (checked2 == 8) {
                            calendar_icon = "68";
                        }
                    } else if (checked1 == 7) {
                        if (checked2 == 1) {
                            calendar_icon = "71";
                        } else if (checked2 == 2) {
                            calendar_icon = "72";
                        } else if (checked2 == 3) {
                            calendar_icon = "73";
                        } else if (checked2 == 4) {
                            calendar_icon = "74";
                        } else if (checked2 == 5) {
                            calendar_icon = "75";
                        } else if (checked2 == 6) {
                            calendar_icon = "76";
                        } else if (checked2 == 7) {
                            calendar_icon = "77";
                        } else if (checked2 == 8) {
                            calendar_icon = "78";
                        }
                    } else if (checked1 == 8) {
                        if (checked2 == 1) {
                            calendar_icon = "81";
                        } else if (checked2 == 2) {
                            calendar_icon = "82";
                        } else if (checked2 == 3) {
                            calendar_icon = "83";
                        } else if (checked2 == 4) {
                            calendar_icon = "84";
                        } else if (checked2 == 5) {
                            calendar_icon = "85";
                        } else if (checked2 == 6) {
                            calendar_icon = "86";
                        } else if (checked2 == 7) {
                            calendar_icon = "87";
                        } else if (checked2 == 8) {
                            calendar_icon = "88";
                        }
                    }
                    calendar_memo = CalendarAddUpdate_memo.getText().toString();

                    Log.d("main:hour", "onClick: 시간"+calendar_hour);

                    CalListUpdate calListUpdate = new CalListUpdate(calendar_date, calendar_icon, calendar_memo,calendar_hour,calendar_minute,calendar_id);
                    calListUpdate.execute();

                    activity.onFragmentChange(4, null);

                }
                //Toast.makeText(getContext(), calendar_icon, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;



    } //onCreateView()

    private void setDividerColor(NumberPicker picker, int color) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void refresh() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    } //refresh()

    @Override
    public void onStart(){
        super.onStart();

        CalendarAddUpdate_memo.setText(calendar_memo);
    }

    @Override
    public void onResume(){
        super.onResume();

        CalendarAddUpdate_memo.setText(calendar_memo);
    }

}