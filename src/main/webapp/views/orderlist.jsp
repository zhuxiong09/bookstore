<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>  

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
	<div class="list orderList">
			<table>
				<tr class="title">
					<th class="orderId">订单编号</th>
					<th>订单商品</th>
					<th>订单时间</th>
					<th class="userName">商品名称</th>
					<th class="price">商品单价</th>
					<th class="createTime">订购数量</th>
					<th class="status">订单总额</th>
				</tr>
				<c:forEach var="list" items="${shoppingOderList }">
					<tr>
					<td height="114">${list.orderId }</td>
					<td class="thumb"><img src="images/book/${list.image }" /></td>
					<td class="thumb">${list.createdate }</td>
					<td>${list.itermName }</td>
					<td>￥${list.price}</td>
					<td>${list.count }</td>
					<td>￥${list.totalPrice}</td>
				</tr>
				</c:forEach>
				
			</table>
			<div class="button"></div>
	</div>

	<!-- 当没有历史订单时显示如下信息 -->
	<c:if test="${empty shoppingOderList}">
		<div class="emptyitems">您还没有历史订单!快去<a href="index">选购图书</a>吧</div>
	</c:if>


</div>
<div id="footer" class="wrap">
	图灵科技网上书城 &copy; 版权所有</div>
</body>
</html>
