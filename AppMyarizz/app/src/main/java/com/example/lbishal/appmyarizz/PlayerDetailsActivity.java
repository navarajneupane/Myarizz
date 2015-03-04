package com.example.lbishal.appmyarizz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;
import java.util.List;


public class PlayerDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        //set the logo
        ImageView imView = (ImageView) findViewById(R.id.imageViewLogo);
        imView.setImageResource(R.drawable.hamrologo);
        //get the number of players from passed context when called from main activity
        final int numberOfPlayers = getIntent().getExtras().getInt("numberOfPlayers");
        //maintain an array to save the name of players
        final String[] listOfPlayers = new String[numberOfPlayers];
        //programmatically create text box to allow user to input name of players
        LinearLayout currentLayout = (LinearLayout)findViewById(R.id.activity_player_details);
        final List<EditText> editTextList = new ArrayList<>(numberOfPlayers);
        for (int i=0; i<numberOfPlayers; i++) {
            EditText ed = new EditText(this);
            // Input type is text
            ed.setInputType(InputType.TYPE_CLASS_TEXT);
            // setting height/width for your editText
            ed.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            currentLayout.addView(ed);
            editTextList.add(ed);
        }
        //get the listener for begin game button
        final Button beginGameButton = (Button)findViewById(R.id.beginGameButton);
        beginGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Log.v("Player detail activity", "The game is begun!");
                    //get the list of players from edit text buttons
                    for (int i=0;i<numberOfPlayers;i++) {
                        listOfPlayers[i]=editTextList.get(i).getText().toString();
                    }
                    Intent playerDetailsIndent = new Intent(getApplicationContext(), IndividualGameActivity.class);
                    playerDetailsIndent.putExtra("listOfPlayers", listOfPlayers);
                    startActivity(playerDetailsIndent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
