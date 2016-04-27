angular.module('myApp')
.controller('EventsController', function($scope, user, logged, date, empEvent, events, Pagination){
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
	
	$scope.createEvent = function(){
		
		var temp3 = [];
		var temp2 = {};
		var temp = {
			'name' : $scope.eventName,
			'budget': $scope.eventBudget,
			'expirationDate': $scope.eventDate,
			'createdBy': logged.username
			
		}
		
		document.body.style.cursor = 'progress';
		
			events.postEvent(temp)
			.then(function(){
				events.getEvents()
				.then(function(res){
					var lastId = res;
					var newId = lastId[lastId.length-1].id;
					var eveBug = lastId[lastId.length-1].budget;
					
					console.log($scope.selection);
					for(i = 0; i < $scope.selection.length; i++){
						var temp2 = {
							'events_id': {'id': newId, 'budget':eveBug},
							'hosting_id': {'id': logged.id},
							'invited_id': {'id': $scope.selection[i].id}
						}
						temp3.push(temp2);
						console.log(temp3);
					}
						empEvent.postArray(temp3)
						.then(function(){
							console.log('posted');
							
						})
						.then(function(){
							
						})
					
					document.body.style.cursor = 'auto';
					$scope.init();
				})
				.then(function(){
					
				})
			})
			.then(function(){
				
			});		
	};

	
	$scope.getEventhost = function(){
		empEvent.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			
			$scope.eventhost = date.empEventDate($scope.eventhost);
			
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
			$scope.panel.events_id.createdDate = date.oneByOne($scope.panel.events_id.createdDate);
			$scope.panel.events_id.expirationDate = date.oneByOne($scope.panel.events_id.expirationDate);
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