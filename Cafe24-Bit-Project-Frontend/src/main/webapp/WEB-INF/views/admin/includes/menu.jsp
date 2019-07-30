<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>      
    <div id="cm-menu">
      <nav class="cm-navbar cm-navbar-primary">
        <div class="cm-flex"><div class="cm-logo"></div></div>
        <div class="btn btn-primary md-menu-white" data-toggle="cm-menu"></div>
      </nav>
      <div id="cm-menu-content">
        <div id="cm-menu-items-wrapper">
          <div id="cm-menu-scroller">
            <ul class="cm-menu-items">
            
            	<c:choose>
            		<c:when test='${param.menu == "product" }'>
            			<li class=""><a href="/admin" class="sf-house">관리자 페이지</a></li>
            			<li class="active"><a href="/admin/manage/product" class="sf-bag">상품관리</a></li>
            			<li class=""><a href="/admin/manage/user" class="sf-profile-group">회원관리</a></li>
            		</c:when>
            		<c:when test='${param.menu == "member" }'>
            			<li class=""><a href="/admin" class="sf-house">관리자 페이지</a></li>
            			<li class=""><a href="/admin/manage/product" class="sf-bag">상품관리</a></li>
            			<li class="active"><a href="/admin/manage/user" class="sf-profile-group">회원관리</a></li>
            		</c:when>
            		<c:otherwise>
            			<li class=""><a href="/admin" class="sf-house">관리자 페이지</a></li>
            		    <li class=""><a href="/admin/manage/product" class="sf-bag">상품관리</a></li>
            			<li class=""><a href="/admin/manage/user" class="sf-profile-group">회원관리</a></li>
            		</c:otherwise>
            	</c:choose>
            </ul>
          </div>
        </div>
      </div>
    </div>