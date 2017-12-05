package com.aspire.wifi.wap.entity;

import java.io.Serializable;
import java.util.Collection;

public class ShiroRole implements Serializable {

	private static final long serialVersionUID = 6177417450707400228L;

	private Integer id;
	private String name;
	private String description;

	private Collection<ShiroPermission> pmss;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<ShiroPermission> getPmss() {
		return pmss;
	}

	public void setPmss(Collection<ShiroPermission> pmss) {
		this.pmss = pmss;
	}
}
