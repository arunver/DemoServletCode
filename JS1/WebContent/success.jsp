<!DOCTYPE html>
<%@page import="com.employee.EmployeeDetails"%>
<html>
<head>
<title>Employee Details</title>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="MyApp.js"></script>
<script src="EmpController.js"></script> 
<LINK REL="stylesheet" HREF="./MyCss.css" TYPE="text/css">
</head>

<body style="overflow:hidden;" bgcolor="#EFFFE1" topmargin="10%">
<form action="http://localhost:8081/JS1/logout" method="post">
<jsp:useBean id="Employee" class="com.employee.EmployeeDetails" scope="session" />
<%
//allow access only if session exists
String user = Employee.getFname();
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals(user))
    	userName = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) 
    	sessionID = cookie.getValue();
}
out.println("Hello:"+" "+user);
String Role= Employee.getRole();
if(Role.equalsIgnoreCase("User"))
{
	
%> 
<div align="right">
<input type="submit" value="Log Out" id ="btn"> 
</div>
<table class="coupon">
<tr>
<td>FIRST NAME</td><td>LAST NAME</td> <td>ADDRESS</td> <td>DATE OF BIRTH</td> <td>EMAIL</td> <td>PHONE NUMBER</td></tr>
<tr>
<td><jsp:getProperty property="fname" name="Employee"/>
</td>
<td>
<jsp:getProperty property="lname" name="Employee"/>
</td>
<td>
<jsp:getProperty property="address" name="Employee"/> 
 </td>
 <td>
 <jsp:getProperty property="dob" name="Employee"/>
</td>
<td>
<jsp:getProperty property="email" name="Employee"/>
</td>
<td>
<jsp:getProperty property="phno" name="Employee"/>
</td>
 </table>
 </form>
 <%
}
else if(Role.equalsIgnoreCase("Admin"))
{
	
}
}
 %>
</body>
</html>