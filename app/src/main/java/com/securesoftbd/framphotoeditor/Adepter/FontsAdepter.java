/*
package com.securesoftbd.framphotoeditor.Adepter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.securesoftbd.framphotoeditor.R;
import com.securesoftbd.framphotoeditor.Utils.Common;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FontsAdepter extends RecyclerView.Adapter<FontsAdepter.FontsViewHolder> {

    ArrayList<String> stringArrayList;
    Context context;

    public FontsAdepter(ArrayList<String> stringArrayList, Context context) {
        this.stringArrayList = stringArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FontsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fonts_view,null);
        return new FontsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FontsViewHolder fontsViewHolder, final int i) {
        fontsViewHolder.font_text.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/"+stringArrayList.get(i)));

        fontsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common._current_fonts="fonts/"+stringArrayList.get(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public class FontsViewHolder extends RecyclerView.ViewHolder {
        TextView font_text;
        public FontsViewHolder(@NonNull View itemView) {
            super(itemView);
            font_text=itemView.findViewById(R.id.fonst_text);
        }
    }
}
*/
