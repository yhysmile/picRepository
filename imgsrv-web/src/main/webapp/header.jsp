<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if("/".equals(path))
	path = "";

response.setHeader("mftour-img-header", "mftour-img-header"); 

%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title></title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="third/materialize/css/materialize.min.css"
	media="screen,projection" />
<!-- Bootstrap Styles-->
<link href="third/bootstrap/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="third/font-awesome/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<link href="third/morris/css/morris-0.4.3.min.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="third/css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- jQuery Js -->
<script src="third/jquery/jquery.js"></script>
<script src="third/jquery/jquery.form.js"></script>
<script src="third/jquery/jquery.dataTables.js"></script>
<script src="third/bootstrap/js/dataTables.bootstrap.js"></script>

<!-- Bootstrap Js -->
<script src="third/bootstrap/js/bootstrap.min.js"></script>

<script src="third/materialize/js/materialize.min.js"></script>

<!-- Metis Menu Js -->
<script src="third/jquery/jquery.metisMenu.js"></script>
<!-- Morris Chart Js -->
<script src="third/morris/raphael-2.1.0.min.js"></script>
<script src="third/morris/morris.js"></script>
</head>
<body>
	<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand waves-effect waves-dark" href="index.jsp"><i
				class="large material-icons">insert_chart</i> <strong>TRACK</strong></a>

			<div id="sideNav" href="">
				<i class="material-icons dp48">toc</i>
			</div>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> <b>John
						Doe</b> <i class="material-icons right">arrow_drop_down</i></a></li>
		</ul>
	</nav>
	<!-- Dropdown Structure -->
	<ul id="dropdown1" class="dropdown-content">
		<li><a href="#"><i class="fa fa-user fa-fw"></i> My Profile</a></li>
		<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
		<li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
	</ul>
	<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav">
				<li><a id="menu" href="<%=path%>/index"
					class="waves-effect waves-dark"><i class="fa fa-table"></i>
						图片列表</a></li>
				<li><a id="menu" href="upload.jsp"
					class="active-menu waves-effect waves-dark" onclick="menuChange()"><i
						class="fa fa-upload"></i> 图片上传</a></li>
				<li><a id="menu" href="hisdata_transfer.jsp"
					class="waves-effect waves-dark"><i class="fa fa-dashboard"></i>
						历史图片处理</a></li>
				<!-- <li><a id="menu" href="tab-panel.html"
					class="waves-effect waves-dark"><i class="fa fa-qrcode"></i>图片展示</a></li> -->
			</ul>
		</div>
	</nav>
</body>
<script type="text/javascript">
	var urlstr = location.href;
	var urlstatus = false;
	$("a#menu").each(
			function() {
				if ((urlstr + '/').indexOf($(this).attr('href')) > -1
						&& $(this).attr('href') != '') {
					$(this).addClass('active-menu');
					urlstatus = true;
				} else {
					$(this).removeClass('active-menu');
				}
			});
	if (!urlstatus) {
		$("#nav a").eq(0).addClass('active-menu');
	}
</script>

</html>
