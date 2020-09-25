<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ page import="java.util.*, br.edu.insper.model.*, br.edu.insper.* " %>
<title>Alteracoes</title>
</head>
<body>
	<% 
	Usuarios usuario = (Usuarios) request.getAttribute("usuario"); 
	Organization organization = (Organization) request.getAttribute("organization"); 
 	%>
		
	<h3> Editar task </h3>
	
	<form action="AtualizaTask" method="post">
		Tarefa: <input type="text" name="todo" value="<%= organization.getToDo() %>">
		<br>
		Prazo final: <input type="date" name="deadline" value="<%= organization.getDeadline() %>">
		<br>
		
		<input type="hidden" name="id_user" value="<%=usuario.getUsuario_id() %>">
		<input type="hidden" name="organization_id" value="<%=organization.getId() %>">
		<input type="submit" name="organization">
	
	</form>

</body>
</html>