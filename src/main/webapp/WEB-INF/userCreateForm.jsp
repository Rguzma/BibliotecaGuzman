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
<title>Formulario para crear un usuario</title>
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
			<a class=regresar href="/usuarios"> Regresar  a la lista de usuarios
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


<h1>Nuevo Usuario</h1>
<fieldset>
<form action="/user/create" method="POST">
    <p>
        <label for="usuario">Usuario*</label>
        <input id="usuario" name="usuario"/>
    </p>
    <p>
        <label for="nombre">Nombre*</label>
        <input id="nombre" name="nombre"/>
    </p>
    <p>
        <label for="password">Password*</label>
        <input id="password" name="password" type="password"/>
    </p>
     <p>
        <label for="confirmPassword">Confirmar Password*</label>
        <input id="confirmPassword" name="confirmPassword" type="password"/>
    </p>
    <p>
        <label for="email">Email*</label>
     
        <input id="email" name="email" type="email"/>
    </p>   
    <p>
        <label for="numero">Número</label>    
        <input id="numero" name="numero" type="number"/>
    </p>
    <p>
    	<p> Es este usuario un administrador? </p>
        <label for="administrador">No es administrador</label>     
        <input type="radio" id="administrador" name="administrador"  value=0 />
     
        <label for="administrador">Sí es administrador</ label>    
        <input type="radio" id="administrador" name="administrador" value=1 />
    </p>
     <button type="submit" value="Submit">Registrar</button>
     <div>
		<c:out value="${ErrorMessage}"></c:out>
	</div>
     </fieldset>
</form>    
</body>
</html>