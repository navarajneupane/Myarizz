package com.example.lbishal.appmyarizz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class IndividualGameActivity extends Activity {
    String TAG = "IndividualGameActivity";
    int selected_checkbox_position; //to keep track of which of the checkbox for winner is selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_game);
        //set the logo
        ImageView imView = (ImageView) findViewById(R.id.imageViewLogo);
        imView.setImageResource(R.drawable.hamrologo);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
        imView.setLayoutParams(params);
        //get the list of players from passed context when called from player details activity
        final String[] listOfPlayers = getIntent().getExtras().getStringArray("listOfPlayers");
        Log.d(TAG, "The players are ");
        for(String players:listOfPlayers) {
            Log.d(TAG, players);
        }

        //create the table and add to the view
        LinearLayout currentView = (LinearLayout)findViewById(R.id.individual_game_view);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableLayout tableLayout = new TableLayout(getApplicationContext());
        tableLayout.setLayoutParams(tableParams);
        tableLayout.setStretchAllColumns(true);
        //set the background color of the table
        tableLayout.setBackgroundColor(Color.LTGRAY);

        TableRow tableRowStatic = new TableRow(getApplicationContext());
        tableRowStatic.setLayoutParams(tableParams);
        //set the background color of the first row
        tableRowStatic.setBackgroundColor(Color.LTGRAY);
        //set the boundary between the rows
        tableRowStatic.setPadding(0,0,0,2); //left, right, top, botoom margin
        TextView tV = new TextView(getApplicationContext());
        tV.setLayoutParams(rowParams);
        tV.setText("Name");
        tV.setTextColor(Color.BLACK);
        tableRowStatic.addView(tV);
        tV = new TextView(getApplicationContext());
        tV.setText("Points");
        tV.setTextColor(Color.BLACK);
        tableRowStatic.addView(tV);
        tV = new TextView(getApplicationContext());
        tV.setText("Seen");
        tV.setTextColor(Color.BLACK);
        tableRowStatic.addView(tV);
        tV = new TextView(getApplicationContext());
        tV.setText("Winner");
        tV.setTextColor(Color.BLACK);
        tableRowStatic.addView(tV);
        tableLayout.addView(tableRowStatic);

        //edit text list [for second column]
        final List<EditText> pointsList = new ArrayList<>();
        //Radio button list for seen/unseen
        final List<CheckBox> seenList = new ArrayList<>();
        //checkbox list for winner
        final List<CheckBox> winnerList = new ArrayList<>();

        for (String S:listOfPlayers) {

            TableRow tableRow = new TableRow(getApplicationContext());
            //set the background color of the first row
            tableRowStatic.setBackgroundColor(Color.LTGRAY);
            //set the boundary between the rows
            tableRowStatic.setPadding(0,0,0,2); //left, right, top, botoom margin
            tableRow.setLayoutParams(tableParams);
            //add the row border color
            //tableRow.setBackgroundResource(R.drawable.row_border);
            //first column: name
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(rowParams);
            textView.setText(S);
            textView.setTextColor(Color.BLACK);
            tableRow.addView(textView);
            //second column: points
            EditText ed = new EditText(getApplicationContext());
            ed.setLayoutParams(rowParams);
            ed.setInputType(InputType.TYPE_CLASS_NUMBER);
            ed.setTextColor(Color.BLACK);
            pointsList.add(ed);
            tableRow.addView(ed);
            //third column: seen/unseen
            CheckBox cB_seen = new CheckBox(getApplicationContext());
            cB_seen.setTextColor(Color.BLACK);
            seenList.add(cB_seen);
            tableRow.addView(cB_seen);
            //fourth column: winner/not-winner
            CheckBox cB = new CheckBox(getApplicationContext());
            cB.setTextColor(Color.BLACK);
            cB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((CheckBox) view).isChecked()) {
                        for (int i = 0; i < winnerList.size(); i++) {
                            if (winnerList.get(i) == view)
                                selected_checkbox_position = i;
                            else
                                winnerList.get(i).setChecked(false);
                        }
                    } else {
                        selected_checkbox_position = -1;
                    }
                }

            });
            winnerList.add(cB);
            tableRow.addView(cB);
            tableLayout.addView(tableRow);
        }

        currentView.addView(tableLayout); //add the table to the view

        //get the listener for calculate
        final Button beginGameButton = (Button)findViewById(R.id.calculateButton);
        final HashMap<String,List<Object>> calculateHashMap = new HashMap<>(listOfPlayers.length);
        final MyarizzUtil util = new MyarizzUtil();
        beginGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Track the number of seen players. Raise error if no seen player
                Integer numberOfSeenPlayers = 0;
                //Flag to ensure that atleast one winner is selected
                Boolean atleastOneWinnerSelected = Boolean.FALSE;
                //Variable to keep track of the user input error
                String pointTableError = ""; //0 means no error
                try {
                    //prepare the hash map to provide to the function to do the calculation
                    for (int i=0;i<listOfPlayers.length;i++) {
                        String currentPlayer = listOfPlayers[i];
                        Integer currentPoints = Integer.parseInt(pointsList.get(i).getText().toString());
                        Boolean winnerFlag = winnerList.get(i).isChecked();
                        if (winnerFlag) {
                            atleastOneWinnerSelected = Boolean.TRUE;
                        }
                        Boolean seenStatus = seenList.get(i).isChecked();
                        if (winnerFlag & !seenStatus) {
                            //the winner is not selected as seen so raise error
                            pointTableError = "WINNER_NOT_SEEN";
                            break; //no need to continue
                        }
                        if(seenStatus) {
                            numberOfSeenPlayers = numberOfSeenPlayers + 1;
                        }
                        List<Object> playerValues = new ArrayList<Object>();
                        playerValues.add(currentPoints);
                        playerValues.add(seenStatus);
                        playerValues.add(winnerFlag);
                        Log.d(TAG,"Current Player Values: "+ currentPlayer + "," + String.valueOf(currentPoints) +
                                "," + String.valueOf(seenStatus) + "," + String.valueOf(winnerFlag));
                        calculateHashMap.put(currentPlayer, playerValues);
                    }

                    if(!pointTableError.isEmpty()) //some error has already been found, raise alert dialog
                    {
                        util.raiseInputError(util.errorCodeString.get(pointTableError), IndividualGameActivity.this);
                    }
                    else if(numberOfSeenPlayers == 0) {
                        pointTableError = "NO_SEEN";
                        util.raiseInputError(util.errorCodeString.get(pointTableError), IndividualGameActivity.this);
                    }
                    else if(!atleastOneWinnerSelected) {
                        //raise the error that no winner has been selected
                        pointTableError = "NO_WINNER";
                        util.raiseInputError(util.errorCodeString.get(pointTableError), IndividualGameActivity.this);
                    }
                    else {
                        //call the function to do the calculations and receive the value
                        Map<String, Integer> calculatedValue = ActionHandler.sendInput(calculateHashMap);

                        //start the activity to display the calculated value
                        Intent resultCalculatedActivity = new Intent(getApplicationContext(), ResultCalculatedActivity.class);
                        resultCalculatedActivity.putExtra("calculatedResult", (HashMap) calculatedValue);//hashmap implements serializable so can be passed in
                        startActivity(resultCalculatedActivity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_individual_game, parent, false);
        return v;
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
