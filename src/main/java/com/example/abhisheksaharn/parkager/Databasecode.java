package com.example.abhisheksaharn.parkager;

/**
 * Created by Abhishek Saharn on 3/25/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Databasecode {
    public static final String KEY_CID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_VNUMBER = "Vehi_number";
    public static final String KEY_MODEL = "Vehi_model";
    public static final String KEY_HOUR = "Hrs";
    public static final String KEY_MINS = "Mins";
    public static final String KEY_DATE = "dates";
    public static final String KEY_MNTH = "Month";
    public static final String KEY_YEAR = "Year";

    public static final String DATABASE_NAME = "Park";
    public static final String DATABASE_TABLE = "Parking_table";
    public static final int DATABASE_VERSION = 1;


    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    public ArrayList<String> retList = new ArrayList<>(10);

    public Cursor searchName(String names) {
        Log.d("cur", "start of search function");
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getReadableDatabase();
        Cursor srchCursor = ourDatabase.rawQuery("SELECT " + KEY_CID + "," + KEY_NAME + " from " + DATABASE_TABLE + " where " + KEY_NAME + "='" + names + "';", null);

        if (srchCursor != null) {
            Log.d("cur", "hhuhuhu");
        }

        return srchCursor;
    }

    public String findDate(long id) {
        String st = "";
        String[] columns = new String[]{KEY_CID, KEY_NAME, KEY_NUMBER,
                KEY_VNUMBER, KEY_MODEL, KEY_HOUR, KEY_MINS, KEY_DATE, KEY_MNTH,KEY_YEAR};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_CID + "="
                + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            st = c.getString(7);
            return st;
        }
        return null;

    }

    public Cursor getReport(String fromDate, String fromMonth, String fromYear, String toDate, String toMonth, String toYear) {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getReadableDatabase();
        int ifromDate=Integer.parseInt(fromDate);
        int itoDate=Integer.parseInt(toDate);
        int ifromMonth=Integer.parseInt(fromMonth);
        int itoMonth=Integer.parseInt(toMonth);
        int ifromYear=Integer.parseInt(fromYear);
        int itoYear=Integer.parseInt(toYear);


        Cursor cr = ourDatabase.rawQuery("Select "+ KEY_CID + "," + KEY_NAME +" from " + DATABASE_TABLE + " where ( "+KEY_DATE + " BETWEEN " + ifromDate +" AND  "+ itoDate+" ) AND ( " +
                                            KEY_MNTH+" BETWEEN "+ifromMonth+" AND "+itoMonth+" ) AND ( "+KEY_YEAR+" BETWEEN "+ifromYear+" AND "+itoYear+");" ,null);
        return cr;

    }


    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_CID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                    + " TEXT NOT NULL, " + KEY_NUMBER + " TEXT NOT NULL, "
                    + KEY_VNUMBER + " TEXT NOT NULL, " + KEY_MODEL
                    + " TEXT NOT NULL, " + KEY_HOUR + " INTEGER NOT NULL, "
                    + KEY_MINS + " INTEGER NOT NULL, " + KEY_DATE
                    + " INTEGER NOT NULL, " + KEY_MNTH + " INTEGER NOT NULL, " + KEY_YEAR + " INTEGER NOT NULL);");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("Drop Table If Exists" + DATABASE_TABLE);
            onCreate(db);

        }

    }

    /*--------------------------------------*/
    public Databasecode(Context c) {
        ourContext = c;
    }

    public Databasecode open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long create(String name, String number, String vehiNumber,
                       String vehiModel, int hour, int minute, int date, int mnth, int year) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_NUMBER, number);
        cv.put(KEY_VNUMBER, vehiNumber);
        cv.put(KEY_MODEL, vehiModel);
        cv.put(KEY_HOUR, hour);
        cv.put(KEY_MINS, minute);
        cv.put(KEY_DATE, date);
        cv.put(KEY_MNTH, mnth);
        cv.put(KEY_YEAR, year);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);



    }

    public String getData() {
        // TODO Auto-generated method stub
        String result = " ";
        String[] columns = new String[]{KEY_CID, KEY_NAME, KEY_NUMBER,
                KEY_VNUMBER, KEY_MODEL, KEY_HOUR, KEY_MINS, KEY_DATE, KEY_MNTH, KEY_YEAR};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
                null, null);
        int iRow = c.getColumnIndex(KEY_CID);
        int iname = c.getColumnIndex(KEY_NAME);
        int inumber = c.getColumnIndex(KEY_NUMBER);
        int ivnumber = c.getColumnIndex(KEY_VNUMBER);
        int ivmodel = c.getColumnIndex(KEY_MODEL);
        int iHour = c.getColumnIndex(KEY_HOUR);
        int iMins = c.getColumnIndex(KEY_MINS);
        int idate = c.getColumnIndex(KEY_DATE);
        int iMnth = c.getColumnIndex(KEY_MNTH);
        int iYear = c.getColumnIndex(KEY_YEAR);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iname)
                    + " " + c.getString(inumber) + " " + c.getString(ivnumber)
                    + " " + c.getString(ivmodel) + " " + c.getString(iHour)
                    + ":" + c.getString(iMins) + " " + c.getString(idate) + "/"
                    + c.getString(iMnth) + "/"
                    + c.getString(iYear) + "\n";

        }
        return result;
    }

    public int getRowId() {
        String result = "";
        String[] row = new String[]{KEY_CID};
        Cursor rowC = ourDatabase.query(DATABASE_TABLE, row, null, null, null,
                null, null);

        int icid = rowC.getColumnIndex(KEY_CID);
        for (rowC.moveToFirst(); !rowC.isAfterLast(); rowC.moveToNext()) {
            if (rowC.isLast())
                result = rowC.getString(icid);
        }
        return Integer.parseInt(result);

    }

    public String findVehicalNumber(long id) {
        // TODO Auto-generated method stub
        String st = "";
        String[] columns = new String[]{KEY_CID, KEY_NAME, KEY_NUMBER,
                KEY_VNUMBER, KEY_MODEL, KEY_HOUR, KEY_MINS, KEY_DATE, KEY_MNTH};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_CID + "="
                + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            st = c.getString(3);
            return st;
        }
        return null;
    }

    public String findDescription(long id) {
        // TODO Auto-generated method stub
        String st = "";
        String[] columns = new String[]{KEY_CID, KEY_NAME, KEY_NUMBER,
                KEY_VNUMBER, KEY_MODEL, KEY_HOUR, KEY_MINS, KEY_DATE, KEY_MNTH};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_CID + "="
                + id, null, null, null, null);
        if (c != null) {

            c.moveToFirst();
            st = c.getString(4);
            return st;
        }
        return null;
    }

    public String findCustomerName(long id) {
        // TODO Auto-generated method stub
        String st = "";
        String[] columns = new String[]{KEY_CID, KEY_NAME, KEY_NUMBER,
                KEY_VNUMBER, KEY_MODEL, KEY_HOUR, KEY_MINS, KEY_DATE, KEY_MNTH};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_CID + "="
                + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            st = c.getString(1);
            return st;
        }
        return null;
    }

    public String findHour(long id) {
        // TODO Auto-generated method stub
        String st = "";
        String[] columns = new String[]{KEY_CID, KEY_NAME, KEY_NUMBER,
                KEY_VNUMBER, KEY_MODEL, KEY_HOUR, KEY_MINS, KEY_DATE, KEY_MNTH};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_CID + "="
                + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            st = c.getString(5);
            return st;
        }
        return null;
    }

    public String findMinute(long id) {
        // TODO Auto-generated method stub
        String st = "";
        String[] columns = new String[]{KEY_CID, KEY_NAME, KEY_NUMBER,
                KEY_VNUMBER, KEY_MODEL, KEY_HOUR, KEY_MINS, KEY_DATE, KEY_MNTH};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_CID + "="
                + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            st = c.getString(6);
            return st;
        }
        return null;
    }

}

