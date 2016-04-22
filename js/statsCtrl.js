angular.module('myApp')
.controller('statsCtrl', function($scope, get, logged){
	$scope.init = function(){
		$scope.arr2 = [];
		$scope.arr = [];
		
		$scope.moneyTotal = 0.0;
		$scope.moneyPaid = 0.0;
		$scope.moneyRest = 0.0;
		
		$scope.getSth();
	};
	
	$scope.getSth = function(){
		get.eventhost(logged.id)
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
		  {label: "To collect: "+ $scope.moneyRest, value: $scope.moneyRest, color: "red"}, 
		  {label: "Collected: "+ $scope.moneyPaid, value: $scope.moneyPaid, color: "#00ff00"}
		  
		];

		$scope.gauge_data = [
		  {label: "CPU", value: 75, suffix: "%", color: "steelblue"}
		];
		
		$scope.options = {thickness: 80};
	};
	
	$scope.init();
	
})

.controller('statsTwoCtrl', function($scope){
		
	
	$scope.data = [
	  {label: "12", value: 12.2, color: "red"}, 
	  {label: "45", value: 45, color: "#00ff00"},
	  {label: "10", value: 10, color: "rgb(0, 0, 255)"}
	];

	$scope.gauge_data = [
	  {label: "CPU", value: 75, suffix: "%", color: "steelblue"}
	];
	
	$scope.options = {thickness: 80};
	

})

