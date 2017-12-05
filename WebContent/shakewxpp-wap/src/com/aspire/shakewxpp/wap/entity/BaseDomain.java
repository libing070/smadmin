package com.aspire.shakewxpp.wap.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * BaseDomain.java         2008-11-21
 * <p/>
 * <p>System: ca 1.0</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: Aspire Technologies</p>
 *
 * @version 1.0
 * @author: 
 */
/**
 * 所有领域DO基类.
 * 
 * @author
 * 
 */
public class BaseDomain extends Object implements Serializable {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6040124023440584351L;

	/** 最大行数. */
	private static final Integer MAX_ROWS = 9999999;

	/**
	 * 动态字段. 在ibatis文件中可用“dynamicFields.xxx”方式读取动态字段值
	 */
	protected Map<String, Object> dynamicFields = new HashMap<String, Object>();

	private int pageNum;
	private int limit;
	private int totalCount;
	private Integer pageSize = MAX_ROWS;
    private int begin;
	private int ends;
   


	
	/**
	 * 起始行数 （oracle物理行号从1开始）. mySql 是从0开始
	 * */
	private Integer start = 1;
	private Integer mysqlStart = 0;
	/**
	 * 结束行数（如果不设置结束行，缺省查所有满足条件记录）.
	 */
	private Integer end = MAX_ROWS;

	/**
	 * 排序字段(例sp.spCode).
	 */
	private String sort;

	/**
	 * 正序|反序(例ASC).
	 */
	private String order;

	public BaseDomain() {
		this.start = 1;
		this.mysqlStart = 0;
		this.end = MAX_ROWS;
		this.limit = 999999;
		this.totalCount = 0;
	}

	public void setPagination(BaseDomain bo) {
		bo.setPageSize(getLimit());
		bo.setStart(getLimit() * (getPageNum() - 1) + 1);
		bo.setMysqlStart(getLimit() * (getPageNum() - 1) + 1);
		bo.setEnd(getStart() + getLimit() - 1);
	}

	/**
	 * 设置动态字段值.
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            字段值
	 */
	public void setField(String fieldName, Object value) {
		if (dynamicFields == null) {
			dynamicFields = new HashMap<String, Object>();
		}
		dynamicFields.put(fieldName, value);
	}

	/**
	 * 返回动态字段值.
	 * 
	 * @param fieldName
	 *            字段名称
	 * @return 对象
	 */
	public Object getField(String fieldName) {
		if (dynamicFields == null) {
			return null;
		}
		return getDynamicFields().get(fieldName);
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		if (start <= 0) {
			this.start = 1;
			this.mysqlStart = 0;
		}
		this.start = start;
		this.mysqlStart = start - 1;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Map<String, Object> getDynamicFields() {
		if (dynamicFields != null && dynamicFields.size() > 0) {
			Set<String> set = dynamicFields.keySet();
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (dynamicFields.get(key) != null && dynamicFields.get(key).getClass().isArray()) {
					Object[] objArr = (Object[]) dynamicFields.get(key);
					if (objArr.length == 1) {
						dynamicFields.put(key, ((Object[]) dynamicFields.get(key))[0]);
					}
				}
			}
		}
		return dynamicFields;
	}

	public void setDynamicFields(Map<String, Object> dynamicFields) {
		this.dynamicFields = dynamicFields;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getMysqlStart() {
		return mysqlStart;
	}

	public void setMysqlStart(Integer mysqlStart) {
		this.mysqlStart = mysqlStart;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	 public int getBegin() {
			return begin;
		}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnds() {
		return ends;
	}

	public void setEnds(int ends) {
		this.ends = ends;
	}
}
