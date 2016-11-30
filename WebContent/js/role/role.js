var vm = angular.module('roleApp',[]);

vm.controller('roleList',['$scope','$http',function($scope,$http){
	
	$scope.role=true;
	
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
		if(result.status==0){
			$scope.username=username;
			$scope.dataList=result.data || {};
		}else{
			localStorage.removeItem("username");
		    localStorage.removeItem("password");
		    window.location.href="./login.html";
		}
			
	}).error(function(result){
			
	});
	
	$http({
		method:'POST',
		url:'/DibikeManagement/manage/role.do',
		dataType:'json',
	}).success(function(result){
		
		$scope.jobList=result.data || {};
		
	}).error(function(result){
		
	});
	
	
	$scope.choose=function(arg,index){
		$http({
			method:'POST',
			params:{
				"manageID":arg
	        },
			url:'/DibikeManagement/manage/updateRole.do',
			dataType:'json',
			
		}).success(function(result){
			$scope.jobList[index].role_name=result.data;
			
		}).error(function(result){
			
		});
		
	}
	
	$scope.loginout=function(){
		localStorage.removeItem("username");
        localStorage.removeItem("password");
    	window.location.href="./login.html";
	}
	
}]);