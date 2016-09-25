<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>about</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="../images/login.js"></script>
    <link href="../css/login2.css" rel="stylesheet" type="text/css"/>
    <link href="../css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>ABOUT<sup>V1.0</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);"
                                           tabindex="7" style="margin-left: 80px;">AUTHOR</a>
            <div class="switch_bottom" style="position: absolute;
            width: 64px; left: 0px;margin-left: 83px;"></div>
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: auto; padding-bottom: 30px;">

        <!--登录-->
        <div class="web_login" id="web_login">


            <div class="login-box">

                <div class="login_form">
                    <input type="hidden" name="did" value="0"/>
                    <input type="hidden" name="to" value="log"/>
                    <div class="myportrait"
                         style="background-image: url(../images/me2.jpg);"></div>

                </div>
                <div class="aboutContent">
                    Name : Knight_JXNU<br>
                    Programming Enthusiasts<br>
                    Skill : Java Web、MySQL<br>
                    Funtion : 共同财物管理<br>
                    Version : 1.0
                </div>

            </div>

        </div>
        <!--登录end-->
    </div>


</div>
</body>
</html>