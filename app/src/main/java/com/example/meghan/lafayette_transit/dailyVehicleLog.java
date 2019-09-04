package com.example.meghan.lafayette_transit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.meghan.lafayette_transit.database.TripBaseHelper;
import com.example.meghan.lafayette_transit.database.TripCursorWrapper;
import com.example.meghan.lafayette_transit.database.TripDbSchema;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class dailyVehicleLog {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static dailyVehicleLog dvl;



    public static dailyVehicleLog get(Context context) {
        if (dvl == null)
            dvl = new dailyVehicleLog(context);
        return dvl;
    }
    private dailyVehicleLog(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TripBaseHelper(mContext).getWritableDatabase();

    }
    //method to add trips with menu button
    public void addTrip(Trip t) {
        ContentValues values = getContentValues(t);
        mDatabase.insert(TripDbSchema.TripTable.NAME, null, values);
        //mTrips.add(t);
    }
    //method to delete trips from database
    public void deleteTrip(Trip t)
    {
        mDatabase.delete(TripDbSchema.TripTable.NAME, "uuid=?",
                new String[] {t.getId().toString()} );
    }



    public List<Trip> getTrips() {
        /*
SQLiteDatabase db = dbHelper.getReadableDatabase();
String selcetQuery = "SELECT" +
        Trip.EMPLOYEE_ID +", "
        */

        List<Trip>trips = new ArrayList<>();


        TripCursorWrapper cursor = queryTrips(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                 trips.add(cursor.getTrip());
                cursor.moveToNext();
        }
    }
    finally {
            cursor.close();
          //  mDatabase.close();
        }
        return trips;
        }


    public Trip getTrip(UUID ID) {
        TripCursorWrapper cursor = queryTrips(TripDbSchema.TripTable.Cols.UUID + "=?",
                new String[]{ID.toString()});
        try{
            if(cursor.getCount() ==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTrip();
        }

        finally {
            cursor.close();
        }
    }

    public void updateTrip(Trip trip) {
        String uuidString = trip.getId().toString();
        ContentValues values = getContentValues(trip);

        mDatabase.update(TripDbSchema.TripTable.NAME, values,
                TripDbSchema.TripTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Trip trip) {
        ContentValues values = new ContentValues();
        values.put(TripDbSchema.TripTable.Cols.UUID, trip.getId().toString());
        values.put(TripDbSchema.TripTable.Cols.VEHICLE_VIN, trip.getVehicleVin());
        values.put(TripDbSchema.TripTable.Cols.EMPLOYEE_ID, trip.getEmployeeId());
        values.put(TripDbSchema.TripTable.Cols.EMPLOYEE_NAME, trip.getEmployeeName());
        values.put(TripDbSchema.TripTable.Cols.EMPLOYEE_STATUS, trip.getStatus().toString());
        values.put(TripDbSchema.TripTable.Cols.TYPE_OF_OUTING, trip.getTypeOfOuting().toString());
        values.put(TripDbSchema.TripTable.Cols.PASSENGER_NAME, trip.getPassengerName());
        values.put(TripDbSchema.TripTable.Cols.ROUTE_AM, trip.isRouteAM());
        values.put(TripDbSchema.TripTable.Cols.ORIGINATION, trip.getOrigination());
        values.put(TripDbSchema.TripTable.Cols.DESTINATION, trip.getDestination());
        values.put(TripDbSchema.TripTable.Cols.ODOMETER_START, trip.getOdometerStart());
        values.put(TripDbSchema.TripTable.Cols.ODOMETER_END, trip.getOdometerEnd());
        values.put(TripDbSchema.TripTable.Cols.START_TIME, trip.getTripTimeStart().getTime());
        values.put(TripDbSchema.TripTable.Cols.END_TIME, trip.getTripTimeEnd().getTime());
        values.put(TripDbSchema.TripTable.Cols.FAR_FEE, trip.getFarAmount());
        values.put(TripDbSchema.TripTable.Cols.FAR_COLLECTED, trip.getFarCollected());
        values.put(TripDbSchema.TripTable.Cols.TRIP_COMPLETE, trip.getFarCollected());
        return values;

    }

    private TripCursorWrapper queryTrips(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(TripDbSchema.TripTable.NAME, null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        return new TripCursorWrapper(cursor);
    }
}





