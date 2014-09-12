package com.order.util.email;

import java.io.File;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class MailUtil {
	
	private final Logger log = Logger.getLogger(getClass());
	
//	private static final String username = "joshua0207@gmail.com";
//	private static final String password = "joshua777";
	
//	@Resource
//	private JavaMailSender mailSender; 
	
	@Resource
	private JavaMailSenderImpl mailSender;
	
	@Resource
	private SimpleMailMessage templateMessage;
	
	/**
	 * 取得From Email address
	 * @return
	 */
	public String getSystemAdminEMAIL() {
		return templateMessage.getFrom();
	}
	
	
	/*private MimeMessage mimeMessage;
	private MimeMessageHelper helper;*/
	
	
	
	/*public static Properties initMailServerProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}*/
	
	
	/**
	 * @author Joshua
	 * 發送 Mail
	 * @param toList
	 * @param subject
	 * @param body
	 * @throws Exception 
	 */
	public void sendMail(Email email) throws Exception {		
		try {
			SimpleMailMessage message = new SimpleMailMessage(templateMessage); 
			message.setFrom(email.getMailFrom());
			message.setTo(getToArray(email.getMailTo())); 
			message.setSubject(email.getSubject()); 
			message.setText(email.getContent()); 
			if ( mailSender.getSession() == null) {
				throw new Exception("Mail Server Error");
			}
			mailSender.send(message);
		} catch (MessagingException e) {
			log.error(e);
			throw e;
		} 
	}
	
	/**
	 * @author Joshua
	 * 發送 HTML Mail
	 * @param toList
	 * @param subject
	 * @param body
	 * @throws MessagingException 
	 */
	public String sendHTMLMail(Email email) throws MessagingException {
		String rtnFlag = "N";
		MimeMessage mime = this.mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mime, true, "UTF-8");
//			helper.setFrom(templateMessage.getFrom());
			helper.setTo(getToArray(email.getMailTo()));
			helper.setSubject(email.getSubject());
			helper.setText(email.getContent(), true);
//			mime.setContent(email.getHtml(), "text/html; charset=UTF-8");//此打開的話則 MimeMessageHelper就要設為false,則helper.setText無效，改用mime.setContent
			this.mailSender.send(mime);
			rtnFlag = "Y";
		} catch (MessagingException e) {
			rtnFlag = "N";
			log.error(e);
			throw e;
		}
		
		return rtnFlag;
	}
	
	
	/**
	 * @author Joshua
	 * 發送帶有附件 Mail
	 * @param toList
	 * @param subject
	 * @param body
	 */
	public void sendAttachmentMail(Email email) throws Exception{
		MimeMessage mime = this.mailSender.createMimeMessage();
		MimeMessageHelper helper;
		  try {
			mime = this.mailSender.createMimeMessage();
			  helper = new MimeMessageHelper(mime,true, "UTF-8");
			  helper.setFrom(templateMessage.getFrom());
			  helper.setTo(getToArray(email.getMailTo())); 
			  helper.setSubject(email.getSubject());
			  helper.setText(email.getContent(),true);
			  for(File file:email.getFiles()) {
				  helper.addAttachment(file.getName(), file);
			  }
			  this.mailSender.send(mime);
		} catch (MessagingException e) {
			log.error(e);
			throw e;
		}
	}
	
	
	/**
	 * 串多筆收信人
	 * @param toList
	 * @return
	 */
	private String[] getToArray(String toList) {
		StringTokenizer stk = new StringTokenizer(toList, " ;,");
		String[] toArray = new String[stk.countTokens()];
		int index = 0;
		while(stk.hasMoreTokens()) {
			toArray[index] = stk.nextToken();
			index++;
		}
		
		return toArray;
	}
	
	
