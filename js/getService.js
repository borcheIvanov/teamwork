angular.module('getService', [])
.service('get', function($http, $q){
	var data = this;
	data.gg = [];
	
	data.users = function(){
		var defer = $q.defer();
		
		$http.get('http://localhost:8080/events/api/people')
		.success(function(res){
			
			data.gg = res;
			defer.resolve(res);
		})
		.error(function(err, status){
			defer.reject(err);
			
		})
		return defer.promise;
		
	};
	data.events = function(){
		var defer = $q.defer();
		
		$http.get('http://localhost:8080/src/api/task')
		.success(function(res){
			data.gg = res;
			defer.resolve(res);
		})
		.error(function(err, status){
			defer.reject(err);
		})
		return defer.promise;
	}
	data.login = function(user, pass){
		var defer = $q.defer();
		
		$http.get('http://localhost:8080/events/api/login/' + user + '/' + pass)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err, status){
			defer.reject(err);
		})
		return defer.promise;
	}
	
	return data;
});