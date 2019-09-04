package com.example.meghan.lafayette_transit.database;

public class TripDbSchema {
    public static final class TripTable {
        public static  final String NAME = "trips";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String VEHICLE_VIN= "vehicleVin";
            public static final String EMPLOYEE_ID = "EmployeeID";
            public static final String EMPLOYEE_NAME = "EmployeeName";
            public static final String EMPLOYEE_STATUS = "employeeStatus";
            public static final String TYPE_OF_OUTING = "typeOfOuting";
            public static final String PASSENGER_NAME = "passengerName";
            public static final String ROUTE_AM = "routeAm";
            public static final String ORIGINATION = "origination";
            public static final String DESTINATION = "destination";
            public static final String ODOMETER_START = "odometerStart";
            public static final String ODOMETER_END = "odometerEnd";
            public static final String START_TIME = "startTime";
            public static final String END_TIME = "endTime";
            public static final String FAR_FEE = "farAmount";
            public static final String FAR_COLLECTED = "farCollected";
            public static final String TRIP_COMPLETE = "tripComplete";

        }

    }
}
