package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    ImageView weather;
    TextView temperature;
    TextView city;
    TextView country;
    String temperatureData,cityData,countryData;
    int image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        weather=findViewById(R.id.weather);
        temperature=findViewById(R.id.temperature);
        city=findViewById(R.id.city);
        country=findViewById(R.id.country);
        getData();
        setData();
    }
    public void getData(){
        if(getIntent().hasExtra("temperature")&&getIntent().hasExtra("city")&&getIntent().hasExtra("country")&&getIntent().hasExtra("weather"))
        {
            temperatureData= getIntent().getStringExtra("temperature");
            cityData=getIntent().getStringExtra("city");
            countryData=getIntent().getStringExtra("country");
            image=getIntent().getIntExtra("weather",1);
        }
        else {
            Toast.makeText(this,"No data", Toast.LENGTH_LONG).show();
        }
    }

    public void setData()
    {
        temperature.setText(temperatureData);
        city.setText(cityData);
        country.setText(countryData);
        weather.setImageResource(image);
    }

}