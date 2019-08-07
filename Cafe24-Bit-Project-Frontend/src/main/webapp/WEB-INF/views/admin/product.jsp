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
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/product.css">
    <title>Cafe24mall 관리자</title>
  </head>
  <body class="cm-no-transition cm-1-navbar">
  	<c:import url="/WEB-INF/views/admin/includes/menu.jsp">
		<c:param name="menu" value="product"/>
	</c:import>
	<c:import url="/WEB-INF/views/admin/includes/header.jsp">
		<c:param name="menu" value="product"/>
	</c:import>
	
    <div id="global">
      <div class="container-fluid" style="margin-top:40px;">
      	<div class="panel panel-default">
      		<div class="panel-body">
      			<div class="row cm-fix-height">
		      		<div class="col-sm-6" id="product-register">
		      			<div class="panel panel-default">
		      				<div class="panel-heading">상품 등록</div>
		      				<div class="panel-body demo-btn" style="min-height: 196px;">
		      				새로운 상품을 등록할 수 있습니다.
		      				</div>
		      			</div>
		      		</div>
		      		<div class="col-sm-6" id="product-list">
		      		    <div class="panel panel-default">
		      				<div class="panel-heading">상품 목록</div>
		      				<div class="panel-body demo-btn" style="min-height: 196px;">
		      				상품을 검색하고, 검색 결과로 나온 상품에 대해서 수정할 수 있습니다.
		      				</div>
		      			</div>
		      		</div>
		      	</div>
		      	<div class="row cm-fix-height">
		      	    <div class="col-sm-6" id="product-category">
		      		    <div class="panel panel-default">
		      				<div class="panel-heading">상품 분류 관리</div>
		      				<div class="panel-body demo-btn" style="min-height: 196px;">
		      				상품의 카테고리를 추가하거나 삭제할 수 있습니다.
		      				</div>
		      			</div>
		      		</div>
		      	</div>
      		</div>
      	</div>
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
    		$("#product-register").click(function(){
    			window.location.href='/admin/manage/product/register';
    		});
    		
    		$("#product-list").click(function(){
    			window.location.href='/admin/manage/product/list';
    		});
    		
    		$("#product-category").click(function(){
    			window.location.href='/admin/manage/product/category';
    		});
    	});
    </script>
  </body>
</html>
