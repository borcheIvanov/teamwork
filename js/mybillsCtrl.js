angular.module('myApp')
.controller('mybillsCtrl', function($scope, get, logged, Pagination){
	
$scope.init = function(){
	$scope.eventhost = []; //eventi kreirani od host = current user
	$scope.eventwhere = [];
	$scope.userId = [];
	$scope.tempArr = [];
	$scope.initArr = [];

	$scope.label = 'Displaying all bills.';
	
	$scope.getEventhost();
	$scope.getEventwhere();
	
};

	$scope.getEventhost = function(){
		get.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			for(i = 0; i < $scope.eventhost.length; i++){
				$scope.eventhost[i].events_id.createdDate = get.dateFunc($scope.eventhost[i].events_id.createdDate);
				$scope.eventhost[i].events_id.expirationDate = get.dateFunc($scope.eventhost[i].events_id.expirationDate);
			}
			$scope.initArr = $scope.eventhost;
			$scope.pages();
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
	
	$scope.invPanel = function(id){
		get.eventId(id)
		.then(function(res){
			$scope.secPanel = res;
			$scope.secPanel.createdDate = get.dateFunc($scope.secPanel.createdDate);
			$scope.secPanel.expirationDate = get.dateFunc($scope.secPanel.expirationDate);
			
		})
		.then(function(){
			
		})
	};

	$scope.info = function(id){
		get.eventPan(id)
		.then(function(res){
			$scope.userId = res;
			$scope.userId.events_id.createdDate = get.dateFunc($scope.userId.events_id.createdDate);
			$scope.userId.events_id.expirationDate = get.dateFunc($scope.userId.events_id.expirationDate);
		})
		.then(function(){
			
		})
	};
	
	$scope.filterFunc = function(condition){
		
		if(condition === 'notPaid'){
			$scope.tempArr = $scope.initArr;
			$scope.eventhost=[];
			for(i = 0; i < $scope.tempArr.length; i++){
				if($scope.tempArr[i].moneyOWNED !== 0.0){
					$scope.eventhost.push($scope.tempArr[i]);
				}
			}
		}else if(condition === 'paid'){
			$scope.tempArr = $scope.initArr;
			$scope.eventhost=[];
			for(i = 0; i < $scope.tempArr.length; i++){
				if($scope.tempArr[i].moneyOWNED === 0.0){
					$scope.eventhost.push($scope.tempArr[i]);
				}
			}
		}else{
			$scope.eventhost = $scope.initArr;
		}
		$scope.label = 'Displaying ' + condition + ' bills.';
		$scope.pages();
	};

	$scope.pages = function(){
		
		$scope.pagination = Pagination.getNew($scope.pagesPer);
		$scope.pagination.numPages = Math.ceil($scope.eventhost.length / $scope.pagination.perPage);
		
	};
	
	$scope.init();
});
	