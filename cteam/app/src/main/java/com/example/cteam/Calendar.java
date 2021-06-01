package com.example.cteam;

import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.cteam.ATask.CalcalSelect;

import com.example.cteam.Dto.CalendarDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;
import static com.example.cteam.PetAdd.petAddDto;

public class Calendar extends Fragment {
    //로그 찍어볼 때
    //private static final String TAG = "main:Calendar";

    PetSelect activity;

    CalendarDTO dto;
    Bundle bundle = null;

    com.applandeo.materialcalendarview.CalendarView CalendarView;

    List<EventDay> events;
    ImageView Calendar_event;

    ArrayList<CalendarDTO> icons;
    CalcalSelect calcalSelect;
    String petname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar,
                container, false);

        activity = (PetSelect) getActivity();

        //캘린더뷰 찾기
        CalendarView = (CalendarView) rootView.findViewById(R.id.CalendarView);

        //달력 범위 설정 : -12개월 ~ +12개월
        java.util.Calendar min = java.util.Calendar.getInstance();
        min.add(java.util.Calendar.MONTH, -12);
        java.util.Calendar max = java.util.Calendar.getInstance();
        max.add(java.util.Calendar.MONTH, 12);
        CalendarView.setMinimumDate(min);
        CalendarView.setMaximumDate(max);

        //이벤트 추가할 배열 생성
        events = new ArrayList<>();

        //캘린더에 이벤트 등록
        CalendarView.setEvents(events);

        //날짜 클릭 > 이벤트 추가 버튼 등장
        CalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                //날짜 데이터 보내는 부분
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String select_date = format.format(eventDay.getCalendar().getTime());
                Bundle bundle = new Bundle();
                bundle.putString("select_date", select_date);

                //이벤트 추가 버튼 등장
                Calendar_event = rootView.findViewById(R.id.Calendar_event);
                Calendar_event.setVisibility(View.VISIBLE);

