// JavaScript Document

$(function($) {
	
	//奖品详情——填写信息
	$("#txt_name").focus(function(){if($.trim($(this).val())=="请输入"){$(this).val("");}})
	$("#txt_name").blur(function(){if($.trim($(this).val())==""){$(this).val("请输入");}})
	$("#txt_photo").focus(function(){if($.trim($(this).val())=="请输入"){$(this).val("");}})
	$("#txt_photo").blur(function(){if($.trim($(this).val())==""){$(this).val("请输入");}})
	$("#txt_ardes").focus(function(){if($.trim($(this).val())=="请输入"){$(this).val("");}})
	$("#txt_ardes").blur(function(){if($.trim($(this).val())==""){$(this).val("请输入");}})
	$("#txt_email").focus(function(){if($.trim($(this).val())=="请输入"){$(this).val("");}})
	$("#txt_email").blur(function(){if($.trim($(this).val())==""){$(this).val("请输入");}})
	
	//最新中奖名单
	/**/	
});



var st;
var lingqu_str="";
function chrompublic(){
	/* 
    判断浏览器名称和版本 
    只能判断:ie/firefox/chrome/opera/safari 
    浏览器内核UA:UA; 
    浏览器内核名称:NV.name; 
    浏览器内核版本:NV.version; 
    浏览器外壳名称:NV.shell; 
*/ 
	var NV = {};  
	var UA = navigator.userAgent.toLowerCase();  
	try{  
		NV.name=!-[1,]?'ie':  
		(UA.indexOf("firefox")>0)?'firefox':  
		(UA.indexOf("chrome")>0)?'chrome':  
		window.opera?'opera':  
		window.openDatabase?'safari':  
		'unkonw';  
	}catch(e){};  
	try{  
		NV.version=(NV.name=='ie')?UA.match(/msie ([\d.]+)/)[1]:  
		(NV.name=='firefox')?UA.match(/firefox\/([\d.]+)/)[1]:  
		(NV.name=='chrome')?UA.match(/chrome\/([\d.]+)/)[1]:  
		(NV.name=='opera')?UA.match(/opera.([\d.]+)/)[1]:  
		(NV.name=='safari')?UA.match(/version\/([\d.]+)/)[1]:  
		'0';  
	}catch(e){};  
	try{  
		NV.shell=(UA.indexOf('360ee')>-1)?'360极速浏览器':  
		(UA.indexOf('360se')>-1)?'360安全浏览器':  
		(UA.indexOf('se')>-1)?'搜狗浏览器':  
		(UA.indexOf('aoyou')>-1)?'遨游浏览器':  
		(UA.indexOf('theworld')>-1)?'世界之窗浏览器':  
		(UA.indexOf('worldchrome')>-1)?'世界之窗极速浏览器':  
		(UA.indexOf('greenbrowser')>-1)?'绿色浏览器':  
		(UA.indexOf('qqbrowser')>-1)?'QQ浏览器':  
		(UA.indexOf('baidu')>-1)?'百度浏览器':  
		'未知或无壳';  
	}catch(e){} 
	//var st=document.body.scrollTop;//滚动条距顶部的距离360可以其他浏览器不行
	if(NV.name=="chrome"){
		st=document.body.scrollTop;
	}
	else{
		st=document.documentElement.scrollTop;
	}
}
/*微博遮罩层*/
function showBg(ct,content){ 
	var bH=$("body").height(); 
	var bW=$("body").width()+16; 
	var objWH=getObjWh(ct); 
	$("#fullbg").css({width:bW,height:bH,display:"block"}); 
	var tbT=objWH.split("|")[0]+"px"; 
	var tbL=objWH.split("|")[1]+"px"; 
	$("#"+ct).css({top:tbT,left:"15%",display:"block"}); 
	$("#"+content).html("<div style='text-align:center'></div>"); 
	$(window).scroll(function(){resetBg()}); 
	$(window).resize(function(){resetBg()}); 
} 
function getObjWh(obj){ 
	chrompublic();
	//var st=document.documentElement.scrollTop;//滚动条距顶部的距离 
	var sl=document.documentElement.scrollLeft;//滚动条距左边的距离 
	var ch=document.documentElement.clientHeight;//屏幕的高度 
	var cw=document.documentElement.clientWidth;//屏幕的宽度 
	var objH=$("#"+obj).height();//浮动对象的高度 
	var objW=$("#"+obj).width();//浮动对象的宽度 
	var objT=Number(st)+(Number(ch)-Number(objH))/2; 
	var objL=Number(sl)+(Number(cw)-Number(objW))/5.1; 
	return objT+"|"+objL; 
} 
function resetBg(){ 
	var fullbg=$("#fullbg").css("display"); 
	if(fullbg=="block"){ 
		var bH2=$("body").height(); 
		var bW2=$("body").width()+16; 
		$("#fullbg").css({width:bW2,height:bH2}); 
		var objV=getObjWh("dialog"); 
		var tbT=objV.split("|")[0]+"px"; 
		var tbL=objV.split("|")[1]+"px"; 
		$("#dialog").css({top:tbT,left:tbL}); 
	} 
} 

