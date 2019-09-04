package com.example.meghan.lafayette_transit;

public enum TYPE_OF_OUTING
{
    SOCIAL("Social", 0),
    EDUCATIONAL("Educational", 1),
    EMPLOYMENT("Employment", 2),
    MEDICAL("Medical", 3),
    OTHER("Other", 4),
    DEFAULT("", 5);

    private String stringValue;
    private int intValue;

    private TYPE_OF_OUTING(String key, int value)
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
