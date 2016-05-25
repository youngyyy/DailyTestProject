package com.example.jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTest {
	
	 public static void main(String[] args) throws ParseException{
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
		 Date dd = new Date();
		 String str = format1.format(dd);
		 System.out.println(str);
		 
		 SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
		 Date dd2 = new Date();
		 String str2 = format2.format(dd2);
		 System.out.println(str2);
		 
		 int index = str.lastIndexOf("/");
 		 String timeStr = str.substring(index+1, str.length()); 
 		 System.out.println(timeStr);
 		 
 		String yearMonthStr = "2015-01"; 
 		String[] yearMonthArr = yearMonthStr.split("-");
		Integer year = Integer.parseInt(yearMonthArr[0]);
		Integer month = Integer.parseInt(yearMonthArr[1]);
		Integer date=15;
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date);
		String endDate = format1.format(cal.getTime());
		
		cal.add(Calendar.MONTH, -1);
		cal.add(Calendar.DATE, 1);
		String startDate = format1.format(cal.getTime());
		System.out.println("startDate: " + startDate +"; endDate: " + endDate); 
 		 
		// List<String> strList = getColumn("2014/12/16","2015/01/15");
		 List<String> strList = getColumn(startDate,endDate);
		 for(int i=0; i<strList.size(); i++){
		 			// System.out.println(i + ":" + strList.get(i));\
		 			String str11 = strList.get(i);
		 	 		System.out.println(str11);
		 }
	 }
	 public static List<String> getColumn(String fstDate, String lstDate) throws ParseException{
			
		    Calendar calendar = Calendar.getInstance();
		    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		 
		    List<String> dateList = new ArrayList<String>(); 
			Date currentDate = new Date();
			String currentDateStr = format.format(currentDate);
			
			lstDate = (lstDate.compareTo(currentDateStr) <= 0)?lstDate:currentDateStr;
			System.out.println("lstDate: " + lstDate);
			
			Date d = format.parse(fstDate);
			calendar.setTime(d);
			String temp = format.format(calendar.getTime());
			
			while(temp.compareTo(lstDate) <= 0){
				dateList.add(temp);
				calendar.add(Calendar.DATE, 1);
				temp = format.format(calendar.getTime());
			}
			//取dd
		   List<String> ddList = new ArrayList<String>();
		   for(int i=0; i<dateList.size(); i++){
		 		String str = dateList.get(i);
		 		int index = str.lastIndexOf("/");
		 	 	String ddStr = str.substring(index + 1, str.length()); 
		 	 	ddList.add(ddStr);
		    }
			return ddList;
		}
}
