angular.module('myApp')

	.value('logged', {
		'id': '',
		'username': ''
	});
	
	var url = 'http://localhost:8080/events';

