package com.sanja.example.sunshineapp.home.utils.constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {
    private static final String ERROR_DATE = "Unknown";
    private static final SimpleDateFormat UI_OUTPUT_DATE_FORMAT = new SimpleDateFormat("EEEE, MMM dd", Locale.ENGLISH);

    public static String getCurrentDate(){
        return UI_OUTPUT_DATE_FORMAT.format(Calendar.getInstance().getTime());
    }
}