package com.example.meghan.lafayette_transit.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.meghan.lafayette_transit.TYPE_OF_OUTING;
import com.example.meghan.lafayette_transit.EMPLOYEE_STATUS;
import com.example.meghan.lafayette_transit.Trip;

import java.util.Date;
import java.util.UUID;

public class TripCursorWrapper extends CursorWrapper {
    public TripCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Trip getTrip() {
        String uuidString = getString(getColumnIndex(TripDbSchema.TripTable.Cols.UUID));
        String vehicleVin = getString(getColumnIndex(TripDbSchema.TripTable.Cols.VEHICLE_VIN));
        String employeeName = getString(getColumnIndex(TripDbSchema.TripTable.Cols.EMPLOYEE_NAME));
        String employeeID = getString(getColumnIndex(TripDbSchema.TripTable.Cols.EMPLOYEE_ID));
        String employeeStatus = getString(getColumnIndex(TripDbSchema.TripTable.Cols.EMPLOYEE_STATUS));
        String typeOfOuting = getString(getColumnIndex(TripDbSchema.TripTable.Cols.TYPE_OF_OUTING));
        String passengerName = getString(getColumnIndex(TripDbSchema.TripTable.Cols.PASSENGER_NAME));
        int routeAm = getInt(getColumnIndex(TripDbSchema.TripTable.Cols.ROUTE_AM));
        String origination = getString(getColumnIndex(TripDbSchema.TripTable.Cols.ORIGINATION));
        String destination = getString(getColumnIndex(TripDbSchema.TripTable.Cols.DESTINATION));
        long odometerStart = getLong(getColumnIndex(TripDbSchema.TripTable.Cols.ODOMETER_START));
        long odometerEnd = getLong(getColumnIndex(TripDbSchema.TripTable.Cols.ODOMETER_END));
        long startTime = getInt(getColumnIndex(TripDbSchema.TripTable.Cols.START_TIME));
        long endTime = getInt(getColumnIndex(TripDbSchema.TripTable.Cols.END_TIME));
        double farAmount = getDouble(getColumnIndex(TripDbSchema.TripTable.Cols.FAR_FEE));
        double farCollected = getDouble(getColumnIndex(TripDbSchema.TripTable.Cols.FAR_COLLECTED));
        boolean tripComplete = Boolean.parseBoolean((TripDbSchema.TripTable.Cols.TRIP_COMPLETE));


        // int isSolved = getInt(getColumnIndex(TripDbSchema.TripTable.Cols.SUBMIT));

        Trip trip = new Trip(UUID.fromString(uuidString));
        trip.setVehicleVin(vehicleVin);
        trip.setEmployeeName(employeeName);
        trip.setEmployeeId(employeeID);
        trip.setPassengerName(passengerName);
        trip.setRouteAM(routeAm != 0);
        trip.setOrigination(origination);
        trip.setDestination(destination);
        trip.setOdometerStart((int) odometerStart);
        trip.setOdometerEnd((int) odometerEnd);
        trip.getTripTimeStart();
        trip.setTripTimeEnd(new Date(endTime));
        trip.setFarAmount(farAmount);
        trip.setFarCollected(farCollected);
        trip.setTripComplete(tripComplete);
        switch (employeeStatus) {
            case "DRIVER":
                trip.setStatus(EMPLOYEE_STATUS.DRIVER);
            case "ADMINISTRATOR":
                trip.setStatus(EMPLOYEE_STATUS.ADMINISTRATOR);
            case "WEB_MASTER":
                trip.setStatus(EMPLOYEE_STATUS.WEB_MASTER);
            case "VOLUNTEER":
                trip.setStatus(EMPLOYEE_STATUS.VOLUNTEER);
            case "X_EMPLOYEE":
                trip.setStatus(EMPLOYEE_STATUS.X_EMPLOYEE);
            case "OTHER":
                trip.setStatus(EMPLOYEE_STATUS.OTHER);
            default:
                trip.setStatus(EMPLOYEE_STATUS.OTHER);
        }
        switch (typeOfOuting) {
            case "SOCIAL":
                trip.setTypeOfOuting(TYPE_OF_OUTING.SOCIAL);
            case "EDUCATIONAL":
                trip.setTypeOfOuting(TYPE_OF_OUTING.EDUCATIONAL);
            case "EMPLOYMENT":
                trip.setTypeOfOuting(TYPE_OF_OUTING.EMPLOYMENT);
            case "MEDICAL":
                trip.setTypeOfOuting(TYPE_OF_OUTING.MEDICAL);
            case "OTHER":
                trip.setTypeOfOuting(TYPE_OF_OUTING.OTHER);
            default:
                trip.setTypeOfOuting(TYPE_OF_OUTING.OTHER);
        }
        return trip;

    }

}
