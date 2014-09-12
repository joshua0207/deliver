package com.order.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BaseController {
//	private final Log logger = Logger.getLogger(getClass());
	final Log log = LogFactory.getLog(getClass());
	
	public Log getLogger() {
		return log;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		String format = "yyyyMMdd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, true, format.length());
		binder.registerCustomEditor(Date.class, customDateEditor);
	}
	

	
	@ExceptionHandler(value = HttpSessionRequiredException.class)
	public ModelAndView handleServiceException(HttpSessionRequiredException e) {
		log.info("Error:" + e);
		ModelAndView modelAndView = new ModelAndView("sessionTimeout", "exception", e);
		return modelAndView;
	}
	
	
	@ExceptionHandler(IOException.class)  
    public ModelAndView handleIOException(IOException e) {  
		e.printStackTrace();
		log.error(e);
		ModelAndView modelAndView = new ModelAndView("error", "exception", e);
		return modelAndView;
    }  

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e) {
		e.printStackTrace();
		log.error(e);
		ModelAndView modelAndView = new ModelAndView("error", "exception", e);
		return modelAndView;
	}
}
