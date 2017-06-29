<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<title>图片上传</title>
<link href="third/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="third/css/style.css" />
<script src="third/jquery/jquery.js"></script>
</head>
<body>
	<form action="./UpLaodServlet" method="post"
		enctype="multipart/form-data">
		老接口上传文件：<input type="file"><br /> <input id="oldfile" type="file"
			style="display: none">
		<div class="input-append">
			<input id="oldCover" class="input-large" type="text"
				style="height: 30px;"> <a class="btn"
				onclick="$('input[id=oldfile]').click();">Browse</a>
		</div>

		<script type="text/javascript">
			$('input[id=oldfile]').change(function() {
				$('#oldCover').val($(this).val());
			});
		</script>
	</form>
	
	<form action="upload" method="post"
		enctype="multipart/form-data">
		新接口上传文件：<input type="file"><br /> <input id="newfile" type="file"
			style="display: none">
		<div class="input-append">
			<input id="newCover" class="input-large" type="text"
				style="height: 30px;"> <a class="btn"
				onclick="$('input[id=newfile]').click();">Browse</a>
		</div>

		<script type="text/javascript">
			$('input[id=newfile]').change(function() {
				$('#newCover').val($(this).val());
			});
		</script>
	</form>
</body>
</html>
