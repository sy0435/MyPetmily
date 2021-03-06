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

        // ????????? ??? ??????
        Intent intent = getIntent();
        BoardDetailDTO boardDetailDTO = (BoardDetailDTO)intent.getSerializableExtra("boardDetailDTO");

        // ????????? ??????
        board_write_subject = boardDetailDTO.getBoard_subject();
        board_write_region1 = boardDetailDTO.getBoard_city();
        board_write_region2 = boardDetailDTO.getBoard_region();

        board_write_title = boardDetailDTO.getBoard_title();
        board_write_content = boardDetailDTO.getBoard_content();

        setspinner();

        // ????????? ????????? ??????
        Log.d(TAG, "???????????? ??? : " + board_write_subject);
        Uboard_write_subject.setSelection(getIndex(Uboard_write_subject, board_write_subject));
        Log.d(TAG, "??????1 ??? : " + board_write_region1);
        Uboard_write_region1.setSelection(getIndex(Uboard_write_region1, board_write_region1));

        //Uboard_write_subject.setSelection(1);
        //Uboard_write_region1.setSelection(7);
        //Uboard_write_region2.getSelectedItem().toString();

        Uboard_write_title.setText(board_write_title);
        Uboard_write_content.setText(board_write_content);

        //????????????
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
            if(fileSize <= 30000000) {  // ??????????????? 30?????? ?????? ????????? ????????? ?????? ??????
                board_write_subject = Uboard_write_subject.getSelectedItem().toString();
                board_write_region1 = Uboard_write_region1.getSelectedItem().toString();
                board_write_region2 = Uboard_write_region2.getSelectedItem().toString();
                board_write_content = Uboard_write_content.getText().toString();
                board_write_title = Uboard_write_title.getText().toString();
                BoardDetailUpdate boardDetailUpdate = new BoardDetailUpdate(boardDetailDTO.getboard_num2(),board_write_subject, board_write_region1, board_write_region2
                        ,board_write_title, board_write_content, pImgDbPathU, imageDbPathU, imageRealPathU);
                boardDetailUpdate.execute();

                //Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();

                Intent showIntent = new Intent(getApplicationContext(), BoardDetail.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // ??? ???????????? ???????????? ???????????? ??????????????? ???????????? ?????? ????????? ???????????? ???????????? ??? ??????????????? ??????????????? ???????????? ?????????. ???, ????????? ???????????? ?????????????????? ??????????????? ??????????????? ????????? affinity(??????, ??????)??? ????????? ?????? ???????????? ????????? ???????????? ??? ??????????????? ?????????????????????.
                        Intent.FLAG_ACTIVITY_SINGLE_TOP | // ??????????????? ????????? ?????? ????????? ??????????????? ?????? ???????????? ???????????? ???????????? ???????????? ????????? ??????????????? ???????????? ????????????. ?????? ?????? ABC??? ???????????? ????????? ???????????? ???????????? C??? ?????????????????? ????????? ABC??? ???????????? ?????????.
                        Intent.FLAG_ACTIVITY_CLEAR_TOP); // ????????? ????????????????????? ??????????????? ??????????????? ??????????????? ?????? ???????????? ?????? ????????? ????????? ??????????????? ???????????? ??? ????????? ???????????? ?????? ??????????????? ?????????????????? ???????????????. ????????? ????????????????????? ????????? ?????????????????? ?????????????????? ????????? ????????????????????? ?????? ??????????????? ???????????????.
                startActivity(showIntent);

                finish();
            }else{
                // ????????? ??????
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("??????");
                builder.setMessage("?????? ????????? 30MB???????????? ????????? ???????????? ???????????? ????????????.\n30MB?????? ????????? ????????? ????????????!!!");
                builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }

        }else {
            Toast.makeText(this, "???????????? ???????????? ?????? ????????????.",
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
            Log.d(TAG, myString + "????????? : " + spinner.getItemAtPosition(i));
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
                if (adspin1.getItem(i).equals("???????????????")) {
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
                }else if(adspin1.getItem(i).equals("???????????????")) {
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

                }else if(adspin1.getItem(i).equals("???????????????")) {
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
                }else if(adspin1.getItem(i).equals("???????????????")) {
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
                }else if(adspin1.getItem(i).equals("???????????????")) {
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

                }else if(adspin1.getItem(i).equals("???????????????")) {
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
                }else if(adspin1.getItem(i).equals("???????????????")) {
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
                }else if(adspin1.getItem(i).equals("?????????????????????")) {
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
                }else if(adspin1.getItem(i).equals("?????????")) {
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
                }else if(adspin1.getItem(i).equals("?????????")) {
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
                }else if(adspin1.getItem(i).equals("????????????")) {
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
                }else if(adspin1.getItem(i).equals("????????????")) {
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
                }else if(adspin1.getItem(i).equals("????????????")) {
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
                }else if(adspin1.getItem(i).equals("????????????")) {
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
                }else if(adspin1.getItem(i).equals("????????????")) {
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
                }else if(adspin1.getItem(i).equals("????????????")) {
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
                }else if(adspin1.getItem(i).equals("?????????????????????")) {
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

        // ????????? ????????? ??????
//        Log.d(TAG, "???????????? ??? : " + board_write_subject);
//        Uboard_write_subject.setSelection(getIndex(Uboard_write_subject, board_write_subject));
//        Log.d(TAG, "??????1 ??? : " + board_write_region1);
//        Uboard_write_region1.setSelection(getIndex(Uboard_write_region1, board_write_region1));

//        Log.d(TAG, "??????2 ??? : " + board_write_region2);
//        Uboard_write_region2.setSelection(getIndex(Uboard_write_region2, board_write_region2));
    }
    
    
    
}
