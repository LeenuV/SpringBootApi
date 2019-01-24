package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CC;
import com.example.demo.model.CCTeacher;
import com.example.demo.model.Teacher;

@Repository
public interface CCTeacherRepo extends JpaRepository<CCTeacher,Integer>{
	
	@Query("select count(ct) from CCTeacherInformation ct where ct.teacher =:teacher and ct.cc =:cc")
	public int getCountByTeacherAndCCId(@Param("teacher") Teacher teacher,@Param("cc") CC cc);
	
	@Query("select ct from CCTeacherInformation ct where requestByRole='cc' and ct.teacher=:id and ct.requestStatus='false'")
	public List<CCTeacher> getPendingCase(@Param("id") Teacher id);
	
	@Query("select ct from CCTeacherInformation ct where requestByRole='teacher' and ct.cc=:id and ct.requestStatus='false'")
	public List<CCTeacher> getCCPendingCase(@Param("id") CC id);
	
	
	@Transactional
	@Modifying
	@Query("update CCTeacherInformation ct set ct.requestStatus=:requestStatus,ct.confirmationDate=:confirmationDate where ct.requestID=:requestID")
	public int changeStatus(@Param("requestID") Integer requestID,@Param("requestStatus") String requestStatus,@Param("confirmationDate") Date confirmationDate);

}
