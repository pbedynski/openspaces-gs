package com.ubs.openspace.model;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;

@SpaceClass
public class KPI implements Serializable {

	private static final long serialVersionUID = -5725663251414957522L;

	private String name;
	private Integer value;

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
