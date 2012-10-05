package com.ubs.openspace.model;

import java.io.Serializable;

public class KPI implements Serializable {
	
	private static final long serialVersionUID = -5725663251414957522L;
	String name;
	Integer value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

}
