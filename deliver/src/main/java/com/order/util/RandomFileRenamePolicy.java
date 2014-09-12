package com.order.util;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;

//圖片上傳改檔案名稱
public class RandomFileRenamePolicy implements FileRenamePolicy {  

	 String nameIndex = "";
	
	 public File rename(File file) { 
		 
		  TimeMachine time = new TimeMachine();	  
		  String body="";  
	      String ext="";  
	     
	      //Date date = new Date();  
	      int pot=file.getName().lastIndexOf(".");  
	      if(pot!=-1){  
	          //body= date.getTime() +""; 
	    	  body=nameIndex+time.getNowTime();
	          ext=file.getName().substring(pot);  
	      }else{  
	          //body=(new Date()).getTime()+""; 
	    	  body=nameIndex+time.getNowTime();
	          ext="";  
	      }  
	      String newName=body+ext;  
	      file=new File(file.getParent(),newName);  
	      return file;  
	 
	 }  

	 
	} 