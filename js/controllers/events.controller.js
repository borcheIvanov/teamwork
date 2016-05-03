angular.module('myApp')
.controller('EventsController', function($scope, user, ceil, logged, date, empEvent, events, Pagination){
	$scope.init = function(){
		$scope.eventhost = [];
		$scope.myEvents = [];
		$scope.selection = [];
		$scope.users = [];
		$scope.panel = [];
		
		$scope.moneyRequired = 0.0;
		
		$scope.tempId = '';
		$scope.error = '';
		$scope.eventName = '';
		$scope.eventDate = '';
		$scope.eventBudget = '';
		$scope.clicked = true;
		$scope.editor = true;
		$scope.definedArr = false;
		
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
			document.body.style.cursor = "wait";
			var temp3 = [];
			var temp2 = {};
			var temp = {
				'name' : $scope.eventName,
				'budget': $scope.eventBudget,
				'expirationDate': $scope.eventDate,
				'createdBy': logged.username
			}
				events.postEvent(temp)
				.then(function(){
					events.getEvents()
					.then(function(res){
						var lastId = res;
						var newId = lastId[lastId.length-1].id;
						var eveBug = lastId[lastId.length-1].budget;
						
						for(i = 0; i < $scope.selection.length; i++){
							var temp2 = {
								'events_id': {'id': newId, 'budget':eveBug},
								'hosting_id': {'id': logged.id},
								'invited_id': {'id': $scope.selection[i].id}
							}
							temp3.push(temp2);
							
						}
							empEvent.postArray(temp3)
							.then(function(){
								console.log('posted');
								document.body.style.cursor = "auto";
								$scope.init();
								
							})
							.then(function(){
								
							})
						
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
					if($scope.eventhost[i].events_id.id !== $scope.eventhost[i-1].events_id.id && $scope.eventhost[i].events_id.isArchived !== true){
						$scope.myEvents.push($scope.eventhost[i]);
					}
				}
			} 
			$scope.myEvents = $scope.myEvents.slice().reverse();
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
		if($scope.selection.length !== 0){
			$scope.definedArr = true;
		}else{
			$scope.definedArr = false;
		}		
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
		if($scope.selection.length !== 0){
			$scope.definedArr = true;
		}else{
			$scope.definedArr = false;
		}
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
		$scope.moneyRequired = 0.0;
		empEvent.eventinv(id)
		.then(function(res){
			$scope.eventinv = res;
			$scope.eventinv = ceil.money($scope.eventinv);
			for(i = 0; i < $scope.eventinv.length; i++){
				$scope.moneyRequired += $scope.eventinv[i].moneyOWNED;
				
			}
		})
		.then(function(){
			
		})
	};
	
	$scope.pages = function(){
		
		$scope.pagination = Pagination.getNew(3);
		$scope.pagination.numPages = Math.ceil($scope.myEvents.length / $scope.pagination.perPage);
		
	};
	
	$scope.hasPaid = function(id){
		
		document.body.style.cursor = "wait";
		var temp = {'moneyOWNED':0.0};
		console.log('clicked');
		empEvent.money(id, temp)
		.then(function(res){
			document.body.style.cursor = "auto";
			$scope.eventPanel(id);
		})
		.then(function(){
			
		})
	};
	
	$scope.moveToClosed = function(){
		console.log('clicked');
		var temp = {"archived" : "true"};
		events.editEvent($scope.tempId, temp)
		.then(function(){
			console.log('moved');
			$scope.init();
		})
		.then(function(){
			
		})
	};
	
	$scope.getId = function(id){
		$scope.tempId = id;
	};
	
	$scope.edit = function(){
		$scope.editor = false;
		document.getElementById('editable').setAttribute('contenteditable', true);
		document.getElementById('editable').style.outline = '1px dotted black';
		
		document.getElementById('editableSec').setAttribute('contenteditable', true);
		document.getElementById('editableSec').style.outline = '1px dotted black';
	};
	
	$scope.save = function(){
		
		var title = document.getElementById('editable').innerHTML;
		var budget = document.getElementById('editableSec').innerHTML;
		
		if(title === '' || budget === ''){
			$scope.error = 'Please enter some values';
		}else{
			$scope.editor = true;
			document.getElementById('editable').setAttribute('contenteditable', false);
			document.getElementById('editable').style.outline = 'none';
			
			document.getElementById('editableSec').setAttribute('contenteditable', false);
			document.getElementById('editableSec').style.outline = 'none';
		
			$scope.panel.events_id.name = title;
			$scope.panel.events_id.budget = budget;
			
			var temp = {"name" : title, "budget" : budget};
			
			events.editEvent($scope.panel.events_id.id, temp)
			.then(function(res){
				console.log('saved');
				$scope.init();
			})
			.then(function(){
				
			})
		}
	};
	
	
	$scope.init();
});