angular.module('myApp')
.value('logged', {
	'id': '',
	'username': ''
})

.controller('logoutCtrl', function(logged, $cookies, $scope, $state){
	logged.username = '';
	logged.id = '';
	$cookies.put('cookie_id', '');
	$cookies.put('cookie_username', '');
	$state.go('login');
	
});