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
<title>Información de Usuarios</title>
</head>
<body>
<div>
<div>
<p>Su usuario: <c:out value="${usuario}"></c:out></p>
</div>
<div>
	<p>
		<a class=regresar href="/libros"> Regresar a la biblioteca
		</a>
	</p>
	<p>
		<form action="/logout" method="POST">
		<input type="hidden" name="logout" value="logout"/>
		<button>Cerrar Sesión</button>
		</form>
	</p>
</div>
</div>
<div>
<h1>
	Lista de Usuarios
</h1>

<div>
<h3>
Otros Usuarios de la biblioteca Guzman Mesen
</h3>
<table>
	<thead>
		<tr>
			<th>Nombre</th>
			<th>Email</th>
			<th>Numero</th>
			<th>Administrador</th>
		<c:if test= "${si_es_administrador}">
			<th>Editar/Eliminar</th>
		</c:if>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios}" var="usuario">
		<tr>
			<td><c:out value="${usuario.nombre }"/></td>
			<td><c:out value="${usuario.email }"/></td>
			<td>
			
				<c:out value="${usuario.numero }"/></td>
			<td><c:if test= "${usuario.administrador == 1}">
					Si
				</c:if>
				<c:if test= "${usuario.administrador == 0}">
					No
				</c:if>
			</td>
			<c:if test= "${si_es_administrador}">
				<td>
					<a class=editUser href="/edit/usuarios/<c:out value="${usuario.id}"/>" >
					<button>Editar Usuario</button>
					</a>
					<form action="/delete/usuarios/<c:out value="${usuario.id}"/>" method="POST">
					<input type="hidden" name="method" value="DELETE" />
					<button type="submit"> Eliminar Usuario</button>
					</form>
				</td>
			</c:if>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<c:if test= "${si_es_administrador}">
<div>
	<a class="crearUsuario" href="/crearUsuario">
	Crear Nuevo Usuario
	</a>
</div>
</c:if>
</body>
</html>