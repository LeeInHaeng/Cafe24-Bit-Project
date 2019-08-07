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

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/product-register.css">
    <title>Cafe24mall 관리자</title>
  </head>
  <body class="cm-no-transition cm-1-navbar">
  	<c:import url="/WEB-INF/views/admin/includes/menu.jsp">
		<c:param name="menu" value="product"/>
	</c:import>
	<c:import url="/WEB-INF/views/admin/includes/header.jsp">
		<c:param name="menu" value="product-register"/>
	</c:import>
	
    <div id="global">
      <div class="container-fluid">
      
      	<form id="regist-form">
      
      	<!-- 기본 정보 -->
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
										<input type="text" id="register-title" name="title" class="form-control" required>
										<span id="title-counter">###</span>
									</div>
									
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											가격 (필수)
										</div>
										<input type="number" id="price" name="price" class="form-control" value="0" required/>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											추가되는 마일리지
										</div>
										<input type="number" id="mileageAdd" name="mileageAdd" class="form-control" value="0"/>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											배송비
										</div>
										<input type="number" id="shippingPrice" name="shippingPrice" class="form-control" value="0"/>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품 요약 설명 (필수)
										</div>
										<input type="text" id="register-description" name="description" class="form-control" required/>
										<span id="description-counter">###</span>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group descriptionDetail">
										<div class="input-group-addon">
											상품 상세 설명
										</div>
										<textarea id="descriptionDetail" name="descriptionDetail" class="form-control"></textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											이미지
										</div>
										<div class="col-sm-5" id="rep-image">
											대표 이미지
											<div id="rep-image-upload" class="sf-box-out"></div>
											<span id="rep-image-upload-cancel" class="sf-sign-error"></span>
											<input type="file" id="rep-image-upload-file" accept=".jpg,.jpeg,.png,.gif,.bmp"/>
											<img class="image-preview" src="#"/>
										</div>
										<div class="col-sm-5" id="add-image">
											추가 이미지
											<div id="add-image-upload" class="sf-box-out"></div>
											<span id="add-image-upload-cancel" class="sf-sign-error"></span>
											<input type="file" id="add-image-upload-file" accept=".jpg,.jpeg,.png,.gif,.bmp" multiple/>
											<div class="image-multiple-preview"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 기본 정보 끝 -->
		
		<!-- 진열 정보 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row cm-fix-height">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">진열 정보</div>
							<div class="panel-body">
								<div class="form-group" id="isdisplay">
									<div class="input-group">
										<div class="input-group-addon">
											진열 여부
										</div>
										<div class="radio">
											<label>
												<input type="radio" name="isdisplay" value="isdisplay-true"><span class="display-text">진열함</span>
												<input type="radio" name="isdisplay" value="isdisplay-false" checked><span class="display-text">진열 안함</span>
											</label>
										</div>
									</div>
								</div>
								<div class="form-group" id="isdisplay-main">
									<div class="input-group">
										<div class="input-group-addon">
											메인 진열 여부
										</div>
										<div class="radio">
											<label>
												<input type="radio" name="isdisplay-main" value="isdisplay-main-true">
												<span class="display-text">진열함</span>
												<input type="radio" name="isdisplay-main" value="isdisplay-main-false" checked>
												<span class="display-text">진열 안함</span>
											</label>
										</div>
									</div>
								</div>
								<div class="form-group" id="issell">
									<div class="input-group">
										<div class="input-group-addon">
											판매 여부
										</div>
										<div class="radio">
											<label>
												<input type="radio" name="issell" value="issell-true"><span class="display-text">판매함</span>
												<input type="radio" name="issell" value="issell-false" checked><span class="display-text">판매 안함</span>
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											상품 분류 (필수)
										</div>
										<div>
											<c:if test='${infos.categorys.result == "success" }'>
												<table class="table table-bordered table-hover" id="product-category">
													<c:forEach items="${infos.categorys.data }"	var="category" varStatus="status">	
														<tr data-no=<fmt:formatNumber value="${category.categoryNo }" type="number"/>>
															<td style="padding-left:${30*category.depth }px">
																${category.categoryName }
															</td>
														</tr>
													</c:forEach>
												</table>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 진열 정보 끝 -->
		
		<!-- 재고 정보 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row cm-fix-height">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">재고 관리 정보</div>
							<div class="panel-body">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											옵션 추가 (필수)
										</div>
										<input type="text" id="input-option-add" placeholder="여러 개의 옵션을 콤마로 구분하여 추가할 수 있습니다."/>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											옵션 값 추가
										</div>
										<div class="option-value-list">
											<button type="button" class="btn btn-turquoise" id="quantity-add">재고 추가하기</button>
										</div>
									</div>
								</div>
								<div class="form-group" id="quantity-manage">
									<div class="input-group">
										<div class="input-group-addon">
											재고 관리 (필수)
										</div>
										<div id="quantity-manage-list">
											
										</div>
										<input type="number" placeholder="재고 수량" id="quantity-count" class="form-control" value="0"/>
									</div>
									<div id="quantity-result"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 재고 정보 끝 -->
		
		<nav class="cm-navbar cm-navbar-midnight text-center nav-register">
			<button type="submit" class="btn btn-success btn-register">상품 등록</button>
		</nav>
		
		</form>
		
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

		      $("#rep-image-upload").click(function(e){
		    	  e.preventDefault();
		    	  $('#rep-image-upload-file').click();
		      });
		      
		      $("#add-image-upload").click(function(e){
		    	  e.preventDefault();
		    	  $('#add-image-upload-file').click();
		      });
		      
		      function oneImagePreview(input) {
		          if (input.files && input.files[0]) {
		              var reader = new FileReader();
		              reader.onload = function(e) {
		            	  $('.image-preview').show();
		                  $('.image-preview').attr('src', e.target.result);
		              }
		              reader.readAsDataURL(input.files[0]);
		          }
		      }
		      
		      function multipleImagePreview(input, placeToInsertImagePreview) {

		          if (input.files) {
		              var filesAmount = input.files.length;
	                  $('.image-multiple-preview').show();

		              for (i = 0; i < filesAmount; i++) {
		                  var reader = new FileReader();

		                  reader.onload = function(event) {
		                      $($.parseHTML('<img>')).attr({src: event.target.result, style: "width:240px;"}).appendTo(placeToInsertImagePreview);
		                  }

		                  reader.readAsDataURL(input.files[i]);
		              }
		          }

		      };
	
		      $("#rep-image-upload-file").change(function() {
		    	  oneImagePreview(this);
		      });
		      
		      $('#add-image-upload-file').change(function() {
		    	  multipleImagePreview(this, 'div.image-multiple-preview');
		      });
		      
		      $('#rep-image-upload-cancel').click(function(){
		    	  $("#rep-image-upload-file").val('');
		    	  $(".image-preview").attr('src', '#');
		    	  $(".image-preview").hide();
		      });
		      $('#add-image-upload-cancel').click(function(){
		    	  $('#add-image-upload-file').val('');
		    	  $(".image-multiple-preview").empty();
		      });
		      
		      $('.table td').click(function(){
		    	  $('.table td').removeClass('selected');
		    	  $(this).addClass('selected');
		      });
		      
		      $('#input-option-add').keydown(function(key){
		    	  if (key.keyCode == 13 && $(this).val()!="") {
		    		  var options = $(this).val().split(',');
		    		  $(".option-value-list").show();
		    		  for(var i=0; i<options.length; i++){
		    			  $(".option-value-list").append(
		    					  '<div class="form-group option-value-'+ options[i]+'">' + 
		    					  '<div class="input-group">' +
		    					  '<div class="input-group-addon">' + 
		    					  	options[i] + 
		    					  '</div>' + 
		    					  '<input type="text" id="value-'+options[i]+'" class="form-control detail-option-add"/>' + 
		    					  '<span class="option-value-remove sf-sign-error"></span>'
		    			  );
		    		  }
		    		  $(this).val('');
		    		  $(this).focus();
		    		}
		      });
		      
		      $(document).on("keydown",".detail-option-add",function(key){ 
		    	  if (key.keyCode == 13 && $(this).val()!="") {
		    		  var detailOptions = $(this).val().split(',');
		    		  for(var i=0; i<detailOptions.length; i++){
		    			  $(this)[0].parentElement.parentElement.append(detailOptions[i]+' ');
		    		  }
		    		  $(this).val('');
		    		  $(this).focus();
			      }
		      });
		      
		      $(document).on("click",".option-value-remove", function(e){
		    	  e.target.parentElement.parentElement.remove();
		    	  if($(".option-value-list")[0].children.length===1){
		    		  $(".option-value-list").hide();
		    		  $("#quantity-manage-list").empty();
		    		  $("#quantity-manage").hide();
		    	  }
		      });
		      
		      $("#quantity-add").click(function(){
		    	  
		    	  $("#quantity-manage").show();
		    	  $("#quantity-manage-list").empty();
		    	  
		    	  for(var i=0; i<$(".option-value-list .form-group").length; i++){
		    		  var detailOptions = $(".option-value-list .form-group")[i].innerText.split('\n')[1].split(' ');
		    		  var selectBox = '<select class="form-control">';
		    		  for(var j=0; j<detailOptions.length; j++){
		    			  if(j==0)
		    				  selectBox += ('<option value="'+detailOptions[j]+'" selected>'+detailOptions[j]+'</option>');
		    			  else
		    				selectBox += ('<option value="'+detailOptions[j]+'">'+detailOptions[j]+'</option>');
		    		  }
		    		  selectBox += '</select>';
		    		  
		    		  $("#quantity-manage-list").append(selectBox);
		    		  $("#quantity-result").empty();
		      	  }
		      });
		      
		      $(document).on("keydown","#quantity-count",function(key){ 
		    	  if (key.keyCode == 13 && $(this).val()!="") {
		    		  var quantityOptions = '';
		    		  var optionDiv = $("#quantity-manage-list")[0].children;
		    		  for(var i=0; i<optionDiv.length; i++){
		    			  quantityOptions += optionDiv[i].value+"/";
		    		  }
		    		  $("#quantity-result").append(quantityOptions.substr(0,quantityOptions.length-1) + " : " + $(this).val()+'<br/>');
		    		  $(this).val('');
		    		  $(this).focus();
			      }
		      });
   
		      $(document).on("keydown", ":input:not(textarea):not(:submit)", function(event) {
		    	    if (event.key == "Enter") {
		    	        event.preventDefault();
		    	    }
		    	});
		      
		      $("#regist-form").submit(function(e){
		    	  var repImage = $("#rep-image-upload-file").prop("files")[0];  
		    	  var addImage = $("#add-image-upload-file").prop("files");
		    	  
		    	  if($("#product-category .selected").length==0){
		    		  alert("상품 분류는 필수 선택 항목 입니다.");
		    		  return false;
		    	  }
		    	  
		    	  if($(".option-value-list .form-group").length==0){
		    		  alert("상품 옵션은 필수 추가 항목 입니다.");
		    		  return false;
		    	  }
		    	  
		    	  if($("#quantity-result").html().split("<br>")[0]===""){
		    		  alert("상품 옵션에 따른 수량은 필수 추가 항목 입니다.");
		    		  return false;
		    	  }
		    	  
		    	  var formData = new FormData();
		    	  formData.append("repImage", repImage);
		    	  for(var i=0; i<addImage.length; i++)
		    	  	formData.append("addImage[]", addImage[i]);

		    	  $.ajax({
		    		  url: "/api/admin/manage/product/image",
		    		  data: formData,
		    		  dataType: 'text',
		    		  processData: false,
		    		  contentType: false,
		    		  type: 'post',
		    		  headers: {'X-CSRF-TOKEN': '${_csrf.token}'},
		    		  success: function(uploadResponse){
		    			  var jsonUploadResponse = JSON.parse(uploadResponse);
		    			  if(jsonUploadResponse.result==="success"){
		    				  
		    				  var productOptionVo = [];
		    				  for(var i=0; i<$(".option-value-list .form-group").length; i++){
		    					  var optionDiv = $(".option-value-list .form-group")[i].innerText.split('\n');
		    					  for(var j=0; j<optionDiv[1].split(' ').length; j++){
		    						  productOptionVo.push({
			    						  optionName: optionDiv[0].trim(),
			    						  optionValue: optionDiv[1].split(' ')[j]
			    					  });
		    					  }
		    				  }
		    				  
		    				  var productQuantityVo = [];
		    				  for(var i=0; i<$("#quantity-result").html().split("<br>").length-1; i++){
		    					  var quantityDiv = $("#quantity-result").html().split("<br>");
		    					  productQuantityVo.push({
		    						  optionCode: quantityDiv[i].split(" : ")[0],
		    						  realQuantity: parseInt(quantityDiv[i].split(" : ")[1]),
		    						  availableQuantity: parseInt(quantityDiv[i].split(" : ")[1])
		    					  });
		    				  }
		    				  
		    				  // 필수 항목들
		    				  var registerData = {
		    						productCategoryNo: parseInt($($("#product-category .selected")[0].parentElement).data("no")),
		    						title: $("#register-title").val(),
		    						price: parseInt($("#price").val()),
		    						description: $("#register-description").val(),
		    						isdisplay: $("#isdisplay input:checked").val()==="isdisplay-true",
		    						issell: $("#issell input:checked").val()==="issell-true",
		    						isdisplayMain: $("#isdisplay-main input:checked").val()==="isdisplay-main-true",
		    						productOptionVo: productOptionVo,
		    						productQuantityVo: productQuantityVo
		    				  }
		    				  
		    				  // 선택 항목들
		    				  if(jsonUploadResponse.data!==null && jsonUploadResponse.data.repImage!==undefined)
		    					  registerData["image"] = jsonUploadResponse.data.repImage;
		    				  else
		    					  registerData["image"] = null;
		    				  
		    				  if($("#mileageAdd").val()=="")
		    					  registerData["mileageAdd"] = parseInt("0");
		    				  else
		    					  registerData["mileageAdd"] = parseInt($("#mileageAdd").val());
		    				  
		    				  if($("#shippingPrice").val()=="")
		    					  registerData["shippingPrice"] = parseInt("0");
		    				  else
		    					  registerData["shippingPrice"] = parseInt($("#shippingPrice").val())
		    				  
		    				  if($("#descriptionDetail").val()!="")
		    					  registerData["descriptionDetail"] = $("#descriptionDetail").val();
		    				  else
		    					  registerData["descriptionDetail"] = null;
		    				  
		    				  var productImageVo = [];
		    				  if(jsonUploadResponse.data!==null && jsonUploadResponse.data.addImage!==undefined){
			    				  for(var i=0; i<jsonUploadResponse.data.addImage.length; i++){
			    					  productImageVo.push({imageDetail: jsonUploadResponse.data.addImage[i]});
			    				  }
			    				  
			    				  registerData["productImageVo"] = productImageVo;
		    				  }else{
		    					  registerData["productImageVo"] = null;
		    				  }
		    				  
		    				  // 상품 등록 요청
		    				  $.ajax({
		    		    		  url: "/admin/manage/product/register",
		    		    		  data: JSON.stringify(registerData),
		    		    		  dataType: 'json',
		    		    		  contentType: "application/json; charset=UTF-8",
		    		    		  type: 'post',
		    		    		  headers: {'X-CSRF-TOKEN': '${_csrf.token}'},
		    		    		  success: function(registerResponse){
		    		    			  if(registerResponse.result==="success"){
		    		    				  alert("상품 등록 성공");
		    		    				  window.location.href = "/admin/manage/product/register";
		    		    			  }
		    		    		  }
		    				  });
		    			  }
		    		  }
		    	  });
		    	  
		    	  return false;
		      });
		});
		      
	</script>

  </body>
</html>
