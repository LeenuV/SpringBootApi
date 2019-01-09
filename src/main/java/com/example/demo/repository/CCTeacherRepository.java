package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CCInformation;
import com.example.demo.model.CCTeacherInformation;
import com.example.demo.model.TeacherInformation;

@Repository
public interface CCTeacherRepository extends JpaRepository<CCTeacherInformation,Integer>{
	
	@Query("select count(ct) from CCTeacherInformation ct where ct.teacher =:teacher and ct.cc =:cc")
	public int getCountByTeacherAndCCId(@Param("teacher") TeacherInformation teacher,@Param("cc") CCInformation cc);
	
	@Query("select ct from CCTeacherInformation ct where requestByRole='cc' and ct.teacher=:id and ct.requestStatus='false'")
	public List<CCTeacherInformation> getPendingCase(@Param("id") TeacherInformation id);
	
	@Query("select ct from CCTeacherInformation ct where requestByRole='teacher' and ct.cc=:id and ct.requestStatus='false'")
	public List<CCTeacherInformation> getCCPendingCase(@Param("id") CCInformation id);
	
	
	@Transactional
	@Modifying
	@Query("update CCTeacherInformation ct set ct.requestStatus=:requestStatus,ct.confirmationDate=:confirmationDate where ct.requestID=:requestID")
	public int changeStatus(@Param("requestID") Integer requestID,@Param("requestStatus") String requestStatus,@Param("confirmationDate") Date confirmationDate);

}
