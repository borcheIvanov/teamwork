angular.module('mybills', [])
.controller('mybillsCtrl', function($scope, get, logged){
	
$scope.init = function(){
	$scope.eventhost = []; //eventi kreirani od host
	$scope.eventwhere = [];
	$scope.eventinv = []; // pokanetite na kilknatiot event
	
	$scope.switched = false;

	$scope.getEventhost();
	//$scope.getEventwhere();
	$scope.getEventinv();
};


$scope.getEventhost = function(){
		get.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			for(i = 0; i < $scope.eventhost.length; i++){
			$scope.eventhost[i].events_id.createdDate = $scope.dateFunc($scope.eventhost[i].events_id.createdDate);
			$scope.eventhost[i].events_id.expirationDate = $scope.dateFunc($scope.eventhost[i].events_id.expirationDate);
			}
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
	
	$scope.getEventinv = function(id){
		console.log(id);
		get.eventinv(id)
		.then(function(res){
			$scope.eventinv = res;
			console.log($scope.eventinv);
		})
		.then(function(){
			
		})
	};
	
	$scope.dateFunc = function(date){
		var temp = new Date(date).toUTCString().split(' ');
		temp = temp[1] + ' ' + temp[2] + ' ' + temp[3]; 
		return temp;
	};
	
	$scope.switchPanel = function(){
		if($scope.switched === true){
			$scope.switched = false;
		}else{
			$scope.switched = true;
		}
		console.log($scope.eventhost);
	};
	
	$scope.init();
});
	