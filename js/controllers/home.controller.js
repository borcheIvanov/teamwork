angular.module('myApp')
.controller('HomeController', function($scope, empEvent, date, logged, Pagination){
	
	$scope.init = function(){
		$scope.events = [];
		$scope.eventhost = [];
		$scope.eHost = [];
		$scope.eventwhere = [];
		$scope.panel = [];
		
		$scope.getEventwhere();
		$scope.getEventhost();
		document.body.style.cursor = "auto";
		
	};
	
	$scope.getEventhost = function(){
		empEvent.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			
			$scope.eventhost = date.empEventDate($scope.eventhost);
			
			for(i = 0; i < $scope.eventhost.length; i++){
				if($scope.eventhost[i].moneyOWNED !== 0.0){
					$scope.eHost.push($scope.eventhost[i]);
				}
			}
			for(i = 0; i < $scope.eHost.length; i++){
				$scope.eHost[i].moneyOWNED = Math.ceil($scope.eHost[i].moneyOWNED);
			}
			$scope.pages();
		})
		.then(function(){
			
		})
	};
	
	$scope.getEventwhere = function(){
		empEvent.eventwhere(logged.id)
		.then(function(res){
			$scope.eventwhere = res;
			$scope.eventwhere = date.empEventDate($scope.eventwhere);
			$scope.pagesSec();
		})
		.then(function(){
			
		})
	};
	
	$scope.eventPanel = function(id){
		empEvent.eventPan(id)
		.then(function(res){
			$scope.panel = res;
			$scope.panel.events_id.createdDate = date.oneByOne($scope.panel.events_id.createdDate);
			$scope.panel.events_id.expirationDate = date.oneByOne($scope.panel.events_id.expirationDate);
		})
		.then(function(){
			
		})
	};
	
	$scope.sendFlag = function(id){
		document.body.style.cursor = "wait";
		console.log('clicked');
		empEvent.flagNot(id)
		.then(function(){
			console.log('success');
			$scope.init();
		})
		.then(function(){
			
		})
		
	};
	
	$scope.hasPaid = function(id){
		document.body.style.cursor = "wait";
		var temp = {'moneyOWNED':0.0};
		console.log('clicked');
		empEvent.money(id, temp)
		.then(function(res){
			console.log('money 0');
			$scope.init();
		})
		.then(function(){
			
		})
	};
	
	$scope.flagAprove = function(id){
		document.body.style.cursor = "wait";
		console.log('clicked');
		empEvent.flagApr(id)
			.then(function(){
				console.log('success');
				$scope.hasPaid(id);
				
			})
			.then(function(){
				
			})
	};
	
	$scope.pages = function(){
		
		$scope.pagination = Pagination.getNew(4);
		$scope.pagination.numPages = Math.ceil($scope.eventhost.length / $scope.pagination.perPage);
		
	};
	
	$scope.pagesSec = function(){
		
		$scope.paginationSec = Pagination.getNew(4);
		$scope.paginationSec.numPages = Math.ceil($scope.eventwhere.length / $scope.paginationSec.perPage);
		
	};

	
	$scope.init();
})