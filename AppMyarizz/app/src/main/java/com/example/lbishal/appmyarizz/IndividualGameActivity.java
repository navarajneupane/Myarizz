package com.example.lbishal.appmyarizz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class IndividualGameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_game);
        //set the logo
        ImageView imView = (ImageView) findViewById(R.id.imageViewLogo);
        imView.setImageResource(R.drawable.hamrologo);
        imView.requestLayout();
        int width = 300;
        int height = 60;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        imView.setLayoutParams(params);
        imView.getLayoutParams().height = 50;
        //get the list of players from passed context when called from player details activity
        final String[] listOfPlayers = getIntent().getExtras().getStringArray("listOfPlayers");
        //create the table and add to the view
        LinearLayout currentView = (LinearLayout)findViewById(R.id.individual_game_view);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(tableParams);

        TableRow tableRowStatic = new TableRow(this);
        tableRowStatic.setLayoutParams(tableParams);
        TextView tV = new TextView(this);
        tV.setLayoutParams(rowParams);
        tV.setText("Name");
        tableRowStatic.addView(tV);
        tV.setText("Points");
        tableRowStatic.addView(tV);
        tV.setText("Seen");
        tableRowStatic.addView(tV);
        tV.setText("Winner");
        tableRowStatic.addView(tV);
        tableLayout.addView(tableRowStatic);

        //edit text list [for second column]
        final List<EditText> pointsList = new ArrayList<>();
        //Radio button list for seen/unseen
        final List<RadioButton> seenList = new ArrayList<>();
        //checkbox list for winner
        final List<CheckBox> winnerList = new ArrayList<>();

        for (String S:listOfPlayers) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(tableParams);
            //first column: name
            TextView textView = new TextView(this);
            textView.setLayoutParams(rowParams);
            textView.setText(S);
            tableRow.addView(textView);
            //second column: points
            EditText ed = new EditText(this);
            ed.setLayoutParams(rowParams);
            ed.setInputType(InputType.TYPE_CLASS_NUMBER);
            pointsList.add(ed);
            tableRow.addView(ed);
            //third column: seen/unseen
            RadioButton rB = new RadioButton(this);
            seenList.add(rB);
            tableRow.addView(rB);
            //fourth column: winner/not-winner
            CheckBox cB = new CheckBox(this);
            winnerList.add(cB);
            tableRow.addView(cB);
            tableLayout.addView(tableRow);
        }

        currentView.addView(tableLayout); //add the table to the view

        //get the listener for calculate
        final Button beginGameButton = (Button)findViewById(R.id.calculateButton);
        final HashMap<String,List<Object>> calculateHashMap = new HashMap<>(listOfPlayers.length);
        beginGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    //prepare the hash map to provide to the function to do the calculation
                    for (int i=0;i<listOfPlayers.length;i++) {
                        String currentPlayer = listOfPlayers[i];
                        Integer currentPoints = Integer.parseInt(pointsList.get(i).getText().toString());
                        Boolean seenStatus = seenList.get(i).isChecked();
                        Boolean winnerFlag = winnerList.get(i).isChecked();
                        List<Object> playerValues = new ArrayList<Object>();
                        playerValues.add(currentPoints);
                        playerValues.add(seenStatus);
                        playerValues.add(winnerFlag);
                        calculateHashMap.put(currentPlayer,playerValues);
                    }
                    //call the function to do the calculations and receive the value
                    //HashMap<String, Integer> calculatedValue = getCalculation(calculateHashMap);

                    //start the activity to display the calculated value
                    Intent resultCalculatedActivity = new Intent(getApplicationContext(), ResultCalculatedActivity.class);
                    resultCalculatedActivity.putExtra("calculatedResult", calculateHashMap);//hashmap implements serializable so can be passed in
                    startActivity(resultCalculatedActivity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_individual_game, menu);
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
