package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CCSubjectInformation implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="subject_id",referencedColumnName="subject_id")
	private SubjectInformation subject;
	@ManyToOne
	@JoinColumn(name="cc_id",referencedColumnName="cc_id")
	private CCInformation cc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SubjectInformation getSubject() {
		return subject;
	}
	public void setSubject(SubjectInformation subject) {
		this.subject = subject;
	}
	public CCInformation getCc() {
		return cc;
	}
	public void setCc(CCInformation cc) {
		this.cc = cc;
	}

	
	
}
