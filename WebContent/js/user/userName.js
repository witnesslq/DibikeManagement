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
		url:'/DibikeManagement/certification/queryNoCheck.do',
		dataType:'json',
	}).success(function(result){
		$scope.queryNoCheck=result.data;
			
	}).error(function(result){
			
	});
	
	$http({
		method:'POST',
		url:'/DibikeManagement/certification/queryPass.do',
		dataType:'json',
	}).success(function(result){
		$scope.queryPass=result.data;
			
	}).error(function(result){
			
	});
	
	$http({
		method:'POST',
		url:'/DibikeManagement/certification/queryNoPass.do',
		dataType:'json',
	}).success(function(result){
		$scope.queryNoPass=result.data;
			
	}).error(function(result){
			
	});
	
	$('.uname_bar').first().show();
	
	$('.verifyList li').on('click',function(){
		var _index=$(this).index();
		$(this).addClass('selected').siblings().removeClass('selected');
		$('.uname_bar').eq(_index).show().siblings('.uname_bar').hide();
		
	});
	
	$scope.pass=function(memberID){
		$http({
			method:'POST',
			params:{
				"memberID":memberID
			},
			url:'/DibikeManagement/certification/checkPass.do',
		}).success(function(result){
			
				
		}).error(function(result){
				
		});
	}
	
	$scope.nopass=function(memberID){
		$http({
			method:'POST',
			params:{
				"memberID":memberID
			},
			url:'/DibikeManagement/certification/checkNoPass.do',
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