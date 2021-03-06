package com.example.cteam;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cteam.ATask.CalListInsert;
import com.example.cteam.Dto.CalendarDTO;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;
import static com.example.cteam.PetAdd.petAddDto;

public class CalendarAddInsert extends Fragment {

    PetSelect activity;

    int selectedTime;
    CalendarDTO dto;
    Bundle bundle = null;

    String calendar_date;
    String calendar_icon;
    String calendar_memo;
    String calendar_minute;
    String calendar_hour;
    String petname;
    TextView CalendarAddInsert_Time;

    ImageView CalendarAddInsert_color1, CalendarAddInsert_color2, CalendarAddInsert_color3, CalendarAddInsert_color4,
            CalendarAddInsert_color5, CalendarAddInsert_color6, CalendarAddInsert_color7, CalendarAddInsert_color8;
    int checked1 = 0;
    ImageView CalendarAddInsert_icon1, CalendarAddInsert_icon2, CalendarAddInsert_icon3, CalendarAddInsert_icon4,
            CalendarAddInsert_icon5, CalendarAddInsert_icon6, CalendarAddInsert_icon7, CalendarAddInsert_icon8;
    int checked2 = 0;
    TextView CalendarAddInsert_memo;
    Button CalendarAddInsert_cancel, CalendarAddInsert_ok;

