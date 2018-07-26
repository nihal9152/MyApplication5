package com.example.chiranjivrajput.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownload extends AsyncTask<String,Integer,Bitmap> {
    @SuppressLint("StaticFieldLeak")
    private ImageView imageView;
    private Bitmap bitmap;
    InputStream in = null;

    //constructor.
    ImageDownload(ImageView imageView) {
        this.imageView = imageView;
    }
    @Override
    protected void onPreExecute() {


    }
    @Override
    protected Bitmap doInBackground(String... params) {

        URL url;
        try {
            url = new URL(params[0]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap data) {
        imageView.setImageBitmap(data);
    }
}
