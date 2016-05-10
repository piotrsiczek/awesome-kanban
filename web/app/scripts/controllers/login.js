'use strict';

angular.module('ctrl.login', [])
	.controller('login-ctrl', function ($scope, $http, $location, security, $httpParamSerializer) {

		$scope.login = function() {

			var basic = security.login("kanban_web", "wanban_web_1234");
			console.log(basic);

			$scope.auth = true;
			var token = '50b597df-f4be-4d2d-a15f-92aa68835e17';
			$http.defaults.headers.common['Authorization'] = 'Bearer ' + token; // jshint ignore:line
			$location.path("/#/board");

			$http.get('http://localhost:8089/kanban/user/data').success(function(data) {
					$scope.user = data;
				}).error(function(data) {
					console.log(data);
				});

			//$http.defaults.headers.common['Authorization'] = 'Basic ' + basic; // jshint ignore:line

			//$scope.data = {grant_type:"password", username: "", password: "", client_id: "kanban_web"};
			//
			//var req = {
			//	method: 'POST',
			//	url: "http://localhost:8089/kanban/oauth/token",
			//	headers: {
			//		"Authorization": "Basic " + basic,
			//		"Content-type": "application/x-www-form-urlencoded; charset=utf-8"
			//	},
			//	data: $httpParamSerializer($scope.data)
			//};
			//$http(req).then(function(data){
			//	$http.defaults.headers.common.Authorization= 'Bearer ' + data.data.access_token;
			//	$cookies.put("access_token", data.data.access_token);
			//	window.location.href="index";
			//});

			//$http.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
			//
			//$scope.data = {grant_type:"password", username: "admin", password: "admin", client_id: "kanban_web"};
			//$scope.encoded = btoa("kanban_web:wanban_web_1234");
			//
			//
			//var data = "username=admin&password=admin&grant_type=password&scope=read%20write&" +
			//	"client_secret=wanban_web_1234&client_id=kanban_web";
			//
			//console.log($scope.encoded);
			//$http.post('http://localhost:8089/kanban/oauth/token',
			//	data,
			//	{'Content-Type': 'application/x-www-form-urlencoded',
			//		'Accept': 'application/json',
			//		'Authorization': "Basic " + $scope.encoded
			//}).success(function(data, status, headers, config) {
			//	console.log(status);
			//}).error(function(data, status, headers, config) {
			//	console.log(data);
			//	console.log(status);
			//});

			//
			//$http.get('http://localhost:8089/kanban/about',
			//	{"headers": {""}}).success(function(data) {
			//	console.log(data);
			//}).error(function(data) {
			//	console.log(data);
			//});




			//$http.get('http://localhost:8089/kanban/about').success(function(data) {
			//		console.log(data);
			//	}).error(function(data) {
			//		console.log(data);
			//	});

		};

	//var url = "http://localhost:8080/";
	//
	//$scope.newsData = [{title: "Title of issue", name: 'Mark Morison', text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus. Phasellus pharetra nulla ac diam. Quisque semper justo at risus. Donec venenatis, turpis vel hendrerit interdum, dui ligula ultricies purus, sed posuere libero dui id orci. Nam congue, pede vitae dapibus aliquet, elit magna vulputate arcu, vel tempus metus leo non est. Etiam sit amet lectus quis est congue mollis. Phasellus congue lacus eget neque. Phasellus ornare, ante vitae consectetuer consequat, purus sapien ultricies dolor, et mollis pede metus eget nisi. Praesent sodales velit quis augue. Cras suscipit, urna at aliquam rhoncus, urna quam viverra nisi, in interdum massa nibh nec erat.', date: '26.06.2015'}];
	//
	//$scope.myPagingFunction = function() {
	//	//var last = $scope.newsData[$scope.newsData.length - 1];
	//	for(var i = 0; i <= 10; i++) {
	//		$scope.newsData.push({title: "Title of issue", name: 'Mark Morison', text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus. Phasellus pharetra nulla ac diam. Quisque semper justo at risus. Donec venenatis, turpis vel hendrerit interdum, dui ligula ultricies purus, sed posuere libero dui id orci. Nam congue, pede vitae dapibus aliquet, elit magna vulputate arcu, vel tempus metus leo non est. Etiam sit amet lectus quis est congue mollis. Phasellus congue lacus eget neque. Phasellus ornare, ante vitae consectetuer consequat, purus sapien ultricies dolor, et mollis pede metus eget nisi. Praesent sodales velit quis augue. Cras suscipit, urna at aliquam rhoncus, urna quam viverra nisi, in interdum massa nibh nec erat.', date: '26.06.2015'});
	//	}
	//}
	//$scope.user = {id: '1234567890', name: 'Mark', surname: 'Morison'};
	//
	//$scope.taskData = [{id: 0, title: 'asdf', text: 'asdf'},
	//	{id: 1, title: 'asdf', text: 'asdf'}];
	//
	//$scope.addTaskVisible = false;
	//$scope.allTasksVisible = true;
	//
	//
	//$http.get(url + 'task').success(function(data, status, headers, config) {
	//	$scope.taskData = data;
	//}).error(function(data, status, headers, config) {
	//	log(data, status);
	//});

});