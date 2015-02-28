package com.example.lbishal.appmyarizz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;


public class ResultCalculatedActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_calculated);
        //set the logo
        ImageView imView = (ImageView) findViewById(R.id.imageViewLogo);
        imView.setImageResource(R.drawable.HamroLogo);
        //get the result
        final HashMap<String,Integer> calculatedResult = (HashMap<String,Integer>)getIntent().getExtras().getSerializable("calculatedResult");

        //iterate through the received result and populate the table
        //create the table and add to the view
        LinearLayout currentView = (LinearLayout)findViewById(R.id.resultCalculatedView);
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
        tableLayout.addView(tableRowStatic);

        for (HashMap.Entry<String, Integer> entry : calculatedResult.entrySet()) {
            String currentPlayer = entry.getKey();
            Integer currentPoints = entry.getValue();

            TableRow tbRow = new TableRow(this);
            tbRow.setLayoutParams(tableParams);
            TextView tv = new TextView(this);
            tv.setLayoutParams(rowParams);
            tV.setText(currentPlayer);
            tbRow.addView(tV);
            tV.setText(currentPoints);
            tbRow.addView(tV);

            tableLayout.addView(tbRow);
        }
        currentView.addView(tableLayout);
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
