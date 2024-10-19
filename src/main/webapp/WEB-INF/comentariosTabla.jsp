<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tabla de comentarios</title>
</head>
<body>
<h1>Esta es la tabla de comentarios</h1>

<table>
	<thead>
		<tr>
			<th>ID</th>		
			<th>Comentario</th>
			<th>libro_id</th>
			<th>usuario_id</th>

			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${comentarios}" var="comentario">
		<tr>
			<td><c:out value="${comentario.id }"/></td>
			<td><c:out value="${comentario.comentario }"/></td>
			<td><c:out value="${comentario.libro_id }"/></td>
			<td><c:out value="${comentario.usuario_id }"/></td>


		</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>