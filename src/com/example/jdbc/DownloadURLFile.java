package com.example.jdbc;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

//20151111 从url上下载文件到本地上
public class DownloadURLFile {
	 /** 
     * @param args 
     */  
    public static void main(String[] args) {  
  
        String res = downloadFromUrl("http://param_ip:param_port/pubData/attach/20151010160222/test.jpg","d:/");  
        System.out.println(res);  
    }  
  
  
    public static String downloadFromUrl(String url,String dir) {  
  
        try {  
            URL httpurl = new URL(url);  
            String fileName = getFileNameFromUrl(url);  
            System.out.println(fileName);  
            File f = new File(dir + fileName);  
            FileUtils.copyURLToFile(httpurl, f);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "Fault!";  
        }   
        return "Successful!";  
    }  
      
    public static String getFileNameFromUrl(String url){  
        String name = new Long(System.currentTimeMillis()).toString() + ".X";  
        int index = url.lastIndexOf("/");  
        if(index > 0){  
            name = url.substring(index + 1);  
            if(name.trim().length()>0){  
                return name;  
            }  
        }  
        return name;  
    }  
}
