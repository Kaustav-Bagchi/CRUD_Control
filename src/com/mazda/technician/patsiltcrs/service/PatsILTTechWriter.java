/*
 * Created on May 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mazda.technician.patsiltcrs.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import org.apache.log4j.Logger;

import com.mazdausa.common.log.EMDCSLogger;


/**
 * @author JTodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatsILTTechWriter {
	
	private String patsILTFile = null;
	
	private static Logger log = EMDCSLogger.getLogger(PatsILTTechWriter.class);
	
	public void writePatsILTTechExtract(Enumeration userEnum){

	    FileWriter writer = null;
	    ObjectOutputStream out = null;

	    try {
	     out = new ObjectOutputStream(new FileOutputStream("PatsILTTech.txt"));
	     writer = new FileWriter("PatsILTTech.txt");
	     log.debug("Pats ILT Tech Extract File Opened");

	     while (userEnum.hasMoreElements()) {
	        String techId       = (String)userEnum.nextElement();
	        log.debug(techId);

	        writer.write(techId);
	        writer.write("\r");
	        writer.write("\n");
	      }
	    }
	    catch (FileNotFoundException e) {
	        log.debug("Pats ILT Tech Extract File Not Found " + e.getMessage());
	    }
	    catch (IOException e) {
	      log.debug("Pats ILT Tech Extract File IO Error " + e.getMessage());
	      e.printStackTrace();
	    }finally{
	        try{
	          writer.close();
	        }catch (Exception e){
	          log.debug("Exception Closing Pats ILT Tech Extract File: ");
	        }
	     }
	  }
	
	 public String getPatsILTFile() {
		if (patsILTFile == null) {
		  return "";
		}
		else {
		  return this.patsILTFile;
		}
	  }
	 
	 public void setPatsILTFile(String patsILTFile) {
		this.patsILTFile = patsILTFile;
	  }

}
