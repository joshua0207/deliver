package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.vo.SysParameterVO;


public interface SysParameterQueryDAO extends BaseQueryDAO<SysParameterVO, Map<String, Object>> {
	
	
	/** 查詢系統資料default*/
	public List<SysParameterVO> findSysParameterDefault() throws DAOObjectNotFoundException;
	
	/** 查詢系統資料零*/
	public List<SysParameterVO> findSysParameterZero() throws DAOObjectNotFoundException;
	
	/** 查詢系統資料一*/
	public List<SysParameterVO> findSysParameterOne(String systemId) throws DAOObjectNotFoundException;
	
	/** 查詢系統資料二*/
	public List<SysParameterVO> findSysParameterTwo(String systemId, String systemParameterId) throws DAOObjectNotFoundException;
	
	
}