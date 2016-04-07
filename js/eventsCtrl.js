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
		var money = ($scope.eventBudget) / ($scope.selection.length);
 		var newId;
		//console.log($scope.selection);
		newId = $scope.events[$scope.events.length-1].id + 1;
		
		for(i = 0; i < $scope.selection.length; i++){
			var temp2 = {
				'events_id': newId,
				'hosting_id': '7',
				'invited_id': $scope.selection[i]
			}
			post.empEvent(temp2)
			.then(function(){
				
			})
			.then(function(){
				
			})
		}
		$scope.postEvents(temp);
		
		
	};
	
	$scope.postEvents = function(temp){
		
		post.events(temp)
		.then(function(){
			$scope.init();
		})
		.then(function(){
			
		});
	};
	
	$scope.postEmpEvent = function(empEvent){
		
		post.empEvent(empEvent)
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