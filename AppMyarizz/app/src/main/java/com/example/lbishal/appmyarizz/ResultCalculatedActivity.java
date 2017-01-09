package com.example.lbishal.appmyarizz;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class ResultCalculatedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_calculated);
        //set the logo
        ImageView imView = (ImageView) findViewById(R.id.imageViewLogo);
        imView.setImageResource(R.drawable.hamrologo);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
        imView.setLayoutParams(params);
        //get the result
        final Map<String,Integer> calculatedResult = (HashMap<String,Integer>)getIntent().getExtras().getSerializable("calculatedResult");

        //iterate through the received result and populate the table
        //create the table and add to the view
        LinearLayout currentView = (LinearLayout)findViewById(R.id.resultCalculatedView);
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        TableLayout tableLayout = new TableLayout(getApplicationContext());
        tableLayout.setStretchAllColumns(true);
        tableLayout.setLayoutParams(tableParams);

        TableRow tableRowStatic = new TableRow(getApplicationContext());
        tableRowStatic.setLayoutParams(tableParams);
        TextView tV = new TextView(getApplicationContext());
        tV.setLayoutParams(rowParams);
        tV.setText("Name");
        tV.setTextColor(Color.BLACK);
        tableRowStatic.addView(tV);
        tV = new TextView(getApplicationContext());
        tV.setText("Points");
        tV.setTextColor(Color.BLACK);
        tableRowStatic.addView(tV);
        tableLayout.addView(tableRowStatic);

        for (HashMap.Entry<String, Integer> entry : calculatedResult.entrySet()) {
            String currentPlayer = entry.getKey();
            Integer currentPoints = entry.getValue();

            TableRow tbRow = new TableRow(getApplicationContext());
            tbRow.setLayoutParams(tableParams);

            tV = new TextView(getApplicationContext());
            tV.setLayoutParams(rowParams);
            tV.setText(currentPlayer);
            tV.setTextColor(Color.BLACK);
            tbRow.addView(tV);

            tV = new TextView(getApplicationContext());
            tV.setLayoutParams(rowParams);
            tV.setText(String.valueOf(currentPoints));
            tV.setTextColor(Color.BLACK);
            tbRow.addView(tV);

            tableLayout.addView(tbRow);
        }
        currentView.addView(tableLayout);


        final MyarizzUtil util = new MyarizzUtil();
        //get the listener for cumulativeButton
        final Button cumulativeButton = (Button)findViewById(R.id.cumulativeButton);
        cumulativeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO:
                util.raiseInputError("Cumulative clicked, not implemented yet!!", ResultCalculatedActivity.this);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result_calculated, menu);
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
