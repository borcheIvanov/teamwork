angular.module('myApp')
	.controller('ArchiveController', function($scope, events, date, logged, Pagination){
		$scope.init = function(){
			$scope.events = [];
			
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
		
		$scope.pagesSec = function(){
		
			$scope.paginationSec = Pagination.getNew($scope.pagesPer);
			$scope.paginationSec.numPages = Math.ceil($scope.events.length / $scope.paginationSec.perPage);
			
		};
		
		
		$scope.init();
	});