package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CC;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.repository.CCInfoRepo;
import com.example.demo.repository.UserInfoRepo;
import com.example.demo.utility.utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CCInfoService {
	
	@Autowired
	private CCInfoRepo ccInfoRepo;
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	public String getCC(Integer ccID)
	{
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		ObjectMapper ob=new ObjectMapper();
		String json="";
		
		try
		{
			if(ccInfoRepo.existsById(ccID))
			{
				CC ccInfo=ccInfoRepo.getOne(ccID);
				json=ob.writeValueAsString(ccInfo);
				key.add("Data");value.add(json);
			}
			else
			{
				key.add("status");value.add("warning");
				key.add("message");value.add("No such CC with given id");
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
	
	public String updateCC(CC ccInfo)
	{
		
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		try {
			if(ccInfoRepo.existsById(ccInfo.getId()))
			{
				User userInfo=userInfoRepo.getOne(ccInfo.getId());
				if(!userInfo.getName().equalsIgnoreCase(ccInfo.getName()))
				{
					userInfo.setName(ccInfo.getName());
					userInfoRepo.save(userInfo);
				}
				ccInfo.setModificationDate(utility.getTodayDate());
				ccInfoRepo.save(ccInfo);
				key.add("status");value.add("success");
				key.add("mesage");value.add("CC Information updated successfully");
			}
			else
			{
				key.add("status");value.add("warning");
				key.add("message");value.add("No such coaching with given id");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			key.clear();value.clear();
			key.add("status");value.add("failure");
			key.add("message");value.add("some error occured please try again later");
		}
		return utility.getResponse(key, value);
	}

}
