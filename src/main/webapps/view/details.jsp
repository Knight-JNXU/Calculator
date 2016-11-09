<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="zh" class="no-js">
<head>
    <title>details</title>
    <link rel="Shortcut Icon" href="../images/icon1.jpg">
</head>
<script type="text/javascript" src="../js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/dataTables.jqueryui.min.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="../css/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../js/details.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="../css/normalize.css"/>
<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.2.0/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/default.css">
<link rel="stylesheet" type="text/css" href="../css/set2.css"/>
<link rel="stylesheet" type="text/css" href="../css/mystyle.css"/>
<link href="../css/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
<script src="../js/jquery.searchableSelect.js"></script>
<script src="../js/myjs.js"></script>
<body>
<input type="button" class="myButton" value="Home"
       onclick="window.location='<%=request.getContextPath()%>/characterController/getAllCharacter'"
       style="margin-left: 20px;margin-top: 20px;margin-bottom: 20px;">
<input type="button" class="myButton" value="Logout"
       onclick="window.location='<%=request.getContextPath()%>/userController/logout'"
       style="margin-left: 20px;margin-top: 20px;margin-bottom: 20px;">
<input type="button" class="myButton" value="Manager"
       onclick="window.location='/userController/goManager'"
       style="margin-left: 20px;margin-top: 20px;margin-bottom: 20px;">
<h4>明细表</h4>
<table id="table1" class="display" cellspacing="0" width="100%"
       style="font-family: Arial;text-align: center;">
    <thead>
    <tr>
        <th>Name</th>
        <th>Expenditure</th>
        <th>Date</th>
        <th>Author</th>
        <th>Remarks</th>
        <th>Operate</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Name</th>
        <th>Expenditure</th>
        <th>Date</th>
        <th>Author</th>
        <th>Remarks</th>
        <th>Operate</th>
    </tr>
    </tfoot>
    <tbody>
    <c:forEach items="${characterlist}" var="item">
        <c:forEach var="i" items="${item.payDateList }">
            <tr>
                <td>${item.name}</td>
                <td>${i.pay}</td>
                <td>${i.date}</td>
                <td>${i.author}</td>
                <td>${i.remark}</td>
                <td><input type="button" value="Delete" class="myButton"
                           style="height:auto;padding: 3 2;"
                           onclick="deleteButton('${item.name}','${i.pay}','${i.date}','${i.author}','${i.remark}',
                                   '/characterController/deleteRecord')"></td>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>
<h4>总价</h4>
<table id="table2" class="display" cellspacing="0" width="100%"
       style="font-family: Arial;text-align: center;">
    <thead>
    <tr>
        <th>Name</th>
        <th>Total</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Name</th>
        <th>Total</th>
    </tr>
    </tfoot>
    <tbody>
    <tr>
        <td>TOTAL</td>
        <td>${total}</td>
    </tr>
    <tr>
        <td>AVG</td>
        <td>${avg}</td>
    </tr>
    <c:forEach items="${characterlist}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.total}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="htmleaf-container">
    <h4>操作角色</h4>
    <form action="<%=request.getContextPath()%>/characterController/operateCharacter" method="post" onsubmit="return checkOpChar();">
        <section class="jq22-content bgcolor-1">
        <span class="input input--nao">
					<input class="input__field input__field--nao" type="text" id="input-1" name="characterName"/>
					<label class="input__label input__label--nao" for="input-1">
						<span class="input__label-content input__label-content--nao">UserName</span>
					</label>
					<svg class="graphic graphic--nao" width="300%" height="100%" viewBox="0 0 1200 60"
                         preserveAspectRatio="none">
						<path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
					</svg>
				</span>
        </section>
        <input type="hidden" id="operateType" name="operateType">
        <input type="submit" class="myButton" value="ADD" style="margin-left:25px; margin-top: 10px;"
               onclick="document.getElementById('operateType').value='add'">
        <input type="submit" class="myButton" value="DELETE" style="margin-left:220px; margin-top: 10px;"
               onclick="document.getElementById('operateType').value='delete'"><br><br>
    </form>
</div>
<script src="../js/classie.js"></script>
<script>
    (function () {
        if (!String.prototype.trim) {
            (function () {
                // Make sure we trim BOM and NBSP
                var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
                String.prototype.trim = function () {
                    return this.replace(rtrim, '');
                };
            })();
        }

        [].slice.call(document.querySelectorAll('input.input__field')).forEach(function (inputEl) {
            // in case the input is already filled..
            if (inputEl.value.trim() !== '') {
                classie.add(inputEl.parentNode, 'input--filled');
            }

            // events:
            inputEl.addEventListener('focus', onInputFocus);
            inputEl.addEventListener('blur', onInputBlur);
        });

        function onInputFocus(ev) {
            classie.add(ev.target.parentNode, 'input--filled');
        }

        function onInputBlur(ev) {
            if (ev.target.value.trim() === '') {
                classie.remove(ev.target.parentNode, 'input--filled');
            }
        }
    })();
</script>
<div style="height: 300px;">
    <h4>增加支出</h4>
    插入多条记录时,多条paymoney、多条remarks用 ";" (英文分号) 隔开
    <form action="<%=request.getContextPath()%>/characterController/addPayMoney" method="post" onsubmit="return checkAddPaySub();">
        <select name="username" id="addPayUserName">
            <option value="username">username</option>
            <c:forEach items="${characterlist}" var="item">
                <option value="${item.name}">${item.name}</option>
            </c:forEach>
        </select>
        <input style="height: 32px; margin-left: 15px;"  placeholder="paymoney" type="text" name="paymoney" id="payValues"/>
        <input style="height: 32px; margin-left: 15px;"  placeholder="remarks" type="text" name="remarks" id="remarksValues"/>
        <input type="submit" class="myButton" value="ADD" style="margin-left: 30px;margin-top: 10px;padding: 3px 4px;">
    </form>
</div>
<script>
    $(function () {
        $('select').searchableSelect();
    });
</script>
</body>
</html>
