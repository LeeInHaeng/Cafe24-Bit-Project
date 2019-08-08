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

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/member.css">
    <title>Cafe24mall 관리자</title>
  </head>
  <body class="cm-no-transition cm-1-navbar">
  	<c:import url="/WEB-INF/views/admin/includes/menu.jsp">
		<c:param name="menu" value="member"/>
	</c:import>
	<c:import url="/WEB-INF/views/admin/includes/header.jsp">
		<c:param name="menu" value="member"/>
	</c:import>
	
    <div id="global">
      <div class="container-fluid">
      	<!-- 검색 정보 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row cm-fix-height">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">회원 검색</div>
							<div class="panel-body">
							
								<div class="form-group" id="ismessage">
									<div class="input-group">
										<div class="input-group-addon">
											문자 수신 여부
										</div>
										<div class="radio">
											<label>
												<input type="radio" name="ismessage" value="ismessage-true" checked><span class="display-text">수신함</span>
												<input type="radio" name="ismessage" value="ismessage-false"><span class="display-text">수신 안함</span>
											</label>
										</div>
									</div>
								</div>
								<div class="form-group" id="isemail">
									<div class="input-group">
										<div class="input-group-addon">
											이메일 수신 여부
										</div>
										<div class="radio">
											<label>
												<input type="radio" name="isemail" value="isemail-true" checked>
												<span class="display-text">수신함</span>
												<input type="radio" name="isemail" value="isemail-false">
												<span class="display-text">수신 안함</span>
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											회원 아이디
										</div>
										<input type="text" id="memberid" name="memberid" class="form-control">
									</div>
								</div>
								
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											회원 이름
										</div>
										<input type="text" id="membername" name="membername" class="form-control">
									</div>
								</div>
								
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											회원 나이
										</div>
										<input type="text" id="age-start" name="age-start" class="form-control">
										<div class="age-text">세 ~</div>
										<input type="text" id="age-end" name="age-end" class="form-control">
										<div class="age-text">세</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											가입일(YYYY-MM-DD)
										</div>
										<input type="text" id="regdate-start" name="regdate-start" class="form-control">
										<div class="regdate-text">~</div>
										<input type="text" id="regdate-end" name="regdate-end" class="form-control">
									</div>
								</div>
								
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											회원의 상태
										</div>
										<input type="text" id="member-status" name="member-status" class="form-control">
									</div>
								</div>
								
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품의 주문 날짜(YYYY-MM-DD)
										</div>
										<input type="text" id="orderdate-start" name="orderdate-start" class="form-control">
										<div class="orderdate-text">~</div>
										<input type="text" id="orderdate-end" name="orderdate-end" class="form-control">
									</div>
								</div>
								
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품 구매 금액
										</div>
										<div class="buyprice-text">&#8361;</div>
										<input type="text" id="buyprice-start" name="buyprice-start" class="form-control">
										<div class="buyprice-text">~</div>
										<input type="text" id="buyprice-end" name="buyprice-end" class="form-control">
									</div>
								</div>
								
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품 구매 건수
										</div>
										<input type="text" id="buycount-start" name="buycount-start" class="form-control">
										<div class="buycount-text">~</div>
										<input type="text" id="buycount-end" name="buycount-end" class="form-control">
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
							<div class="panel-heading">회원 검색 결과</div>
							<div class="panel-body">
								<table class="table table-bordered table-hover" id="member-list">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col">이름</th>
									      <th scope="col">아이디</th>
									      <th scope="col">등록일</th>
									      <th scope="col">휴대전화</th>
									      <th scope="col">나이</th>
									      <th scope="col">지역</th>
									      <th scope="col">메일/문자 수신</th>
									      <th scope="col">회원 상태</th>
									    </tr>
									  </thead>
									  <tbody id="member-list-result">
									  	<!-- 
									  	<tr>
									  		<th>
								  				<div class="form-check" data-id="user1">
													<input type="checkbox" class="form-check-input">
												</div>
								  			</th>
								  			<th>이름일</th>
								  			<th>user1</th>
								  			<th>2019-08-08</th>
								  			<th>010-1234-1234</th>
								  			<th>26</th>
								  			<th>서울시 마포구 망원동</th>
								  			<th>수신 안함/수신 안함</th>
								  			<th>활성</th>
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
		<!-- 검색 결과 끝 -->
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
    	
    	var searchMember = function(){
			
			var param = {
					ismessage: $("#ismessage input:checked").val()==="ismessage-true",
					ismail: $("#isemail input:checked").val()==="isemail-true"
				};
				
				if($("#memberid").val()!=="")
					param["memberId"] = $("#memberid").val();
				if($("#membername").val()!=="")
					param["name"] = $("#membername").val();
				if($("#age-start").val()!=="")
					param["ageStart"] = $("#age-start").val();
				else
					param["ageStart"] = parseInt("0");
				if($("#age-end").val()!=="")
					param["ageEnd"] = $("#age-end").val();
				else
					param["ageEnd"] = parseInt("0");
				if($("#regdate-start").val()!=="")
					param["joinDateStart"] = $("#regdate-start").val();
				if($("#regdate-end").val()!=="")
					param["joinDateEnd"] = $("#regdate-end").val();
				if($("#member-status").val()!=="")
					param["status"] = $("#member-status").val();
				if($("#orderdate-start").val()!=="")
					param["orderDateStart"] = $("#orderdate-start").val();
				if($("#orderdate-end").val()!=="")
					param["orderDateEnd"] = $("#orderdate-end").val();
				if($("#buyprice-start").val()!=="")
					param["buyPriceStart"] = $("#buyprice-start").val();
				else
					param["buyPriceStart"] = parseInt("0");
				if($("#buyprice-end").val()!=="")
					param["buyPriceEnd"] = $("#buyprice-end").val();
				else
					param["buyPriceEnd"] = parseInt("0");
				if($("#buycount-start").val()!=="")
					param["buyCountStart"] = $("#buycount-start").val();
				else
					param["buyCountStart"] = parseInt("0");
				if($("#buycount-end").val()!=="")
					param["buyCountEnd"] = $("#buycount-end").val();
				else
					param["buyCountEnd"] = parseInt("0");
				
				$.ajax({
					url: "/admin/manage/user/list",
					data: JSON.stringify(param),
			    	dataType: 'json',
			    	contentType: "application/json; charset=UTF-8",
			    	type: 'post',
			    	headers: {'X-CSRF-TOKEN': '${_csrf.token}'},
			    	success: function(userListResponse){
			    		
			    		console.log(userListResponse);
			    		/*
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
			    		*/
			    	}
				});
		}

		$("#search-btn").click(searchMember);
		
		/*
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
		});*/
    });
    </script>
  </body>
</html>
