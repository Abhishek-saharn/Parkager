package com.example.abhisheksaharn.parkager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.software.shell.fab.ActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;


public class CheckIn extends ActionBarActivity implements View.OnClickListener, View.OnTouchListener {
    public static int CustomerId = 0;
    public static final String APP_PATH_SD_CARD = "/ParkData/Images/";


    Button update, view, outButton;
    ImageView addImage;
    EditText etName, etNumber, etvnumber, etvmodel;
    TimePicker tp;
    TextView tvTime, tvname, tvnum, tvVnum, tvVdesc;
    private int hour, minute, date, mnth,year;
    final static int cameradata = 0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        Button Button_updt = (Button) findViewById(R.id.b_updt);

        Button_updt.bringToFront();

        Button_updt.setOnClickListener(this);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "fonts/SkarpaLt.ttf");
        Button_updt.setTypeface(custom_font);

        Toolbar toolbar = (Toolbar) findViewById(R.id.included_appbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        checkinInitializers(); // ///// Initializing Variables
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // /to hide
        // automatic
        // popping
        // of
        // Keypad
        // update.setOnClickListener(this);
        //view.setOnClickListener(this);
        //outButton.setOnClickListener(this);
        addImage.setOnTouchListener(this);


    }

    public void getDateTime() {
        // TODO Auto-generated method stub
        Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        date = cal.get(Calendar.DAY_OF_MONTH);
        mnth = cal.get(Calendar.MONTH) + 1;
        year=cal.get(Calendar.YEAR);
    }


    private void checkinInitializers() {
        // update = (Button) findViewById(R.id.button2);
        //view = (Button) findViewById(R.id.vDb);
        //outButton = (Button) findViewById(R.id.buttonOut);
        etName = (EditText) findViewById(R.id.eName);
        etNumber = (EditText) findViewById(R.id.editText1);
        etvnumber = (EditText) findViewById(R.id.etVehicaln);
        etvmodel = (EditText) findViewById(R.id.etVehicalm);
        tvTime = (TextView) findViewById(R.id.textView3);
        addImage = (ImageView) findViewById(R.id.imageView1);
        tvname = (TextView) findViewById(R.id.tvCname);
        tvnum = (TextView) findViewById(R.id.tvCnumber);
        tvVnum = (TextView) findViewById(R.id.tvVnumber);
        tvVdesc = (TextView) findViewById(R.id.tvVdesc);
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
            case R.id.b_updt:
                Boolean ifItWorks = true;
                String name = etName.getText().toString();
                String number = etNumber.getText().toString();
                String vehiNumber = etvnumber.getText().toString();
                String vehiModel = etvmodel.getText().toString();

                getDateTime();
                String storedtime = hour + ":" + minute;
                // String storedDate=date+"/"+mnth;
                if (name.equals("") || number.equals("") || vehiNumber.equals("")
                        || vehiModel.equals("") || number.length() < 10||number.length() > 10) {
                    AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
                    alert2.setTitle("Warning!!");
                    alert2.setMessage("Please Fill All Field or Check your Mobile number");
                    alert2.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    // TODO Auto-generated method stub

                                }
                            });
                    Dialog dia = alert2.create();
                    dia.show();

                } else {
                    try {

                        Databasecode entry = new Databasecode(CheckIn.this);
                        entry.open();
                        entry.create(name, number, vehiNumber, vehiModel, hour,
                                minute, date, mnth,year);
                        Log.d("afterCreate", "Created");
                        CustomerId = entry.getRowId();
                        entry.close();
                        SaveImage(bmp);
                    } catch (Exception e) {
                        // TODO: handle exception
                        ifItWorks = false;

                        String Error = e.toString();
                        Dialog d = new Dialog(CheckIn.this);
                        d.setTitle("Error");
                        TextView tv = new TextView(CheckIn.this);
                        tv.setText(Error);
                        d.setContentView(tv);
                        d.show();
                    } finally {
                        if (ifItWorks.equals(true)) {

                            int ExCid = CustomerId;
                            AlertDialog.Builder alert = new AlertDialog.Builder(
                                    this);
                            alert.setTitle("Car Parked");
                            alert.setMessage("Customer ID:" + ExCid
                                    + "\nTime of Parking:" + storedtime);
                            alert.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface arg0,
                                                            int arg1) {
                                            // TODO Auto-generated method stub

                                        }

                                    });
                            alert.setNeutralButton("View DataBase",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface arg0,
                                                            int arg1) {
                                            // TODO Auto-generated method stub
                                            Intent intent = new Intent(
                                                    CheckIn.this, DataTable.class);
                                            startActivity(intent);
                                        }
                                    });
                            Dialog dialog = alert.create();
                            dialog.show();
                            view.setVisibility(View.VISIBLE);

						/*
                         * Dialog d = new Dialog(CheckIn.this);
						 * d.setTitle("Car Parked"); TextView tv = new
						 * TextView(CheckIn.this); tv.setText("Parking ID of " +
						 * name + " is " + ExCid); d.setContentView(tv);
						 * d.show();
						 */
                        }
                    }

                }

                break;



        }


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ((ImageView) view).setImageAlpha(200);
                break;
            case MotionEvent.ACTION_UP:
                ((ImageView) view).setImageAlpha(255);
            case MotionEvent.ACTION_CANCEL:
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Intent icamera = new Intent(
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(icamera, cameradata);

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle ext = data.getExtras();
            bmp = (Bitmap) ext.get("data");
            addImage.setImageBitmap(bmp);

        }

    }

    private void SaveImage(Bitmap bitmap) {
        String full_path = Environment.getExternalStorageDirectory().getAbsolutePath() + APP_PATH_SD_CARD;
        try {
            File dir = new File(full_path);
            if (!dir.exists()) dir.mkdirs();
            OutputStream fout;
            File file = new File(full_path, "custom" + CustomerId + ".png");
            file.createNewFile();
            fout = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fout);
            fout.flush();
            fout.close();
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            Toast.makeText(CheckIn.this, "Saved", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(CheckIn.this, "Saved to External() string=" + e.getMessage(), Toast.LENGTH_LONG).show();

        }


    }
}
