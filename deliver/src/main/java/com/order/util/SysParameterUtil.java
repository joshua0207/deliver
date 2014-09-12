package com.order.util;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.dao.SysParameterQueryDAO;
import com.order.mode.vo.SysParameterVO;


@Component
public class SysParameterUtil {

	private static final Log log = LogFactory.getLog(SysParameterUtil.class);
	
	@Autowired
	private SysParameterQueryDAO sysParameterQueryDAO;

	/*
	 * cpStr 要等於 path.properties 內的Connection Pool 設定值, 如:connectionPoolTr ,
	 * connectionPoolQr return String
	 */
	public String getCPValue(String cpStr) throws Exception {
		ResourceBundle cp = ResourceBundle.getBundle("conf.path");
		return cp.getString(cpStr).trim();
	}
	
	public String getSysPrmValue(String strSysId,String strSysPrmId)//ex. shopadmin,picture
	{
		String strTemp = "";
		try{
		SysParameterVO sysPrmVO = null;		
		List<SysParameterVO> arySysPrmList = null;	
				
		arySysPrmList = sysParameterQueryDAO.findSysParameterTwo(strSysId, strSysPrmId);  
		
		if( arySysPrmList != null && arySysPrmList.size() > 0){
			sysPrmVO = (SysParameterVO) arySysPrmList.get(0);//只取最先得到的那一個
			strTemp = sysPrmVO.getSYSTEM_VALUE();
			sysPrmVO = null;		
		}
		
		
		}catch(Exception ex){
			log.info("getSysPrmValue() error :"+ex);
		}
		
		return strTemp;
	}

	
	 
}
