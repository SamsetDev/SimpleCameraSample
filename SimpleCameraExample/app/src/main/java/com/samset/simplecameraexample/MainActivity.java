package com.samset.simplecameraexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int TAKE_PICTURE_CODE = 100;
    private Button btncapture;
    private Bitmap cameraBitmap = null;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncapture = ((Button) findViewById(R.id.take_picture));
        imageView = (ImageView) findViewById(R.id.img);
        btncapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (TAKE_PICTURE_CODE == requestCode) {
            processCameraImage(data);
        }
    }

    private void startCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, TAKE_PICTURE_CODE);
    }

    private void processCameraImage(Intent intent) {

        cameraBitmap = (Bitmap) intent.getExtras().get("data");

        imageView.setImageBitmap(cameraBitmap);
    }
}