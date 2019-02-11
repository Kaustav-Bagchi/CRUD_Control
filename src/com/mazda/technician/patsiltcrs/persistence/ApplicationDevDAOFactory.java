/*
 * Created on Dec 8, 2009
 *
 * <b>Change History:</b>
 * <pre>
 * Copyright 2004 by Mazda North America Operations, Inc.,
 * 7755 Irvine Center Drive Irvine, CA  92623, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Mazda North America Operations, Inc. ("Confidential Information").  
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Mazda North America Operations.
 * </pre>
 *  
 * 
 */
package com.mazda.technician.patsiltcrs.persistence;

import org.apache.log4j.Logger;

import com.mazdausa.common.DAO.DAOFactoryAbstract;
import com.mazdausa.common.DAO.JDBCDAOAbstract;
import com.mazda.technician.patsiltcrs.service.IApplicationConstants;
import com.mazdausa.common.log.EMDCSLogger;

/**
 * @author mvu
 * @version
 * <b>Description:</b>
 * 
 */
public class ApplicationDevDAOFactory extends DAOFactoryAbstract implements IApplicationConstants
{
    private static Logger log = EMDCSLogger.getLogger(ApplicationDevDAOFactory.class);
    private static ApplicationDevDAOFactory instance;
    private String configUrl;
    
    /**
     * <b>Description:</b>
     * 
     */
    private ApplicationDevDAOFactory()
    {
        super();

    }

    /**
     * 
     * @return
     */
    public synchronized static ApplicationDevDAOFactory getInstance()
    {
        if(instance == null)
            instance = new ApplicationDevDAOFactory(); 
        return instance;
    }
    
    /**
     * This method overrides super class
     * @see super class
     */
    protected JDBCDAOAbstract createDAOImplementation(String className) throws Exception
    {
        try
        {
            return (JDBCDAOAbstract)Class.forName(className).newInstance();
        }
        catch(Exception e)
        {
            log.error("Exception when instantiating the class "+className+ " Exception:"+e);
            throw e;
        }
    }

    public void initialize(String url)
    {
        this.configUrl = url;
        super.init(this.configUrl);
    }
    
    /**
     * This method overrides super class
     * @see super class
     */
    public String getConfigResourceURL()
    {
        return configUrl;
    }

    
    /**
     * <b>Description:</b>
     *
     * @return Returns the configUrl.
     */
    public String getConfigUrl()
    {
        return configUrl;
    }
    /**
     * <b>Description</b> 
     *
     * @param configUrl The configUrl to set.
     */
    public void setConfigUrl(String configUrl)
    {
        this.configUrl = configUrl;
    }
    
    /**
     * Reload the configuration.
     * Can be invoked from the action to reload the DAO configuration
     * The DAO config init should be called from the system init eiter in the application context init
     * or the Struts plugin
     *
     */
    public void reloadConfig()
    {
        if(this.configUrl != null)
        {
            super.init(this.configUrl);
        }
    }
    
    
    
}
