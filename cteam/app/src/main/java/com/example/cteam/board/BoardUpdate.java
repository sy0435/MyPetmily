package com.example.cteam.board;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.Board.BoardDetailUpdate;
import com.example.cteam.ATask.ListUpdate;
import com.example.cteam.Dto.BoardDetailDTO;
import com.example.cteam.Dto.BoardinsertDTO;
import com.example.cteam.Login;
import com.example.cteam.R;

import java.io.File;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;

public class BoardUpdate extends AppCompatActivity {

    private static final String TAG = "BoardUpdate";
    public static BoardDetailDTO boardDetailDTO = null;

    String board_write_subject, board_write_region1, board_write_region2;
    String board_write_title, board_write_content;


    Spinner Uboard_write_subject;
    Spinner Uboard_write_region1;
    Spinner Uboard_write_region2;


    EditText Uboard_write_title, Uboard_write_content;
    Button Uboard_write_filebutton, Uboard_write_cancel, Uboard_write_submit;
    ImageView Uboard_write_image;

    ArrayAdapter<CharSequence> adspin1, adspin2;
    
    public String imagePath;
    public String pImgDbPathU;
    public String imageRealPathU = "", imageDbPathU = "";
    
    final int CAMERA_REQUEST = 1010;
    final int LOAD_IMAGE = 1011;

