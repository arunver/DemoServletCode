<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<LINK REL="stylesheet" HREF="./MyCss.css" TYPE="text/css">
<script type="text/javascript">
	$(document).ready(function(){
		
		 $('.submit').click(function(){
			var userName = $('#userName').val();
			var password = $('#password').val();
			
			//alert(userName+" "+password);
		if(userName!='' && password!='')
			{
			 /* $.ajax({
				url:"login",
				type:"POST",
				data:{user:userName, pass:password},
				success:function(response)
				{
						alert(response);
					
					if($.trim(response) == "success")
						{
						window.location.href = './success.jsp';
						}
					else
						{
						window.location.href = './JS1.jsp';
						}
				},
				error:function(msg)
				{
					alert(msg);
				}
				
			});  */
			}
		else
			{
				if(userName =='' && password == '')
				{
					$('#userError').html('Please provide username');
					$('#passError').html('Please provide password');
				}
				else if(userName =='' )
				{
					$('#userError').html('Please provide username');
					$('#passError').html('');
				}
				else 
				{
					$('#passError').html('Please provide password');
					$('#userError').html('');
				}
				
				return false;

			}
		
		}); 
		$(".register").click(function() {
			
			var destinationUrl= './register.jsp'
			window.location.href = destinationUrl;
			return false;
		});
	});
	
	/* function validateForm()
	{
		var userName = $('#userName').val();
		var user = document.getElementById("userName").val();
	} */
</script>
</head>
<body bgcolor="#EFFFE1" topmargin="10%">


<form action="http://localhost:8081/JS1/login" name="loginForm" method="post">
<div align="Center"> 
<table class="coupon">
<tr><td colspan="2">
<%
	String passwd = (String)request.getAttribute("passwd");
if(passwd!=null){
%>
<div id="errorHtml" style="color:red;" align="center"><%=passwd %> </div><%} %>
</td>
</tr>
<tr>
<td>Name</td>
<td>
<input class="textbox" type="text" name="Username" id="userName"/>
<div id="userError"></div>
</td>
</tr>
<tr>
<td>Password</td>
<td>
<input class="textbox" type="password" name="password" id="password">
<div id="passError"></div>
</td>
</tr>
<tr>
<td><input type="submit" value="Register" class="register" id="btn"/></td>
<td>
<input type="submit" class="submit" value="Login" align="right" id="btn"/>
</td>
</tr>
</table>
</div>
</form>
</body>
</html>