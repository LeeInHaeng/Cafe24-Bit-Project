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
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/shop-homepage.css" rel="stylesheet">
	<!-- icon -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/small-n-flat.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/client/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->
	
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<h1 class="my-4">Cafe24 Mall</h1>
				<c:if test="${infos.categorys.data != null }">
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
				</c:if>
			</div>
			<!-- /.col-lg-3 -->

			<c:if test="${infos.products.data != null }">
			<div class="col-lg-9">
				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<c:forEach items="${infos.products.data.products }" var="product" varStatus="status">
							<c:if test="${status.index==0 }">
								<li data-target="#carouselExampleIndicators" data-slide-to="${status.index }" class="active"></li>
							</c:if>
							<c:if test="${status.index!=0 }">
								<li data-target="#carouselExampleIndicators" data-slide-to="${status.index }"></li>
							</c:if>
						</c:forEach>
					</ol>
					<div class="carousel-inner" role="listbox">
						<c:forEach items="${infos.products.data.products }" var="product" varStatus="status">
							<c:if test="${status.index==0 }">
								<div class="carousel-item active" data-no="<fmt:formatNumber value="${product.productNo }" type="number"/>">
									<img class="d-block img-fluid" src="${product.image }">
								</div>
							</c:if>
							<c:if test="${status.index!=0 }">
								<div class="carousel-item" data-no="<fmt:formatNumber value="${product.productNo }" type="number"/>">
									<img class="d-block img-fluid" src="${product.image }">
								</div>
							</c:if>
						</c:forEach>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>

				<div class="row">
					<c:forEach items="${infos.products.data.products }" var="product" varStatus="status">
						<div class="col-lg-4 col-md-6 mb-4 product-container" data-no="<fmt:formatNumber value="${product.productNo }" type="number"/>">
							<div class="card h-100">
								<img class="card-img-top" src="${product.image }" alt="">
								<div class="card-body">
									<h4 class="card-title">
										${product.title }
									</h4>
									<h5>&#8361;<fmt:formatNumber value="${product.price }" type="number"/></h5>
									<p class="card-text">
										${fn:replace(product.description, newLine, "<br>") }
									</p>
								</div>
								<div class="card-footer">
								</div>
							</div>
						</div>
					</c:forEach>

					<div class="pager">
						<c:if test="${infos.products.data != null }">
							<ul>
								<c:if test="${infos.products.data.prevPage > 0 }" >
									<li><a href="/main/${infos.products.data.prevPage }">◀</a></li>
								</c:if>
	
								<c:forEach begin="${infos.products.data.beginPage }" end="${infos.products.data.beginPage + infos.products.data.pageSize - 1 }" var="page">
									<c:choose>
										<c:when test="${page < infos.products.data.endPage+1 }">
											<li><a href="/main/${page }">${page }</a></li>
										</c:when> 
									</c:choose>
								</c:forEach>
								
								<c:if test="${infos.products.data.nextPage > 0 }" >
									<li><a href="/main/${infos.products.data.nextPage }">▶</a></li>
								</c:if>	
							</ul>
						</c:if>
					</div>

				</div>
				<!-- /.row -->
			</div>
			<!-- /.col-lg-9 -->
			</c:if>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/client/includes/footer.jsp' />
	<!-- /.Footer -->
	
	<script>
	$(function() {
        
		$('.list-group-item').on('click', function() {
			$('.sf-sign', this)
		      .toggleClass('sf-sign-right')
		      .toggleClass('sf-sign-down');
		});

	    $(document).on("click", ".carousel-item", function(e){
	    	window.location.href="/detail/" + $(this).data("no");
	    });
	    
	    $(document).on("click", ".product-container", function(e){
	    	window.location.href="/detail/" + $(this).data("no");
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