    File file = null;
    long fileSize = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write_update);

        Uboard_write_subject = findViewById(R.id.Uboard_write_subject);
        Uboard_write_region1 = findViewById(R.id.Uboard_write_region1);
        Uboard_write_region2 = findViewById(R.id.Uboard_write_region2);
        Uboard_write_title = findViewById(R.id.Uboard_write_title);
        Uboard_write_content = findViewById(R.id.Uboard_write_content);
        Uboard_write_filebutton = findViewById(R.id.Uboard_write_filebutton);
        Uboard_write_cancel = findViewById(R.id.Uboard_write_cancel);
        Uboard_write_submit = findViewById(R.id.Uboard_write_submit);
        Uboard_write_image = findViewById(R.id.Uboard_write_image);

        // 보내온 값 파싱
        Intent intent = getIntent();
        BoardDetailDTO boardDetailDTO = (BoardDetailDTO)intent.getSerializableExtra("boardDetailDTO");

        // 텍스트 삽입
        board_write_subject = boardDetailDTO.getBoard_subject();
        board_write_region1 = boardDetailDTO.getBoard_city();
        board_write_region2 = boardDetailDTO.getBoard_region();

        board_write_title = boardDetailDTO.getBoard_title();
        board_write_content = boardDetailDTO.getBoard_content();

        setspinner();

        // 스피너 아이템 세팅
        Log.d(TAG, "서브젝트 값 : " + board_write_subject);
        Uboard_write_subject.setSelection(getIndex(Uboard_write_subject, board_write_subject));
        Log.d(TAG, "주소1 값 : " + board_write_region1);
        Uboard_write_region1.setSelection(getIndex(Uboard_write_region1, board_write_region1));

        //Uboard_write_subject.setSelection(1);
        //Uboard_write_region1.setSelection(7);
        //Uboard_write_region2.getSelectedItem().toString();

        Uboard_write_title.setText(board_write_title);
        Uboard_write_content.setText(board_write_content);

        //사진수정
        imagePath = boardDetailDTO.getBoard_imagepath();
        pImgDbPathU = imagePath;
        imageDbPathU = pImgDbPathU;
        Uboard_write_image.setVisibility(View.VISIBLE);
        Glide.with(this).load(imagePath).into(Uboard_write_image);
        Uboard_write_filebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uboard_write_image.setVisibility(View.VISIBLE);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);
            }
        });

    }

    public void btnBoardUpdateSubmit(View view){
        if(isNetworkConnected(this) == true){
            if(fileSize <= 30000000) {  // 파일크기가 30메가 보다 작아야 업로드 할수 있음
                board_write_subject = Uboard_write_subject.getSelectedItem().toString();
                board_write_region1 = Uboard_write_region1.getSelectedItem().toString();
                board_write_region2 = Uboard_write_region2.getSelectedItem().toString();
                board_write_content = Uboard_write_content.getText().toString();
                board_write_title = Uboard_write_title.getText().toString();
                BoardDetailUpdate boardDetailUpdate = new BoardDetailUpdate(boardDetailDTO.getboard_num2(),board_write_subject, board_write_region1, board_write_region2
                        ,board_write_title, board_write_content, pImgDbPathU, imageDbPathU, imageRealPathU);
                boardDetailUpdate.execute();

                //Toast.makeText(getApplicationContext(), "수정성공", Toast.LENGTH_LONG).show();

                Intent showIntent = new Intent(getApplicationContext(), BoardDetail.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여 그 태스크안에 엑티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 엑티비티와 동일한 affinity(관계, 유사)를 가지고 있는 태스크가 있다면 그곳으로 새 엑티비티가 들어가게됩니다.
                        Intent.FLAG_ACTIVITY_SINGLE_TOP | // 엑티비티를 호출할 경우 호출된 엑티비티가 현재 태스크의 최상단에 존재하고 있었다면 새로운 인스턴스를 생성하지 않습니다. 예를 들어 ABC가 엑티비티 스택에 존재하는 상태에서 C를 호출하였다면 여전히 ABC가 존재하게 됩니다.
                        Intent.FLAG_ACTIVITY_CLEAR_TOP); // 만약에 엑티비티스택에 호출하려는 엑티비티의 인스턴스가 이미 존재하고 있을 경우에 새로운 인스턴스를 생성하는 것 대신에 존재하고 있는 엑티비티를 포그라운드로 가져옵니다. 그리고 엑티비티스택의 최상단 엑티비티부터 포그라운드로 가져올 엑티비티까지의 모든 엑티비티를 삭제합니다.
                startActivity(showIntent);

                finish();
            }else{
                // 알림창 띄움
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("알림");
                builder.setMessage("파일 크기가 30MB초과하는 파일은 업로드가 제한되어 있습니다.\n30MB이하 파일로 선택해 주십시요!!!");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }

        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void btnBoardUpdateCancel(View view){
        Log.d(TAG, "onCreate: count" + Uboard_write_region2.getCount());
        finish();
    }

    //private method of your class
    private int getIndex(Spinner spinner, String myString){

        for (int i=0;i<spinner.getCount();i++){
            Log.d(TAG, myString + "아이템 : " + spinner.getItemAtPosition(i));
            if (spinner.getItemAtPosition(i).toString().equals(myString)){
                return i;
            }
        }

        return 0;
    }
    
    private void setspinner() {
        final Spinner spin1 = (Spinner)findViewById(R.id.Uboard_write_region1);
        final Spinner spin2 = (Spinner)findViewById(R.id.Uboard_write_region2);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_region, android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin1.getItem(i).equals("서울특별시")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_seoul, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("부산광역시")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_busan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));

                }else if(adspin1.getItem(i).equals("대구광역시")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_daegu, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("인천광역시")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("광주광역시")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_gwangju, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));

                }else if(adspin1.getItem(i).equals("대전광역시")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_daejeon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("울산광역시")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_ulsan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("세종특별자치시")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_sejong, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("경기도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("강원도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_gangwon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("충청북도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_chung_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("충청남도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_chung_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("전라북도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_jeon_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("전라남도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_jeon_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("경상북도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_gyeong_buk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("경상남도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_gyeong_nam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }else if(adspin1.getItem(i).equals("제주특별자치도")) {
                    adspin2 = ArrayAdapter.createFromResource(BoardUpdate.this, R.array.spinner_region_jeju, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // 스피너 아이템 세팅
//        Log.d(TAG, "서브젝트 값 : " + board_write_subject);
//        Uboard_write_subject.setSelection(getIndex(Uboard_write_subject, board_write_subject));
//        Log.d(TAG, "주소1 값 : " + board_write_region1);
//        Uboard_write_region1.setSelection(getIndex(Uboard_write_region1, board_write_region1));

//        Log.d(TAG, "주소2 값 : " + board_write_region2);
//        Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
    }
    
    
    
}
