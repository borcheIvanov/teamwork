angular.module('postService',[])
.service('post', function($http, $q){
	var data = this;
	var config = {
		headers : {
			'Content-Type': 'application/json'
		}
	};
	data.user = function(user){
		var defer = $q.defer();
		$http.post(url + '/api/employee', user, config)
		.success(function(res){
			defer.resolve(res);
			console.log(res);
			
		})
		.error(function(err, status){
			defer.reject(err);
			alert('fail');
		});
		return defer.promise;
	};
	
	data.events = function(evnt){
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
	
	data.empEvent = function(empEvent){
		var defer = $q.defer();
		
		$http.post(url + '/api/empevent', empEvent, config)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err, status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	
	return data;
});