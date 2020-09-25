<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ page import="java.util.*, br.edu.insper.model.* " %>
<title>Principal</title>
</head>
<body>
	<%
		Usuarios usuario = (Usuarios) request.getAttribute("usuario");
		List<Organization> organizations = (List<Organization>) request.getAttribute("organizations");
	%>
	
	<h1>
		Olá, <%= usuario.getUsuario() %>, quais as tarefas pra hoje? 
	</h1>

	<h2> Nova tarefa </h2>
	<form action='CriaTask' method='post'>
		to-do: <input type='text' name='todo'>
		<br>		
		Deadline: <input type='date' name='deadline'>
		<br>
		<input type='hidden' name='id_user' value="<%=usuario.getUsuario_id() %>">
		<input type='submit' value='gravar'>
		
	</form>
	
	<br>
	<h2> Tarefas do dia </h2>
	<table border='2'>
		<tr>
			<td>Lista para fazer </td>
			<td>Prazo final</td>

		</tr>
		<%
			for (Organization organization : organizations) {
				out.println("<tr><td>" + organization.getToDo() + "</td>");
				out.println("<td>" + organization.getDeadline().getTime().toString() + "</td>");
				
				out.println(
						"<td><form action=\"./Remove\" method=\"get\"> <button type=\"submit\" name=\"remove\" value=\""
								+ organization.getId() + "\" >APAGAR </button></td></form>");
				out.println(
						"<td><form action=\"EditaTask\" method=\"post\"><button type=\"submit\" name=\"edit\" value=\""
								+ organization.getId() + "\">EDITAR </button></td></tr></form>");
				
			}
		%>
	</table>

</body>
</html>