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
	<link href="${pageContext.servletContext.contextPath }/assets/css/client/shop-login.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/client/includes/navigation.jsp'>
		<c:param name="active" value="login" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
 		<div class="card card-container">
        	<img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin" name="loginForm">
                <span id="reauth-memberid" class="reauth-memberid"></span>
                <input type="text" id="memberid" class="form-control" placeholder="아이디" name="memberid" required autofocus>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호" name="password" required>
                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> 자동 로그인
                    </label>
                </div>
                <button id="login-btn" class="btn btn-lg btn-primary btn-block btn-signin" type="submit">로그인</button>
            </form><!-- /form -->
            <a href="javascript:loginForm.submit();" class="forgot-password">
                비밀번호를 잊으셨습니까?
            </a>
        </div>
        <!-- /.card-container -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/client/includes/footer.jsp' />
	<!-- /.Footer -->
	
	<script>
	$(function(){
		$("form").submit(function(e){
			var param = {
				id: $("#memberid").val(),
				pass: $("#inputPassword").val()
			};
				
			$.ajax({
		    	url: "/member/login",
		    	data: JSON.stringify(param),
		    	dataType: 'json',
		    	contentType: "application/json; charset=UTF-8",
		    	type: 'post',
		    	headers: {'X-CSRF-TOKEN': '${_csrf.token}'},
		    	success: function(loginResponse){
		    		if(loginResponse.result==="success" && loginResponse.data.id!==undefined){
		    			alert("로그인 성공!");
		    			window.location.href = "/";
		    		}
		    		else{
		    			alert(loginResponse.message || loginResponse.data);
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