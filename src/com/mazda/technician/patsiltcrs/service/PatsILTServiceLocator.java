/*
 * Created on May 14, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.service;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.mazdausa.common.DAO.DAOConfigData;
import com.mazda.technician.patsiltcrs.service.PatsILTServiceLocator;
import com.mazdausa.common.exceptions.EmazdaApplicationException;
import com.mazdausa.common.log.EMDCSLogger;

/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatsILTServiceLocator {
	
	private static Map serviceMap = Collections.synchronizedMap(new HashMap());
    private static Logger log = EMDCSLogger.getLogger(PatsILTServiceLocator.class);
    
    
    static class DirectoryLoader extends DefaultHandler
    {
     
        private String interfaceClassName = null;
        private String implementationClassName = null;
        private String configFile;
        
        
        void loadFrom(String configFile)
        {
            this.configFile = configFile;
            try
            {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                log.debug(">>>Loading the configuration from "+configFile);
                saxParser.parse(new File(configFile), this);
            }
            catch(Exception e)
            {
                log.error("Exception encountered when parsing the xml file:" + configFile + ". Exception:" + e);
            }
            
        }
        
        
        /**
          * This method overrides super class
          * 
          * @see super class
          */
        public void startElement(String namespaceURI,
                String lName, // local name
                String qName, // qualified name
                Attributes attrs)
        		throws SAXException
        {
            
            String eName = lName; // element name
            if ("".equals(eName)) eName = qName; // namespaceAware = false

            if (attrs != null) 
            {
	
	            for (int i = 0; i < attrs.getLength(); i++) 
	            {
		            String aName = attrs.getLocalName(i); // Attr name
		            if ("".equals(aName)) 
		                aName = attrs.getQName(i);
		            String attrValue = attrs.getValue(i);
		            log.debug("Now loading attribute--> "+aName);
		            if("interface".equals(aName))
		            {
		                interfaceClassName = attrValue;            	
		            } 
		            else if("implementation".equals(aName))
		            {
		                implementationClassName = attrValue;
		            }
	
		            if(interfaceClassName != null && implementationClassName != null)
		            {
		                log.debug("Loading the service interface: "+interfaceClassName);
		                log.debug("Loading the service implementation name: "+implementationClassName);
		                
		                inject(interfaceClassName, implementationClassName); //Inject into the locator directory
		                interfaceClassName = null;
		                implementationClassName = null;
		            }
		            
	            }
            }  
        }    
            
            /**
             * 
             * @param infName
             * @param implName
             */
            private void inject(String infName, String implName)
            {
                try
                {
                    Class intfClass = Class.forName(infName);
                    Class implClass = Class.forName(implName);
                    log.debug("Loading the inteterface class: "+intfClass.getName());
                    log.debug("Loading the implementation class: "+implClass.getName());
                    
                    
                    serviceMap.put(intfClass, implClass);
                    
                }
                catch(Exception e)
                {
                    log.error("Exception when loading the service from configuration file:"+configFile+ ". Exception"+ e);
                }
                
            }
            
            /**
             * 
             * @param classOrIntfName
             * @return
             * @throws Exception
             */
            private Object instantiate(String classOrIntfName) throws Exception
            {
                return Class.forName(classOrIntfName).newInstance();
                
            }
    }
    
    /**
     * <b>Description: </b>
     *  
     */
    private PatsILTServiceLocator()
    {
        super();

    }
    public static IPatsILTService locate(Class interfaceClass) throws EmazdaApplicationException
    {
        try
        {
	        Object serviceObject = serviceMap.get(interfaceClass);
	        if(serviceObject instanceof Class)
	        {
	        	log.debug("The object from directory is a class type");
	        }
	        
	        
	        Class serviceClsObject = (Class) serviceMap.get(interfaceClass);
	        
	        log.debug("Service class retrieve from directory: "+serviceClsObject.getName());
	        
	        if(IPatsILTService.class.isAssignableFrom(serviceClsObject))
	        {
	        	log.debug("Service class retrieve from directory is derived from IEmdcsAppDevService");
	        }
	        
	        Object objInstance = serviceClsObject.newInstance();
	        
	        log.debug("The instance of type:"+objInstance.getClass().getName());
	        
	        return (IPatsILTService) serviceClsObject.newInstance();
        }
        catch(Exception e)
        {
            throw new EmazdaApplicationException(e);
        }
    }
    
    /**
     * Add the service
     * 
     * @param serviceInterface
     * @param implementation
     */
    public static void add(IPatsILTService serviceInterface, EmdcsAppDevServiceAbstract implementation)
    {
        serviceMap.put(serviceInterface, implementation);
    }
    
    /**
     * Empty the service collection Meant for application initiation only
     *  
     */
    public static void clearAll()
    {
        serviceMap.clear();
    }
    
    /**
     * 
     * @param url
     */
    public static void loadServiceDirectory(String url)
    {
        DirectoryLoader loader = new DirectoryLoader();
        loader.loadFrom(url);
        loader = null; //Release it
    }
    
    public static void dumpContent()
    {
        log.debug("---------------- Content of Service Locator Directory ---");
        for(Iterator iter = serviceMap.keySet().iterator(); iter.hasNext();)
        {
            Class intf = (Class) iter.next();
            Class impl = (Class) serviceMap.get(intf);
            
            log.debug("Interface:"+intf.getName());
            log.debug("Implementation:"+impl.getName());
            
        }
    }
    
    
    

}

