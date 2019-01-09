package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CCTeacherInformation implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Request_ID")
	private Integer requestID;
	
	@ManyToOne
	@JoinColumn(name="teacher_id",referencedColumnName="teacher_id")
	private TeacherInformation teacher;
	
	@ManyToOne
	@JoinColumn(name="cc_id",referencedColumnName="cc_id")
	private CCInformation cc;
	
	private String requestByRole;
	
	@Column(name="Req_Status",columnDefinition = "varchar(20) not null default 'false'")
	private String requestStatus;
	
	@Temporal(TemporalType.DATE)
	private Date requestedDate;
	
	private Date confirmationDate;

	public Integer getRequestID() {
		return requestID;
	}

	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
	}

	public TeacherInformation getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherInformation teacher) {
		this.teacher = teacher;
	}

	public CCInformation getCc() {
		return cc;
	}

	public void setCc(CCInformation cc) {
		this.cc = cc;
	}

	public String getRequestByRole() {
		return requestByRole;
	}

	public void setRequestByRole(String requestByRole) {
		this.requestByRole = requestByRole;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public CCTeacherInformation() {
		super();
	}

	public CCTeacherInformation(TeacherInformation teacher, CCInformation cc, String requestByRole) {
		super();
		this.teacher = teacher;
		this.cc = cc;
		this.requestByRole = requestByRole;
	}

	public CCTeacherInformation(Integer requestID, TeacherInformation teacher, CCInformation cc, String requestByRole,
			String requestStatus, Date requestedDate, Date confirmationDate) {
		super();
		this.requestID = requestID;
		this.teacher = teacher;
		this.cc = cc;
		this.requestByRole = requestByRole;
		this.requestStatus = requestStatus;
		this.requestedDate = requestedDate;
		this.confirmationDate = confirmationDate;
	}

	public CCTeacherInformation(Integer requestID, TeacherInformation teacher, String requestByRole,
			String requestStatus, Date requestedDate, Date confirmationDate) {
		super();
		this.requestID = requestID;
		this.teacher = teacher;
		this.requestByRole = requestByRole;
		this.requestStatus = requestStatus;
		this.requestedDate = requestedDate;
		this.confirmationDate = confirmationDate;
	}

	
	
	

}
