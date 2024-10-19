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
<title>Tabla de libros</title>
</head>
<body>
<div>
	<div>
		<h3>¡Bienvenido! 
		<c:out value="${nombre}"></c:out>
		(<c:out value="${usuario}"></c:out>)
		</h3>
	</div>
	
	<div>
		<p><a class=head_link href="/usuarios" >Ver información de usuarios</a></p>
		<p><a class=head_link href="/informacion/<c:out value="${id}"></c:out>" >Ver mi información</a></p>
		<p>
			<form action="/logout" method="POST">
			<input type="hidden" name="logout" value="logout"/>
			<button>Cerrar Sesión</button>
		</form>
		</p>
	</div>
</div>
<div>
	<h1>Esta es la biblioteca Guzmán Mesén</h1>
	<div>
		<form action="/libros/busqueda/" method="GET">
			<label for="q"></label>
			<input type="text" id="q" name="q" placeholder="Search..." >
			<button type="submit" value="submit">Buscar</button>
			
			<div>
  				<input type="checkbox" id="tipo" name="tipo" value="tipo">
  				<label for="tipo"> Tipo</label><br>
  				<input type="checkbox" id="genero_o_materia" name="genero_o_materia" value="genero_o_materia">
  				<label for="genero_o_materia"> Género o materia</label>
  				<input type="checkbox" id="donde_esta_el_libro" name="donde_esta_el_libro" value="donde_esta_el_libro">
  				<label for="donde_esta_el_libro"> Ubicación</label><br>
  				<input type="checkbox" id="dueño" name="dueño" value="dueño_id">
  				<label for="dueño"> Dueñ@</label><br>
  			</div>
		</form>
	
	</div>

<table>
	<thead>
		<tr>	
			<th>Título</th>
			<th>Autor</th>
			<th>Editorial</th>
			<th>Año</th>
			<th>Tipo</th>
			<th>Género o materia</th>
			<th>País</th>
			<th>Dónde está el libro?</th>
			<th>Dueño</th>
			<th>Detalles</th>
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${libros}" var="libro">
		<tr>
			<td><c:out value="${libro.titulo }"/></td>
			<td><c:out value="${libro.autor }"/></td>
			<td><c:out value="${libro.editorial }"/></td>
			<td><c:out value="${libro.año }"/></td>
			<td><c:out value="${libro.tipo }"/></td>
			<td><c:out value="${libro.genero_o_materia }"/></td>
			<td><c:out value="${libro.pais }"/></td>
			<td><c:out value="${libro.donde_esta_el_libro }"/></td>
			<td><c:out value="${libro.dueño_id }"/></td>
			<td><a class=detalles_link href="libro/detalles" >Ver detalles</a></td>

		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>