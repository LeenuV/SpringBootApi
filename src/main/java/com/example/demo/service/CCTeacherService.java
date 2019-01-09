package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CCInformation;
import com.example.demo.model.CCTeacherInformation;
import com.example.demo.model.TeacherInformation;
import com.example.demo.repository.CCInfoRepository;
import com.example.demo.repository.CCTeacherRepository;
import com.example.demo.repository.TeacherInfoRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.utility.utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CCTeacherService {
	
	@Autowired
	private CCInfoRepository ccRepo;
	
	@Autowired
	private TeacherInfoRepository teacherRepo;
	
	@Autowired
	private UserInfoRepository userInfoRepo;
	
	@Autowired
	private CCTeacherRepository ccTeacherRepo;
	
	public List<CCTeacherInformation> listccTeacher()
	{
		return ccTeacherRepo.findAll();
	}
	
	public CCTeacherInformation findOne(Integer id)
	{
		return ccTeacherRepo.getOne(id);
	}
	
	public String addCCTeacher(CCTeacherInformation ccTeacherInfo)
	{
		System.out.println("create");
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		
		try{
			Boolean ccDB=ccRepo.existsById(ccTeacherInfo.getCc().getId());
			Boolean teacherDB=teacherRepo.existsById(ccTeacherInfo.getTeacher().getId());
			
			int teacherUserDB=userInfoRepo.getCountByIDAndRole(ccTeacherInfo.getTeacher().getId(),"Teacher");
			
			if(!ccDB)
			{
				key.add("status");value.add("fail");
				key.add("message");value.add("No coaching registered with given id");
			}
			else if(teacherUserDB==0)
			{
				key.add("status");value.add("fail");
				key.add("message");value.add("No teacher registered with given id");
			}
			else
			{
				int ccTeacherDBCount=ccTeacherRepo.getCountByTeacherAndCCId(ccTeacherInfo.getTeacher(),ccTeacherInfo.getCc());
				if(ccTeacherDBCount==0)
				{
					ccTeacherInfo.setRequestedDate(utility.getTodayDate());
					ccTeacherInfo.setRequestStatus("false");
					if(!teacherDB) {
						teacherRepo.save(ccTeacherInfo.getTeacher());
					}
					ccTeacherRepo.save(ccTeacherInfo);
					key.add("status");value.add("success");
					key.add("message");value.add("Request Successfully sent");
				}
				else
				{
					key.add("status");value.add("fail");
					key.add("message");value.add("Request already sent");
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			key.clear();value.clear();
			key.add("Status"); value.add("Error");
			key.add("Message"); value.add("Some Error occured at Server End");
		}
		return utility.getResponse(key, value);
	}
	
	public String getPendingReq(String role,Integer id)
	{
		List<String> key=new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		String json="";
		List<CCTeacherInformation> ccTeacherReq=null;
		try
		{
			if(role.equalsIgnoreCase("teacher"))
			{
				Boolean teacherDB=teacherRepo.existsById(id);
				if(!teacherDB)
				{
					key.add("status");value.add("fail");
					key.add("message");value.add("No teacher registered with given id");
				}
				else
				{
					ccTeacherReq=ccTeacherRepo.getPendingCase(teacherRepo.getOne(id));
					if(ccTeacherReq==null||ccTeacherReq.size()==0)
					{
						key.add("status");value.add("warning");
						key.add("message");value.add("No Pending request");
					}
					else
					{
						key.add("status");value.add("success");
						key.add("message");value.add("Data fetched successfully");
						ObjectMapper ob=new ObjectMapper();
						json=ob.writeValueAsString(ccTeacherReq);
						key.add("Data");value.add(json);
					}
				}
			}
			else if(role.equalsIgnoreCase("CC"))
			{
				Boolean ccDB=ccRepo.existsById(id);
				if(!ccDB)
				{
					key.add("status");value.add("fail");
					key.add("message");value.add("No CC registered with given id");
				}
				else
				{
					ccTeacherReq=ccTeacherRepo.getCCPendingCase(ccRepo.getOne(id));
					if(ccTeacherReq==null||ccTeacherReq.size()==0)
					{
						key.add("status");value.add("warning");
						key.add("message");value.add("No Pending request");
					}
					else
					{
						key.add("status");value.add("success");
						key.add("message");value.add("Data fetched successfully");
						ObjectMapper ob=new ObjectMapper();
						json=ob.writeValueAsString(ccTeacherReq);
						key.add("Data");value.add(json);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			key.clear();value.clear();
			key.add("Status");value.add("Error");
			key.add("Message");value.add("Some Error occured at Server End");
			e.printStackTrace();
		}
		return utility.getResponse(key, value);
	}
	
	
	public String requestAction(Integer reqID,String status) {
		
		List<String> key=new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		try
		{
			Boolean req=ccTeacherRepo.existsById(reqID);
			if(!req)
			{
				key.add("status");value.add("fail");
				key.add("message");value.add("No request with given id");
			}
			else
			{
				int reqActionDB=ccTeacherRepo.changeStatus(reqID, status, utility.getTodayDate());
				if(reqActionDB==0)
				{
					key.add("status");value.add("fail");
					key.add("message");value.add("Failed to change the status of request");
				}
				else
				{
					key.add("status");value.add("success");
					key.add("message");value.add("Successfully updated status of request");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			key.clear();value.clear();
			key.add("Status");value.add("Error");
			key.add("Message");value.add("Some Error occured at Server End");
			e.printStackTrace();
		}
		return utility.getResponse(key, value);
	}

}
