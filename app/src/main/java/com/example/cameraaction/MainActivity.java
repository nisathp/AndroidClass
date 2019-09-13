package com.example.cameraaction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.myimage);

    }

    public void takeSnap(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {

            try {

                if (data != null) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();


                    assert bitmap != null;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] arraydata = stream.toByteArray();

                    myImage(imageView, arraydata);

                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private void myImage(ImageView imageView, byte[] arraydata) {

        bitmap = BitmapFactory.decodeByteArray(arraydata, 0, arraydata.length);

        imageView.setImageBitmap(bitmap);
    }
}
