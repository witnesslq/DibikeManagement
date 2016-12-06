var vm = angular.module('userName',[]);

vm.controller('userList',['$scope','$http',function($scope,$http){
	
	$scope.userName=true;
	$scope.users=['姓名','国籍','图片','操作'];
	
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
		url:'/DibikeManagement/certification/check.do',
		dataType:'json',
	}).success(function(result){
		$scope.checks=result.data;
			
	}).error(function(result){
			
	});
	
	$scope.audit=function(memberID){
		console.log(memberID);
		$http({
			method:'POST',
			params:{
				"memberID":memberID
			},
			url:'/DibikeManagement/certification/check.do',
		}).success(function(result){
			
				
		}).error(function(result){
				
		});
	}
	
	$scope.loginout=function(){
		localStorage.removeItem("username");
        localStorage.removeItem("password");
    	window.location.href="./login.html";
	}
	
}]);