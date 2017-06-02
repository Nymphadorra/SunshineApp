package com.sanja.example.sunshineapp.home.utils.constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    private static final SimpleDateFormat UI_OUTPUT_DATE_FORMAT = new SimpleDateFormat("EEEE, MMM dd", Locale.ENGLISH);
    private static final SimpleDateFormat FORECAST_OUTPUT_DATE_FORMAT = new SimpleDateFormat("EEE", Locale.ENGLISH);

    public static String getCurrentDate(){
        return UI_OUTPUT_DATE_FORMAT.format(Calendar.getInstance().getTime());
    }

    public static String formatDate(long dateInMillis){
        String formattedDate;
        Date date = new Date(dateInMillis * 1000);
        formattedDate = FORECAST_OUTPUT_DATE_FORMAT.format(date);
        return formattedDate;
    }
}