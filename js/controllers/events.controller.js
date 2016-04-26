angular.module('myApp')
.controller('EventsController', function($scope, user, logged, empEvent, date, Pagination){
	$scope.init = function(){
		$scope.eventhost = [];
		$scope.myEvents = [];
		$scope.selection = [];
		$scope.users = [];
		$scope.panel = [];
		
		$scope.id = '';
		$scope.error = '';
		$scope.eventName = '';
		$scope.eventDate = '';
		$scope.eventBudget = '';
		$scope.clicked = true;
		
		$scope.getEventhost();
		$scope.getUsers();
	};
	
	// -------------------------------------
	
	$scope.getUsers = function(){
		user.getUsers()
		.then(function(res){
			$scope.users = res;
		})
		.then(function(){
			
		})
	};
	
	$scope.getEventhost = function(){
		empEvent.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			for(i = 0; i < $scope.eventhost.length; i++){
			$scope.eventhost[i].events_id.createdDate = date.Func($scope.eventhost[i].events_id.createdDate);
			$scope.eventhost[i].events_id.expirationDate = date.Func($scope.eventhost[i].events_id.expirationDate);
			}
			
			for(i = 0; i < $scope.eventhost.length; i++){
				if(i === 0){
					$scope.myEvents.push($scope.eventhost[0]);
				}
				if(i !== 0){
					if($scope.eventhost[i].events_id.id !== $scope.eventhost[i-1].events_id.id){
						$scope.myEvents.push($scope.eventhost[i]);
					}
				}
			} 
			$scope.pages();
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
	};
	
	$scope.deselect = function(index){
		
		$scope.users.push($scope.selection[index]);
		for(i = 0; i < $scope.selection.length; i++){
			if(i === index){
				for(j = i; j < $scope.selection.length-1; j++ ){
					$scope.selection[j] = $scope.selection[j+1];
				}
			}
			
		}
		$scope.selection.length = $scope.selection.length - 1; 
	};
	
	$scope.eventPanel = function(id){
		empEvent.eventPan(id)
		.then(function(res){
			$scope.panel = res;
			$scope.panel.events_id.createdDate = date.Func($scope.panel.events_id.createdDate);
			$scope.panel.events_id.expirationDate = date.Func($scope.panel.events_id.expirationDate);
			$scope.getEventinv($scope.panel.events_id.id);
		})
		.then(function(){
			
		})
	};
	
	$scope.getEventinv = function(id){
		
		empEvent.eventinv(id)
		.then(function(res){
			$scope.init();
			$scope.eventinv = res;
			console.log($scope.eventinv);
			
		})
		.then(function(){
			
		})
	};
	
	$scope.pages = function(){
		
		$scope.pagination = Pagination.getNew(3);
		$scope.pagination.numPages = Math.ceil($scope.myEvents.length / $scope.pagination.perPage);
		
	};
	
	
	$scope.init();
});