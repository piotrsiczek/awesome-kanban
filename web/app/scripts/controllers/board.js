'use strict';

angular.module('ctrl.board', [])
	.controller('board-ctrl', function ($scope, $http, $location, security) {

		//console.log($scope.auth);
		//if (!$scope.auth) {
		//	console.log("asdf");
		//	$location.path('/login')
		//} else {

		//var token = '9d601780-da42-4b2f-ad72-6c52728ea084';
		//$http.defaults.headers.common['Authorization'] = 'Bearer ' + token; // jshint ignore:line

		$http.get('http://localhost:8089/kanban/api/board?groupId=571a993f31dac19cc55ce156').success(function(data) {
			console.log(data);
		}).error(function(data) {
			console.log(data);
		});

		

	});