var app = angular.module('myApp', ['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	.when('/',{
		templateUrl: 'login.html',
		controller: 'logCtrl'
	})
	.when('/register',{
		templateUrl: 'register.html'
	})
	.otherwise({
		redirectTo: '/'
	});
});

app.controller('logCtrl', function($scope, $location){
	
});