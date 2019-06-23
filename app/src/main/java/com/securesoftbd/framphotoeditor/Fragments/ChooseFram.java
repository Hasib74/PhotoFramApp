package com.securesoftbd.framphotoeditor.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.securesoftbd.framphotoeditor.Adepter.FramAdepter;
import com.securesoftbd.framphotoeditor.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChooseFram extends Fragment {

    ArrayList list=new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ImageView ManageButton;
        RelativeLayout ParticipantButton;
        RecyclerView fram_recycularView;
        FramAdepter framAdepter;

        View v=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_choose_fram,null);

        ManageButton=v.findViewById(R.id.button_manager);
        ParticipantButton=v.findViewById(R.id.button_participant);

        fram_recycularView=v.findViewById(R.id.recycular_frams);


        fram_recycularView.setHasFixedSize(true);
        fram_recycularView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        list.add(R.drawable.fram_1);
        list.add(R.drawable.fram_2);

        framAdepter=new FramAdepter(getContext(),list);

        fram_recycularView.setAdapter(framAdepter);



        return  v;
    }
}
