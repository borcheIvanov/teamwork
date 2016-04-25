angular.module('myApp')
.controller('eventsCtrl', function($scope, get, post, logged, put){
	
	$scope.init = function(){
		$scope.events = [];
		$scope.eventhost = [];
		$scope.eHost = [];
		$scope.eventwhere = [];
		$scope.panel = [];
		
		$scope.getEventwhere();
		$scope.getEventhost();
		
	};
	
	$scope.getEvents = function(){
		get.events()
		.then(function(res){
			$scope.events = res;
			for(i = 0; i < $scope.events.length; i++){
				$scope.events[i].createdDate = get.dateFunc($scope.events[i].createdDate);
				$scope.events[i].expirationDate = get.dateFunc($scope.events[i].expirationDate);
			}
		})
		.then(function(){
			
		})
	};
	
	$scope.getEventhost = function(){
		get.eventhost(logged.id)
		.then(function(res){
			$scope.eventhost = res;
			for(i = 0; i < $scope.eventhost.length; i++){
			$scope.eventhost[i].events_id.createdDate = get.dateFunc($scope.eventhost[i].events_id.createdDate);
			$scope.eventhost[i].events_id.expirationDate = get.dateFunc($scope.eventhost[i].events_id.expirationDate);
			}
			for(i = 0; i < $scope.eventhost.length; i++){
				if($scope.eventhost[i].moneyOWNED !== 0.0){
					$scope.eHost.push($scope.eventhost[i]);
				}
			}
			for(i = 0; i < $scope.eHost.length; i++){
				$scope.eHost[i].moneyOWNED = Math.ceil($scope.eHost[i].moneyOWNED);
			}
		})
		.then(function(){
			
		})
	};
	
	$scope.payMoney = function(id){
		for(i = 0; i < $scope.eHost.length; i++){
			if($scope.eHost[i].id === id){
				$scope.eHost[i].moneyOWNED  = 0.0;
				put.money(id, $scope.eHost[i])
				.then(function(res){
					$scope.getEventhost();
				})
				.then(function(){
					
				})
			}
		}
	};
	
	$scope.getEventwhere = function(){
		get.eventwhere(logged.id)
		.then(function(res){
			$scope.eventwhere = res;
		})
		.then(function(){
			
		})
	};

	
/*	$scope.createEvent = function(){
		
		var temp = {
			'name' : $scope.eventName,
			'budget': $scope.eventBudget,
			'expirationDate': $scope.eventDate,
			'createdBy': logged.username
		}
		var money = ($scope.eventBudget) / ($scope.selection.length);
		
		document.body.style.cursor = 'progress';
		
			post.events(temp)
			.then(function(){
				get.events()
				.then(function(res){
					var lastId = res;
					var newId = lastId[lastId.length-1].id;
					
					console.log($scope.selection);
					for(i = 0; i < $scope.selection.length; i++){
						var temp2 = {
							'events_id': {'id': newId},
							'hosting_id': {'id': logged.id},
							'invited_id': {'id': $scope.selection[i].id},
							'moneyOWNED' : money
						}
						post.empEvent(temp2)
						.then(function(){
							
						})
						.then(function(){
							
						})
					}
					document.body.style.cursor = 'auto';
					$scope.init();
				})
				.then(function(){
					
				})
			})
			.then(function(){
				
			});		
	};
*/
	


	
	$scope.eventPanel = function(id){
		get.eventPan(id)
		.then(function(res){
			$scope.panel = res;
			$scope.panel.events_id.createdDate = get.dateFunc($scope.panel.events_id.createdDate);
			$scope.panel.events_id.expirationDate = get.dateFunc($scope.panel.events_id.expirationDate);
		})
		.then(function(){
			
		})
	};

	
	

	
	
	$scope.init();
})