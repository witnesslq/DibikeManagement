var vm = angular.module('passWApp',[]);

vm.controller('passWList',['$scope','$http',function($scope,$http){
	
	$scope.password=true;
	
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
	
	$scope.changePass=function(){
		angular.element('.change').show();
		angular.element('.meta_content').removeClass('hide');
		
		$scope.submitPass=function(){
			$scope.isDisabled = true;
			var pass1=$('.pass1').val();
			var pass2=$('.pass2').val();
			
			if(pass1===pass2){
				$http({
					method:'POST',
					params:{
						"name":username,
						"password":pass2
					},
					url:'/DibikeManagement/manage/updateManagePassword.do',
					dataType:'json',
					
				}).success(function(result){
					alert('修改成功,重新登录');
					localStorage.removeItem("username");
			        localStorage.removeItem("password");
			    	window.location.href="./login.html";
					
					
				}).error(function(result){
					
				});
			}else{
				alert('傻B,2次输入的密码不一致,请重新输入');
				
			}
			
		}
		
		$scope.cancel=function(){
			$('.pass1').val('');
			$('.pass2').val('');
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