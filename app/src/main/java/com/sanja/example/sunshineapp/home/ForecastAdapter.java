package com.sanja.example.sunshineapp.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanja.example.sunshineapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>{
    private final List<Forecast> forecast;

    public ForecastAdapter() {
        this.forecast = new ArrayList<>();
    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_item, parent, false);
        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        holder.tvForecastDay.setText("SUN");
        holder.tvForecastTemperature.setText("30 °C");
    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }

    public void refreshForecast(List<Forecast> forecast){
        this.forecast.clear();
        this.forecast.addAll(forecast);
        notifyDataSetChanged();
    }

    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_forecast_state) ImageView ivForecastState;
        @BindView(R.id.tv_forecast_day) TextView tvForecastDay;
        @BindView(R.id.tv_forecast_temperature) TextView tvForecastTemperature;

        public ForecastAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}