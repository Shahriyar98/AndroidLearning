package Helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.arbabkhan.databaseintegration.UserBioDataActivity;

import java.io.File;

/**
 * Created by ArbabKhan on 11/06/2018.
 */



public class MediaFetcher {

    public static final int PICK_FROM_CAMERA = 1;
    public static final int PICK_FROM_GALLARY = 2;

    public Uri outPutfileUri;
    Activity activity;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public void openGallery(Context context) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        Activity activity = (Activity) context;
        activity.startActivityForResult(galleryIntent,PICK_FROM_GALLARY);
    }

    public void openCamera(Context context) {
        // permission has been granted, continue as usual
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
        outPutfileUri = Uri.fromFile(file);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);

        Activity activity = (Activity) context;
        activity.startActivityForResult(captureIntent, PICK_FROM_CAMERA);
    }
}
