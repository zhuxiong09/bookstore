<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<%@ include file="navbar.jsp" %>  <!-- 导航页 -->
</div>
<div id="content" class="wrap">
	<div class="success">
		<div class="information" >
			<p>恭喜：购买成功！</p>
			<p><a href="orderlist">点此查看订单详情&gt;&gt;</a></p>
		</div>
	</div>
</div>
<div id="footer" class="wrap">
	图灵科技网上书城 &copy; 版权所有</div>
</body>
</html>
