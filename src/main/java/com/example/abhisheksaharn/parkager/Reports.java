package com.example.abhisheksaharn.parkager;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Reports extends ActionBarActivity implements View.OnClickListener {
     AfterItemClick aicObj=new AfterItemClick();
    Button bshow;
    ListView rlv;
    List<ReportData> Rlist = new ArrayList<ReportData>();
    CustomAdapter2 customAdapter2;

    EditText date_from, date_to;
    String getFromDate, getToDate, fromDate, fromMonth, fromYear, toDate, toMonth, toYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar7);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlv = (ListView) findViewById(R.id.reportListView);
        rlv.setDivider(null);
        date_from = (EditText) findViewById(R.id.etFrom);

        date_to = (EditText) findViewById(R.id.etTo);

        bshow = (Button) findViewById(R.id.BShow);


        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!charSequence.toString().equals(current)) {
                    String clean = charSequence.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int j = 2; j <= cl && j < 6; j += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        if (mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    if (date_from.isFocused()) {
                        date_from.setText(current);

                        date_from.setSelection(sel < current.length() ? sel : current.length());

                    } else {
                        date_to.setText(current);


                        date_to.setSelection(sel < current.length() ? sel : current.length());
                    }

                }

            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        date_from.addTextChangedListener(tw);
        date_to.addTextChangedListener(tw);
        bshow.bringToFront();
        bshow.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reports, menu);
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

    @Override
    public void onClick(View view) {
        {

            getFromDate = date_from.getText().toString();
            fromDate = getFromDate.substring(0, 2);
            fromMonth = getFromDate.substring(3, 5);
            fromYear = getFromDate.substring(6, 10);
            getToDate = date_to.getText().toString();
            toDate = getToDate.substring(0, 2);
            toMonth = getToDate.substring(3, 5);
            toYear = getToDate.substring(6, 10);
            /*-------------------------------------------------------------------------------*/
            Databasecode objReport = new Databasecode(this);
            Cursor curRepo = objReport.getReport(fromDate, fromMonth, fromYear, toDate, toMonth, toYear);
            Log.d("adptr", "adptr created");
            for (curRepo.moveToFirst(); !curRepo.isAfterLast(); curRepo.moveToNext()) {
                String name = curRepo.getString(1);
                String id = curRepo.getString(0);
             //   String amount=String.valueOf(aicObj.getAmount());
               String amount="Rs 1000";
                ReportData reportData=new ReportData(name,id,amount);
                Rlist.add(reportData);
                Log.d("adptr", "2adptr created");
            }
            /*-------------------------------------------------------------*/

            customAdapter2 = new CustomAdapter2(this, Rlist);
            Log.d("adptr", "adptr created");
            rlv.setAdapter(customAdapter2);
            Log.d("setADpter","adapter");

            Toast.makeText(this, fromDate + "is Date" + fromMonth + "Is Mounth" + fromYear + " is Year ", Toast.LENGTH_LONG).show();
        }
    }
}
