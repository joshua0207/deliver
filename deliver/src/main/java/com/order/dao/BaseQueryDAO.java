package com.order.dao;

import java.util.List;

public interface BaseQueryDAO<T ,K> {
	
	T findByKey(K k) throws DAOObjectNotFoundException;
	List<T> findAll();
	
	
}
