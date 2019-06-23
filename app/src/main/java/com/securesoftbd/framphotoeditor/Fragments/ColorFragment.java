/*
package com.securesoftbd.framphotoeditor.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.securesoftbd.framphotoeditor.R;
import com.securesoftbd.framphotoeditor.Utils.Common;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerView;

import java.util.ArrayList;
import java.util.List;

public class ColorFragment extends Fragment {

    ColorPickerView colorPickerView;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View v=inflater.inflate(R.layout.fragment_colors,null);

        colorPickerView=v.findViewById(R.id.colorPickerView);


        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {

                Common._current_color=colorEnvelope.getColor();
            }
        });


        return  v;
    }
}
*/
