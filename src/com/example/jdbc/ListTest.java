package com.example.jdbc;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	 public static void main(String args[])
	    {
	        User user1=new User();
	        List<User> userList=new  ArrayList<User>();
	        user1.setName("呵呵");
	        user1.setSex("男");
	        userList.add(user1);
	        
	        User user2=new User();
	        user2.setName("嘻嘻");
	        user2.setSex("女");
	        userList.add(user2);
	        
	        User obj1 = userList.get(0);
	        obj1.setName("change1");
	        
	        User obj2 = new User();
	        obj2 = userList.get(1);
	        obj2.setName("change2");
	        
	        for(User userInfo:userList)
	        {  
	        	
	            System.out.println(userInfo);
	        }
	    }
}

class User{
	 //姓名
    private String name;
    
    //性别
    private String sex;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }
    
    @Override
    public String toString()
    {
        return "User [name=" + name + ", sex=" + sex + "]";
    }
}