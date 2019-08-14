<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/bootstrap/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/order-detail.css" rel="stylesheet">
	<!-- icon -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/small-n-flat.css" rel="stylesheet">
</head>

<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/client/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-lg-3">
				<h1 class="my-4">Cafe24 Mall</h1>
				<div class="list-group">
					<table class="table table-bordered table-hover" id="product-category">
						<c:forEach items="${infos.categorys.data }"	var="category" varStatus="status">	
							<tr data-no=<fmt:formatNumber value="${category.categoryNo }" type="number"/>>
								<td style="padding-left:${30*category.depth }px">
									${category.categoryName }
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<table class="table" id="product-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">상품명(옵션)</th>
							<th scope="col">판매가</th>
							<th scope="col">수량</th>
							<th scope="col">배송비</th>
						</tr>
					</thead>
					${infos.products.data }
					<tbody>
						<c:forEach items="${infos.products.data.products }"	var="product" varStatus="status">	
							<tr>
								<td>
									<img src=${product.image } class="product-image">
									${product.title }<br/>
									<span class="product-option">(
										<c:forEach items="${product.productOptionVo }" var="option">
											${option.optionValue }
										</c:forEach>
									)</span>
								</td>
								<td>
									&#8361;<fmt:formatNumber value="${product.price }" type="number"/>
								</td>
								<td>
									<fmt:formatNumber value="${product.quantity }" type="number"/>
								</td>
								<td>		
									&#8361;<fmt:formatNumber value="${product.shippingPrice }" type="number"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<form>
					<div class="form-group">
						<label>받는 사람</label>
						<input type="text" id="reciever" class="form-control">
					</div>
					<div class="form-group">
						<label>받는 사람의 주소</label>
						<input type="text" id="recieverAddress" class="form-control">
					</div>
					<div class="form-group">
						<label>배송 메세지</label>
						<input type="text" id="message" class="form-control">
					</div>
					<div class="form-group">
						<label>결제 수단</label>
						<input type="text" id="paymethod" class="form-control">
					</div>
					<c:if test="${member==null }">
						<div class="form-group">
							<label>휴대폰 번호</label>
							<input type="text" id="phone" class="form-control">
						</div>
						<div class="form-group">
							<label>주문 조회 비밀번호</label>
							<input type="password" id="nonmember-pass" class="form-control">
						</div>
					</c:if>
				</form>
				
				<div id="final-payment">
					최종 결제 금액 : &#8361;
					<span id="final-price">0</span>
				</div>

			</div>
			<!-- /.col-lg-9 -->

		</div>

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/client/includes/footer.jsp' />
	<!-- /.Footer -->
	
	<script>

	$(function(){

	    var currentPosition = parseInt($(".list-group").css("top"));
	    $(window).scroll(function() { 
	    	var position = $(window).scrollTop();
	    	$(".list-group").stop().animate({"top":position+currentPosition+"px"},500);
	    });
	    
	    
	    $("#cart-list").click(function(){
	    	window.location.href = "/cart";
	    });

	    var finalPrice = 0;
	    for(var i=1; i<$("#product-table tr").length; i++){
	    	var price = parseInt($("#product-table tr")[i].children[1].innerText.replace(",","").substring(1));
	    	var quantity = parseInt($("#product-table tr")[i].children[2].innerText.replace(",",""));
	    	var shippingPrice = parseInt($("#product-table tr")[i].children[3].innerText.replace(",","").substring(1));
	    	
	    	finalPrice += (price*quantity+shippingPrice);
	    }
	    
	    $("#final-price").html(finalPrice);
	});
		
	</script>
	
</body>

</html>