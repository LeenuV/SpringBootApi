package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StudentInformation;
import com.example.demo.model.UserInformation;
import com.example.demo.repository.StudentInfoRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.utility.utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentInfoService {
	
	@Autowired
	StudentInfoRepository studentInfoRepo;
	
	@Autowired
	UserInfoRepository userInfoRepo;
	
	public String updateStudent(StudentInformation studentInfo)
	{
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		
		try
		{
			if(studentInfoRepo.existsById(studentInfo.getId()))
			{
				StudentInformation studentDB=studentInfoRepo.getOne(studentInfo.getId());
				if(!studentDB.getName().equalsIgnoreCase(studentInfo.getName())) {
					UserInformation userDB=userInfoRepo.getOne(studentDB.getId());
					userDB.setName(studentInfo.getName());
					userInfoRepo.save(userDB);
				}
				studentInfo.setModificationDate(utility.getTodayDate());
				studentInfoRepo.save(studentInfo);
				key.add("status");value.add("success");
				key.add("message");value.add("student updated successfully");
			}
			else
			{
				key.add("status");value.add("warning");
				key.add("message");value.add("No such student with given Id");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			key.clear();value.clear();
			key.add("status");value.add("error");
			key.add("message");value.add("failed to save please try again");
		}
		
		return utility.getResponse(key, value);
	}
	
	public String getStudent(Integer studentID)
	{
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		ObjectMapper ob=new ObjectMapper();
		String json="";
		
		try
		{
			if(studentInfoRepo.existsById(studentID))
			{
				StudentInformation studentInfo=studentInfoRepo.getOne(studentID);
				json=ob.writeValueAsString(studentInfo);
				key.add("Data");value.add(json);
			}
			else
			{
				key.add("status");value.add("warning");
				key.add("message");value.add("No such student with given id");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			key.clear();value.clear();
			key.add("status");value.add("error");
			key.add("message");value.add("some error occured please try again later");
		}
		return utility.getResponse(key, value);
	}

}
