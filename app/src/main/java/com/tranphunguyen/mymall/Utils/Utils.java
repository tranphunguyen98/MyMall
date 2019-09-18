package com.tranphunguyen.mymall.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Trần Phú Nguyện on 9/18/2019.
 */
public class Utils {

    public static void makeLongToast(Context context, String message) {

        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }

}
