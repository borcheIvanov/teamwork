angular.module('myApp', ['ui.router', 'log', 'reg', 'getService', 'postService'])

.config(function($stateProvider, $urlRouterProvider){
	$stateProvider
	.state('login',{
		url: '',
		templateUrl: 'login.html',
		controller: 'logCtrl'
	})
	.state('register',{
		url:'/register',
		templateUrl: 'register.html',
		controller: 'regCtrl'
	})
	
});

