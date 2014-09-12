package com.order.dao;


public interface BaseTrsDAO<T ,K> {
	
	void insert(T t);
	void update(T t);
	void deleteByKey(K k);
	
	
	
}
