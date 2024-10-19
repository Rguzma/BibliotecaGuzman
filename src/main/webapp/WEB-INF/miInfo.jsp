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
<title>Mi información</title>
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
Esta es la información de su cuenta
</h1>
<p>Nombre: <c:out value="${nombre}"></c:out></p>
<p>Usuario: <c:out value="${usuario}"></c:out></p>
<p>Email: <c:out value="${email}"></c:out></p>
<p>Número: <c:out value="${numero}"></c:out></p>
<p>Es administrador: <c:out value="${administrador}"></c:out></p>


	<p>
		<a class=editUser href="/edit/usuarios/<c:out value="${id}"/>" >
		<button>Editar sus datos</button>
		</a>
		<form action="/delete/usuarios/<c:out value="${id}"/>" method="POST">
		<input type="hidden" name="method" value="DELETE" />
		<button type="submit"> Eliminar su cuenta</button>
		</form>
	</p>
</div>

</body>
</html>