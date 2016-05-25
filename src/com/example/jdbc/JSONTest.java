package com.example.jdbc;

import com.alibaba.fastjson.JSON;
import com.example.domain.Member;

public class JSONTest {
     public static void main(String[] args){
    	 method1();
     }
     
     public static void method1(){
    	 String str ="{\"memb_no\":\"\",\"name\":\"atest\","
             + "\"department\":\"\",\"email\":\"232@qq.com\"}";
         //,\"telephone\":null
    	 Member newEmployee = JSON.parseObject(str.trim(),Member.class);
    	 System.out.println("newEmployee telephone: " + newEmployee.getTelephone());
    	 
     }
}
