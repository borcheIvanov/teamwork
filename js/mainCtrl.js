angular.module('myApp', ['ui.router', 'ngCookies', 'n3-pie-chart', 'simplePagination'])

.config(function($stateProvider, $urlRouterProvider){
	$stateProvider
	.state('login',{
		url: '/',
		templateUrl: 'login.html',
		controller: 'logCtrl'
	})
	.state('register',{
		url:'/register',
		templateUrl: 'register.html',
		controller: 'regCtrl'
	})
	.state('home', {
		url: '/home',
		templateUrl: 'home.html',
		controller: 'eventsCtrl'
	})
	.state('events', {
		url: '/events',
		templateUrl: 'events.html',
		controller: 'newEventsCtrl'
	})
	.state('mybills', {
		url: '/mybills',
		templateUrl: 'mybills.html',
		controller: 'mybillsCtrl'
	})
	.state('stats', {
		url: '/stats',
		templateUrl: 'stats.html',
		controller: 'statsCtrl'
	})
	.state('logout', {
		url:'/logout',
		controller: 'logoutCtrl'
	});
	$urlRouterProvider.otherwise('/');
})

.run(function(logged, $rootScope, $state, $cookies) {
	var cookie_id = $cookies.get('cookie_id');
	var cookie_username = $cookies.get('cookie_username');
	
	if(cookie_id !== '' && cookie_username !== ''){
		logged.id = cookie_id;
		logged.username = cookie_username;
	}
	
    $rootScope.$on('$stateChangeStart',
        function(event, toState, toParams, fromState, fromParams){
			
			if(toState.name === 'events' && logged.username === ''){
				event.preventDefault();
				$state.go('login');
			}
			if(toState.name === 'mybills' && logged.username === ''){
				event.preventDefault();
				$state.go('login');
			}
			if(toState.name === 'login' && logged.username !== ''){
				event.preventDefault();
				$state.go('home');
			}
		});
})



