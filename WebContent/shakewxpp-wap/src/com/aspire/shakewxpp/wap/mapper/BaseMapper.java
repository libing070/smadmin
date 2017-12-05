package com.aspire.shakewxpp.wap.mapper;

import java.util.List;

/***
 * 
 * @author bowen_a
 * 
 * @param <T>
 */
public interface BaseMapper<T> {
	public List<T> queryAll();

	public T queryOne();
}
