angular.module('reg', [])
.controller('regCtrl', function($scope, get, post){
	
	$scope.init = function(){
		$scope.getAll();
		$scope.userName = '';
		$scope.firstName = '';
		$scope.surName = '';
		$scope.email = '';
		$scope.password = '';
		$scope.passwordAgain = '';
		$scope.error = '';
	};
	
	$scope.getAll = function(){
		get.users()
		.then(function(res){
			//success
			$scope.users = res;
		})
		.then(function(err){
			//error
		})
	};
	
	$scope.submit = function(){
		var mail = true;
		var uName = true;
		var temp = {
			'name': $scope.firstName,
			'surname': $scope.surName,
			'username': $scope.userName,
			'active': 'true',
			'email': $scope.email,
			'password': $scope.password
		}
		
		for(i = 0; i < $scope.users.length; i++){
			if($scope.users[i].email === $scope.email){
				mail = false;
			}
			if($scope.userName === $scope.users[i].username){
				uName = false;
			}
		}
		
		if($scope.password != $scope.passwordAgain){
			$scope.error = 'password missmatch';
		}else if(mail === false){
			$scope.error = 'Email already in use.';
		}else if(uName === false){
			$scope.error = 'Username taken.';
		}else{
			post.user(temp)
			.then(function(){
				
				$scope.init();
			})
			.then(function(){
				
			});
		}
	};
	
	

	$scope.init();
});