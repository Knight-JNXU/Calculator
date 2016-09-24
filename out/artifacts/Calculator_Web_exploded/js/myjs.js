/**
 * Created by Knigh on 2016/9/15.
 */
function chekcSubmit() {
    var u = $('#u').val();
    var p1 = $('#p').val();
    var p2 = $('#p2').val();
    if(p1 != p2 || u=="" || typeof(u)=="undefined"){
        alert("输入非法！");
        return false;
    }
    return true;
}
function deleteButton(charactername, m, d, a, u) {
    $.ajax({
        type:'POST',
        url:u,
        dataType:'json',
        data:{charactername:charactername,
            money:m, date:d, author:a}
    });
    window.location.reload();
}

