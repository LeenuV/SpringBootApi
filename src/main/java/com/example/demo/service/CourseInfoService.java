package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CourseInformation;
import com.example.demo.repository.CourseInfoRepository;
import com.example.demo.utility.utility;

@Service
public class CourseInfoService {
	
	@Autowired
	private CourseInfoRepository courseInfoRepo;
	
	public String addCourse(CourseInformation courseInfo)
	{
		List<String> key=new ArrayList<String>();
		List<String>value=new ArrayList<String>();
		
		courseInfoRepo.save(courseInfo);
		key.add("status");value.add("success");
		key.add("message");value.add("Course added successfully");
		return utility.getResponse(key, value);
	}

}
