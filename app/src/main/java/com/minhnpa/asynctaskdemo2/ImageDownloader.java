package com.minhnpa.asynctaskdemo2;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Long, Bitmap> {
    //    private ProgressDialog dialog;
    MainActivity host;
    private OnDownloadImageListener onDownloadImageListener;

    public ImageDownloader(MainActivity host) {
        this.host = host;
//        dialog = new ProgressDialog(context);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String strUrl = params[0];
        Bitmap bitmap = null;

        try {
            URL imgUrl = new URL(strUrl);
            InputStream is = imgUrl.openStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
//        dialog.dismiss();
        host.hideProgressBar();

        if (bitmap != null) {
            onDownloadImageListener.onDownloadImageCompleted(bitmap);
        } else {
            onDownloadImageListener.onDownloadImageError("Error!");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        dialog.setMessage("Downloading... ");
//        dialog.show();
        host.showProgressBar();
    }

    public void setDownloadImageListener(OnDownloadImageListener listener) {
        onDownloadImageListener = listener;
    }

    interface OnDownloadImageListener {
        void onDownloadImageCompleted(Bitmap bitmap);

        void onDownloadImageError(String error);
    }
}
