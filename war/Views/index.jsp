<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.springmvc.note.model.Note" %>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notes</title>
<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.5/flatly/bootstrap.min.css" rel="stylesheet" integrity="sha256-sHwgyDk4CGNYom267UJX364ewnY4Bh55d53pxP5WDug= sha512-mkkeSf+MM3dyMWg3k9hcAttl7IVHe2BA1o/5xKLl4kBaP0bih7Mzz/DBy4y6cNZCHtE2tPgYBYH/KtEjOQYKxA==" crossorigin="anonymous">
<link href="/css/main.css" rel="stylesheet" />
</head>
<body>
<div class="container">
	<h1>NOTE APP</h1>
	<ul class="list-group">
	  <%
		if(request.getAttribute("notes")!=null){
		List<Note> notes = (List<Note>)request.getAttribute("notes");
		if(!notes.isEmpty()){
			for(Note note : notes){
	   %>
		<li class="list-group-item" ><a class="btn btn-danger btn-xs pull-right del-btn" data-key="<%=note.getKey() %>"  href="delete/<%=note.getKey()%>">Delete</a><a class="btn btn-info btn-xs pull-right del-btn"  href="edit/<%=note.getKey()%>">Edit</a><span class="badge"><%=note.getDate() %></span><%=note.getNote()%></li>	
		<%}}}%>
	</ul>
</div>
</body>
</html>