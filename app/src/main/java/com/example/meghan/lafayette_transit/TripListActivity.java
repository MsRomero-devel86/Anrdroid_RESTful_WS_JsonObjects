package com.example.meghan.lafayette_transit;

import android.support.v4.app.Fragment;

public class TripListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TripListFragment();
    }
}
