package com.example.abhisheksaharn.parkager;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SearchByName extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Toolbar toolbar;
    List<Data> alist = new ArrayList<Data>();
    EditText getname;
    ListView lv;
    TextView tvid, tvname;
    String name;
    Button search;
    private Adapter adapter;
    ArrayList<Data> arrayData;

    CustomAdapter customAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Log.d("start", "1");
        setContentView(R.layout.activity_search_by_name);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        Log.d("start", "1");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView);

        // tvid = (TextView) findViewById(R.id.id);
        // tvname = (TextView) findViewById(R.id.name);

        getname = (EditText) findViewById(R.id.name_to_search);
        search = (Button) findViewById(R.id.button1);

/*
        arrayData =new ArrayList<Data>();
        Data d1=new Data(R.drawable.fab_plus_icon,"Title1","34");
        arrayData.add(d1);
        CustomAdapter ca=new CustomAdapter(this,arrayData);
        lv.setAdapter(ca);

   */


        search.setOnClickListener(this);
        lv.setOnItemClickListener(this);






        /*recyclerView = (RecyclerView) findViewById(R.id.resultList);
        adapter=new Adapter(this,getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
    }

 /*   public static List<Information> getData()
    {
        List<Information> data=new ArrayList<>();
        String[] titles= {"CSE","ECE","MTH","Eng"};
        int[] Icons={R.drawable.abc_btn_check_to_on_mtrl_015,R.drawable.abc_btn_check_to_on_mtrl_015,R.drawable.abc_btn_check_to_on_mtrl_015,R.drawable.abc_btn_check_to_on_mtrl_015,};
        for(int i=0;i<titles.length&&i<Icons.length;i++)
        {
            Information current=new Information();
            current.title=titles[i];
            current.iconId=Icons[i];
            data.add(current);

        }
        return data;
    }
    */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_by_name, menu);
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
        switch (view.getId()) {
            case R.id.button1:


                name = getname.getText().toString();

                Bundle basket = new Bundle();
                basket.putString("Kname", name);
                Intent intent = new Intent(SearchByName.this, SearchResult.class);
                intent.putExtras(basket);
                startActivity(intent);

               /* Log.d("start", "1");
                Databasecode baseobject = new Databasecode(this);
                Log.d("start", "1");
                Cursor cr = baseobject.searchName(name);
                Log.d("start", "1");
                for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()) {
                    String id = cr.getString(0);
                    String rname = cr.getString(1);
                    String file_name = "custom" + id + ".png";
                    Data data = new Data(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ParkData/Images/" + file_name), rname, id);
                    alist.add(data);
                    Log.d("list", "Item Added");


                }


                if (!cr.isClosed()) {
                    cr.close();
                }

                customAdapter = new CustomAdapter(this, alist);
                Log.d("adptr", "adptr created");
                lv.setAdapter(customAdapter);
                Log.d("adptrt", "adptradded");

                //  tvid.setText(id);


                //tvname.setText(rname);  */


                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
