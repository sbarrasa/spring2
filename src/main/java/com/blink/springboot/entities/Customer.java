package com.blink.springboot.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.blink.springboot.config.Formats;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

public class Customer implements Serializable {
	private static final long serialVersionUID = 666L;


	public Customer() {}
	
	
	@JsonView(Views.Order.class)
	private Long id;

	@JsonView(Views.Order.class)
	private String names;

	@JsonView(Views.Order.class)
	private String lastNames;
	
	@JsonView(Views.Order.class)
	private Sex sex;
	
	@JsonFormat(pattern=Formats.DATE_VIEW)
	private LocalDate birthday;

	private List<Specs> specs;

	private List<Customer> childs;
	 	
	public Long getId() {
		return id;
	}
	public Customer setId(Long id) {
		this.id = id;
		return this;

	}

	public String getNames() {
		return names;
	}
	public Customer setNames(String names) {
		this.names = names;
		return this;
	}
	public String getLastNames() {
		return lastNames;
	}
	public Customer setLastNames(String lastNames) {
		this.lastNames = lastNames;
		return this;
	}
	public Sex getSex() {
		return sex;
	}
	public Customer setSex(Sex sex) {
		this.sex = sex;
		return this;

	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public Customer setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;

	}




	public Integer getAge() {
		return Period.between(birthday, LocalDate.now()).getYears();
	}


	public List<Customer> getChilds() {
		return childs;
	}

	public Customer setChilds(List<Customer> childs) {
		this.childs = childs;
		return this;

	}
	public List<Specs> getSpecs() {
		return this.specs;
	}


	public Customer setSpecs(List<Specs> specs) {
		this.specs = specs;
		return this;

	}

	@JsonIgnore
	public String getFullName() {
		return "%s, %s".formatted(getLastNames(), getNames());
	}

	@JsonIgnore
	public String getFullNameAndId() {
		return "%s (#%d)".formatted(getFullName(), getId());
	}

	public String toString() {
		return getFullNameAndId();
	}

}
