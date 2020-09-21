<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

  <div id="navbar">
		<div class="userMenu">
			<ul>
            	<li>欢迎你，${sessionScope.get("userName") }!</li>
				<li><a href="homePage">首页</a></li>
				<li><a href="orderlist">我的订单</a></li>
				<li><a href="shopping">购物车</a></li>
				<li><a href="Cancellation">注销</a></li>
			</ul>
		</div>
		<form method="post" name="search" action="search">
			搜索：<input class="input-text" type="text" name="keywords" value="${keywords }" />
			<input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>  