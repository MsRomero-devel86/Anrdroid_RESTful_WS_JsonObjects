package com.example.meghan.lafayette_transit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meghan.mp4_drivelog.R;

import java.util.List;

public class TripListFragment extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mTripRecyclerView;
    private TripAdapter mAdapter;
    private int mPosition;
    private boolean mSubtitleVisible;
   // private Button mListTrip;

  //  TextView mTripListView;
    //ProgressBar proBar;
    //String txt;
    private LayoutInflater inflater;


    //this method will create the callbacks for the add button in menu
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_trip_list
                , container, false);

        mTripRecyclerView = (RecyclerView) view.findViewById(R.id.trip_recycler_view);
        mTripRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    //this method will create the add button.that is connected with fragment_crime_list layout.
    //show add button and subtitkes in the action bar if one is present
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_trip_list, menu);
        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    //method to create the crimes as you add them with the button menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_trip:
                Trip trip = new Trip();
                dailyVehicleLog.get(getActivity()).addTrip(trip);
                Intent intent = TripPagerActivity.newIntent(getActivity(),
                        trip.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    //methods will display the subtitles next too the add button
    private void updateSubtitle() {
        dailyVehicleLog dvl = dailyVehicleLog.get(getActivity());
        int tripCount = dvl.getTrips().size();
        String subtitle = getString(R.string.subtitle_format, tripCount);
        if (!mSubtitleVisible) {//showing no subtitles
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        dailyVehicleLog dvl = dailyVehicleLog.get(getActivity());
        List<Trip> trips = dvl.getTrips();

        if (mAdapter == null) {
            mAdapter = new TripAdapter(trips);
            mTripRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyItemChanged(mPosition);
            mAdapter.setTrips(trips);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }

    private class TripHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mTripIdTextView;
        private TextView mOriginOfTripView;
        //private CheckBox mSolvedCheckBox;
        private Trip mTrip;

        public TripHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTripIdTextView = itemView.findViewById(R.id.list_item_trip_id_text_view);
            mOriginOfTripView = itemView.findViewById(R.id.origination_text);
          //  mSolvedCheckBox = itemView.findViewById(R.id.trip_solved);


        }

        public void bindTrip(Trip trip) {
            mTrip = trip;
            mTripIdTextView.setText(mTrip.getId().toString());
            mOriginOfTripView.setText(mTrip.getOrigination());

        }

        @Override
        public void onClick(View v) {
            mPosition = getAdapterPosition();
            Intent intent =TripPagerActivity.newIntent(getActivity(), mTrip.getId());
            startActivity(intent);
        }
    }


    private class TripAdapter extends RecyclerView.Adapter<TripHolder> {
        private List<Trip> mTrips;

        public TripAdapter(List<Trip> trips) {
            mTrips = trips;
        }

        @Override
        public TripHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_trip, parent, false);
            return new TripHolder(view);
        }

        @Override
        public void onBindViewHolder(TripHolder tripHolder, int position) {
            Trip trip = mTrips.get(position);
              tripHolder.bindTrip(trip);
        }


        @Override
        public int getItemCount() {
            return mTrips.size();
        }

        public void setTrips(List<Trip> trips) {
            mTrips = trips;
        }
    }



}