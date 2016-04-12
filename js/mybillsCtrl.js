angular.module('mybills', [])
.controller('mybillsCtrl', function($scope, get, logged){
	
$scope.init = function(){
	$scope.eventhost = [];
	$scope.eventwhere = [];
	$scope.eventinv = [];

	$scope.getEventhost();
	$scope.getEventwhere();
	$scope.getEventinv();
};
console.log(logged.id);
$scope.getEventhost = function(){
		get.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			
		})
		.then(function(){
			
		})
	};
	
	$scope.getEventwhere = function(){
		get.eventwhere(logged.id)
		.then(function(res){
			$scope.eventwhere = res;
			
		})
		.then(function(){
			
		})
	};
	
	$scope.getEventinv = function(){
		get.eventinv(1)
		.then(function(res){
			$scope.eventinv = res;
			
		})
		.then(function(){
			
		})
	};
	
	$scope.init();
});
	