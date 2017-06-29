<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<!-- <link rel="stylesheet" href="third/materialize/css/materialize.min.css"
		media="screen,projection" /> -->
	<!-- Bootstrap Styles-->
	<link href="third/bootstrap/css/bootstrap.css" rel="stylesheet" />
	<!-- FontAwesome Styles-->
	<link href="third/css/font-awesome.css" rel="stylesheet" />
	<!-- Morris Chart Styles-->
	<link href="third/morris/css/morris-0.4.3.min.css" rel="stylesheet" />
	<!-- Custom Styles-->
	<link href="third/css/custom-styles.css" rel="stylesheet" />
	<!-- Google Fonts-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans'
		rel='stylesheet' type='text/css' />
	<!-- jQuery Js -->
	<script src="third/jquery/jquery.js"></script>
	<!-- Bootstrap Js -->
	<script src="third/bootstrap/js/bootstrap.min.js"></script>
	
	<script src="third/materialize/js/materialize.min.js"></script>
	
	<!-- Metis Menu Js -->
	<script src="third/jquery/jquery.metisMenu.js"></script>
	<!-- Morris Chart Js -->
	<script src="third/morris/raphael-2.1.0.min.js"></script>
	<script src="third/morris/morris.js"></script>
	
	<!-- DATA TABLE SCRIPTS -->
	<script src="third/jquery/jquery.dataTables.js"></script>
	<script src="third/bootstrap/js/dataTables.bootstrap.js"></script>
		<!-- Custom Js -->
	<script src="third/js/custom-scripts.js"></script>
