angular.module('myApp')
	.controller('ArchiveController', function($scope, events, date, logged, empEvent, ceil, Pagination){
		$scope.init = function(){
			$scope.events = [];
			$scope.eventinv = [];
			
			$scope.pagesPer = 5;
			
			$scope.moneyRequired = 0.0;
			
			$scope.getClosed();
		};
		
		$scope.getClosed = function(){
			events.getClosed()
			.then(function(res){
				$scope.events = res;
				$scope.events = date.eventDate($scope.events);
				$scope.pagesSec();
				
			})
			.then(function(err){
				
			})
		};
		
		$scope.getEventinv = function(id){
			$scope.moneyRequired = 0.0;
			empEvent.eventinv(id)
			.then(function(res){
				$scope.eventinv = res;
				$scope.eventinv = date.empEventDate($scope.eventinv);
				$scope.eventinv = ceil.money($scope.eventinv);
				for(i = 0; i < $scope.eventinv.length; i++){
					$scope.moneyRequired += $scope.eventinv[i].moneyOWNED;
				}
			})
			.then(function(){
				
			})
		};
		
		$scope.pagesSec = function(){
		
			$scope.pagination = Pagination.getNew($scope.pagesPer);
			$scope.pagination.numPages = Math.ceil($scope.events.length / $scope.pagination.perPage);
			
		};
		
		
		$scope.init();
	});