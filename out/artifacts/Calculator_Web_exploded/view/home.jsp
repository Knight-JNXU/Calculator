<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="../images/login.js"></script>
    <link href="../css/login2.css" rel="stylesheet" type="text/css"/>
    <script src="../js/highcharts.js"></script>
    <script src="../js/mypiechart.js"></script>
</head>
<body style="height: auto;">
<h1>HOME<sup>V1.0</sup></h1>
<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);"
                                           tabindex="7" style="margin-left: 20px;">概况</a>
            <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8" style="margin-left: 30px;">基本</a>
            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: auto;">

        <input id="chlist" type="hidden" name="characterList" value='${characters }'/>
        <div id="container" style="width: 300px; height: 300px; margin: 0 auto"></div>

        <div class="web_login" id="web_login">
            <div class="login-box">
                <div class="login_form">

                </div>
            </div>

        </div>
    </div>

    <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">

        <div class="web_login" style="height: 180px;">
            <input type="hidden" name="to" value="reg"/>
            <input type="hidden" name="did" value="0"/>
            <ul class="reg_form" id="reg-ul">
                <div id="userCue" class="cue">收支</div>
                <div style="margin-left: 70px;">
                    <table style="text-align:center;margin:auto;
                    height: auto;font-size: 14px;margin: 5px;">
                        <c:forEach items="${payList}" var="item">
                            <tr>
                                <td>${item.fromName}</td>
                                <td>-></td>
                                <td>${item.toName}</td>
                                <td>:</td>
                                <td>${item.money}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <input type="button" value="Details"
                       style="width:150px;margin-top: 20px;margin-left: 70px;"
                       class="button_blue"
                       onclick="window.location.href='/characterController/showDetails'"/>
            </ul>
        </div>
    </div>
    <!--注册end-->

</div>
</body>
</html>