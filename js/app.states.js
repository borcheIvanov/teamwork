angular.module('myApp')
.config(function($stateProvider, $urlRouterProvider){
	$stateProvider
	.state('login',{
		url: '/',
		templateUrl: 'login.html',
		controller: 'LoginController'
	})
	.state('register',{
		url:'/register',
		templateUrl: 'register.html',
		controller: 'RegistrationController'
	})
	.state('home', {
		url: '/home',
		templateUrl: 'home.html',
		controller: 'HomeController'
	})
	.state('events', {
		url: '/events',
		templateUrl: 'events.html',
		controller: 'EventsController'
	})
	.state('mybills', {
		url: '/mybills',
		templateUrl: 'mybills.html',
		controller: 'MybillsController'
	})
	.state('stats', {
		url: '/stats',
		templateUrl: 'stats.html',
		controller: 'StatsController'
	})
	.state('archive', {
		url: '/archive',
		templateUrl: 'archive.html',
		controller: 'ArchiveController'
	})
	.state('logout', {
		url:'/logout',
		controller: 'LogoutController'
	});
	$urlRouterProvider.otherwise('/');
})