package com.example.abhisheksaharn.parkager;

import android.app.Dialog;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;


public class AfterItemClick extends ActionBarActivity {
    TextView tvId, tvName, tvDesc, tvVno, tvIntime, tvOuttime, tvAmount,
            tvAmountLabel;
    String idAfterClick = null;
    String idAfterCheckout = null;
    ImageView preview;
    String InHour, Indate;


    long Id, Amount = 0;
    private int hour, minute, date, mnth, top_h, top_m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_item_click);


        Toolbar toolbar = (Toolbar) findViewById(R.id.included_appbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializers();


        Bundle got = getIntent().getExtras();
        idAfterClick = got.getString("keyid");
        getIntent();

        Bundle getAfterCheckout = getIntent().getExtras();
        idAfterCheckout = getAfterCheckout.getString("KeyfromCheckout");
        getIntent();

        display();

    }

    private void display() {
        try {

            if (idAfterClick == null) {
                Id = Long.parseLong(idAfterCheckout);

            } else {
                Id = Long.parseLong(idAfterClick);
            }
            Databasecode objFind = new Databasecode(AfterItemClick.this);
            objFind.open();

            String name = objFind.findCustomerName(Id);
            String VDesc = objFind.findDescription(Id);
            String Vnumber = objFind.findVehicalNumber(Id);
            InHour = objFind.findHour(Id);
            String InMinute = objFind.findMinute(Id);
            Indate = objFind.findDate(Id);

            objFind.close();
            Typeface custom_font = Typeface.createFromAsset(getAssets(),
                    "fonts/SkarpaLt.ttf");
            tvName.setTypeface(custom_font);
            tvId.setTypeface(custom_font);
            String recievedTime = InHour + ":" + InMinute;
            tvId.setText(Id+"");
            tvName.setText(name);
            tvDesc.setText(VDesc);
            tvVno.setText(Vnumber);
            tvIntime.setText("Entry Time  " + recievedTime);
            getDateTime();
            tvOuttime.setText("Exit Time  :" + hour + ":" + minute);
            top_h = hour - Integer.parseInt(InHour);
            top_m = minute - Integer.parseInt(InMinute);
           getAmount();
            tvAmountLabel.setVisibility(View.VISIBLE);
            tvAmount.setText("Rs." + Amount);
            String file_name = "custom" + Id + ".png";
            preview.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ParkData/Images/" + file_name));

        } catch (Exception e) {
            // TODO: handle exception
            String Error = e.toString();
            Dialog d = new Dialog(AfterItemClick.this);
            d.setTitle("Error");
            TextView tv = new TextView(AfterItemClick.this);
            tv.setText(Error);
            d.setContentView(tv);
            d.show();
        }
    }

    private void initializers() {

        tvId = (TextView) findViewById(R.id.tvID);
        tvName = (TextView) findViewById(R.id.name);
        tvDesc = (TextView) findViewById(R.id.tvVdesc);
        tvVno = (TextView) findViewById(R.id.tvVnumber);
        tvIntime = (TextView) findViewById(R.id.tvIntime);
        tvOuttime = (TextView) findViewById(R.id.tvOuttime);
        tvAmount = (TextView) findViewById(R.id.tvRs);
        tvAmountLabel = (TextView) findViewById(R.id.tvAmount);
        preview = (ImageView) findViewById(R.id.dynamicIV);
    }

    public void getAmount() {
        // TODO Auto-generated method stub
        int days = date - Integer.parseInt(Indate);
        if (days >= 1) {

            Amount = days * 200;

        }
        if (top_h == 0) {
            if (top_m > 6) {
                Amount = 10;
            } else
                Amount = 0;
        }
        if (top_h != 0) {

            if (Integer.parseInt(InHour) < hour)
                Amount = Amount + top_h * 10;
            else {
                int lasttime = 24 - Integer.parseInt(InHour);
                int nexttime = hour;
                int totaltime = lasttime + nexttime;
                Amount = Amount + totaltime * 10;

            }
        }

    }

    private void getDateTime() {
        // TODO Auto-generated method stub
        Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        date = cal.get(Calendar.DATE);
        mnth = cal.get(Calendar.MONTH);

    }





}
