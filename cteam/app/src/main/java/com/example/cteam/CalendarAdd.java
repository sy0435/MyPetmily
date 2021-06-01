package com.example.cteam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.ATask.CalListDelete;
import com.example.cteam.ATask.CalListSelect;
import com.example.cteam.Adapter.CalendarAdapter;
import com.example.cteam.Dto.CalendarDTO;
import com.example.cteam.Dto.PetDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;
import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;

public class CalendarAdd extends Fragment {
    public static CalendarDTO selectIcon = null;

    PetSelect activity;
    int clicked=0;

    CalendarDTO dto;
    Bundle bundle = null;

    Button CalendarAdd_insert, CalendarAdd_update, CalendarAdd_delete, CalendarAdd_back;

    RecyclerView CalendarAdd_view;
    ArrayList<CalendarDTO> icons;
    CalendarAdapter adapter;
    String calendar_date;
    String petname;

    CalListSelect calListSelect;

    String select_date = "";

    Button button[] = new Button[24];

    Integer[] Rid_button = {

            R.id.barBtn0, R.id.barBtn1, R.id.barBtn2, R.id.barBtn3, R.id.barBtn4,

            R.id.barBtn5, R.id.barBtn6, R.id.barBtn7, R.id.barBtn8, R.id.barBtn9,

            R.id.barBtn10, R.id.barBtn11, R.id.barBtn12, R.id.barBtn13, R.id.barBtn14,

            R.id.barBtn15, R.id.barBtn16, R.id.barBtn17, R.id.barBtn18, R.id.barBtn19,

            R.id.barBtn20, R.id.barBtn21, R.id.barBtn22, R.id.barBtn23

    };

    Bundle sbundle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar_add, container, false);

        activity = (PetSelect) getActivity();

        //날짜 데이터 받는 부분
        if(activity.cBundle != null){
            bundle = activity.cBundle;
            select_date = bundle.getString("select_date");

        }

        //추가 클릭 > CalendarInsert로 이동
        CalendarAdd_insert = rootView.findViewById(R.id.CalendarAdd_insert);

        //리사이클러 뷰 셋팅
            icons = new ArrayList<>();
            adapter = new CalendarAdapter(getActivity(), icons);
            CalendarAdd_view = (RecyclerView) rootView.findViewById(R.id.CalendarAdd_view);
            //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            //CalendarAdd_view.setLayoutManager(layoutManager);
            CalendarAdd_view.setLayoutManager(new LinearLayoutManager(getActivity()));

           DividerItemDecoration dividerItemDecoration =
                    new DividerItemDecoration(CalendarAdd_view.getContext(),new LinearLayoutManager(getContext()).getOrientation());
//          // 리싸이클러뷰 구분선

           CalendarAdd_view.addItemDecoration(dividerItemDecoration);
             //구분선 추가

            RecyclerDecoration spaceDecoration = new RecyclerDecoration();
            CalendarAdd_view.addItemDecoration(spaceDecoration);


            //간격
            CalendarAdd_view.addItemDecoration(new RecyclerDecoration());


        CalendarAdd_view.setAdapter(adapter);



        //추가버튼 그냥 클릭시
        CalendarAdd_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "시간을 먼저 클릭하세요", Toast.LENGTH_SHORT).show();
            }
        });


        //데이터 불러옴
            calendar_date = select_date;
            petname = petAddDto.getPetname();
            if(isNetworkConnected(getActivity()) == true) {
                calListSelect = new CalListSelect(icons, adapter, calendar_date, petname);
                try {
                    calListSelect.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

            //바 버튼찾기
        for(int i=0;i<=23; i++){

            button[i] = rootView.findViewById(Rid_button[i]);

        }//버튼찾기







        for(int i=0; i<icons.size(); i++){

            if(icons.get(i).getCalendar_hour()!=null&&!icons.get(i).getCalendar_hour().equals("")) {
                int hour = Integer.parseInt(icons.get(i).getCalendar_hour().trim());
                //Toast.makeText(activity, hour, Toast.LENGTH_SHORT).show();
                if(hour==0||hour==12){
                    button[hour].setBackgroundResource(R.drawable.barbtnleftclicked);
                }else if(hour==11||hour==23){
                    button[hour].setBackgroundResource(R.drawable.barbtnrightclicked);
                }else{
                    button[hour].setBackgroundResource(R.drawable.barbtnclicked);

                }

            }

        }



        
        //버튼 클릭시
        for(int i=0; i<button.length; i++){

            final int INDEX;

            INDEX = i;



            button[INDEX].setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {


                    /*  if(INDEX==0||INDEX==12){
                    button[INDEX].setBackgroundResource(R.drawable.barbtnleftclicked);
                }else if(INDEX==11||INDEX==23){
                    button[INDEX].setBackgroundResource(R.drawable.barbtnrightclicked);
                    }else{
                    button[INDEX].setBackgroundResource(R.drawable.barbtnclicked);

                }*/

                    btnclicked();

                        button[INDEX].setSelected(true);

                  
                    CalendarAdd_insert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            
                            if(isNetworkConnected(getContext()) == true){
                                bundle.putInt("time",INDEX);
                                activity.onFragmentChange(5, bundle);

                            } else {
                                Toast.makeText(getContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });




                }

            });

        }//버튼클릭시


        //버튼막기
