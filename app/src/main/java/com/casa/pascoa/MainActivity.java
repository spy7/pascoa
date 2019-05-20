package com.casa.pascoa;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<File> pascoaList = new ArrayList<>();
    private String name;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        adjustButtons();
    }

    private void loadImages() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        path = new File(path, "Camera");

        File[] files = path.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().toLowerCase().startsWith(name.toLowerCase())) {
                    pascoaList.add(file);
                }
            }
        }

        showImage();
    }

    private void showImage() {
        String number = ((TextView)findViewById(R.id.box1)).getText().toString() +
                ((TextView)findViewById(R.id.box2)).getText().toString() +
                ((TextView)findViewById(R.id.box3)).getText().toString() +
                ((TextView)findViewById(R.id.box4)).getText().toString();
        for (File file : pascoaList) {
            if (file.getName().split("\\.")[0].endsWith(number)) {
                setImage(file);
            }
        }
    }

    private void adjustButtons() {
        findViewById(R.id.left1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pressLeft(findViewById(R.id.box1));
            }
        });

        findViewById(R.id.right1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pressRight(findViewById(R.id.box1));
            }
        });

        findViewById(R.id.left2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pressLeft(findViewById(R.id.box2));
            }
        });

        findViewById(R.id.right2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pressRight(findViewById(R.id.box2));
            }
        });

        findViewById(R.id.left3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pressLeft(findViewById(R.id.box3));
            }
        });

        findViewById(R.id.right3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pressRight(findViewById(R.id.box3));
            }
        });

        findViewById(R.id.left4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pressLeft(findViewById(R.id.box4));
            }
        });

        findViewById(R.id.right4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pressRight(findViewById(R.id.box4));
            }
        });

        findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                name = ((TextView)findViewById(R.id.my_name)).getText().toString();
                if (name.length() > 0) {
                    findViewById(R.id.frame).setVisibility(View.GONE);
                    findViewById(R.id.foto).setVisibility(View.VISIBLE);
                    findViewById(R.id.number).setVisibility(View.VISIBLE);
                    loadImages();
                }
            }
        });
    }

    private void pressLeft(View view) {
        TextView box = (TextView) view;
        int i = Integer.parseInt(box.getText().toString());
        box.setText(String.valueOf(i > 0 ? i - 1 : 9));
        showImage();
    }

    private void pressRight(View view) {
        TextView box = (TextView) view;
        int i = Integer.parseInt(box.getText().toString());
        box.setText(String.valueOf(i < 9 ? i + 1 : 0));
        showImage();
    }

    private void setImage(File file) {
        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        ImageView myImage = findViewById(R.id.foto);
        myImage.setImageBitmap(myBitmap);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
