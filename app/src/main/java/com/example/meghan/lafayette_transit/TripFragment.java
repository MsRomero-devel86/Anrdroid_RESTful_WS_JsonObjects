package com.example.meghan.lafayette_transit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.meghan.mp4_drivelog.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TripFragment extends Fragment {
    private static final String ARG_TRIP_ID = "trip_id";
    //private static final String DIALOG_DATE = "DialogDate";
    // private static final String DIALOG_TIME ="DialogTime";
  //  private static final int REQUEST_DATE = 0;
    // private static final int REQUEST_TIME = 1;

    private Trip mTrip;
    //private Button mDateButton;
    // private Button mTimeButton;
    private TextView mTripIdField;
    private EditText mVehicleVin;
    private EditText mEmployeeId;
    private EditText mEmployeeName;
    private EditText mPassengerName;
    private EditText mOrigination;
    private EditText mDestination;
    private EditText mOdometerStart;
    private EditText mOdometerEnd;
    private EditText tripTimeStart;
    private EditText tripTimeEnd;
    private EditText mFarAmount;
    private EditText mFarCollected;
    private CheckBox mSubmit;
    private Spinner statusSnip, typeSpin;

    public TripFragment() {
        // Required empty public constructor
    }

    //these following functions set the views by calling trip UUID with serializable
    //get arugs and activity between dailyVehicle log and this class
    public static TripFragment newInstance(UUID tripId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRIP_ID, tripId);

        TripFragment fragment = new TripFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID tripId = (UUID) getArguments().getSerializable(ARG_TRIP_ID);
        mTrip = dailyVehicleLog.get(getActivity()).getTrip(tripId);

    }

    @Override
    public void onPause() {
        super.onPause();
        dailyVehicleLog.get(getActivity()).updateTrip(mTrip);
    }

    //create the on view for all textflieds for all varables and the drop down menu
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trip, container, false);
        setHasOptionsMenu(true);



        mTripIdField = (TextView) v.findViewById(R.id.list_item_trip_id_text_view);

    /*    tripTimeStart= (Button) v.findViewById(R.id.trip_StartTime);
        updateTime();
        tripTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mTrip.getTime());
                dialog.setTargetFragment(TripFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });*/

        mVehicleVin = (EditText) v.findViewById(R.id.vehicleID_text);
        mVehicleVin.setText(mTrip.getVehicleVin());
        mVehicleVin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setVehicleVin(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
            }
        });

        mEmployeeId = (EditText) v.findViewById(R.id.employeeID_text);
        mEmployeeId.setText(mTrip.getEmployeeId());
        mEmployeeId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setEmployeeId(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
            }
        });

        mEmployeeName = (EditText) v.findViewById(R.id.driverName_text);
        mEmployeeName.setText(mTrip.getEmployeeName());
        mEmployeeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setEmployeeName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
            }
        });

        mPassengerName = (EditText) v.findViewById(R.id.passengerName_text);
        mPassengerName.setText(mTrip.getPassengerName());
        mPassengerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setPassengerName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
            }
        });

        mOrigination = (EditText) v.findViewById(R.id.origination_text);
        mOrigination.setText(mTrip.getOrigination());
        mOrigination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setOrigination(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
            }
        });
        mDestination = (EditText) v.findViewById(R.id.destination_text);
        mDestination.setText(mTrip.getDestination());
        mDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setDestination(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
                //int i = Integer.parseInt(editText.getText().toString());
            }
        });

        mOdometerStart = (EditText) v.findViewById(R.id.odometerStart_text);
        mOdometerStart.setText(String.valueOf(mTrip.getOdometerStart()));
        mOdometerStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setOdometerStart(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mOdometerEnd = (EditText) v.findViewById(R.id.odometerEnd_text);
        mOdometerEnd.setText(String.valueOf(mTrip.getOdometerEnd()));
        mOdometerEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setOdometerEnd(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                //no text
            }
        });

