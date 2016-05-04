angular.module('myApp', ['ui.router', 'ngCookies', 'simplePagination', 'angularCharts'])

.run(function(logged, $rootScope, $state, $cookies) {
	
	var cookie_id = $cookies.get('cookie_id');
	var cookie_username = $cookies.get('cookie_username');
	
	if(cookie_id !== '' && cookie_username !== ''){
		logged.id = cookie_id;
		logged.username = cookie_username;
	}
	
    $rootScope.$on('$stateChangeStart',
        function(event, toState, toParams, fromState, fromParams){
			
			if(toState.name !== 'login' && logged.username === '' && toState.name !== 'register'){
				event.preventDefault();
				$state.go('login');
			}
			if(toState.name === 'events' && fromState.name === 'login'){
				event.preventDefault();
				$state.go('home');
			}
			
			if(toState.name === 'login' && logged.username !== ''){
				event.preventDefault();
				$state.go('home');
			}
		});
});



