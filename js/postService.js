angular.module('postService',[])
.service('post', function($http, $q){
	var data = this;
	data.user = function(user){
		var defer = $q.defer();
		$http.post('http://localhost:8080/events/api/people', user)
		.success(function(res){
			defer.resolve(res);
		})
		.error(function(err, status){
			defer.reject(err);	
		});
		return defer.promise;
	};
	
	
	return data;
});