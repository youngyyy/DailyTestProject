package com.example.jdbc;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class HashmapTest {
     public static void main(String[] args){
    	/* HashMap<String, Object> map = new HashMap<String, Object>();
    	 map.put("aaa", 1);
    	 Integer count = (Integer)map.get("bbb");
    	 System.out.println("count: " + count);
    	 if(!map.containsKey("bbbb")){
    		 map.put("bbb", 2);
    	 }
    	 map.put("bbb",4);
    	 map.put("bbb",6);
    	 System.out.println(map.keySet());
    	 System.out.println(map.size());
    	 System.out.println("map.get('bbb'): " + map.get("bbb"));*/
    	/* String oldLeaderIdStr = null;
    	 Integer oldLeaderId = Integer.parseInt(oldLeaderIdStr); //null 出错
    	 System.out.println("oldLeaderIdStr: " + oldLeaderIdStr);
    	 System.out.println("oldLeaderId: " + oldLeaderId);*/
    	 
    	 /*2015.4.14
    	  * Date date = new Date(); 
    	 Timestamp nowdate2 = new Timestamp(date.getTime());
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	 String datestr = sdf.format(nowdate2);
    	 System.out.println(datestr);
		int year = nowdate2.getYear()+1900;
    	 int month = nowdate2.getMonth()+1;
    	 int day = nowdate2.getDate();
    	 System.out.println(year + "-"+ month + "-" +day);*/
    	/* 
    	 * 2015.4.15
    	 * HashMap<String,Object> temp = new HashMap<String, Object>();
 		temp.put("7_1", 1);  //超速_绿色
 		temp.put("7_2", 2);  //超速_中级
 		temp.put("7_3", 3);  //超速_高级
 		temp.put("3_1", 4);  //非工用车_绿色
 		temp.put("3_2", 5);  //非工用车_中级
 		temp.put("3_3", 6);  //非工用车_高级
 		temp.put("1_1", 7);  //保养_绿色
 		temp.put("1_2", 8);  //保养_中级
 		temp.put("1_3", 9);  //保养_高级
 		String tempStr = "1_10";
 		System.out.println(temp.get(tempStr)  == null);
 		System.out.println((Integer)temp.get(tempStr));
 		
 		Integer index = -1;
 		if(temp.get(tempStr) != null){
 		switch((Integer)temp.get(tempStr)){
          case 1:
              index = 1;break;
          case 2:
              index = 2;break;
          case 3: 
	           index = 3;break;
          case 4: 
              index = 4;break;
          case 5:
              index = 5;break;
          case 6:
              index = 6;break;
          case 7: 
              index = 7;break;
          case 8: 
              index = 8;break;
          default:
               index = -1;break;  //没有预警
        }//switch
 		}//if
 		System.out.println(index);*/
    	 
    	 //2015/4/15 Integer 转化为BigInteger
    	/* Integer aaa = 23354;
    	// BigInteger bbb = new BigInteger("\""+aaa+"\"");
    	 BigInteger ccc = new BigInteger(""+aaa);
    	 
    	 System.out.println("aaa: " + aaa + "; bbb: "  + "; ccc: " + ccc);*/
    	 
    	 //2015.4.23 截取字符串
    	 /*String featSizeIdStr="abcdefg";
    	 featSizeIdStr = featSizeIdStr.substring(0,featSizeIdStr.length()-1);
    	 System.out.println("featSizeIdStr: " + featSizeIdStr);*/
    	 
    	 //2015.5.12 字符串比较
    	/* String s1="20150512";
    	 String s2="20140924";
    	 String s3="20160203";
    	 String s4="20150512";
    	 
    	 int result1 = s1.compareTo(s2);
    	 int result2 = s1.compareTo(s3);
    	 int result3 = s2.compareTo(s3);
    	 
    	 System.out.println("result1: " + result1
    			 + "; result2: " + result2 
    			 + "; result3: " + result3 + "; result4: " + s1.compareTo(s4));*/
    	 
    	 //2015.5.29  判断字符串是否为非负整数和小数
    	/* String reg = "\\d+(\\.\\d+)?";
 		String str = "12";
 		
 		System.out.println(str.matches(reg));*/
    	 /*String temp = "56/123";
    	 String[] arr = temp.split("/");
    	 Double value = Double.parseDouble(arr[0]);
    	 System.out.println("value: " + value + "; arr length: " + arr.length);*/
    	 
    	// String authorityTextStr="ssss,";
    	// authorityTextStr = authorityTextStr.substring(0,authorityTextStr.length()-1);
    	// System.out.println("authorityTextStr: " + authorityTextStr);
    	 
    	// Integer s = 0;
    	// System.out.println("s: " + s);
    	 method1();
     }
    
     //20150729  测试substring
     public static void method1(){
    	  String temp_county = "徐汇区";
    	  String temp_county_end = temp_county.substring(temp_county.length() - 1);
		  System.out.println("temp_county_end: " + temp_county_end);
		  //注意判断字符串不等   不可用 !=
		  if(!(temp_county_end.equals("区")) && !(temp_county_end.equals("县"))){
				temp_county = temp_county + "区";
		  }
		  
		  System.out.println("temp_county: " + temp_county);
     }
}
