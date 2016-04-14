angular.module('putService', [])
.service('put', function($scope, $q, $http){
	var data = this;
	
	data.employee = function(val){
		var defer = $q.deffer();
		
		$http.put(url+ '/api/empevent'+id, val)
		.success(function(res){
			defer.resolve(res);
			console.log('success');
			console.log(res);
		})
		.error(function(err,status){
			defer.reject(err);
			console.log(err);
			console.log(status);
			
		})
		return defer.promise;
	};
	
	return data;
});