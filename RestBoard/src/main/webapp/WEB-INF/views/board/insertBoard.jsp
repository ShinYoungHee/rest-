<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/boardStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>게시판 새글 쓰기</title>
</head>
<body>
<div id="s_left"></div>
<div id="contents">

<center>	<h1>게시판 새글 쓰기</h1></center>
	<hr>
	<center>
	<form action="/board/post" method="post">
		 말머리 <select id="heading" name="heading">
				<option value="[일반]">일반</option>
				<option value="[질문]">질문</option>
				<option value="[공략]">공략</option>
		</select>
		제목  <input type="text" name="title" placeholder="게시글 제목을 입력하세요" size="61"><br/><br/>
		작성자 <input type="text" name="name" size="10">
		<br/><br/>
		<table  border="1" cellpadding="0" cellspacing="0">
			
			<tr>
				<td bgcolor="orange">내용</td>
				<td colspan="3"><textarea name="content" rows="20" cols="80"></textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="글 올리기">
				</td>
			</tr>
		</table>
	</form>
	<hr>
	
	<a href="/board">[목록으로]</a>
	</center>
</div>
<div id="s_right"></div>

</body>
</html>