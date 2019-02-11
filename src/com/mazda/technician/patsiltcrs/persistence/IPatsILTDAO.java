/*
 * Created on May 14, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.persistence;

/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import com.mazda.technician.patsiltcrs.domain.IPatsILTBio;
import com.mazda.technician.patsiltcrs.persistence.IPatsILTDAO;
import com.mazda.technician.patsiltcrs.service.IApplicationConstants;

/**
 * @author mvu
 * @version
 * <b>Description:</b>
 * 
 */
public interface IPatsILTDAO {
    /**
     * return the location object from the backend
     * @param code
     * @return
     */
    public abstract IPatsILTBio find(String userId, String enrlDate, String fromDate, String toDate, String patsJobs);
}