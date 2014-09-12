package com.order.dao;

import java.util.List;

public interface BaseDAO<T ,K> {
	void insert(T t);
	void update(T t);
	void deleteByKey(K k);
	T findByKey(K k) throws DAOObjectNotFoundException;
	List<T> findAll();
	
	
}
