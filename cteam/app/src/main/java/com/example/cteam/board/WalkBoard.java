package com.example.cteam.board;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cteam.ATask.Board.BoardselectList;
import com.example.cteam.Adapter.BoardAdapter;
import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.Dto.BoardDetailDTO;
import com.example.cteam.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;
import static com.example.cteam.R.array.board_SpinnerArray;

public class WalkBoard extends Fragment {
    public static BoardDTO boardDTO = null;
    public static BoardDetailDTO boardDetailDTO = null;

    Spinner spinner_board, spinner_City, spinner_Sigungu;
    ArrayAdapter<CharSequence> City_spinner, Sigungu_spinner;

    //ATask
    BoardselectList boardselectList;
    //DTO
    ArrayList<BoardDTO> myItemArrayList;

    private ArrayList<BoardDTO> myItemArrayListCopy;

    //도시와 구
    String city="";
    String sigungu="";
    //도시+구(검색할때 사용)
    String city2="";

    //검색어
    String text="";
    //게시판여부
    String board_kind="";


    //RecyclerView
    RecyclerView recyclerView;
    //Adapter
    BoardAdapter boardAdapter;


    EditText board_editsearch;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_walk_board,
                container, false);

        spinner_board = rootView.findViewById(R.id.board_spinner_board);
        ArrayList<String> list = new ArrayList<>();

        board_editsearch=rootView.findViewById(R.id.board_editsearch);

        //게시판 선택창
        ArrayAdapter spinnerAdapter =
                ArrayAdapter.createFromResource(rootView.getContext(), board_SpinnerArray,
                        android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_board.setAdapter(spinnerAdapter);

        //도시 선택
        spinner_City = rootView.findViewById(R.id.board_spinnercity);
        spinner_Sigungu = rootView.findViewById(R.id.board_spinnersigungu);
        Button board_search = rootView.findViewById(R.id.board_search);
        City_spinner = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.spinner_region, android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(City_spinner);


        //전체보기 or 나눔게시판
        spinner_board.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (spinner_board.getSelectedItem().equals("전체보기")) {
//                    board_kind="전체보기";
//                    search(text,board_kind,city2);
//                }else if(spinner_board.getSelectedItem().equals("산책게시판")){
//                    board_kind="산책게시판";
//                    search(text,board_kind,city2);
//                }else{
//                    board_kind="나눔게시판";
//                    search(text,board_kind,city2);
//                }
                board_kind=spinner_board.getSelectedItem().toString();

                if(board_kind.equals("전체보기")){
                    City_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region, android.R.layout.simple_spinner_dropdown_item);
                    City_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_City.setAdapter(City_spinner);
                }
                Log.d("board_kind", "onItemSelected: "+board_kind);
                search(text,board_kind,city2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });






        spinner_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (City_spinner.getItem(i).equals("서울특별시")) {

                    //도시설정
                    city="서울특별시";

                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_seoul, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                }else if(City_spinner.getItem(i).equals("전체보기")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_all, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="전체보기";
                            sigungu="전체보기";

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(City_spinner.getItem(i).equals("부산광역시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_busan, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            city="부산광역시";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);



                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("대구광역시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_daegu, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            city="대구광역시";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("인천광역시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_incheon, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            city="인천광역시";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("광주광역시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_gwangju, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="광주광역시";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("대전광역시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_daejeon, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="대전광역시";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("울산광역시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_ulsan, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="울산광역시";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("세종특별자치시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_sejong, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="세종특별자치시";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("경기도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="경기도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("강원도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_gangwon, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="강원도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("충청북도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_chung_buk, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="충청북도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("충청남도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_chung_nam, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="충청남도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("전라북도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_jeon_buk, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="전라북도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("전라남도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_jeon_nam, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="전라남도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("경상북도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_gyeong_buk, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="경상북도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("경상남도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_gyeong_nam, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="경상남도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if(City_spinner.getItem(i).equals("제주특별자치도")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_jeju, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            city="제주특별자치도";
                            sigungu=Sigungu_spinner.getItem(i).toString();
                            Log.d("sigungu", "onItemSelected: "+Sigungu_spinner.getItem(i).toString());

                            city2=city+sigungu;
                            Log.d("city2", "onItemSelected: "+city2);
                            search(text,board_kind,city2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }




            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        // 게시판 리사이클러 뷰 찾기
        recyclerView = rootView.findViewById(R.id.board_list);

        // 리사이클러뷰 어댑터 선언
        myItemArrayList = new ArrayList<>();

        boardAdapter = new BoardAdapter(rootView.getContext(), myItemArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext(),
                RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(boardAdapter);

        if(isNetworkConnected(rootView.getContext()) == true){
            boardselectList = new BoardselectList(myItemArrayList, boardAdapter);
            try {
                boardselectList.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(rootView.getContext(), "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }



        //리스트 복사
        myItemArrayListCopy= new ArrayList<BoardDTO>();
        myItemArrayListCopy.addAll(myItemArrayList);


      /*  spinner_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (City_spinner.getItem(i).equals("서울특별시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_seoul, android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Sigungu.setAdapter(Sigungu
        */


        board_editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                text = board_editsearch.getText().toString();
                Log.d(text, "afterTextChanged: "+text);
                search(text,board_kind,city2);
            }
        });




        // 임의의 데이터 만들기 (이 부분에서 게시판 정보를 가져와야 함)
//        BoardDTO dto = new BoardDTO();
//        dto.setTitle("글제목");
//        dto.setId("아이디");
//        dto.setDate("20200822");
//        dto.setComment("8개");
//
//        BoardDTO dto2 = new BoardDTO();
//        dto.setTitle("글제목2임");
//        dto.setId("현열");
//        dto.setDate("54654654");
//        dto.setComment("99개");
//
//        ArrayList<BoardDTO> bList = new ArrayList<>();
//        bList.add(dto);
//        bList.add(dto2);


        //글등록 플로팅액션버튼 → 글쓰기 Activity로 이동
        FloatingActionButton fab = rootView.findViewById(R.id.board_write);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(),BoardWrite.class);
                startActivity(intent);
            }
        });
        return rootView;



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("WalkBoard이동", "오류??");

        if ((requestCode == 100) && (resultCode == Activity.RESULT_OK)) {
            // recreate your fragment here
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            Log.d("WalkBoard새로고침", "오류?");

        }
        if ((requestCode == 101) && (resultCode == Activity.RESULT_OK)) {
            // recreate your fragment here
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
        if ((requestCode == 102) && (resultCode == Activity.RESULT_OK)) {
            // recreate your fragment here
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
    }


    // 검색을 수행하는 메소드
    public void search(String charText,String board_kind,String city2) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        myItemArrayList.clear();

        // 게시판 종류가 전체보기일 때는 모든 데이터를 보여준다.
        if (board_kind.equals("전체보기") ) {

            if(city2.equals("전체보기전체보기")) {

                if (charText.length() == 0) {   //if1-if2 전체보기 & 도시가 전체보기 & 글자 "" 일때

                    myItemArrayList.addAll(myItemArrayListCopy); //모든 목록


                }//if1-if2 전체보기 & 도시가 전체보기 & 글자 "" 일때
                else {

                    for(int i = 0;i < myItemArrayListCopy.size(); i++)
                    {
                        // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                        if (myItemArrayListCopy.get(i).getTitle().toLowerCase().contains(charText))
                        {
                            // 검색된 데이터를 리스트에 추가한다.
                            myItemArrayList.add(myItemArrayListCopy.get(i));
                        }
                    }

                    // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
                    boardAdapter.notifyDataSetChanged();


                }//if1-else2 전체보기 & 도시가 전체보기 & 글자 "" 가 아닐때

            }//if1-if1 전체보기 & 도시가 전체보기 일때

            else{//if1-else1 전체보기 & 도시가 전체보기가 아닐때

                if(charText.length() == 0){// 전체보기 & 도시가 전체보기가 아닐때 & 글자 안썼을때
                    for(int i = 0;i < myItemArrayListCopy.size(); i++)
                    {
                        // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                        if (myItemArrayListCopy.get(i).getCity2().toLowerCase().contains(city2))
                        {
                            // 검색된 데이터를 리스트에 추가한다.
                            myItemArrayList.add(myItemArrayListCopy.get(i));
                        }
                    }

                    // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
                    boardAdapter.notifyDataSetChanged();

                }else{// 전체보기 & 도시가 전체보기가 아닐때 & 글자 썼을때
                    for(int i = 0;i < myItemArrayListCopy.size(); i++)
                    {
                        // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                        if (myItemArrayListCopy.get(i).getCity2().toLowerCase().contains(city2)&&
                                myItemArrayListCopy.get(i).getTitle().toLowerCase().contains(charText) )
                        {
                            // 검색된 데이터를 리스트에 추가한다.
                            myItemArrayList.add(myItemArrayListCopy.get(i));
                        }
                    }

                    // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
                    boardAdapter.notifyDataSetChanged();


                }


            }//if1-else1 전체보기 & 도시가 전체보기가 아닐때

        }//if1 전체보기 일때

        else
        //게시판이 전체보기가 아닐때(나눔 or 산책)- 게시판 선택했을때
        {


            if( !city2.equals("전체보기전체보기") ){


                if( charText.length() > 0 ){//게시판 종류선택했을때 && 도시와 구 선택했을때 && 글썼을때

                    for(int i = 0;i < myItemArrayListCopy.size(); i++)
                    {
                        // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                        if (myItemArrayListCopy.get(i).getSubject().toLowerCase().contains(board_kind)
                                &&myItemArrayListCopy.get(i).getCity2().toLowerCase().contains(city2)
                                &&myItemArrayListCopy.get(i).getTitle().toLowerCase().contains(charText))
                        {
                            // 검색된 데이터를 리스트에 추가한다.
                            Log.d("search", "search: "+myItemArrayListCopy.get(i).getCity2()+","+city2);

                            myItemArrayList.add(myItemArrayListCopy.get(i));
                        }
                    }

                    // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
                    boardAdapter.notifyDataSetChanged();

                }else {//게시판 종류선택했을때 && 도시와 구 선택했을때 && 글X

                    for (int i = 0; i < myItemArrayListCopy.size(); i++) {
                        // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                        if (myItemArrayListCopy.get(i).getSubject().toLowerCase().contains(board_kind)
                                && myItemArrayListCopy.get(i).getCity2().toLowerCase().contains(city2)) {
                            // 검색된 데이터를 리스트에 추가한다.
                            myItemArrayList.add(myItemArrayListCopy.get(i));
                        }
                    }

                    // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
                    boardAdapter.notifyDataSetChanged();


                }

            }else{ //게시판 종류만 선택했을때, 도시선택X

                if(charText.length() > 0){//게시판 종류선택했을때 && 도시와 구 X && 글만 썼을때
                    for(int i = 0;i < myItemArrayListCopy.size(); i++)
                    {
                        // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                        if (myItemArrayListCopy.get(i).getSubject().toLowerCase().contains(board_kind)
                                &&myItemArrayListCopy.get(i).getTitle().toLowerCase().contains(charText))
                        {
                            // 검색된 데이터를 리스트에 추가한다.
                            myItemArrayList.add(myItemArrayListCopy.get(i));
                        }
                    }

                    // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
                    boardAdapter.notifyDataSetChanged();
                }else{//게시판 종류선택했을때 && 도시와 구 X && 글X

                    for(int i = 0;i < myItemArrayListCopy.size(); i++)
                    {
                        // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                        if (myItemArrayListCopy.get(i).getSubject().toLowerCase().contains(board_kind))

                        {
                            Log.d("subject", "search: subject "+myItemArrayListCopy.get(i).getSubject()+","+board_kind);
                            // 검색된 데이터를 리스트에 추가한다.
                            myItemArrayList.add(myItemArrayListCopy.get(i));
                        }
                    }

                    // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
                    boardAdapter.notifyDataSetChanged();


                }


            }


        }//else1 전체보기가 아닐때

        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        boardAdapter.notifyDataSetChanged();
    }


}