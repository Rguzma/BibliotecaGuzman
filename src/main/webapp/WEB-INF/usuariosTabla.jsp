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
<title>Tabla de usuarios</title>
</head>
<body>
<h1>Usuarios de la biblioteca Guzman</h1>

<table>
	<thead>
		<tr>
			<th>Usuario</th>
			<th>Nombre</th>
			<th>password</th>
			<th>Email</th>
			<th>Numero</th>
			<th>Administrador</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios}" var="usuario">
		<tr>

			<td><c:out value="${usuario.usuario }"/></td>
			<td><c:out value="${usuario.nombre }"/></td>
			<td><c:out value="${usuario.password }"/></td>
			<td><c:out value="${usuario.email }"/></td>
			<td><c:out value="${usuario.numero }"/></td>
			<td><c:out value="${usuario.administrador }"/></td>


		</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>