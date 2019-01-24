package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.repository.StudentInfoRepo;
import com.example.demo.repository.UserInfoRepo;
import com.example.demo.utility.utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentInfoService {
	
	@Autowired
	StudentInfoRepo studentInfoRepo;
	
	@Autowired
	UserInfoRepo userInfoRepo;
	
	public String updateStudent(Student studentInfo)
	{
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		
		try
		{
			if(studentInfoRepo.existsById(studentInfo.getId()))
			{
				Student studentDB=studentInfoRepo.getOne(studentInfo.getId());
				if(!studentDB.getName().equalsIgnoreCase(studentInfo.getName())) {
					User userDB=userInfoRepo.getOne(studentDB.getId());
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
				Student studentInfo=studentInfoRepo.getOne(studentID);
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
