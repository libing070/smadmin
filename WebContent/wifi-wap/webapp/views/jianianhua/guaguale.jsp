<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.subject.Subject,org.apache.shiro.SecurityUtils,com.aspire.wifi.wap.util.GetConfigFile;"%>
<%
    Subject currentUser = SecurityUtils.getSubject();
    String mobile="";
   	if(currentUser.isAuthenticated()){
    	 mobile = currentUser.getPrincipal().toString();
    }
%>
<!DOCTYPE html>
<html >
<head>
<%@ include file="../common/include.jsp"%>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <link href="<%=ctxPath %>/resources/css/guahongbao.css" rel="stylesheet" />
    <title>Lottery Demo</title>
    <style type="text/css">
      
        #lotteryContainer {
            position:relative;
            width: 250px;
            height:74px;
        }
        #drawPercent {
            color:#F60;
        }
        .guaguale-input{background-color:#FFF; border:#CCC solid 1px; border-radius:1px; width:45%; height:1.5em; line-height:1.5em;}
        
    </style>
    <script>
    function Lottery(id, cover, coverType, width, height, drawPercentCallback) {
    this.conId = id;
    this.conNode = document.getElementById(this.conId);
    this.cover = cover;
    this.coverType = coverType;
    this.background = null;
    this.backCtx = null;
    this.mask = null;
    this.maskCtx = null;
    this.lottery = null;
    this.lotteryType = 'image';
    this.width = width ||300;
    this.height = height||100;
    this.clientRect = null;
    this.drawPercentCallback = drawPercentCallback;
    
}

Lottery.prototype = {
    createElement: function (tagName, attributes) {
        var ele = document.createElement(tagName);
        for (var key in attributes) {
            ele.setAttribute(key, attributes[key]);
        }
        return ele;
    },
    getTransparentPercent: function(ctx, width, height) {
        var imgData = ctx.getImageData(0, 0, width, height),
            pixles = imgData.data,
            transPixs = [];
        for (var i = 0, j = pixles.length; i < j; i += 4) {
            var a = pixles[i + 3];
            if (a < 128) {
                transPixs.push(i);
            }
        }
        return (transPixs.length / (pixles.length / 4) * 100).toFixed(2);
    },
    resizeCanvas: function (canvas, width, height) {
        canvas.width = width;
        canvas.height = height;
        canvas.getContext('2d').clearRect(0, 0, width, height);
    },
    drawPoint: function (x, y) {
        this.maskCtx.beginPath();
        var radgrad = this.maskCtx.createRadialGradient(x, y, 0, x, y, 30);
        radgrad.addColorStop(0, 'rgba(0,0,0,0.6)');
        radgrad.addColorStop(1, 'rgba(255, 255, 255, 0)');
        this.maskCtx.fillStyle = radgrad;
        this.maskCtx.arc(x, y, 30, 0, Math.PI * 2, true);
        this.maskCtx.fill();
        if (this.drawPercentCallback) {
            this.drawPercentCallback.call(null, this.getTransparentPercent(this.maskCtx, this.width, this.height));
        }
    },
    bindEvent: function () {
        var _this = this;
        var device = (/android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i.test(navigator.userAgent.toLowerCase()));
        var clickEvtName = device ? 'touchstart' : 'mousedown';
        var moveEvtName = device? 'touchmove': 'mousemove';
        if (!device) {
            var isMouseDown = false;
            document.addEventListener('mouseup', function(e) {
                isMouseDown = false;
            }, false);
        } else {
            document.addEventListener("touchmove", function(e) {
                if (isMouseDown) {
                    e.preventDefault();
                }
            }, false);
            document.addEventListener('touchend', function(e) {
                isMouseDown = false;
            }, false);
        }
        this.mask.addEventListener(clickEvtName, function (e) {
            isMouseDown = true;
            var docEle = document.documentElement;
            if (!_this.clientRect) {
                _this.clientRect = {
                    left: 0,
                    top:0
                };
            }
            var x = (device ? e.touches[0].clientX : e.clientX) - _this.clientRect.left + docEle.scrollLeft - docEle.clientLeft;
            var y = (device ? e.touches[0].clientY : e.clientY) - _this.clientRect.top + docEle.scrollTop - docEle.clientTop;
            _this.drawPoint(x, y);
        }, false);

        this.mask.addEventListener(moveEvtName, function (e) {
            if (!device && !isMouseDown) {
                return false;
            }
            var docEle = document.documentElement;
            if (!_this.clientRect) {
                _this.clientRect = {
                    left: 0,
                    top:0
                };
            }
            var x = (device ? e.touches[0].clientX : e.clientX) - _this.clientRect.left + docEle.scrollLeft - docEle.clientLeft;
            var y = (device ? e.touches[0].clientY : e.clientY) - _this.clientRect.top + docEle.scrollTop - docEle.clientTop;
            _this.drawPoint(x, y);
        }, false);
    },
    drawLottery: function () {
        this.background = this.background || this.createElement('canvas', {
            style: 'position:absolute;left:0;top:0;'
        });
        this.mask = this.mask || this.createElement('canvas', {
            style: 'position:absolute;left:0;top:0;'
        });

        if (!this.conNode.innerHTML.replace(/[\w\W]| /g, '')) {
            this.conNode.appendChild(this.background);
            this.conNode.appendChild(this.mask);
            this.clientRect = this.conNode ? this.conNode.getBoundingClientRect() : null;
            this.bindEvent();
        }

        this.backCtx = this.backCtx || this.background.getContext('2d');
        this.maskCtx = this.maskCtx || this.mask.getContext('2d');

        if (this.lotteryType == 'image') {
            var image = new Image(),
                _this = this;
            image.onload = function () {
                _this.width = this.width;
                _this.height = this.height;
                _this.resizeCanvas(_this.background, this.width, this.height);
                _this.backCtx.drawImage(this, 0, 0);
                _this.drawMask();
            }
            image.src = this.lottery;
        } else if (this.lotteryType == 'text') {
            this.width = this.width;
            this.height = this.height;
            this.resizeCanvas(this.background, this.width, this.height);
            this.backCtx.save();
            this.backCtx.fillStyle = '#FFF';
            this.backCtx.fillRect(0, 0, this.width, this.height);
            this.backCtx.restore();
            this.backCtx.save();
            var fontSize = 30;
            this.backCtx.font = 'Bold ' + fontSize + 'px Arial';
            this.backCtx.textAlign = 'center';
            this.backCtx.fillStyle = '#F60';
            this.backCtx.fillText(this.lottery, this.width / 2, this.height / 2 + fontSize / 2);
            this.backCtx.restore();
            this.drawMask();
        }
    },
    drawMask: function() {
        this.resizeCanvas(this.mask, this.width, this.height);
        if (this.coverType == 'color') {
            this.maskCtx.fillStyle = this.cover;
            this.maskCtx.fillRect(0, 0, this.width, this.height);
            this.maskCtx.globalCompositeOperation = 'destination-out';
        } else if (this.coverType == 'image'){
            var image = new Image(),
                _this = this;
            image.onload = function () {
                _this.maskCtx.drawImage(this, 0, 0);
                _this.maskCtx.globalCompositeOperation = 'destination-out';
            }
            image.src = this.cover;
        }
    },
    init: function (lottery, lotteryType) {
        this.lottery = lottery;
        this.lotteryType = lotteryType || 'image';
        this.drawLottery();
    }
}
    
    
    
    </script>
</head>
<body>
<!--头部-->
<jsp:include page="../app_head.jsp" flush="true"></jsp:include>
<!--头部结束-->
  <!--   <button id="freshBtn">刷新彩票</button><label>已刮开 <span id="drawPercent">0%</span> 区域。</label>-->

	<div class="guahongbao-bg"><img src="<%=ctxPath %>/resources/images/guajiang/head01.jpg" width="100%" /></div>
	<div class="guahongbao-bg2">
	  <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td class="guahongbao-guajiang-l">&nbsp;</td>
	    <td class="guahongbao-guajiang-m">
	        <div class="top"></div>
	        <div class="m" id="lotteryContainer" ></div>
	        <div class="bottom"></div>
	    </td>
	    <td class="guahongbao-guajiang-r">&nbsp;</td>
	  </tr>
	</table>
	
	</div>

	<!-- 聚友惠详情 -->
	<!-- 聚友惠详情 结束 -->
	
	
	
	<!-- 聚友惠评论 -->
	<!-- 聚友惠评论 结束 -->
	
	<!--活动规则-->
	<div class="ju-listblock2">
		<p><span class="guahongbao-guize">活动规则</span></p>
		<ol>
		  <li class="ju-guize">每参与一次游戏即可获得刮刮卡红包一张</li>
		  <li class="ju-guize">奖品包括流量包70M流量包、150M流量包</li>
		  <li class="ju-guize">流量包当月发放，下月生效</li>
		</ol>
	    <p class="ju-guize" style="text-indent:2.2em;">注：本活动最终解释权归CMCC-FREE</p>
	</div>
	<!--活动规则-->
	
	<table width="90%" border="0" align="center" cellpadding="5" cellspacing="5">
	  <tr>
	    <td class="gua-btn1" width="50%" onclick="window.location.href='<%=ctxPath %>/index'" >不玩了</td>
	    <td class="gua-btn2" onclick="window.location.href='<%=ctxPath %>/jianianhua/youxi'" >再战一次</td>
	  </tr>
	</table>
    <script>
	var mobile ='<%=mobile %>';
	var lotteryContainer = $('#lotteryContainer');

     //   window.onload = function () {
    /** $(function(){
            var lottery = new Lottery('lotteryContainer',ctxPaths+ '/resources/images/guajiang/cover.png', 'image', 250, 100, null);
            lottery.init(ctxPaths+ '/resources/images/guajiang/prize02.png', 'image');
            var drawPercentNode = document.getElementById('drawPercent');

     //       document.getElementById('freshBtn').onclick = function() {
      //          drawPercentNode.innerHTML = '0%';
      //          lottery.init(getRandomStr(10), 'text');
      //      }


            function drawPercent(percent) {
             //   drawPercentNode.innerHTML = percent + '%';
               
            }
        })

        function getRandomStr(len) {
            var text = "";
            var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            for( var i=0; i < len; i++ )
                text += possible.charAt(Math.floor(Math.random() * possible.length));
            return text;
        }
**/
    
      $(function(){
    	 var jsonData={"mobile":mobile,"activityId":8};
				 jQuery.ajax({
			            url : ctxPaths+'/jianianhua/getPrize',
			            type : "POST",
			   	    	async: false,
			            dataType : "json",
			            data : jsonData,
			          	 success : function(data) {
				         		if(data.CODE=="TRUE"){
				         			   var lottery = new Lottery('lotteryContainer',ctxPaths+ '/resources/images/guajiang/cover.png', 'image', 250, 100, null);
				         			if(data.isPrize=="70"){
            							lottery.init(ctxPaths+ '/resources/images/guajiang/prize02.png', 'image');
            						}else if (data.isPrize=="150"){
            							lottery.init(ctxPaths+ '/resources/images/guajiang/prize03.png', 'image');            							
            						}else if(data.isPrize=="0"){
            							lottery.init(ctxPaths+ '/resources/images/guajiang/prize01.png', 'image');  
            							       							
            						}else if(data.isPrize=="1"){ 
            							lotteryContainer.html('<table> <tr><td>非移动号码，很抱歉，只能送人了，赠送奖品：150M流量包</td></tr><tr><td> 移动号码：<input type="text" id="ownermobile" class="guaguale-input"  /> <input type="button" value="赠送" onclick="zengsong('+data.prizeType+','+data.jianianhuaId+')"> </td></tr></table>');
            						}else if(data.isPrize=="2"){ 
            							lotteryContainer.html('<table> <tr><td>非移动号码，很抱歉，只能送人了，赠送奖品：70M流量包</td></tr><tr><td> 移动号码：<input type="text" id="ownermobile" class="guaguale-input" /><input type="button" value="赠送" onclick="zengsong('+data.prizeType+','+data.jianianhuaId+')"> </tr></table>');
            						}
				         		}
				         }
			         });
    })
    
    
    function zengsong(prizeType,jianianhuaId){
    	var ownermobile = $("#ownermobile").val();
    
		 var jsonData={"ownermobile":ownermobile,"activityId":"8","jianianhuaId":jianianhuaId,"prizeId":prizeType};
				 jQuery.ajax({
			            url : ctxPaths+'/jianianhua/zengsong',
			            type : "POST",
			   	    	async: false,
			            dataType : "json",
			            data : jsonData,
			          	 success : function(data) {
				         		if(data.CODE=="TRUE"){
				         			lotteryContainer.html('<table algin="middle"> <tr><td>赠送成功</td></tr></table>');
				         		}
				         }
			         });
    }
    </script>
    
    <jsp:include page="../app_footer.jsp" flush="true"></jsp:include>
</body>
</html>