/** ----------------------------------------以下為其他測試範例----------------------------------------------------*/	
	
	
	
	/**
	 * @author Joshua
	 * @date 2013/09/08
	 * @param user user object
	 * @desc 發送註冊確認信
	 */
	public  void sendNotReceive(Email email) {
		try {
        	StringBuffer html = new StringBuffer();
        	MimeMessage mimeMessage = mailSender.createMimeMessage();
        	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        	html.append("<h2>Welcome to Stream</h2><br>");
            html.append("<h3>Please click the Link to reset Password</h3><br>");
            html.append("<a href='" + "http://localhost/mailVerify/" + email.getSubject() + "'><h1>Click Here<h1></a><br>");
			helper.setTo(email.getMailTo());
			helper.setSubject("註冊確認信");
			helper.setFrom(templateMessage.getFrom());
			mimeMessage.setContent(html.toString(), "text/html; charset=UTF-8");
			mailSender.send(mimeMessage);
			log.info("Done"); 
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	
	/** 
	 * @author Joshua
	 * 發送確認密碼Mail
	 */
	public  void sendForgetPasswordMail(Email email) {
		
		//SimpleMailMessage message = new SimpleMailMessage();
        try {
        	StringBuffer html = new StringBuffer();
        	MimeMessage mimeMessage = mailSender.createMimeMessage();
        	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        	html.append("<h2>Welcome to Stream</h2><br>");
            html.append("<h3>Please click the Link to reset Password</h3><br>");
            html.append("<a href='" + "http://localhost/initResetPassword/" + email.getContent() + "'><h1>Click Here<h1></a><br>");
			helper.setTo(email.getMailTo());
			helper.setSubject("User Forget Password");
			helper.setFrom(templateMessage.getFrom());
			mimeMessage.setContent(html.toString(), "text/html; charset=UTF-8");
			mailSender.send(mimeMessage);
			log.info("Done"); 
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		/*
		Session session = Session.getInstance(initMailServerProperties(),
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try { 
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Stream"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getUserEmail()));
			message.setSubject("User Forget Password");

            MimeBodyPart textPart = new MimeBodyPart();
            StringBuffer html = new StringBuffer();
            html.append("<h2>Welcome to Stream</h2><br>");
            html.append("<h3>Please click the Link to reset Password</h3><br>");
            html.append("<a href='" + "http://localhost/initResetPassword/" + user.getPassword() + "'><h1>Click Here<h1></a><br>");
            textPart.setContent(html.toString(), "text/html; charset=UTF-8");

            Multipart email = new MimeMultipart();
            email.addBodyPart(textPart);
            
            message.setContent(email);
			
			Transport.send(message);
			System.out.println("Done"); 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		*/
	}
	
	
	/*public static void sendSignMail(Email email) { 
		Session session = Session.getInstance(initMailServerProperties(),
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try { 
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Stream"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getUserEmail()));
			message.setSubject("User Verify for Stream");

            MimeBodyPart textPart = new MimeBodyPart();
            StringBuffer html = new StringBuffer();
            html.append("<h2>Welcome to Stream</h2><br>");
            html.append("<h3>Please click the Link to complete Register</h3><br>");
            html.append("<a href='" + "http://localhost/mailVerify/" + user.getToken() + "'><h1>Click Here<h1></a><br>");
            textPart.setContent(html.toString(), "text/html; charset=UTF-8");

            Multipart email = new MimeMultipart();
            email.addBodyPart(textPart);
            
            message.setContent(email);
			
			Transport.send(message);
			System.out.println("Done"); 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}*/
	

	public void main(String[] args) {
		//User user = new User();
		//user.setPassword("0DPiKuNIrrVmD8IUCuw1hQxNqZc=");
		//user.setUserEmail("artlonglong@gmail.com");
		//sendForgetPasswordMail(user);
		
		Email email = new Email();
		email.setMailFrom("joshua0207@gmail.com");
		email.setMailTo("joshua0207@gmail.com");
		email.setSubject("主旨......");
		email.setContent("內容。。。。。。");
		
		//發送多筆人
		StringBuffer toSb = new StringBuffer();
		toSb.append("joshua0207@gmail.com");
		toSb.append(";");
		toSb.append("joshua111@gmail.com");
		toSb.append(";");
		String [] toArray = getToArray(toSb.toString());
		

//		StringBuffer sb = new StringBuffer();
//		sb.append("單位名稱:");
//		sb.append(contactus.getName());
//		sb.append("<br>");
//		sb.append("聯絡人:");
//		sb.append(contactus.getContact());
//		sb.append("<br>");
//		sb.append("聯絡電話-手機號碼:");
//		sb.append(contactus.getMobile());
//		sb.append("<br>");
//		sb.append("聯絡電話-市話(日):");
//		sb.append(contactus.getDayphone());
//		sb.append(HTML_NEW_LINE);
//		sb.append("聯絡電話-市話(夜):");
//		sb.append(contactus.getNightphone());
//		sb.append("<br>");
//		sb.append("電子郵件");
//		sb.append(contactus.getEmail());
//		sb.append("<br>");
//		sb.append("==訊息=============================");
//		sb.append("<br>");
//		sb.append(contactus.getContent().replaceAll("\n", "<br>"));
//		sb.append("<br>");
//		sb.append("==================================");
	}
}
