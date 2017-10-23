$("#findUser").click(function(){
	$.get("finduser.html", function(data){
 		swal({
 			  title: '核实账号',
 			   html:   data||"",
 			  showCancelButton: true,
 			  confirmButtonText: '提交',
 			  cancelButtonText :'退出',
 			 preConfirm: function() {
				    return new Promise(function(resolve,reject) {
				    	if($('#findusername').val()==""){
				    		reject('请填写用户名');
				    	}
				    	if(!(/^1[34578]\d{9}$/.test($('#findphone').val()))){
				    		reject('请正确填写手机号码');
				    	}
				        resolve([$('#findusername').val(),
				                 $('#findphone').val()]);
				        
				    });
				  }
 			}).then(function(result) {
 			  if (result) {
 				 $.ajax({
				  		type: "POST",
				  	   	async: true,
				  	   	url: ctx + "/checkuser.do",
				  	   	data: {"findusername":result[0],"findphone":result[1]},
				  	  	error: function(){	
				  	  	swal({
							text: "系统错误 请稍后再试！",
				            timer: 2000,
				            width:300,
				            showConfirmButton:false,
				        });
				  		},
				  		success: function(res){
				  			if(res.status){
				  				swal({
				  				  title: '重置密码',
				  				  input: 'text',
				  				  showCancelButton: true,
				  				  confirmButtonText: '确定',
				  				 cancelButtonText :'取消',
				  				  inputValidator: function(value) {
				  				    return new Promise(function(resolve, reject) {
				  				      if (value) {
				  				        resolve();
				  				      } else {
				  				        reject('密码不能为空');
				  				      }
				  				    });
				  				  }
				  				}).then(function(result) {
				  				  if (result) {
				  					   $.ajax({
									  		type: "POST",
									  	   	async: true,
									  	   	url: ctx + "/finduser.do",
									  	   	data: {"findusername":$('#findusername').val(),"findpassword":MD5(result),},
									  	  	error: function(){	
									  	  	swal({
												text: "系统错误 请稍后再试！",
									            timer: 2000,
									            width:300,
									            showConfirmButton:false,
									        });
									  		},
									  		success: function(res){
									  			if(res.status){
									  				swal({
									  					type: 'success',
									  					html: ' 重置密码成功！',
									  					confirmButtonText: '确定',
									  				});
									  				
									  			}else{
									  				swal({
									  					type: 'error',
									  					html: ' 重置密码失败，请稍后再试！',
									  					confirmButtonText: '确定',
									  				});	
									  			}
									  	
									  		}
									  	});
				  					  
				  				  }
				  				});
				  			}else{
				  				swal({
									text:"账号或手机号码不存在！",
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