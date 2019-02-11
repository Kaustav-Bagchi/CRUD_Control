/*
 * Created on May 14, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Enumeration;



import org.apache.log4j.Logger;

import com.mazdausa.common.DAO.JDBCDAOAbstract;
import com.mazda.technician.patsiltcrs.domain.IPatsILTBio;
import com.mazda.technician.patsiltcrs.domain.PatsILTBio;

import com.mazda.technician.patsiltcrs.service.IApplicationConstants;
import com.mazdausa.common.util.ApplicationUtil;
import com.mazda.technician.patsiltcrs.persistence.PatsILTDAO;
import com.mazda.technician.patsiltcrs.persistence.QueryProvider;
import com.mazdausa.common.log.EMDCSLogger;



/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatsILTDAO extends JDBCDAOAbstract implements IApplicationConstants, IPatsILTDAO {
	
	private static Logger log = EMDCSLogger.getLogger(PatsILTDAO.class);


    /**
     * <b>Description:</b>
     * 
     */
    public PatsILTDAO()
    {
        super();

    }
     
    /**
     * return the location object from the backend
     * @param code
     * @return
     */
    public IPatsILTBio find(String courseNum, String enrlDate, String patsJobs, String fromDate, String toDate)
    {
        Connection con = null;
        ResultSet result = null;
        Statement statement = null;
        IPatsILTBio bio = null;
        String nextDate = IApplicationConstants.DEFAULT_DT;
        log.debug("Pats Jobs = " + patsJobs);
        
        try
        {
            log.debug("Check out the connection");
            con = super.getConnection(LOCATION_DS_KEY);
            log.debug("Create the statement");
            statement = con.createStatement();
            log.debug("execute the query");
            if (toDate.equals(IApplicationConstants.DEFAULT_DT)){
               log.debug(QueryProvider.getQueryCurrentDatePlus1(IApplicationConstants.SQL_SCEHMA));
               result = statement.executeQuery(QueryProvider.getQueryCurrentDatePlus1(IApplicationConstants.SQL_SCEHMA));
               
               if (result.next()){
               	  toDate = result.getString("cur_date").substring(0,10);
               	  log.debug("Date Range: " + fromDate + " to " + toDate);
               }
            }
            if (toDate.equals(IApplicationConstants.DEFAULT_DT)){
            	log.debug("Full PATS Query: " + QueryProvider.getQueryTechCourse(courseNum, patsJobs, IApplicationConstants.SQL_SCEHMA));
                result = statement.executeQuery(QueryProvider.getQueryTechCourse(courseNum, patsJobs, IApplicationConstants.SQL_SCEHMA));
             }else{
             	log.debug("Delta PATS Query: " + QueryProvider.getQueryTechCourseDelta(courseNum,patsJobs, IApplicationConstants.SQL_SCEHMA,nextDate, fromDate, toDate));
                result = statement.executeQuery(QueryProvider.getQueryTechCourseDelta(courseNum, patsJobs, IApplicationConstants.SQL_SCEHMA, nextDate, fromDate, toDate));
  
             }
            bio = new PatsILTBio();
            while (result.next()) { 
             bio.addUserList(result.getString("auth_id"));
            }
            
            
        }catch(Exception e)
        {
            
            log.error("Exception when finding the Technician Course Data for Course Number " + courseNum + "  Exception: "+e);
        }
        
        finally
        {
            super.releaseResource(result, statement, con);//Release all resource used
        }
        return bio;
    }

}
