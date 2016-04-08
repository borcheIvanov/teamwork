angular.module('myApp', ['ui.router', 'log', 'reg', 'getService', 'postService', 'event', 'value'])

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
		url: '/events',
		templateUrl: 'events.html',
		controller: 'eventsCtrl'
	});
	$urlRouterProvider.otherwise('/');
})

.run(function(logged, $rootScope, $state) {
    $rootScope.$on('$stateChangeStart',
        function(event, toState, toParams, fromState, fromParams){
			if(toState.name === 'events' &&  logged.username === ''){
				//event.preventDefault();
				//$state.go('login');
			}
		});
});

