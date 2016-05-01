'use strict';

angular.module('ctrl.login', [])
	.controller('login-ctrl', function ($scope, $http, $location, security) {

	if ($scope.auth) {
		$location.path("/board")
	}

		$scope.login = function() {

			var basic = security.login("kanban_web", "wanban_web_1234");
			console.log(basic);


			$http.defaults.headers.common['Authorization'] = 'Basic ' + basic; // jshint ignore:line
			//$http.defaults.headers.common['Authorization'] = 'Bearer ccb6437b-0c0c-48c9-bb07-60c1289f21ce'; // jshint ignore:line

			$http.post('http://localhost:8089/kanban/oauth/token',
				{grant_type: 'password',
					username: 'admin',
					password: 'admin'}).success(function(data, status, headers, config) {
				console.log(status);
			}).error(function(data, status, headers, config) {
				console.log(data);
				console.log(status);
			});


			//$http.get('http://localhost:8089/kanban/api/board?groupId=571a993f31dac19cc55ce156', {headers: {Authorization: 'Bearer ccb6437b-0c0c-48c9-bb07-60c1289f21ce'}}).success(function(data) {
			//	console.log(data);
			//}).error(function(data) {
			//	console.log(data);
			//});

			//$http({
			//	method: 'GET',
			//	url: 'http://localhost:8089/kanban/api/board?groupId=571a993f31dac19cc55ce156',
			//	headers: {
			//		'Authorization': 'Basic bb17e2d8-9c78-4a3f-baa5-811762e51773',
			//		'Accept': 'application/json',
			//		'X-Requested-With': 'XMLHttpRequest'
			//	}
			//}).then(function(response) {
			//	if (response.data == 'ok') {
			//		// success
			//	} else {
			//		// failed
			//	}
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