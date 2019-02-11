/*
 * Created on May 14, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.domain;

import java.util.Vector;
import java.util.Enumeration;

/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IPatsILTBio {
	/**
     * <b>Description:</b>
     *
     * @return Returns the certCode.
     */
    public abstract String getUserId();
 
    /**
     * <b>Description</b> 
     *
     * @param certCode The certCode to set.
     */
     
    
	public abstract Vector getUsrList();
	
	public abstract Enumeration getUserList();
	
	public abstract String getTechList();
	
    public abstract void addUserList(String userId);

}
