package com.securesoftbd.framphotoeditor.Fragments;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.securesoftbd.framphotoeditor.Home;
import com.securesoftbd.framphotoeditor.R;
import com.securesoftbd.framphotoeditor.Utils.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Saved extends Fragment {


     String image_path;

     ImageView edit_image;

     ImageView edit_frame;
     TextView namebox;
     RelativeLayout btn_unduh_foto,btn_bagikan;
     RelativeLayout final_image;

     public  String YOUR_FOLDER_NAME="FramPhotoEditor";
     public int MY_EXTERNAL_STORAGE_PERMISSION_CODE=100;



    @Override
    public void onResume() {
        super.onResume();

        /*if (Common._share==1){
            startActivity(new Intent(getContext(),Home.class));
            Common._share=0;

        }
*/




    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_save,null);




        edit_image=v.findViewById(R.id.edit_image);
        edit_frame=v.findViewById(R.id.frame);
        btn_unduh_foto=v.findViewById(R.id.btn_unduh_foto);
        final_image=v.findViewById(R.id.final_image);
        btn_bagikan=v.findViewById(R.id.btn_bagikan);


        namebox=v.findViewById(R.id.name_box);





        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/baskerville bold bt.ttf");
        namebox.setTypeface(face);

     /*   if (Common._current_color!=0 && Common._current_fonts!=null){

            namebox.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
                    Common._current_fonts));
            namebox.setTextColor(Common._current_color);
        }*/

        if (Common._current_fonts!=null){
            namebox.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
                    Common._current_fonts));
        }


        if (Common._current_color !=0 ){
            namebox.setTextColor(Common._current_color);
        }else {
            namebox.setTextColor(Common._color);
        }


        ImageView view = (ImageView) edit_image;

        view.setScaleType(ImageView.ScaleType.MATRIX);
        edit_image.setImageBitmap(Common._current_image);
        view.setImageMatrix(Common._current_matrix);

        edit_frame.setImageResource(Common._current_fram);
        namebox.setText(Common._current_namebox);
       // namebox.setTextColor(Common._color);

        btn_unduh_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),"Tab ",Toast.LENGTH_SHORT).show();


                create_folder();
                getSaveImageFilePath(create_folder());

/*

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_EXTERNAL_STORAGE_PERMISSION_CODE );


                        Log.d("FramPhotoEditor", "Image Path : "+getSaveImageFilePath(create_folder()));
                        System.out.print("FramPhotoEditor "+getSaveImageFilePath(create_folder()));
                    }
                }
*/

                Toast.makeText(getContext(),"File Path "+getSaveImageFilePath(create_folder()),Toast.LENGTH_LONG).show();




             //   System.out.print("ImagePath :="+getSaveImageFilePath());


                Intent in=new Intent(getContext(),Home.class);
                in.putExtra("task",100);
                in.putExtra("image_path",image_path);
                getContext().startActivity(in);



            }
        });

        btn_bagikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout final_img = (RelativeLayout) final_image.findViewById(R.id.final_image);

               // RelativeLayout layout = (RelativeLayout)findViewById(R.id.rel);
                final_img.setDrawingCacheEnabled(true);
                Bitmap  mBitmap = Bitmap.createBitmap(final_img.getDrawingCache());


                String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(),
                        mBitmap, "Design", null);

                Uri uri = Uri.parse(path);

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                share.putExtra(Intent.EXTRA_TEXT, "Halo saya telah membuat foto saya dengan frame eksklusif partisipan Give4dream. Yang belum bikin, bikin yuk! Kita pasti menang!\n" +
                        "!");
                getActivity().startActivity(Intent.createChooser(share, "Share Your Image!"));


                Common._share=1;

              //  getActivity().finish();


              //  startActivity(new Intent(getActivity(), Home.class));
            }
        });



        return v;
    }
/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_EXTERNAL_STORAGE_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               create_folder();

                getSaveImageFilePath(create_folder());
                Log.i("FramPhotoEditor", "Image Path : "+getSaveImageFilePath(create_folder()));
                Log.d("FramPhotoEditor", "Image Path : "+getSaveImageFilePath(create_folder()));
                System.out.print("FramPhotoEditor "+getSaveImageFilePath(create_folder()));

            } else {
           //     Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }

    }*/

    String getSaveImageFilePath(File mediaStorageDir) {

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "IMG_" + timeStamp + ".jpg";

        String selectedOutputPath = mediaStorageDir.getPath() + File.separator + imageName;
        Log.d(YOUR_FOLDER_NAME, "selected camera path " + selectedOutputPath);

        final_image.setDrawingCacheEnabled(true);
        final_image.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(final_image.getDrawingCache());

        int maxSize = 1080;

        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();

        if (bWidth > bHeight) {
            int imageHeight = (int) Math.abs(maxSize * ((float)bitmap.getWidth() / (float) bitmap.getHeight()));
            bitmap = Bitmap.createScaledBitmap(bitmap, maxSize, imageHeight, true);
        } else {
            int imageWidth = (int) Math.abs(maxSize * ((float)bitmap.getWidth() / (float) bitmap.getHeight()));
            bitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, maxSize, true);
        }
        final_image.setDrawingCacheEnabled(false);
        final_image.destroyDrawingCache();

        OutputStream fOut = null;
        try {
            File file = new File(selectedOutputPath);
            fOut = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();

           // Toast.makeText(getContext(),"Success ",Toast.LENGTH_LONG);
            Log.i("ImageSaved", "getSaveImageFilePath Success ");
        } catch (Exception e) {
            e.printStackTrace();
           // Toast.makeText(getContext(),"Filed ",Toast.LENGTH_LONG);
            Log.i("ImageSaved", "getSaveImageFilePath Failed: "+e);
        }
        image_path=selectedOutputPath;

        return selectedOutputPath;
    }

    public File  create_folder(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), YOUR_FOLDER_NAME);
        // Create a storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.i(YOUR_FOLDER_NAME, "Failed to create directory");
            }{
                Log.i(YOUR_FOLDER_NAME, "Successfully   create directory");
            }
        }else {
            Log.i(YOUR_FOLDER_NAME, "Created Directory");
        }



        return mediaStorageDir;
    }






}


