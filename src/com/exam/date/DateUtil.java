package com.exam.date;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {  
	  
    public static Date stringToDate(String dateString) {  
        ParsePosition position = new ParsePosition(0);  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date dateValue = simpleDateFormat.parse(dateString, position);  
        return dateValue;  
    }  
  
}  