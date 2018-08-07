<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Hello 51 World</title>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

</head>

<body>
<h2>Hello 51 World:移动支付客户端组测试服务器</h2>
<a href="getNewToken">获取新Token</a>
<p>渠道请求格式为：http://localhost:8082/c1/apply.htm?reqNo=1113&token=abc</p>
<p>目前系统支持多个用户进行测试，每次获取新Token会重置系统数据，重置部分包括时间和已请求的reqNo记录，系统时间从第一次请求apply.htm接口开始计时</p>

</body>
</html>
