<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false" %>
<html>
<head>

<title>Title</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
	function checkUserName(){
		var userName=document.getElementById("userName");
		var userName_span=document.getElementById("userName_span");
		if(userName.value==""){
			userName_span.innerHTML="用户名不能因为空！"
			return false;
		}else{
			userName_span.innerHTML=""
				return true;
		}
	}
	function checkPassWord(){
		var passWord = document.getElementById("passWord");
		var passWord_span = document.getElementById("passWord_span");
		if(passWord.value == ""){
			passWord_span.innerHTML = "密码不能为空！";
			return false;
		}else{
			passWord_span.innerHTML = "";
			return true;
		}
	}
	function check(){
		return checkUserName()&&checkPassWord();
	}
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">网上书城</div>
		<div id="navbar" style="height: 2em;"></div>
	</div>
	<div id="login">
		<h2>用户登陆</h2>
		<form method="post" onsubmit="return check()" action="login">
			<dl>
				<dt>用户名：</dt>
				<dd>
					<input class="input-text" type="text" name="userName" id="userName" onblur="checkUserName()"/><span id="userName_span"></span>
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input class="input-text" type="password" name="passWord" id="passWord" onblur="checkPassWord()"/><span id="passWord_span"></span>
				</dd>
				<dt></dt>
				<dd class="button">
					<input class="input-btn" type="submit" name="submit" value="" />
					<input class="input-reg" type="button" name="register" value="" onclick="window.location='register';" />
				</dd>
			</dl>
		</form>
	</div>
	<div id="footer" class="wrap">图灵科技网上书城 &copy; 版权所有</div>
</body>
</html>
