angular.module('log',[])
.controller('logCtrl', function($scope, get){
	$scope.init = function(){
		$scope.error = '';
		$scope.userName = '';
		$scope.password = '';
	};
	
	$scope.access = function(){
		get.login($scope.userName, $scope.password)
		.then(function(res){
			console.log(res);
		})
		.then(function(){
			
		})
	};
	
	$scope.init();
})