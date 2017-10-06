package com.example.ohmprakashpagolu.test1;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class EdgesActivity extends AppCompatActivity {

    private static final String TAG = EdgesActivity.class.getSimpleName();

    protected String[] requestedPermissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    protected static final String KEY_BITMAP = "IMAGE_PATH";
    protected static final int SELECT_PICTURE = 1;
    protected static final int PERMISSIONS_REQUEST = 9;


    ImageView detectEdgesImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_edges);
        Uri path = getIntent().getExtras().getParcelable(KEY_BITMAP);
        try {
            //detectEdges(readBitmapFromPath(this, path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void detectEdges(Bitmap bitmap) {
        Mat rgba = new Mat();
        Utils.bitmapToMat(bitmap, rgba);
        Mat edges = new Mat(rgba.size(), CvType.CV_8UC1);
        Imgproc.cvtColor(rgba, edges, Imgproc.COLOR_RGB2GRAY, 4);
        Imgproc.Canny(edges, edges, 80, 100);
        //showBitmap(this, bitmap, imageView);
        Bitmap resultBitmap = Bitmap.createBitmap(edges.cols(), edges.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(edges, resultBitmap);
        //showBitmap(this, resultBitmap, detectEdgesImageView);
    }
}