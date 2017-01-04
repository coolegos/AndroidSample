package com.egos.samples.contentprovider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;

/**
 * Created by Egos on 2016/12/25.
 * ContentProvider用来存储大文件，一般不是用ContentProvider直接来存储的，而是通过重写
 * {@link #openFile}方法来将文件对应成一个文件的位置。
 * 使用Intent来传数据的时候不能太大
 */
public class ProviderFile extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI("", "", 0);
    }

    @Override
    public boolean onCreate() {
        // 将assets的文件拷出来，模拟网上下载到ExternalFile中
        File file = new File(getContext().getExternalFilesDir(null), "test.jpg");
        if (!file.exists()) {
            AssetManager assetManager = getContext().getAssets();
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = assetManager.open("test.JPG");
                outputStream = new BufferedOutputStream(new FileOutputStream(file));
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        if (uri.toString().endsWith(".jpg")) {
            return "image/jpeg";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        if ("image/jpeg".equals(getType(uri))) {
            // 通过这里将一个文件对应了一个Uri
            File file = new File(getContext().getExternalFilesDir(null), uri.getPath());
            if (file.exists()) {
                return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            }
        }
        throw new FileNotFoundException(uri.getPath());
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode, CancellationSignal signal) throws FileNotFoundException {
        return super.openFile(uri, mode, signal);
    }
}
