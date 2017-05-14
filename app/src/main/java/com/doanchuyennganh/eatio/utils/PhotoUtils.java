package com.doanchuyennganh.eatio.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

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

}
