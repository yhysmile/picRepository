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
				<h1 class="page-header">历史数据迁移</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">Data</a></li>
					<li class="active">Transfer</li>
				</ol>

			</div>
			<div id="page-inner">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-content">
								<form id="hisTransfer" action="uploadHistorical" method="get">
									<div class="row">
										<div class="input-field col s6">
											<i class="material-icons prefix">account_circle</i> <input
												name="imgNum" type="number" class="validate"> <label
												for="icon_prefix">一次迁移条数</label>
										</div>
										<div class="input-field col s6"></div>
										<div>
											<input type="button" value="提交" onclick="transferSubmit()">
											<input type="button" value="取消" onclick="transferCancel()">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>

					<div class="col-lg-12">
						<div class="card">
							<div class="card-content">
								迁移结果
								<div id="resultSuccess" style="display: none;"
									class="alert alert-success"></div>
								<div id="resultError" style="display: none;"
									class="alert alert-danger"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="right">
				<a id="loading" class="btn"
					data-toggle="popover" data-placement="bottom"></a>
			</div>
				style="display: none" />
		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<script type="text/javascript">
		function transferSubmit() {
			var imgNum = $("#hisTransfer input[name=imgNum]");
			if (imgNum.val() <= 0) {
				alert("迁移条数必须大于0");
				return;
			}
     	    $('#loading').popover({
		        trigger : 'hover',//鼠标以上时触发弹出提示框
		        html:true,//开启html 为true的话，data-content里就能放html代码了
		        content:"<img src='1.png'>"
		    });
		    $('#weixin').popover({
		        trigger : 'hover',
		        html:true,
		        content:"<img src='2.png'>"
		    });


			$("#hisTransfer").ajaxSubmit(
					{
						success : function(data) {
							$("#loading").hide();
							if (data.errorCode == 10000) {
								imgNum.val("");
								$("div#resultSuccess").show();
								$("div#resultSuccess").html(data.data);
								alert("迁移成功");
							} else {
								$("div#resultError").show();
								$("div#resultError").html(
										"错误码：" + data.errorCode + "<br />错误原因："
												+ data.errorMsg);
							}

						},
						error : function(xhr) {
							$("#loading").hide();
							alert("迁移失败，系统错误");
						}
					});
		}
		function transferCancel() {
			$("hisTransfer input[name=imgNum]").val("");
		}
	</script>
</body>

</html>
