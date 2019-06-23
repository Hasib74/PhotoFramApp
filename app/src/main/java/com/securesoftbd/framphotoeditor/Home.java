package com.securesoftbd.framphotoeditor;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.securesoftbd.framphotoeditor.Fragments.ChooseFram;
import com.securesoftbd.framphotoeditor.Fragments.PhotoEditorWithFrame;
import com.securesoftbd.framphotoeditor.Fragments.Saved;
import com.securesoftbd.framphotoeditor.Fragments.SelectImage;
import com.securesoftbd.framphotoeditor.Utils.Common;

public class Home extends AppCompatActivity {

    RelativeLayout border1,border2,border3,border4;
    RelativeLayout backgroundColor1,backgroundColor2,backgroundColor3,backgroundColor4,mainLayout;

    TextView text1,text2,text3,text4;
    String namebox;

    int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        initialize();
        initial_view();

        //counting_button_click();

        step=getIntent().getIntExtra("task",0);
        namebox=getIntent().getStringExtra("namebox");

        complete_stape(step);


        if (step==100){

            Snackbar snackbar = Snackbar
                    .make(mainLayout, "Successfully saved to "+getIntent().getStringExtra("image_path"), Snackbar.LENGTH_LONG);
            snackbar.show();


        }


    }

    private void complete_stape(int step) {
        switch (step){
            case 2:
                change_button_colors(2);
                replace_fragment(new ChooseFram());
            break;
            case 3:
                change_button_colors(3);
                replace_fragment(new PhotoEditorWithFrame());
            break;
            case 4:
                change_button_colors(4);
                replace_fragment(new Saved());
                break;

        }
    }


    private void initial_view() {
        change_button_colors(1);


        replace_fragment(new SelectImage());


    }



    private void change_button_colors(int i) {
        switch (i){
            case 1:
                border1.setBackgroundColor(Color.WHITE);
                backgroundColor1.setBackgroundColor(Color.BLACK);
                text1.setTextColor(Color.WHITE);

                border2.setBackgroundColor(Color.WHITE);
                backgroundColor2.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text2.setTextColor(Color.BLACK);

                border3.setBackgroundColor(Color.WHITE);
                backgroundColor3.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text3.setTextColor(Color.BLACK);

                border4.setBackgroundColor(Color.WHITE);
                backgroundColor4.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text4.setTextColor(Color.BLACK);

              break;

            case 2:
                border2.setBackgroundColor(Color.WHITE);
                backgroundColor2.setBackgroundColor(Color.BLACK);
                text2.setTextColor(Color.WHITE);

               border1.setBackgroundColor(Color.WHITE);
                backgroundColor1.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text1.setTextColor(Color.BLACK);

                border3.setBackgroundColor(Color.WHITE);
                backgroundColor3.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text3.setTextColor(Color.BLACK);

               border4.setBackgroundColor(Color.WHITE);
                backgroundColor4.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text4.setTextColor(Color.BLACK);

                break;

            case 3:
                border3.setBackgroundColor(Color.WHITE);
                backgroundColor3.setBackgroundColor(Color.BLACK);
                text3.setTextColor(Color.WHITE);

                border2.setBackgroundColor(Color.WHITE);
                backgroundColor2.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text2.setTextColor(Color.BLACK);

               border1.setBackgroundColor(Color.WHITE);
                backgroundColor1.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text1.setTextColor(Color.BLACK);

              border4.setBackgroundColor(Color.WHITE);
                backgroundColor4.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text4.setTextColor(Color.BLACK);

                break;


            case 4:
                border4.setBackgroundColor(Color.WHITE);
                backgroundColor4.setBackgroundColor(Color.BLACK);
                text4.setTextColor(Color.WHITE);

                border1.setBackgroundColor(Color.WHITE);
                backgroundColor1.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text1.setTextColor(Color.BLACK);

                border3.setBackgroundColor(Color.WHITE);
                backgroundColor3.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text3.setTextColor(Color.BLACK);

                border2.setBackgroundColor(Color.WHITE);
                backgroundColor2.setBackgroundColor(getResources().getColor(R.color.colorBox));
                text2.setTextColor(Color.BLACK);

                break;



        }
    }

    private void initialize() {
        border1=findViewById(R.id.border1);
        border2=findViewById(R.id.border2);
        border3=findViewById(R.id.border3);
        border4=findViewById(R.id.border4);

        backgroundColor1=findViewById(R.id.background_color1);
        backgroundColor2=findViewById(R.id.background_color2);
        backgroundColor3=findViewById(R.id.background_color3);
        backgroundColor4=findViewById(R.id.background_color4);

        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);

        mainLayout=findViewById(R.id.snackBar);

    }


    public  void replace_fragment(Fragment fragment){

                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, fragment);
                fragmentTransaction.commit();


    }


}
