/*
 * Created on Jun 8, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.action;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazda.technician.patsiltcrs.domain.IPatsILTBio;
import com.mazda.technician.patsiltcrs.persistence.ApplicationDevDAOFactory;
import com.mazda.technician.patsiltcrs.service.IApplicationConstants;
import com.mazda.technician.patsiltcrs.service.PatsILTServiceLocator;
import com.mazda.technician.patsiltcrs.service.IPatsILTCrsFinderService;
import com.mazda.technician.patsiltcrs.action.PatsILTTechList;
import com.mazdausa.common.util.ApplicationUtil;

import com.mazdausa.common.configuration.EmdcsRuntimeEnvironment;
import com.mazdausa.common.log.EMDCSLogger;

/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatsILTTechList extends HttpServlet implements Servlet, IApplicationConstants {
	
	public PatsILTTechList() {
		super();
	}
	
	private IPatsILTBio patsBio = null;
	private String techList;
	private String courseNum;
	private String enrlDate;
	private String patsJobs;
	private String fromDate;
	private String toDate; 
	
	
	private static Logger log;
	
	   static
	   {
	   	  initAction(); 
	   }

	   
	   protected static void initAction(){
	   	
	   	  log = EMDCSLogger.getLogger(PatsILTTechList.class);
	   	  String baseUrl = PatsILTTechList.class.getClassLoader().getResource(BASE_CONFIG_URL).getPath();
           if(baseUrl != null)
           {
            if(baseUrl.startsWith("/"))
                baseUrl = baseUrl.replaceFirst("/","");
            
            log.debug("Loading Pats ILT application service directory service");
            PatsILTServiceLocator.loadServiceDirectory(baseUrl+CONFIG_SERVICE_DIRECTORY);
            log.debug("Loading Pats ILT application DAO Configuration");
            ApplicationDevDAOFactory.getInstance().initialize(baseUrl+CONFIG_DAO_CONFIG_FILE);
            
        }
	   	  
	   	  
	   }
	   
	   final ResourceBundle patsILTProperties = ResourceBundle.getBundle("com.mazda.technician.patsiltcrs.resources.patsiltcrs");
	   
	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
		   
	   	  doPost(req,resp);
		    
		 }
	   
		
	   /**
		 * Capture the request and start the posting
		 */
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
		{
			String courseNum = "";
			String parmFromDt = "fromDate";
			String parmToDt   = "toDate";
			String parmJob    = "patsJob";
			String techList = "";
			this.enrlDate = null;
			this.fromDate = null;
			this.toDate   = null;
			String jobCd    = "";
			
			
			/*******************************************************
			 * Validate request parameters
			 *******************************************************
			 */
			
			/*
			 * Get From Date Input Parm
			 */
			this.fromDate = req.getParameter(parmFromDt);
			enrlDate = this.fromDate;
			if (this.fromDate == null) { 
				enrlDate =  IApplicationConstants.DEFAULT_DT;
				this.fromDate =  IApplicationConstants.DEFAULT_DT;
			}
			if ("".equals(this.fromDate)) { 
				enrlDate =  IApplicationConstants.DEFAULT_DT;
				this.fromDate =  IApplicationConstants.DEFAULT_DT;
			}
						
			/*
			 * Get To Date Input Parm
			 */
			this.toDate = req.getParameter(parmToDt);
			if (this.toDate == null) { 
				this.toDate =  IApplicationConstants.DEFAULT_DT;
			}
			if ("".equals(this.toDate)) { 
				this.toDate =  IApplicationConstants.DEFAULT_DT;
			}
			
			
			/*
			 * Get PATS Job Code Input Parm
			 */
			jobCd = req.getParameter(parmJob);			
			if ((jobCd != null) &&
			   !("".equals(jobCd))){
				jobCd = (" ('" + jobCd + "' )");
			}
			
			courseNum = getCourseNum();
			
			
			log.debug("Do Post:  Here in PatsILTTechList ");
			try
			{
			    this.patsBio = executeAction(enrlDate, this.fromDate, this.toDate, jobCd);
			    this.techList = getTechList();
			    resp.setContentType("text/html");
				resp.setHeader("techLIst", techList);
				PrintWriter out = resp.getWriter();
				out.println(this.techList);
				out.flush();
		        out.close();
			}catch(Exception e){
				log.error("Exception thrown by the PatsILTFinder Service layer.  Exception " +e.getMessage());				
			}
		    
		}
		
		public IPatsILTBio executeAction(String enrlDate, String fromDate, String toDate, String jobCd)throws Exception {
			
			this.courseNum = "";			
			this.courseNum = getCourseNum();
			this.enrlDate  = enrlDate;
			this.patsJobs  = jobCd;
			
			
			if ((this.patsJobs == null) ||
				("".equals(this.patsJobs))){	
				this.patsJobs =  getPatsJobs();
			}
			
			log.debug("PatsJobs: " + this.patsJobs);
			log.debug("From Date: " + fromDate);
			log.debug("To Date: " + toDate);
			
			try
			{
//				Lookup the PATS ILT Course finder 
	            
				IPatsILTCrsFinderService patsILTFinder = (IPatsILTCrsFinderService) PatsILTServiceLocator.locate(IPatsILTCrsFinderService.class);
	            //Invoke the finder
	            log.debug("Set the code into the finder service");
	            patsILTFinder.setCourseNum(this.courseNum);
	            patsILTFinder.setEnrlDate(this.enrlDate);
	            patsILTFinder.setPatsJobs(this.patsJobs);
	            patsILTFinder.setFromDate(fromDate);
	            patsILTFinder.setToDate(toDate);
	            log.debug("Invoke the finder service");
	            patsILTFinder.execute();
	            
	            log.debug("Retrieve the Pats ILT Course object from the finder service");
	            this.patsBio = patsILTFinder.getPatsILT();
	            
	            log.debug("Tech List Retrieved : " + patsBio.getTechList());
			}catch(Exception e){
				log.error("Exception thrown by the PatsILTFinder Service layer.  Exception " +e.getMessage());				
			}  
			
			
			return this.patsBio;
	
        }
		
		public String getTechList(){
		   
			this.techList = this.patsBio.getTechList();  
		 	
	        return this.techList;
		}
		
		private String getCourseNum(){
			//** Change by Interra to move the pats properties file out of the application EAR **
			//String courseNum = patsILTProperties.getString(COURSE_NUMBER);
			String courseNum = ApplicationUtil.getSystemProperty("PATS_ILT_CRS_PROPERTIES", COURSE_NUMBER);
			return courseNum;
	    }
		
		private String getPatsJobs(){
			//** Change by Interra to move the pats properties file out of the application EAR **
			//String patsJobs = patsILTProperties.getString("PATS_JOBS");
			String patsJobs = ApplicationUtil.getSystemProperty("PATS_ILT_CRS_PROPERTIES","PATS_JOBS");
			return patsJobs;
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

		   return prefix;
		} 
		
		
		
	   
	   
}
