angular.module('getService', [])
.service('get', function($http, $q){
	var data = this;
	data.gg = [];
	
	data.users = function(){
		var defer = $q.defer();
		
		$http.get(url + '/api/employee')
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
	
	data.login = function(user, pass){
		var defer = $q.defer();
		
		$http.get(url + '/api/login/' + user + '/' + pass)
		.success(function(res){
			defer.resolve(res);
			console.log('welcome');
		})
		.error(function(err, status){
			defer.reject(err);
			console.log('log in');
		})
		return defer.promise;
	};
	
	return data;
});