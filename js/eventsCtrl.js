angular.module('event', [])
.controller('eventsCtrl', function($scope, get, post){
	
	$scope.init = function(){
		$scope.events = [];
		$scope.users = [];
		$scope.selection = [];
		
		$scope.eventName = '';
		$scope.eventDate = '';
		$scope.eventBudget = '';
		
		$scope.getEvents();
		$scope.getUsers();
	};
	
	$scope.getEvents = function(){
		get.events()
		.then(function(res){
			$scope.events = res;
		})
		.then(function(){
			
		})
	};
	
	$scope.getUsers = function(){
		get.users()
		.then(function(res){
			$scope.users = res;
		})
		.then(function(){
			
		})
	};
	
	$scope.createEvent = function(){
		var temp = {
			'name' : $scope.eventName,
			'budget': $scope.eventBudget,
			'expirationDate': $scope.eventDate
		}
		
		post.events(temp)
		.then(function(){
			$scope.init();
		})
		.then(function(){
		
		})
	};
	
	$scope.toggleSelection = function(name){
		var idx = $scope.selection.indexOf(name);
		if(idx > -1){
			$scope.selection.splice(idx, 1);
		}
		else{
			$scope.selection.push(name);
		}
	};

	
	
	
	$scope.init();
})