//关闭灰色JS遮罩层和操作窗口 
function closeBg(){ 
	$("#fullbg").css("display","none"); 
	$("#dialog").css("display","none"); 
	$(".foot4").css("display","none");
} 




/*微博遮罩层_top12*/
function showBg_top12(ct,content){ 
	var bH=$("body").height(); 
	var bW=$("body").width()+16; 
	var objWH=getObjWh_top12(ct); 
	$("#fullbg").css({width:bW,height:bH,display:"block"}); 
	var tbT=objWH.split("|")[0]+"px"; 
	var tbL=objWH.split("|")[1]+"px"; 
	$("#"+ct).css({display:"block"}); 
	$("#"+content).html("<div style='text-align:center'></div>"); 
	$(window).scroll(function(){resetBg_top12()}); 
	$(window).resize(function(){resetBg_top12()}); 
} 
function getObjWh_top12(obj){ 
	chrompublic();
	//var st=document.documentElement.scrollTop;//滚动条距顶部的距离 
	var sl=document.documentElement.scrollLeft;//滚动条距左边的距离 
	var ch=document.documentElement.clientHeight;//屏幕的高度 
	var cw=document.documentElement.clientWidth;//屏幕的宽度 
	var objH=$("#"+obj).height();//浮动对象的高度 
	var objW=$("#"+obj).width();//浮动对象的宽度 
	var objT=Number(st)+(Number(ch)-Number(objH))/2; 
	var objL=Number(sl)+(Number(cw)-Number(objW))/5.1; 
	return objT+"|"+objL; 
} 
function resetBg_top12(){ 
	var fullbg=$("#fullbg").css("display"); 
	if(fullbg=="block"){ 
		var bH2=$("body").height(); 
		var bW2=$("body").width()+16; 
		$("#fullbg").css({width:bW2,height:bH2}); 
		var objV=getObjWh_top12("apDiv1"); 
		var tbT=objV.split("|")[0]+"px"; 
		var tbL=objV.split("|")[1]+"px"; 
		//$("#apDiv1").css({top:tbT,left:tbL}); 
	} 
} 

//关闭灰色JS遮罩层和操作窗口 
function closeBg_top12(){ 
	$("#fullbg").css("display","none"); 
	$("#apDiv1").css("display","none"); 
	$(".foot4").css("display","none");
} 

function showbg_f(){
	$(".foot4").css("display","block");
	showBg('dialog','dialog_content');
}

function showbg_top12(){
	$(".foot4").css("display","block");
	showBg_top12('apDiv1','apDiv1_content');
}


//业务订阅
function BusSubPtion(thisobj){
	if($("#btn_dg").val()=="我要订购"){
		var sp_name=$("#dyshp_name").text()+"\n"+$("#sp_zftitle").text();
		var order_title='您要办理的是'+sp_name+'是否确认？';
		$("#order_title").text(order_title);
		$("#order_tc").css("display","block");
	}
}
function closeorder_tc(obj_id){
	$("#"+obj_id).css("display","none");
}
function btsubmitorder_tc(){
	$("#order_tc").css("display","none");
	$("#btn_dg").val('已订购');
	$("#btn_dg").removeClass("bt_wydy");
	$("#btn_dg").addClass("btn_lingqu_Nmry");
	$("#order_tc2").css("display","block");
}

//领取
function ling_qu(objt){
	if($(objt).val()=="领取"){
		$("#order_tc").css("display","none");
		$(objt).val("已领取")
		$(objt).removeClass("bt_wydy");
		$(objt).addClass("btn_lingqu_Nmry");
		$("#order_tc2").css("display","block");
	}
}
//填写资料领取
function userconnet_s(){
	var order_title='请您仔细核对所填信息，提交后将不能修改。确认提交吗？';
	$("#order_title").text(order_title);
	$("#order_tc").css("display","block");
}
function btsubmitlingqu_tc(){
	$("#order_tc").css("display","none");
	lingqu_str="恭喜您的信息已提交！请保持手机畅通等待领取奖品哦~";
	window.location="Winning_check_ipadyt.html";
}

function dinggouCG(sp_name){
	alert(sp_name+'订购成功');
	$("#btn_dg").val('已订购');
	$("#btn_dg").removeClass("bt_wydy");
	$("#btn_dg").addClass("btn_lingqu_Nmry");
}



//菜单
$(function () {
	//绑定事件处理
	$(".pic_user a").click(function (e) {
		if ($("#Mun_id").css("display") == "none") {
				e.stopPropagation();
				//设置弹出层位置
				var offset = $(e.target).offset();
				//设置弹出层位置在点击的下面
				//$("#Mun_id").css({ top: offset.top + $(e.target).height() + "px", left: offset.left });
				$("#Mun_id").show();
				}
		else {
			$("#Mun_id").hide();
		}
	});
	//单击空白区域隐藏弹出层
	$(document).click(function (event) { $("#Mun_id").hide(); });
	//单击弹出层则自身隐藏
	//$("#divObj").click(function (event) { $("#divObj").hide(speed) });
});

	
	
	
//foot_close
function foot_close(){
	$("#foot_music_id").css("display","none");
}
	
	