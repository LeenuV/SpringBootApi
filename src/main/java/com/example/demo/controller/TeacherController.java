package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherInfoService;

@RestController
public class TeacherController {
	
	@Autowired
	private TeacherInfoService teacherService;
	
	@PutMapping(value="/teacher/updateTeacher/{teacherID}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateTeacher(@RequestBody Teacher teacherInfo,@PathVariable("teacherID") Integer teacherID)
	{
		teacherInfo.setId(teacherID);
		return teacherService.updateTeacher(teacherInfo);
	}
	
	@GetMapping(value="/teacher/getDetail/{teacherID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findTeacher(@PathVariable("teacherID") Integer teacherID)
	{
		return teacherService.getTeacher(teacherID);
	}

}
