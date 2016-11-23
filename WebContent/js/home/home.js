var vm = angular.module('homeApp',[]);

vm.controller('homeList',['$scope','$http',function($scope,$http){
	
	var username=window.localStorage.getItem("username");
	var password=window.localStorage.getItem("password");
	
	if(username==null||password==null){
		window.location.href='/DibikeManagement/login.html';
	}else{
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
	}
	
	
	$http({
		method:'POST',
		url:'/DibikeManagement/manage/role.do',
		dataType:'json',
	}).success(function(result){
		
		$scope.jobList=result.data;
		
		console.log($scope.jobList);
		
	}).error(function(result){
		
	});
	
	
	$scope.loginout=function(){
		localStorage.removeItem("username");
        localStorage.removeItem("password");
    	window.location.href="./login.html";
	}
	
}]);