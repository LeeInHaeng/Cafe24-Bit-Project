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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/custom.css">
    <title>Clearmin Docs</title>
  </head>
  <body class="cm-no-transition cm-1-navbar">
  	<c:import url="/WEB-INF/views/admin/includes/menu.jsp">
		<c:param name="menu" value="product"/>
	</c:import>
	<c:import url="/WEB-INF/views/admin/includes/header.jsp">
		<c:param name="menu" value="product-register"/>
	</c:import>
	
    <div id="global">
      <div class="container-fluid" style="margin-top:40px;">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row cm-fix-height">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">기본 정보</div>
							<div class="panel-body">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품명 (필수)
										</div>
										<input type="text" id="register-title" name="title" class="form-control">
										<span id="title-counter">###</span>
									</div>
									
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											가격
										</div>
										<input type="number" name="price" class="form-control" value="0"/>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											추가되는 마일리지
										</div>
										<input type="number" name="mileageAdd" class="form-control" value="0"/>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품 요약 설명
										</div>
										<input type="text" id="register-description" name="description" class="form-control"/>
										<span id="description-counter">###</span>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품 상세 설명
										</div>
										<textarea style="height:150px;" name="descriptionDetail" class="form-control"></textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											이미지
										</div>
										<div class="col-sm-5" id="rep-image">
											대표 이미지
											<div id="rep-image-upload" class="sf-box-out" style="height:50px;"></div>
										</div>
										<div class="col-sm-5" id="add-image">
											추가 이미지
											<div id="add-image-upload" class="sf-box-out" style="height:50px;"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
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
		$(function() {
		      $('#register-title').keyup(function (e){
		          var title = $(this).val();
		          $('#title-counter').html(title.length + '/200');
		      });
		      $('#register-title').keyup();
		      
		      $('#register-description').keyup(function (e){
		          var title = $(this).val();
		          $('#description-counter').html(title.length + '/255');
		      });
		      $('#register-description').keyup();
		});
	</script>

  </body>
</html>
