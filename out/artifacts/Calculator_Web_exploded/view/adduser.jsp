<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="../images/login.js"></script>
    <script type="text/javascript" src="../js/myjs.js"></script>
    <link href="../css/login2.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>添加用户<sup>V1.0</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch"><a style="margin-left: 90px;" class="switch_btn_focus" id="switch_qlogin"
                                           href="javascript:void(0);"
                                           tabindex="7">添加用户</a>
            <div class="switch_bottom" id="switch_bottom"
                 style="margin-left: 90px;position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 320px;">

        <!--登录-->
        <div class="web_login" id="web_login">


            <div class="login-box">

                <div class="login_form">
                    <form action="/userController/addUser" name="loginform" accept-charset="utf-8" id="login_form"
                          class="loginForm" method="post" onsubmit="return chekcSubmit();"><input type="hidden" name="did"
                                                                                          value="0"/>
                        <input type="hidden" name="to" value="log"/>
                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>
                            <div class="inputOuter" id="uArea">

                                <input type="text" id="u" name="user" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>
                            <div class="inputOuter" id="pArea">

                                <input type="password" id="p" name="passwd" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea2">
                            <label class="input-tips" for="p2">密码：</label>
                            <div class="inputOuter" id="pArea2">

                                <input type="password" id="p2" name="password2" class="inputstyle"/>
                            </div>
                        </div>

                        <div style="padding-left:50px;margin-top:20px;margin-left: 15px;"><input type="submit"
                                                                                                 value="ADD"
                                                                                                 style="width:150px;"
                                                                                                 class="button_blue"/>
                        </div>
                    </form>
                </div>

            </div>

        </div>
        <!--登录end-->
    </div>

</div>
</body>

</html>