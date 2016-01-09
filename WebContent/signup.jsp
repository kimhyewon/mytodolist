<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<link rel="stylesheet" type="text/css" href="css/signup.css">
</head>
<body>
	<section id="main_section">
		<div id="main_body">
			<div id="santa_hat"></div>
			<div id="title"></div>
			<div id="mascot"></div>

			<c:set var="actionUri" value="/user/create" />
			
			<form name="user" method="post" action="${actionUri}">
				<c:if test="${not empty errorMessage}">
					<div class="control-group">
						<label class="error">${errorMessage}</label>
					</div>
				</c:if>
				<ul>
					<li class="id_section">
						<img src="img/id.jpg" id="id_img">
						
						<c:choose>
				    	<c:when test="${empty userId}">
							<input type="text" name="userId" id="id_input">
						</c:when>
						<c:otherwise>
							<input type="text" name="userId" value="${userId}" id="id_input">
						</c:otherwise>
						</c:choose>
					</li>
					<li class="pw_section">
						<img src="img/pw.jpg" id="pw_img">
						<input type="password" id="pw_input" name="password" value="${user.password}" placeholder="">
						
					</li>
				</ul>
				<input type="image" src="img/signup_btn.jpg" border="0" onfocus='this.blur()'>
			</form>
		</div>
	</section>
	<footer>
	</footer>
</body>
</html>