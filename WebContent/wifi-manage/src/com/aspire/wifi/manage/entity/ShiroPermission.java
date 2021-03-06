package com.aspire.wifi.manage.entity;

import java.io.Serializable;

public class ShiroPermission implements Serializable {

	private static final long serialVersionUID = -8792590494605747957L;

	private Integer id;
	private String name;
	private String description;
	private String permission;

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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}
