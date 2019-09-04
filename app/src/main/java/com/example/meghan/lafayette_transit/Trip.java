package com.example.meghan.lafayette_transit;

import java.util.Date;
import java.util.UUID;

//enum EMPLOYEE_STATUS {DRIVER, ADMINSTRATOR, WEB_MASTER, VOLUNTEER, X_EMPLOYEE, OTHER}
//enum TYPE_OF_OUTING {SOCIAL, EDUCATION, EMPLOYMENT, MEDICAL, OTHER}

public class Trip {
    private UUID mId;//primary key trip id from database
    private String vehicleVin;
    private String employeeName;
    private String employeeId;
    private EMPLOYEE_STATUS status = EMPLOYEE_STATUS.DEFAULT;
    private TYPE_OF_OUTING typeOfOuting = TYPE_OF_OUTING.DEFAULT;
    private String passengerName;
    private boolean routeAM;
    private boolean mTripComplete;
    private String origination;
    private String destination;
    private int odometerStart;
    private int odometerEnd;
    private Date tripTimeStart;
    private Date tripTimeEnd;
    private double farAmount;
    private double farCollected;
    private Date mTime;


    public Trip() {
        //Generate unique identifier
        this(UUID.randomUUID());
    }

    public Trip(UUID id) {
        this.mId = id;
        this.tripTimeStart = new Date();
        this.tripTimeEnd = new Date(); //default to now to prevent null
    }


    public UUID getId() {
        return mId;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public EMPLOYEE_STATUS getStatus() {
        return status;
    }

    public void setStatus(EMPLOYEE_STATUS status) {
        this.status = status;
    }

    public TYPE_OF_OUTING getTypeOfOuting() {
        return typeOfOuting;
    }

    public void setTypeOfOuting(TYPE_OF_OUTING typeOfOuting) {
        this.typeOfOuting = typeOfOuting;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String isRouteAM() {
        return routeAM == true ? "true" : "false";
    }

    public void setRouteAM(boolean routeAM) {
        this.routeAM = routeAM;
    }

    public String getOrigination() {
        return origination;
    }

    public void setOrigination(String origination) {
        this.origination = origination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getOdometerStart() {
        return odometerStart;
    }

    public void setOdometerStart(int odometerStart) {
        this.odometerStart = odometerStart;
    }

    public int getOdometerEnd() {
        return odometerEnd;
    }

    public void setOdometerEnd(int odometerEnd) {
        this.odometerEnd = odometerEnd;
    }

    public Date getTripTimeStart() {
        return tripTimeStart;
    }

    //public void setTripTimeStart(Date tripTimeStart) {
    //   this.tripTimeStart = tripTimeStart;
    // }

    public boolean isTripComplete() {
        return Boolean.parseBoolean(mTripComplete == true?"Complete":"false");
    }

    public void setTripComplete(boolean mTripComplete) {
        this.mTripComplete = mTripComplete;
    }

    public Date getTripTimeEnd() {
        return tripTimeEnd;
    }

    public void setTripTimeEnd(Date tripTimeEnd) {
        this.tripTimeEnd = tripTimeEnd;
    }

    public double getFarAmount() {
        return farAmount;
    }

    public void setFarAmount(double farAmount) {
        this.farAmount = farAmount;
    }

    public double getFarCollected() {
        return farCollected;
    }

    public void setFarCollected(double farCollected) {
        this.farCollected = farCollected;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Trip))
            return false;
        Trip trip = (Trip) o;

        return getId().equals(trip.getId());
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId='" + mId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeStatus='" + status + '\'' +
                ", typeOfOuting=" + typeOfOuting +
                ", passengerName=" + passengerName +
                ", routeAM=" + routeAM +
                ", origination='" + origination + '\'' +
                ", destination='" + destination + '\'' +
                ", odometerStart=" + odometerStart +
                ", odometerEnd=" + odometerEnd +
                ", tripTimeStart=" + tripTimeStart +
                ", farAmount=" + farAmount +
                ", farCollected=" + farCollected +
                ", tripComplete=" + mTripComplete +
                '}';
    }

}


