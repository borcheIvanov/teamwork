angular.module('myApp')
	.service('logger', function($http, $q, logged, $cookies){
	
		var data = this;
		
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
		
		return data;
	});