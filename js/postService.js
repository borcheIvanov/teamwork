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
		$http.post( url + '/api/employee', user, config)
		.success(function(res){
			defer.resolve(res);
			
		})
		.error(function(err, status){
			defer.reject(err);
			alert('fail');
		});
		return defer.promise;
	};
	
	
	return data;
});