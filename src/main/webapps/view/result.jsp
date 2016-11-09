<%--
  Created by IntelliJ IDEA.
  User: Knigh
  Date: 2016/9/15
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>result</title>
    <link rel="Shortcut Icon" href="../images/icon1.jpg">
    <style>
        body{ background-color: #F9F6EF; color: #494949; }
        .jq22-content{ width: 900px; height: auto; margin-left: auto; margin-right: auto; padding: 20px; border: 1px solid #D0D0D0; background-color: #EFEFEF; margin-top: 50px; font-weight: bold; line-height: 24px; }
    </style>
    <link rel="stylesheet" type="text/css" href="../css/mystyle.css"/>
</head>
<body>
<div class="jq22-content bgcolor-3" style="width: 400px; margin: auto; text-align: center;">
    <p class="word1"></p>
    <p class="word2"></p>
</div>
<input type="hidden" value="${resultStr}" id="resultStr">
<input type="hidden" value="${targetUrl}" id="targetUrl">
<script src="../js/jquery1.11.1.min.js" type="text/javascript"></script>
<script>window.jQuery || document.write('<script src="../js/jquery-1.11.0.min.js"><\/script>')</script>
<script src="../js/l-by-l.min.js"></script>
<script>
    jQuery(document).ready(function ($) {
        var r = $('#resultStr').val();
        $(".word1").lbyl({
            content: r,
            speed: 100,
            type: 'show',
            finished: function () {
                $('.word2').lbyl({
                    content: "",
                    speed: 150,
                    type: 'fade',
                    fadeSpeed: 500
                })
            } // Finished Callback
        });

    });
</script>
<input type="button" value="跳转"
       class="myButton" style="margin-left: 47%; margin-top: 15px;"
       onclick="window.location='${targetUrl}'"/>
</body>
</html>
