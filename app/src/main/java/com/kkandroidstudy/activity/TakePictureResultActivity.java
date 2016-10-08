package com.kkandroidstudy.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.kkandroidstudy.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TakePictureResultActivity extends AppCompatActivity {
    private ImageView iv_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture_result);
        String picPath = getIntent().getStringExtra("picPath");
        iv_picture = (ImageView) findViewById(R.id.iv_picture);
        Log.e("picPath",picPath);
        try {
            FileInputStream fis = new FileInputStream(picPath);
            Bitmap bmp = BitmapFactory.decodeStream(fis);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            bmp = bmp.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
            iv_picture.setImageBitmap(bmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
