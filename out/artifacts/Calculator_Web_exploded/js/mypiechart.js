/**
 * Created by Knigh on 2016/9/4.
 */
// $.get("/characterController/getAllCharacter");
function getRandomColor(){
    var colorElements = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f";
    var colorArray = colorElements.split(",");
    var color ="#";
    for(var i =0;i<6;i++){
        color+=colorArray[Math.floor(Math.random()*16)];
    }
    return color;
}
$(document).ready(function () {
    var chart = {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false
    };
    var title = {
        text: '饼状图'
    };
    var tooltip = {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    };
    var plotOptions = {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: false
            },
            showInLegend: true
        }
    };
    var colors = [];
    var list = eval($("#chlist").val());
    var mydata = [];
    var i;
    for(i=0; i<list.length-1; i++){
        mydata.push([list[i].name, list[i].total]);
        colors.push(getRandomColor());
    }
    mydata.push({name:list[i].name,y:list[i].total,sliced: true,selected: true});
    colors.push(getRandomColor());
    perData = [list[0].name,list[0].total];
    var series = [{
        type: 'pie',
        name: 'Browser share',
        data: mydata
    }];

    var json = {};
    json.chart = chart;
    json.title = title;
    json.tooltip = tooltip;
    json.series = series;
    json.plotOptions = plotOptions;
    json.colors = colors;
    $('#container').highcharts(json);
});

