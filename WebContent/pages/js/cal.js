
 //获取当前时间的前12个月的开始日期和结束日期  
 var getStartEndmonth = function() {  
     var d = new Date();  
     var result = [];  
     for(var i = 0; i < 12; i++) {  
         d.setMonth(d.getMonth() - 1);  
         var m = d.getMonth() + 1;  
         m = m < 10 ? "0" + m : m;  
         //在这里可以自定义输出的日期格式  
         if(i==0){
        	 result.push(d.getFullYear() + "-" + m+"-"+getlastday(d.getFullYear(),m));  
         }else if(i==11){
        	 result.push(d.getFullYear() + "-" + m+"-01");  
        	 
         }
     }  
     
     
     return result.reverse();  
} 
 //获取当前月的最后一天
 function getlastday(year,month)     
 {     
	 month=month>9?month:"0"+month;
  var new_year = year;    //取当前地年份     
  var new_month = month++;//取下一个月地第一天，方便计算（最后一天不固定）     
  if(month>12)            //如果当前大于12月，则年份转到下一年     
  {     
   new_month -=12;        //月份减     
   new_year++;            //年份增     
  }     
  var new_date = new Date(new_year,new_month,1);                //取当年当月中地第一天     
  return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期     
 }   
 
 console.log(getStartEndmonth());
var myChart = echarts.init(document.getElementById('contentShow1'));
function getVirtulData(startEnd) {
    var date = +echarts.number.parseDate(startEnd[0]);
   var end = +echarts.number.parseDate(startEnd[1]);
    var dayTime = 3600 * 24 * 1000;
    var data = [];
    for (var time = date; time < end; time += dayTime) {
        data.push([
            echarts.format.formatTime('yyyy-MM-dd', time),
            Math.floor(Math.random() * 10000)
        ]);
    }
    return data;
}
var data = getVirtulData(getStartEndmonth());
  var start_=getStartEndmonth()[0];
  var end_=getStartEndmonth()[1];
 console.log(start_);
option = {
    backgroundColor: '#404a59',

    title: {
        top: 30,
        text: '2016年某人每天的步数',
        subtext: '数据纯属虚构',
        left: 'center',
        textStyle: {
            color: '#fff'
        }
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        top: '30',
        left: '100',
        data:['步数', 'Top 12'],
        textStyle: {
            color: '#fff'
        }
    },
    calendar: [{
        top: 100,
        left: 'center',
        range: [start_,end_],
        splitLine: {
            show: true,
            lineStyle: {
                color: '#000',
                width: 4,
                type: 'solid'
            }
        },
        yearLabel: {
            formatter: '  1st',
            textStyle: {
                color: '#fff'
            }
        },
        itemStyle: {
            normal: {
                color: '#323c48',
                borderWidth: 1,
                borderColor: '#111'
            }
        },
        dayLabel: {
            firstDay: 1, // 从周一开始
            nameMap: 'cn'
            },
        monthLabel:{
                 nameMap: 'cn'
            }
    }
        /*, {
        top: 340,
        left: 'center',
        range: ['2016-07-01', '2016-12-31'],
        splitLine: {
            show: true,
            lineStyle: {
                color: '#000',
                width: 4,
                type: 'solid'
            }
        },
        yearLabel: {
            formatter: '{start}  2nd',
            textStyle: {
                color: '#fff'
            }
        },
        itemStyle: {
            normal: {
                color: '#323c48',
                borderWidth: 1,
                borderColor: '#111'
            }
        }
    }*/],
    series : [
        {
            name: '步数',
            type: 'scatter',
            coordinateSystem: 'calendar',
            data: data,
            symbolSize: function (val) {
                return val[1] / 500;
            },
            itemStyle: {
                normal: {
                    color: '#ddb926'
                }
            }
        },
        {
            name: '步数',
            type: 'scatter',
            coordinateSystem: 'calendar',
            calendarIndex: 1,
            data: data,
            symbolSize: function (val) {
                return val[1] / 500;
            },
            itemStyle: {
                normal: {
                    color: '#ddb926'
                }
            }
        },
        {
            name: 'Top 12',
            type: 'effectScatter',
            coordinateSystem: 'calendar',
            calendarIndex: 1,
            data: data.sort(function (a, b) {
                return b[1] - a[1];
            }).slice(0, 12),
            symbolSize: function (val) {
                return val[1] / 500;
            },
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            itemStyle: {
                normal: {
                    color: '#f4e925',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
        },
        {
            name: 'Top 12',
            type: 'effectScatter',
            coordinateSystem: 'calendar',
            data: data.sort(function (a, b) {
                return b[1] - a[1];
            }).slice(0, 12),
            symbolSize: function (val) {
                return val[1] / 500;
            },
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            itemStyle: {
                normal: {
                    color: '#f4e925',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
        }
    ]
};

myChart.setOption(option);

var a=[123,121];
var b=[];
b.push("aaa");
b.push("ccc");
/*console.log(a);
console.log(b);*/