//create spinner that will hold enum choices

        List<String> empStatus = new ArrayList<>();
        empStatus.add(EMPLOYEE_STATUS.DRIVER.toString());
        empStatus.add(EMPLOYEE_STATUS.ADMINISTRATOR.toString());
        empStatus.add(EMPLOYEE_STATUS.VOLUNTEER.toString());
        empStatus.add(EMPLOYEE_STATUS.WEB_MASTER.toString());
        empStatus.add(EMPLOYEE_STATUS.X_EMPLOYEE.toString());
        empStatus.add(EMPLOYEE_STATUS.OTHER.toString());
        statusSnip = (Spinner) v.findViewById(R.id.status_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, empStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        statusSnip.setAdapter(adapter);

        List<String> outingType = new ArrayList<>();
        outingType.add(TYPE_OF_OUTING.EDUCATIONAL.toString());
        outingType.add(TYPE_OF_OUTING.EMPLOYMENT.toString());
        outingType.add(TYPE_OF_OUTING.MEDICAL.toString());
        outingType.add(TYPE_OF_OUTING.SOCIAL.toString());
        outingType.add(TYPE_OF_OUTING.OTHER.toString());
        typeSpin = (Spinner) v.findViewById(R.id.type_outing_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, outingType);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeSpin.setAdapter(adapter1);


      /*  Spinner spinn = (Spinner) v.findViewById(R.id.type_outing_spinner);
        String statusList[] = {"EMPLOYEE", "FORMER_EMPLOYEE", "VOLUNTEER", "OTHER"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, statusList);
        // R.array.type_outing, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(adapter2);
        spinn.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statusSnip.getSelectedItem().toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spin = (Spinner) v.findViewById(R.id.type_outing_spinner);
        String typeOFOutingList[] = {"MEDICAL", "NONMEDICAL", "SCHOOL", "WORK", "OTHER"};
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, typeOFOutingList);
        //   R.array.type_outing, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(adapter);
        spin.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeSpin.getSelectedItem().toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/

        tripTimeStart = (EditText) v.findViewById(R.id.trip_StartTime);
        tripTimeStart.setText(mTrip.getTripTimeStart().toString());
        tripTimeStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.getTripTimeStart();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //no text
            }
        });

        tripTimeEnd = (EditText) v.findViewById(R.id.tripEndTime_text);
        tripTimeEnd.setText(mTrip.getTripTimeEnd().toString());
        tripTimeEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setTripTimeEnd(new Date());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //no text
            }
        });

        mFarAmount = (EditText) v.findViewById(R.id.fareAmount_text);
        mFarAmount.setText(String.valueOf(mTrip.getFarAmount()));
        mFarAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setFarAmount(Double.parseDouble(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                //no text
            }
        });
        mFarCollected = (EditText) v.findViewById(R.id.fareCollected_text);
        mFarCollected.setText(String.valueOf(mTrip.getFarCollected()));
        mFarCollected.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setFarCollected(Double.parseDouble(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                //no text
            }
        });

       mSubmit = (CheckBox)v.findViewById(R.id.trip_solved);
       mSubmit.setChecked(mTrip.isTripComplete());
       mSubmit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
               mTrip.setTripComplete(isChecked);
           }
       });

        return v;
    }

    //inflate the menu view for the delete trip
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_trip, menu);
    }

    //create an opition menu bar and set new Intent to start new activity
    //create menu delete button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete_trip:
                dailyVehicleLog.get(getActivity()).deleteTrip(mTrip);
                Intent intent = new Intent(getContext(), TripListActivity.class);//!!!!
                startActivity(intent);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTrip.setTripTimeEnd(date);
            updateDate();
            //mDateButton.setText(mCrime.getDate().toString());
        }
        /*
        if (requestCode == REQUEST_TIME) {
            Date time = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mTrip.setTime(time);
            updateTime();
        }*/
    /*}

    private void updateDate() {

        mDateButton.setText(mTrip.getTripTimeEnd().toString());
    }
    // private void updateTime() {

    //   mTimeButton.setText(mTrip.getTime().toString());


    //}
    */
}
