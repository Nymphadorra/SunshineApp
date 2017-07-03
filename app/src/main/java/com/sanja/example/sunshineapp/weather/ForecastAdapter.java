package com.sanja.example.sunshineapp.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanja.example.sunshineapp.R;
import com.sanja.example.sunshineapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {
    private final List<Forecast> forecasts;

    public ForecastAdapter() {
        this.forecasts = new ArrayList<>();
    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_item, parent, false);
        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        Forecast forecast = forecasts.get(position);
        holder.ivForecastState.setImageResource(WeatherIconSelector.getIcon(forecast.getWeatherDescription().get(0).getIcon()));
        holder.tvForecastDay.setText(Utils.formatDate(forecast.getDate()));
        holder.tvForecastTemperature.setText(String.valueOf(forecast.getForecastTemperature().getTemperature()));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public void refreshForecast(List<Forecast> forecasts) {
        this.forecasts.clear();
        this.forecasts.addAll(forecasts);
        // First item is removed because it represents weather for current day and we want for forecast
        // to show only upcoming weather.
        this.forecasts.remove(0);
        notifyDataSetChanged();
    }

    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_forecast_state) ImageView ivForecastState;
        @BindView(R.id.tv_forecast_day) TextView tvForecastDay;
        @BindView(R.id.tv_forecast_temperature) TextView tvForecastTemperature;

        public ForecastAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}