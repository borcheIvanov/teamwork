angular.module('myApp')
.controller('StatsController', function($scope, empEvent, ceil, logged){
	$scope.init = function(){
		
		var tempArr = [];
		$scope.bills = [];
		$scope.events = [];
		
		
		$scope.paidPercent = 0.0;
		$scope.restPercent = 0.0;
		
		$scope.typeSelector = 'pie';
		
		$scope.moneyTotal = 0.0;
		$scope.moneyPaid = 0.0;
		$scope.moneyRest = 0.0;
		
		$scope.getEvents();
	};
	
	$scope.getEvents = function(){
		empEvent.eventhost(logged.id)
		.then(function(res){
			$scope.bills = res;
			calculate(res);
			
		})
		.then(function(){
			
		})
	};
	
	$scope.selectEvent = function(){
		$scope.moneyPaid = 0.0;
		$scope.moneyRest = 0.0;
		$scope.moneyTotal = 0.0;
		
		if($scope.eventSelector === 'all' || $scope.eventSelector === undefined){
			$scope.init();
		}else{
			for(i = 0; i < $scope.bills.length; i++){
				if($scope.bills[i].events_id.id == $scope.eventSelector){
					$scope.moneyRest += $scope.bills[i].moneyOWNED;
					$scope.moneyTotal = $scope.bills[i].events_id.budget;
				}
			}
			
			$scope.moneyPaid = $scope.moneyTotal - $scope.moneyRest;
			drawPie();
		}
	};
	
	var calculate = function(niza){
		var events = [];
		
		for(i = 0; i < niza.length; i++){
			if(i === 0){
				events.push(niza[0]);
			}
			if(i !== 0){
				if(niza[i].events_id.id !== niza[i-1].events_id.id){
					events.push(niza[i]);
				}
			}
		}
		ceil.money(niza);
		
		for(i = 0; i < niza.length; i++){
			$scope.moneyTotal += niza[i].events_id.budget;
			$scope.moneyRest += niza[i].moneyOWNED;
		}
		$scope.moneyPaid = $scope.moneyTotal - $scope.moneyRest;
		
		$scope.events = events;
		
		drawPie();
		drawChart();
	};
	
	var drawPie = function(){
		
		
		console.log($scope.moneyTotal, $scope.moneyRest, $scope.moneyPaid);
		
		if($scope.moneyTotal !== 0.0){
			$scope.paidPercent = Math.floor(($scope.moneyPaid / $scope.moneyTotal) * 100);
			$scope.restPercent = Math.floor(($scope.moneyRest / $scope.moneyTotal) * 100);
		}else{
			$scope.paidPercent = 0;
			$scope.restPercent = 0;
		}
		
		$scope.config = {
			title: '',
			tooltips: true,
			labels: false,
			legend: {
				display: true,
				position: 'left'
			}
		};
		$scope.data = {
			series: ['Paid', 'Not Paid'],
			data: [{
				x: "Paid",
				y: [$scope.paidPercent],
				tooltip: $scope.paidPercent + "% Paid"
			}, {
				x: "Not Paid",
				y: [$scope.restPercent],
				tooltip: $scope.restPercent + "% Not Paid"
			}]
		};	
	};
	
	var drawChart = function(){
		$scope.configSec = {
			title: 'Users',
			tooltips: true,
			labels: false,
			legend: {
				display: true,
				position: 'right'
			}
		};
		$scope.dataSec = {
			series: ['Paid', 'Not Paid'],
			data: [{
				x: "Paid",
				y: [100,200],
				tooltip: "% Paid"
			}, {
				x: "Not Paid",
				y: [300, 500],
				tooltip: "% Not Paid"
			}]
		};
	};
	
	
	$scope.init();
})


