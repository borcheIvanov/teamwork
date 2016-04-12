angular.module('myApp', ['ui.router', 'log', 'reg', 'getService', 'postService', 'event', 'value','mybills'])

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
	.state('events', {
		url: '/home',
		templateUrl: 'home.html',
		controller: 'eventsCtrl'
	})
	.state('mybills', {
		url: '/mybills',
		templateUrl: 'mybills.html',
		controller: 'mybillsCtrl'
	});
	$urlRouterProvider.otherwise('/');
})

.run(function(logged, $rootScope, $state) {
    $rootScope.$on('$stateChangeStart',
        function(event, toState, toParams, fromState, fromParams){
			if(toState.name === 'events' &&  logged.username === ''){
				event.preventDefault();
				$state.go('login');
			}
			
		});
})


