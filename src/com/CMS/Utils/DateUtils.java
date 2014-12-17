package com.CMS.Utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * 
 * @author joeyy
 *2014/11/25
 */
/*
 * function getcurrentTime and format it
 */
public class DateUtils {
	public static Timestamp getSysNow(){
		return new Timestamp(System.currentTimeMillis());	
	}
	/*
	 * function switch calendar to java.sql.Timestamp
	 * @return Timestamp nowtime
	 * @param Calendar nowtime
	 */
	public static Timestamp SwitchSqlDate(Calendar cal) {  
		Date date = cal.getTime();  
		Timestamp datetime = new Timestamp(date.getTime()); 
		return datetime;  
		}  
	/*
	 * function getnowTime to String
	 * @return String Time
	 * @param Date date
	 */
	 public static String getDate(Date date){
	        return getDate(date, "yyyy-MM-dd HH:mm:ss");
	    }
	 /*
	  * function GetNowDate to String
	  * @return String NowTime
	  */
	 public static String getNowDate(){
	        return getDate(new Date());
	    }
	 /*
	  * function GetDateTo formatStr
	  * @return formatStr
	  * @param Date date
	  */
	  public static String getDate(Date date,String formatStr){
	        if(date!=null){
	            if(null!=formatStr && ""!=formatStr.trim()){
	                SimpleDateFormat simpleDateFormat;
	                String dateStr = null;
	                try {
	                    simpleDateFormat = new SimpleDateFormat(formatStr);
	                    dateStr = simpleDateFormat.format(date);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                return dateStr;
	            }else{
	                return null;
	            }
	        }else{
	            return null;
	        }
	    }


}
