package com.order.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.order.mode.vo.SMSRtnVO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URLEncoder;




public class SmsUtil
{	  
  private static final Log log = LogFactory.getLog(SmsUtil.class);
  private String hostName = "";
  private int port = 0;
  private String path = "";	  
  private static MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
  private static int connectionTimeOut = 20000;
  private static int socketTimeOut = 10000;
  private static int maxConnectionPerHost = 5;
  private static int maxTotalConnections = 40;
  //private static final byte CR = (byte)'\r';
  //private static final byte LF = (byte)'\n';
  //private static final byte[] CRLF = new byte[]{CR,LF};

  
  public SmsUtil() throws Exception {
	   	  
		manager.getParams().setConnectionTimeout(connectionTimeOut);
		manager.getParams().setSoTimeout(socketTimeOut);
		manager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		manager.getParams().setMaxTotalConnections(maxTotalConnections);
				  
 }

	
	
	
    /**
     * 透過 HTTP 方式, 傳送簡訊   
     * @param SMSVO 傳送的 內容
     * @return SMSRtnVO 回傳簡訊業者提供的內容
     */
    @SuppressWarnings("finally")
	public SMSRtnVO sendSMS(SMSVO smsvo) throws Exception {
                                 
        String content = "";
        String smbody = "";
        HttpClient client = new HttpClient(manager);
        GetMethod get = null;
        String responseBody = "";
        SMSRtnVO smsRtnvo = new SMSRtnVO();
        
        smsvo.setEncodingValue("Big5");                  //編碼方式:Big5 , Unicode , UTF8 , 預設為Big5
                
        smbody = URLEncoder.encode(smsvo.getSMBodyValue(),"BIG5"); //將URL EnCode 為Big5 (三竹簡訊要求要轉成Big5)
        smbody = smbody.replaceAll("\\+", "%20");   //EnCode 後, 空白會變成 +  , 將 + 轉成 %20 
                      
        content =  "?" + smsvo.getUserName()+ "&" + smsvo.getUserPasswd()+ 
                   "&" + smsvo.getDestPhoneNum() + "=" + smsvo.getDestPhoneNumValue() +
                   "&" + smsvo.getSMBody() + "=" + smbody;
        
        if(!smsvo.getEncoding().equals("") && !smsvo.getEncodingValue().equals(""))
        	content += "&" + smsvo.getEncoding() + "=" + smsvo.getEncodingValue();
        if(!smsvo.getDestName().equals("") && !smsvo.getDestNameValue().equals(""))
            content += "&" + smsvo.getDestName() + "=" + smsvo.getDestNameValue();
        if(!smsvo.getSmsResponseUrl().equals(""))
            content += "&" + smsvo.getSmsResponseUrl();
        
        log.info("傳送簡訊 URL => " + this.hostName + ":" + this.port + this.path);
        log.info("傳送簡訊 Para => " + content);
       
        get = new GetMethod(this.hostName + ":" + this.port + this.path + content);

      try {        	
        	smsRtnvo.setHttpRtnCode(client.executeMethod(get));       //發送簡訊並抓取Http回傳值
            responseBody = new String(get.getResponseBodyAsString()); //簡訊業者回傳值
            
            log.info("returnCode:" + smsRtnvo.getHttpRtnCode());
            log.info("簡訊業者回傳值:" + responseBody.toString());
 
            String[] rsp = responseBody.split("\r\n");  //CR LF 換行字元當作分界字串, 將回傳資料拆開
            for(int i=0;i<rsp.length;i++){
            	log.info("response["+i+"]:"+rsp[i]);
            	String[] tmp = rsp[i].split("=");
            	
            	if(tmp[0].equalsIgnoreCase("msgid")){ //抓取簡訊回傳序號
            		smsRtnvo.setMsgId(tmp[1]);
            	}
            	
            	if(tmp[0].equalsIgnoreCase("statuscode")){ //抓取簡訊回傳狀態值
            		smsRtnvo.setStatusCode(tmp[1]);
            		smsRtnvo.setStatusCodeDesc(tmp[1]);
            	}
            	 
            	if(tmp[0].equalsIgnoreCase("AccountPoint")){ //抓取簡訊剩餘點數
            		smsRtnvo.setAccountPoint(Long.parseLong(tmp[1]));
            	}
            	
            	if(tmp[0].equalsIgnoreCase("Duplicate")){ //抓取是否為重複發送簡訊
            		smsRtnvo.setDuplicate(tmp[1]);
            	}            	
            }         
        } catch (Exception ex) {
            log.error(ex.toString());
        } finally {
        	get.releaseConnection();        	
        	return smsRtnvo;
        }
    }


   
	
    /**
     * 抓取簡訊傳送時需要的相關參數(Ex: ServerName, Port, username , password ....)
     */
    public SMSVO getUrlPara(SysParameterUtil sysutil) throws Exception {
    	
    	SMSVO smsvo = new SMSVO();
    	    	
    	hostName = sysutil.getSysPrmValue("deliver", "SMSServerName");
    	port = Integer.parseInt(sysutil.getSysPrmValue("deliver", "SMSServerPort"));
    	path = sysutil.getSysPrmValue("deliver", "SMSServerPath");
    	
    	smsvo.setUserName(sysutil.getSysPrmValue("deliver", "SMSUserName"));
    	smsvo.setUserPasswd(sysutil.getSysPrmValue("deliver", "SMSUserPassword"));
    	smsvo.setDestPhoneNum(sysutil.getSysPrmValue("deliver", "SMSPhoneNum"));
    	smsvo.setDestName(sysutil.getSysPrmValue("deliver", "SMSDestName"));
    	smsvo.setEncoding(sysutil.getSysPrmValue("deliver", "SMSEncoding"));
    	smsvo.setSMBody(sysutil.getSysPrmValue("deliver", "SMSBody"));
    	smsvo.setSmsResponseUrl(sysutil.getSysPrmValue("deliver", "SMSResponseUrl"));
    	
    	return smsvo;
    }
	
}
