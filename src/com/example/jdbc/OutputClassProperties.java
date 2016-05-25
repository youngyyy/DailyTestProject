package com.example.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class OutputClassProperties {
	public static String toString(Object obj){ 
		String s = ""; 
		try { 
		s = getPropertyString(obj); 
		} catch (Exception e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
		} 
		return s; 
	} 

	public static String getPropertyString(Object entityName) throws Exception { 
		Class c = entityName.getClass(); 
		Field field [] = c.getDeclaredFields(); 
		StringBuffer sb = new StringBuffer(); 

		sb.append("------ " + " begin ------\n"); 
		for(Field f : field){ 
		sb.append(f.getName()); 
		sb.append(" : "); 
		sb.append(invokeMethod(entityName,f.getName(),null)); 
		sb.append("\n"); 
		} 
		sb.append("------ " + " end ------\n"); 
		return sb.toString(); 
	} 

	public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception{
		Class ownerClass = owner.getClass();

		methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
		Method method = null;
		try {
		method = ownerClass.getMethod("get" + methodName);
		} catch (SecurityException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		return " can't find 'get" + methodName + "' method";
		}

		return method.invoke(owner);
	}
	
	//测试输出类的所有属性
	public static void main(String[] args){
		Student stu = new Student();
		stu.setAge(11);
		stu.setName("test");
		String str = toString(stu);
		
		System.out.println("str: " + str);
	}
}

//Student 类
class Student{
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
