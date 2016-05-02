'use strict';

angular.module('ctrl.board', [])
	.controller('board-ctrl', function ($scope, $http, $location, security) {


		//security.isAuthenticated();

		console.log($scope.auth);
		if (!$scope.auth) {
			$location.path('/login')
		}

		

	});