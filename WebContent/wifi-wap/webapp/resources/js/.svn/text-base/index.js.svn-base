function getTip(){
	var jsonData = "";
	jQuery.ajax
	({
		type: "get",
		url: ctxPaths + "/tip",
		dataType: "json",
		cache: false,
		success: function(data){
		if(data.CODE=="FALSE"){
			alert("获取公告信息失败！");
		}else if(data.CODE=="TRUE"){
			var list   = data.list;
			var html = '';
			if(list.length>0){
				html+='<MARQUEE scrollAmount=2 scrollDelay=8 width=100% border="0" >'; 
				for(var i=0;i<list.length;i++){
					html+='<span>• '+list[i].publish_content+'</span>&nbsp;&nbsp;';
				}
				html+='</marquee>'; 
			}
			$('.index-gonggao-text').html(html);
		}else{
			alert("对不起网络异常！");
		}
		},
		error: function(){
			$('.index-gonggao-text').html("获取公告信息");
		}
	});
	
}