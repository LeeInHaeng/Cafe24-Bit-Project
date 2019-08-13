<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/shop-join.css" rel="stylesheet">
	<!-- icon -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/small-n-flat.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/client/includes/navigation.jsp'>
		<c:param name="active" value="join" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
 		<div class="card card-container">
 			<h3>회원가입</h3>
 			<form id="registForm">
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-user-id prepend-img"></i>
 						</span>
 					</div>
 					<input type="text" class="form-control" id="memberid" placeholder="아이디" required/>
 					<input type="button" class="btn btn-outline-info" id="check-id" value="아이디 중복 체크">
					<img style="display:none" id="check-image" src="${pageContext.servletContext.contextPath }/assets/images/client/check.png" />
 				</div>
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-lock prepend-img"></i>
 						</span>
 					</div>
 					<input type="password" class="form-control" id="memberpass" placeholder="비밀번호" required/>
 				</div>
 				 <div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-profile prepend-img"></i>
 						</span>
 					</div>
 					<input type="text" class="form-control" id="membername" placeholder="이름" required/>
 				</div>
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-map-map-marker prepend-img"></i>
 						</span>
 					</div>
 					<input type="text" class="form-control" id="memberaddress" placeholder="주소" required/>
 				</div>
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-phone prepend-img"></i>
 						</span>
 					</div>
 					<input type="text" class="form-control" id="membertel" placeholder="전화번호" required/>
 				</div>
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-device-mobile-phone prepend-img"></i>
 						</span>
 					</div>
 					<input type="text" class="form-control" id="memberphone" placeholder="휴대폰 번호" required/>
 				</div>
 				<div class="form-group input-group">
	 				<label>문자 수신 여부</label>
	 				<label class="radio-inline"><input type="radio" name="ismessage" value="message-true" checked>수신</label>
					<label class="radio-inline"><input type="radio" name="ismessage" value="message-false">미수신</label>
 				</div>
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-envelope-letter prepend-img"></i>
 						</span>
 					</div>
 					<input type="email" class="form-control" id="memberemail" placeholder="이메일" required/>
 				</div>
				<div class="form-group input-group">
	 				<label>이메일 수신 여부</label>
	 				<label class="radio-inline"><input type="radio" name="isemail" value="email-true" checked>수신</label>
					<label class="radio-inline"><input type="radio" name="isemail" value="email-false">미수신</label>
 				</div>
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-calendar prepend-img"></i>
 						</span>
 					</div>
 					<input type="text" class="form-control" id="memberbirth" placeholder="생년월일(YYYY-MM-DD)" required/>
 				</div>
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-building prepend-img"></i>
 						</span>
 					</div>
 					<input type="text" class="form-control" id="refund-name" placeholder="환불 계좌 은행명" required/>
 				</div>
 				<div class="form-group input-group">
 					<div class="input-group-prepend">
 						<span class="input-group-text">
 							<i class="sf-bullhorn prepend-img"></i>
 						</span>
 					</div>
 					<input type="text" class="form-control" id="refund-number" placeholder="환불 계좌 번호" required/>
 				</div>
 				<button id="register-btn" class="btn btn-lg btn-primary btn-block btn-register" type="submit">회원가입</button>
 			</form>
        </div>
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/client/includes/footer.jsp' />
	<!-- /.Footer -->
	
	<script>
	$(function(){
		$("#check-id").click(function(e){
			e.preventDefault();
			
			var memberId = $("#memberid").val()
			
			if(memberId==="")
				return;
			
			  $.ajax({
	    		  url: "/member/check/" + memberId,
	    		  type: 'get',
	    		  success: function(checkResponse){
	    			  var jsonCheckResponse = JSON.parse(checkResponse);
	    			  if(jsonCheckResponse.result==="success" && jsonCheckResponse.data===false){
	    				  $("#check-image").show();
	    			  }else{
	    				  alert(jsonCheckResponse.message || jsonCheckResponse.data);
	    				  $("#check-image").hide();
	    				  $("#memberid").val("");
	    				  $("#memberid").focus();
	    			  }
	    		  }
			  });
		});
		
		$("#registForm").submit(function(e){
			
			var param = {
				id: $("#memberid").val(),
				pass: $("#memberpass").val(),
				name: $("#membername").val(),
				address: $("#memberaddress").val(),
				tel: $("#membertel").val(),
				phone: $("#memberphone").val(),
				ismessage: $("input[name*='ismessage']:checked").val()==="message-true",
				email: $("#memberemail").val(),
				ismail: $("input[name*='isemail']:checked").val()==="email-true",
				birth: $("#memberbirth").val(),
				refundName: $("#refund-name").val(),
				refundNumber: $("#refund-number").val()
			};
			
			$.ajax({
		    	url: "/member/join",
		    	data: JSON.stringify(param),
		    	dataType: 'json',
		    	contentType: "application/json; charset=UTF-8",
		    	type: 'post',
		    	headers: {'X-CSRF-TOKEN': '${_csrf.token}'},
		    	success: function(registResponse){
		    		if(registResponse.result==="success" && registResponse.data==null){
		    			alert("회원가입 성공!");
		    			window.location.href = "/member/login";
		    		}
		    		else{
		    			alert(registResponse.message || registResponse.data);
		    		}
		    	}
			});
			
			return false;
		});
		
	    $("#cart-list").click(function(){
	    	window.location.href = "/cart";
	    });
	});
	</script>
	
</body>
</html>