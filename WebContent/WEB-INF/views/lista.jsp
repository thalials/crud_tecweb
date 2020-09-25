<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<%@ page import="java.util.*, br.edu.insper.* " %>
	<form action='Cria' method='post'>
		to-do: <input type='text' name='todo'><br>
		Doing: <input type='text' name='doing'><br>
		Done: <input type='text' name='done'><br>
		<input type='submit' value='gravar'>
	</form>
	<table border = "1">
		<c:forEach var="organization" items="${organizations}">
			
			<tr>
			<td>${organization.toDo}</td>
			<td>${organization.doing}</td>
			<td>${organization.done}</td>
			<td>
				<form action='Remove' method='post'>
					<input type='hidden' name='id' value='${organization.id}'>
					<input type='submit' value='remover'>
				</form>
						
			</td>
			<td>
				<form action='Atualiza' method='get'>
					<input type='hidden' name='id' value='${organization.id}'>
					<input type='hidden' name='todo' value='${organization.toDo}'>
					<input type='hidden' name='doing' value='${organization.doing}'>
					<input type='hidden' name='done' value='${organization.done}'>
					<input type='submit' value='atualizar'>
				</form>
			</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>