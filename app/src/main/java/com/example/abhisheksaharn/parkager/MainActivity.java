package com.example.abhisheksaharn.parkager;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.software.shell.fab.ActionButton;

import java.security.PublicKey;

public class MainActivity extends ActionBarActivity implements
        OnClickListener {
    private Toolbar toolbar;
    public ActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button (Shell Software)

        actionButton = (ActionButton) findViewById(R.id.action_button);
        actionButton.bringToFront();
        actionButton.setShowAnimation(ActionButton.Animations.JUMP_FROM_DOWN);
        actionButton.show();
        actionButton.setOnClickListener(this);

        /*ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_drawer);
        FloatingActionButton floatingActionButton = new FloatingActionButton.Builder(this).setContentView(imageView).build();
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageResource(R.drawable.ic_launcher);
        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.ic_launcher);
        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.ic_launcher);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this).addSubActionView(button1).addSubActionView(button2).addSubActionView(button3).attachTo(floatingActionButton).build();
        */


        toolbar = (Toolbar) findViewById(R.id.included_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationDrawerFragment navigationDrawerFragment;
        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragmend_layout);
        navigationDrawerFragment.setUp(R.id.fragmend_layout, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        Button ivIn = (Button) findViewById(R.id.Bin);
        Button ivOut = (Button) findViewById(R.id.Bout);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "fonts/SkarpaLt.ttf");
        ivIn.setTypeface(custom_font);
        ivOut.setTypeface(custom_font);
        ivIn.setOnClickListener(this);
        ivOut.setOnClickListener(this);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View arg0) { // TODO Auto-generated method
        switch (arg0.getId()) {
            case R.id.Bin:

                Intent i = new Intent(MainActivity.this, CheckIn.class);
                startActivity(i);

                break;
            case R.id.Bout:
                Intent i2 = new Intent(MainActivity.this, CheckOut.class);
                startActivity(i2);
                break;
            case R.id.action_button:

                startActivity(new Intent(MainActivity.this, DataTable.class));

        }
    }

}
