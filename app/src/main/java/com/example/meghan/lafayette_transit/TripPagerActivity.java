package com.example.meghan.lafayette_transit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.meghan.mp4_drivelog.R;

import java.util.List;
import java.util.UUID;

public class TripPagerActivity extends AppCompatActivity {

    private static final String EXTRA_TRIP_ID =
            "com.example.meghan.mp4_drivelog.trip_id";

    private ViewPager mViewPager;
    private List<Trip> mTrip;


    public static Intent newIntent(Context packageContext, UUID tripId) {
        Intent intent = new Intent(packageContext, TripPagerActivity.class);
        intent.putExtra(EXTRA_TRIP_ID, tripId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_pager);

        UUID tripID = (UUID) getIntent().getSerializableExtra(EXTRA_TRIP_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_trip_pager_view_pager);

        mTrip = dailyVehicleLog.get(this).getTrips();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Trip trip = mTrip.get(position);
                return TripFragment.newInstance(trip.getId());
            }

            @Override
            public int getCount() {
                return mTrip.size();
            }
        });

        for (int i = 0; i < mTrip.size(); i++)
        {
            if (mTrip.get(i).getId().equals(tripID))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }


    }
}
