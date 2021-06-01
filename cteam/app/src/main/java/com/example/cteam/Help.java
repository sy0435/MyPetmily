package com.example.cteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.HelpAdapter;
import com.example.cteam.PetSelect;
import com.example.cteam.R;

import java.util.ArrayList;
import java.util.List;

public class Help extends Fragment {
    PetSelect activity;
    private RecyclerView recyclerview;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_help,container,false);

        activity = (PetSelect) getActivity();

        recyclerview = rootView.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        List<HelpAdapter.Item> data = new ArrayList<>();

        HelpAdapter.Item list1 = new HelpAdapter.Item(HelpAdapter.HEADER, "반려동물은 어디서 데려오나요?");
        list1.invisibleChildren  = new ArrayList<>();
        list1.invisibleChildren .add(new HelpAdapter.Item(HelpAdapter.CHILD,
                "사지 말고 입양하세요"));
        data.add(list1);

        HelpAdapter.Item list2 = new HelpAdapter.Item(HelpAdapter.HEADER, "어플의 사용법");
        list2.invisibleChildren  = new ArrayList<>();
        list2.invisibleChildren .add(new HelpAdapter.Item(HelpAdapter.CHILD, "보다 보면 알아요"));
        data.add(list2);

        HelpAdapter.Item list3 = new HelpAdapter.Item(HelpAdapter.HEADER, "다운로드 받는 법");
        list3.invisibleChildren  = new ArrayList<>();
        list3.invisibleChildren .add(new HelpAdapter.Item(HelpAdapter.CHILD, "플레이스토어로 가서 받아요"));
        data.add(list3);

        recyclerview.setAdapter(new HelpAdapter(data));

        return rootView;
    }
}
