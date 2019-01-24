package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CC implements Serializable{
	
	@Id
	@Column(name="cc_id")
	private Integer id;
	@Column(name="cc_name")
	private String name;
	@Column(name="cc_email")
	private String email;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	private Date creationDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone = "IST")
	private Date modificationDate;
	@Column(name="cc_status")
	private String status;
	@Column(name="cc_phone")
	private String phone;
	
	private String address1;
	private String address2;
	private String pincode;
	private String city;
	private String state;
	
	private String picturePath;
	private String description;
	private String videoPath;
	
	private Integer subjectCount;
	private Integer classCount;
		
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="cc_id",referencedColumnName="user_id")
	private User userInformation;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public Integer getSubjectCount() {
		return subjectCount;
	}
	public void setSubjectCount(Integer subjectCount) {
		this.subjectCount = subjectCount;
	}
	public Integer getClassCount() {
		return classCount;
	}
	public void setClassCount(Integer classCount) {
		this.classCount = classCount;
	}
	
	
	
	public User getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(User userInformation) {
		this.userInformation = userInformation;
	}
	public CC(Integer id,String name,String phone, String status, Date creationDate) {
		super();
		this.id=id;
		this.name = name;
		this.creationDate = creationDate;
		this.status = status;
		this.phone = phone;
	}
	public CC() {
		super();
	}
	public CC(Integer id, String name, String email, Date creationDate, Date modificationDate, String status,
			String phone, String address1, String address2, String pincode, String city, String state,
			String picturePath, String description, String videoPath, Integer subjectCount, Integer classCount,
			User userInformation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.status = status;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.picturePath = picturePath;
		this.description = description;
		this.videoPath = videoPath;
		this.subjectCount = subjectCount;
		this.classCount = classCount;
		this.userInformation = userInformation;
	}
	public CC(Integer id) {
		super();
		this.id = id;
	}
	
	
	


}
