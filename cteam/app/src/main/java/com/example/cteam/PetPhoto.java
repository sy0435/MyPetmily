package com.example.cteam;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cteam.ATask.PetPhoto.PetPhotoListSelect;
import com.example.cteam.Adapter.PetPhotoAdapter;
import com.example.cteam.Dto.PetPhotoDTO;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;
import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;

public class PetPhoto extends Fragment {

    public static PetPhotoDTO selectPetPhoto = null;
    private Context context;

    ImageButton photoPlus, photoMinus;

    TextView petPhotoTitle;
    RecyclerView petPhoto_View;
    ArrayList<PetPhotoDTO> petPhotos;
    PetPhotoAdapter petPhotoAdapter;

    PetPhotoListSelect petPhotoListSelect;

    //java & xml ??????
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pet_photo, container, false);

        Log.d("main:petphoto", "onCreateView: ????????? ");

        checkDangerousPermissions();
        //??????????????? ????????????
       // context=container.getContext();

        //??????????????? ??? ??????
        petPhotos = new ArrayList<>();
        petPhotoAdapter = new PetPhotoAdapter(getActivity(), petPhotos);
        petPhoto_View = (RecyclerView) root.findViewById(R.id.petPhotoRecyclerView);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //CalendarAdd_view.setLayoutManager(layoutManager);
      //  petPhoto_View.setLayoutManager(new LinearLayoutManager(getActivity()));


        //pet???????????? ????????? ??????
        petPhotoTitle=root.findViewById(R.id.petPhotoTitle);
        petPhotoTitle.setText(petAddDto.getPetname()+"??? ??????");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                petPhoto_View.VERTICAL, false);
        petPhoto_View.setLayoutManager(layoutManager);


//        DividerItemDecoration dividerItemDecoration =
//                new DividerItemDecoration(petPhoto_View.getContext(),new LinearLayoutManager(getContext()).getOrientation());
////          // ?????????????????? ?????????
//
//        petPhoto_View.addItemDecoration(dividerItemDecoration);
//        //????????? ??????
//
//        RecyclerDecoration spaceDecoration = new RecyclerDecoration();
//        petPhoto_View.addItemDecoration(spaceDecoration);
//
//
//        //??????
//        petPhoto_View.addItemDecoration(new RecyclerDecoration());
//
//
        petPhoto_View.setAdapter(petPhotoAdapter);







        //????????????????????? ??? ???????????? Activity??? ??????
        FloatingActionButton petPhoto_floating = root.findViewById(R.id.petPhoto_floating);
        petPhoto_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getActivity(), PetPhotoInsert.class);
              //  intent.putExtra("adpater", (Serializable) petPhotoAdapter);

                startActivityForResult(intent,10001);


            }
        });





        //????????? ?????????

        String pet_name= petAddDto.getPetname();
        String member_id= loginDTO.getMember_id();

            //Atask ??????
        if(isNetworkConnected(getActivity()) == true) {
            petPhotoListSelect = new PetPhotoListSelect(petPhotos, petPhotoAdapter, pet_name,member_id);
            try {
                petPhotoListSelect.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "???????????? ???????????? ?????? ????????????.",
                    Toast.LENGTH_SHORT).show();
        }


        return root;
    }


    //????????????
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 10001) && (resultCode == Activity.RESULT_OK)) {
            // recreate your fragment here
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
    }



    private void checkDangerousPermissions() {
        String[] permissions = {

                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CAMERA

        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(getActivity(), permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // Toast.makeText(this, "?????? ??????", Toast.LENGTH_LONG).show();
        } else {
            // Toast.makeText(this, "?????? ??????", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[0])) {
               // Toast.makeText(this, "?????? ?????? ?????????.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions, 1);
            }
        }
    }







    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    //  Toast.makeText(this, permissions[i] + " ????????? ?????????.", Toast.LENGTH_LONG).show();
                } else {
                    // Toast.makeText(this, permissions[i] + " ????????? ???????????? ??????.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}