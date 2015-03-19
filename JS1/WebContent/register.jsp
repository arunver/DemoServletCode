<!DOCTYPE html>
<html>
<head>
<title>Employee Register</title>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="MyApp.js"></script>
<script src="EmpController.js"></script> 
<LINK REL="stylesheet" HREF="./MyCss.css" TYPE="text/css">
</head>

<body style="overflow:hidden;" bgcolor="#EFFFE1">

<div data-ng-app="mainApp" data-ng-controller="EmpController" align="Center" >
<form name="EmpForm" novalidate>
		
		<table class="coupon">
					<tr><td>First Name: </td><td> <input class="textbox" name="fname"  type="text" data-ng-model="emp.fname" required/>
					 <span style="color:red" data-ng-show="isInvalid('fname')"><span data-ng-show="error('fname')"><br>First Name is required.</span>
					   </span>
					</td></tr>
					 <tr><td>Last Name: </td><td> <input class="textbox" name="lname" type="text" data-ng-model="emp.lname" required>
					  <span style="color:red" data-ng-show="isInvalid('lname')"><span data-ng-show="error('lname')"><br>Last Name is required.</span>
					   </span>
					 </td></tr>
					 <tr><td>Password: </td><td> <input class="textbox" name="password" type="password" data-ng-model="emp.password" required>
					  <span style="color:red" data-ng-show="isInvalid('password')"><span data-ng-show="error('password')"><br>Password is required.</span>
					   </span>
					 </td></tr>
					<tr><td>House No.: </td><td> <input class="textbox" name="HouseNo" type="text" data-ng-model="emp.HouseNo" required>
					  <span style="color:red" data-ng-show="isInvalid('HouseNo')"><span data-ng-show="error('HouseNo')"><br>House No. is required.</span>
					   </span>
					</td></tr>
					<tr><td>Locality: </td><td> <input class="textbox" name="Locality" type="text" data-ng-model="emp.Locality" required>
					  <span style="color:red" data-ng-show="isInvalid('Locality')"><span data-ng-show="error('Locality')"><br>Locality is required.</span>
					   </span>
					</td></tr>
					<tr><td>State: </td><td> <input class="textbox" name="State" type="text" data-ng-model="emp.State" required>
					 <span style="color:red" data-ng-show="isInvalid('State')"><span data-ng-show="error('State')"><br>State is required.</span>
					   </span>
					</td></tr>
					<tr><td>Country: </td><td> <input class="textbox" name="Country" type="text" data-ng-model="emp.Country" required>
					 <span style="color:red" data-ng-show="isInvalid('Country')"><span data-ng-show="error('Country')"><br>Country is required.</span>
					   </span>
					</td></tr>
					<tr><td>Phone No.: </td><td> <input class="textbox" name="phno" type="text" data-ng-model="emp.phno" required>
					 <span style="color:red" data-ng-show="isInvalid('phno')"><span data-ng-show="error('phno')"><br>Phone No. is required.</span>
					   </span>
					</td></tr>
					<tr><td>Email: </td><td> <input class="textbox" name="email" type="email" data-ng-model="emp.email" required>
					 <span style="color:red" data-ng-show="isInvalid('email')"><span data-ng-show="error('email')"><br>Email is required.</span>
					   </span>
					</td></tr>
					<tr><td>DOB: </td><td> <input class="textbox" name="dob" type="date" data-ng-model="emp.dob" required>
					 <span style="color:red" data-ng-show="isInvalid('dob')"><span data-ng-show="error('dob')"><br>DOB is required.</span>
					   </span>
					</td></tr>  
					<tr><td>
					<button data-ng-click="reset()" id="btn">Reset</button></td><td>
					<button data-ng-disabled="error('fname') ||
								 error('lname') ||
								  error('Locality')||error('HouseNo') ||error('password') || error('State')||error('Country') || error('phno')||error('email') ||error('dob')" data-ng-click="submit()" id="btn">Submit</button> 
						</td></tr>
		</table>

		<br>
		<input id ="btn" type="Submit" value="Login" data-ng-click="login()">

</form>
</div>


</body>
</html>