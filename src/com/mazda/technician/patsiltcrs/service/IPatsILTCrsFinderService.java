/*
 * Created on May 17, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.service;

import com.mazda.technician.patsiltcrs.domain.IPatsILTBio;
import com.mazda.technician.patsiltcrs.service.IPatsILTService;

/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IPatsILTCrsFinderService extends IPatsILTService{
	
	
    /**
     * <b>Description</b> 
     *
     * @param ImagePrtTech The ImagePrtTech to set.
     */
    void setUserId(String string);
    
    void setCourseNum(String string);
    
    void setEnrlDate(String string);
    
    void setFromDate(String string);
    
    void setToDate(String string);
    
    void setPatsJobs(String string);
    
    /**
     * @return
     */
    IPatsILTBio getPatsILT();
    
   	
}
