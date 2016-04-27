angular.module('myApp')
	
	.service('date', function(){
		var data = this;
		
		data.oneByOne = function(date){
			var temp = new Date(date).toUTCString().split(' ');
			temp = temp[1] + ' ' + temp[2] + ' ' + temp[3]; 
			return temp;
		};
		
		data.empEventDate = function(niza){
			for(i = 0; i < niza.length; i++){
				var temp1 = new Date(niza[i].events_id.createdDate).toUTCString().split(' ');
				temp1 = temp1[1] + ' ' + temp1[2] + ' ' + temp1[3];
				niza[i].events_id.createdDate = temp1;
				var temp2 = new Date(niza[i].events_id.expirationDate).toUTCString().split(' ');
				temp2 = temp2[1] + ' ' + temp2[2] + ' ' + temp2[3];
				niza[i].events_id.expirationDate = temp2;
					
			}
			return niza;
		};
		
		data.eventDate = function(niza){
			for(i = 0; i < niza.length; i++){
				var temp1 = new Date(niza[i].createdDate).toUTCString().split(' ');
				temp1 = temp1[1] + ' ' + temp1[2] + ' ' + temp1[3];
				niza[i].createdDate = temp1;
				var temp2 = new Date(niza[i].expirationDate).toUTCString().split(' ');
				temp2 = temp2[1] + ' ' + temp2[2] + ' ' + temp2[3];
				niza[i].expirationDate = temp2;
					
			}
			return niza;
		};
		
		return data;
	})
	
	
	
	
	
	
	
	
	
	
	
	
	