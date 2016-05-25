package com.example.jdbc;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;



public class CodecTest {
	 public static void main(String[] args) throws UnsupportedEncodingException{
		/// String str = "RuS$AdMiN2013";
		/// String str = "97a66a59faecdee64bbd2569a4a06ec2661b55d2";
		// method2();
		// method1();
		// method5();
		 
		method8();
		
	 }
	 
	 //加密
	 public static void method1(){
		// String str = "Abc123";
		 String str="zD4acX8HCO$7+kz5";
		// String str="Abc123";
         String md5Str = DigestUtils.md5Hex(str);
         
         String sh1Str = DigestUtils.shaHex(str);
         
         System.out.println("md5Str: " + md5Str + ", length: " +
        		 md5Str.length() + "; sh1Str: " + sh1Str
        		 + ", length: " + sh1Str.length());
	 }
	 
	 //编码
	 public static void method2() throws UnsupportedEncodingException{
		 String filename = "高德员工_2015080701.jpg";
		 String str = new String(filename.getBytes("utf-8"),"iso-8859-1");
		 String str2 = new String(filename.getBytes("GB2312"),"iso-8859-1");
		 String str3 = new String(str2.getBytes("utf-8"),"iso-8859-1");
		 String[] arr = new String("40,高,数据部,王伟,修改,更新,FC,14Q2,上海,C:\fakepath\test1.jpg,").split(",",-1);
		 System.out.println("str: " + str + "; str2: " + str2 + "; str3: " + str3 + "; arr.length: " + arr.length 
				 + "; arr[end]: " + arr[arr.length-2]);
	 }
	 
	 //20150813 坐标字符串拆分以及合并
	 public static void method3(){
		    String tempPointsStr = "112,32|||||100,12";	
		    System.out.println("before dealing tempPointsStr: " + tempPointsStr);
		    
		    if(tempPointsStr != null && !tempPointsStr.equals("")){
		    	String[] tempPointsArr = tempPointsStr.split("\\|");
		    	System.out.println("tempPointsArr.length: " + tempPointsArr.length);
		    	for(int i=0; i<tempPointsArr.length; i++){
		    		System.out.println("i - " + i+ ": " + tempPointsArr[i]);
		    		String tempStr = tempPointsArr[i];//单个坐标字符串
		    		if(tempStr != null && !tempStr.equals("")){
		    			String[] tempArr = tempStr.split(",");
		    			if(tempArr.length == 2){
		    				Double tempX = Double.parseDouble(tempArr[0]);
		    				Double tempY = Double.parseDouble(tempArr[1]);
		    				if(tempX > 10000){
		    					tempX = tempX/3600;
		    				}
		    				if(tempY > 10000){
		    					tempY = tempY/3600;
		    				}
		    				tempStr = tempX + "," + tempY;
		    			}
		    		}//tempStr != null
		    		
		    		tempPointsArr[i] = tempStr;
		    	}//for
		    	
		    	System.out.println("tempPointsArr.length: " + tempPointsArr.length);
		    	//将处理后的子坐标字符串合成一个字符串
		    	String tempNewStr = "";
		    	for(int i=0; i<tempPointsArr.length; i++){
		    		tempNewStr += tempPointsArr[i] + "|";
		    	}
		    	if(tempPointsArr.length == 4 || tempPointsArr.length > 4){
		    		tempNewStr = tempNewStr.substring(0, tempNewStr.length()-1);
		    	}else if(tempPointsArr.length == 2){
		    		tempNewStr += "|";
		    	}else if(tempPointsArr.length == 1){
		    		tempNewStr += "||";
		    	}
		    	
		    	
		    	tempPointsStr = tempNewStr; //将处理后的新坐标字符串赋给tempPointsStr
		    }
		    
		    System.out.println("after dealing tempPointsStr: " + tempPointsStr);
	 }
	 
	 //urlencode urldecode 20150909
	 public static void method4() throws UnsupportedEncodingException{
		 String str1="导航报错->车厂问题处理->报错详情信息";
		 String str2 = java.net.URLEncoder.encode("导航报错->车厂问题处理->报错详情信息","UTF-8");
		 String str3 = java.net.URLDecoder.decode(str2,"UTF-8");
		 
		 System.out.println("Str1: " + str1 + "; str2: " + str2
				 + "; str3: " + str3);
	 }
	 
	 //20150916 字符串分割问题
	 public static void method5(){
		 String str = "http://param_ip:param_port/pubData/attach/20150915182224/test.jpg";
		 String[] strArr = str.split("/");
		 
		 System.out.println("strArr length: " + strArr.length);
		 
		 for(Integer i=0; i<strArr.length; i++){
			 System.out.println("i - " + i + " : " + strArr[i]);
		 }
		 
		 String[] strArr2 = new String("aaaa").split("/");
		 System.out.println("strArr2 length: " + strArr2.length);
	 }
	 
	 //20151110 测试字符串分割
	 public static void method6(){
		  String str = "aaaa;ddd;ggg;ggg;hhh";
		  String[] arr = str.split(";");
		  System.out.println("arr length: " + arr.length + "; arr[0]: " + arr[0]);
		  String str1 = "aa";
		  String[] arr1 = str1.split(";");
		  System.out.println("arr1 length: " + arr1.length + "; arr1[0]: " + arr1[0]);
	 } 
	 
	 //20151111
	 public static void method7() throws UnsupportedEncodingException{
		  String str=" 1次修改方案:更新结果在下一版本体现&lt;br&gt;2次修改方案:测试2次结果反馈<br>3次修改方案:测试多次下发，反馈结果。＜br＞";
		  System.out.println("str: " + str);
		  String str2=new String(str.getBytes(),"UTF-8");
		  System.out.println("str2: " + str2);
		  String str3=java.net.URLDecoder.decode(str,"UTF-8");
		  System.out.println("str3: " + str3);
		  String str4=str.replace("＜","<");
		  System.out.println("str4: " + str4);
	 }
	 
	 //20160215 
	 public static void method8(){
		 String testStr = "http://www.baidu.com";
		 System.out.println("test result : " + testStr.contains("http:"));
		 String str = "ddff" + null;
		 System.out.println("str: " + str);
		 String strFile = "http://domainame/attachment/201603/24/20160324111314/.xlsx";
		 System.out.println("strFile length: " + strFile.length());
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     System.out.println("current date: " + format.format(new Date()));
	 }
}
