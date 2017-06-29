<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if("/".equals(path))
	path = "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>用户登录</title>
<link href="<%=path%>/third/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/third/css/style.css" />
<script src="<%=path%>/third/jquery/jquery.js"></script>
</head>
<body>
	<div class="signin">
		<div class="signin-head">
			<h1>登 录</h1>
			<hr
			style="height: 1px; border: none; border-top: 2px ridge rgb(6, 106, 117);" />
		</div>
		
		<form id="form" class="form-signin" action="<%=path%>/login"
			method="post">
			<input id="username" type="text" class="form-control" placeholder="用户名" autofocus value="cn=Manager"/>
			<input id="password" type="password" class="form-control" placeholder="密码" value="mftour123"/>
			<button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>
			<label class="checkbox"> <input type="checkbox" value="remember-me"> 记住我
			</label>
			<span id="error" style="color: red"></span>
			
		</form>

	</div>
</body>
<script>
	function check(form) {
		if (form.username.value == '') {
			alert("请输入用户帐号!");
			form.username.focus();
			return false;
		}
		if (form.password.value == '') {
			alert("请输入登录密码!");
			form.password.focus();
			return false;
		}
		$.post("<%=path%>/login", {
			username : form.username.value,
			password : form.password.value
		}, function(data) {
			if (data.errorCode == 10000) {
			        var data1={
			        		  "searchStartDate": 0,
			        		  "searchEndDate": 0,
			        		  "page": {
			        		    "current_page": 1,
			        		    "page_size": 20
			        		  },
			        		  "styleName": "defaultStyle"
			        		};  
		     
			        $.ajax({ 
			            type:"POST", 
			            url:"<%=path%>/imglist", 
			            dataType:"json",      
			            contentType:"application/json",               
			            data:JSON.stringify(data1), 
			            success:function(data){ 
			                                       
			            } 
			         }); 
			if (data.errorCode == 10000) {			        
			  window.location.href="imgindex";
			} else {
				alert(data.errorMsg);
			}
		});
		return false;
	}
</script>
</html>