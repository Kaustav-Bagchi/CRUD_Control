/*
 * Created on Jun 11, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.action;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.net.*;

import java.util.ResourceBundle;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazda.technician.patsiltcrs.domain.IPatsILTBio;
import com.mazda.technician.patsiltcrs.service.IApplicationConstants;

import com.mazdausa.common.log.EMDCSLogger;
import com.mazdausa.common.configuration.EmdcsRuntimeEnvironment;
import com.mazdausa.common.configuration.EMDCSPropertiesManager;
/**
 * @author jtodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatsILTCourseUserList extends HttpServlet implements Servlet, IApplicationConstants {
	
	
	 private IPatsILTBio patsBio = null;
	 private String techList;
	
	private static Logger log;
	
	   static
	   {
	   	  initAction(); 
       }
	   
   protected static void initAction(){
   	
   	log = EMDCSLogger.getLogger(PatsILTCourseUserList.class);
     	
  }
   final ResourceBundle patsILTProperties = ResourceBundle.getBundle("com.mazda.technician.patsiltcrs.resources.patsiltcrs");
   
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
	   
   	 log.debug("PatsILTCourseUserList Your Are Here in doGet");
    
   	 try{
   	   executeAction();
   	 }catch (Exception e){
   	 	
   	 }
   	
	}
   
   public void executeAction() throws Exception{
   	
   	   	
   	//URL patsURL = new URL(patsILTProperties.getString(PATS_URL + "." + getEnvPrefix()));
   	
   	URL patsURL = new URL(EMDCSPropertiesManager.getInstance().getProperty("APPLICATION_URLS",PATS_URL));
   	URLConnection ptConn = patsURL.openConnection();
   	BufferedReader in = new BufferedReader(new InputStreamReader(ptConn.getInputStream()));
   	
   	String inputLine;
   	while ((inputLine = in.readLine()) != null){
   		log.debug("Tech User Ids for Course 203: " +  inputLine);
   		in.close();
   	}
   		
   	
   }
   
   private static String getEnvPrefix()
	{
	    String prefix = "DEV"; 
	    if(EmdcsRuntimeEnvironment.isTest())
	        prefix = "TEST";
	    else
		    if(EmdcsRuntimeEnvironment.isQA())
		        prefix = "QA";
		    else
			    if(EmdcsRuntimeEnvironment.isProd())
			        prefix = "PROD";
			    
	   log.debug("Environment: " + prefix);		    

	   return prefix;
	} 	   
	   
}
