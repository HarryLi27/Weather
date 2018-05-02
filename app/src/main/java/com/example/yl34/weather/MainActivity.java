package com.example.yl34.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import static com.example.yl34.weather.getAPI.*;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public String sky;
    public String
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    private static final String TAG = "weather:MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button location = findViewById(R.id.location);
        //JSONObject weather = getWeatherJSON("London");
        //getWeatherInfo(weather);
        //if(sky == "Clear") {
        //    final ImageView sunny = findViewById(R.id.sunny);
        //    sunny.setVisibility(View.VISIBLE);
        //}

        getAPI.placeIdTask asyncTask =new getAPI.placeIdTask(new getAPI.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure) {
                cityField.setText(weather_city);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: "+weather_humidity);
                pressure_field.setText("Pressure: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "get the current location");
                JSONObject weather = getWeatherJSON("London");
                getWeatherInfo(weather);
                if(sky == "Clear") {
                    final ImageView sunny = findViewById(R.id.sunny);
                    sunny.setVisibility(View.VISIBLE);
                }
                if(sky == "Clouds") {
                        final ImageView sunny = findViewById(R.id.sunny);
                        sunny.setVisibility(View.VISIBLE);
                }
                if(sky == "Rain") {
                    final ImageView sunny = findViewById(R.id.sunny);
                    sunny.setVisibility(View.VISIBLE);
                }
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
