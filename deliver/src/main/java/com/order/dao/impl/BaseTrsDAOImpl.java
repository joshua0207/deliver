package com.order.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.order.dao.BaseTrsDAO;


/**
 * the Base DAO which is extend by all mybatis DAO
 * 
 * @author Tito Chiang
 * @param <T>
 *            the Result object
 * @param <K>
 *            the primary key
 */
public abstract class BaseTrsDAOImpl<T, K> extends SqlSessionDaoSupport implements
		BaseTrsDAO<T, K> {
	protected String getNameSpace() {
		return this.getClass().getInterfaces()[0].getName();
	}

	
//	@Autowired
//	@Qualifier(value="sqlSessionFactoryTrs")
	@Resource(name="sqlSessionFactoryTrs")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void insert(T t) {
		this.getSqlSession().insert(getNameSpace() + ".insert", t);
	}

	@Override
	public void update(T t) {
		this.getSqlSession().update(getNameSpace() + ".update", t);
	}

	@Override
	public void deleteByKey(K k) {
		this.getSqlSession().delete(getNameSpace() + ".deleteByKey", k);
	}

}