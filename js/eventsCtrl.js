angular.module('event', [])
.controller('eventsCtrl', function($scope, get, post, logged, $rootScope){
	
	$scope.init = function(){
		$scope.events = [];
		$scope.users = [];
		$scope.selection = [];
		
		$scope.eventName = '';
		$scope.eventDate = '';
		$scope.eventBudget = '';
		$scope.clicked = true;
		
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
			$rootScope.newId = $scope.events[$scope.events.length-1].id + 1;
			
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
		
				for(i = 0; i < $scope.selection.length; i++){
					var temp2 = {
						'events_id': {'id': $rootScope.newId},
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
	
	$scope.dateFunc = function(date){
		var temp = new Date(date).toUTCString().split(' ');
		temp = temp[1] + ' ' + temp[2] + ' ' + temp[3]; 
		return temp;
	};
	
	$scope.select = function(index){
		$scope.selection.push($scope.users[index]);
		for(i = 0; i < $scope.users.length; i++){
			if(i === index){
				for(j = i; j < $scope.users.length-1; j++ ){
					$scope.users[j] = $scope.users[j+1];
				}
			}
		}
		$scope.users.length = $scope.users.length - 1; 
		console.log(index);
	};
	
	$scope.deselect = function(index){
		console.log(index);
		$scope.users.push($scope.selection[index]);
		for(i = 0; i < $scope.selection.length; i++){
			if(i === index){
				for(j = i; j < $scope.selection.length-1; j++ ){
					$scope.selection[j] = $scope.selection[j+1];
				}
			}
			console.log($scope.selection);
		}
		$scope.users.length = $scope.users.length - 1; 
	};

	$scope.eventPanel = function(id){
		get.eventPan(id)
		.then(function(res){
			$scope.panel = res;
			$scope.panel[0].events_id.createdDate = $scope.dateFunc($scope.panel[0].events_id.createdDate);
			$scope.panel[0].events_id.expirationDate = $scope.dateFunc($scope.panel[0].events_id.expirationDate);
			console.log($scope.panel);
		})
		.then(function(){
			
		})
		
	};
	
	$scope.showUsers = function(){
		if($scope.clicked === true){
			$scope.clicked = false;
		}else{
			$scope.clicked = true;
		}
	};
	
	
	
	$scope.init();
})