// ============================================================== 
// Bar chart option
// ============================================================== 
var myChart = echarts.init(document.getElementById('bar-chart'));

// specify chart configuration item and data
option = {
    tooltip : {
        trigger: 'axis'
    },

    
    legend: {
 
        data:['Quarto 1','Quarto 2'],
        textStyle: {
                    color: "white"
                }
        
    },
    toolbox: {
        show : true,
        feature : {
            
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    color: ["#55ce63", "#009efb","#3a5791","#4b4b59"],
    calculable : true,
    xAxis : [
        {

            axisLabel: {
            textStyle: {
                color: "white"
            }
        },
            type : 'category',
            data : ['Nov','Dez','Jan'],
        }
    ],
    yAxis : [
        {
            
            
            type : 'value',
            axisLabel : {
                 textStyle: {
                    color: "white"
                },
            formatter: '{value} €'
           }
        }
    ],
    series : [
        { 
            name:'Quarto 1',
            type:'bar',
            data:[200, 440, 500],

                
        },
        {
            name:'Quarto 2',
            type:'bar',
            data:[194, 511, 654],

            
          
        },
    ]
};
                    

// use configuration item and data specified to show chart
myChart.setOption(option, true), $(function() {

            function resize() {
                setTimeout(function() {
                    myChart.resize()
                }, 100)
            }
            $(window).on("resize", resize), $(".sidebartoggler").on("click", resize)
        });

