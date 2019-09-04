package com.example.meghan.lafayette_transit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TripBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "lafayette_transit.db";
    public TripBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TripDbSchema.TripTable.NAME+ "(" +
                "_id integer primary key autoincrement," +
                        TripDbSchema.TripTable.Cols.UUID + ", "+
                        TripDbSchema.TripTable.Cols.VEHICLE_VIN+ ", " +
                        TripDbSchema.TripTable.Cols.EMPLOYEE_ID+ "," +
                        TripDbSchema.TripTable.Cols.EMPLOYEE_NAME + "," +
                        TripDbSchema.TripTable.Cols.EMPLOYEE_STATUS+ "," +
                        TripDbSchema.TripTable.Cols.TYPE_OF_OUTING + "," +
                        TripDbSchema.TripTable.Cols.PASSENGER_NAME + "," +
                        TripDbSchema.TripTable.Cols.ROUTE_AM + "," +
                        TripDbSchema.TripTable.Cols.ORIGINATION + "," +
                        TripDbSchema.TripTable.Cols.DESTINATION + "," +
                        TripDbSchema.TripTable.Cols.ODOMETER_START + ", " +
                        TripDbSchema.TripTable.Cols.ODOMETER_END +", " +
                        TripDbSchema.TripTable.Cols.START_TIME + ", " +
                        TripDbSchema.TripTable.Cols.END_TIME + ", " +
                        TripDbSchema.TripTable.Cols.FAR_FEE + "," +
                        TripDbSchema.TripTable.Cols.FAR_COLLECTED +
                        TripDbSchema.TripTable.Cols.TRIP_COMPLETE + ")"

                );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

