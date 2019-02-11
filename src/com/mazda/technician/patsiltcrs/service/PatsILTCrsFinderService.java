/*
 * Created on May 17, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.service;

import org.apache.log4j.Logger;

import com.mazda.technician.patsiltcrs.domain.IPatsILTBio;
import com.mazda.technician.patsiltcrs.persistence.ApplicationDevDAOFactory;
import com.mazda.technician.patsiltcrs.persistence.IPatsILTDAO;
import com.mazda.technician.patsiltcrs.service.EmdcsAppDevServiceAbstract;
import com.mazda.technician.patsiltcrs.service.IPatsILTCrsFinderService;
import com.mazda.technician.patsiltcrs.service.PatsILTCrsFinderService;
import com.mazdausa.common.exceptions.EmazdaApplicationException;
import com.mazdausa.common.log.EMDCSLogger;

/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatsILTCrsFinderService extends EmdcsAppDevServiceAbstract implements IPatsILTCrsFinderService {
	 
	/**
	 * @John Todd
	 * @version
	 * <b>Description:</b>
	 * 
	 */
	private static Logger log = EMDCSLogger.getLogger(PatsILTCrsFinderService.class);
    private IPatsILTBio patsILT;
    private String userId;
    private String certDate;
    private String courseNum;
    private String patsJobs;
    private String enrlDate;
    private String fromDate;
    private String toDate;
    
    
    /**
     * <b>Description:</b>
     * 
     */
    public PatsILTCrsFinderService()
    {
        
    	super();
    	log.debug("PatsILTCRSFinderService super");
        

    }

    /**
     * This method overrides super class
     * @see super class
     */
    public void execute() throws EmazdaApplicationException
    {
        try
        {
        	log.debug("The service is starting");
	        patsILT = PatsILTLmsService.call(getPropertyAsArray(this.getCourseNum()), getPropertyAsArray(this.patsJobs), this.getFromDate(), this.getToDate());
	        //patsILT = dao.find(this.getCourseNum(), this.getEnrlDate(), this.getPatsJobs(), this.getFromDate(), this.getToDate()); //PATS_Enhancement:Kaustav: find() will be replaced by call() which will be a webservice call
	        log.debug("The service complete");
	         
        }
        catch(Exception e)
        {
            throw new EmazdaApplicationException(e);
        }
    }

    /**
     * Description : Helper method to parse system property strings into string array
     * Author : Kaustav Bagchi
     */
    public String[] getPropertyAsArray(String input)
    {
    	
    	/*
    	 * Generic method to trim '(' , ')' from String and separate the String based on ',' separator as array
    	 */
    	
    	input =  input.substring(0, input.length()-1);
    	input = input.substring(1, input.length());
    	input = input.replace("'","");
    	return input.split(","); 	
    	
    }
    
    
    /**
     * <b>Description:</b>
     *
     * @return Returns the location.
     */
    public IPatsILTBio getPatsILT()
    {
        return patsILT;
    }
    /**
     * <b>Description</b> 
     *
     * @param location The location to set.
     */
    public void setPatsILT(IPatsILTBio patsILT)
    {
        this.patsILT = patsILT;
    }
    
    
    /**
     * This method overrides super class
     * @see super class
     */
    public void setCourseNum(String courseNum)
    {
        this.courseNum = courseNum;
    }
    /**
     * <b>Description:</b>
     *
     * @return Returns the Course Number.
     */
    public String getCourseNum()
    {
    	log.debug("Course Number = " + courseNum); 
        return courseNum;
        
    }
    
    /**
     * This method overrides super class
     * @see super class
     */
    public void setPatsJobs(String patsJobs)
    {
        this.patsJobs = patsJobs;
    }
    /**
     * <b>Description:</b>
     *
     * @return Returns the Course Number.
     */
    public String getPatsJobs()
    {
    	log.debug("Pats Job Codes = " + patsJobs); 
        return patsJobs;
        
    }
    /**
     * This method overrides super class
     * @see super class
     */
    public void setEnrlDate(String enrlDate)
    {
        this.enrlDate = enrlDate;
    }
    /**
     * <b>Description:</b>
     *
     * @return Returns the Course Number.
     */
    public String getEnrlDate()
    {
    	log.debug("Enrollment Date = " + enrlDate); 
        return enrlDate;
        
    } 
    
    /**
     * This method overrides super class
     * @see super class
     */
    public void setFromDate(String fromDate)
    {
        this.fromDate = fromDate;
    }
    /**
     * <b>Description:</b>
     *
     * @return Returns the Course Number.
     */
    public String getFromDate()
    {
    	log.debug("From Date = " + fromDate); 
        return fromDate;
        
    } 
    /**
     * This method overrides super class
     * @see super class
     */
    public void setToDate(String toDate)
    {
        this.toDate = toDate;
    }
    /**
     * <b>Description:</b>
     *
     * @return Returns the Course Number.
     */
    public String getToDate()
    {
    	log.debug("To Date = " + toDate); 
        return toDate;
        
    }    
    /**
     * This method overrides super class
     * @see super class
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    /**
     * <b>Description:</b>
     *
     * @return Returns the locationCode.
     */
    public String getUserId()
    {
        return userId;
    }
    
   
}