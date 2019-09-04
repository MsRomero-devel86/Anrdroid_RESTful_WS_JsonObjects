package com.example.meghan.lafayette_transit;


import android.app.Activity;
import android.util.Log;
//import org.apache.commons.io.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RestService extends Activity {
    private static final String BASE_URL = "http://10.0.2.2:8080/Trips/webresources/entity.trip";


    /* private static AsyncHttp client = new AsyncHttpClient();
      public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
       client.get(getAbsoluteUrl(url), params, responseHandler);
   }
/*
   public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
       client.post(getAbsoluteUrl(url), params, responseHandler);
   }
 */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL;
    }

    public static String invokeTripRest() {
        String result = " ";
        try {
            java.net.URL url = new URL(BASE_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
              urlConnection.setRequestMethod("GET");
             urlConnection.setRequestMethod("POST");
            //  urlConnection.setRequestMethod("DELETE");
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json;charset=UTF-8");
            // urlConnection.setRequestProperty("Expect", "100-continue");
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

           /********************************************************
            if (inputStream != null)
            result = IOUtils.toString(inputStream);
        }
        catch (Exception e) {
            Log.d("Error", e.getMessage());
            return e.getMessage();
        }
/******************************************************************************/
    int data = 0;

            while (data != -1) {
                result += (char) data;
                data = inputStream.read();
                System.out.println(result);
            }
            return result;

        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }


        return result;

    }
}
/*****************************************************
    public static String put() {
        Integer code = -1;
        String ENDPOINT_URL_TRIP = "http://10.0.2.2:8080/Trips/webresources/entityclasses.trip/";
        String idOfForeignKeys = "1;tripId=8803;employeeId=100;vehicleVin=v1000";
        String jsonString = "{\"am\":false,\"destination\":\"Bertrand Drive\",\"employee\":{\"employeeId\":\"100\",\"employeeName\":\"Paul Newman\",\"employeeStatus\":\"DRIVER\"},\"fareAmount\":2.00,\"fareAmountCollected\":0.00,\"isTripComplete\":false,\"odometerEnd\":10,\"odometerStart\":100000,\"origination\":\"Bertratnd Drive\",\"tripPK\":{\"employeeId\":\"100\",\"tripId\":\"8889\",\"vehicleVin\":\"v1000\"},\"tripTimeEnd\":\"2019-04-05T05:00:00Z[UTC]\",\"tripTimeStart\":\"2019-04-08T18:38:58Z[UTC]\",\"typeOfOuting\":\"SOCIAL\",\"vehicle\":{\"odometer\":10,\"vehicleId\":\"1\",\"vehicleVin\":\"v1000\"}}";
        try {
            URL url = new URL(ENDPOINT_URL_TRIP + idOfForeignKeys);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("PUT");
            c.setDoOutput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            OutputStreamWriter osw = new OutputStreamWriter(c.getOutputStream());
            osw.write(jsonString);
            osw.flush();
            osw.close();

            code = c.getResponseCode();
            System.out.println(c.getResponseCode());
        } catch (Exception e) {
            System.out.println(e);
        }
        return code.toString();
    }

    public static String post() {
        Integer code = -1;
        String ENDPOINT_URL_TRIP = "http://10.0.2.2:8080/Trips/webresources/entityclasses.trip/trips/";
        String jsonString = "[{\"am\":true,\"destination\":\"456\",\"employee\":{\"employeeId\":\"100\",\"employeeName\":\"John Wayne\",\"employeeStatus\":\"DRIVER\"},\"fareAmount\":0.00,\"fareAmountCollected\":0.00,\"isTripComplete\":false,\"odometerEnd\":10,\"odometerStart\":10,\"origination\":\"123\",\"tripPK\":{\"employeeId\":\"100\",\"tripId\":\"899\",\"vehicleVin\":\"v1000\"},\"tripTimeEnd\":\"2019-04-05T05:00:00Z[UTC]\",\"tripTimeStart\":\"2019-04-08T18:38:58Z[UTC]\",\"typeOfOuting\":\"SOCIAL\",\"vehicle\":{\"odometer\":10,\"vehicleId\":\"1\",\"vehicleVin\":\"v1000\"}}]";
        String reqBody = jsonString;
        try {
            URL url = new URL(ENDPOINT_URL_TRIP);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(reqBody);
            osw.flush();
            osw.close();
            code = connection.getResponseCode();
            System.out.println(connection.getResponseCode());
        } catch (Exception e) {
            System.out.println(e);
        }
        return code.toString();
    }


    public static String delete() {
        Integer code = -1;
        String ENDPOINT_URL_TRIP = "http://10.0.2.2:8080/Trips/webresources/entityclasses.trip/";
        String idOfForeignKeys = "1;tripId=88;employeeId=100;vehicleVin=v1000";
        try {
            URL url = new URL(ENDPOINT_URL_TRIP + idOfForeignKeys);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("DELETE");
            c.setDoOutput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");


            code = c.getResponseCode();
            System.out.println(c.getResponseCode());
        } catch (Exception e) {
            System.out.println(e);
        }
        return code.toString();
    }
}
/*******************************************************************************************/
