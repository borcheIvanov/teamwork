angular.module('myApp')
.controller('LoginController', function($scope, logger, logged, $state){
	$scope.init = function(){
		$scope.error = '';
		$scope.userName = '';
		$scope.password = ''
	};
	
	$scope.access = function(){
		logger.login($scope.userName, $scope.password)
		.then(function(res){
			$scope.error = '';
			$state.go('events');
		})
		.then(function(err, status){
			
		});
		
		$scope.error = 'Wrong username or password';
		
		$scope.userName = '';
		$scope.password = '';
		
	};
	
	$scope.enter = function(){
		if(event.which === 13){
			$scope.access();
		}
	};
	
	$scope.init();
})