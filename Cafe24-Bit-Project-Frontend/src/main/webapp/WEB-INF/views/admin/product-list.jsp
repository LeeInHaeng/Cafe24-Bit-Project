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
									  			<div class="form-check" data-no="32">
													<input type="checkbox" class="form-check-input" id="exampleCheck1">
												</div>
									  		</th>
									  		<th>
									  			<img src="/images/2019777567341.jpg" class="product-image">
									  			베이지색 블라우스9
									  		</th>
									  		<th>
									  			<p>색상:흰색</p>
									  			<p>색상:빨강</p>
									  			<p>사이즈:80</p>
									  			<p>사이즈:90</p>
									  		</th>
									  		<th>&#8361;99000</th>
									  		<th>진열함</th>
									  		<th>메인에 진열함</th>
									  		<th>판매함</th>
									  		<th>수정된 카테고리</th>
									  		<th>2019-08-07</th>
									  	</tr> 
									  	 -->
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
			
			var searchProduct = function(){
				
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
				    			$("#product-list-result").empty();
				    			for(var i=0; i<products.length; i++){
				    				
				    				var productNo = products[i].productNo;
				    				var productImage = products[i].image;
				    				var productTitle = products[i].title;
				    				var productPrice = products[i].price;
				    				var isdisplay = products[i].isdisplay ? '진열함' : '진열안함';
				    				var isdisplayMain = products[i].isdisplayMain ? '메인에 진열함' : '메인에 진열안함';
				    				var issell = products[i].issell ? '판매함' : '판매안함';
				    				var productCategory = products[i].category;
				    				var productRegDate = products[i].regDate.split(' ')[0];
				    				
				    				var productOption = "";
				    				for(var j=0; j<products[i].productOptionVo.length; j++)
				    					productOption += ("<p>"+products[i].productOptionVo[j].optionName+":"+products[i].productOptionVo[j].optionValue+"</p>");
				    				
				    				$("#product-list-result").append(
							    		'<tr>'+
									  		'<th>'+
								  				'<div class="form-check" data-no="'+productNo+'">'+
													'<input type="checkbox" class="form-check-input">'+
												'</div>'+
								  			'</th>'+
								  			'<th>'+
								  				'<img src="'+productImage+'" class="product-image">'+
								  				productTitle+
								  			'</th>'+
								  			'<th>'+
								  			productOption+
								  			'</th>'+
								  			'<th>&#8361;'+productPrice+'</th>'+
								  			'<th>'+isdisplay+'</th>'+
								  			'<th>'+isdisplayMain+'</th>'+
								  			'<th>'+issell+'</th>'+
								  			'<th>'+productCategory+'</th>'+
								  			'<th>'+productRegDate+'</th>'+
							  			'</tr>'
				    				);
				    			}
				    		}
				    	}
					});
			}
			
			$("#search-btn").click(searchProduct);
			
			$("#search-clear").click(function(){
				$("#isdisplay input").removeAttr("checked");
				$("#issell input").removeAttr("checked");
				$("#isdisplay-main input").removeAttr("checked");

				$($("#isdisplay input")[0]).prop("checked", true);
				$($("#issell input")[0]).prop("checked", true);
				$($("#isdisplay-main input")[0]).prop("checked", true);
				
				$("#product-title").val("");
				$("#product-category").val("");
				$("#product-regist-date").val("");
			});
			
			$(document).keyup(function(key){
				if(key.keyCode===13){
					searchProduct();
				}
			});
		});
	</script>

  </body>
</html>