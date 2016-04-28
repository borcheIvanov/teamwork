angular.module('myApp')

	.factory('ceil', function(){
		return{
			money: function(niza){
				for(i = 0; i < niza.length; i++){
					niza[i].moneyOWNED = Math.ceil(niza[i].moneyOWNED);
					
				}
				
				return niza;
			}
			
		}
	});