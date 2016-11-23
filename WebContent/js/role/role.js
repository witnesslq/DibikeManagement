var vm = angular.module('roleApp',[]);

vm.controller('roleList',['$scope','$http',function($scope,$http){
	
	$scope.role=true;
	
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