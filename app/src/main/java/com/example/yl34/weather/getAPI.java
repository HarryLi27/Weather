package com.example.yl34.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getAPI {
    public static final String openweather_URL =
            "http://api.openweathermap.org/data/2.5/weather?q=%s";
    public static final String openweatherAPI = "8f3e2e163ae68b59cbeae76e4688d546";
    public static String sky;
    public static double minTemp;
    public static double maxTemp;
    
    /**public static JSONObject getWeatherJSON(String q) {
        try {
            URL url = new URL(String.format(openweather_URL, q));
            //System.out.println(url);
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("x-api-key", openweatherAPI);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();
            JSONObject weather = new JSONObject(json.toString());
            if(weather.getInt("cod") != 200){
                return null;
            }
            return weather;
        } catch(Exception e) {
            return null;
        }
    }
    public static void getWeatherInfo(JSONObject json) {
        try {
            JSONObject list = json.getJSONArray("list").getJSONObject(0);
            JSONArray weather = list.getJSONArray("weather");
            sky = weather.getJSONObject(0).getString("main");
            JSONObject main = list.getJSONObject("main");
            minTemp = main.getDouble("temp_min");
            maxTemp = main.getDouble("temp_max");
        } catch (Exception e) { }
    }*/
}
