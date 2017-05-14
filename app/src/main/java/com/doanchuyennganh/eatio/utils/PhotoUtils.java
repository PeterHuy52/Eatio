package com.doanchuyennganh.eatio.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 5/4/2017.
 */

public class PhotoUtils {
    public static final String PREFIX_BASE64 = "data:image/jpeg;base64,";

    public static String convertBitmapToBase64(Bitmap bitmap) {
        Bitmap bm = resizeBitmap(bitmap, 360, 360, false);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        String base64Str = PREFIX_BASE64 + Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
        return base64Str;
    }

    public static String convertUriToBase64(Context context, Uri uri) throws IOException {
        Bitmap bm=handleImageIfRotate(context, uri);
        return convertBitmapToBase64(bm);
    }

    public static Bitmap convertBase64ToBitmap(String base64str) {
        byte[] decodedBytes = Base64.decode(
                base64str.substring(base64str.indexOf(",") + 1),
                Base64.DEFAULT
        );
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static Bitmap resizeBitmap(Bitmap bm, int width, int height, boolean filter) {
        Bitmap bitmap = Bitmap.createScaledBitmap(bm, width, height, filter);
        return bitmap;
    }

    private static Bitmap handleImageIfRotate(Context context,Uri uri) throws IOException {
        String path=uri.getPath();
        Bitmap bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        ExifInterface exif = new ExifInterface(path);
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;

        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0,360, 360, matrix, true);
        return rotatedBitmap;

    }

}
