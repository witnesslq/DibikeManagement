var vm = angular.module('loginApp',[]);

vm.controller('loginList',['$scope','$http',function($scope,$http){
	
	$scope.login= function (e) {      
        var keycode = window.event ? e.keyCode : e.which;
        if (keycode == 13) {  
            $scope.submit();  
        }  
    }  
	
	$scope.submit=function(){
		var username=$('#username').val();
        var password=$('#password').val();
        
        $http({
    		method:'POST',
    		params:{
    			"username":username,
                "password":password
    		},
    		url : '/DibikeManagement/manage/login.do',
    		
    	}).success(function(result){
    		if(result.status==0){
            	window.localStorage.setItem("username",username);
            	window.localStorage.setItem("password",password);
            	
          		window.location.href='/DibikeManagement/index.html';
            	
            }else{
            	alert('傻B,用户名或密码错误');
            	return false;
            }
    		
    	}).error(function(result){
    		
    	});
	}
	
}]);