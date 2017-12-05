$(function(){
	$.get("loadHome1.html",{"currTime": new Date().getTime() } ,function(data){
		$("#loadHome1").append(data);
	});
	
	
	$.get("loadHome2.html",{"currTime": new Date().getTime() }, function(data){
		$("#loadHome2").append(data);
	});
		
});