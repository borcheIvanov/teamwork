angular.module('myApp')
.controller('HomeController', function($scope, empEvent, date, logged){
	
	$scope.init = function(){
		$scope.events = [];
		$scope.eventhost = [];
		$scope.eHost = [];
		$scope.eventwhere = [];
		$scope.panel = [];
		
		$scope.getEventwhere();
		$scope.getEventhost();
		
	};
	
	$scope.getEventhost = function(){
		empEvent.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			for(i = 0; i < $scope.eventhost.length; i++){
			$scope.eventhost[i].events_id.createdDate = date.Func($scope.eventhost[i].events_id.createdDate);
			$scope.eventhost[i].events_id.expirationDate = date.Func($scope.eventhost[i].events_id.expirationDate);
			}
			for(i = 0; i < $scope.eventhost.length; i++){
				if($scope.eventhost[i].moneyOWNED !== 0.0){
					$scope.eHost.push($scope.eventhost[i]);
				}
			}
			for(i = 0; i < $scope.eHost.length; i++){
				$scope.eHost[i].moneyOWNED = Math.ceil($scope.eHost[i].moneyOWNED);
			}
		})
		.then(function(){
			
		})
	};
	
	$scope.payMoney = function(id){
		for(i = 0; i < $scope.eHost.length; i++){
			if($scope.eHost[i].id === id){
				$scope.eHost[i].moneyOWNED  = 0.0;
				put.money(id, $scope.eHost[i])
				.then(function(res){
					$scope.getEventhost();
				})
				.then(function(){
					
				})
			}
		}
	};
	
	$scope.getEventwhere = function(){
		empEvent.eventwhere(logged.id)
		.then(function(res){
			$scope.eventwhere = res;
			$scope.eventwhere[0].events_id.expirationDate = date.Func($scope.eventwhere[0].events_id.expirationDate);
			console.log($scope.eventwhere);
		})
		.then(function(){
			
		})
	};
	
	$scope.eventPanel = function(id){
		empEvent.eventPan(id)
		.then(function(res){
			$scope.panel = res;
			$scope.panel.events_id.createdDate = date.Func($scope.panel.events_id.createdDate);
			$scope.panel.events_id.expirationDate = date.Func($scope.panel.events_id.expirationDate);
		})
		.then(function(){
			
		})
	};

	
	$scope.init();
})