    String select_date = "";
    java.text.SimpleDateFormat tmpDateFormat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar_add_insert, container, false);

        activity = (PetSelect) getActivity();
        //???????????? ??????
        final NumberPicker np = rootView.findViewById(R.id.petbarPicker);

        //?????? ????????? ?????? ?????? : bundle??? ?????? ??? bundle ?????????
        if(activity.cBundle != null){
            bundle = activity.cBundle;
            select_date = bundle.getString("select_date");
            calendar_hour= String.valueOf(bundle.getInt("time"));
            activity.cBundle = null;
        }

        //?????? ?????? ??????
        tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

        //????????????
        np.setMinValue(0);
        np.setMaxValue(59);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setDividerColor(np, android.R.color.white );
        np.setWrapSelectorWheel(false);
        np.setValue(0);

        //?????? 0??????
        calendar_minute="0";
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    calendar_minute = String.valueOf(newVal);

               // Toast.makeText(activity, calendar_minute, Toast.LENGTH_SHORT).show();
            }
        });


        selectedTime=np.getValue();

        Log.d("calendarInsert", "????????? ??????"+selectedTime);

        CalendarAddInsert_Time=rootView.findViewById(R.id.CalendarAddInsert_Time);


        //?????? ??????
        CalendarAddInsert_Time.setText(calendar_hour+"???");



        //??????
        CalendarAddInsert_memo = rootView.findViewById(R.id.CalendarAddInsert_memo);
        //CalendarAddInsert_memo.setText();

        //?????? ?????? > (?????? ???????????? ?????? ???????)
        CalendarAddInsert_color1 = rootView.findViewById(R.id.CalendarAddInsert_color1);
        CalendarAddInsert_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 1;
            }
        });
        CalendarAddInsert_color2 = rootView.findViewById(R.id.CalendarAddInsert_color2);
        CalendarAddInsert_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 2;
            }
        });
        CalendarAddInsert_color3 = rootView.findViewById(R.id.CalendarAddInsert_color3);
        CalendarAddInsert_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 3;
            }
        });
        CalendarAddInsert_color4 = rootView.findViewById(R.id.CalendarAddInsert_color4);
        CalendarAddInsert_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 4;
            }
        });
        CalendarAddInsert_color5 = rootView.findViewById(R.id.CalendarAddInsert_color5);
        CalendarAddInsert_color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 5;
            }
        });
        CalendarAddInsert_color6 = rootView.findViewById(R.id.CalendarAddInsert_color6);
        CalendarAddInsert_color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 6;
            }
        });
        CalendarAddInsert_color7 = rootView.findViewById(R.id.CalendarAddInsert_color7);
        CalendarAddInsert_color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 7;
            }
        });
        CalendarAddInsert_color8 = rootView.findViewById(R.id.CalendarAddInsert_color8);
        CalendarAddInsert_color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.BLACK);

                checked1 = 8;
            }
        });

        //?????????1 ?????? > (?????? ???????????? ?????? ???????)
        CalendarAddInsert_icon1 = rootView.findViewById(R.id.CalendarAddInsert_icon1);
        CalendarAddInsert_icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);

                checked2 = 1;
            }
        });
        //?????????2 ??????
        CalendarAddInsert_icon2 = rootView.findViewById(R.id.CalendarAddInsert_icon2);
        CalendarAddInsert_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked1 == 1) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#FF0000"));
                } else if (checked1 == 2) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#FF9800"));
                } else if (checked1 == 3) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#FFC107"));
                } else if (checked1 == 4) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#41AF39"));
                } else if (checked1 == 5) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#3F51B5"));
                } else if (checked1 == 6) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#A566FF"));
                } else if (checked1 == 7) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if (checked1 == 8) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);

                checked2 = 2;
            }
        });
        //?????????3 ??????
        CalendarAddInsert_icon3 = rootView.findViewById(R.id.CalendarAddInsert_icon3);
        CalendarAddInsert_icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked1 == 1) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#FF0000"));
                } else if (checked1 == 2) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#FF9800"));
                } else if (checked1 == 3) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#FFC107"));
                } else if (checked1 == 4) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#41AF39"));
                } else if (checked1 == 5) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#3F51B5"));
                } else if (checked1 == 6) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#A566FF"));
                } else if (checked1 == 7) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if (checked1 == 8) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);

                checked2 = 3;
            }
        });
        //?????????4 ??????
        CalendarAddInsert_icon4 = rootView.findViewById(R.id.CalendarAddInsert_icon4);
        CalendarAddInsert_icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);

                checked2 = 4;
            }
        });
        //?????????5 ??????
        CalendarAddInsert_icon5 = rootView.findViewById(R.id.CalendarAddInsert_icon5);
        CalendarAddInsert_icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);

                checked2 = 5;
            }
        });
        //?????????6 ??????
        CalendarAddInsert_icon6 = rootView.findViewById(R.id.CalendarAddInsert_icon6);
        CalendarAddInsert_icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);

                checked2 = 6;
            }
        });
        //?????????4 ??????
        CalendarAddInsert_icon7 = rootView.findViewById(R.id.CalendarAddInsert_icon7);
        CalendarAddInsert_icon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);

                checked2 = 7;
            }
        });
        //?????????8 ??????
        CalendarAddInsert_icon8 = rootView.findViewById(R.id.CalendarAddInsert_icon8);
        CalendarAddInsert_icon8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#FFC107"));
                }  else if(checked1 == 4) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#4374D9"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);

                checked2 = 8;
            }
        });

        //?????? > ???????????? ?????? CalendarAdd??? ??????
        CalendarAddInsert_cancel = rootView.findViewById(R.id.CalendarAddInsert_cancel);
        CalendarAddInsert_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(4, null);
            }
        });

        //?????? > ???????????? CalendarAdd??? ??????
        CalendarAddInsert_ok = rootView.findViewById(R.id.CalendarAddInsert_ok);
        CalendarAddInsert_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected(getContext()) == true) {
                    calendar_date = select_date;
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
                    calendar_memo = CalendarAddInsert_memo.getText().toString();

                    petname = petAddDto.getPetname();
                    CalListInsert calListInsert = new CalListInsert(calendar_date, calendar_icon, calendar_memo,calendar_hour,calendar_minute, petname);
                    calListInsert.execute();

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


    @Override
    public void onStart(){
        super.onStart();

        CalendarAddInsert_memo.setText(null);
    }

    @Override
    public void onResume(){
        super.onResume();

        CalendarAddInsert_memo.setText(null);
    }

}