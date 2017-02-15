package com.example.abhisheksaharn.parkager;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.software.shell.fab.ActionButton;

import java.util.Calendar;


public class CheckOut extends ActionBarActivity implements View.OnClickListener {
    TextView tvlabel;
    EditText edId;

    String stringtopass;

    RadioGroup rg;
    RadioButton rbId, rbName;


    long Amount = 0;
    private int hour, minute, date, mnth, top_h, top_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_out);

        ActionButton actionButton1 = (ActionButton) findViewById(R.id.action_button);
        actionButton1.show();
        actionButton1.bringToFront();
        actionButton1.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.included_appbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // /to hide
        // automatic
        // popping
        // of
        // Keypad
        // update.setOnClickListener(this);
        //view.setOnClickListener(this);
        //outButton.setOnClickListener(this);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "fonts/SkarpaLt.ttf");
        // Typepad
        // getActionBar().setBackgroundDrawable(new
        // ColorDrawable(this.getResources().getColor(goodGreen1)));


        // TODO Auto-generated method stub


        rg = (RadioGroup) findViewById(R.id.rg);
        rbId = (RadioButton) findViewById(R.id.RseachById);
        rbName = (RadioButton) findViewById(R.id.RseachByName);


        tvlabel = (TextView) findViewById(R.id.tventerCID);
        edId = (EditText) findViewById(R.id.editText1);

        tvlabel.setTypeface(custom_font);
        rbId.setTypeface(custom_font);
        rbName.setTypeface(custom_font);
        rbName.setOnClickListener(this);
        rbId.setOnClickListener(this);

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
        switch (view.getId()) {
            case R.id.action_button:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edId.getWindowToken(), 0);
                stringtopass = edId.getText().toString();
                if (rbName.isChecked()) {
                    Bundle basket = new Bundle();
                    basket.putString("KeyfromCheckout", stringtopass);
                    Intent intent = new Intent(CheckOut.this, SearchResult.class);
                    intent.putExtras(basket);
                    startActivity(intent);
                }
                if (rbId.isChecked()) {
                    Bundle basket = new Bundle();
                    basket.putString("KeyfromCheckout", stringtopass);
                    Intent intent = new Intent(CheckOut.this, AfterItemClick.class);
                    intent.putExtras(basket);
                    startActivity(intent);


                }
                break;
            case R.id.RseachById:
                tvlabel.setText("  Enter Customer Id");
                break;
            case R.id.RseachByName:
                tvlabel.setText("  Enter Name");
                break;

        }


    }


}