//        CalendarAdd_insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                return;
//            }
//        });


        //스와이프로 수정 삭제
        MySwipeHelper swipeHelper= new MySwipeHelper(getContext(),CalendarAdd_view,150) {
            @Override
            public void instantiatrMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {
                buffer.add(new MyButton(getContext(),
                        "Delete",
                        20,
                        R.drawable.trashcan,
                        Color.parseColor("#FF3C30"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                               // Toast.makeText(CalendarAdd.this, "Delete click", Toast.LENGTH_SHORT).show();
                                Log.d("TAG",viewHolder.getAdapterPosition()+"");
                                CalListDelete calListDelete = new CalListDelete(icons.get(viewHolder.getLayoutPosition()).getCalendar_id());
                                calListDelete.execute();
                                icons.remove(viewHolder.getAdapterPosition());                // 해당 항목 삭제
                                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());    // Adapter에 알려주기.

                                refresh();
                                adapter.notifyDataSetChanged();
                            }
                        }));
                buffer.add(new MyButton(getContext(),
                        "Update",
                        20,
                        R.drawable.modifypen,
                        Color.parseColor("#03DAC5"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {

                                   selectIcon= icons.get(viewHolder.getAdapterPosition());
                                    activity.onFragmentChange(6, null);

                            }
                        }));
            }
        };// swipeHelper
        //스와이프로 삭제
//        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//
//                if(isNetworkConnected(getContext()) == true) {
//
//                        CalListDelete calListDelete = new CalListDelete(icons.get(viewHolder.getLayoutPosition()).getCalendar_id());
//                        calListDelete.execute();
//
//                } else {
//                    Toast.makeText(getContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
//                }
//
//                icons.remove(viewHolder.getLayoutPosition());
//
//                adapter.notifyItemRemoved(viewHolder.getLayoutPosition());
//            }
//        };
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(CalendarAdd_view);




        //수정 > CalendarUpdate로 이동
        CalendarAdd_update = rootView.findViewById(R.id.CalendarAdd_update);
        CalendarAdd_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkConnected(getContext()) == true) {
                    //선택된 아이콘이 있을 때만 이동
                    if (selectIcon != null) {
                        //화면이동
                        //수정에 데이터 보내는 부분
                        /*sbundle = new Bundle();
                        sbundle.putSerializable("selectIcon", selectIcon);

                        Log.d("main:Calendaradd", "onClick: " + selectIcon.getCalendar_memo());*/

                        activity.onFragmentChange(6, null);
                    } else {
                        Toast.makeText(getContext(), "수정할 스케줄을 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //삭제
        CalendarAdd_delete = rootView.findViewById(R.id.CalendarAdd_delete);
        CalendarAdd_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected(getContext()) == true) {
                    //선택된 아이콘이 있을 때만 삭제
                    if(selectIcon != null){
                        CalListDelete calListDelete = new CalListDelete(selectIcon.getCalendar_id());
                        calListDelete.execute();
                        // 화면갱신
                        refresh();
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "삭제할 스케줄을 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //뒤로가기 > CalendarAdd로 이동
        CalendarAdd_back = rootView.findViewById(R.id.CalendarAdd_back);
        CalendarAdd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(3, null);
            }
        });

        /*
        //뒤로가기 > 화면 이동 X 화면 종료 O
        CalendarAdd_back = rootView.findViewById(R.id.CalendarAdd_back);
        CalendarAdd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(CalendarAdd.this).commit();
                fragmentManager.popBackStack();
            }
        });
        */

        return rootView;

    } //onCreateView()

    //새로고침
    private void refresh() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    } //refresh()

    private void btnclicked(){

        for(int j=0; j<24; j++){
            button[j].setSelected(false);
        }

    }

}