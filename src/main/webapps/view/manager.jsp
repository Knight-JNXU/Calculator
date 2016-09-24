<%--
  Created by IntelliJ IDEA.
  User: Knigh
  Date: 2016/9/16
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>manager</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="../images/login.js"></script>
    <link href="../css/login2.css" rel="stylesheet" type="text/css"/>
</head>
<h1>超级管理员<sup>V1.0</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch">
            <a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);"
               style="margin-left: 90px;" tabindex="7">功能</a>
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: auto; padding-bottom: 30px;">

        <!--登录-->
        <div class="web_login" id="web_login">


            <div class="login-box">

                <div class="login_form" style="margin-left: 110px;">
                    <input type="button" value="添加用户"
                           style="width:150px;"
                           class="button_blue"
                           onclick="window.location='/userController/goAddUser'"/>

                    <input type="button" value="HOME"
                           style="width:150px;margin-top: 20px;"
                           class="button_blue"
                           onclick="window.location='/characterController/getAllCharacter'"/>

                    <input type="button" value="DETAIL"
                           style="width:150px;margin-top: 20px;"
                           class="button_blue"
                           onclick="window.location='/characterController/showDetails'"/>

                    <input type="button" value="清   空"
                           style="width:150px;margin-top: 20px;"
                           class="button_blue"
                           onclick="window.location='/characterController/clear'"/>

                    <input type="button" value="注   销"
                           style="width:150px;margin-top: 20px;"
                           class="button_blue"
                           onclick="window.location='/userController/logout'"/>
                </div>

            </div>

        </div>
        <!--登录end-->
    </div>

</div>
</body>
</html>
