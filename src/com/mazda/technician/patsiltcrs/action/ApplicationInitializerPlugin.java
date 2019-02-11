package com.mazda.technician.patsiltcrs.action;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;


import com.mazda.technician.patsiltcrs.persistence.ApplicationDevDAOFactory;
import com.mazda.technician.patsiltcrs.service.IPatsILTService;
import com.mazda.technician.patsiltcrs.service.IApplicationConstants;
import com.mazda.technician.patsiltcrs.service.PatsILTServiceLocator;
import com.mazdausa.common.log.EMDCSLogger;

/*
 * Created on Sep 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author mvu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationInitializerPlugin implements PlugIn, IApplicationConstants
{

    private static Logger log = EMDCSLogger.getLogger(ApplicationInitializerPlugin.class);
    
    /**
     * 
     */
    public ApplicationInitializerPlugin()
    {
        super();
    }

    /**
     * Off line cycle handling
    * This method overrides super class
    * @see super class
     */
    public void destroy()
    {
        PatsILTServiceLocator.clearAll();
    }
 
    /**
     * Startup cycle handling
     * Application Initialization Cycle 
     * @see super class
     */
    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException
    {
        String baseUrl = ApplicationInitializerPlugin.class.getClassLoader().getResource(BASE_CONFIG_URL).getPath();
        if(baseUrl != null)
        {
            if(baseUrl.startsWith("/"))
                baseUrl = baseUrl.replaceFirst("/","");
            
            log.debug("Loading application service directory service");
            PatsILTServiceLocator.loadServiceDirectory(baseUrl+CONFIG_SERVICE_DIRECTORY);
            log.debug("Loading application DAO Configuration");
            ApplicationDevDAOFactory.getInstance().initialize(baseUrl+CONFIG_DAO_CONFIG_FILE);
            
        }

    }

}
