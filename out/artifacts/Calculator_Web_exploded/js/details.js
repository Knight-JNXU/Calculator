/**
 * Created by Knigh on 2016/9/6.
 */
// $(document).ready(function() {
//     $('#example').DataTable();
// } );
$(document).ready(function drawTable() {
    /*alert("abc:" + $("#chlist").val());
    var list = eval($("#chlist").val());
    var htmls='<table id="example" class="display" cellspacing="0" width="100%"'
        +'style="font-family: Arial;text-align: center;">'
        +'<thead><tr><th>Name</th><th>Expenditure</th> <th>Date</th></tr></thead>'
        +'<tfoot><tr><th>Name</th><th>Expenditure</th> <th>Date</th></tr></tfoot>';
    for(var i=0; i<list.length; i++){
        htmls += '<tbody><tr><td>'+list.name+'<td>';
        var mlist = list[i].moneyList;
        for(var j=0; j<mlist.length; j++){
            htmls += '<td>'+mlist[j]+'</td>';
        }
        htmls += '<td></td>';
        htmls += '</tr></tbody>';
    }
    alert(htmls);
    $('#mytable').innerHTML = htmls;*/
    $('#table1').DataTable({iDisplayLength :100});
    $('#table2').DataTable();
    // var tableList = $("[id^='example_']");
    /*for(var i=0; i<tableList.length; i++){
        var id = tableList[i].attr("id");
        // $('#'+).DataTable();
        tableList[i].DataTable();
    }*/
});
