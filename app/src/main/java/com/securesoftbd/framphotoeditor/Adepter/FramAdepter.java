package com.securesoftbd.framphotoeditor.Adepter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.securesoftbd.framphotoeditor.Home;
import com.securesoftbd.framphotoeditor.R;
import com.securesoftbd.framphotoeditor.Utils.Common;

import java.util.ArrayList;
import java.util.List;

public class FramAdepter  extends RecyclerView.Adapter<FramAdepter.FramViewHolder> {
    Context context;
    ArrayList fram_list;

    public FramAdepter(Context context, ArrayList fram_list) {
        this.context = context;
        this.fram_list = fram_list;
    }

    @NonNull
    @Override
    public FramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_fram_card,null);
        return new FramViewHolder(v);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull FramViewHolder framViewHolder,final int i) {
        Log.i("Check_id", "onBindViewHolder: "+fram_list.get(i));
        framViewHolder.imageView.setImageResource((Integer) fram_list.get(i));

        framViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common._current_fram= (int) fram_list.get(i);

                Intent in=new Intent(context, Home.class);
                in.putExtra("task",3);
                context.startActivity(in);

              Common._color=getDominantColor(BitmapFactory.decodeResource(context.getResources(),
                      (Integer) fram_list.get(i)));



            }
        });

    }

    public int getDominantColor(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        final int color = newBitmap.getPixel(1, 1);
        newBitmap.recycle();
        return color;
    }

    @Override
    public int getItemCount() {
        return fram_list.size();
    }

    public class FramViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public FramViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);

        }
    }
}
