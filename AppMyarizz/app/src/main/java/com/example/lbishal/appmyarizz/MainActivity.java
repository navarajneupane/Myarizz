package com.example.lbishal.appmyarizz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set the logo
        ImageView imView = (ImageView) findViewById(R.id.imageViewLogo);
        imView.setImageResource(R.drawable.hamrologo);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
        imView.setLayoutParams(params);
        final NumberPicker np;
        //set the properties for number picker
        np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMaxValue(6);
        np.setMinValue(3);
        np.setValue(3);
        //get the handler for start button
        Button startButton = (Button) findViewById(R.id.startButton);
        //set button click listener
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Log.v("App on create",": button is clicked.");
                    //get the value from number clicker and pass it to next activity
                    int numberOfPlayers = np.getValue();
                    Intent playerDetailsIndent = new Intent(getApplicationContext(), PlayerDetailsActivity.class);
                    playerDetailsIndent.putExtra("numberOfPlayers", numberOfPlayers);
                    startActivity(playerDetailsIndent);
                } catch (Exception e) {
                    Log.v("App on create", "Run into Exception");
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
