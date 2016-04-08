angular.module('event', [])
.controller('eventsCtrl', function($scope, get, post, logged){
	
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
			for(i = 0; i < $scope.events.length; i++){
				$scope.events[i].createdDate = $scope.dateFunc($scope.events[i].createdDate);
				$scope.events[i].expirationDate = $scope.dateFunc($scope.events[i].expirationDate);
			}
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
		$scope.postEvents(temp);
 		$scope.getEvents();
		var newId = $scope.events[$scope.events.length-1].id + 1;
		
		for(i = 0; i < $scope.selection.length; i++){
			var temp2 = {
				'events_id': {'id': newId},
				'hosting_id': {'id': logged.id},
				'invited_id': {'id': $scope.selection[i]},
				'moneyOWNED' : money
			}
			post.empEvent(temp2)
			.then(function(){
				
			})
			.then(function(){
				
			})
		}
		
		
		
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
	
	$scope.dateFunc = function(date){
		var temp = new Date(date).toUTCString().split(' ');
		temp = temp[1] + ' ' + temp[2] + ' ' + temp[3]; 
		return temp;
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

	$scope.eventPanel = function(id){
		get.eventPan(id)
		.then(function(res){
			$scope.panel = res;
			console.log($scope.panel);
		})
		.then(function(){
			
		})
		
	};
	
	
	
	$scope.init();
})