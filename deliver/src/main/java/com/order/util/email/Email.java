package com.order.util.email;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Email implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(Email.class);
	
	private String mailServer;//MailServer名稱
	private String mailFrom;//寄件者email
	private String mailName;//寄件者名稱
	private String mailTo;//收件者
	private String subject;//信件主旨
	private String content;//信件內容
	private String fileName;//附檔名稱
	private List<File> files;//附檔檔案多筆夾帶
	
	public String getMailServer() {
		return mailServer;
	}
	
	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}
	
	public String getMailFrom() {
		return mailFrom;
	}
	
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	
	public String getMailName() {
		return mailName;
	}
	
	public void setMailName(String mailName) {
		this.mailName = mailName;
	}
	
	public String getMailTo() {
		return mailTo;
	}
	
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}	
	
	
}
