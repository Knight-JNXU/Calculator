<%--
  Created by IntelliJ IDEA.
  User: Knigh
  Date: 2016/9/3
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <!--theme-style-->
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/mystyle.css" rel="stylesheet" type="text/css" media="all" />
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Fashion Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--fonts-->
    <link href='http://fonts.useso.com/css?family=Roboto:400,300,100,500,700,900' rel='stylesheet' type='text/css'>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
            });
        });
    </script>
</head>
<body>
<!--header-->
<div class="header" id="home">
    <div class="container">
        <div class="header-top">
            <div class="logo">
                <a href="#"><img src="images/logo.png" alt="" ></a>
            </div>
            <div class="top-nav">
                <span class="menu"> </span>
                <script>
                    $("span.menu").click(function(){
                        $(".top-nav ul").slideToggle(500, function(){
                        });
                    });
                </script>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="required">
            <%--<input type="submit" value="ABOUT" onclick="window.location='<%=request.getContextPath()%>/userController/about'">--%>
            <input type="submit" value="ABOUT" onclick="window.location='/htmls/about.html'">
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
<!---->
<div class="banner">
    <div class="banner-matter">
        <h2>公共财物管理系统</h2>
        <a href="<%=request.getContextPath()%>/loginController/go" class="down">Go</a>
    </div>
</div>
<!--footer-->
<div class="footer">
    <script type="text/javascript">
        $(document).ready(function() {
            $().UItoTop({ easingType: 'easeOutQuart' });

        });
    </script>
</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
