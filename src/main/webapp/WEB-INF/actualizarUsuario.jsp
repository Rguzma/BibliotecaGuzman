<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--  form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario para actualizar usuario</title>
</head>
<body>
<div>
<div>
<p>Su usuario: <c:out value="${usuarioSession}"></c:out></p>
</div>
<div>
	<p>
		<a class=regresar href="/libros"> Regresar a la biblioteca
		</a>
	</p>
	<p>
	<c:if test= "${usuarioSession == usuario.usuario }">
			<a class=regresar href="/informacion/<c:out value="${id}"></c:out>"> Regresar  a la información de su usuario
			</a>
	</c:if>
	<c:if test= "${usuarioSession != usuario.usuario }">
			<a class=regresar href="/usuarios"> Regresar  a la lista de usuarios
			</a>
	</c:if>
	</p>
	<p>
		<form action="/logout" method="POST">
		<input type="hidden" name="logout" value="logout"/>
		<button>Cerrar Sesión</button>
		</form>
	</p>
</div>
</div>


<h1>Actualizar Usuario
		<c:out value="${usuario.nombre}"></c:out>
		(<c:out value="${usuario.usuario}"></c:out>)
</h1>
<fieldset>
<form action="/user/actualizar/<c:out value="${usuario.id}"></c:out>" method="POST">
    <p>
        <label for="usuario">Usuario*</label>
        <input id="usuario" name="usuario" value="<c:out value="${usuario.usuario}"></c:out>"/>
    </p>
        <p>
        <label for="nombre">Nombre*</label>
        <input id="nombre" name="nombre" value="<c:out value="${usuario.nombre}"></c:out>"/>
    </p>

    <p>
        <label for="password">Password*</label>
        <input id="password" name="password"/ type=password>
    </p>
    <p>
        <label for="confirmPassword">Confirmar Password*</label>
        <input id="confirmPassword" name="confirmPassword" type="password"/>
    </p>
    <p>
        <label for="email">Email*</label>
     
        <input type=email id="email" name="email" value="<c:out value="${usuario.email}"></c:out>"/>
    </p>   
    <p>
        <label for="numero">Número</label>    
        <input type=number id="numero" name="numero" value="<c:out value="${usuario.numero}"></c:out>"/>
    </p>
    <p>
    	<p> Es este usuario un administrador?* </p>
        <label for="administrador">No es administrador</label>     
        <input type="radio" id="administrador" name="administrador"  value=0 />
     
        <label for="administrador">Sí es administrador</ label>    
        <input type="radio" id="administrador" name="administrador" value=1 />
    </p>
     	<button type="submit" value="Submit">Editar</button>
     	<div>
		<c:out value="${ErrorMessage}"></c:out>
		</div>
     </fieldset>
</form>    
</body>
</html>