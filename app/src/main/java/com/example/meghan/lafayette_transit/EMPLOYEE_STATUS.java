package com.example.meghan.lafayette_transit;

public enum EMPLOYEE_STATUS
{
    DRIVER("Driver", 0),
    ADMINISTRATOR("Administrator", 1),
    WEB_MASTER("Web Master", 2),
    VOLUNTEER("Voluteer", 3),
    X_EMPLOYEE("X Employee", 4),
    OTHER("Other", 5),
    DEFAULT("", 6);


    private String stringValue;
    private int intValue;

    private EMPLOYEE_STATUS(String key, int value)
    {
        stringValue = key;
        intValue = value;
    }

    @Override
    public String toString()
    {
        return stringValue;
    }
}
