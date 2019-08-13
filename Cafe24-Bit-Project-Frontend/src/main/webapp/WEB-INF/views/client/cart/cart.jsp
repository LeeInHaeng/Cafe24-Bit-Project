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
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/cart-detail.css" rel="stylesheet">
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

				<table class="table" id="cart-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col">상품명(옵션)</th>
							<th scope="col">판매가</th>
							<th scope="col">수량</th>
							<th scope="col">배송비</th>
							<th scope="col">장바구니 관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${infos.carts.data }"	var="cart" varStatus="status">	
							<tr>
								<td>
									<div class="form-check" data-no=<fmt:formatNumber value="${cart.cartNo }" type="number"/>>
										<input type="checkbox" class="form-check-input">
									</div>
								</td>
								<td>
									<img src=${cart.image } class="cart-image">
									${cart.title }<br/>
									<span class="cart-option">(
										<c:forEach items="${cart.productOptionVo }" var="option">
											${option.optionValue }
										</c:forEach>
									)</span>
								</td>
								<td>&#8361;<fmt:formatNumber value="${cart.price }" type="number"/></td>
								<td>		
									<fmt:formatNumber value="${cart.quantity }" type="number"/>
								</td>
								<td>&#8361;<fmt:formatNumber value="${cart.shippingPrice }" type="number"/></td>
								<td>
									<button class="btn btn-outline-info btn-sm">옵션 수정</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<button class="btn btn-outline-danger btn-sm">선택 물품 삭제</button>
				
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
	    
	    $(document).on("click", ".form-check-input", function(){
	    	var finalPrice = 0;
	    	for(var i=0; i<$(".form-check-input").length; i++){
	    		if($($(".form-check-input")[i]).is(":checked")){
	    			var checkedTD = $($(".form-check-input")[i]).closest("tr")[0].children;
	    			var onePrice = parseInt(checkedTD[2].innerText.replace(",","").substring(1,checkedTD[2].innerText.length-1));
	    			var productCount = parseInt(checkedTD[3].innerText);
	    			var shippingPrice = parseInt(checkedTD[4].innerText.replace(",","").substring(1,checkedTD[2].innerText.length-1));

	    			finalPrice += (onePrice*productCount+shippingPrice);
	    		}
	    	}
	    	$("#final-price").html(finalPrice);
	    })
	});
		
	</script>
	
</body>

</html>