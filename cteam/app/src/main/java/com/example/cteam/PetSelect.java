package com.example.cteam;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cteam.Adapter.PetPhotoAdapter;
import com.example.cteam.Dto.CalendarDTO;
import com.example.cteam.Dto.MemberDTO;
import com.example.cteam.board.BoardWrite;
import com.example.cteam.board.WalkBoard;
import com.example.cteam.Help;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;


public class PetSelect extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "PetSelect";
    public static MemberDTO dto = null;
    WalkBoard walkBoard;
    FindStore findStore;
    Iot iot;
    PetPhoto petPhoto;
    Toolbar toolbar;
    MyPage myPage;
    Help help;
    Logout logout;
    PetChoose petChoose;
    Pw_MyPage_Fragment pw_myPage_fragment;
    MyPosting myPosting;
    // MemberDTO dto;
    Bundle mBundle = null;

    //캘린더 - 프래그먼트 선언
    Calendar calendar;
    CalendarAdd calendarAdd;
    CalendarAddInsert calendarAddInsert;
    CalendarAddUpdate calendarAddUpdate;

    //캘린더 - IconDTO
    public static CalendarDTO cdto = null;
    Bundle cBundle = null;

    Bundle sBundle=null;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_select);
        calendar=new Calendar();
        walkBoard = new WalkBoard();
        petPhoto = new PetPhoto();
        findStore = new FindStore();
        iot = new Iot();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        myPage = new MyPage();
        petChoose = new PetChoose();
        pw_myPage_fragment = new Pw_MyPage_Fragment();
        myPage = new MyPage();
        help = new Help();
        logout = new Logout();
        myPosting = new MyPosting();

        //캘린더 - 프래그먼트 객체 생성
        //calendar = (com.example.cteam.Calendar) getSupportFragmentManager().findFragmentById(R.id.calendar);
        calendar = new Calendar();
        calendarAdd = new CalendarAdd();
        calendarAddInsert = new CalendarAddInsert();
        calendarAddUpdate = new CalendarAddUpdate();

        //MemberDTO
        //dto = new MemberDTO("jamong", "자몽", "0000", "보물 1호?", "나","010-0000-0000");

        //frameLayout 초기화
        getSupportFragmentManager().beginTransaction().replace(R.id.container, petChoose).commit();

        //드로워에 로그인 정보 표시하기
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //String loginID = "자몽";
        View headerView = navigationView.getHeaderView(0);
        ImageView imageView = headerView.findViewById(R.id.profile);
        TextView tvloginID = headerView.findViewById(R.id.user_id);
        TextView tvloginName = headerView.findViewById(R.id.user_name);
        tvloginID.setText(loginDTO.getMember_id());
        tvloginName.setText(petAddDto.getPetname());

        Glide.with(this).load(petAddDto.getPetimage_path()).into(imageView);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, calendar).commit(); //첫화면에 프래그먼트 1이 나오게

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tab1 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,calendar).commit();
                        break;
                    case R.id.tab2 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, iot).commit();
                        break;
                    case R.id.tab3 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,petPhoto).commit();
                        break;
                    case R.id.tab4 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, walkBoard).commit();
                        break;
                }

                return true;
            }
        });//선택됐을때

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //선택된 아이디를 얻는다
        int id = menuItem.getItemId();

        if(id == R.id.nav_myPage){
            Bundle bundle = new Bundle();
            //bundle.putSerializable("memberdto", dto);

            onFragmentSelected(0, null);
        }else if(id == R.id.nav_help){
            onFragmentSelected(1, null);
        }else if(id == R.id.nav_logout){
            onFragmentSelected(2, null);
        }else if(id == R.id.nav_pet){
            Intent intent = new Intent(this, PetAdd.class);
            startActivity(intent);
            petAddDto = null;
        }else if(id == R.id.nav_board){
            onFragmentSelected(3, null);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);        //메뉴 누른뒤에 사라지는 것(스타트상태로)
        return true;
    }

    //프레그먼트가 선택 되었을 때 화면전환
    //번들은 보낼 데이터가 있을때 사용
    public void onFragmentSelected(int position, Bundle bundle){
        Fragment curFragment = null;

        if (position == 0){
            mBundle = bundle;
            curFragment = pw_myPage_fragment;
        }else if (position == 1){
            curFragment = help;
        }else if (position == 2) {
            curFragment = logout;
        }else if (position == 3) {
            curFragment = myPosting;
        }/*else if (position == 3){
            Intent intent = new Intent(this, PetAdd.class);
            startActivity(intent);

        }*/
        getSupportFragmentManager().beginTransaction().replace(R.id.container,curFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void onFragmentChange(int state, Bundle bundle){
        if(state == 1){
            mBundle = bundle;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, myPage).commit();
        }else if(state == 2){
            mBundle = bundle;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, petChoose).commit();
        } else if (state == 3) {
            cBundle = bundle;
            getSupportFragmentManager().beginTransaction().replace(R.id.container, calendar).commit();
        } else if (state == 4) {
            cBundle = bundle;
            getSupportFragmentManager().beginTransaction().replace(R.id.container, calendarAdd).commit();
        } else if (state == 5) {
            cBundle = bundle;
            getSupportFragmentManager().beginTransaction().replace(R.id.container, calendarAddInsert).commit();
        } else if (state == 6) {
            //cBundle = bundle;
            sBundle = bundle;
            getSupportFragmentManager().beginTransaction().replace(R.id.container, calendarAddUpdate).commit();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);

        Log.d("main:petselect", "onNewIntent: 11111");
        if(intent.getStringExtra("fragment").equals("petphotoupdate") ) {
            petPhoto.onActivityResult(10001, RESULT_OK, intent);
        }

        if(intent.getStringExtra("fragment").equals("BoardDetail") ) {
            walkBoard.onActivityResult(101, RESULT_OK, intent);
        }

        if(intent.getStringExtra("fragment").equals("Boardwrite") ) {
            Log.d("WalkBoard이동", "오류?");
            walkBoard.onActivityResult(100, RESULT_OK, intent);
        }

        if(intent.getStringExtra("fragment").equals("BoardWrite") ) {
            Log.d("WalkBoard이동", "오류?");
            walkBoard.onActivityResult(102, RESULT_OK, intent);
        }


    }
}