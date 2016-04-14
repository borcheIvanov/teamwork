angular.module('mybills', [])
.controller('mybillsCtrl', function($scope, get, logged){
	
$scope.init = function(){
	$scope.eventhost = []; //eventi kreirani od host = current user
	$scope.eventwhere = [];
	$scope.eventinv = []; // pokanetite na kilknatiot event
	$scope.new_inv = [];
	
	$scope.switched = false;

	$scope.getEventhost();
	
};


$scope.getEventhost = function(){
		get.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			for(i = 0; i < $scope.eventhost.length; i++){
			$scope.eventhost[i].events_id.createdDate = $scope.dateFunc($scope.eventhost[i].events_id.createdDate);
			$scope.eventhost[i].events_id.expirationDate = $scope.dateFunc($scope.eventhost[i].events_id.expirationDate);
			}
			for(i = 0; i < $scope.eventhost.length; i++){
				if(i === 0){
					$scope.new_inv.push($scope.eventhost[0]);
				}
				if(i !== 0){
					if($scope.eventhost[i].events_id.id !== $scope.eventhost[i-1].events_id.id){
						$scope.new_inv.push($scope.eventhost[i]);
					}
				}
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
		
		get.eventinv(id)
		.then(function(res){
			$scope.eventinv = res;
			
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
	};
	
	$scope.payMoney = function(id){
		for(i = 0; i < $scope.eventinv.length; i++){
			if($scope.eventinv[i].id === id){
				$scope.eventinv[i].moneyOWNED  = 0.0;
				console.log($scope.eventinv[i]);
			}
		}
		
		
	}

	
	$scope.init();
});
	