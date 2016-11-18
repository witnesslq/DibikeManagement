var vm = angular.module('homeApp',[]);

vm.controller('homeList',['$scope','$http',function($scope,$http){
	
	var username=window.localStorage.getItem("username");
	var password=window.localStorage.getItem("password");
	
	$http({
		method:'POST',
		params:{
			"username":username,
            "password":password
		},
		url:'http://localhost:8080/DibikeManagement/manage/login.do',
		dataType:'json',
		
	}).success(function(result){
		$scope.username=username;
		
		$scope.dataList=result.data;
		
		
	}).error(function(result){
		
	});
	
	$http({
		method:'POST',
		url:'http://localhost:8080/DibikeManagement/manage/role.do',
		dataType:'json',
	}).success(function(result){
		$scope.jobList=result.data;
		
	}).error(function(result){
		
	});
	
	
	$scope.choose=function(mes,name){
		
		$http({
			method:'POST',
			params:{
				"manageID":mes
	        },
			url:'http://localhost:8080/DibikeManagement/manage/updateRole.do',
			dataType:'json',
			
		}).success(function(result){
			
			console.log(result.data);
			console.log(name);
			name=result.data;
			console.log(name);
			
			
			//window.location.reload();
			
		}).error(function(result){
			
		});
		
	}
	
}]);