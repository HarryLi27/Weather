package com.example.yl34.weather;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Locale;

public class getAPI {
    private static final String OPEN_WEATHER_MAP_URL =
            "api.openweathermap.org/data/2.5/weather?q=%s";

    private static final String OPEN_WEATHER_MAP_API = "8f3e2e163ae68b59cbeae76e4688d546";
    public interface AsyncResponse {
        void processFinish(String output1, String output2, String output3, String output4);
    }
    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {

        public AsyncResponse delegate = null;

        public placeIdTask(AsyncResponse asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            JSONObject jsonWeather = null;
            try {
                jsonWeather = getWeatherJSON(params[0]);
            } catch (Exception e) {
                Log.d("Error", "Cannot process JSON results", e);
            }


            return jsonWeather;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                if(json != null){
                    JSONObject weather = json.getJSONArray("weather").getJSONObject(0);
                    String sky = weather.getString("main");
                    JSONObject main = json.getJSONObject("main");
                    //String description = details.getString("description").toUpperCase(Locale.US);
                    String temperature = main.getString("temp");
                    String humidity = main.getString("humidity") + "%";
                    String pressure = main.getString("pressure") + " hPa";

                    delegate.processFinish(sky, temperature, humidity, pressure);

                }
            } catch (Exception e) {
                //Log.e(LOG_TAG, "Cannot process JSON results", e);
            }
        }
    }
    public static JSONObject getWeatherJSON(String q){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_URL,q));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            connection.addRequestProperty("x-api-key", OPEN_WEATHER_MAP_API);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            return null;
        }
    }
}
