package com.example.demo.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class utility {
	
	public static String generateOTP()
	{
		java.util.Random r=new java.util.Random();
        String otp = Integer.toString(r.nextInt(1000000));
        return otp;
	}
	
	public static String getResponse(List<String> key,List<String> value)
	{
		JSONObject response =new JSONObject();
		try
		{
			Iterator<String> it1=key.iterator();
			Iterator<String> it2=value.iterator();
			
			while(it1.hasNext()&&it2.hasNext())
			{
				response.put(it1.next(), it2.next());
			}
			return response.toString();
		}
		catch(Exception ex)
		{
			try {
				response.put("Error", "Error while creating response "+ex.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		return "";
		
	}
	
	public static String getResponse(String key,String value)
	{
		JSONObject response =new JSONObject();
		try
		{
			response.put(key, value);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return response.toString();
		
	}
	
	public static Date getTodayDate(){
		   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();  
		    return date;
	}
	
	public static Boolean isNull(Object object){
		if(object != null){
			return false;
		}else{
			return true;
		}
	}

}
