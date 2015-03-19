mainApp.controller('EmpController', function($scope,$http,$window,$location) {
	
	$scope.emp = {
				
				fullname: function()
				{
					var myObject = $scope.emp;
					return myObject.fname+" "+myObject.lname;
				},
				Address: function()
				{
					var myObject = $scope.emp;
					return myObject.HouseNo+" "+myObject.Locality+" "+myObject.State+" "+myObject.Country;
				}
	};
	$scope.reset = function() {
			$scope.emp.fname = "";
	        $scope.emp.lname = "";
	        $scope.emp.Locality = "";
	        $scope.emp.State = "";
	        $scope.emp.Country = "";
	        $scope.emp.dob = "";
	        $scope.emp.phno = "";
	        $scope.emp.HouseNo = "";
	        $scope.emp.email = "";
	        $scope.emp.password="";
	}
	
	$scope.isInvalid = function(field){
		
		return $scope.EmpForm[field].$invalid && $scope.EmpForm[field].$dirty;
	  };
	  
	$scope.login =function(){
		
		window.location.href ='./JS1.jsp';
	};
	  
	$scope.error = function(field){
			
		  return $scope.EmpForm[field].$error.required;
		  };
		  

		  $scope.submit = function () {

			  var fname = $scope.emp.fname;
			  var lname = $scope.emp.lname;
			  var address = $scope.emp.Address();
			  var dob = $scope.emp.dob;
			  var email = $scope.emp.email;
			  var pass = $scope.emp.password;
			  var phno = $scope.emp.phno;
			  /*var data = $.param({
            json: JSON.stringify({fName:fname,lName:lname,Address:address,DOB:dob,Email:email,Pass:pass,Phno:phno})
        });*/

			  var data = $.param({fName:fname,lName:lname,Address:address,DOB:dob,Email:email,Pass:pass,Phno:phno});

			  $http({
				  url: './insert',
				  method: 'POST',
				  headers: {
					  'Content-Type': 'application/x-www-form-urlencoded'},
					  data: data,
			  })
			  .success(function (data, status, headers, config) 
				{

				  alert(data);

				  if($.trim(data)=="Employee Added Successfully")
				  {
					  var destinationUrl= './JS1.jsp'
					  alert("Redirecting....")
					  $window.location.href = destinationUrl;
				  }

					  })
					  .error(function (data, status, headers, config) { 
						  alert(data);
					  });

		  };
		    
});