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
import javax.sql.DataSource;
//import com.ibm.websphere.advanced.cm.factory.DataSourceFactory; CR Migration
//import com.ibm.websphere.advanced.cm.factory.Attributes; CR Migration
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import com.mazdausa.common.log.EMDCSLogger;

public class DBDataSource {


// Logging object
private static Logger log = EMDCSLogger.getLogger(DBDataSource.class);

/**
* DBDataSource constructor comment.
*/
public DBDataSource() {
	super();
}
/**
* Insert the method's description here.
* Creation date: (8/26/2003 8:10:01 AM)
* 
* @return java.sql.Connection
*/
/*public static void setupDataSource() 
{
	try
	  {
		String dataSourceName = DataSourceDetails.getInstance().getDatasourceName();
		boolean db652switch = "jdbc/merant652".equals(dataSourceName);
		
		//Get the initial context
		javax.naming.Context ctx = new InitialContext();


		Attributes attrs = new Attributes();
		attrs.connTimeout = 30;
		if(db652switch)
			attrs.databaseName = "SERVICE_OP_DATA";
		else
			attrs.databaseName = "MNAOEPTSCSQL";
		if(db652switch)
			attrs.url = "jdbc:sequelink://EPSC65TDB:19996";
		else
			attrs.url = "jdbc:sequelink://MNAOEPTSCSQL:19996";

		

		
		attrs.defaultConnTimeout = 30;
		attrs.orphanTimeout = 120;
		attrs.driver = "com.merant.sequelink.jdbc.SequeLinkDriver";
		attrs.min = 1;
		attrs.max = 3;
		attrs.name = "jdbc/merant";
		attrs.statementCacheSize = 10;

		DataSourceFactory factory = new DataSourceFactory();
		DataSource dataSource = factory.createJDBCDataSource(attrs);

		factory.bindDataSource(ctx, dataSource);
	}
catch(Exception e)
	{
	  e.printStackTrace();
	}
	 
}*/
}
