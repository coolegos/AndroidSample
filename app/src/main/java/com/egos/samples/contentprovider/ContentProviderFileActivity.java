package com.egos.samples.contentprovider;

import java.io.InputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/25.
 * 将文件通过Content Provider来存储。对于一些图片使用Intent无法传递(Intent有传递的最大值)
 */
public class ContentProviderFileActivity extends AppCompatActivity {

    public static final Uri URI = Uri.parse("content://com.egos.samples/test.jpg");

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_file);
        mImageView = (ImageView) findViewById(R.id.image);
        new LoadImageTask().execute();
    }

    /**
     * 从Provider中读取图片
     */
    class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;

            try {
                //通过ContentResolver获取图片的输入流，再转化为Bitmap
                InputStream is = getContentResolver().openInputStream(URI);
                bitmap = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                mImageView.setImageBitmap(bitmap);
            }
        }
    }
}
