package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CCTeacher;
import com.example.demo.service.CCTeacherService;
import com.example.demo.utility.utility;

@RestController
public class ccTeacherController {
	
	@Autowired
	private CCTeacherService ccTeacherService;
	
	@PostMapping(value="/affilliation/request",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reqTeacherforCC(@RequestBody CCTeacher ccTeacher)
	{
		System.out.println(utility.isNull(ccTeacher));
		return ccTeacherService.addCCTeacher(ccTeacher);
	}
	
	@GetMapping(value="/affilliation/request/{Role}/{ID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public String getPendingReq(@PathVariable("Role") String role,@PathVariable("ID") Integer id)
	{
		return ccTeacherService.getPendingReq(role,id);
	}
	
	@PostMapping(value="/affilliation/requestAction/{reqID}/{status}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reqAction(@PathVariable("reqID") Integer reqID,@PathVariable("status") String status)
	{
		return ccTeacherService.requestAction(reqID,status);
	}

}
