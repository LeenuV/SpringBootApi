package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentInfoService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentInfoService studentService;

	@PutMapping(value="/student/UpdateStudent/{studentID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateStudent(@RequestBody Student studentInfo,@PathVariable("studentID") Integer studentID)
	{
		studentInfo.setId(studentID);
		return studentService.updateStudent(studentInfo);
	}
	
	@GetMapping(value="/student/getDetail/{studentID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findStudent(@PathVariable("studentID") Integer studentID)
	{
		return studentService.getStudent(studentID);
	}
}
