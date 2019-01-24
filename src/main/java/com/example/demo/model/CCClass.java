package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CCClass implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="cc_id",referencedColumnName="cc_id")
	private CC cc;
	
	@ManyToOne
	@JoinColumn(name="class_id",referencedColumnName="class_id")
	private Class classID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CC getCc() {
		return cc;
	}

	public void setCc(CC cc) {
		this.cc = cc;
	}

	public Class getClassID() {
		return classID;
	}

	public void setClassID(Class classID) {
		this.classID = classID;
	}
	
	

}
