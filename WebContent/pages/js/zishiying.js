 var myChart= echarts.init(document.getElementById('WorldMap'));
//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
var resizeWorldMapContainer = function () {
	console.log("width:"+window.innerWidth+'px'+"----height:"+window.innerHeight+'px');
	$("#WorldMap").css("width",window.innerWidth+'px');
	$("#WorldMap").css("height",window.innerHeight+'px');
   
};
//设置容器高宽
resizeWorldMapContainer();
// 基于准备好的dom，初始化echarts实例

// 指定图表的配置项和数据
var option = {
    title: {
        text: 'ECharts 入门示例'
    },
    tooltip: {},
    legend: {
        data:['销量'],
    /*    height:$("#WorldMap").css("height"),
        width: $("#WorldMap").css("width"),*/
    },
    xAxis: {
        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
    }]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

//用于使chart自适应高度和宽度
window.onresize = function () {
    //重置容器高宽
    resizeWorldMapContainer();
    myChart.resize();
};