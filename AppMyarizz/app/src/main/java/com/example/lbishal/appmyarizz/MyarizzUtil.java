package com.example.lbishal.appmyarizz;

import android.app.AlertDialog;
import android.content.Context;

import java.util.HashMap;

/**
 * Created by navaraj.neupane on 7-1-2017.
 */

public class MyarizzUtil {

    //hashmap to store the string to be displayed for differ error conditions of user input
    HashMap<String, String> errorCodeString = new HashMap<String, String>(){
        {
            put("NO_WINNER","No winner selected. Check the box for the player who won the game");
            put("NO_SEEN","No seen player selected. At least one player must be seen.");
            put("WINNER_NOT_SEEN","The winner must be a seen player.");
            put("EMPTY_NAME","Empty names not allowed.");
            put("DUPLICATE_NAME","Duplicate player's names not allowed.");

        }};

    public void raiseInputError(String msg, Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context).
                setMessage(msg)
                .setTitle("Input error")
                .setPositiveButton("Ok",null);
        builder.show();
    }

}
