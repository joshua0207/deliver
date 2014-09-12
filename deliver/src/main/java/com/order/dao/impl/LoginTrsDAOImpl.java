package com.order.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.LoginTrsDAO;
import com.order.mode.Car;
import com.order.mode.vo.LoginAdminVO;


@Repository("loginTrsDAOTarget")
public class LoginTrsDAOImpl extends BaseTrsDAOImpl<LoginAdminVO, Map<String, Object>> implements LoginTrsDAO {

	
	@Override
	public Car findByKey(String id)  {
		
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findCar", id);
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