<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
	<div id="wrapper">
		<%@ include file="./header.jsp"%>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">图片上传</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">Forms</a></li>
					<li class="active">Data</li>
				</ol>

			</div>
			<div id="page-inner">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-content">
								<form id="newUpload" action="upload" method="post"
									enctype="multipart/form-data">
									<div class="file-field input-field">
										<div class="btn">
											<span id="newSpan">新图片接口上传</span> <input type="file"
												name="file" multiple>
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate" type="text" name="filename"
												placeholder="Upload one or more files">
										</div>
									</div>
									<div>
										<input type="button" value="提交" onclick="newSubmit()">
										<input type="button" value="取消" onclick="newCancel()">
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="col-lg-12">
						<div class="card">
							<div class="card-content">
								<form id="oldUpload" action="UpLaodServlet" method="post"
									enctype="multipart/form-data">
									<div class="file-field input-field">
										<div class="btn">
											<span id="oldSpan">老图片接口上传</span> <input type="file"
												name="file" multiple>
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate" type="text" name="filename"
												placeholder="Upload one or more files">
										</div>
									</div>
									<div>
										<input type="button" value="提交" onclick="oldSubmit()">
										<input type="button" value="取消" onclick="oldCancel()">
									</div>
								</form>
							</div>
						</div>
					</div>


				</div>

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<script type="text/javascript">
		function newSubmit() {
			var btn = $('#newSpan'); //按钮文本
			var uploadInput = $("#newUpload input[name=file]");
			var uploadNameInput = $("#newUpload input[name=filename]");
			if (uploadInput.val() == '') {
				alert("请选择上传文件");
				return;
			}
			$("#newUpload").ajaxSubmit({
				beforeSend : function() {
					btn.html('上传中..');
				},
				success : function(data) {
					uploadInput.val("");
					uploadNameInput.val("");
					btn.html('新图片接口上传');
					alert("上传成功");
				},
				error : function(xhr) {
					btn.html('新图片接口上传');
					alert("上传失败");
				}
			});
		}
		function newCancel() {
			$("#newUpload input[name=file]").val("");
			$("#newUpload input[name=filename]").val("");
		}

		function oldSubmit() {
			var btn = $('#oldSpan'); //按钮文本
			var uploadInput = $("#oldUpload input[name=file]");
			var uploadNameInput = $("#oldUpload input[name=filename]");
			if (uploadInput.val() == '') {
				alert("请选择上传文件");
				return;
			}

			$("#oldUpload").ajaxSubmit({
				beforeSend : function() {
					btn.html('上传中..');
				},
				success : function(data) {
					uploadInput.val("");
					uploadNameInput.val("");
					btn.html('老图片接口上传');
					alert("上传成功");
				},
				error : function(xhr) {
					btn.html('老图片接口上传');
					alert("上传失败");
				}
			});
		}
		function oldCancel() {
			$("#oldUpload input[name=file]").val("");
			$("#oldUpload input[name=filename]").val("");
		}
	</script>
</body>

</html>
