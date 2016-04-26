angular.module('myApp')
	
	.factory('date', function(){
		return{
			Func: function(date){
				var temp = new Date(date).toUTCString().split(' ');
				temp = temp[1] + ' ' + temp[2] + ' ' + temp[3]; 
				return temp;
			}
		}
	});