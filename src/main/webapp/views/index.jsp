<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false" %>
<html >
<head>
    <title>Title</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<style>
		.price {
			border: none;
			margin-left: 25%;
		}
	</style>
	<script type="text/javascript">
	//检查是否有选择图书
		function check(){
			var bookIds=document.getElementsByName("bookId");
			var isOk=false;//false表示没有图书被选中,true有图书被选中；
			for(var i=0;i<bookIds.length;i++){
				if(bookIds[i].checked){
					isOk = true;
					break;
				}
			}
			if(isOk==false){
				alert("请至少选择一本图书加入购物车！");
				return false;
			}else{
				return true;
			}
		}
	</script>

</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	
	<%@ include file="navbar.jsp" %>  <!-- 导航页 -->
	
</div>
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="shopping" onsubmit="return check()">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
				</tr>
				<c:forEach var="book" items="${bookList }">
					<tr>
						<td><input type="checkbox" name="bookId" value="${book.bid }" /></td>
						<td class="title">${book.bookname }</td>
						<td><input type="text" name="price" value="${book.price }" class="price"/></td>
						<td>${book.stock }</td>
						<td class="thumb"><img src="images/book/${book.image }" /></td>
					</tr>
				</c:forEach>
			</table>
			
			<!-- 当找到图书信息内容大于一页时显示分页 -->
			<c:if test="${b_maxPage>1 }">
				<div class="page-spliter">
				[${b_page }/${b_maxPage}]&nbsp;
				<a href="search?b_page=${b_page-1 }">&lt;</a>
				<a href="search?b_page=1">首页</a>
				<a href="search?b_page=${b_maxPage }">尾页</a>
				<a href="search?b_page=${b_page+1 }">></a>
			</div>
			</c:if>
			<!-- 当找到图书信息时显示下列内容 -->
			<c:if test="${b_maxPage>0 }">
				<div class="button">
					<input class="input-btn" type="submit" name="submit" value="" />
				</div>
			</c:if>
			
			<!-- 当未找到图书信息时显示下列内容 -->
			<c:if test="${b_maxPage==0 }">
				<div class="unfind">没有找到您需要的图书……</div>
			</c:if>
			 

		</form>
	</div>
</div>
<div id="footer" class="wrap">
	图灵科技网上书城 &copy; 版权所有</div>
</body>
</html>

    