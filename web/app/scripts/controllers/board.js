'use strict';

angular.module('ctrl.board', [])
	.controller('board-ctrl', function ($scope, $http, $location, security) {


		//security.isAuthenticated();


		if (!$scope.auth) {
			$location.path('/login')
		}

		

	});