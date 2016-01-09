<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/board.css">

</head>
<body>
	<div id="navi_con">
		<span class="go_board"><a href="/board">My Board</a></span>
		|
		<span class="logout"><a href="/logout">Log out</a></span>
	</div>
	<div id="container">
		<div id="back_con">
			<div id="myboards"></div>
			<c:forEach items="${boards}" var="each">	
				<span class="list">
					<a href="/article?boardId=${each.boardId}&color=all">${each.listName}</a>
					<div class="deco_con">
						<span class="tree1"></span>
						<span class="tree2"></span>
					</div>
				</span>
			</c:forEach>

			<div id="add_btn"></div>
		</div>

		<form method="post" action="/board">
			<div id="add_list_con">
				<div class="add_desc">Enter new list name</div>
				<input type="text" name="listName" id="list_input"><br />
				<input type="submit" value="CREATE" id="list_buttom">
			</div>
		</form>
		
	</div>
	<footer>
	</footer>

	<script type="text/javascript">
	$( init );
	  	function init() {
	  	$('.list').draggable( {
	    	containment: '#container',
	    	cursor: 'move',
	    	snap: '#container'
	  	});
	}
  	</script>
  	<script type="text/javascript" src="js/board.js"></script>
</body>
</html>