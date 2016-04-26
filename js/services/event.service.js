angular.module('myApp')
.service('events', function($http, $q){
	var data = this;
	
	var config = {
		headers : {
			'Content-Type': 'application/json'
		}
	};
	
	//////////////////////////////get

	data.getEvents = function(){
		var defer = $q.defer();
		
		$http.get(url + '/api/event')
		.success(function(res){
			data.gg = res;
			defer.resolve(res);
		})
		.error(function(err, status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	data.getEventById = function(id){
		var defer = $q.defer();
		
		$http.get(url + '/api/event/' + id)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err,status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	//////////////////////////////post
	
	data.postEvent = function(evnt){
		var defer = $q.defer();
		
		$http.post(url + '/api/event', evnt, config)
		.success(function(res){
			defer.resolve(res);
			console.log('Event successfuly created!');
		})
		.error(function(err, status){
			defer.reject(err);
			console.log('Error!');
		})
		return defer.promise;
	};
	
	
	
	return data;
});