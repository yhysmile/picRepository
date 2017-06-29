<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
				<h1 class="page-header">图片列表</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">Data</a></li>
					<li class="active">ImageList</li>
				</ol>

			</div>
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="card">
							<div class="card-action">
								<form id="searchForm" action="<%=path%>/index" method="post">
									请输入关键字:<input type="text" name="searchKey" id="searchKey"/>
									<input type="hidden" name="page.currentPage" value="${pageImage.currentPage }" id="cPage"/>
									<input type="hidden" name="page.pageSize" value="${pageImage.pageSize}" id="pSize"/>
									<input type="submit" value="search"/>
								</form>
							</div>
							<div class="card-content">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover" id="dataTables-example">
										<thead>
											<tr>
												<th>图片名称</th>
												<th>图片MD5值</th>
												<th>图片路径</th>
												<th>创建时间</th>
												<th>扩展信息</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${ not empty pageImage and not empty pageImage.datas}">
												<c:forEach items="${pageImage.datas}" var="img">
													<tr class="odd gradeX">
														<td><a href="<%=path%>/imgDetail?imgName=${img.name }">${img.name }</a></td>
														<td>${img.md5Name }</td>
														<td>${img.path }</td>
														<td class="center">${img.createDate }</td>
														<td class="center">${img.extend }</td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${ not empty pageImage}">
												<tr class="odd gradeX">
													<td colspan="5" class="center">
														总共有${pageImage.totalPage }页数据，每页展示
														<select onchange="changePageSize(this.options[this.options.selectedIndex].value)" style="width:60px; display:block; ">  
														  <option value="10" <c:if test="${10 eq pageImage.pageSize}">selected</c:if> >10</option>     
														  <option value="20" <c:if test="${20 eq pageImage.pageSize}">selected</c:if> >20</option>     
														  <%-- <option value="50" <c:if test="${50 eq pageImage.pageSize}">selected</c:if> >50</option>    
														  <option value="100" <c:if test="${100 eq pageImage.pageSize}">selected</c:if> >100</option>      --%>
														</select>  
														条数据，
														当前第${pageImage.currentPage }页，
														<a href="javascript:void();" onclick="changeCurPage(this)" value="1">首页</a>
														<c:forEach varStatus="i" begin="${pageImage.showStartPage}" end="${pageImage.showEndPage}" step="1">
									                        <c:choose>
									                           <c:when test="${pageImage.currentPage == i.current}">
									                              ${i.current}
									                           </c:when>
									                           <c:otherwise>
									                               <a href="javascript:void();" onclick="changeCurPage(this)" value="${i.current}">${i.current}</a>
									                           </c:otherwise>
									                        </c:choose>
									                   </c:forEach>
									                   <a href="javascript:void();" onclick="changeCurPage(this)" value="${pageImage.totalPage}">尾页</a>
													</td>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>
							
							</div>
						</div>
						<!--End Advanced Tables -->
					</div>
				</div>
			</div>
		</div>
		<script>
			//$(document).ready(function() {
			//	$('#dataTables-example').dataTable();
			//});
			function changePageSize(pSize){
	            $("#pSize").val(pSize);
				$("#searchForm").submit();
				 
			}
			function changeCurPage(obj){
				var cPage =  $(obj).attr('value');
				document.getElementById("cPage").value = cPage;
				document.getElementById("searchForm").submit();
			}
		</script>
	
</body>

</html>
