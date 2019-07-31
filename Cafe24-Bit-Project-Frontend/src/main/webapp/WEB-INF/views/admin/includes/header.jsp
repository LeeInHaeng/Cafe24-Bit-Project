<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>        

    <header id="cm-header">
      <nav class="cm-navbar cm-navbar-primary">
        <div class="btn btn-primary md-menu-white hidden-md hidden-lg" data-toggle="cm-menu"></div>
        <div class="cm-flex"><h1>Cafe24Mall 관리자 페이지</h1></div>
      </nav>
      	<c:choose>
      		<c:when test='${param.menu == "product" }'>
			    <nav class="cm-navbar cm-navbar-default cm-navbar-slideup">
					<div class="cm-flex">
						<div class="cm-breadcrumb-container">

				        	<ol class="breadcrumb">
								<li>
									<a href="/admin">관리자 페이지</a>
								</li>
								<li class="active">
									<a href="/admin/manage/product">상품 관리</a>
								</li>
							</ol>
		            			
		            		
						</div>
					</div>
				</nav>
			</c:when>
			
			<c:when test='${param.menu == "product-register" }'>
			    <nav class="cm-navbar cm-navbar-default cm-navbar-slideup">
					<div class="cm-flex">
						<div class="cm-breadcrumb-container">

				        	<ol class="breadcrumb">
								<li>
									<a href="/admin">관리자 페이지</a>
								</li>
								<li>
									<a href="/admin/manage/product">상품 관리</a>
								</li>
								<li class="active">
									<a href="/admin/manage/product/register">상품 등록</a>
								</li>
							</ol>
		            			
		            		
						</div>
					</div>
				</nav>
			</c:when>
		</c:choose>
    </header>