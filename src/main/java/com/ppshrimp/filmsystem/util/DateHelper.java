package com.ppshrimp.filmsystem.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHelper {
	
    public Date getCurrentDate() {
    	Calendar c=Calendar.getInstance(); 
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");      
		return Date.valueOf(f.format(c.getTime()));
    }
    
    public Date getAfterCurrentDate(int num) {
    	Calendar c=Calendar.getInstance(); 
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    	c.add(Calendar.DAY_OF_MONTH, num);
    	return Date.valueOf(f.format(c.getTime()));
    }
    
    public String getCurrentTime() {
    	Calendar c=java.util.Calendar.getInstance(); 
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
		return f.format(c.getTime());
    }
    
}
