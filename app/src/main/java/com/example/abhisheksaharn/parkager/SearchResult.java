package com.example.abhisheksaharn.parkager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.Browser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.List;


public class SearchResult extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    List<String> stringList = new ArrayList<String>();
    List<Data> alist = new ArrayList<Data>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        lv = (ListView) findViewById(R.id.onlyListView);
        lv.setDivider(null);
        Bundle got = getIntent().getExtras();
        String name = got.getString("KeyfromCheckout");

        Databasecode baseobject = new Databasecode(this);
        Log.d("start", "1");
        Cursor cr = baseobject.searchName(name);
        Log.d("start", "1");
        if (cr != null) {
            for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()) {
                String id = cr.getString(0);
                String rname = cr.getString(1);
                String file_name = "custom" + id + ".png";
                stringList.add(id);
                Data data = new Data(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ParkData/Images/" + file_name), rname, id);
                alist.add(data);
                Log.d("list", "Item Added");


            }
            customAdapter = new CustomAdapter(this, alist);
            Log.d("adptr", "adptr created");
            lv.setAdapter(customAdapter);
            Log.d("adptrt", "adptradded");
        } else {
            Data data = new Data(null, "Not Found", null);
            alist.add(data);

        }


        if (!cr.isClosed()) {
            cr.close();
        }
        lv.setOnItemClickListener(this);



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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String id_from_list = stringList.get(position);
        Bundle bucket = new Bundle();
        bucket.putString("keyid", id_from_list);
        Intent intent = new Intent(SearchResult.this, AfterItemClick.class);
        intent.putExtras(bucket);
        startActivity(intent);
    }
}
