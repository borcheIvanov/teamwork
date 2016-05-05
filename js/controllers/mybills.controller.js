angular.module('myApp')
.controller('MybillsController', function($scope, empEvent, ceil, date, logged, Pagination){
	
$scope.init = function(){
	$scope.eventhost = []; //eventi kreirani od host = current user
	$scope.eventwhere = [];
	$scope.userId = [];
	$scope.tempArr = [];
	$scope.initArr = [];

	$scope.label = 'Displaying all bills.';
	
	$scope.getEventhost();
	
};

	$scope.getEventhost = function(){
		empEvent.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			$scope.eventhost = date.empEventDate($scope.eventhost);
			$scope.eventhost = ceil.money($scope.eventhost);
			$scope.initArr = $scope.eventhost;
			$scope.pages();
		})
		.then(function(){
			
		})
	};

	$scope.info = function(id){
		empEvent.eventPan(id)
		.then(function(res){
			$scope.userId = res;
			$scope.userId.events_id.createdDate = date.oneByOne($scope.userId.events_id.createdDate);
			$scope.userId.events_id.expirationDate = date.oneByOne($scope.userId.events_id.expirationDate);
		})
		.then(function(){
			
		})
	};
	
	$scope.filterFunc = function(condition){
		
		if(condition === 'notPaid'){
			$scope.tempArr = $scope.initArr;
			$scope.eventhost=[];
			for(i = 0; i < $scope.tempArr.length; i++){
				if($scope.tempArr[i].isPayed !== true){
					$scope.eventhost.push($scope.tempArr[i]);
				}
			}
		}else if(condition === 'paid'){
			$scope.tempArr = $scope.initArr;
			$scope.eventhost=[];
			for(i = 0; i < $scope.tempArr.length; i++){
				if($scope.tempArr[i].isPayed === true){
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
	