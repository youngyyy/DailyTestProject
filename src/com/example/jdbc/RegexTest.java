package com.example.jdbc;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args){
		method3();
	}
	
	public static void method1(){
		String str="amap123(";
		Pattern pattern = Pattern.compile("^(?![0-9a-z]+$)(?![0-9A-Z]+$)(?![0-9\\W]+$)(?![a-z\\W]+$)(?![a-zA-Z]+$)(?![A-Z\\W]+$)[a-zA-Z0-9\\W_]+$");
		Matcher matcher = pattern.matcher(str);
		boolean b = matcher.matches();
		System.out.println(b);
	}
	public static void method2(){
		 String[] to = new String[1];
	        to[0] = "qqq";
	       System.out.println(to.length); 
	}
	
	@SuppressWarnings("deprecation")
	public static void method3(){
		System.out.println(java.net.URLEncoder.encode("Abc&2013"));
	}
}
