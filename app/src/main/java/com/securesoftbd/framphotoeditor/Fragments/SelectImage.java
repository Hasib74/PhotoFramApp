package com.securesoftbd.framphotoeditor.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.securesoftbd.framphotoeditor.Home;
import com.securesoftbd.framphotoeditor.R;
import com.securesoftbd.framphotoeditor.Utils.Common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class SelectImage extends Fragment {


    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int PICK_PHOTO_CODE = 101;

    public  String TAG="SelctImageActivity";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RelativeLayout btn_camera;
        RelativeLayout btn_gallery;

        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_capture_image, null);

        btn_camera = v.findViewById(R.id.btn_camera);
        btn_gallery = v.findViewById(R.id.btn_gelllary);

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                }
            }
        });

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                intent.setType("image/*");

                /*

     String PHOTO_FILE_NAME = UUID.randomUUID().toString()+".jpg";
     Utils.putValue(this, Constants.UserPortraitFilePath,PHOTO_FILE_NAME);
    Intent intent = new Intent(Intent.ACTION_PICK);
    intent.setType("image/*");
    startActivityForResult(intent, PHOTO_REQUEST_GALLERY);

                * */

                // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
                // So as long as the result is not null, it's safe to use the intent.
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    // Bring up gallery to select a photo
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
            }
        });

        Log.i(TAG, "onCreateView: "+Common._current_image);

        return v;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Common._current_image=photo;
            //imageView.setImageBitmap(photo);

            Intent in=new Intent(getContext(), Home.class);
            in.putExtra("task",2);
            startActivity(in);



        } else  if (requestCode == PICK_PHOTO_CODE && resultCode == RESULT_OK && null !=data){
            Uri photoUri = data.getData();
            // Do something with the photo based on Uri
            Bitmap selectedImage;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), photoUri);

                Common._current_image=selectedImage;

                Intent in=new Intent(getContext(), Home.class);
                in.putExtra("task",2);
                startActivity(in);

            } catch (IOException e) {
                e.printStackTrace();
            }
            // Load the selected image into a preview



        } else {
        //Toast.makeText(context, "You have not selected and image", Toast.LENGTH_SHORT).show();
    }
    }

}