package com.order.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.order.dao.BaseDAO;
import com.order.dao.DAOObjectNotFoundException;

/**
 * the Base DAO which is extend by all mybatis DAO
 * 
 * @author Tito Chiang
 * @param <T>
 *            the Result object
 * @param <K>
 *            the primary key
 */
public abstract class BaseDAOImpl<T, K> extends SqlSessionDaoSupport implements
		BaseDAO<T, K> {
	protected String getNameSpace() {
		return this.getClass().getInterfaces()[0].getName();
	}

	@Resource
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

	@SuppressWarnings("unchecked")
	@Override
	public T findByKey(K k) throws DAOObjectNotFoundException {
		Object o = this.getSqlSession().selectOne(
				getNameSpace() + ".findByKey", k);
		if (o == null)
			throw new DAOObjectNotFoundException(
					"There is no data corresponding to PK=" + k.toString());
		return (T) o;
	}

	@Override
	public List<T> findAll() {
		return this.getSqlSession().selectList(getNameSpace() + ".findAll");
	}

	protected boolean isExisted(K k) {
		boolean b = true;
		try {
			findByKey(k);
		} catch (DAOObjectNotFoundException e) {
			b = false;
		}
		return b;
	}
}