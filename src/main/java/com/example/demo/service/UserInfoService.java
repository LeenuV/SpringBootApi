package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CC;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.repository.CCInfoRepo;
import com.example.demo.repository.StudentInfoRepo;
import com.example.demo.repository.TeacherInfoRepo;
import com.example.demo.repository.UserInfoRepo;
import com.example.demo.utility.utility;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	@Autowired
	private StudentInfoRepo studentInfoRepo;
	
	@Autowired
	private TeacherInfoRepo teacherInfoRepo;
	
	@Autowired
	private CCInfoRepo ccInfoRepo;
	
	public String saveUser(User userInfo)
	{
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		try {
			User userInfoDB=userInfoRepo.findByRoleAndPhone(userInfo.getPhone(),userInfo.getRole());
			if(userInfoDB!=null && userInfoDB.getId()!=null)
			{
				key.add("status");value.add("warning");
				key.add("message");value.add("User already added");
				key.add("role");value.add(userInfoDB.getRole());
				key.add("phone");value.add(userInfoDB.getPhone());
				key.add("isActive");value.add(userInfoDB.getUserStatus());
			}
			else
			{
				userInfo.setOtp(utility.generateOTP());
				userInfoRepo.save(userInfo);
				key.add("status");value.add("success");
				key.add("message");value.add("User added Successfully");
				key.add("role");value.add(userInfo.getRole());
				key.add("phone");value.add(userInfo.getPhone());
				key.add("userID");value.add(userInfo.getId().toString());
				key.add("OTP");value.add(userInfo.getOtp());
			}
		}
		catch(Exception ex)
		{
		key.clear();value.clear();
		key.add("status");value.add("Error");
		key.add("message");value.add("some error occured at server side");
		}
		return utility.getResponse(key, value);
		
	}
	
	public String validateOTP(User userInfo)
	{
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		
		User userInfoDB=userInfoRepo.findByRoleAndPhone(userInfo.getPhone(), userInfo.getRole());
		try {
			if(userInfoDB==null)
			{
				key.add("status");value.add("warning");
				key.add("message");value.add("Please register first");
			}
			else
			{
				if(userInfoDB.getOtp().equalsIgnoreCase(userInfo.getOtp())) {
					if(userInfoDB.getRole().equalsIgnoreCase("CC"))
					{
						CC ccInfo =new CC(userInfoDB.getId(),userInfoDB.getName(),userInfoDB.getPhone(),"True",utility.getTodayDate());
						ccInfoRepo.save(ccInfo);
					}
					else if(userInfoDB.getRole().equalsIgnoreCase("Teacher"))
					{
						Teacher teacherInfo =new Teacher(userInfoDB.getId(),userInfoDB.getName(),userInfoDB.getPhone(),"True",utility.getTodayDate());
						teacherInfoRepo.save(teacherInfo);
					}
					else
					{
						Student studentInfo =new Student(userInfoDB.getId(),userInfoDB.getName(),userInfoDB.getPhone(),"True",utility.getTodayDate());
						studentInfoRepo.save(studentInfo);
					}
					key.add("status");value.add("Success");
					key.add("message");value.add("OTP Matches");
				}
				else
				{
					key.add("status");value.add("warning");
					key.add("message");value.add("Incorrect OTP");
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			key.clear();value.clear();
			key.add("status");value.add("error");
			key.add("message");value.add("some error occured at server side");
		}
		return utility.getResponse(key, value);
	}
	

}
