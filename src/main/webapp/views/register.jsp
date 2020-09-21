<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
	function checkname(){
	var userName=document.getElementById("userName");	
	var spanuserName=document.getElementById("spanuserName");
	if(userName.value==""){
		spanuserName.innerHTML="用户名不能因为空！";
		return false;
	}else{
		spanuserName.innerHTML="";
		return true;
	}
}	
	function checkpass(){
		var passWord=document.getElementById("passWord");	
		var spanpassWord=document.getElementById("spanpassWord");
		if(passWord.value==""){
			spanpassWord.innerHTML="用户名不能因为空！";
			return false;
		}else{
			spanpassWord.innerHTML="";
			return true;
		}
	}	
		function checkrepass(){
			var rePassWord=document.getElementById("rePassWord");
			var passWord=document.getElementById("passWord");	
			var spanpassWord=document.getElementById("spanpassWord");
			if(rePassWord.value!=passWord.value){
				spanrePassWord.innerHTML="两次输入的密码不一样！";
				return false;
			}else{
				spanrePassWord.innerHTML="";
				return true;
			}
		}
		
		function checkemail(){
			var reg = /^\w+@\w+.[a-z]{2,3}$/;
			var email=document.getElementById("email");	
			var spanemail=document.getElementById("spanemail");
			var result=reg.test(email.value);
			if(result==true){
				spanemail.innerHTML="";
				return true;
			}else{
				spanemail.innerHTML="邮箱格式不正确！";
				return false;
			}
		}	
		
		function check(){
			return checkname()&&checkpass()&&checkrepass()&&checkemail();
		}


</script>

</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar" style="height:2em;"></div>
</div>
<div id="register">
	<div class="title">
		<h2>欢迎注册网上书城</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写注册信息</li>
			<li class="unpass">2.注册成功</li>
		</ul>
	</div>
	<form method="post" action="register" onsubmit="return check()">
		<dl>
			<dt>用 户 名：</dt>
			<dd><input class="input-text" type="text" name="userName" id="userName" onblur="checkname()"/><span id="spanuserName" style="display:inline;"></span></dd>
		</dl>
		<dl>
			<dt>密　　码：</dt>
			<dd><input class="input-text" type="password" name="passWord" id="passWord" onblur="checkpass()"/><span id="spanpassWord" style="display:inline;"></span></dd>
		</dl>
		<dl>
			<dt>确认密码：</dt>
			<dd><input class="input-text" type="password" name="rePassWord" id="rePassWord" onblur="checkrepass()"/><span id="spanrePassWord" style="display:inline;"></span></dd>
		</dl>
		<dl>
			<dt>Email地址：</dt>
			<dd><input class="input-text" type="text" name="email" id="email" onblur="checkemail()"/><span id="spanemail" style="display:inline;"></span></dd>
		</dl>
		<dl>
			<dt>&nbsp;&nbsp;</dt>
			<dd class="button"><input class="input-reg" type="submit" name="register" value="" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	图灵科技网上书城 &copy; 版权所有</div>
</body>
</html>
