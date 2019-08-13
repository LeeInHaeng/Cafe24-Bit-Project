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
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/shop-detail.css" rel="stylesheet">
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

				<div class="card mt-4">
					<img class="card-img-top img-fluid"
						src="${infos.product.data.image }" alt="">
					<div class="card-body">
						<h3 class="card-title">${infos.product.data.title }</h3>
						<h4>
							&#8361;<fmt:formatNumber value="${infos.product.data.price }" type="number"/>
							(+ 배송비 : &#8361;<fmt:formatNumber value="${infos.product.data.shippingPrice }" type="number"/>)
						</h4>
						<p class="card-text">
							${infos.product.data.description }
						</p>
						<div class="option-group">
							<button type="button" class="btn btn-outline-info btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								상품의 옵션을 선택해 주세요.
							</button>
							<div class="dropdown-menu">
							<c:forEach items="${infos.product.data.productQuantityVo }" var="option" varStatus="status">
							    <button class="dropdown-item">${option.optionCode }</button>
							</c:forEach>
							 </div>
							 
							<div id="option-result">
							</div>
						</div>
					</div>			
					<div>		
						<button id="buy-btn" class="btn btn-outline-dark btn-lg">즉시 구매</button>
						<button id="add-cart-btn" class="btn btn-outline-secondary btn-lg">장바구니에 담기</button>
					</div>
				</div>
				<!-- /.card -->

				<div class="card card-outline-secondary my-4">
					<div class="card-header">상세 설명</div>
					<div class="card-body">
						<p>
							${fn:replace(infos.product.data.descriptionDetail, newLine, "<br>") }
						</p>
						<c:forEach items="${infos.product.data.productImageVo }" var="addImage" varStatus="status">
							<p>
							<img src="${addImage.imageDetail }"/>
							</p>
						</c:forEach>
					</div>
				</div>
				<!-- /.card -->

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
		
		if("${infos.product.result }"==="fail"){
			alert("잘못된 요청입니다!");
			window.location.href="/";
		}
		
	    $(document).on("click",".dropdown-item", function(e){
	    	for(var i=0; i<$(".option-code").length; i++){
	    		if($(".option-code")[i].innerHTML===$(this).html())
	    			return;
	    	}
	    	
	    	$("#option-result").append(
				'<div class="option-result-value" data-code="'+$(this).html()+'">' +
					'<span class="option-code">'+$(this).html()+'</span>' +
			        '<div class="quantity-controller">' +
			            '<div class="input-group">' +
			            	'<span class="input-group-btn">' +
				              	'<button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="'+$(this).html()+'">' +
				                  	'<span class="sf-sign sf-sign-delete"></span>' +
				                '</button>' +
			                '</span>' +
			                '<input type="text" name="'+$(this).html()+'" class="form-control input-number" size="1" value="1" min="1" max="99">' +
			                '<span class="input-group-btn">' +
				              	'<button type="button" class="btn btn-default btn-number" data-type="plus" data-field="'+$(this).html()+'">' +
				                	'<span class="sf-sign sf-sign-add"></span>' +
				                '</button>' +
			                '</span>' +
			            '</div>' +
			        '</div>' +
			        '<div class="sf-sign sf-sign-error option-delete" data-code="'+$(this).html()+'"></div>'+
				'</div>'
	    	);
	    });

		$(document).on("click",'.btn-number',function(e){
		    e.preventDefault();
		    
		    fieldName = $(this).attr('data-field');
		    type      = $(this).attr('data-type');
		    var input = $("input[name='"+fieldName+"']");
		    var currentVal = parseInt(input.val());
		    if (!isNaN(currentVal)) {
		        if(type == 'minus') {
		            
		            if(currentVal > input.attr('min')) {
		                input.val(currentVal - 1).change();
		            } 
		            if(parseInt(input.val()) == input.attr('min')) {
		                $(this).attr('disabled', true);
		            }

		        } else if(type == 'plus') {

		            if(currentVal < input.attr('max')) {
		                input.val(currentVal + 1).change();
		            }
		            if(parseInt(input.val()) == input.attr('max')) {
		                $(this).attr('disabled', true);
		            }

		        }
		    } else {
		        input.val(0);
		    }
		});
		$(document).on("focusin",'.input-number',function(){
		   $(this).data('oldValue', $(this).val());
		});
		$(document).on("change",'.input-number',function() {
		    
		    minValue =  parseInt($(this).attr('min'));
		    maxValue =  parseInt($(this).attr('max'));
		    valueCurrent = parseInt($(this).val());
		    
		    name = $(this).attr('name');
		    if(valueCurrent >= minValue) {
		        $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
		    } else {
		        alert('최대 주문 수량을 넘었습니다.');
		        $(this).val($(this).data('oldValue'));
		    }
		    if(valueCurrent <= maxValue) {
		        $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
		    } else {
		        alert('최대 주문 수량을 넘었습니다.');
		        $(this).val($(this).data('oldValue'));
		    }
		    
		    
		});
		$(document).on("keydown",".input-number",function (e) {
		        // Allow: backspace, delete, tab, escape, enter and .
		        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
		             // Allow: Ctrl+A
		            (e.keyCode == 65 && e.ctrlKey === true) || 
		             // Allow: home, end, left, right
		            (e.keyCode >= 35 && e.keyCode <= 39)) {
		                 // let it happen, don't do anything
		                 return;
		        }
		        // Ensure that it is a number and stop the keypress
		        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
		            e.preventDefault();
		        }
		    });
		
		$(document).on("click", ".option-delete", function(e){
			for(var i=0; i<$(".option-result-value").length; i++){
				if($($(".option-result-value")[i]).data("code")===$(this).data("code"))
					$($(".option-result-value")[i]).remove();
			}
		});
		
		$("#add-cart-btn").click(function(){
			if($("#option-result")[0].childElementCount===0){
				alert("옵션을 선택해 주세요.");
				return;
			}

			for(var i=0; i<$("#option-result")[0].childElementCount; i++){
				var optionCode = $($($("#option-result")[0]).children()[i]).data("code");
				var param = {
					productNo : parseInt(window.location.pathname.split('/')[window.location.pathname.split('/').length-1]),
					optionCode : optionCode,
					quantity : parseInt($($(".quantity-controller input[name='"+optionCode+"']")[0]).val())
				};

				$.ajax({
				    url: "/cart",
				    data: JSON.stringify(param),
				    dataType: 'json',
				    contentType: "application/json; charset=UTF-8",
				    type: 'post',
				    headers: {'X-CSRF-TOKEN': '${_csrf.token}'},
				    success: function(addCartResponse){
						if(addCartResponse.result==="success"){
							alert("장바구니 추가 완료!");
						}
						else
							alert(addCartResponse.message || addCartResponse.data);
				    }
				});
			}

		});
		
	    var currentPosition = parseInt($(".list-group").css("top"));
	    $(window).scroll(function() { 
	    	var position = $(window).scrollTop();
	    	$(".list-group").stop().animate({"top":position+currentPosition+"px"},500);
	    });
	    
	    
	    $("#cart-list").click(function(){
	    	window.location.href = "/cart";
	    });
	});
		
	</script>
	
</body>

</html>