/*
                //달력 아래에 붙일 아이콘 데이터 불러옴
                calendar_date = select_date;
                if(isNetworkConnected(getActivity()) == true) {
                    calListSelect2 = new CalListSelect2(icons, adapter2, calendar_date);
                    try {
                        calListSelect2.execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "인터넷이 연결되어 있지 않습니다.",
                            Toast.LENGTH_SHORT).show();
                }

                icons = new ArrayList<>();
                adapter2 = new CalendarAdapter2(getActivity(), icons);
                Calendar_view = rootView.findViewById(R.id.Calendar_view);
                Calendar_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                Calendar_view.setAdapter(adapter2);
*/

                //이벤트 추가 버튼 클릭 > 날짜 데이터 갖고 CalendarAdd로 이동
                Calendar_event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.onFragmentChange(4, bundle);
                    }
                });

            }
        });

        //달력에 붙일 아이콘 데이터 불러옴
        petname = petAddDto.getPetname();
        if (isNetworkConnected(getActivity()) == true) {
            calcalSelect = new CalcalSelect(petname);
            try {
                icons = calcalSelect.execute().get();
                //Log.d("main:Calendar", "onCreateView: " + icons.get(0).calendar_date);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        //날짜 형식 바꾸기
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String strDate = format.format(date);

        String date1;
        String date2;
        Date firstDate;
        Date secondDate;
        long calDate;
        long calDates;

        //같은 날짜 갯수 구하기
        ArrayList<Integer> dateCounts = new ArrayList<>();
        int count = 1;

        for (int i = 0; i < icons.size(); i++) {
            if (i == icons.size() - 1) {
                dateCounts.add(count);
                break;
            }
            String tmpdate = icons.get(i).calendar_date;
            if (icons.get(i + 1).calendar_date != null && tmpdate.equals(icons.get(i + 1).calendar_date)) {
                count++;
            } else {
                dateCounts.add(count);
                count = 1;
            }
        }
        for(int i = 0; i < dateCounts.size(); i++) {
            //Log.d("main:Calendar", "onCreateView: " + dateCounts.get(i));
        }

        //캘린더에 아이콘 넣기
        int sum = 0;
        try {
            for (int i = 0; i < dateCounts.size(); i++) {
                //날짜 차이 구하기
                java.util.Calendar calendar3 = java.util.Calendar.getInstance();

                if (i == 0) {
                    date1 = icons.get(i).calendar_date;
                    date2 = format.format(calendar3.getTime());
                } else {
                    sum += dateCounts.get(i-1);
                    //Log.d("main:Calendar", "onCreateView: sum" + i + " : " + sum);
                    date1 = icons.get(sum).calendar_date;
                    date2 = format.format(calendar3.getTime());
                    //Log.d("main:Calendar", "onCreateView: sum :" + sum);
                }

                firstDate = format.parse(date1);
                secondDate = format.parse(date2);

                calDate = firstDate.getTime() - secondDate.getTime();
                calDates = calDate / (24 * 60 * 60 * 1000);

                //아이콘 넣기
                LayerDrawable cal_icon3 = (LayerDrawable) getResources().getDrawable(R.drawable.cal_3icons);
                for (int j = 0; j < dateCounts.get(i); j++) {
                    //Log.d("main:calen", "onCreateView: dateCounts.get(j)" + dateCounts.get(j));
                    //Log.d("main:calen", "onCreateView: Sum + k" + sum+j);

                    //첫번째 아이콘
                    if (j == 0) {
                        if (icons.get(sum + j).calendar_icon.equals("11")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon11));
                        } else if (icons.get(sum + j).calendar_icon.equals("12")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon12));
                        } else if (icons.get(sum + j).calendar_icon.equals("13")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon13));
                        } else if (icons.get(sum + j).calendar_icon.equals("14")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon14));
                        } else if (icons.get(sum + j).calendar_icon.equals("15")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon15));
                        } else if (icons.get(sum + j).calendar_icon.equals("16")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon16));
                        } else if (icons.get(sum + j).calendar_icon.equals("17")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon17));
                        } else if (icons.get(sum + j).calendar_icon.equals("18")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon18));
                        } else if (icons.get(sum + j).calendar_icon.equals("21")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon21));
                        } else if (icons.get(sum + j).calendar_icon.equals("22")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon22));
                        } else if (icons.get(sum + j).calendar_icon.equals("23")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon23));
                        } else if (icons.get(sum + j).calendar_icon.equals("24")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon24));
                        } else if (icons.get(sum + j).calendar_icon.equals("25")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon25));
                        } else if (icons.get(sum + j).calendar_icon.equals("26")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon26));
                        } else if (icons.get(sum + j).calendar_icon.equals("27")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon27));
                        } else if (icons.get(sum + j).calendar_icon.equals("28")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon28));
                        } else if (icons.get(sum + j).calendar_icon.equals("31")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon31));
                        } else if (icons.get(sum + j).calendar_icon.equals("32")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon32));
                        } else if (icons.get(sum + j).calendar_icon.equals("33")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon33));
                        } else if (icons.get(sum + j).calendar_icon.equals("34")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon34));
                        } else if (icons.get(sum + j).calendar_icon.equals("35")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon35));
                        } else if (icons.get(sum + j).calendar_icon.equals("36")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon36));
                        } else if (icons.get(sum + j).calendar_icon.equals("37")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon37));
                        } else if (icons.get(sum + j).calendar_icon.equals("38")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon38));
                        } else if (icons.get(sum + j).calendar_icon.equals("41")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon41));
                        } else if (icons.get(sum + j).calendar_icon.equals("42")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon42));
                        } else if (icons.get(sum + j).calendar_icon.equals("43")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon43));
                        } else if (icons.get(sum + j).calendar_icon.equals("44")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon44));
                        } else if (icons.get(sum + j).calendar_icon.equals("45")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon45));
                        } else if (icons.get(sum + j).calendar_icon.equals("46")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon46));
                        } else if (icons.get(sum + j).calendar_icon.equals("47")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon47));
                        } else if (icons.get(sum + j).calendar_icon.equals("48")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon48));
                        } else if (icons.get(sum + j).calendar_icon.equals("51")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon51));
                        } else if (icons.get(sum + j).calendar_icon.equals("52")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon52));
                        } else if (icons.get(sum + j).calendar_icon.equals("53")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon53));
                        } else if (icons.get(sum + j).calendar_icon.equals("54")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon54));
                        } else if (icons.get(sum + j).calendar_icon.equals("55")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon55));
                        } else if (icons.get(sum + j).calendar_icon.equals("56")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon56));
                        } else if (icons.get(sum + j).calendar_icon.equals("57")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon57));
                        } else if (icons.get(sum + j).calendar_icon.equals("58")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon58));
                        } else if (icons.get(sum + j).calendar_icon.equals("61")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon61));
                        } else if (icons.get(sum + j).calendar_icon.equals("62")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon62));
                        } else if (icons.get(sum + j).calendar_icon.equals("63")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon63));
                        } else if (icons.get(sum + j).calendar_icon.equals("64")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon64));
                        } else if (icons.get(sum + j).calendar_icon.equals("65")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon65));
                        } else if (icons.get(sum + j).calendar_icon.equals("66")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon66));
                        } else if (icons.get(sum + j).calendar_icon.equals("67")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon67));
                        } else if (icons.get(sum + j).calendar_icon.equals("68")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon68));
                        } else if (icons.get(sum + j).calendar_icon.equals("71")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon71));
                        } else if (icons.get(sum + j).calendar_icon.equals("72")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon72));
                        } else if (icons.get(sum + j).calendar_icon.equals("73")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon73));
                        } else if (icons.get(sum + j).calendar_icon.equals("74")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon74));
                        } else if (icons.get(sum + j).calendar_icon.equals("75")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon75));
                        } else if (icons.get(sum + j).calendar_icon.equals("76")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon76));
                        } else if (icons.get(sum + j).calendar_icon.equals("77")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon77));
                        } else if (icons.get(sum + j).calendar_icon.equals("78")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon78));
                        } else if (icons.get(sum + j).calendar_icon.equals("81")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon81));
                        } else if (icons.get(sum + j).calendar_icon.equals("82")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon82));
                        } else if (icons.get(sum + j).calendar_icon.equals("83")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon83));
                        } else if (icons.get(sum + j).calendar_icon.equals("84")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon84));
                        } else if (icons.get(sum + j).calendar_icon.equals("85")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon85));
                        } else if (icons.get(sum + j).calendar_icon.equals("86")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon86));
                        } else if (icons.get(sum + j).calendar_icon.equals("87")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon87));
                        } else if (icons.get(sum + j).calendar_icon.equals("88")) {
                            cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon88));
                        }
                    } //첫번째 아이콘

                    //두번째 아이콘
                    if (dateCounts.get(i) == 2 || dateCounts.get(i) >= 3) {
                        if (j == 1) {
                            if (icons.get(sum + j).calendar_icon.equals("11")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon11));
                            } else if (icons.get(sum + j).calendar_icon.equals("12")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon12));
                            } else if (icons.get(sum + j).calendar_icon.equals("13")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon13));
                            } else if (icons.get(sum + j).calendar_icon.equals("14")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon14));
                            } else if (icons.get(sum + j).calendar_icon.equals("15")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon15));
                            } else if (icons.get(sum + j).calendar_icon.equals("16")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon16));
                            } else if (icons.get(sum + j).calendar_icon.equals("17")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon17));
                            } else if (icons.get(sum + j).calendar_icon.equals("18")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon18));
                            } else if (icons.get(sum + j).calendar_icon.equals("21")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon21));
                            } else if (icons.get(sum + j).calendar_icon.equals("22")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon22));
                            } else if (icons.get(sum + j).calendar_icon.equals("23")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon23));
                            } else if (icons.get(sum + j).calendar_icon.equals("24")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon24));
                            } else if (icons.get(sum + j).calendar_icon.equals("25")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon25));
                            } else if (icons.get(sum + j).calendar_icon.equals("26")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon26));
                            } else if (icons.get(sum + j).calendar_icon.equals("27")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon27));
                            } else if (icons.get(sum + j).calendar_icon.equals("28")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon28));
                            } else if (icons.get(sum + j).calendar_icon.equals("31")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon31));
                            } else if (icons.get(sum + j).calendar_icon.equals("32")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon32));
                            } else if (icons.get(sum + j).calendar_icon.equals("33")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon33));
                            } else if (icons.get(sum + j).calendar_icon.equals("34")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon34));
                            } else if (icons.get(sum + j).calendar_icon.equals("35")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon35));
                            } else if (icons.get(sum + j).calendar_icon.equals("36")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon36));
                            } else if (icons.get(sum + j).calendar_icon.equals("37")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon37));
                            } else if (icons.get(sum + j).calendar_icon.equals("38")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon38));
                            } else if (icons.get(sum + j).calendar_icon.equals("41")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon41));
                            } else if (icons.get(sum + j).calendar_icon.equals("42")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon42));
                            } else if (icons.get(sum + j).calendar_icon.equals("43")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon43));
                            } else if (icons.get(sum + j).calendar_icon.equals("44")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon44));
                            } else if (icons.get(sum + j).calendar_icon.equals("45")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon45));
                            } else if (icons.get(sum + j).calendar_icon.equals("46")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon46));
                            } else if (icons.get(sum + j).calendar_icon.equals("47")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon47));
                            } else if (icons.get(sum + j).calendar_icon.equals("48")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon48));
                            } else if (icons.get(sum + j).calendar_icon.equals("51")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon51));
                            } else if (icons.get(sum + j).calendar_icon.equals("52")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon52));
                            } else if (icons.get(sum + j).calendar_icon.equals("53")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon53));
                            } else if (icons.get(sum + j).calendar_icon.equals("54")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon54));
                            } else if (icons.get(sum + j).calendar_icon.equals("55")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon55));
                            } else if (icons.get(sum + j).calendar_icon.equals("56")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon56));
                            } else if (icons.get(sum + j).calendar_icon.equals("57")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon57));
                            } else if (icons.get(sum + j).calendar_icon.equals("58")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon58));
                            } else if (icons.get(sum + j).calendar_icon.equals("61")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon61));
                            } else if (icons.get(sum + j).calendar_icon.equals("62")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon62));
                            } else if (icons.get(sum + j).calendar_icon.equals("63")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon63));
                            } else if (icons.get(sum + j).calendar_icon.equals("64")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon64));
                            } else if (icons.get(sum + j).calendar_icon.equals("65")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon65));
                            } else if (icons.get(sum + j).calendar_icon.equals("66")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon66));
                            } else if (icons.get(sum + j).calendar_icon.equals("67")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon67));
                            } else if (icons.get(sum + j).calendar_icon.equals("68")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon68));
                            } else if (icons.get(sum + j).calendar_icon.equals("71")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon71));
                            } else if (icons.get(sum + j).calendar_icon.equals("72")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon72));
                            } else if (icons.get(sum + j).calendar_icon.equals("73")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon73));
                            } else if (icons.get(sum + j).calendar_icon.equals("74")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon74));
                            } else if (icons.get(sum + j).calendar_icon.equals("75")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon75));
                            } else if (icons.get(sum + j).calendar_icon.equals("76")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon76));
                            } else if (icons.get(sum + j).calendar_icon.equals("77")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon77));
                            } else if (icons.get(sum + j).calendar_icon.equals("78")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon78));
                            } else if (icons.get(sum + j).calendar_icon.equals("81")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon81));
                            } else if (icons.get(sum + j).calendar_icon.equals("82")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon82));
                            } else if (icons.get(sum + j).calendar_icon.equals("83")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon83));
                            } else if (icons.get(sum + j).calendar_icon.equals("84")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon84));
                            } else if (icons.get(sum + j).calendar_icon.equals("85")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon85));
                            } else if (icons.get(sum + j).calendar_icon.equals("86")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon86));
                            } else if (icons.get(sum + j).calendar_icon.equals("87")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon87));
                            } else if (icons.get(sum + j).calendar_icon.equals("88")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon88));
                            } //아이콘
                        } //두번째
                    } else {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.background_transparent));
                    } //스케줄이 2개이상 일때만

                    //세번째 아이콘
                    if (dateCounts.get(i) >= 3) {
                        if (j == 2) {
                            if (icons.get(sum + j).calendar_icon.equals("11")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon11));
                            } else if (icons.get(sum + j).calendar_icon.equals("12")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon12));
                            } else if (icons.get(sum + j).calendar_icon.equals("13")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon13));
                            } else if (icons.get(sum + j).calendar_icon.equals("14")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon14));
                            } else if (icons.get(sum + j).calendar_icon.equals("15")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon15));
                            } else if (icons.get(sum + j).calendar_icon.equals("16")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon16));
                            } else if (icons.get(sum + j).calendar_icon.equals("17")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon17));
                            } else if (icons.get(sum + j).calendar_icon.equals("18")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon18));
                            } else if (icons.get(sum + j).calendar_icon.equals("21")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon21));
                            } else if (icons.get(sum + j).calendar_icon.equals("22")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon22));
                            } else if (icons.get(sum + j).calendar_icon.equals("23")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon23));
                            } else if (icons.get(sum + j).calendar_icon.equals("24")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon24));
                            } else if (icons.get(sum + j).calendar_icon.equals("25")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon25));
                            } else if (icons.get(sum + j).calendar_icon.equals("26")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon26));
                            } else if (icons.get(sum + j).calendar_icon.equals("27")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon27));
                            } else if (icons.get(sum + j).calendar_icon.equals("28")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon28));
                            } else if (icons.get(sum + j).calendar_icon.equals("31")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon31));
                            } else if (icons.get(sum + j).calendar_icon.equals("32")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon32));
                            } else if (icons.get(sum + j).calendar_icon.equals("33")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon33));
                            } else if (icons.get(sum + j).calendar_icon.equals("34")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon34));
                            } else if (icons.get(sum + j).calendar_icon.equals("35")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon35));
                            } else if (icons.get(sum + j).calendar_icon.equals("36")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon36));
                            } else if (icons.get(sum + j).calendar_icon.equals("37")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon37));
                            } else if (icons.get(sum + j).calendar_icon.equals("38")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon38));
                            } else if (icons.get(sum + j).calendar_icon.equals("41")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon41));
                            } else if (icons.get(sum + j).calendar_icon.equals("42")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon42));
                            } else if (icons.get(sum + j).calendar_icon.equals("43")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon43));
                            } else if (icons.get(sum + j).calendar_icon.equals("44")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon44));
                            } else if (icons.get(sum + j).calendar_icon.equals("45")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon45));
                            } else if (icons.get(sum + j).calendar_icon.equals("46")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon46));
                            } else if (icons.get(sum + j).calendar_icon.equals("47")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon47));
                            } else if (icons.get(sum + j).calendar_icon.equals("48")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon48));
                            } else if (icons.get(sum + j).calendar_icon.equals("51")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon51));
                            } else if (icons.get(sum + j).calendar_icon.equals("52")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon52));
                            } else if (icons.get(sum + j).calendar_icon.equals("53")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon53));
                            } else if (icons.get(sum + j).calendar_icon.equals("54")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon54));
                            } else if (icons.get(sum + j).calendar_icon.equals("55")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon55));
                            } else if (icons.get(sum + j).calendar_icon.equals("56")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon56));
                            } else if (icons.get(sum + j).calendar_icon.equals("57")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon57));
                            } else if (icons.get(sum + j).calendar_icon.equals("58")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon58));
                            } else if (icons.get(sum + j).calendar_icon.equals("61")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon61));
                            } else if (icons.get(sum + j).calendar_icon.equals("62")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon62));
                            } else if (icons.get(sum + j).calendar_icon.equals("63")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon63));
                            } else if (icons.get(sum + j).calendar_icon.equals("64")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon64));
                            } else if (icons.get(sum + j).calendar_icon.equals("65")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon65));
                            } else if (icons.get(sum + j).calendar_icon.equals("66")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon66));
                            } else if (icons.get(sum + j).calendar_icon.equals("67")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon67));
                            } else if (icons.get(sum + j).calendar_icon.equals("68")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon68));
                            } else if (icons.get(sum + j).calendar_icon.equals("71")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon71));
                            } else if (icons.get(sum + j).calendar_icon.equals("72")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon72));
                            } else if (icons.get(sum + j).calendar_icon.equals("73")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon73));
                            } else if (icons.get(sum + j).calendar_icon.equals("74")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon74));
                            } else if (icons.get(sum + j).calendar_icon.equals("75")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon75));
                            } else if (icons.get(sum + j).calendar_icon.equals("76")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon76));
                            } else if (icons.get(sum + j).calendar_icon.equals("77")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon77));
                            } else if (icons.get(sum + j).calendar_icon.equals("78")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon78));
                            } else if (icons.get(sum + j).calendar_icon.equals("81")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon81));
                            } else if (icons.get(sum + j).calendar_icon.equals("82")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon82));
                            } else if (icons.get(sum + j).calendar_icon.equals("83")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon83));
                            } else if (icons.get(sum + j).calendar_icon.equals("84")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon84));
                            } else if (icons.get(sum + j).calendar_icon.equals("85")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon85));
                            } else if (icons.get(sum + j).calendar_icon.equals("86")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon86));
                            } else if (icons.get(sum + j).calendar_icon.equals("87")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon87));
                            } else if (icons.get(sum + j).calendar_icon.equals("88")) {
                                cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon88));
                            } //아이콘
                        } //세번째
                    } else {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.background_transparent));
                    } //스케줄이 3개이상 일때만

                } //for j
                //달력에 적용하기
                calendar3.add(java.util.Calendar.DAY_OF_MONTH, (int) calDates);
                events.add(new EventDay(calendar3, cal_icon3));
                //날짜, 아이콘 일치하는지 로그 찍어보기
                Log.d("main:Calendar", "date: " + icons.get(i).calendar_date + ", icon: " + icons.get(i).calendar_icon);
            } //for i

        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
        //날짜에 아이콘 붙이기 (3개짜리)
        LayerDrawable cal_icon3 = (LayerDrawable) getResources().getDrawable(R.drawable.cal_3icons);

        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon11));
        cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon33));
        cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon44));

        java.util.Calendar calendar3 = java.util.Calendar.getInstance();
        calendar3.add(java.util.Calendar.DAY_OF_MONTH, 0);
        events.add(new EventDay(calendar3, cal_icon3));

        //날짜에 아이콘 붙이기 (4개짜리)
        LayerDrawable cal_icon4 = (LayerDrawable) getResources().getDrawable(R.drawable.cal_4icons);

        cal_icon4.setDrawableByLayerId(R.id.cal_4icons1, getResources().getDrawable(R.drawable.icon11));
        cal_icon4.setDrawableByLayerId(R.id.cal_4icons2, getResources().getDrawable(R.drawable.icon22));
        cal_icon4.setDrawableByLayerId(R.id.cal_4icons3, getResources().getDrawable(R.drawable.icon33));
        cal_icon4.setDrawableByLayerId(R.id.cal_4icons4, getResources().getDrawable(R.drawable.icon44));

        java.util.Calendar calendar4 = java.util.Calendar.getInstance();
        calendar4.add(java.util.Calendar.DAY_OF_MONTH, 0);
        events.add(new EventDay(calendar4, cal_icon4));
        */

        return rootView;

    } //onCreateView()

}