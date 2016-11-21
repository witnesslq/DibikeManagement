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
		url:'/DibikeManagement/manage/login.do',
		dataType:'json',
		
	}).success(function(result){
		$scope.username=username;
		
		$scope.dataList=result.data;
		
		
	}).error(function(result){
		
	});
	
	$http({
		method:'POST',
		url:'/DibikeManagement/manage/role.do',
		dataType:'json',
	}).success(function(result){
		$scope.jobList=result.data;
		
	}).error(function(result){
		
	});
	
	
	$scope.choose=function(mes,index){
		
		$http({
			method:'POST',
			params:{
				"manageID":mes
	        },
			url:'/DibikeManagement/manage/updateRole.do',
			dataType:'json',
			
		}).success(function(result){
			
			
			
		}).error(function(result){
			
		});
		
	}
	
}]);