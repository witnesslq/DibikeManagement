var vm = angular.module('userSApp',[]);

vm.controller('userSList',['$scope','$http',function($scope,$http){
	$scope.userShow=true;
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
	
	var myChart1 = echarts.init(document.getElementById('week'));
	var myChart2 = echarts.init(document.getElementById('month'));
	
	$http({
		method:'POST',
		url:'/DibikeManagement/member/countMember.do',
		dataType:'json',
	}).success(function(result){
	
		option1 = {
			  title: {
			      text: '本周注册量'
			  },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['用户注册量']
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: ['周一','周二','周三','周四','周五','周六','周日']
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'用户注册',
			            type:'line',
			            stack: '总量',
			            //data:result.data[0]
			        	data:[3,5,90,102,68,98,150],
			        	itemStyle : {
							normal : {
							color:'#eb6588',
								
							}
						},
			        },
			    ],
			    backgroundColor:'#109ba3',
			};
		 myChart1.setOption(option1);
		
		 option2 = {
				  title: {
				      text: '本月注册量'
				  },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['用户注册量']
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    toolbox: {
				        feature: {
				            saveAsImage: {}
				        }
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: ['第一周','第二周','第三周','第四周']
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        {
				            name:'用户注册',
				            type:'line',
				            stack: '总量',
				            //data:result.data[1]
				        	data:[3,5,90,102],
				        	itemStyle : {
								normal : {
								color:'#eb6588',
									
								}
							},
				        },
				    ],
				    backgroundColor:'#109ba3',
				};
			 myChart2.setOption(option2);
		 
	}).error(function(result){
		
	});
	
	
	$scope.loginout=function(){
		localStorage.removeItem("username");
        localStorage.removeItem("password");
    	window.location.href="./login.html";
	}
	
}]);