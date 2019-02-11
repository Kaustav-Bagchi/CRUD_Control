<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<TITLE>Emdcs Application Exception</TITLE>

  

<LINK rel="stylesheet" href="css/mazdaMaster.css" type="text/css">
 
<STYLE>
body
   {
      font-family: arial, sans-serif, Verdana;;
	color: teal
      
   }

.errorPageTitle
{
   font-style:italic;
   font-size:30pt;
}

.errorPageBody
{
   padding-top:40px;
   padding-bottom:10px;
}

#errorPageContainer
{
    display:block;
    width:600px;
    padding-left:25px;
    padding-top:10px;
    padding-bottom:10px;
    background-color: #fbfbfe;
}

.errorPageSectionTitle
{
	font-weight:bold;
}

#errorReporting
{
   margin-top:30px;
}

#errorInfoDetail
{
	margin-top:20px;
}

.applicationInfoData
{
	padding-left:20px;

}

.errorPageHelpDesk
{
	display:block;
    background-color: #eaeaea;
    width:500px;
    padding-left:30px;
    padding-top:10px;
    padding-bottom:10px;
   
}

a:link, a:hover, a:visited, a:active
{
   font-size:10pt;
   color:blue;
   text-decoration:none;
}

a:hover
{
   font-size:10pt;
   color:red;
   text-decoration:none
}

</STYLE>

<!-- Server side
<c:choose>
	<c:when test="${not empty pageContext.exception}">
		<c:set var="problemType">JSP Exception</c:set>
		<c:set var="appException" value="${pageContext.exception}"/>
		<c:set var="causeException" value="${appException.cause}"/>
    </c:when>

	<c:when test="${not empty requestScope['javax.servlet.error.exception']}">
		<c:set var="problemType">Servlet Exception</c:set>
		<c:set var="appException" value="${requestScope['javax.servlet.error.exception']}"/>
		<c:set var="causeException" value="${appException.cause}"/>
    </c:when>

	<c:when test="${not empty requestScope['org.apache.struts.action.EXCEPTION']}">
		<c:set var="problemType">Struts Framework Exception</c:set>
		<c:set var="appException" value="${requestScope['org.apache.struts.action.EXCEPTION']}"/>
		<c:set var="causeException" value="${appException.cause}"/>
    </c:when>
    <c:otherwise>
        <c:set var="problemType">Unidentified Application Error</c:set>
   </c:otherwise>
</c:choose>




-->

</HEAD>






<BODY>




<div id="errorPageContainer">

<div class="errorPageTitle">Application Error</div>

<div class="errorPageBody">An unexpected error occurred in the application.
<p>Please contact
<div class="errorPageHelpDesk">
<div><b>Mazda Systems Help Center</b></div>
(800) 421-6507, Mon-Fri 5:00A - 5:00P (Pacific Time)
</div>
for assisstance.

</P>
<BR>or click <a href="${pageContext.request.contextPath}"/index.html>here</a> to return to the application home page.
</div>
<div id="errorReporting">
If you contact the Mazda Systems Help Center to report this incidence, please provide the following information to the Help Center agent:
<div id="errorInfoDetail">
<div class="errorPageSectionTitle">Application Name</div><div class="applicationInfoData">${pageContext.servletContext.servletContextName}&nbsp;</div>
<div class="errorPageSectionTitle">Application URL</div>
	<div class="applicationInfoData">${pageContext.request.requestURL}&nbsp;</div>
<div class="errorPageSectionTitle">Exception type</div>
	<div class="applicationInfoData">${problemType}</div>
<div class="errorPageSectionTitle">Exception message</div>
	<div class="applicationInfoData">${appException}</div>
<div class="errorPageSectionTitle">Cause</div>
	<div class="applicationInfoData">${causeException}</div>


<!-- Additional info

<c:if test="${not empty appException}">
	<c:out value="${appException}"/>
    <c:forEach var="stackItem" items="${appException.stackTrace}">
		<c:out value="${stackItem}"/>
    </c:forEach>
    
</c:if>


<c:if test="${not empty causeException}">
	<c:out value="${appException}"/>
    <c:forEach var="stackItem" items="${causeException.stackTrace}">
		<c:out value="${stackItem}"/>
    </c:forEach>
    
</c:if>

-->

</div>
</div>
</div>
</BODY>

</HTML>
