package com.vina.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 5624550276593704198L;
	
	private String name;
	private String gender;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
