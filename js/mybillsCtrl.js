angular.module('mybills', [])
.controller('mybillsCtrl', function($scope, get, logged, put, $rootScope){
	
$scope.init = function(){
	$scope.eventhost = []; //eventi kreirani od host = current user
	$scope.eventwhere = [];
	$scope.eventinv = []; // pokanetite na kilknatiot event
	$scope.new_inv = [];
	$scope.secPanel = [];
	
	$scope.numInv;
	$scope.moneyEach = 0.0;
	$scope.moneyCol = 0.0;
	$scope.moneyReq = 0.0;
	$scope.totalMoney;
	$scope.switched = false;
	$rootScope.id = '';
	
	$scope.getEventhost();
	$scope.getEventwhere();
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
		
		$rootScope.id = id;
		get.eventinv(id)
		.then(function(res){
			$scope.init();
			$scope.eventinv = res;
			$scope.numInv = $scope.eventinv.length;
			$scope.totalMoney = $scope.eventinv[0].events_id.budget;
			for(i = 0; i < $scope.eventinv.length; i++){
				$scope.moneyReq -= $scope.eventinv[i].moneyOWNED;
			}
			$scope.moneyCol = $scope.totalMoney + $scope.moneyReq;
			$scope.moneyEach = $scope.totalMoney / $scope.numInv;
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
				put.money(id, $scope.eventinv[i])
				.then(function(res){
					
				})
				.then(function(){
					
				})
			}
		}
		$scope.moneyCol = $scope.moneyCol + $scope.moneyEach;
		$scope.moneyReq = $scope.moneyReq + $scope.moneyEach;
	};
	
	$scope.invPanel = function(id){
		get.eventId(id)
		.then(function(res){
			$scope.secPanel = res;
			$scope.secPanel.createdDate = $scope.dateFunc($scope.secPanel.createdDate);
			$scope.secPanel.expirationDate = $scope.dateFunc($scope.secPanel.expirationDate);
			
			console.log($scope.totalMoney);
		})
		.then(function(){
			
		})
	}

	
	$scope.init();
});
	