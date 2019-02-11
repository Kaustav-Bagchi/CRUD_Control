
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM Software Development Platform" />
<title></title>
<link rel="stylesheet" href="css/appdev.css" type="text/css" />
<link rel="stylesheet" href="css/mazdaMaster.css" type="text/css" />


</head>

<body>
<div id="appContainer">
<div id="logo">
<img src="img/mazda_wingleft_logo_sm.gif" width="100" height="84" />
</div>
<div id="mainHeading">
   System information
</div>

<div id="bodyContent">

	<div id="NavBar" style='background-image: url("img/title-bar-back.gif")'>
	
	Welcome ${UserInfo.firstName} ${UserInfo.lastName}
	</div>
	
    <div>
		The user job code is ${UserInfo.primJobCd}
    </div>

	<div id="infoContent" class="form-text">
	This information panel displays. That means you have succesfully setup your local development environment to develop web applications for Mazda.
	<br />
<br />
<span class="sub-header">General guideline</span><br />
<ul>
	<li>The WAR module display name should be the same as the WAR module file name minus the extension.
	<li>The EAR file name should be the same as the WAR module name with the EAR suffix and extension EAR
    <li>The EAR module display name shoule be the same as the EAR module name minus the EAR extension</li>
	
</ul>
<span class="sub-header">Application Comon Framework</span><br />
<br />From a high level view, the common framework offers followng services:<br />
<ul>
<li>ApplicationUtil to access the common properties</li>
	<li>Java mail</li>
	<li>DAO framework</li>
	<li>Interface to security</li>
	<li>LDAP query service</li>
	<li>JMS interface for sending / receiving messages<br />

	</li>
</ul>
<br />
</div>

</div>
</div>


</body>
</html>
