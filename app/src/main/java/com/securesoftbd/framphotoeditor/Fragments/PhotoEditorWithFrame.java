package com.securesoftbd.framphotoeditor.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.FloatMath;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.securesoftbd.framphotoeditor.Home;
import com.securesoftbd.framphotoeditor.R;
import com.securesoftbd.framphotoeditor.Utils.Common;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class PhotoEditorWithFrame extends Fragment {
   /* private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;

    float scalediff;*/



    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;

    public  static  int TextColor;
    public  static   String TextFonts;
    public static TextView box_text;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final ImageView edit_image;

        ImageView edit_frame;
        final EditText namebox;
        final RelativeLayout image_plate;
        //final TextView box_text;
        int ColorCode;
        TextView change_color_and_fonts;


        View v=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_photo_editor_with_frame,null);


        edit_image=v.findViewById(R.id.edit_image);
        edit_frame=v.findViewById(R.id.frame);
        namebox=v.findViewById(R.id.name_box);
        image_plate=v.findViewById(R.id.image_plate);
        box_text=v.findViewById(R.id.box_text);
        change_color_and_fonts=v.findViewById(R.id.change_color_and_fonts);


        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/baskerville bold bt.ttf");
        /*box_text.setTypeface(face);
        box_text.setTextColor(Common._color);*/


      if (TextColor!=0 && TextFonts!=null){

           box_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
                   TextFonts));
           box_text.setTextColor(TextColor);
       }


       if (Common._current_color==0){
           box_text.setTextColor(Common._color);
       }


        image_plate.setDrawingCacheEnabled(true);


        edit_image.setImageBitmap(Common._current_image);
        edit_frame.setImageResource(Common._current_fram);


        // ColorCode =edit_frame.getDrawingCache().getPixel(0, 0);
        namebox.setTextColor(Common._color);

        limitDrag(matrix,edit_image);


        edit_frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                dragAndZoom(edit_image, event);
                return true;
            }
        });

      //  Log.d("TextColor", "onCreateView: "+Common._color);

      //  namebox.setTextColor(Common._color);

        box_text.setPaintFlags(box_text.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));
        box_text.setPaintFlags(View.INVISIBLE);
        box_text.setPaintFlags(0);

        change_color_and_fonts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog();
                bottomSheetDialog.show(getFragmentManager(),"bottom_sheet");

            }
        });



        namebox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                   if (!namebox.getText().toString().isEmpty()){

                     //  final int random = new Random().nextInt(61) + 20;
                     /*  Bitmap b = image_plate.getDrawingCache();
                       try {
                           b.compress(Bitmap.CompressFormat.JPEG, 95, new FileOutputStream("/some/location/"+random+".jpg"));
                       } catch (FileNotFoundException e) {
                           e.printStackTrace();
                       }*/

                       Common._current_namebox=namebox.getText().toString();
                       Common._current_matrix=edit_image.getImageMatrix();


                       Intent in=new Intent(getContext(), Home.class);
                       in.putExtra("task",4);
                      // in.putExtra("namebox",namebox.getText().toString());
                       startActivity(in);
                   }else {

                   }
                }
                return false;
            }
        });


        namebox.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                box_text.setText(s);
                Log.i("TEXT_CHANGE", "onTextChanged: s "+s+" , befor"+before+"  , count"+count );
            }
        });

       // image_zooming_rotation_drag(edit_frame);



        return v;
    }

    private void image_zooming_rotation_drag(ImageView edit_image) {
/*        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250, 250);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin = 50;
        layoutParams.bottomMargin = -250;
        layoutParams.rightMargin = -250;

        edit_image.setLayoutParams(layoutParams);*/


      /*  edit_image.setOnTouchListener(new View.OnTouchListener() {
            RelativeLayout.LayoutParams parms;
            int startwidth;
            int startheight;
            float dx = 0, dy = 0, x = 0, y = 0;
            float angle = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final ImageView view = (ImageView) v;
                ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:

                        parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        startwidth = parms.width;
                        startheight = parms.height;
                        dx = event.getRawX() - parms.leftMargin;
                        dy = event.getRawY() - parms.topMargin;
                        mode = DRAG;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 10f) {
                            mode = ZOOM;
                        }

                        d = rotation(event);

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {

                            x = event.getRawX();
                            y = event.getRawY();

                            parms.leftMargin = (int) (x - dx);
                            parms.topMargin = (int) (y - dy);

                            parms.rightMargin = 0;
                            parms.bottomMargin = 0;
                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                            view.setLayoutParams(parms);

                        } else if (mode == ZOOM) {

                            if (event.getPointerCount() == 2) {

                                newRot = rotation(event);
                                float r = newRot - d;
                                angle = r;

                                x = event.getRawX();
                                y = event.getRawY();

                                float newDist = spacing(event);
                                if (newDist > 10f) {
                                    float scale = newDist / oldDist * view.getScaleX();
                                    if (scale > 0.6) {
                                        scalediff = scale;
                                        view.setScaleX(scale);
                                        view.setScaleY(scale);

                                    }
                                }

                                view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                x = event.getRawX();
                                y = event.getRawY();

                                parms.leftMargin = (int) ((x - dx) + scalediff);
                                parms.topMargin = (int) ((y - dy) + scalediff);

                                parms.rightMargin = 0;
                                parms.bottomMargin = 0;
                                parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                view.setLayoutParams(parms);


                            }
                        }
                        break;


                }

                return false;
            }
        });*/





    }
    /*private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }*/

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }


    private void dragAndZoom(View v, MotionEvent event)
    {
        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;

        // Handle touch events here...

        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:

                // first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());


                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                mode = NONE;

                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                oldDist = spacing(event);

                if (oldDist > 5f)
                {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;

                }
                break;

            case MotionEvent.ACTION_MOVE:

                if (mode == DRAG)
                {
                    matrix.set(savedMatrix);

                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);

                }
                else if (mode == ZOOM)
                {
                    // pinch zooming
                    float newDist = spacing(event);

                    if (newDist > 5f)
                    {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix); // display the transformation on screen


    }

    private float spacing(MotionEvent event)
    {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }



    private void midPoint(PointF point, MotionEvent event)
    {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }


    private void limitDrag(Matrix m, ImageView view) {

        int _y_up = 0;


        float[] values = new float[9];
        m.getValues(values);
        float transX = values[Matrix.MTRANS_X];
        float transY = values[Matrix.MTRANS_Y];
        float scaleX = values[Matrix.MSCALE_X];
        float scaleY = values[Matrix.MSCALE_Y];

        Rect bounds = view.getDrawable().getBounds();
        int viewWidth = getResources().getDisplayMetrics().widthPixels;
        int viewHeight = getResources().getDisplayMetrics().heightPixels;



        if(viewHeight<=480)
        {

            _y_up=0;
        }
        if(viewHeight>480&&viewHeight<980)
        {

            _y_up=140;
        }


        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;
        int __width=width;
        int __height=height;
        width = viewWidth / 2;
        height = viewHeight / 2;


        //height = 200 ;
        float minX = (-width) ;//* scaleX;
        float minY = (-height) ;//* scaleY;



        if ((transX) > (viewWidth)) {

            //_x_left

            transX = viewWidth;
        } else if (transX < minX) {


            transX = minX;
        }


        if ((-transX) > (viewWidth)) {
            // _x_right
            transX = -(viewWidth);
        } else if (-transX < minX) {

            transX = -(minX+30);
        }



        if ((transY) > (viewHeight)) {
            //  _y_up
            transY =( viewHeight);


        } else if (transY < minY) {

            transY = (minY+_y_up);
        }

        if ((-transY) > (viewHeight)) {
            //  _y_down
            transY = -(viewHeight);

        } else if (-transY < minY) {

            transY = -(minY+170);
        }

        values[Matrix.MTRANS_X] = transX;
        values[Matrix.MTRANS_Y] = transY;
        m.setValues(values);
    }










    ////BottomSheet Dilog

    public static class BottomSheetDialog extends BottomSheetDialogFragment {

        static TextView final_text_and_color;
        RelativeLayout btn_color,btn_fonts;
        TextView font_text,color_text;
    public static int color;
    public static String fonts;





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







        switch (viewPager.getCurrentItem()){
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
        }




       /*     // final_text_and_color.setTextColor(-8089345);
        CoordinatorLayout colayout = v.findViewById(R.id.coordinatorLayout);


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
        });
*/







           /* CoordinatorLayout.LayoutParams layoutParams =
                    (CoordinatorLayout.LayoutParams) ((View) container.getParent()).getLayoutParams();
            final CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
            if (behavior != null && behavior instanceof BottomSheetBehavior) {
                ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
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

                                ((BottomSheetBehavior) behavior).setState(BottomSheetBehavior.STATE_EXPANDED);
                                break;
                            case BottomSheetBehavior.STATE_SETTLING:
                                Log.d("CHECK", "--------------  STATE_SETTLING");
                                break;
                        }


                    }

                    @Override
                    public void onSlide(@NonNull View view, float v) {

                    }
                });
            }

*/


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
        /*ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        _fontsRecycuar.addItemDecoration(itemDecoration);
*/


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
                            box_text.setTextColor(colorEnvelope.getColor());
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
                return new FontsAdepter.FontsViewHolder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull FontsAdepter.FontsViewHolder fontsViewHolder, final int i) {
                fontsViewHolder.font_text.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/"+stringArrayList.get(i)));

                fontsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Common._current_fonts="fonts/"+stringArrayList.get(i);
                        TextFonts="fonts/"+stringArrayList.get(i);;
                        // fonts="fonts/"+stringArrayList.get(i);
                        final_text_and_color.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/"+stringArrayList.get(i)));
                        box_text.setTypeface(Typeface.createFromAsset(context.getAssets(),
                                TextFonts));


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



}
