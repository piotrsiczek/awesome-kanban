'use strict';

/**
 * @ngdoc overview
 * @name webApiApp
 * @description
 * # webApiApp
 *
 * Main module of the application.
 */
angular
	.module('webApiApp', [
		'ngAnimate',
		'ngCookies',
		'ngResource',
		'ngRoute',
		'service.security',
		//'service.security',
		'ctrl.main'
	])
	.config(function ($routeProvider) {
		$routeProvider
			.when('/login', {
				templateUrl: 'views/login.html',
				controller: 'login-ctrl'
			})
			.when('/board', {
				templateUrl: 'views/board.html',
				controller: 'board-ctrl'
			})
			.otherwise({
				redirectTo: '/board'
			});
	});
