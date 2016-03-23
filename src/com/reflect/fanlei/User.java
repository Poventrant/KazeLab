package com.reflect.fanlei;

import javax.persistence.*;
import javax.persistence.Entity;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 2016-3-5 庞文全
 */
public class User implements java.io.Serializable {

	private Integer id;
	private String name;

	public User() {
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


}