</head>
<body>
	<div id="wrapper">
		<!-- header 文件开始 -->
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
	<!-- <ul id="dropdown1" class="dropdown-content">
		<li><a href="#"><i class="fa fa-user fa-fw"></i> My Profile</a></li>
		<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
		<li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
	</ul> -->
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
			</ul>
		</div>
	</nav>
		<!-- header 文件结束 -->
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">图片详情</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">Data</a></li>
					<li><a href="<%=path%>/index">ImageList</a></li>
					<li class="active">ImageDetail</li>
				</ol>
			</div>
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="card">
							<div class="card-action">
								<form id="detailForm" action="<%=path%>/imgDetail" method="post">
									<input type="hidden" value="${imgUrl }" name="sourceImg"/>
								</form>
							</div>
							<div class="card-content">
								<table >
									<tr>
										<td>
											<table >
												<tbody>
													<!-- <tr>
														<td>样式名称：<input type="text" name="" style="readonly=readonly;width:300px; " /></td>
													</tr> -->
													<tr >
														<td >缩略方式：
														<input type="radio" name="breviary" checked value="fixedWH"/>固定宽高缩放 &nbsp;&nbsp;
														<input type="radio" name="breviary" value="constrain"/>等比例缩放
														</td>
													</tr>
													<tr>
														<td><br/>
															<select name="zoom" style="width:200px; display:block; "> 
															    <option value="shortCenter">按短边缩放，居中裁剪</option> 
															    <option value="longZoom">按长边缩放，缩略填充</option> 
														    </select> 
														  	<br/>
														</td>
													</tr>
													<tr>
														<td>
															缩略宽度：<input type="text" name="w_weight" style="width:100px; " value="400"/>&nbsp;&nbsp;
															缩略高度：<input type="text" name="h_height" style="width:100px; " value="400"/><br/>
														</td>
													</tr>
													<tr>
														<td><br/>缩略限制：
															<select name="abbreLimit" style="width:200px; display:block; "> 
															    <option value="notForbid">不禁止</option> 
															    <option value="forbid">禁止图片放大</option> 
														    </select><br/>
														</td>
													</tr>
													<tr>
														<td>适应方向：    
															<select name="adaptDirection" style="width:200px; display:block; "> 
															    <option value="defaultByMaster">按原图默认</option> 
															    <option value="rotateShorten">先旋转后缩略</option> 
														    </select> <br/>
														</td>
													</tr>
													<tr>
														<td>图片处理：
															<input name="sharpen" type="checkbox" value="sharpen" />图片锐化  
															<br/>
														</td>
													</tr>
													<tr>
														<td><br/>图片质量：
															<select name="picQuality" style="width:200px; display:block; "> 
															    <option value="relative">相对质量</option> 
															    <option value="absolute">绝对质量</option> 
															    <option value="notCompress">不压缩</option> 
														    </select> 
														    质量值（1到100）：<input type="text" name="qualityVal" style="width:100px; " value="90"/>
														</td>
													</tr>
													<tr>
														<td><br/>保存格式：
															<select name="saveFormat" style="width:200px; display:block; "> 
															    <option value="artwork">原图格式</option> 
															    <option value="jpg">jpg</option> 
															    <option value="png">png</option> 
															    <option value="webp">webp</option> 
															    <option value="bmp">bmp</option> 
														    </select> 
														</td>
													</tr>
													<!-- <tr>
														<td>缩略方式：
															<input type="radio" name="" style="display:block; "/>不设置 &nbsp;&nbsp;
															<input type="radio" name="" style="display:block; "/>文字水印&nbsp;&nbsp;
															<input type="radio" name="" style="display:block; "/>图片水印&nbsp;&nbsp;
														</td> 
													</tr> -->
												</tbody>
											</table>
										
										</td>
										<td>&nbsp;&nbsp;</td>
										<td>
											预览图片:<br/><br/>
											<div style = "width:500px; height:500px; overflow:auto;">
												<img src="${imgUrl }"  alt="DIVCSS5的LOGO" name="previewPic"/>
											</div>
											<br/><br/><input type ="button" value="刷新预览" onclick="refreshPreview()"/>
											<input type ="button" value="原图" onclick="originPic()"/>
											<input type ="button" value="新窗口显示" onclick="openNewWindow()"/>
										</td>
									</tr>
								</table>
							
							</div>
						</div>
						<!--End Advanced Tables -->
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<script>
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
	
		function refreshPreview(){
			//缩略方式
			var breviary =$('input[name="breviary"]').filter(':checked').val();
			//缩放
			var zoom = $("select[name=zoom]").find("option:selected").val();
			//缩略宽度
			var w = $("input[name=w_weight]").val();
			//缩略高度
			var h = $("input[name=h_height]").val();
			//缩略限制
			var abbreLimit = $("select[name=abbreLimit]").find("option:selected").val();
			//适应方向
			var adaptDirection = $("select[name=adaptDirection]").find("option:selected").val();
			//图片处理
			var sharpen =$('input[name="sharpen"]').filter(':checked').val();
			//图片质量
			var picQuality = $("select[name=picQuality]").find("option:selected").val();
			//图片质量值
			var qualityVal = $("input[name=qualityVal]").val();
			//保存格式
			var saveFormat = $("select[name=saveFormat]").find("option:selected").val();
			//图片路径
			var imgUrl = $("input[name=sourceImg]").attr("value");
			//alert("缩略方式:"+breviary+";缩放:"+zoom+";缩略宽度:"+w+";缩略高度:"+h+";缩略限制:"+abbreLimit+";适应方向:"+adaptDirection+";图片处理:"+sharpen+";图片质量:"+picQuality+";质量值:"+qualityVal+";保存格式:"+saveFormat+";图片路径:"+imgUrl);
			
			var reg = new RegExp("^[0-9]*$");  
			if(!reg.test(w)){  
		        alert("缩略宽度请输入数字!"); 
		        return;
		    } 
			if(!reg.test(h)){  
		        alert("缩略高度请输入数字!"); 
		        return;
		    }
			
			var wb = w.replace(/(^s*)|(s*$)/g, "").length,hb = h.replace(/(^s*)|(s*$)/g, "").length;
			if(wb > 0 && ( w > 4096 || w <= 0 )){
				alert("缩略宽度请输入1-4096之间数值!"); 
		        return;
			}
			if(hb > 0 && ( h != "" && h > 4096 || h <= 0)){
				alert("缩略高度入请输入1-4096之间数值!"); 
		        return;
			}
			
			if(picQuality != "notCompress"){
				if(!reg.test(qualityVal)){  
			        alert("图片质量值请输入数字!"); 
			        return;
			    }
				if(qualityVal > 100 || qualityVal <= 0){
					alert("图片质量值请输入1-100之间数值!"); 
			        return;
				}
			}
			
			var map={
				"fixedWHshortCenter":",m_fill",
			    "fixedWHlongZoom":",m_fixed",
			    "constrainshortCenter":",m_lfit",
			    "constrainlongZoom":",m_mfit",
			    "notForbid":",limit_0",
			    "forbid":",limit_1",
			    "defaultByMaster":",0 ",
			    "rotateShorten":",1",
			    "relative":",q_",
			    "absolute":",Q_",
			    "jpg":",jpg",
			    "png":",png",
			    "webp":",webp",
			    "bmp":",bmp"
			};
			
			var zoomVal = map[breviary+zoom];
			var lim = map[abbreLimit];
			//缩略
			var sf = "/resize" + zoomVal + lim;
			if(wb > 0){
				sf += ",w_" + w;
			}
			if(hb > 0){
				sf += ",h_" + h;
			}	
			
			//适应
			var sy = "/auto-orient" + map[adaptDirection];
			
			//锐化
			var rh = "";
			if(sharpen != null && sharpen != undefined && sharpen != ""){
				rh += "/sharpen,100";
			}
			
			//质量
			var zl = "";
			if(picQuality != "notCompress"){
				zl += "/quality" + map[picQuality] + qualityVal;
			}
			
			//格式
			var gs = "";
			if(saveFormat != "artwork"){
				gs += "/format" + map[saveFormat];
			}
			
			if(null != imgUrl && undefined != imgUrl){
				var repImgUrl = imgUrl + "?x-oss-process=image" + sf + sy + rh + zl + gs;
				$("img[name=previewPic]").attr("src", repImgUrl);
				
				//alert(repImgUrl);
			}
		}		
		
		function originPic(){
			var imgUrl = $("input[name=sourceImg]").attr("value");
			$("img[name=previewPic]").attr("src", imgUrl);
		}
		
		function openNewWindow(){
			var imgUrl = $("img[name=previewPic]").attr("src");
			window.open(imgUrl);  
		}
	</script>
</body>

</html>
