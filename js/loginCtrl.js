angular.module('log',[])
.controller('logCtrl', function($scope, get, logged, $state){
	$scope.init = function(){
		$scope.error = '';
		$scope.userName = '';
		$scope.password = '';
		
		
	};
	
	$scope.access = function(){
		
		get.login($scope.userName, $scope.password)
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
	
	
	
	$scope.init();
})