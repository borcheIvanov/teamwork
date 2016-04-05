angular.module('log',[])
.controller('logCtrl', function($scope, get){
	$scope.init = function(){
		$scope.error = '';
		$scope.email = '';
		$scope.password = '';
	};
	
	$scope.access = function(){
		get.login()
		.then(function(res){
			console.log(res);
		})
		.then(function(err){
			console.log(err);
		})
	};
	
	$scope.init();
})