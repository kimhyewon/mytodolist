<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<section id="main_section">
		<div id="main_body">
			<div id="santa_hat"></div>
			<div id="title"></div>
			<div id="mascot"></div>

			<form method="post" action="/login">
				<c:if test="${not empty errorMessage}">
					<div class="control-group">
						<label class="error">${errorMessage}</label>
					</div>
				</c:if>

				<ul>
					<li class="id_section">
						<img src="img/id.jpg" id="id_img" >
						<input type="text" name="userId" id="id_input" style="z-index:-1;">
					</li>
					<li class="pw_section">
						<img src="img/pw.jpg" id="pw_img" >
						<input type="password" name="password" id="pw_input">
					</li>
				</ul>
				<input type="image" src="img/login_btn.jpg" border="0" onfocus='this.blur()'>
			</form>
		</div>
	</section>
	<footer>
	</footer>
</body>
</html>