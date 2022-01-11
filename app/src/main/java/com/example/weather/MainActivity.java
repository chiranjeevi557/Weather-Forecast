package com.example.weather;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity {
    final String api = "e5c45b78e47aa5a3a7631253456cadb0";
    final String baseUrl = "https://api.openweathermap.org/data/2.5/";
    private ApiInterface apiService;
    private RecyclerView recyclerView;
    ArrayList<WeatherResponse> weatherInfo;
    //ArrayList<WeatherResponse>

    MyAdapter adapter;
    EditText city;
    Button search;
    //double temperature;
    //String country;
    //String city1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.editText);
        search = findViewById(R.id.button);
        weatherInfo = new ArrayList<>();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 getWeather();
            }

        });

    }

    public void getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface myapi = retrofit.create(ApiInterface.class);

            Call<WeatherResponse> example = myapi.getWeather(city.getText().toString(), api);


            example.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    WeatherResponse mydata = response.body();

                    weatherInfo.add(mydata);

              /* Main main = mydata.getMain();
               Sys sys = mydata.getSys();
               double temp = main.getTemp();
               temperature = (int) (temp - 273.15);
               city1 = mydata.getName();
               country = sys.getCountry();*/


                    createRecyclerView();
                }


                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "failed to retrieve data\ncheck your internet connection", Toast.LENGTH_LONG).show();

                }
            });
        }



        public void createRecyclerView(){
            recyclerView = findViewById(R.id.recycleview);
            adapter = new MyAdapter(this, weatherInfo);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }





}



