package com.ubs.openspace.model;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass
public class KPI implements Serializable {

	private static final long serialVersionUID = -5725663251414957522L;

	private String name;
	private Integer value;
	private String category;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SpaceIndex(type=SpaceIndexType.EXTENDED)
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@SpaceIndex(type=SpaceIndexType.BASIC)
	@SpaceRouting
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return String.format("[KPI] name: %s, value: %d, category: %s",name, value, category);
	}
}
