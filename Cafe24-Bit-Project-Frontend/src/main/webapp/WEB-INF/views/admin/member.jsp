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
  	<c:import url="/WEB-INF/views/admin/includes/menu.jsp">
		<c:param name="menu" value="member"/>
	</c:import>
	<c:import url="/WEB-INF/views/admin/includes/header.jsp" />
	
    <div id="global">
      <div class="container-fluid">
        <div class="panel panel-default"  id="member-manage">
	          <div class="panel-body">
		    <h2 style="margin:0">회원 관리 페이지</h2> 
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
		$("#member-manage").click(function(){
			window.location.href='/admin/manage/user';
		});
    });
    </script>
  </body>
</html>
