package com.order.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.LoginQueryDAO;
import com.order.mode.ShopMemData;
import com.order.mode.vo.LoginAdminVO;


@Repository("loginQueryDAOTarget")
public class LoginQueryDAOImpl extends BaseQueryDAOImpl<LoginAdminVO, Map<String, Object>> implements LoginQueryDAO {

	
	@Override
	public LoginAdminVO findByShopidPassword(String shopId, String password)  {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("password", password);
		return this.getSqlSession().selectOne(getNameSpace() + ".findByShopidPassword", map);
	}

	@Override
	public ShopMemData findForgetPasswdByShopId(String shopId) throws DAOObjectNotFoundException {
		
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findForgetPasswdByShopId", shopId);
	}
	
	

//	@Override
//	public void deleteByKey(String acSegment3, String acSegment4) {
//		super.deleteByKey(getKeys(acSegment3, acSegment4));
//	}
//
//	@Override
//	public Account findByKey(String acSegment3, String acSegment4)
//			throws DAOObjectNotFoundException {
//		return super.findByKey(getKeys(acSegment3, acSegment4));
//	}
//
//	//for findByKey, deleteByKey
//	private Map<String, Object> getKeys(String acSegment3, String acSegment4) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("acSegment3", acSegment3);
//		map.put("acSegment4", acSegment4);
//		return map;
//	}
//	
//	@Override
//	public List<Account> findAllByAcSegment3(String acSegment3)
//			throws DAOObjectNotFoundException {
//		return this.getSqlSession().selectList(getNameSpace() + ".findAllByAcSegment3", acSegment3);
//	}
}