angular.module('getService', [])
.service('get', function($http, $q, logged, $cookies){
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
			var temp = res;
			console.log(temp);
			logged.id = temp.id;
			logged.username = temp.username;
			
			$cookies.put('cookie_username', logged.username);
			$cookies.put('cookie_id', logged.id);
			
			
		})
		.error(function(err, status){
			defer.reject(err);
			console.log('log in');
		})
		return defer.promise;
	};
	
	data.eventPan = function(id){
		var defer = $q.defer();
		
		$http.get(url + '/api/empeventinv/' + id)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err,status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	data.eventhost = function(id){
		var defer = $q.defer();
		
		$http.get(url + '/api/empeventhost/' + id)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err,status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	data.eventinv = function(id){
		var defer = $q.defer();
		
		$http.get(url + '/api/empeventinv/' + id)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err,status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	data.eventwhere = function(id){
		var defer = $q.defer();
		
		$http.get(url + '/api/empeventwhere/' + id)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err,status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	return data;
});