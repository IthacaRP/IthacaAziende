package me.gabryosas.utils.internal;

import java.text.SimpleDateFormat;

public class Date {
    /**
     Può tornare utile per prendere la data.
     */
    public static String getDate(){
        java.util.Date data = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(data);
        return date;
    }
}
