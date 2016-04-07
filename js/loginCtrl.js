angular.module('log',[])
.controller('logCtrl', function($scope, get){
	$scope.init = function(){
		$scope.error = '';
		$scope.userName = '';
		$scope.password = '';
		$scope.temp = 'false';
		
	};
	
	$scope.access = function(){
		
		get.login($scope.userName, $scope.password)
		.then(function(res){
			$scope.error = '';
		})
		.then(function(err, status){
			
		});
		$scope.error = 'Wrong username or password';	
	};
	
	
	
	$scope.init();
})