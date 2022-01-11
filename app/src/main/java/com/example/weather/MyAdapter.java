package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
@SuppressWarnings("unused")

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    //ArrayList<WeatherResponse> weather;
    Context context;
    char ch='C';
    View view;
    ArrayList<WeatherResponse>weatherInfo;
    String imageBaseUrl= "https://openweathermap.org/img/wn/";
    String imageUrl ;
    String imageUrlEndPoint = "@2x.png";
    String iconId;

    public MyAdapter (Context context,ArrayList<WeatherResponse> weatherInfo){
       this.weatherInfo=weatherInfo;

        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.recycle,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        WeatherResponse weatherResponse=weatherInfo.get(i);
        holder.temperature.setText( weatherResponse.getMain().getTemp());
        holder.city.setText(weatherResponse.getName());
        holder.country.setText(weatherResponse.getSys().getCountry());
        iconId = weatherResponse.getWeather().get(0).getIcon();

       // setImageIcon(holder,iconId);

    }

    @Override
    public int getItemCount() {
        return weatherInfo.size() ;
    }
}
class ViewHolder extends RecyclerView.ViewHolder {

    TextView temperature;
    TextView city;
    TextView country;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.temperature = itemView.findViewById(R.id.temperature_textView);
        this.city = itemView.findViewById(R.id.cityName_textView);
        this.country=itemView.findViewById(R.id.countryName_textView);
    }
   /* public void setImageIcon(@NonNull ViewHolder holder,  String iconId){
        String imageUrl = imageBaseUrl + iconId + imageUrlEndPoint;

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.mipmap.ic_launcher_round)
                .circleCrop();

        Glide.with(context)
                .load(imageUrl)
                .apply(options)
                .into(holder.imageIcon);

    }*/
}