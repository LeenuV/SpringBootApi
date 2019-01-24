package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Course;
import com.example.demo.service.CourseInfoService;

@RestController
public class courseController {
	
	@Autowired
	private CourseInfoService courseService;
	
	@PostMapping(value="/course/addCourse",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reqAction(@RequestBody Course courseInfo)
	{
		return courseService.addCourse(courseInfo);
	}
}
