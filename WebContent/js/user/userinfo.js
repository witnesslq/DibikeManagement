var vm = angular.module('userApp',[]);

vm.controller('userList',['$scope','$http',function($scope,$http){
	
	$scope.userInfo=true;
	$scope.isDisabled = true;
	
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
	
	$scope.changeUser=function(){
		$scope.isDisabled = false;
		angular.element('.change').show();
		
		$scope.submitUser=function(){
			$scope.isDisabled = true;
			var newname=$('.user').val();
			
			$http({
				method:'POST',
				params:{
					"oldName":username,
		            "newName":newname
				},
				url:'/DibikeManagement/manage/updateManage.do',
				dataType:'json',
				
			}).success(function(result){
				alert('修改成功,重新登录');
				localStorage.removeItem("username");
		        localStorage.removeItem("password");
		    	window.location.href="./login.html";
				
				
			}).error(function(result){
				
			});
			
		}
		$scope.cancel=function(){
			$scope.isDisabled = true;
			$scope.username=username;
			angular.element('.change').hide();
		}
		
	}
	
	
	$scope.loginout=function(){
		localStorage.removeItem("username");
        localStorage.removeItem("password");
    	window.location.href="./login.html";
	}
	
}]);