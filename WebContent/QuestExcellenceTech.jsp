<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Mazda's Quest For Excellence - Technicians</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css">
</head>



<script>
    function forwardToTarget(url)
       {
          window.location = url;
       }

</script>


<%@ page import="java.text.DateFormat, com.mazda.technician.patsiltcrs.domain.IPatsILTBio " %>

<%
 Object retObj;
 String techList;
 techList = null;
 IPatsILTBio bio;
 


 retObj = request.getSession().getAttribute("PatsBio");
 if (retObj instanceof IPatsILTBio){ 

     techList = ((IPatsILTBio)retObj).getTechList();
   
}

//try{
//techList = response.getWriter().toString();
//}catch (Exception e){
//}

 	
 	
	

 %>
  
<body>
<div id=MainContent>
<form name="theForm" action="QuestExcellenceSales.jsp">
  <div id="ContentWrapper">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="images/banner.jpg"></td>
      </tr>
      <tr>
        <td align="center" id="Content"><table border="0" cellpadding="0" cellspacing="0" id="Text"></td>
   
    
      <td>Technician User:&nbsp;&nbsp;&nbsp;&nbsp; <%=techList%></td>
    </tr>
  </table>


          <table width="200" border="0" align="center" cellpadding="0" cellspacing="0" id="button-table">
            <tr>
              <td align="center"><label for="button"></label>
                <input type="button" name="cmdClose" class="button" value="Close" onClick="window.close();"></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>


</div>
</body>
</html>
