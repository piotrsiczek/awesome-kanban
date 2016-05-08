angular.module('ctrl.main', ['ctrl.login',
								'ctrl.board'])
	.controller('main-ctrl', function ($scope) {
		$scope.auth = false;
	});
