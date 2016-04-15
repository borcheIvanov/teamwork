angular.module('putService', [])
.service('put', function($q, $http){
	var data = this;
	
	
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