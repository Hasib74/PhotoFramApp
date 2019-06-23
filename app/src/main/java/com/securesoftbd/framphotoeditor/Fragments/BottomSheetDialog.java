/*
package com.securesoftbd.framphotoeditor.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;



import com.securesoftbd.framphotoeditor.R;
import com.securesoftbd.framphotoeditor.Utils.Common;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerView;

import java.util.ArrayList;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    static TextView final_text_and_color;
    RelativeLayout btn_color,btn_fonts;
    TextView font_text,color_text;
    */
/*public static int color;
    public static String fonts;*//*






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.fragment_bottom_sheet,null);

        btn_color=v.findViewById(R.id.brn_color);
        btn_fonts=v.findViewById(R.id.btn_fonts);
        font_text=v.findViewById(R.id.fonst_text);
        color_text=v.findViewById(R.id.color_text);
        final_text_and_color=v.findViewById(R.id.final_fonts_and_colors);



        Log.i("CHECK", "onCreateView: Color "+Common._current_color);
        Log.i("CHECK", "onCreateView: Fonts "+Common._current_fonts);



        if (Common._current_color!=0 && Common._current_fonts!=null){
            final_text_and_color.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),""+Common._current_fonts));
            Log.i("CHECK", "onCreateView: VODA 1");
        }

        final ViewPager viewPager =v.findViewById(R.id.bottom_viewPager);
        viewPager.setAdapter(new ViewPagerAdepter(getChildFragmentManager()));

        viewPager.setCurrentItem(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btn_fonts.setBackground(getResources().getDrawable(R.drawable.button_shape_black));
            font_text.setTextColor(Color.WHITE);
            btn_color.setBackground(getResources().getDrawable(R.drawable.button_shape_white));

        }


        btn_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btn_color.setBackground(getResources().getDrawable(R.drawable.button_shape_black));
                    color_text.setTextColor(Color.WHITE);
                    btn_fonts.setBackground(getResources().getDrawable(R.drawable.button_shape_white));
                    font_text.setTextColor(getResources().getColor(R.color.colorAss));
                }

            }
        });

        btn_fonts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btn_fonts.setBackground(getResources().getDrawable(R.drawable.button_shape_black));
                    font_text.setTextColor(Color.WHITE);
                    btn_color.setBackground(getResources().getDrawable(R.drawable.button_shape_white));
                    color_text.setTextColor(getResources().getColor(R.color.colorAss));
                }

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {



            }

            @Override
            public void onPageSelected(int i) {
                Log.i("CHECK", "onPageSelected: "+ i);
                if (i==1){

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        btn_color.setBackground(getResources().getDrawable(R.drawable.button_shape_black));
                        color_text.setTextColor(Color.WHITE);
                        btn_fonts.setBackground(getResources().getDrawable(R.drawable.button_shape_white));
                        font_text.setTextColor(getResources().getColor(R.color.colorAss));
                    }
                }else if(i==0){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        btn_fonts.setBackground(getResources().getDrawable(R.drawable.button_shape_black));
                        font_text.setTextColor(Color.WHITE);
                        btn_color.setBackground(getResources().getDrawable(R.drawable.button_shape_white));
                        color_text.setTextColor(getResources().getColor(R.color.colorAss));
                    }

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });



 */
/*       switch (viewPager.getCurrentItem()){
            case 0:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btn_fonts.setBackground(getResources().getDrawable(R.drawable.button_shape_black));
                    btn_color.setBackground(getResources().getDrawable(R.drawable.button_shape_white));
                }
                break;
            case 1  :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btn_color.setBackground(getResources().getDrawable(R.drawable.button_shape_black));
                    btn_fonts.setBackground(getResources().getDrawable(R.drawable.button_shape_white));
                }
        }*//*





       // final_text_and_color.setTextColor(-8089345);
       */
/* CoordinatorLayout colayout = v.findViewById(R.id.coordinatorLayout);


        final BottomSheetBehavior behavior=BottomSheetBehavior.from(colayout);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("CHECK", "--------------  STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        Log.d("CHECK", "--------------  STATE_EXPANDED");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        Log.d("CHECK", "--------------  STATE_COLLAPSED");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d("CHECK", "--------------  STATE_DRAGGING");

                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("CHECK", "--------------  STATE_SETTLING");
                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });*//*


        return v;
    }



    public class ViewPagerAdepter extends FragmentStatePagerAdapter {

        public ViewPagerAdepter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int i) {
            switch (i)
            {
                case 0:
                    return new FontsFragment();
                //ChildFragment1 at position 0
                case 1:
                    return new ColorFragment();
                //ChildFragment2 at position 1

            }
            return null; //does not happen
        }


        @Override
        public int getCount() {
            return 2;
        }
    }



    public static class FontsFragment extends Fragment {

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



    }


    public static  class ColorFragment extends Fragment {

        ColorPickerView colorPickerView;




        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            final View v=inflater.inflate(R.layout.fragment_colors,null);

            colorPickerView=v.findViewById(R.id.colorPickerView);


            colorPickerView.setColorListener(new ColorListener() {
                @Override
                public void onColorSelected(ColorEnvelope colorEnvelope) {


                    if (colorEnvelope.getColor() == -65538){
                        if (Common._current_color!=0){
                            final_text_and_color.setTextColor(Common._current_color);
                        }

                    }else {
                        final_text_and_color.setTextColor(colorEnvelope.getColor());
                        Common._current_color=colorEnvelope.getColor();
                    }



                    Log.i("CHECK", "onColorSelected: "+colorEnvelope.getColor());

                }
            });


            return  v;
        }
    }



    public static class FontsAdepter extends RecyclerView.Adapter<FontsAdepter.FontsViewHolder> {

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
                   // fonts="fonts/"+stringArrayList.get(i);
                    final_text_and_color.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/"+stringArrayList.get(i)));
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



}
*/
