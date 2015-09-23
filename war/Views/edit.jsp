<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.springmvc.note.model.Note" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Note</title>
<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.5/flatly/bootstrap.min.css" rel="stylesheet" integrity="sha256-sHwgyDk4CGNYom267UJX364ewnY4Bh55d53pxP5WDug= sha512-mkkeSf+MM3dyMWg3k9hcAttl7IVHe2BA1o/5xKLl4kBaP0bih7Mzz/DBy4y6cNZCHtE2tPgYBYH/KtEjOQYKxA==" crossorigin="anonymous">
<link href="/css/main.css" rel="stylesheet" />
</head>
<body>
<%
Note note = new Note();
if(request.getAttribute("note")!= null){
	note = (Note)request.getAttribute("note");
}
%>
<div class="container">
<h1>Edit Note</h1>
<form role="form" method="post">
<input type="hidden" name="key" id="key" value="<%=note.getKey()%>" /> 
  <div class="form-group">
	  <label for="email">Email</label>
	  <input type="email" class="form-control" name="email" id="email" value="<%=note.getEmail() %>">
  </div>
  <div class="form-group">
    <label for="note">Note</label>
    <textarea class="form-control" rows="5" name="note" ><%=note.getNote() %></textarea>
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>
</div>
</body>
</html>