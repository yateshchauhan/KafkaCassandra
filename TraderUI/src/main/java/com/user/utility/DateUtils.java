package com.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static Date converLocalDateToDate(LocalDate date){
        return convertDate(convertDate(date));
    }

    public static String convertDate(Date date){

        if(date == null)
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateTxt = formatter.format(date);

        return dateTxt;
    }

    public static String convertDate(LocalDate localDate){

        if(localDate == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateTxt = formatter.format(localDate);

        return dateTxt;
    }

    public static Date convertDate(String yyyyMMdd){

        Date date = null;
        try {
            if (yyyyMMdd == null)
                return null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            date = formatter.parse(yyyyMMdd);
        }catch(Exception e){
            e.printStackTrace();
        }
        return date;
    }
}
