package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserInfoService;

@RestController
public class UserController {
	
	@Autowired
	private UserInfoService userService;
	
	@PostMapping(value="/newUser/register",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String add(@RequestBody User userInfo) {
		System.out.println("calles");
		return userService.saveUser(userInfo);
	}	
	
	@PostMapping(value="/newUser/validateOTP",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String validateOTP(@RequestBody User userInfo) {
		System.out.println("calles");
		if(userInfo.getPassword() != null)
			userInfo.setPassword(userInfo.getPassword());
			//userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return userService.validateOTP(userInfo);
	}	

}
