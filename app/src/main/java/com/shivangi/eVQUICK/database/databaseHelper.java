package com.shivangi.eVQUICK.database;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.shivangi.eVQUICK.models.Slots;

import java.util.ArrayList;


public class databaseHelper {


    public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String TAG = DatabaseHelper.class.getSimpleName();

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "charging_station_db";

        private SQLiteDatabase mDatabase;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // CREATE Table totalslots
            db.execSQL(Slots.sqlQuery);
            Log.d(TAG, "Created Table : " + Slots.TABLE_NAME);
            
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + Slots.TABLE_NAME);

            // Create tables again
            onCreate(db);
        }

        public long insertSlots(Slots slots) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Slots.COLUMN_TOTAL_SLOTS, Slots.getTotalSlots());
            contentValues.put(Slots.COLUMN_AVAILABLE_SLOTS, Slots.getAvailableSlots());

            // timestamp date will be added automatically;

            long id = db.insert(Slots.TABLE_NAME, null, contentValues);

            // close db connection
            db.close();

            Log.d(TAG, "InsertedSlotDetail: " + slots.getTotalSlots());
            return id;
        }

        public Slots getSlotDetail(long id) {

            // get readable database as we are not inserting anything
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(Slots.TABLE_NAME,
                    new String[]{Slots.COLUMN_ID, Slots.COLUMN_TOTAL_SLOTS,
                            Slots.COLUMN_AVAILABLE_SLOTS
                    },
                    id + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            @SuppressLint("Range") Slots slots = new Slots(
                    cursor.getInt(cursor.getColumnIndex(Slots.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(Slots.COLUMN_TOTAL_SLOTS)),
                    cursor.getString(cursor.getColumnIndex(Slots.COLUMN_AVAILABLE_SLOTS))

            );

            // close the db connection
            cursor.close();

            return slots;
        }

        public ArrayList<Slots> getAllSlotsDetail() {
            // Select All Query
//        String selectQuery = "SELECT  * FROM " + Slots.TABLE_NAME + " ORDER BY " +
//                Slots.COLUMN_ID + " DESC";

            String selectQuery = "SELECT  * FROM " + Slots.TABLE_NAME ;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            ArrayList<Slots> slotsList = new ArrayList<>();
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") Slots slots = new Slots(
                            cursor.getInt(cursor.getColumnIndex(Slots.COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(Slots.COLUMN_TOTAL_SLOTS)),
                            cursor.getString(cursor.getColumnIndex(Slots.COLUMN_AVAILABLE_SLOTS))
                    );
                    slotsList.add(slots);
                } while (cursor.moveToNext());
            }
            db.close();

            return slotsList;
        }

        public void cleanTable() {
            String truncateQuery = "DELETE FROM " + Slots.TABLE_NAME;
            SQLiteDatabase db = this.getWritableDatabase();

            // delete all the record
            db.execSQL(truncateQuery);

            Log.d(TAG, "cleanTable(): ");
            db.close();

        }

        public int getSlotsCount() {
            String countQuery = "SELECT  * FROM " + Slots.TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);

            int count = cursor.getCount();

            Log.d(TAG, "getSlotsCount: " + count);
            cursor.close();


            // return count
            return count;
        }

        public boolean isSlotsDetailEmpty() {
            // if slotscount() == 0 return true else false
            return getSlotsCount() != 0? false : true;
        }

        public boolean isTableExists(String tableName, boolean openDb) {
            if(openDb) {
                if(mDatabase == null || !mDatabase.isOpen()) {
                    mDatabase = getReadableDatabase();
                }

                if(!mDatabase.isReadOnly()) {
                    mDatabase.close();
                    mDatabase = getReadableDatabase();
                }
            }

            Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
            if(cursor!=null) {
                if(cursor.getCount()>0) {
                    cursor.close();
                    return true;
                }
                cursor.close();
            }
            return false;
        }
    }
}
