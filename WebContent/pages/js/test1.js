"use strict";//严格模式
$(function() {
    // 散点图
    function scatter_chart(element) {
        // 初始化图形
        var myChart = echarts.init(document.getElementById(element));
        var data = {
            data1: [
                ['2016-01-03', 1]
            ],
            data2: [
                ['2016-01-01', 1],
                ['2016-01-10', 1],
                ['2016-02-01', 1],
            ],
            data3: [
                ['2016-01-010', 1],
                ['2016-02-10', 1],
                ['2016-03-01', 1],
            ]
        };
        // 获取日期数据
        // function getVirtulData(year) {
        //     year = year || '2017';
        //     var date = +echarts.number.parseDate(year + '-01-01');
        //     var end = +echarts.number.parseDate((+year + 1) + '-01-01');
        //     var dayTime = 3600 * 24 * 1000;
        //     var data = [];
        //     for (var time = date; time < end; time += dayTime) {
        //         data.push([
        //             // echarts.format.formatTime('yyyy-MM-dd', time),
        //             '2016-01-01',
        //             Math.floor(Math.random() * 10000)
        //         ]);
        //     }
        //     return data;
        // }

        // var data = getVirtulData(2016);
        var option = {
            backgroundColor: '#404a59',
            title: {
                top: 30,
                text: '开关状态',
                subtext: null,
                left: 'center',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '60',
                left: 'center',
                data: ['终端套利', '有价卡', '客户欠费'],
                textStyle: {
                    color: '#fff'
                }
            },
            calendar: [{
                top: 100,
                left: 'center',
                range: ['2016'],
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#000',
                        width: 4,
                        type: 'solid'
                    }
                },
                dayLabel: { //左侧星期轴
                    show: true, //是否显示
                    nameMap: 'cn'
                },
                yearLabel: { //左侧年份轴
                    formatter: '{start}  1st',
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
            }],
            series: [{
                    name: '终端套利',
                    type: 'scatter',
                    coordinateSystem: 'calendar',
                    encode: {
                        tooltip: [0] // 表示维度 0会在 tooltip 中显示。
                    },
                    data: data.data1,
                    symbolSize: 10,
                    cursor: 'defult',
                    itemStyle: {
                        normal: {
                            color: 'blue'
                        }
                    }
                }, {
                    name: '有价卡',
                    type: 'scatter',
                    coordinateSystem: 'calendar',
                    encode: {
                        tooltip: [0] // 表示维度 0会在 tooltip 中显示。
                    },
                    data: data.data2,
                    symbolSize: 10,
                    itemStyle: {
                        normal: {
                            color: '#ddb926'
                        }
                    }
                },
                {
                    name: '客户欠费，有价卡',
                    type: 'scatter',
                    coordinateSystem: 'calendar',
                    encode: {
                        tooltip: [0] // 表示维度 0会在 tooltip 中显示。
                    },
                    data: data.data3,
                    symbolSize: 10,
                    itemStyle: {
                        normal: {
                            color: 'green'
                        }
                    }
                },
                // {
                //     name: '养卡套利',
                //     type: 'effectScatter',
                //     coordinateSystem: 'calendar',
                //     data: data2,
                //     symbolSize: 10,
                //     showEffectOn: 'render',
                //     rippleEffect: {
                //         brushType: 'stroke'
                //     },
                //     hoverAnimation: true,
                //     itemStyle: {
                //         normal: {
                //             color: '#ccc',
                //             shadowBlur: 10,
                //             shadowColor: '#333'
                //         }
                //     },
                //     zlevel: 1
                // }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
    scatter_chart('scatterCtn'); //调用

    function gantt_chart(element) {
        var myChart = echarts.init(document.getElementById(element));
        var option = {
            title: {
                text: 'test',
                x: 'center'
            },
            calculable: false,
            tooltip: {
                show: true,
                axisPointer: {
                    type: 'shadow'
                },
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar', 'stack', 'tiled'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                },
                formatter: function(params) {
                    return params[1] + '<br/>耗时 : ' + params[2] + '天';
                }
            },
            axis: {
                splitLine: {
                    show: false
                },
                splitArea: {
                    show: false
                }
            },
            // calculable: true,
            yAxis: [{
                type: "category",
                splitLine: { show: true },
                data: ["项目确定", "问卷设计", "试访", "问卷确定", "实地执行", "数据录入", "数据分析"]
            }],
            xAxis: [{
                type: 'value',
                // data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                axisLabel: {
                    formatter: function(value) {
                        var date = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];
                        return date[value];
                    }
                },
            }],
            series: [{
                    name: "辅助",
                    type: "bar",
                    stack: "总",
                    itemStyle: {
                        normal: {
                            barBorderColor: 'rgba(0,0,0,0)',
                            color: 'rgba(0,0,0,0)'
                        },
                        emphasis: {
                            barBorderColor: 'rgba(0,0,0,0)',
                            color: 'rgba(0,0,0,0)'
                        }
                    },
                    data: [0, 1, 2, 3, 4, 5, 6]
                },
                {
                    name: "项目确定",
                    type: "bar",
                    stack: "总",
                    data: [1, 0, 0, 0, 0, 0, 0]
                },
                {
                    name: "问卷设计",
                    type: "bar",
                    stack: "总",
                    data: [0, 1, 0, 0, 0, 0, 0]
                },
                {
                    name: "试访",
                    type: "bar",
                    stack: "总",
                    data: [0, 0, 2, 0, 0, 0, 0]
                },
                {
                    name: "问卷确定",
                    type: "bar",
                    stack: "总",
                    data: [0, 0, 0, 1, 0, 0, 0]
                },
                {
                    name: "实地执行",
                    type: "bar",
                    stack: "总",
                    data: [0, 0, 0, 0, 4, 0, 0]
                },
                {
                    name: "数据录入",
                    type: "bar",
                    stack: "总",
                    data: [0, 0, 0, 0, 0, 1, 0]
                },
                {
                    name: "数据分析",
                    type: "bar",
                    stack: "总",
                    data: [0, 0, 0, 0, 0, 0, 3]
                }
            ]
        };
        myChart.setOption(option);
    }
    // gantt_chart('ganttCtn');
    $('#scatterWrap').niceScroll('#scatterCtn');
    // 为断点条形图添加hover事件
    $('#ganttWrap ul').on('mouseover', 'li', function() {
        var n = $(this).index() + 1;
        var str = '关';
        if (!$(this).hasClass('off')) {
            str = '开';
        }
        $(this).attr('title', n + '月' + str);
    });
});