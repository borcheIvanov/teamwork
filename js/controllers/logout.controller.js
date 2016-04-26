angular.module('myApp')
	.controller('LogoutController', function(logged, $cookies, $scope, $state){
		
		logged.username = '';
		logged.id = '';
		$cookies.put('cookie_id', '');
		$cookies.put('cookie_username', '');
		$state.go('login');
		
	});