/*
 * Created on Mar 17, 2010
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
public class QueryProvider {
	 
	
	public static String getQueryTechCourse(String courseNum, String patsJobs, String sqlSchema) {
	  	return "select distinct ltrim(rtrim(auth.auth_id)) as auth_id  from " + sqlSchema + ".tblTasAuth auth , " + sqlSchema + ".tblTasEnrollment enrl, " +
		    sqlSchema + ".tblTasEmployee emp, " + sqlSchema + ".tblTasEmployeeJobCode job " + 
	  		"where auth.employee_id  =  enrl.employee_id  and auth.employee_id =  emp.employee_id  and auth.employee_id =  job.employee_id " +
			"  and emp.employment_status  = 'A'   and enrl.course_number in " + courseNum + " and enrl.enrollment_status = 'A' " +
			"  and job.job_code  in " + patsJobs + " and job.primary_secondary_code = 'P'  and job.termination_date is null   " + 
			" order by 1  ";
	    } 
	public static String getQueryTechCourseDelta(String courseNum, String patsJobs, String sqlSchema, String enrlDate, String fromDate, String toDate) {
	  	return "select distinct ltrim(rtrim(auth.auth_id)) as auth_id  from " + sqlSchema + ".tblTasAuth auth , " + sqlSchema + ".tblTasEnrollment enrl, " +
		    sqlSchema + ".tblTasEmployee emp, " + sqlSchema + ".tblTasEmployeeJobCode job " + 
	  		"where auth.employee_id  =  enrl.employee_id  and auth.employee_id =  emp.employee_id  and auth.employee_id =  job.employee_id " +
			"  and emp.employment_status  = 'A'   and enrl.course_number in " + courseNum + " and enrl.enrollment_status = 'A' " +
			"  and job.job_code  in " + patsJobs + "  and job.primary_secondary_code = 'P'  and job.termination_date is null   " + 
			"  and enrl.updated_date between '" + fromDate + "' and '" + toDate + "' " +
			" order by 1  ";
	  	
	    } 
	public static String getQueryPATSNextDate(String sqlSchema) {
	  	return "select substring(long_description,1,23) as pats_date  from " + sqlSchema + ".tblTasCode  " + 
	  		"where code_group = 'PATSDate' and code_id = 'patsDate1' AND ok_to_use='Y'  ";
	    } 
	
	public static String getQueryCurrentDate(String sqlSchema) {
	  	return "select getdate() as cur_date  "; 
	} 
	
	public static String getQueryCurrentDatePlus1(String sqlSchema) {
	  	return "select getdate() + 1 as cur_date  "; 
	} 
}
