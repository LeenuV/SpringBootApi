package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CCInformation;
import com.example.demo.model.CCTeacherInformation;
import com.example.demo.model.CourseInformation;
import com.example.demo.model.StudentInformation;
import com.example.demo.model.TeacherInformation;
import com.example.demo.model.UserInformation;
import com.example.demo.service.CCInfoService;
import com.example.demo.service.CCTeacherService;
import com.example.demo.service.CourseInfoService;
import com.example.demo.service.StudentInfoService;
import com.example.demo.service.TeacherInfoService;
import com.example.demo.service.UserInfoService;
import com.example.demo.utility.utility;

@RestController
public class MainController {
	
	@Autowired
	private UserInfoService userService;
	
	@Autowired
	private StudentInfoService studentService;
	
	@Autowired
	private TeacherInfoService teacherService;
	
	@Autowired
	private CCInfoService ccService;
	
	@Autowired
	private CCTeacherService ccTeacherService;
	
	@Autowired
	private CourseInfoService courseService;
	
	@RequestMapping(value="/check",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public String check()
	{
		System.out.println("hello world");
		return "Hello";
	}
	
	@PostMapping(value="/newUser/register",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String add(@RequestBody UserInformation userInfo) {
		System.out.println("calles");
		return userService.saveUser(userInfo);
	}	

	@PostMapping(value="/newUser/validateOTP",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String validateOTP(@RequestBody UserInformation userInfo) {
		System.out.println("calles");
		if(userInfo.getPassword() != null)
			userInfo.setPassword(userInfo.getPassword());
			//userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return userService.validateOTP(userInfo);
	}	
	
	@PutMapping(value="/student/UpdateStudent/{studentID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateStudent(@RequestBody StudentInformation studentInfo,@PathVariable("studentID") Integer studentID)
	{
		studentInfo.setId(studentID);
		return studentService.updateStudent(studentInfo);
	}
	
	@PutMapping(value="/teacher/updateTeacher/{teacherID}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateTeacher(@RequestBody TeacherInformation teacherInfo,@PathVariable("teacherID") Integer teacherID)
	{
		teacherInfo.setId(teacherID);
		return teacherService.updateTeacher(teacherInfo);
	}
	
	@PutMapping(value="/CC/updateCC/{ccID}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateCC(@RequestBody CCInformation ccInfo,@PathVariable("ccID") Integer ccID)
	{
		ccInfo.setId(ccID);
		return ccService.updateCC(ccInfo);
	}
	
	@GetMapping(value="/student/getDetail/{studentID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findStudent(@PathVariable("studentID") Integer studentID)
	{
		return studentService.getStudent(studentID);
	}
	
	@GetMapping(value="/teacher/getDetail/{teacherID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findTeacher(@PathVariable("teacherID") Integer teacherID)
	{
		return teacherService.getTeacher(teacherID);
	}
	
	@GetMapping(value="/cc/getDetail/{ccID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findCC(@PathVariable("ccID") Integer ccID)
	{
		return ccService.getCC(ccID);
	}
	
	@PostMapping(value="/affilliation/request",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reqTeacherforCC(@RequestBody CCTeacherInformation ccTeacher)
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
	
	@PostMapping(value="/course/addCourse",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reqAction(@RequestBody CourseInformation courseInfo)
	{
		return courseService.addCourse(courseInfo);
	}
}
