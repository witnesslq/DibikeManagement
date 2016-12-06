var vm = angular.module('bikeApp',[]);

vm.controller('bikeList',['$scope','$http',function($scope,$http){
	
	$scope.bikeManage=true;
	$scope.bikes=['自行车编号','图片','问题','描述','状态']
	
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
		url:'/DibikeManagement/bikeFault/queryNoCheck.do',
		dataType:'json',
	}).success(function(result){
		$scope.NoCheck=result.data || {};
	}).error(function(result){
			
	});
	
	
	
	$http({
		method:'POST',
		url:'/DibikeManagement/bikeFault/queryPass.do',
		dataType:'json',
	}).success(function(result){
		$scope.queryPass=result.data || {};
		
	}).error(function(result){
		
		
	});
	
	$http({
		method:'POST',
		url:'/DibikeManagement/bikeFault/queryNoPass.do',
		dataType:'json',
	}).success(function(result){
		
		$scope.queryNoPass=result.data || {};
		
		
	}).error(function(result){
		
	});
	
	$('.verifybox').first().show();
	
	$('.verifyList li').on('click',function(){
		var _index=$(this).index();
		$(this).addClass('selected').siblings().removeClass('selected');
		$('.verifybox').eq(_index).show().siblings('.verifybox').hide();
		
	});
	
	$scope.loginout=function(){
		localStorage.removeItem("username");
        localStorage.removeItem("password");
    	window.location.href="./login.html";
	}
	
}]);