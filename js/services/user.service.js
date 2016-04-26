angular.module('myApp')
	.service('user', function($http, $q, logged){
	
		var data = this;
		
		var config = {
			headers : {
				'Content-Type': 'application/json'
			}
		};
		
	//////////////////////////////get

		data.getUsers = function(){
			
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
		
	//////////////////////////////post
		
		data.postUser = function(user){
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
		
		return data;
	});