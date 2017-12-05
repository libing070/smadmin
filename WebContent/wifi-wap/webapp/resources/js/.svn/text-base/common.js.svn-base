//手机提示弹出框  参数：提示内容
function phoneAlert(prompt,times){
	if(times==null){
		times=10000;
	}
	
	$("#zz").html(prompt);
	var zzHtml=$("#zz")[0].outerHTML;	
	$("#zz").remove();	
	
	$("body").append(zzHtml);
	$("#zz").fadeIn("slow",function(){
		if($(this).is(":visible")){
			var $this = $(this);
			setTimeout(function(){
				$this.fadeOut("slow");
			},times);
		}
	});
}
//获取URL请求的参数
function getQueryStringRegExp(name){
    var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
    if (reg.test(location.href)) {   	
    	return unescape(RegExp.$2.replace(/\+/g, " "));   
    }
    return "";
};

//删除URL请求参数
function urlParamDel(url,name){
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	return url.replace(reg,"");
}

//取获参数值
function getQueStr(url,ref){ 
	var str = url.substr(url.indexOf('?')+1);
	if(str.indexOf('&')!=-1){
		var arr = str.split('&');
		for(i in arr){
			if(arr[i].split('=')[0]==ref)
				return arr[i].split('=')[1];
		}
	}else{
		return url.substr(url.indexOf('=')+1);
	}
}

//设置参数值	    
function setQueStr(url,ref,value){ 
	var str = "";			
	if (url.indexOf('?') !=-1)
		str = url.substr(url.indexOf('?')+1);
	else
		return url + "?" + ref + "=" + value;
	var returnurl="";
	var setparam = "";    
	var arr;
	var modify = "0";
	if(str.indexOf('&')!=-1){
		 arr = str.split('&');		 
		 for(i in arr){
			if(arr[i].split('=')[0]==ref){
				setparam = value;
				modify = "1";
			}else{
				setparam = arr[i].split('=')[1];
			}	 
			returnurl = returnurl + arr[i].split('=')[0] + "=" + setparam + "&";
		 }		 
		 returnurl = returnurl.substr(0,returnurl.length-1);
		 if (modify == "0")
			if (returnurl == str)
		   returnurl = returnurl + "&" + ref+ "=" + value;
	}else{
		 if (str.indexOf('=')!=-1){
			arr = str.split('=');
		  
			if(arr[0]==ref){   
				setparam = value;
				modify = "1";
			}else{
				setparam = arr[1];
			}		   
			returnurl = arr[0] + "=" + setparam;
			  
			if (modify == "0")
				if (returnurl == str)
					returnurl = returnurl +"&" + ref + "=" + value;
		 }else
			returnurl = ref + "=" + value; 
	}		
	return url.substr(0,url.indexOf('?')) + "?" + returnurl;
}
 
//删除参数值
function delQueStr(url,ref){ 
	var str = "";		
	if (url.indexOf('?') !=-1)
		str = url.substr(url.indexOf('?')+1);
	else
		return url;		
	var arr = "";
	var returnurl="";
	var setparam = ""; 		
	if(str.indexOf('&')!=-1){        
		arr = str.split('&');		 
		for(i in arr){
			if(arr[i].split('=')[0] !=ref){
				returnurl = returnurl + arr[i].split('=')[0] + "=" + arr[i].split('=')[1] + "&";
			}      
		}
		return url.substr(0,url.indexOf('?')) + "?" +returnurl.substr(0,returnurl.length-1);
	}else{    
		arr = str.split('=');			  
		if (arr[0]==ref)
			return url.substr(0,url.indexOf('?'));
		else
			return url;
	}
}

//替换URL请求的参数
function ChangeParam(url,name, value) {
    var newUrl = "";
    var reg = new RegExp("(^|)" + name + "=([^&]*)(|$)");
    var tmp = name + "=" + value;
    if (url.match(reg) != null) {
        newUrl = url.replace(eval(reg), tmp);
    }
    else {
        if (url.match("[\?]")) {
            newUrl = url + "&" + tmp;
        }
        else {
            newUrl = url + "?" + tmp;
        }
    }
    return newUrl;
}

/*日期格式化追加的方法*/
Date.prototype.pattern=function(fmt) {
    var o = {
    "M+" : this.getMonth()+1, //月份
    "d+" : this.getDate(), //日
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
    "H+" : this.getHours(), //小时
    "m+" : this.getMinutes(), //分
    "s+" : this.getSeconds(), //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S" : this.getMilliseconds() //毫秒
    };
    var week = {
    "0" : "\u65e5",
    "1" : "\u4e00",
    "2" : "\u4e8c",
    "3" : "\u4e09",
    "4" : "\u56db",
    "5" : "\u4e94",
    "6" : "\u516d"
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
};

/**
 * 获取字符串长度（1个中文长度为2个长度单位）
 * @param val
 * @returns {number}
 */
function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) //全角
            len += 2;
        else
            len += 1;
    }
    return len;
}

/**
 * 显示进度条
 */
function showLoading() {
	$("#maskBox").show();
}
/**
 * 关闭进度条
 */
function hideLoading() {
	$("#maskBox").hide();
}

/**
 * 隐藏手机号
 * @param mobile
 * @returns {String}
 */
function maskMobilePhone(mobile) {
	return mobile.substr(0, 3) + "****" + mobile.substr(7, mobile.length);
}