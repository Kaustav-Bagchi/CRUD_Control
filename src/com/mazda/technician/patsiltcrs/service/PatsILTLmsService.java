package com.mazda.technician.patsiltcrs.service;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.mazda.technician.patsiltcrs.domain.IPatsILTBio;
import com.mazda.technician.patsiltcrs.domain.PatsILTBio;
import com.mazda.technician.patsiltcrs.persistence.LmsResponseBean;
import com.mazdausa.common.log.EMDCSLogger;
import com.mazdausa.common.util.ApplicationUtil;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;

/**
 * 
 * @author Kaustav Bagchi
 *
 */

public class PatsILTLmsService {
	
	private static Logger log = EMDCSLogger.getLogger(PatsILTLmsService.class);
	
	public static IPatsILTBio call(String[] courseNum,String[] jobCode,String fromDate,String toDate)
	{
		IPatsILTBio bio = new PatsILTBio();
		log.debug("Pats Jobs ="+jobCode);
		HttpURLConnection connection = null;
		
		try
		{
		
			if (toDate.equals(IApplicationConstants.DEFAULT_DT))
			{
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, 1);
				Date nextDay = c.getTime();
				SimpleDateFormat serviceFormat = new SimpleDateFormat("dd/MM/yyyy");
				toDate = serviceFormat.format(nextDay);
			}
			
			URL url = new URL(ApplicationUtil.getSystemProperty("PATS_ILT_CRS_PROPERTIES","URL"));
			
			connection = (HttpURLConnection)url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type","application/json");
			

			
			String inputJson = getLmsInput(courseNum,jobCode,fromDate,toDate);
			System.out.println(inputJson);
			log.debug("Input to LMS is " + inputJson);
			
			OutputStream os = connection.getOutputStream();
			os.write(inputJson.getBytes());
			os.flush();
			os.close();
			
			int responseCode = connection.getResponseCode();
			
			
			if(responseCode == HttpURLConnection.HTTP_OK)
			{
				
				ObjectMapper om = new ObjectMapper();
				
				InputStream in = new BufferedInputStream(connection.getInputStream());
				
				LmsResponseBean[] res = om.readValue(in, LmsResponseBean[].class);
				for(LmsResponseBean bean : res)
				{
					
					bio.addUserList(bean.getUserId());
				}
				in.close();
			}
			else
			{
				log.error("Getting an error while sending request to LMS web service.Response code is = "+responseCode);
			}
			
		}
		catch(Exception e)
		{
			log.error("Exception when finding the Technician Course Data for Course Number " + courseNum + "  Exception: "+e);
		}
		finally
		{
			connection.disconnect();
		}
		
		return bio;
	}
	
	
	public static String getLmsInput(String[] courseNum,String[] jobCode,String fromDate,String toDate)
	{
		StringBuffer inputJson = new StringBuffer();
		
		
		inputJson.append("{\"courseNumber\":[");
		
		for (int i=0;i<courseNum.length;i++)
		{
			if(i==(courseNum.length-1))
			{
				inputJson.append(courseNum[i]);
			}
			else
			{
				inputJson.append(courseNum[i]+",");
			}
		}
		
		inputJson.append("],\"jobCode\":[");
		
		for(int i=0;i<jobCode.length;i++)
		{
			if(i==(jobCode.length-1))
			{
				inputJson.append("\""+jobCode[i]+"\"");
			}
			else
			{
				inputJson.append("\""+jobCode[i]+"\",");
			}
		}
		
		inputJson.append("],\"fromDate\":\""+parseDate(fromDate)+"\",\"toDate\":\""+parseDate(toDate)+"\"}");
		
		return inputJson.toString();
	}
	
	/**
     * Description : Method to parse the date from request parameters from YYYY-MM-DD to DD/MM/YYYY
     * Author : Kaustav Bagchi
     */
    
    public static String parseDate(String date)
    {
    	if(date.indexOf("-")>=0)
    	{
	    	String temp[] = date.split("-");
	    	date = temp[2]+"/"+temp[1]+"/"+temp[0];
    	}
    	return date;
    }

}
