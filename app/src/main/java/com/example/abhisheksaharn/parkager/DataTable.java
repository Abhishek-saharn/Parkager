package com.example.abhisheksaharn.parkager;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DataTable extends ActionBarActivity {
    Toolbar toolbar;
    TextView tv;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_table);
        toolbar = (Toolbar) findViewById(R.id.included_appbar4);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            tv = (TextView) findViewById(R.id.textView3);
            Databasecode viewentry = new Databasecode(this);
            viewentry.open();
            String Data = viewentry.getData();
            viewentry.close();
            tv.setText(Data);
        } catch (Exception e) {
            String error = e.toString();
            Dialog d = new Dialog(DataTable.this);
            d.setTitle("Error");
            TextView tv2 = new TextView(DataTable.this);
            tv2.setText(error);
            d.setContentView(tv2);
            d.show();
        }
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
