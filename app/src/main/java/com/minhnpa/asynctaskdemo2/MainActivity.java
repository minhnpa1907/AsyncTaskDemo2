package com.minhnpa.asynctaskdemo2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ImageDownloader.OnDownloadImageListener {
    Button btnDownload;
    ImageView imgView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = (Button) findViewById(R.id.btnDownload);
        imgView = (ImageView) findViewById(R.id.imgView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnDownload.setOnClickListener(this);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnDownload) {
            ImageDownloader imageDownloader = new ImageDownloader(this);
            imageDownloader.setDownloadImageListener(this);
            imageDownloader.execute("https://www.google.com.vn/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        }
    }


    @Override
    public void onDownloadImageCompleted(Bitmap bitmap) {
        imgView.setImageBitmap(bitmap);
    }

    @Override
    public void onDownloadImageError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
