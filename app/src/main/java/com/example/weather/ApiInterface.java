package com.example.weather;

import android.graphics.ColorSpace;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("q") String location, @Query("appid") String apiKey);

}
