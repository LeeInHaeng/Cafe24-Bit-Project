<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/bootstrap-clearmin.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/roboto.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/material-design.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/small-n-flat.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/font-awesome.min.css">
    <title>Clearmin Docs</title>
  </head>
  <body class="cm-no-transition cm-1-navbar">
	<c:import url="/WEB-INF/views/admin/includes/menu.jsp" />
	<c:import url="/WEB-INF/views/admin/includes/header.jsp" />
	
    <div id="global">
      <div class="container-fluid">
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
      		<div class="col-sm-6" id="member-manage">
      		    <div class="panel panel-default">
      				<div class="panel-heading">회원 관리</div>
      				<div class="panel-body demo-btn" style="min-height: 196px;">
      					회원을 관리할 수 있습니다.
      				</div>
      			</div>
      		</div>
      	</div>
      </div>
      <c:import url="/WEB-INF/views/admin/includes/footer.jsp" />
    </div>
    
    <script src="${pageContext.request.contextPath}/assets/js/admin/jquery-2.1.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/jquery.mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/jquery.cookie.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/fastclick.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/admin/clearmin.min.js"></script>
    
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
    		
    		$("#member-manage").click(function(){
    			window.location.href='/admin/manage/user';
    		});
    	});
    </script>
  </body>
</html>