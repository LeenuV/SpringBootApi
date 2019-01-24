package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Class implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="class_id")
	private Integer classID;
	private String classDetail;
	
	public Integer getClassID() {
		return classID;
	}
	public void setClassID(Integer classID) {
		this.classID = classID;
	}
	public String getClassDetail() {
		return classDetail;
	}
	public void setClassDetail(String classDetail) {
		this.classDetail = classDetail;
	}

	
	
}
