/**
 * Created by Knigh on 2016/9/15.
 */

/**
 * 角色操作过滤
 * @returns {boolean}
 */
function checkOpChar() {
    var characterName = $('#input-1').val();
    if(characterName=="" || typeof(characterName)=="undefined"){
        alert("UserName is null!");
        return false;
    }
    return true;
}

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
/**
 * 删除按钮
 * @param charactername
 * @param m
 * @param d
 * @param a
 * @param u
 */
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
/**
 * 返回 str 中 ; 的数量
 * @param str
 * @returns {number}
 */
function contansSemicolon(str){
    var chars = str.match(/./g);
    var sum = 0;
    for(var i=0; i<chars.length; i++){
        if(chars[i] == ';'){
            sum++;
        }
    }
    return sum;
}
/**
 * add paymony 过滤
 * @returns {boolean}
 */
function checkAddPaySub() {
    var username = $('#addPayUserName').val();
    if(username == "username"){
        alert("username is null!")
        return false;
    }
    var payValue = $('#payValues').val();
    if(payValue == ""){
        alert("pay money is null!");
        return false;
    }
    var remarks = $('#remarksValues').val();
    if(payValue.indexOf(";") != -1){
        if(remarks == ""){
            alert("remarks数量和paymoney数量不一致!");
            return false;
        }
        var payVS = contansSemicolon(payValue);
        var remarkS = contansSemicolon(remarks);
        if(remarkS != payVS){
            alert("remarks数量和paymoney数量不一致!");
            return false;
        }
    }
    return true;
}

