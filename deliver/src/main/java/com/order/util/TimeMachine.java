package com.order.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class TimeMachine
{
	private GregorianCalendar time = null;
	private static final Log log = LogFactory.getLog(TimeMachine.class);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
	public TimeMachine()
	{
		time = new GregorianCalendar();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		   TimeMachine time = new TimeMachine();   
		   String nowTime = time.getNowTime(); //現在時間
		   System.out.print(nowTime);
	}

	public String getNowTime()
	{
		String year;
		String month;
		String date;
		String hour;
		String minute;
		String second;
		String NowDatetime = "";
		String minisecond;
		year = String.valueOf(time.get(time.YEAR)).substring(2, 4);
		month = String.valueOf(time.get(time.MONTH) + 1);
		date = String.valueOf(time.get(time.DATE));
		hour = String.valueOf(time.get(time.HOUR_OF_DAY));
		minute = String.valueOf(time.get(time.MINUTE));
		second = String.valueOf(time.get(time.SECOND));

        minisecond =  String.valueOf(time.get(time.MILLISECOND));
        
//        System.currentTimeMillis();
		if (month.length() <= 1)
		{
			month = "0" + month;
		}
		if (date.length() <= 1)
		{
			date = "0" + date;
		}
		if (hour.length() <= 1)
		{
			hour = "0" + hour;
		}
		if (minute.length() <= 1)
		{
			minute = "0" + minute;
		}
		if (second.length() <= 1)
		{
			second = "0" + second;
		}
		NowDatetime = year + month + date + hour + minute + second + minisecond;
		
		return NowDatetime;
	}
	public String getNowTimewithSymbol()
	{
		String year;
		String month;
		String date;
		String hour;
		String minute;
		String second;
		String NowDatetime = "";
		
		year = String.valueOf(time.get(time.YEAR));
		month = String.valueOf(time.get(time.MONTH) + 1);
		date = String.valueOf(time.get(time.DATE));
		hour = String.valueOf(time.get(time.HOUR_OF_DAY));
		minute = String.valueOf(time.get(time.MINUTE));
		second = String.valueOf(time.get(time.SECOND));

		if (month.length() <= 1)
		{
			month = "0" + month;
		}
		if (date.length() <= 1)
		{
			date = "0" + date;
		}
		if (hour.length() <= 1)
		{
			hour = "0" + hour;
		}
		if (minute.length() <= 1)
		{
			minute = "0" + minute;
		}
		if (second.length() <= 1)
		{
			second = "0" + second;
		}
		NowDatetime = year +"-"+ month +"-"+ date +" "+ hour +":"+ minute +":"+ second;
		return NowDatetime;
	}
	public String getNowTimewithSymbol1()
	{
		String year;
		String month;
		String date;
		String hour;
		String minute;
		String second;
		String NowDatetime = "";
		
		year = String.valueOf(time.get(time.YEAR));
		month = String.valueOf(time.get(time.MONTH) + 1);
		date = String.valueOf(time.get(time.DATE));
		hour = String.valueOf(time.get(time.HOUR_OF_DAY));
		minute = String.valueOf(time.get(time.MINUTE));
		second = String.valueOf(time.get(time.SECOND));

		if (month.length() <= 1)
		{
			month = "0" + month;
		}
		if (date.length() <= 1)
		{
			date = "0" + date;
		}
		if (hour.length() <= 1)
		{
			hour = "0" + hour;
		}
		if (minute.length() <= 1)
		{
			minute = "0" + minute;
		}
		if (second.length() <= 1)
		{
			second = "0" + second;
		}
		NowDatetime = year +"/"+ month +"/"+ date +" "+ hour +":"+ minute ;
		return NowDatetime;
	}
	public String getTodayDate()
	{
		String year;
		String month;
		String date;
		//String hour;
		//String minute;
		//String second;
		String NowDatetime = "";

		year = String.valueOf(time.get(time.YEAR));
		month = String.valueOf(time.get(time.MONTH) + 1);
		date = String.valueOf(time.get(time.DATE));
		if (month.length() <= 1)
		{
			month = "0" + month;
		}
		if (date.length() <= 1)
		{
			date = "0" + date;
		}
		
		NowDatetime = year + month + date;
		return NowDatetime;
	}
	public String getTodayYear()
	{
		String year;
		year = String.valueOf(time.get(time.YEAR));
		return year;
	}
	public String getTodayMonth()
	{
		String month;
		month = String.valueOf(time.get(time.MONTH) + 1);
		if (month.length() <= 1)
		{
			month = "0" + month;
		}
		return month;
	}
	public String getTodayDay()
	{
		String day;
		day = String.valueOf(time.get(time.DATE));
		if (day.length() <= 1)
		{
			day = "0" + day;
		}
		return day;
	}
	
	//產生序號的function
  public String serial(String name,int num){

	  return name = name +"_"+getNowTime() + String.format("%1$,02d", num);
	  
  }
	//產生random
  public String random(String name){
	  
	  return name +"_"+getNowTime() + String.format("%1$,03d", new Random().nextInt(999));	
  }
	
	//產生week
  public String week(String name){
	  
		String second1 = String.valueOf(time.get(time.WEEK_OF_MONTH)-1);
		System.out.println("second1:"+second1);
	  
	  return name +"_"+getNowTime() + String.format("%1$,03d", new Random().nextInt(999));	
  }
  
	//產生time
  public int time1(){

	  int houra=time.get(time.HOUR_OF_DAY)*60;
	  int minutea=time.get(time.MINUTE);
	  int hourminSum = houra + minutea;
	  
	  return hourminSum;	
  }
  
  /** 
   * 获取日期年份 
   * @param date 
   * @return 
   * @throws ParseException 
   */  
  public int getYear(String date) throws ParseException{  
      Calendar calendar = Calendar.getInstance();  
      calendar.setTime(dateFormat.parse(date));  
      return calendar.get(Calendar.YEAR);  
  }  
    
  /** 
   * 获取日期月份 
   * @param date 
   * @return 
   * @throws ParseException 
   */  
  public int getMonth(String date) throws ParseException{  
      Calendar calendar = Calendar.getInstance();  
      calendar.setTime(dateFormat.parse(date));  
      return (calendar.get(Calendar.MONTH) + 1);  
  }  
    
  /** 
   * 获取日期号 
   * @param date 
   * @return 
   * @throws ParseException 
   */  
  public int getDay(String date) throws ParseException{  
      Calendar calendar = Calendar.getInstance();  
      calendar.setTime(dateFormat.parse(date));  
      return calendar.get(Calendar.DAY_OF_MONTH);  
  }  
  /** 
   * 获取月份起始日期 
   * @param date 
   * @return 
   * @throws ParseException 
   */  
  public String getMinMonthDate(String date) throws ParseException{  
      Calendar calendar = Calendar.getInstance();  
      calendar.setTime(dateFormat.parse(date));  
      calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
      return dateFormat.format(calendar.getTime());  
  }  
  
  /** 
   * 获取月份最后日期 
   * @param date 
   * @return 
   * @throws ParseException 
   */  
  public String getMaxMonthDate(Date date) throws ParseException{  
      Calendar calendar = Calendar.getInstance();  
      calendar.setTime(date); 
      calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
      return dateFormat.format(calendar.getTime());  
  }  
 
}
