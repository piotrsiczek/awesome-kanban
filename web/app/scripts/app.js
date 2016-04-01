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
		'ngRoute'
	])
	.config(function ($routeProvider) {
		$routeProvider
			.when('/', {
				templateUrl: 'views/main.html',
				controller: 'MainCtrl',
				controllerAs: 'main'
			})
			.when('/about', {
				templateUrl: 'views/about.html',
				controller: 'AboutCtrl',
				controllerAs: 'about'
			})
			.otherwise({
				redirectTo: '/'
			});
	});
