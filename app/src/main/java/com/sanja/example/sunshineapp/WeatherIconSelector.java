package com.sanja.example.sunshineapp;

public class WeatherIconSelector {

    public static int getIcon(String weatherIcon){
        switch (weatherIcon){
            case("01d"):
                return R.drawable.ic_01d;
            case("01n"):
                return R.drawable.ic_01n;
            case("02d"):
                return R.drawable.ic_02d;
            case("02n"):
                return R.drawable.ic_02n;
            case("03d"):
                return R.drawable.ic_03d;
            case("03n"):
                return R.drawable.ic_03n;
            case("04d"):
                return R.drawable.ic_04d;
            case("04n"):
                return R.drawable.ic_04n;
            case("09d"):
                return R.drawable.ic_09d;
            case("09n"):
                return R.drawable.ic_09n;
            case("10d"):
                return R.drawable.ic_10d;
            case("10n"):
                return R.drawable.ic_10n;
            case("11d"):
                return R.drawable.ic_11d;
            case("11n"):
                return R.drawable.ic_11d;
            case("13d"):
                return R.drawable.ic_13d;
            case("13n"):
                return R.drawable.ic_13n;
            default:
                return R.drawable.ic_sun_placeholder;
        }
    }
}