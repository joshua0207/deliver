package com.order.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.oreilly.servlet.MultipartRequest;


@Component
public class UploadUtil {
	
	/**
	 * 上傳Method
	 * @param request
	 * @return
	 * @throws IOException
	 */
	 public  HashMap<String, Object> upload(HttpServletRequest request,String path, String nameIndex) throws IOException  
	 {  
		HashMap<String, Object> hm = new HashMap<String, Object>();
	  //存绝對路徑  
	  //String filePath = "C://upload";  
	  //存相對路徑  
	  //String filePath = request.getSession().getServletContext().getRealPath("/")+"image"; 
		String filePath = path;  
		File uploadPath = new File(filePath);  
	  //檢查文件夾是否存在 不存在 創建整個路徑資料夾  
	  if(!uploadPath.exists())  
	  {  
	   uploadPath.mkdirs();  
	  }  
	  //文件最大容量 10M  
	  int fileMaxSize = 10*1024*1024;  
	   
	  //文件名  
	  String fileName = ""; 
	  //文件類型
	  String contentType ="";
	  //副檔名
	  String subfileName = "";
	  //上傳文件數  
	  int fileCount = 0;  
	  //重新命名  
	  RandomFileRenamePolicy rfrp=new RandomFileRenamePolicy(); 
	  rfrp.nameIndex = nameIndex;
	  
	  //上傳文件  
	  MultipartRequest mulit = new MultipartRequest(request,filePath,fileMaxSize,"UTF-8",rfrp);  
	 
//	  String pictureDescrp = mulit.getParameter("pictureDescrp");//得到test欄位值
//	  String pictureContent = mulit.getParameter("pictureContent");//得到textarea欄位值
//	  String userName = mulit.getParameter("userName");  
	   
	  Enumeration<?> filesname = mulit.getFileNames();  
	       while(filesname.hasMoreElements()){  
	            String name = (String)filesname.nextElement();  
	            fileName = mulit.getFilesystemName(name);  //文件名
	            contentType = mulit.getContentType(name); //文件類型 
	            
	            if(fileName != null && !fileName.equals("")){
	            	subfileName = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
	            }
	            if(fileName!=null){  
	             fileCount++;  
	            }  
       
	       }  	   
	        
	        hm.put("fileName", fileName);//文件名
	        hm.put("fileCount", fileCount);//上傳文件數 
	        hm.put("mulit", mulit);//回傳mulit，可由此取出各欄位的值
	        hm.put("contentType", contentType);//文件類型
	        hm.put("subfileName", subfileName);//副檔名
	        
	       return hm;  
	 }

}
