package com.example.lbishal.appmyarizz;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by navaraj.neupane on 7-1-2017.
 */

public class MyarizzUtil {

    public void raiseInputError(String msg, Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context).
                setMessage(msg)
                .setTitle("Input error")
                .setPositiveButton("Ok",null);
        builder.show();
    }

}
