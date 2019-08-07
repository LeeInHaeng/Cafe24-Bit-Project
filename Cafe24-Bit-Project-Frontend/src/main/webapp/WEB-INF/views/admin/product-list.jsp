<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/clearmin/bootstrap-clearmin.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/clearmin/roboto.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/clearmin/material-design.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/clearmin/small-n-flat.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/clearmin/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/product-list.css">
    <title>Cafe24mall 관리자</title>
  </head>
  <body class="cm-no-transition cm-1-navbar">
  	<c:import url="/WEB-INF/views/admin/includes/menu.jsp">
		<c:param name="menu" value="product"/>
	</c:import>
	<c:import url="/WEB-INF/views/admin/includes/header.jsp">
		<c:param name="menu" value="product-list"/>
	</c:import>
	
    <div id="global">
      <div class="container-fluid">
      
      	<!-- 검색 정보 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row cm-fix-height">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">상품 검색</div>
							<div class="panel-body">
							
								<div class="form-group" id="isdisplay">
									<div class="input-group">
										<div class="input-group-addon">
											진열 여부
										</div>
										<div class="radio">
											<label>
												<input type="radio" name="isdisplay" value="isdisplay-true" checked><span class="display-text">진열함</span>
												<input type="radio" name="isdisplay" value="isdisplay-false"><span class="display-text">진열 안함</span>
											</label>
										</div>
									</div>
								</div>
								<div class="form-group" id="isdisplay-main">
									<div class="input-group">
										<div class="input-group-addon">
											메인 진열 여부
										</div>
										<div class="radio">
											<label>
												<input type="radio" name="isdisplay-main" value="isdisplay-main-true" checked>
												<span class="display-text">진열함</span>
												<input type="radio" name="isdisplay-main" value="isdisplay-main-false">
												<span class="display-text">진열 안함</span>
											</label>
										</div>
									</div>
								</div>
								<div class="form-group" id="issell">
									<div class="input-group">
										<div class="input-group-addon">
											판매 여부
										</div>
										<div class="radio">
											<label>
												<input type="radio" name="issell" value="issell-true" checked><span class="display-text">판매함</span>
												<input type="radio" name="issell" value="issell-false"><span class="display-text">판매 안함</span>
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품명
										</div>
										<input type="text" id="product-title" name="title" class="form-control">
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											카테고리명
										</div>
										<input type="text" id="product-category" name="title" class="form-control">
									</div>
								</div>
								
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											등록일(YYYY-MM-DD)
										</div>
										<input type="text" id="product-regist-date" name="title" class="form-control">
									</div>
								</div>

								<button id="search-btn" class="btn btn-primary">검색</button>
								<button id="search-clear" class="btn btn-gray">검색 조건 초기화</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 검색 정보 끝 -->

      	<!-- 검색 결과 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row cm-fix-height">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">상품 검색 결과</div>
							<div class="panel-body">
								<table class="table table-bordered table-hover" id="product-list">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col">상품명</th>
									      <th scope="col">상품옵션</th>
									      <th scope="col">판매가</th>
									      <th scope="col">진열여부</th>
									      <th scope="col">메인 진열여부</th>
									      <th scope="col">판매여부</th>
									      <th scope="col">카테고리</th>
									      <th scope="col">상품 등록일</th>
									    </tr>
									  </thead>
									  <tbody id="product-list-result">
										<!-- 
									  	<tr>
									  		<th>
									  			<div class="form-check">
													<input type="checkbox" class="form-check-input" id="exampleCheck1">
												</div>
									  		</th>
									  		<th>이미지+상품명</th>
									  		<th>상품옵션</th>
									  		<th>판매가</th>
									  		<th>진열여부</th>
									  		<th>메인 진열여부</th>
									  		<th>판매여부</th>
									  		<th>카테고리</th>
									  		<th>상품 등록일</th>
									  	</tr> -->
									  </tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 검색 정보 끝 -->

      </div>
      <c:import url="/WEB-INF/views/admin/includes/footer.jsp" />
    </div>
    
    <script src="${pageContext.request.contextPath}/assets/js/admin/jquery/jquery-2.1.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/jquery/jquery.mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/jquery/jquery.cookie.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/clearmin/fastclick.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/clearmin/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/clearmin/clearmin.min.js"></script>

	<script>
		$(function(){
			
			$("#search-btn").click(function(){
				
				var param = {
					isdisplay: $("#isdisplay input:checked").val()==="isdisplay-true",
					issell: $("#issell input:checked").val()==="issell-true",
					isdisplayMain: $("#isdisplay-main input:checked").val()==="isdisplay-main-true"
				};
				
				if($("#product-title").val()!=="")
					param["productName"] = $("#product-title").val();
				if($("#product-category").val()!=="")
					param["categoryName"] = $("#product-category").val();
				if($("#product-regist-date").val()!=="")
					param["regDateStart"] = $("#product-regist-date").val();
				
				$.ajax({
					url: "/admin/manage/product/list",
					data: JSON.stringify(param),
			    	dataType: 'json',
			    	contentType: "application/json; charset=UTF-8",
			    	type: 'post',
			    	headers: {'X-CSRF-TOKEN': '${_csrf.token}'},
			    	success: function(productListResponse){
			    		if(productListResponse.result==="success"){
			    			var products = productListResponse.data;
			    			console.log(products);
			    			for(var i=0; i<products.length; i++){
			    				$("#product-list-result").append(
			    					
			    				);
			    			}
			    		}
			    	}
				});
			});
		});
	</script>

  </body>
</html>