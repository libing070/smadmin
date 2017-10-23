$(document).ready(function(){
	$.get("listManager.html", function(data){
		$("#mainDate").html(data);
	});
	
	
});