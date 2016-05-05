angular.module('myApp')
.service('empEvent', function($http, $q, logged, $cookies){
	var data = this;
	data.gg = [];
	
	var config = {
		headers : {
			'Content-Type': 'application/json'
		}
	};
	
	
	data.eventPan = function(id){
		var defer = $q.defer();
		
		$http.get(url + '/api/empevent/' + id)
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
	
	//////////////////////////////post
	
	data.postArray = function(empEvent){
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
	
	//////////////////////////////put
	
	data.flagNot = function(id){
		var defer = $q.defer();
		
		$http.put(url + '/api/empeventNOTIFY/' + id)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err, status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	data.flagApr = function(id){
		var defer = $q.defer();
		
		$http.put(url + '/api/empeventAPPROVE/' + id)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err, status){
			defer.reject(err);
		})
		return defer.promise;
	};
	
	data.money = function(id, val){
		var defer = $q.defer();
		
		$http.put(url+ '/api/empevent/' + id, val)
		.success(function(res){
			defer.resolve(res);
			console.log('success');
		})
		.error(function(err,status){
			defer.reject(err);
			console.log(err);
			alert(status);
			
		})
		return defer.promise;
	};
	
	return data;
});