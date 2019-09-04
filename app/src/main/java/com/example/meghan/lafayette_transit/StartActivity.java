package com.example.meghan.lafayette_transit;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.meghan.mp4_drivelog.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StartActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://10.0.2.2:8080/Trips/webresources/entity.trip";
    String jsonString = "{\"am\":false,\"destination\":\"Bertrand Drive\",\"employee\":{\"employeeId\":\"100\",\"employeeName\":\"Paul Newman\",\"employeeStatus\":\"DRIVER\"},\"fareAmount\":2.00,\"fareAmountCollected\":0.00,\"isTripComplete\":false,\"odometerEnd\":10,\"odometerStart\":100000,\"origination\":\"Bertratnd Drive\",\"tripPK\":{\"employeeId\":\"100\",\"tripId\":\"8889\",\"vehicleVin\":\"v1000\"},\"tripTimeEnd\":\"2019-04-05T05:00:00Z[UTC]\",\"tripTimeStart\":\"2019-04-08T18:38:58Z[UTC]\",\"typeOfOuting\":\"SOCIAL\",\"vehicle\":{\"odometer\":10,\"vehicleId\":\"1\",\"vehicleVin\":\"v1000\"}}";
    //private static final int  VERSION = 2;
    String tripList = "Trip id: " + "8881" + "\n" +
            "Passenger Name: " + "John Wayne" + "\n" +
            "Origination: " + "123 Main St Lafayette La, 70506" + "\n" +
            "Destination: " + "098 West Drive Lafayette LA, 70506" + "\n" +
            "Odometer Start:" + "12000" + "\n" +
            "Odometer End: " + "1260" + "\n" +
            "Trip Time Start: " +"8:00 AM Tuesday 1, 2019"+ "\n" +
            "Trip time End:" + "9:00 AM Tuesday 1, 2019" + "\n" +
            "Type of Outing: " + "MEDICAL" + "\n" +
            "Employee Status: " + "DRIVER" + "\n" +
            "Fare Amount: " + "0.00" + "\n" +
            "Fare Amount Collected: " +"0.00" + "\n";
    ProgressBar progBar;
    String resultTXT, editTxt;
    Button mListDaybtn, mLogOut, mSendTrip, mVehicleInscp, mManualBtn;
    TextView mListTripView;
    //EditText mEmployeeId;
    String data = "  ";
    String dataSingle = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        progBar = (ProgressBar) findViewById(R.id.progressBar);
        mListTripView = (TextView) findViewById(R.id.tripListTView);
        //mEmployeeId = (EditText)findByViewById(R.id.employeeId_ET)
        mListDaybtn = (Button) findViewById(R.id.listTripBtn);
        mManualBtn = (Button) findViewById(R.id.addTripBtn);
        mManualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TripListActivity.class));
            }
        });

        mLogOut = (Button) findViewById(R.id.logoutBtn);
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        mListDaybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AsyncCallWS task = new AsyncCallWS();
                task.execute();
                mListTripView.setText("Trips : " + tripList);
         /*String s = mEmployeeId.getText().toString();
          if(s.equals().getEmployeeid() && !isTripComplete()){
                mListTripView.setText("Trips : " + dataSingle);      }
         * else if(!s.equals("100"))
         * {ListTripView.setText("Incorrect Employee Id");
         * else{
         * ListTripView.setText("No Trips Found");*/
            }
        });
    }


    public class AsyncCallWS extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            //  super.onPreExecute();
            progBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            resultTXT = RestService.invokeTripRest();
            return null;
        }
    }
}
/******************
            String line = " ";
            try {

                URL url;
                //make connection to URL
                HttpURLConnection connection = null;
                try {
                    url = new URL(BASE_URL);
                    connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();//read data
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    int data = inputStream.read();
                    while (data != -1) {
                        line += (char) data;
                        data = inputStream.read();
                        System.out.println(line);
                    }
                    return line;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return dataSingle;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("data", result);
                String dataParse = " ";
            progBar.setVisibility(View.INVISIBLE);
            try {
                JSONArray ja = new JSONArray(data);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    dataSingle = "Trip id: " + jo.getString("trip_id") + "\n" +
                            "Passenger Name: " + jo.getString("passenger_name_AM_route") + "\n" +
                            "Origination: " + jo.getString("origination") + "\n" +
                            "Destination: " + jo.getString("destination") + "\n" +
                            "Odometer Start:" + jo.getString("odometer_start") + "\n" +
                            "Odometer End: " + jo.getString("odometer_end") + "\n" +
                            "Trip Time Start: " + jo.getString("trip_time_start") + "\n" +
                            "Trip time End:" + jo.getString("trip_time_end") + "\n" +
                            "Type of Outing: " + jo.getString("type_of_outing") + "\n" +
                            "Employee Status: " + jo.getString("Employee_status") + "\n" +
                            "Fare Amount: " + jo.getString("fare_amount") + "\n" +
                            "Fare Amount Collected: " + jo.getString("fare_collected") + "\n";

                    dataParse = dataParse + dataSingle + "\n";
                    mListTripView.setText("Trips: " + dataParse);
                }


            } catch (
                    JSONException e) {
                e.printStackTrace();


            }
        }
    }
}
    /*

    private class AsyncCallTripRSpost
            extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            resultTXT = RestService.post();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mListTripView.setText(resultTXT);
            progBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            progBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }


    private class AsyncCallTripRSput
            extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            resultTXT = RestService.put();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mListTripView.setText(resultTXT);
            progBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            progBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    private class AsyncCallTripRSdelete
            extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            resultTXT = RestService.delete();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mListTripView.setText(resultTXT);
            progBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            progBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }
}
/**************************
      return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mListTripView.setText(resultTXT);
            progBar.setVisibility(View.INVISIBLE);
        }
    }****************************/






