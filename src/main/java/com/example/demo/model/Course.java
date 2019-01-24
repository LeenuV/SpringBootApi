package com.example.demo.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="course")
public class Course implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="course_id")
	private Integer id;
	private Integer createdById;
	private String createdByRole;
	
	@ManyToOne
	@JoinColumn(name="class_id",referencedColumnName="class_id")
	private Class classID;
	
	@ManyToOne
	@JoinColumn(name="subject_id",referencedColumnName="subject_id")
	private Subject subject;
	
	@ManyToOne
	@JoinColumn(name="teacher_id",referencedColumnName="teacher_id")
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name="cc_id",referencedColumnName="cc_id")
	private CC cc;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	@Column(name="Start_Date",columnDefinition="date")
	private Date startDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	@Column(name="End_Date",columnDefinition="date")
	private Date endDate;
	
	private String frequency;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:MM:ss")
	@Column(name="Start_Time",columnDefinition="time")
	private Time startTime;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:MM:ss")
	@Column(name="End_Time",columnDefinition="time")
	private Time endTime;
	
	private String title;
	
	private String description;
	
	private String status;
	
	private Integer studentCount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCreatedById() {
		return createdById;
	}
	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}
	public String getCreatedByRole() {
		return createdByRole;
	}
	public void setCreatedByRole(String createdByRole) {
		this.createdByRole = createdByRole;
	}
	public Class getClassID() {
		return classID;
	}
	public void setClassID(Class classID) {
		this.classID = classID;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public CC getCc() {
		return cc;
	}
	public void setCc(CC cc) {
		this.cc = cc;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	
	

}
