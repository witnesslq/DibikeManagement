var vm = angular.module('homeApp',[]);

vm.controller('Aaa',['$scope','$http',function($scope,$http){
	
	var username=window.localStorage.getItem("username");
	var password=window.localStorage.getItem("password");
	
	$http({
		method:'POST',
		params:{
			"username":username,
            "password": password
		},
		url:'http://localhost:8080/DibikeManagement/manage/login.do',
		dataType:'json',
		
	}).success(function(result){
		
		$scope.username=username;
		$scope.dataList=result.data;
		console.log($scope.dataList);
		
	}).error(function(result){
		
	});
	
	$scope.link=function(){
		
		$http({
			method:'POST',
			url:'http://localhost:8080/DibikeManagement/manage/role.do',
			
		}).success(function(result){
			$scope.jobList=result.data;
			
			
		}).error(function(result){
			
		});
	}
	
	$scope.choose=function(e){
		
		$http({
			method:'POST',
			params:{
				"manageID":e
	           
			},
			url:'http://localhost:8080/DibikeManagement/manage/login.do',
			
		}).success(function(result){
			
			
		}).error(function(result){
			
		});
	}
	
}]);