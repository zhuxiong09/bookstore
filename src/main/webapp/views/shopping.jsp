<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>  

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>购物车</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/node.js" language="javascript"></script>

<script>
	function init(){
	    var mybody = document.getElementById("mybody");
	    clearWhitespace(mybody); //清除body内所有空白节点
		
		calc_total_price();	//调用计算所有订单总价的函数	
	}
	//计算所有订单总价
	function calc_total_price(){
		var mytable = document.getElementById("mytable");
		var sum = 0;	//总价格
		/*从第2行遍历到表格最后一行累计价格*/
		for(var i=1;i<mytable.rows.length;i++){
			var tr = mytable.rows[i];	//得到行对象
			var td = tr.cells[4];		//得到价格单元格
			sum += parseFloat(td.innerHTML.substr(1));	//累计价格		
		}
		document.getElementById("total_price").innerHTML = sum;
	}
	//修改订购数量
	//obj - 订购数量文本框
	//price-图书的价格(单价)
	//bookId-图书的编号
	//stock-当前图书的库存
	function changeNumbers(obj,price,bookId,stock){
		if(isNaN(obj.value)){
			alert("订购数量必须为数字！");
			obj.value = 1;
			obj.parentNode.nextSibling.innerHTML = "￥" + (obj.value * price);
		}else if (obj.value <= 0) {
			alert("订购数量不能小于1！");
			obj.value = 1;
		    //修改每本图书的订购金额
			obj.parentNode.nextSibling.innerHTML = "￥" + (obj.value * price);
		} else if (obj.value > stock) {
			alert("库存剩余"+stock+"，订购数量不能超过库存！");
			obj.value = stock;
		    //修改每本图书的订购金额
			obj.parentNode.nextSibling.innerHTML = "￥" + (obj.value * price);
		}else{
			//修改每本图书的订购金额
		    obj.parentNode.nextSibling.innerHTML = "￥" + (obj.value * price);
                curNums= obj.value;
		    //?bookId="+bookId+"&curNums="+obj.value;
			var url = "updateShoppingCar?bookId="+bookId+"&curNums="+obj.value ;
			location.href = url;
		}
	}
	//移除订单
	function clearOrder(bookId){
	    //?bookId="+bookId+"&curNums=0
		var url = "deleteShoppingCar?bookId="+bookId+"&curNums=0";
		location.href = url;
	}
	
</script>
</head>


<body onload="init()" id="mybody"> 
    <div id="header" class="wrap">
        <div id="logo">网上书城</div>


    <%@ include file="navbar.jsp" %>  <!-- 导航页 -->

    </div>
    <div id="content" class="wrap">
    <div class="list bookList">
        <form method="post" name="shopping" action="shopping-result">
            <table id="mytable">
                <tr class="title">
                    <th class="view">图片预览</th>
                    <th>图书编号</th>
                    <th>书名</th>
                    <th class="nums">数量</th>
                    <th class="price">价格</th>
                    <th class="price">操作</th>
                </tr>
            <c:forEach var="book" items="${shoppingList}">
                <tr>
                    <td class="thumb"><img src="images/book/${book.image }" /></td>
                    <td><input type="text" name="bookId" value="${book.bid }" /></td>
                    <td class ="title"/> ${book.bookname } </td>
                    <td><input class="input-text" type="text" name="nums" value="${shoppingMap[book.bid] }" onblur="changeNumbers(this,${book.price},${book.bid},${book.stock})"/></td>
                    <td>￥${shoppingMap[book.bid]*book.price}</td>
                    <td><a href="#" onclick="clearOrder(${book.bid})">移除</a></td>
                </tr>
            </c:forEach>




            </table>
            <div class="button">
              <h4>总价：￥<span id="total_price"></span>元</h4>
                <input class="input-chart" type="submit" name="submit" value="" />
            </div>
        </form>

        <!-- 当购物车为空时显示如下信息 -->
        <c:if test="${empty shoppingList}">
            <div class="emptyshopping">您的购物车空空如也!快去<a href="homePage">选购图书</a>吧</div>
        </c:if>


    </div>
    </div>
    <div id="footer" class="wrap">
        图灵科技网上书城 &copy; 版权所有</div>
</body>
</html>
