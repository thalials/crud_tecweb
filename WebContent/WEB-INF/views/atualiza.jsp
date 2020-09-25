<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Atualizar</title>
</head>
<body>
	<form action='Atualiza' method='post'>
		to-dooo: <input type='text' name='${param.toDo}'><br>
		Doing: <input type='text' name='${param.doing}'><br>
		Done: <input type='text' name='${param.done}'><br>
		<input type='hidden' name='${param.id}'>
		<input type='submit' value='atualizar'>
	</form>
</body>
</html>