package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CCSubject implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="subject_id",referencedColumnName="subject_id")
	private Subject subject;
	@ManyToOne
	@JoinColumn(name="cc_id",referencedColumnName="cc_id")
	private CC cc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public CC getCc() {
		return cc;
	}
	public void setCc(CC cc) {
		this.cc = cc;
	}

	
	
}
