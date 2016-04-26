angular.module('myApp')
.controller('StatsController', function($scope, empEvent, logged){
	$scope.init = function(){
		$scope.arr2 = [];
		$scope.arr = [];
		
		$scope.moneyTotal = 0.0;
		$scope.moneyPaid = 0.0;
		$scope.moneyRest = 0.0;
		
		$scope.getSth();
		
	};
	
	$scope.getSth = function(){
		empEvent.eventhost(logged.id)
		.then(function(res){
			$scope.arr2 = res;
			for(i = 0; i < $scope.arr2.length; i++){
				if(i === 0){
					$scope.arr.push($scope.arr2[0]);
				}
				if(i !== 0){
					if($scope.arr2[i].events_id.id !== $scope.arr2[i-1].events_id.id){
						$scope.arr.push($scope.arr2[i]);
					}
				}
			}
			
			for(i = 0; i < $scope.arr.length; i++){
				$scope.moneyTotal += $scope.arr[i].events_id.budget;
			}
			for(i = 0; i < $scope.arr2.length; i++){
				$scope.moneyPaid += $scope.arr2[i].moneyOWNED;
			}
			$scope.moneyRest = $scope.moneyTotal - $scope.moneyPaid;
			$scope.printPie();
			
		})
	};
	
	$scope.printPie = function(){
		$scope.data = [
		  {label: "To collect: ", value: $scope.moneyRest, color: "red"}, 
		  {label: "Collected: ", value: $scope.moneyPaid, color: "#00ff00"}
		  
		];

		$scope.gauge_data = [
		  {label: "CPU", value: 75, suffix: "%", color: "steelblue"}
		];
		
		$scope.options = {thickness: 80};
		
	};
	
	
	$scope.init();
})

.controller('statsTwoCtrl', function($scope, empEvent, logged){
	
	$scope.init = function(){
		$scope.numArr = [];
		$scope.userArr = [];
		$scope.numArr2 = [];
		$scope.chartArray = [];
		$scope.chartPrint = [];
		
		$scope.getNumbers();
	};
	
	$scope.getNumbers = function(){
		empEvent.eventhost(logged.id)
		.then(function(res){
			$scope.userArr = res;
			$scope.printChart();
		})
		.then(function(){
			
		})
	};
	
	$scope.printChart = function(){
		
		for(i = 0; i < $scope.userArr.length; i++){
			
			$scope.chartArray.push({'x': $scope.userArr[i].invited_id.name, 'y': [ $scope.userArr[i].moneyOWNED, 500 ]});
		}
		
		$scope.chartArray.forEach(function(n) {
					
						$scope.chartPrint.push(n);
					});
		
		
		$scope.config = {
			title: 'paid/not paid',
			tooltips: true,
			labels: false,
			legend: {
				display: true,
				position: 'right'
			}
		};

		$scope.data = {
				series: ['Paid', 'Owe'],
				data: $scope.chartPrint
			};
	};
	
	
	$scope.init();
})


