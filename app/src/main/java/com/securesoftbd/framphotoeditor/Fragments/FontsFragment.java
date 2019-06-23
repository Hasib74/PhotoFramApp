/*
package com.securesoftbd.framphotoeditor.Fragments;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.securesoftbd.framphotoeditor.Adepter.FontsAdepter;
import com.securesoftbd.framphotoeditor.R;

import java.util.ArrayList;

public class FontsFragment extends Fragment {

    ArrayList<String> fontList = new ArrayList<>();
    RecyclerView _fontsRecycuar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fonts, null);

        _fontsRecycuar = v.findViewById(R.id.fonts_recycular);
        _fontsRecycuar.setLayoutManager(new GridLayoutManager(getActivity(), 4,GridLayoutManager.VERTICAL,false));
       */
/* ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        _fontsRecycuar.addItemDecoration(itemDecoration);*//*




        _fontsRecycuar.setHasFixedSize(true);


        fontList.add("American Typewriter Regular.ttf");
        fontList.add("BASKE1.ttf");
        fontList.add("BASKE9.ttf");
        fontList.add("BASKE10.ttf");
        fontList.add("baskervi.ttf");
        fontList.add("baskerville bold bt.ttf");
        fontList.add("Baskerville Bold font.ttf");
        fontList.add("BaskervilleBoldBT.ttf");
        fontList.add("BaskervilleBoldItalicBT.ttf");
        fontList.add("BaskervilleBT.ttf");
        fontList.add("BaskervilleItalicBT.ttf");
        fontList.add("BASKRV_L.ttf");
        // fontList.add("BERNIERShade-Regular.ttf");
        fontList.add("BERNIER™ Distressed.ttf");
        fontList.add("BERNIER™ Regular.ttf");
        fontList.add("BERNIER™ Shade.ttf");
        fontList.add("BigshotOne.ttf");
        fontList.add("BRADHI.ttf");
        fontList.add("Chalkduster.ttf");
        fontList.add("Georgia.ttf");
        fontList.add("georgia bold.ttf");
        fontList.add("georgia bold italic.ttf");
        fontList.add("Georgia Bold Italic font.ttf");
        fontList.add("georgia italic.ttf");
        fontList.add("Georgia Regular font.ttf");
        //fontList.add("GEORGIA4.ttf");
        fontList.add("georgiab.ttf");
        fontList.add("georgiai.ttf");
        fontList.add("georgiaz.ttf");
        // fontList.add("Noteworthy-Lt.ttf");
        fontList.add("seriffic.ttf");


        _fontsRecycuar.setAdapter(new FontsAdepter(fontList, getActivity()));


        return v;
    }



}*/
