function pageObj (divid,objName,pageSize){	
		this.extend=function(o,n,override){    
			for(var p in n){
				if(n.hasOwnProperty(p) && (!o.hasOwnProperty(p) || override)){
					o[p]=n[p]; 
				}
			}
		};
		this.pageID = divid;
		this.objName = objName;
		//生成分页基础模版
		this.divinit = function(){			
			var pageHtml = "";
			pageHtml+='<div class="pagingBox">';
			pageHtml+='<input type="button" value="<上一页" class="normalPage" onclick="javascript:'+objName+'.gotoPage(-1,1,\''+objName+'\');"/>';
			pageHtml+='<span id="_pagelist_Div_'+objName+'_">1/6</span>';
			pageHtml+='<input type="button" value="下一页>" class="normalPage" onclick="javascript:'+objName+'.gotoPage(+1,1,\''+objName+'\');"/>';			
			pageHtml+='第<input type="text" class="XPageNum" value="1" id="_currentPage_'+this.pageID+'_" />页';
			pageHtml+='<input type="button" value="跳转" class="goPage" onclick="javascript:'+objName+'.gotoPageRefresh();"/>';
			pageHtml+='</div>';			
			$("#"+this.pageID).html(pageHtml);			
		}
		this.divinit();	
		
		this.getpageID = function(){
			return this.pageID;
		}
		
		this._currentPage = 1;
		this._totalNumber = 1;	
		this._totalPages = 1;
		if(pageSize){
			this._pageSize = pageSize;
		}else{
			this._pageSize = 10;
		}
		this._currentPage_ = $("#_currentPage_"+this.pageID+"_");
		this._url_ = "";
		this._parameter_ = "";		
		this._pagelist_Div_ = $("#_pagelist_Div_"+this.pageID+"_");	
		this.successFun_ ;
		this.failureFun_;
				
		this.initValue = function (scope){
			var scope_ = this;
			if(scope){
				scope_ = scope;
			}
			scope_._currentPage = 1;
			scope_._totalNumber = 1;
			scope_._pageSize = 5;
			scope_._totalPages = 1;
			scope_._currentPage_.val(1);
			scope_._url_ = "";
			scope_._parameter_ = "";
			scope_.successFun_ ;
			scope_.failureFun_;
		}
		
		/*根据
			每页展示数量、当前页码、总数量
		     计算
			当前页的数据开始点、数据结束点 、 总页数
		*/
		this.getPageInfo  =  function (pageSize,currentPage,totalNumber,scope){
			var scope_ = this;
			if(scope){
				scope_ = scope;
			}
			scope_._totalPages = Math.ceil(totalNumber/scope_._pageSize);
			scope_.pageStart = currentPage;
			scope_.endPage = Number(scope_.pageStart)+Number(scope_._pageCount_)-1;		
			if(scope_.endPage > scope_._totalPages){					
				scope_.endPage = scope_._totalPages;
				scope_.pageStart = Number(scope_.endPage) -Number(scope_._pageCount_)+1;
				if(scope_.pageStart<1){
					scope_.pageStart =1;
				}
			}		
			if(currentPage<=1){
				scope_._currentPage_.val(1);			
				scope_._currentPage = 1;						
			}else if(currentPage >= scope_._totalPages){
				if(scope_._totalPages<=1){
					scope_._currentPage_.val(1);
					scope_._currentPage = 1;		
				}else{
					scope_._currentPage_.val(scope_._totalPages);
					scope_._currentPage = scope_._totalPages;	
				}
			}	
			_pageList_str = scope_._currentPage+'/'+scope_._totalPages;		
			scope_._pagelist_Div_.html(_pageList_str);
		}
		
		//分页面板的刷新事件
		this.gotoPageRefresh = function(scope){
			var scope_ = this;
			if(scope){
				scope_ = obj;
			}
			//调用数据请求方法
			scope_.gotoPage(scope_._currentPage_.val(),2,scope_.objName);
		}

		//提供给jsp外部调用的方法，用于数据请求
		this.gridLoad_Page=function(parameter,url,successCallback,failureCallback,scope){			
			var scope_ = this;
			if(scope){
				scope_ = scope;
			}			
			//回调方法存储
			scope_.successFun_ = successCallback;
			scope_.failureFun_ = failureCallback;			
			//获取每页数据量
			var _pageSize = scope_._pageSize;			
			//参数存储，以备分页控件点击时进行赋值使用
			scope_._parameter_ = parameter;
			//请求路径存储，以备分页控件点击时进行赋值使用
			scope_._url_ = url;				
			//参数拼装，加入分页参数
			scope_.extend(parameter,{"limit":_pageSize,"pageNum":scope_._currentPage_.val()},true);			
			//数据请求
			$.ajax({
				type: "POST",
				dataType:"JSON",
				url: url,
				data: parameter,//包含请求数据以及分页数据
				beforeSend: function(XMLHttpRequest){},
				complete: function(XMLHttpRequest, textStatus){},
			    error: function(){					
					art.alert(window.error_title);
				},																		
				success: function(data){					
					if(data!=null &&　data !=""){	
						var obj = data;	
						var success = obj.CODE;					
						if(success=="TRUE"){		
							successCallback(obj);
							try{								
								scope._totalNumber = obj.totalCount;
								scope.getPageInfo(scope_._pageSize,scope._currentPage,obj.totalCount,scope);	
							}catch(e){}							
						}else if(success=="FALSE"){
							failureCallback(data);
						}	
					}else{}			
				},
				callback:function(data){	
					alert("数据获取失败");				
					art.alert(data);
				}
			}); 
		 }

	     // 分页  type = 1 时，用来计算，不存在type时page为需要跳转的页面
		 //page:page页码 ，type:1,page参数为当前页码的累加值；2，跳转页码    ； obj :为作用域
	     this.gotoPage = function (page,type,scope){
	    	   var scope_ = this;
	    	   if(scope){
	    		   scope_ = eval(scope);
	    	   }
	    	   if(type == '1'){
	        	   scope_._currentPage = Number(scope_._currentPage) + Number(page); 	 
	           }else{            
					if(page == 1){
						scope_._currentPage = 1;					
					}else if(page >1 && page <= scope_._totalPages){
						scope_._currentPage = page;
					}else{
						scope_._currentPage = scope_._totalPages;
					}
	           }   	          
	           if(scope_._currentPage<=1){
	        	   scope_._currentPage = 1;	        	   
	           }else if(scope_._currentPage>=scope_._totalPages){
	        	   scope_._currentPage = scope_._totalPages;	        	   
	           } 
	           scope_._currentPage_.val(scope_._currentPage);  
	           
	           var _pageSize_temp = scope_._pageSize;
	           if(_pageSize_temp != scope_._pageSize) {			
	           		scope_._pageSize = _pageSize_temp;
	           		scope_._currentPage_.val(1);
	           }	           
	           			  
	           scope_.gridLoad_Page(scope_._parameter_,scope_._url_,scope_.successFun_,scope_.failureFun_,scope_);
	       }	       
}




