<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/boardStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>글 변경</title>
</head>
<body>
<div id="s_left"></div>
<div id="s_left"></div>
<div id="contents">

<center>	<h1>게시판 새글 쓰기</h1></center>
	<hr>
	<center>
	<form action="/board/post/${board.num }" method="post">
		<input type="hidden" name="_method" value="put"/>
		 말머리 <select id="heading" name="heading">
				<option value="[일반]">일반</option>
				<option value="[질문]">질문</option>
				<option value="[꿀팁]">꿀팁</option>
		</select>
		제목  <input type="text" name="title" value="${board.title }" size="61">
		
		<br/><br/>
		<table  border="1" cellpadding="0" cellspacing="0">
			
			<tr>
				<td bgcolor="orange">내용</td>
				<td colspan="3"><textarea name="content" rows="20" cols="80">${board.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="글  수정">
				</td>
			</tr>
		</table>
	</form>
	<hr>
	</center>
</div>
<div id=s_right></div>

</body>
</html>