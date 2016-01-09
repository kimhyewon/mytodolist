<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/showTasks.css">
</head>
<body>
	<div id="navi_con">
		<span class="go_board"><a href="/board">My Board</a></span>
		|
		<span class="logout"><a href="/logout">Log out</a></span>
	</div>
	<h1>${boardName}</h1>
	<div id='category_con'>
		+ add categorys<br /><br />
		<form method="post" action="/category">
			<div id='create_category_con'>
				<div id="hide_category"></div>
				Name
				<input type="text" name="categoryName" id="input_category_name"><br />
				Select a color<br />
				<div id="color_con">
					<span class="color1"></span>
					<span class="color2"></span>
					<span class="color3"></span>
					<span class="color4"></span>
					<span class="color5"></span>
				</div>
				<br />
				<input type="radio" class="category_radio_btn" name="color" value="F29797">
				<input type="radio" class="category_radio_btn" name="color" value="FCC300">
				<input type="radio" class="category_radio_btn" name="color" value="99C843">
				<input type="radio" class="category_radio_btn" name="color" value="8CCCFF">
				<input type="radio" class="category_radio_btn" name="color" value="AE9EDE">
				<br />
				<input type="hidden" name="boardId" value="${boardId}" />
				<input type="submit" value="OK" id="category_buttom">
			</div>
		</form>
		<div id="show_categorys_con">
			<a href="/article?boardId=${boardId}&color=all">
				<div id="see_all">
					ALL
				</div>
			</a>
			<table class="each_category">
 				<tr class="category_color">
 					<c:forEach items="${categorys}" var="each">
 <!-- 이 부분  -->
    						<td>
    							<a href="/article?boardId=${boardId}&color=${each.color}">
    							<div id="each_color" style="background-color:#${each.color};"></div>
    							</a>
    						</td>

    				</c:forEach>
  				</tr>
  				<tr class="category_name">
  					<c:forEach items="${categorys}" var="each">
    					<td>${each.categoryName}</td>
    				</c:forEach>
  				</tr>
  			</table>
		</div>
	</div>
	<section id="todoapp">
		<!-- task 입력 -->
		<form method="post" action="/article">
			<header id="header">
				<div id="category_btn"></div>
				<div id="category_select_box">
					<div id="color_set">
						<span class="color_sample1"></span>
						<span class="color_sample2"></span>
						<span class="color_sample3"></span>
						<span class="color_sample4"></span>
						<span class="color_sample5"></span>
						<input type="checkbox" class="color_radio_btn" name="categoryId" value="1">
						<input type="checkbox" class="color_radio_btn" name="categoryId" value="2">
						<input type="checkbox" class="color_radio_btn" name="categoryId" value="3">
						<input type="checkbox" class="color_radio_btn" name="categoryId" value="4">
						<input type="checkbox" class="color_radio_btn" name="categoryId" value="5">
					</div>
				</div>
				<input type="text" name="content" id="new-todo" placeholder="+  What needs to be done?" autofocus>
				<input type="text" name="doneDate" id="testDatepicker">
				<input type="hidden" name="isdone" value="false">
				<input type="hidden" name="boardId" value="${boardId}" />
				<input type="submit" value="확인" id="article_creat_btn">
			</header>
		</form>
		<section id="main">
			<ul id="todo-list">
				<c:forEach items="${articles}" var="each" varStatus="status">	
					<c:choose>
						<c:when test="${each.isDone == '1'}">
							<li class="{}">
								<div class="category_label" style="background-color:#${each.color};"></div>
								<div class="date_label"></div>
								<script>
									var targetString = '${each.doneDate}';
									var month = targetString.substring(5, 7);
									var day = targetString.substring(8, 10);
									var map = {'month':month, 'day':day};
									document.getElementsByClassName("date_label")[${status.count}-1].innerHTML = map.month+'.'+map.day;
								</script>

								<div class="view">
								 	<label>${each.content}</label>
									<form method="post" action="/article/complete">
										<button id="complete"></button>		
										<input type="hidden" name="articleId" value="${each.articleId}" />
										<input type="hidden" name="boardId" value="${boardId}" />
									</form>

									<form method="post" action="/article/delete">
										<button id="destroy"></button>
										<input type="hidden" name="articleId" value="${each.articleId}" />
										<input type="hidden" name="boardId" value="${boardId}" />
									</form>
								</div>
							</li>
						</c:when>
						<c:otherwise>
							<li class="completed">
								<div class="category_label" style="background-color:#${each.color};"></div>
								<div class="date_label"></div>
								<script>
									var targetString = '${each.doneDate}';
									var month = targetString.substring(5, 7);
									var day = targetString.substring(8, 10);
									var map = {'month':month, 'day':day};
									document.getElementsByClassName("date_label")[${status.count}-1].innerHTML = map.month+'.'+map.day;
								</script>

								
								<div class="view">
								 	<label>${each.content}</label>
									<form method="post" action="/article/complete">
										<button id="complete"></button>		
										<input type="hidden" name="articleId" value="${each.articleId}" />
										<input type="hidden" name="boardId" value="${boardId}" />
									</form>

									<form method="post" action="/article/delete">
										<button id="destroy"></button>
										<input type="hidden" name="articleId" value="${each.articleId}" />
										<input type="hidden" name="boardId" value="${boardId}" />
									</form>
								</div>
							</li>
					 	</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</section>
	</section>
	<footer>
		
	</footer>
	

	<script type="text/javascript" src="js/showTasks.js"></script>
	<script type="text/javascript" src="js/taskAjax.js"></script>
</body>
</html>