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
			var newname=$('.username').val();
			if(newname==username){
				alert('傻B，修改后的用户名不能和原来的用户名一致');
				
			}else{
				$http({
					method:'POST',
					params:{
						"oldName":username,
			            "newName":newname
					},
					url:'/DibikeManagement/manage/updateManage.do',
					dataType:'json',
					
				}).success(function(result){
					alert('用户名修改成功,重新登录');
					localStorage.removeItem("username");
			        localStorage.removeItem("password");
			    	window.location.href="./login.html";
					
					
				}).error(function(result){
					
				});
			}
			
		}
		$scope.cancel=function(){
			$scope.isDisabled = true;
			$('.username').val(username);
			angular.element('.change').hide();
		}
	}
	
	
	$scope.loginout=function(){
		localStorage.removeItem("username");
        localStorage.removeItem("password");
    	window.location.href="./login.html";
	}
	
}]);