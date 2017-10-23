	$("#regUser").click(function(){
		$.get("reguser.html", function(data){
			 swal({
			     title: '用户注册',
			     html:   data||"",
				 preConfirm: function() {
				    return new Promise(function(resolve) {
				        resolve([$('#regusername').val(),
				                 $('#regpassword').val(),
				                 $('#regphone').val()]);
				    });
				  }
				}).then(function(result) {
					 if (result) {
						 
						   var date= eval(result);
						   if (isNull(date[0])||date[0].length!=6) {
								swal({
									text: "请输入6位用户名!",
						            timer: 2000,
						            width:300,
						            showConfirmButton:false,
						        });
						        return;
						    }
						    if (isNull(date[1])) {
						    	swal({
									text: "请输入密码!",
						            timer: 2000,
						            width:300,
						            showConfirmButton:false,
						        });
						        return;
						    }
						    if(!(/^1[34578]\d{9}$/.test(date[2]))){
						    	swal({
									text: "请正确输入手机号码!",
						            timer: 2000,
						            width:300,
						            showConfirmButton:false,
						        });
						        return;
						    }
						   $.ajax({
						  		type: "POST",
						  	   	async: true,
						  	   	url: ctx + "/reguser.do",
						  	   	data: {"regusername":date[0],"regpassword":MD5(date[1]),"regphone":date[2]},
						  	  	error: function(){	
						  	  	swal({
									text: "系统错误 请稍后再试！",
						            timer: 2000,
						            width:300,
						            showConfirmButton:false,
						        });
						  		},
						  		success: function(res){
						  			if(!isNull(res)){
						  				swal({
											text: res.date,
								            timer: 2000,
								            width:300,
								            showConfirmButton:false,
								        });
						  			}
						  		}
						  	});
						  }
			});
	    });	

	    
   });
