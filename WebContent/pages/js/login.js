
//初始化数据

var sleep=60,interval=null;

$(document).ready(function(){
	const items = new Set([1, 2, 3, 4, 5, 5, 5, 5]);
	console.log(items.size+"---"); // 5
	
	
	const map = new Map([
	                     ['name', '张三'],
	                     ['title', 'Author']
	                   ]);
	console.log(map.get('title'));
	var username = getLocalStorage("username");
	if(!isNull(username)){
		$('#username').val(username);
	}
	
	//******************************获取验证码开始******************************
	 var btn = document.getElementById ('getyzm');
	 btn.onclick = function ()
	    {
		  var currphone = $('#currphone').val();
		 if(!(/^1[34578]\d{9}$/.test(currphone))){
		    	swal({
					text: "请正确输入手机号码!",
		            timer: 2000,
		            width:300,
		            showConfirmButton:false,
		        });
		        return false;
		    }
	      if (!interval)
	      {
	        this.disabled = "disabled";
	        this.value = "重新发送 (" + sleep-- + ")";
	        interval = setInterval (function ()
	        {
	          if (sleep == 0)
	          {
	            if (!!interval)
	            {
	              clearInterval (interval);
	              interval = null;
	              sleep = 60;
	              btn.removeAttribute ('disabled');
	              btn.value = "获取验证码";
	            }
	            return false;
	          }
	          btn.value = "重新发送 (" + sleep-- + ")";
	        }, 1000);
	      }
	      
	      $.ajax({
	  		type: "POST",
	  	   	async: true,
	  	   	url: ctx + "/sendsms.do",
	  	   	data: {"currphone":currphone},
	  	  	error: function(){	
	  	  		
	  		},
	  		success: function(res){
	  			if(!isNull(res)){
	  			}
	  		}
	  	});
	    };
//******************************获取验证码结束******************************
});
//登录

$("#btnLogin").click(function(){
	var username = $('#username').val();
	var password = $('#password').val();
	var currphone = $('#currphone').val();
	var yzmvalue = $('#yzmvalue').val();
	if (isNull(username)||username.length!=6) {
		swal({
			text: "请输入6位用户名!",
            timer: 2000,
            width:300,
            showConfirmButton:false,
        });
        return false;
    }
    if (isNull(password)) {
    	swal({
			text: "请输入密码!",
            timer: 2000,
            width:300,
            showConfirmButton:false,
        });
        return false;
    }
    if(!(/^1[34578]\d{9}$/.test(currphone))){
    	swal({
			text: "请正确输入手机号码!",
            timer: 2000,
            width:300,
            showConfirmButton:false,
        });
        return false;
    }
    if(isNull(yzmvalue)){
    	swal({
			text: "请输入验证码!",
            timer: 2000,
            width:300,
            showConfirmButton:false,
        });
        return false;
    }
	//加密
 password = MD5(password);
	$.ajax({
		type: "POST",
	   	async: true,
	   	url: ctx + "/login.do",
	   	data: {"shopNo":username,"password":password,"yzmvalue":yzmvalue},
	  	error: function(){	
	  		swal({
				text: "登录失败!",
	            timer: 2000,
	            width:300,
	            showConfirmButton:false,
	        });
	  		return ;
		},
		success: function(res){
			var status=res.status;
			if(status=="0"||status=="1"){
				swal({
					text: res.note,
		            timer: 2000,
		            width:300,
		            showConfirmButton:false,
		        });
			}else if(status=="2"){
				swal({
					text: res.note,
		            width:300,
		            showConfirmButton:false,
		        });
				setTimeout(function() {
					window.location.href = ctx+"/pages/index.html";
			      }, 2000);
		        
			}else{
				swal({
					text: "系统错误 稍后再试！",
		            timer: 2000,
		            width:300,
		            showConfirmButton:false,
		        });
			}
			
		}
	});
});
