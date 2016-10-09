package com.kkandroidstudy.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.kkandroidstudy.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CustomCameraActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {
    private Button btn_capture;

    private SurfaceView surfaceView;

    private Camera camera;

    private SurfaceHolder holder;

    private Camera.PictureCallback pictureCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                File tempFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp.png");
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(data);
                fos.close();
                Intent intent = new Intent(CustomCameraActivity.this, TakePictureResultActivity.class);
                intent.putExtra("picPath", tempFile.getAbsolutePath());
                startActivity(intent);
                CustomCameraActivity.this.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        btn_capture = (Button) findViewById(R.id.btn_capture);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        btn_capture.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_capture:
                capture();
                break;
        }

    }

    /**
     * 获取预览相机图像
     */
    private void capture() {
        camera.takePicture(null, null, pictureCallBack);
    }

    /**
     * 获取Camera对象
     */
    public Camera getCamera(int id) {
        Camera c = null;
        int numCams = Camera.getNumberOfCameras();
        try {
            if (numCams > 1) {
                c = Camera.open(id);
            } else {
                c = Camera.open();
            }
            // get Camera parameters
            Camera.Parameters mParams = c.getParameters();
            c.setDisplayOrientation(90);
            List<String> focusModes = mParams.getSupportedFocusModes();
            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            }
//            mParams.setExposureCompensation(0);
            mParams.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
            // set Camera parameters
            mParams.setPictureSize(1280, 720);
            mParams.setPreviewSize(1280, 720);
            mParams.setPictureFormat(PixelFormat.JPEG);
            //设置图片质量
            mParams.setJpegQuality(100);
            if (id == 1) {
                mParams.setRotation(270);
            } else {
                mParams.setRotation(90);
            }
            c.setParameters(mParams);
            mParams = null;
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return c;
    }


    /**
     * 开始预览相机内容
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放相机资源
     */
    private void releaseCamera() {
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (camera == null) {
            camera = getCamera(0);
            if (holder != null) {
                setStartPreview(camera, holder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setStartPreview(camera, holder);
//        Log.e("相机参数:", camera.getParameters().flatten());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.stopPreview();
        setStartPreview(camera, holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }
}
