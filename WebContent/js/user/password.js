var vm = angular.module('passWApp',[]);

vm.controller('passWList',['$scope','$http',function($scope,$http){
	
	$scope.password=true;
	
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
	
	$scope.changePass=function(){
		angular.element('.change').show();
		angular.element('.meta_content').removeClass('hide');
		
		$scope.submitPass=function(){
			$scope.isDisabled = true;
			var pass1=angular.element('.pass1').val();
			var pass2=angular.element('.pass2').val();
			
			if(pass1!==pass2){
				alert('2次输入的密码不一致,请重新输入');
			}else if(pass1===password){
				alert('旧密码不能和新密码一致,请重新输入');
			}else if(pass1.length!=6&&pass2.length!=6){
				alert('密码的长度必须是6位,请重新输入');
			}else{
				$http({
					method:'POST',
					params:{
						"name":username,
						"password":pass2
					},
					url:'/DibikeManagement/manage/updateManagePassword.do',
					dataType:'json',
					
				}).success(function(result){
					alert('密码修改成功,重新登录');
					localStorage.removeItem("username");
			        localStorage.removeItem("password");
			    	window.location.href="./login.html";
					
				}).error(function(result){
					
				});
			}
		}
		
		$scope.cancel=function(){
			angular.element('.pass1').val('');
			angular.element('.pass2').val('');
			angular.element('.change').hide();
			angular.element('.meta_content').addClass('hide');
		}
	}
	
	
	
	$scope.loginout=function(){
		localStorage.removeItem("username");
        localStorage.removeItem("password");
    	window.location.href="./login.html";
	}
	
}]);