package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.TeacherInformation;
import com.example.demo.model.UserInformation;
import com.example.demo.repository.TeacherInfoRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.utility.utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TeacherInfoService {
	
	@Autowired
	private TeacherInfoRepository teacherInfoRepo;
	
	@Autowired
	private UserInfoRepository userInfoRepo;
	
	public String getTeacher(Integer teacherID)
	{
		List<String> key=new ArrayList<String>();
		List<String>value=new ArrayList<String>();
		ObjectMapper ob=new ObjectMapper();
		String json="";
		try
		{
			if(teacherInfoRepo.existsById(teacherID))
			{
				TeacherInformation teacherInfo=teacherInfoRepo.getOne(teacherID);
				json=ob.writeValueAsString(teacherInfo);
				key.add("data");value.add(json);
			}
			else
			{
				key.add("status");value.add("warning");
				key.add("message");value.add("No teacher exists of given id");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			key.clear();value.clear();
			key.add("status");value.add("failed");
			key.add("message");value.add("Some error occured please try again");
		}
		return utility.getResponse(key, value);
	}
	
	public String updateTeacher(@RequestBody TeacherInformation teacherInfo)
	{
		List<String> key=new ArrayList<String>();
		List<String>value=new ArrayList<String>();
		try
		{
			if(teacherInfoRepo.existsById(teacherInfo.getId()))
			{
				TeacherInformation teacherInfoDB=teacherInfoRepo.getOne(teacherInfo.getId());
				if(!teacherInfoDB.getName().equalsIgnoreCase(teacherInfo.getName())) {
					UserInformation userInfo=userInfoRepo.getOne(teacherInfoDB.getId());
					userInfo.setName(teacherInfo.getName());
					userInfoRepo.save(userInfo);
				}
				
				teacherInfo.setModificationDate(utility.getTodayDate());
				teacherInfoRepo.save(teacherInfo);
				key.add("status");value.add("success");
				key.add("message");value.add("Teacher Information updated successfully");
			}
			else
			{
				key.add("status");value.add("warning");
				key.add("message");value.add("No such teacher with given id");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			key.clear();value.clear();
			key.add("status");value.add("failed");
			key.add("message");value.add("Some error occured please try again");
		}
		return utility.getResponse(key, value);
		
	}

}
