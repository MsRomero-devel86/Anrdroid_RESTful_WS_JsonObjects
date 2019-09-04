package com.example.meghan.lafayette_transit;

import android.support.v4.app.Fragment;


public class DailyVehicleLogActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment(){ return new TripFragment(); }
}


