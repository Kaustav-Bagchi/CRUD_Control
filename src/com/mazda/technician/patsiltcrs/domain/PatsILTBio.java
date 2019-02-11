/*
 * Created on May 14, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.domain;

import java.util.Vector;
import java.util.Enumeration;


import com.mazda.technician.patsiltcrs.domain.EmdcsAppDomainAbstract;
import com.mazda.technician.patsiltcrs.domain.IPatsILTBio;


/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatsILTBio extends EmdcsAppDomainAbstract implements IPatsILTBio{
	
	  private String userId;
	  protected Vector userIdList;
	  private String vendorUrl;
	  private String techList;
	  
	  public String getUserId() {
		if (userId == null) {
		  return "";
		}
		else {
		  return this.userId;
		}
	  }
	  
	  public String getTechList() {
	  	
	  	StringBuffer sb = new StringBuffer("");
	    String techId = null;
	    
	  	if (this.userIdList != null) {
	  	   Enumeration userEnum = this.userIdList.elements();
	  	   while (userEnum.hasMoreElements()){
	    	   techId = (String)userEnum.nextElement();
	    	   sb.append(techId + "|");
	       }
	  	  this.techList = sb.toString();
	    }
	  	return this.techList; 
	  	
	  }
	  
	  public void addUserList(String userId) {
	  	
	  	 if (this.userIdList == null) {
	         this.userIdList = new Vector();
	     }
	     this.userIdList.add(userId);
	     }
	  
	  public Vector getUsrList(){
	  	
        return this.userIdList;
      }
	  
      public Enumeration getUserList(){
      	
	    return this.userIdList.elements();
      }
	  
	   
  
	  
	  public void setUserId(String userId) {
		this.userId = userId;
	  }
	  
	  
	
}
